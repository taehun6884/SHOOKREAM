package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import db.JdbcUtil;
import mail.GoogleMailAuthenticator;
import vo.AuthBean;
import vo.MemberBean;
import vo.ReviewBean;
import vo.WishBean;

public class MemberDAO {
private MemberDAO() {}
	
	private static MemberDAO instance = new MemberDAO();

	public static MemberDAO getInstance() {
		return instance;
	}
	// ----------------------------------------------------------------------------------
	// 데이터베이스 접근에 사용할 Connection 객체를 Service 객체로부터 전달받기 위한
	// Connection 타입 멤버변수 선언 및 Setter 메서드 정의
	private Connection con;

	public void setConnection(Connection con) {
		this.con = con;
	}

	
	// ----------------------------------------------------------------------------------
	// 로그인
	public boolean isLoginUser(String id, String pass) {
		boolean isLogintUser = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT member_id,member_pass from member where member_id=? and member_pass=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
		
			rs = pstmt.executeQuery();
			if(rs.next()) {
				isLogintUser = true;
			}
		} catch (SQLException e) {
			System.out.println("구문 오류 - isLoginUser");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return isLogintUser;
	}
	// 로그인


	
	// 회원가입
	public int insertMember(MemberBean member) {
		int insertCount = 0;
		
		PreparedStatement pstmt = null, pstmt2 = null;
		ResultSet rs = null;
		
		try {
			int member_idx = 1; // 회원 idx 처리
			String sql = "SELECT MAX(member_idx) FROM member";
			pstmt= con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				member_idx = rs.getInt(1) + 1;
			} 
			
			sql = "INSERT INTO member VALUES(?,?,?,?,?,now(),?,?,?,?,'N')";
			pstmt2= con.prepareStatement(sql);
			
			pstmt2.setInt(1, member_idx);
			pstmt2.setString(2, member.getMember_id());
			pstmt2.setString(3, member.getMember_name());
			pstmt2.setString(4, member.getMember_pass());
			pstmt2.setString(5, member.getMember_email());
			pstmt2.setString(6, member.getMember_phone());
			pstmt2.setInt(7, 0);
			pstmt2.setInt(8, 0);
			pstmt2.setString(9, member.getMember_address());
			
			insertCount = pstmt2.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류! - insertMember()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(pstmt2);
		}
		return insertCount;
	}
	// 회원삭제
	
	public boolean isDeleteUser(String id, String pass) {
		int deleteCount = 0;
		boolean isDeleteSuccess = false;
		PreparedStatement pstmt = null;
		
		try {
			
			String sql = "DELETE FROM member WHERE member_id=? AND member_pass=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			deleteCount = pstmt.executeUpdate();
			if(deleteCount>0) {
				isDeleteSuccess =true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// DB 자원 반환
			JdbcUtil.close(pstmt);
		}
		
		return isDeleteSuccess;
	} //회원삭제
	
	// 회원 정보 수정 updateMember()
	public boolean updateMember(MemberBean member,boolean isChangePass) {
		int updateMember = 0;
		boolean result = false;
		PreparedStatement pstmt = null;
		
		try {
			// 패스워드 변경 여부에 따른 각각의 SQL 구문 작성
			String sql = "";
			if(isChangePass) { //패스워드 변경시
				sql = "UPDATE member "
								+ "SET "
										+ " member_name=?"
										+ " ,member_id=?,"
										+ " member_pass=?,"
										+ " member_address=?,"
										+ " member_email=?,"
										+ " member_phone=?"
									+ "WHERE member_id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, member.getMember_name());
				pstmt.setString(2, member.getMember_id());
				pstmt.setString(3, member.getMember_pass());
				pstmt.setString(4, member.getMember_address());
				pstmt.setString(5, member.getMember_email());
				pstmt.setString(6, member.getMember_phone());
				pstmt.setString(7, member.getMember_id());
				
				result = true;
			} else { //패스워드 미변경시
				sql = "UPDATE member "
								+ "SET "
									+ " member_name=?,"
									+ " member_id=?,"
									+ " member_address=?,"
									+ " member_email=?,"
									+ " member_phone=?"
								+ "WHERE member_id=?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, member.getMember_name());
				pstmt.setString(2, member.getMember_id());
				pstmt.setString(3, member.getMember_address());
				pstmt.setString(4, member.getMember_email());
				pstmt.setString(5, member.getMember_phone());
				pstmt.setString(6, member.getMember_id());
			}
			
			updateMember = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("sql 구문 오류 - updateMember()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return result;
		
	} // 회원 정보 수정 끝
	
	
	// 로그인 판별 작업 수행 또는 
		// 게시물 수정 권한 여부를 판별할 
		// isRightUser() 메서드 
		// => 파라미터 : MemberDTO 객체(member)   리턴타입 : boolean(isRightUser)
		public boolean isRightUser(MemberBean member) {
			boolean isRightUser = false;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			con = JdbcUtil.getConnection();
			
			try {
				
				//3.
				// 아이디, 패스워드가 일치하는 레코드 검색
				String sql = "SELECT member_id FROM member WHERE member_id=? AND member_pass=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, member.getMember_id()); 
				pstmt.setString(2, member.getMember_pass());
				
				//4.
				rs = pstmt.executeQuery();
				
				// 조회 결과 레코드가 존재할 경우 isLoginSuccess를 true로 변경
				if(rs.next()) {
					isRightUser = true;
				}
			} catch (SQLException e) {
				System.out.println("SQL 구문 오류 발생! - isRightUser()");
				e.printStackTrace();
			} finally {
				//DB 자원 반환(역순)
			 	JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}	
			
			return isRightUser;
			
		} // 회원 수정 isRightUser()메서드 끝


	public MemberBean getInfo(String id) {
		MemberBean vo = null;
		ResultSet rs  = null;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "SELECT * FROM member WHERE member_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo = new MemberBean();
				vo.setMember_idx(rs.getInt("member_idx"));
				vo.setMember_name(rs.getString("member_name"));
				vo.setMember_id(rs.getString("member_id"));
				vo.setMember_pass(rs.getString("member_pass"));
				vo.setMember_address(rs.getString("member_address"));
				vo.setMember_email(rs.getString("member_email"));
				vo.setMember_phone(rs.getString("member_phone"));
				System.out.println(vo);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return vo; 
	} // 회원 목록 끝
	
	public MemberBean getInfo(int idx) {
		MemberBean vo = null;
		ResultSet rs  = null;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "SELECT * FROM member WHERE member_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo = new MemberBean();
				vo.setMember_idx(rs.getInt("member_idx"));
				vo.setMember_name(rs.getString("member_name"));
				vo.setMember_id(rs.getString("member_id"));
				vo.setMember_pass(rs.getString("member_pass"));
				vo.setMember_address(rs.getString("member_address"));
				vo.setMember_email(rs.getString("member_email"));
				vo.setMember_phone(rs.getString("member_phone"));
				System.out.println(vo);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return vo; 
	} // 회원 목록 끝

	// ID 중복 체크를 위한 회원 ID 조회
	public int selectAllId(String id) {
		int result = 0;
		
		ResultSet rs  = null;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "SELECT member_id FROM member WHERE member_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = 1; // 존재할 경우
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 - selectAllId()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return result;
	}
	//----------------------회원 조회(관리자)--------------------------
			public List<MemberBean> selectMemberList() {
				List<MemberBean> memberList = null;
				
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				//전체 회원 조회 -> WHERE 절 넣지 않음.
				String sql = "SELECT * FROM member";
				
				try {
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();
					
					memberList = new ArrayList<MemberBean>();
					while(rs.next()) {
						//있을 경우 member 객체에 저장
						MemberBean member = new MemberBean();
						member.setMember_idx(rs.getInt("member_idx")); // 회원번호
						member.setMember_id(rs.getString("member_id")); // ID
						member.setMember_pass(rs.getString("member_pass")); // 패스워드
						member.setMember_name(rs.getString("member_name")); // 이름(성함)
						member.setMember_email(rs.getString("member_email")); // 이메일
						member.setMember_date(rs.getDate("member_date")); // 가입날짜
						member.setMember_phone(rs.getString("member_phone")); // 휴대폰 번호
						member.setMember_address(rs.getString("member_address")); // 주소
						member.setMember_dec(rs.getInt("member_dec")); // 신고횟수
						member.setMember_point(rs.getInt("member_point")); // 적립금
						
						memberList.add(member);
						//확인작업
						System.out.println(member);
					}
					
				} catch (SQLException e) {
					System.out.println("회원조회 실패 - 관리자");
					e.printStackTrace();
				} finally {
					JdbcUtil.close(rs);
					JdbcUtil.close(pstmt);
				}
				
				return memberList;
			}
			
		
			// 아이디 찾기
		public String findID(MemberBean member) {
			String id = "";
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				// 1) 이름/이메일과 일치하는 아이디 가져오기
				String sql = "SELECT member_id FROM member WHERE member_name=? AND member_email=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, member.getMember_name());
				pstmt.setString(2, member.getMember_email());
				rs = pstmt.executeQuery();
				
				if(rs.next()) { // 이름과 이메일이 일치한 경우
					 id = rs.getString("member_id"); // 1) 아이디
				};
			}catch (Exception e) {
				System.out.println("sql 구문 오류 - findID()");
				e.printStackTrace();
			}
					
			return id;
		} //아이디 찾기() 끝
		
		
		
		
		
	
			
			// 찜한 상품 조회
			public WishBean selectWish(int member_idx) {
				WishBean wish = null;
				
				ResultSet rs  = null;
				PreparedStatement pstmt = null;
				
				try {
					String sql = "SELECT * FROM wish WHERE member_idx=?";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, member_idx);
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						wish = new WishBean();
						wish.setMember_idx(rs.getInt("member_idx"));
						wish.setWish_idx(rs.getInt("wish_idx"));
						wish.setProduct_idx(rs.getInt("product_idx"));
						
						System.out.println(wish);
					}
				} catch (SQLException e) {
					System.out.println("SQL 구문 오류 - selectWish()");
					e.printStackTrace();
				} finally {
					JdbcUtil.close(rs);
					JdbcUtil.close(pstmt);
				}
						return wish;
			}
			public int selectMemberIdx(String sId) {
				int member_idx = 0;
				
				ResultSet rs  = null;
				PreparedStatement pstmt = null;
				
				try {
					String sql = "SELECT member_idx FROM member WHERE member_id=?";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, sId);
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						member_idx = rs.getInt(1);
					}
					
				} catch (SQLException e) {
					System.out.println("SQL 구문 오류 - selectMemberIdx()");
					e.printStackTrace();
				} finally {
					JdbcUtil.close(rs);
					JdbcUtil.close(pstmt);
				}
				
				return member_idx;
			}


			public int insertReview(ReviewBean review) { // 리뷰 작성
				int insertCount = 0;
				
				PreparedStatement pstmt=null, pstmt2=null;
				ResultSet rs = null;
				
				try {
					int review_idx = 1; // 새 글 번호
					
					String sql = "SELECT MAX(review_idx) FROM review";
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery(); // 조회를 실행하면
					
					if(rs.next()) { // 조회 결과가 있을 경우(= 기존 게시물이 하나라도 존재할 경우)
						review_idx = rs.getInt(1) + 1; // 기존 게시물 번호 중 가장 큰 번호(= 조회 결과) + 1
					}
					
					sql = "INSERT INTO review VALUES(?,?,?,?,?,?,now(),?)";
					pstmt2 = con.prepareStatement(sql);

					pstmt2.setInt(1, review_idx);
					pstmt2.setInt(2, review.getProduct_idx());
					pstmt2.setInt(3, review.getMember_idx());
					pstmt2.setString(4, review.getReview_content());
					pstmt2.setString(5, review.getReview_img());
					pstmt2.setString(6, review.getReview_real_img());
					pstmt2.setString(7, review.getRe_order_detail());
					
					System.out.println("리뷰 > DAO 확인 : " + pstmt2);
					
					insertCount = pstmt2.executeUpdate();
					} catch (SQLException e) {
						System.out.println("SQL 구문 오류! - insertReview()");
						e.printStackTrace();
					} finally {
						JdbcUtil.close(rs);
						JdbcUtil.close(pstmt);
						JdbcUtil.close(pstmt2);
					}
				return insertCount;
			}


			// 임시비밀번호(아이디 확인)
			public boolean findPass(MemberBean member) {
				boolean isRightUser = false;
				
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				try {
					String sql ="SELECT member_id FROM member WHERE member_id=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, member.getMember_id());
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						isRightUser=true;
					}
					
				} catch (SQLException e) {
					System.out.println("구문오류-findPass()");
					e.printStackTrace();
				} finally {
					JdbcUtil.close(rs);
					JdbcUtil.close(pstmt);
				}
			
				return isRightUser;
			}

			// 임시비번 -> 비번 수정
			public boolean updatePass(MemberBean member, StringBuilder imsiPw) {
				boolean result = false;
				
				PreparedStatement pstmt = null;
				
				try {
					String sql="UPDATE member SET member_pass=? WHERE member_id=?";
					pstmt.setString(1, imsiPw.toString());
					pstmt.setString(2, member.getMember_id());
					if(pstmt.executeUpdate() > 0) {
						result = true;
						
					}
				} catch (SQLException e) {
					System.out.println("sql구문오류 - updatePass()");
					e.printStackTrace();
				} finally {
					JdbcUtil.close(pstmt);
				}
				
				
				return result;
			}

			// 이메일 인증 위해 auth 테이블에 데이터 넣기
			public boolean insertAuth(AuthBean auth) {
				boolean insertSuccess = false;
				
				PreparedStatement pstmt = null;
				ResultSet rs = null;
			
				try {
					// auth 테이블에 삽입
					String sql = "INSERT INTO auth VALUES(?,?)";
					pstmt= con.prepareStatement(sql);
					
					pstmt.setString(1, auth.getAuth_id());
					pstmt.setString(2, auth.getAuth_authCode());
					
					if(pstmt.executeUpdate()>0) {
						insertSuccess = true;
					};
					
				} catch (SQLException e) {
					System.out.println("sql 구문 오류 - insertAuth()");
					e.printStackTrace();
				} finally {
					JdbcUtil.close(rs);
					JdbcUtil.close(pstmt);
				}
				
				return insertSuccess;
		}


			// 회원가입시 회원가입 쿠폰 지급
         public int insertWelcomCoupon() {
            int insertCount = 0;
            
            PreparedStatement pstmt=null, pstmt2=null, pstmt3=null;
            ResultSet rs = null, rs2 = null;
            
            try {
               int member_idx = 1; // 회원 idx 처리
               String sql = "SELECT MAX(member_idx) FROM member";
               pstmt= con.prepareStatement(sql);
               rs = pstmt.executeQuery();
               
               if(rs.next()) {
                  member_idx = rs.getInt(1) + 1;
               } 
//               System.out.println(member_idx);
               
               // coupon 조회
               int coupon_idx = 0;
               String coupon_name = "";
               int coupon_price = 0;
               
               sql = "SELECT coupon_idx,coupon_name,coupon_price FROM coupon where coupon_name = '회원가입 감사 쿠폰'";
               pstmt2 = con.prepareStatement(sql);
               rs2 = pstmt2.executeQuery(); 
               
               if(rs2.next()) { 
                  coupon_idx = rs2.getInt(1);
                  coupon_name = rs2.getString(2);
                  coupon_price = rs2.getInt(3);
               }
               System.out.println(coupon_idx);
               // member_coupon insert 작업
               sql = "INSERT INTO member_coupon VALUES(?,?,?,?,0,now(),date_add(now(),interval 30 day))";
               pstmt3 = con.prepareStatement(sql);

               pstmt3.setInt(1, member_idx);
               pstmt3.setInt(2, coupon_idx);
               pstmt3.setString(3, coupon_name);
               pstmt3.setInt(4, coupon_price);
               
//               System.out.println(pstmt3);
               
               insertCount = pstmt3.executeUpdate();
               } catch (SQLException e) {
                  System.out.println("SQL 구문 오류! - insertWelcomCoupon()");
                  e.printStackTrace();
               } finally {
                  JdbcUtil.close(rs);
                  JdbcUtil.close(pstmt);
                  JdbcUtil.close(pstmt2);
                  JdbcUtil.close(pstmt3);
               }
            return insertCount;
         }
			

}
