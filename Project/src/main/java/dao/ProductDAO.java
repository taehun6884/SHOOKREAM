package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import db.JdbcUtil;
import java.util.List;
import db.JdbcUtil;
import vo.CouponBean;
import vo.MemberCouponBean;
import vo.OrderBean;
import vo.ProductBean;
import vo.ReviewBean;
import vo.WishBean;
import vo.cartBean;
import vo.imageBean;


public class ProductDAO {
private ProductDAO() {}
	
	private static ProductDAO instance = new ProductDAO();

	public static ProductDAO getInstance() {
		return instance;
	}
	// ----------------------------------------------------------------------------------
	// 데이터베이스 접근에 사용할 Connection 객체를 Service 객체로부터 전달받기 위한
	// Connection 타입 멤버변수 선언 및 Setter 메서드 정의
	private Connection con;

	public void setConnection(Connection con) {
		this.con = con;
	}
	
	
	//--------상품 등록 작업--------------
	public int insertProduct(ProductBean product) {
		int insertCount = 0; 
		int insertCount2 = 0;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		ResultSet rs = null;
		
		try {
			//----------------idx 작업------------------
			String sql = "SELECT MAX(product_idx) FROM product";
			int idx = 1; // 새 글 번호
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { 
				 //true -> 조회결과가 있을 경우 (= 게시물이 하나라도 존재할 경우)
				 //존재하지 않을 경우 rs.next는 false , DB에서는 NULL이 표기된다.
				idx = rs.getInt(1) + 1;
			}
			System.out.println("새글 번호 :" + idx);
			
			//----------------상품 등록----------------------

			sql = "INSERT INTO product VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,now(),?)";
			
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setInt(1, idx); //idx
			pstmt2.setString(2, product.getProduct_name()); //name
			pstmt2.setString(3, product.getProduct_brand()); //brand
			pstmt2.setString(4, product.getProduct_size()); // size
			pstmt2.setInt(5, product.getProduct_price()); // price
			pstmt2.setInt(6, product.getProduct_release_price()); // release price(쿠폰적용가격)
			pstmt2.setInt(7, 0); // buy price(누적가격)
			pstmt2.setInt(8, product.getProduct_amount()); // amount
			pstmt2.setInt(9, 0); // sell_count
			pstmt2.setString(10, product.getProduct_exp()); //요약 설명
			pstmt2.setString(11, product.getProduct_detail_exp()); //상세 설명
			pstmt2.setString(12, product.getProduct_color()); //색상
			pstmt2.setDouble(13, product.getProduct_discount_price()); //할인율
			pstmt2.setInt(14, 0); //상품 좋아요 수 누적

			
			insertCount = pstmt2.executeUpdate();
			
			//----------------이미지 등록------------------
			// image_idx 작업
			if(insertCount > 0) {
				sql = "SELECT MAX(image_idx) FROM image";
				int idx2 = 1; // 새 글 번호
				pstmt3 = con.prepareStatement(sql);
				rs = pstmt3.executeQuery();
				
				if(rs.next()) { 
					 //true -> 조회결과가 있을 경우 (= 게시물이 하나라도 존재할 경우)
					 //존재하지 않을 경우 rs.next는 false , DB에서는 NULL이 표기된다.
					idx2 = rs.getInt(1) + 1;
				}
				
				sql = "INSERT INTO image (image_idx, product_idx, image_main_file, image_real_file1, image_real_file2) VALUES(?,?,?,?,?)";
				pstmt4 = con.prepareStatement(sql);
				pstmt4.setInt(1, idx2);
				pstmt4.setInt(2, idx);
				pstmt4.setString(3, product.getProduct_img());
				pstmt4.setString(4, product.getProduct_img2());
				pstmt4.setString(5, product.getProduct_img3());
				
				insertCount2 = pstmt4.executeUpdate();
			}
			
			
		} catch (SQLException e) {
			System.out.println("상품등록 - 관리자");
			e.printStackTrace();
		} 
		return insertCount2;
	}

	//상품 상품별 사이즈 적용
	public List<String> ProductCategory(String product_name){
		List<String> categorylist = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select distinct(product_size) from product where product_name=? order by product_size";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, product_name);
			rs = pstmt.executeQuery();
			categorylist = new ArrayList<String>();
			while(rs.next()) {
				product_name = rs.getString(1);
				categorylist.add(product_name);
			}
			System.out.println(categorylist);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return categorylist;
	}
	
		//상품 상품별 컬러 적용
		public List<String> ProductColorCategory(String product_name){
			List<String> colorlist = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String sql = "select distinct(product_color) from product where product_name=?";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, product_name);
				rs = pstmt.executeQuery();
				colorlist  = new ArrayList<String>();
				while(rs.next()) {
					product_name = rs.getString(1);
					colorlist.add(product_name);
				}
				System.out.println("colorlist:"+colorlist );
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return colorlist;
		}

	// 상품 상세 정보 조회
	public ProductBean selectProduct(int product_idx) {
		ProductBean product = null;
		System.out.println(product_idx);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * FROM product WHERE product_idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, product_idx);
			rs = pstmt.executeQuery();
			
			// 조회 결과가 있을 경우
			if(rs.next()) {
				// ProductBean 객체 생성 후 조회 데이터 저장
				product = new ProductBean();
				product.setProduct_idx(rs.getInt("product_idx"));
				product.setProduct_name(rs.getString("product_name"));
				product.setProduct_brand(rs.getString("product_brand"));
				product.setProduct_price(rs.getInt("product_price"));
				product.setProduct_size(rs.getString("product_size"));
				product.setProduct_release_price(rs.getInt("product_release_price"));
				product.setProduct_buy_price(rs.getInt("product_buy_price"));
				product.setProduct_amount(rs.getInt("product_amount"));
				product.setProduct_sell_count(rs.getInt("product_sell_count"));
				product.setProduct_exp(rs.getString("product_exp"));
				product.setProduct_detail_exp(rs.getString("product_detail_exp"));
				product.setProduct_color(rs.getString("product_color"));
				product.setProduct_discount_price(rs.getInt("product_discount_price"));
//				product.setProduct_img(rs.getString("product_img"));
				product.setProduct_date(rs.getTimestamp("product_date"));
//				System.out.println(product);
			}
		} catch (SQLException e) {
			System.out.println("SQL구문 오류 - selectProduct()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return product;
	}
	// 멤버 쿠폰 조회
    public MemberCouponBean selectMemberCoupon(String coupon_content, int member_idx) {
       MemberCouponBean member_coupon = null;
       
       ResultSet rs  = null;
       PreparedStatement pstmt = null;
       
       try {
          String sql = "SELECT c.coupon_content"
                + " FROM member_coupon m join coupon c"
                + " on m.coupon_idx = c.coupon_idx"
                + " WHERE coupon_content LIKE ? AND member_idx=?";
          
          pstmt = con.prepareStatement(sql);
          pstmt.setString(1, "%"+coupon_content+"%");
          pstmt.setInt(2, member_idx);
          System.out.println(pstmt);
          rs = pstmt.executeQuery();
          
          if(rs.next()) {
             member_coupon = new MemberCouponBean();
//             member_coupon.setMember_idx(rs.getInt("member_idx"));
//             member_coupon.setCoupon_idx(rs.getInt("coupon_idx"));
//             member_coupon.setCoupon_price(rs.getInt("coupon_price"));
//             member_coupon.setCoupon_name(rs.getString("coupon_name"));
             member_coupon.setCoupon_content(rs.getString("coupon_content"));
//             member_coupon.setCoupon_start(rs.getString("coupon_start"));
//             member_coupon.setCoupon_end(rs.getString("coupon_end"));
//             member_coupon.setCoupon_isUse(rs.getInt("isUse"));
             
             System.out.println("member_coupon : " + member_coupon);
          }
       } catch (SQLException e) {
          System.out.println("SQL 구문 오류 - selectMemberCoupon()");
          e.printStackTrace();
       } finally {
          JdbcUtil.close(rs);
          JdbcUtil.close(pstmt);
       }
       
       return member_coupon;
    }

	// 관리자 - 상품 목록 조회
	public List<ProductBean> selectProductList() {
		ArrayList<ProductBean> productList = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT p.product_idx, p.product_brand, p.product_name, p.product_price, p.product_date, p.product_amount, p.product_color, i.image_main_file "
					+ "FROM product p join image i "
					+ "on p.product_idx = i.product_idx ORDER BY product_date desc";
			
//			String sql = "SELECT p.product_idx, p.product_brand, p.product_name, p.product_price, p.product_date, p.product_amount, p.product_color, i.image_main_file "
//					+ "FROM product p join image i "
//					+ "on p.product_idx = i.product_idx GROUP BY product_name ORDER BY product_date desc";
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			productList = new ArrayList<ProductBean>();
			
			while(rs.next()) {
				ProductBean product = new ProductBean();
				product.setProduct_idx(rs.getInt("product_idx"));
				product.setProduct_name(rs.getString("product_name"));
				product.setProduct_brand(rs.getString("product_brand"));
//				product.setProduct_size(rs.getString("product_size"));
				product.setProduct_price(rs.getInt("product_price"));
//				product.setProduct_release_price(rs.getInt("product_release_price"));
//				product.setProduct_buy_price(rs.getInt("product_buy_price"));
				product.setProduct_amount(rs.getInt("product_amount"));
//				product.setProduct_sell_count(rs.getInt("product_sell_count"));
//				product.setProduct_exp(rs.getString("product_exp"));
//				product.setProduct_detail_exp(rs.getString("product_detail_exp"));
				product.setProduct_color(rs.getString("product_color"));
//				product.setProduct_discount_price(rs.getInt("product_discount_price"));
				product.setProduct_img(rs.getString("image_main_file"));
				product.setProduct_date(rs.getTimestamp("product_date"));
				
				productList.add(product);
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 - selectProductList()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return productList;
	}
	
	//----------------장바 구니----------------------
		public int CartInsert(int product_idx, int member_idx, cartBean cart) {
			int CartInsert = 0;
			PreparedStatement pstmt1 = null,pstmt2 = null;
			ResultSet rs = null;
			
			try {
				String sql = "SELECT MAX(cart_idx) FROM cart";
				int cart_idx = 1;
				pstmt1 = con.prepareStatement(sql);
				rs = pstmt1.executeQuery();
				
				if(rs.next()) { 
					cart_idx = rs.getInt(1) + 1;
				}
				System.out.println(cart_idx);
		 //--------------------장바구니 등록--------------------------------		
			sql = "INSERT INTO cart VALUES(?,?,?,now(),?,?,?,?,?,?,?,?)";	
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setInt(1, cart_idx);
			pstmt2.setInt(2, member_idx);	
			pstmt2.setInt(3, product_idx);
			pstmt2.setInt(4, cart.getCart_price()); 
			pstmt2.setInt(5, cart.getCart_discount()); 
			pstmt2.setInt(6, cart.getCart_order_price()); 
			pstmt2.setInt(7, cart.getCart_count()); 
			pstmt2.setString(8, cart.getCart_size()); 
			pstmt2.setString(9, cart.getCart_color()); 
			pstmt2.setString(10, cart.getCart_product_name()); 
			pstmt2.setString(11, cart.getCart_product_image()); 
			
			CartInsert = pstmt2.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt2);
				JdbcUtil.close(pstmt1);
			}
			
			return CartInsert;
		}
	
	
		//장바구니 리스트 출력
		public List<cartBean> getCartList(int member_idx, int startRow, int listLimit) {
			 List<cartBean> cartlist = null;
			 PreparedStatement pstmt =  null;
			 ResultSet rs = null;
				
			 String sql ="SELECT c.cart_idx, c.cart_product_name, c.cart_product_image, c.cart_price, c.cart_discount, c.cart_order_price, c.cart_color,c.cart_size, c.cart_count, c.member_idx, c.product_idx "
			 		+ "FROM cart c join product p join image i join member m "
			 		+ "on c.product_idx = p.product_idx and c.product_idx = i.product_idx and c.member_idx = m.member_idx "
			 		+ "where m.member_idx=? "
			 		+ "LIMIT ?,?";
			 
			 try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, member_idx);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, listLimit);
				rs = pstmt.executeQuery();
				cartlist = new ArrayList<cartBean>();
				
				while(rs.next()) {
					cartBean vo = new cartBean();
					vo.setMember_idx(rs.getInt("member_idx"));
					vo.setProduct_idx(rs.getInt("product_idx"));
					vo.setCart_idx(rs.getInt("cart_idx"));
					vo.setCart_price(rs.getInt("cart_price"));
					vo.setCart_discount(rs.getInt("cart_discount"));
					vo.setCart_order_price(rs.getInt("cart_order_price"));
					vo.setCart_count(rs.getInt("cart_count"));
					vo.setCart_size(rs.getString("cart_size"));
					vo.setCart_color(rs.getString("cart_color"));
					vo.setCart_product_name(rs.getString("cart_product_name"));
					vo.setCart_product_image(rs.getString("cart_product_image"));
					cartlist.add(vo);
				}
			 } catch (SQLException e) {
				e.printStackTrace();
			}finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
			return cartlist;
		}//
		
		// 장바구니 총 수량 새기
		public int selectCartListCount(int member_idx) {
			int listCount = 0;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				// board 테이블의 모든 레코드 갯수 조회
				// => 제목에 검색어를 포함하는 레코드 조회(WHERE subject LIKE '%검색어%')
				//    (단, 쿼리에 직접 '%?%' 형태로 작성 시 ? 문자를 파라미터로 인식하지 못함
				//    (따라서, setXXX() 메서드에서 문자열 결합으로 "%" + "검색어" + "%" 로 처리)
				String sql = "SELECT COUNT(cart_idx) "
									+ "FROM cart "
									+ "where member_idx = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, member_idx);
				rs = pstmt.executeQuery();
				// 조회 결과가 있을 경우 listCount 변수에 저장
				if(rs.next()) {
					listCount = rs.getInt(1);
				}
				
			} catch (SQLException e) {
				System.out.println("BoardDAO - selectBoardListCount()");
				e.printStackTrace();
			} finally {
				// DB 자원 반환
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
			
			return listCount;
		}
		
		//장바구니 총 금액
		public int totalPrice(int member_idx) {
			int total = 0;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String sql = "SELECT sum(c.cart_order_price) "
					+ "FROM cart c join product p join member m "
					+ "on c.product_idx = p.product_idx and c.member_idx = m.member_idx "
					+ "where p.product_idx is not null and m.member_idx = ?";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, member_idx);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					total = rs.getInt(1);
				}
				System.out.println(total);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
			
			
			return total;
		}
		
		
		// ----------- 장바구니 금액 증가 작업 -----------------
		public int plusTotal(int cart_idx) {
			int plusTotal = 0;
			int member_idx = 0;
			int product_idx = 0;
			int product_release_price = 0;
			
			PreparedStatement pstmt = null;
			PreparedStatement pstmt2 = null;
			ResultSet rs = null;
			//체크박스 value값인 cart_idx 를 통해 member_idx, product_idx를 검색(기존 값들 가져오기)
			
	
			try {
				String sql = "SELECT c.member_idx, p.product_release_price, p.product_idx "
						+ "FROM cart c join product p join member m "
						+ "on c.product_idx = p.product_idx and c.member_idx = m.member_idx "
						+ "where m.member_idx is not null and c.cart_idx = ?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, cart_idx);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					member_idx = rs.getInt(1);
					product_release_price = rs.getInt(2);
					product_idx = rs.getInt(3);
				}
				System.out.println(member_idx + product_idx + cart_idx);
				sql = "UPDATE cart c join product p join member m "
						+ "ON c.product_idx = p.product_idx and c.member_idx = m.member_idx SET c.cart_order_price = c.cart_order_price + ? WHERE p.product_idx =? and m.member_idx = ? and c.cart_idx = ?";
				pstmt2 = con.prepareStatement(sql);
				pstmt2.setInt(1, product_release_price);
				pstmt2.setInt(2, product_idx);
				pstmt2.setInt(3, member_idx);
				pstmt2.setInt(4, cart_idx);
				plusTotal = pstmt2.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt2);
				JdbcUtil.close(pstmt);
			}
			return plusTotal;
		}

		
		
		// ----------- 장바구니 금액 감소 작업 -----------------
		public int minusTotal(int cart_idx) {
			int minusTotal = 0;
			int member_idx = 0;
			int product_idx = 0;
			int product_release_price = 0;
			
			PreparedStatement pstmt = null;
			PreparedStatement pstmt2 = null;
			ResultSet rs = null;
			//체크박스 value값인 cart_idx 를 통해 member_idx, product_idx를 검색(기존 값들 가져오기)
			
	
			try {
				String sql = "SELECT c.member_idx, p.product_release_price, p.product_idx "
						+ "FROM cart c join product p join member m "
						+ "on c.product_idx = p.product_idx and c.member_idx = m.member_idx "
						+ "where m.member_idx is not null and c.cart_idx = ?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, cart_idx);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					member_idx = rs.getInt(1);
					product_release_price = rs.getInt(2);
					product_idx = rs.getInt(3);
				}
				System.out.println(member_idx + product_idx + cart_idx);
				sql = "UPDATE cart c join product p join member m "
						+ "ON c.product_idx = p.product_idx and c.member_idx = m.member_idx SET c.cart_order_price = c.cart_order_price - ? WHERE p.product_idx =? and m.member_idx = ? and c.cart_idx = ?";
				pstmt2 = con.prepareStatement(sql);
				pstmt2.setInt(1, product_release_price);
				pstmt2.setInt(2, product_idx);
				pstmt2.setInt(3, member_idx);
				pstmt2.setInt(4, cart_idx);
				minusTotal = pstmt2.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt2);
				JdbcUtil.close(pstmt);
			}
			return minusTotal;
		}

		//-----------------장바구니 금액 +, - 후 포워딩을 위해 member_idx를 찾는 메서드-------------

		public int selectMember(int cart_idx) {
			int member_idx = 0;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql ="SELECT member_idx FROM cart WHERE cart_idx = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, cart_idx);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					member_idx = rs.getInt(1);
				}
      } catch (SQLException e) {
				System.out.println("SQL 구문 오류 - selectMember");
      } finally{
        JdbcUtil.close(rs);
        JdbcUtil.close(pstmt);
			}
			
			return member_idx;
		}
		

		// 메인 - 메인화면 베스트 상품 목록 조회
		public List<ProductBean> selectBestProductList(int startRow, int listLimit) {
			ArrayList<ProductBean> productBestList = null;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "SELECT p.product_idx, p.product_brand, p.product_name, p.product_price, p.product_discount_price, i.image_main_file "
						+ "FROM product p join image i "
						+ "on p.product_idx = i.product_idx GROUP BY product_name ORDER BY p.product_idx ASC LIMIT ?,?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, listLimit);
				rs = pstmt.executeQuery();
				
				productBestList = new ArrayList<ProductBean>();
				
				while(rs.next()) {
					ProductBean product = new ProductBean();
					product.setProduct_idx(rs.getInt("product_idx"));
					product.setProduct_name(rs.getString("product_name"));
					product.setProduct_brand(rs.getString("product_brand"));
//					product.setProduct_size(rs.getString("product_size"));
					product.setProduct_price(rs.getInt("product_price"));
//					product.setProduct_release_price(rs.getInt("product_release_price"));
//					product.setProduct_buy_price(rs.getInt("product_buy_price"));
////					product.setProduct_amount(rs.getInt("product_amount"));
//					product.setProduct_sell_count(rs.getInt("product_sell_count"));
//					product.setProduct_exp(rs.getString("product_exp"));
//					product.setProduct_detail_exp(rs.getString("product_detail_exp"));
//					product.setProduct_color(rs.getString("product_color"));
					product.setProduct_discount_price(rs.getInt("product_discount_price"));
					product.setProduct_img(rs.getString("image_main_file"));
//					product.setProduct_date(rs.getTimestamp("product_date"));
					
					productBestList.add(product);
				}
			} catch (SQLException e) {
				System.out.println("SQL 구문 오류 - selectBestProductList()");
				e.printStackTrace();
			} finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
			return productBestList;
		}

		
		// 메인 - 메인화면 최근 등록 상품 목록 조회
		public List<ProductBean> selectNewProductList(int startRow, int listLimit) {
			ArrayList<ProductBean> productNewList = null;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "SELECT p.product_idx, p.product_brand, p.product_name, p.product_price, p.product_date, p.product_discount_price, i.image_main_file "
				+ "FROM product p join image i "
				+ "on p.product_idx = i.product_idx GROUP BY product_name ORDER BY product_date desc LIMIT ?,?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, listLimit);
				rs = pstmt.executeQuery();
				
				productNewList = new ArrayList<ProductBean>();
				
				while(rs.next()) {
					ProductBean product = new ProductBean();
					product.setProduct_idx(rs.getInt("product_idx"));
					product.setProduct_name(rs.getString("product_name"));
					product.setProduct_brand(rs.getString("product_brand"));
//					product.setProduct_size(rs.getString("product_size"));
					product.setProduct_price(rs.getInt("product_price"));
//					product.setProduct_release_price(rs.getInt("product_release_price"));
//					product.setProduct_buy_price(rs.getInt("product_buy_price"));
//					product.setProduct_amount(rs.getInt("product_amount"));
//					product.setProduct_sell_count(rs.getInt("product_sell_count"));
//					product.setProduct_exp(rs.getString("product_exp"));
//					product.setProduct_detail_exp(rs.getString("product_detail_exp"));
//					product.setProduct_color(rs.getString("product_color"));
					product.setProduct_discount_price(rs.getInt("product_discount_price"));
					product.setProduct_img(rs.getString("image_main_file"));
					product.setProduct_date(rs.getTimestamp("product_date"));
					
					productNewList.add(product);
				}
			} catch (SQLException e) {
				System.out.println("SQL 구문 오류 - selectNewProductList()");
				e.printStackTrace();
			} finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
			return productNewList;
		}

		
		// 메인 - 메인화면 세일 상품 목록 조회
		public List<ProductBean> selectSaleProductList(int startRow, int listLimit) {
			ArrayList<ProductBean> productSaleList = null;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "SELECT p.product_idx, p.product_brand, p.product_name, p.product_price,p.product_discount_price, i.image_main_file "
						+ "FROM product p join image i "
						+ "on p.product_idx = i.product_idx "
						+ "WHERE product_discount_price > 0 "
						+ "GROUP BY product_name ORDER BY product_discount_price ASC LIMIT ?,?";
				
//				String sql = "SELECT * FROM product "
//						+ "WHERE product_discount_price IS NOT NULL "
//						+ "ORDER BY product_discount_price";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, listLimit);
				rs = pstmt.executeQuery();
				
				productSaleList = new ArrayList<ProductBean>();
				
				while(rs.next()) {
					ProductBean product = new ProductBean();
					product.setProduct_idx(rs.getInt("product_idx"));
					product.setProduct_name(rs.getString("product_name"));
					product.setProduct_brand(rs.getString("product_brand"));
//					product.setProduct_size(rs.getString("product_size"));
					product.setProduct_price(rs.getInt("product_price"));
//					product.setProduct_release_price(rs.getInt("product_release_price"));
//					product.setProduct_buy_price(rs.getInt("product_buy_price"));
//					product.setProduct_amount(rs.getInt("product_amount"));
//					product.setProduct_sell_count(rs.getInt("product_sell_count"));
//					product.setProduct_exp(rs.getString("product_exp"));
//					product.setProduct_detail_exp(rs.getString("product_detail_exp"));
//					product.setProduct_color(rs.getString("product_color"));
					product.setProduct_discount_price(rs.getInt("product_discount_price"));
					product.setProduct_img(rs.getString("image_main_file"));
//					product.setProduct_date(rs.getTimestamp("product_date"));
					
					productSaleList.add(product);
				}
			} catch (SQLException e) {
				System.out.println("SQL 구문 오류 - selectSaleProductList()");
				e.printStackTrace();
			} finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
			return productSaleList;
		}

		// 메인 - 카테고리별 상품 목록 조회
		public List<ProductBean> selectCGProductList(String cg,int startRow, int listLimit) {
			ArrayList<ProductBean> productCGList = null;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "SELECT p.product_idx, p.product_brand, p.product_name, p.product_price, p.product_discount_price, i.image_main_file "
						+ "FROM product p join image i "
						+ "on p.product_idx = i.product_idx "
						+ "WHERE product_brand LIKE ? "
						+ "GROUP BY product_name ORDER BY product_sell_count ASC LIMIT ?,?";
				
//				String sql = "SELECT * FROM product "
//						+ "WHERE product_brand LIKE ? "
//						+ "ORDER BY product_sell_count asc";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+cg+"%");
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, listLimit);
				
				rs = pstmt.executeQuery();
				
				productCGList = new ArrayList<ProductBean>();
				
				while(rs.next()) {
					ProductBean product = new ProductBean();
					product.setProduct_idx(rs.getInt("product_idx"));
					product.setProduct_name(rs.getString("product_name"));
					product.setProduct_brand(rs.getString("product_brand"));
//					product.setProduct_size(rs.getString("product_size"));
					product.setProduct_price(rs.getInt("product_price"));
//					product.setProduct_release_price(rs.getInt("product_release_price"));
//					product.setProduct_buy_price(rs.getInt("product_buy_price"));
//					product.setProduct_amount(rs.getInt("product_amount"));
//					product.setProduct_sell_count(rs.getInt("product_sell_count"));
//					product.setProduct_exp(rs.getString("product_exp"));
//					product.setProduct_detail_exp(rs.getString("product_detail_exp"));
//					product.setProduct_color(rs.getString("product_color"));
					product.setProduct_discount_price(rs.getInt("product_discount_price"));
					product.setProduct_img(rs.getString("image_main_file"));
//					product.setProduct_date(rs.getTimestamp("product_date"));
					
					productCGList.add(product);
//					System.out.println(productCGList);
				}
			} catch (SQLException e) {
				System.out.println("SQL 구문 오류 - selectCGProductList()");
				e.printStackTrace();
			} finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
			return productCGList;
		}

		
		// 메인 - 검색어 상품 목록 조회
		public List<ProductBean> selectKeywordProductList(String keyword,int startRow, int listLimit) {
			ArrayList<ProductBean> productSearchList = null;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "SELECT p.product_idx, p.product_brand, p.product_name, p.product_price, i.image_main_file "
						+ "FROM product p join image i "
						+ "on p.product_idx = i.product_idx "
						+ "WHERE product_brand LIKE ? OR product_name LIKE ?"
						+ "GROUP BY product_name ORDER BY product_sell_count ASC LIMIT ?,?";
				
//				String sql = "SELECT * FROM product "
//						+ "WHERE product_brand LIKE ? "
//						+ "ORDER BY product_sell_count asc";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setString(2, "%"+keyword+"%");
				pstmt.setInt(3, startRow);
				pstmt.setInt(4, listLimit);
				
				rs = pstmt.executeQuery();
				
				productSearchList = new ArrayList<ProductBean>();
				
				while(rs.next()) {
					ProductBean product = new ProductBean();
					product.setProduct_idx(rs.getInt("product_idx"));
					product.setProduct_name(rs.getString("product_name"));
					product.setProduct_brand(rs.getString("product_brand"));
//					product.setProduct_size(rs.getString("product_size"));
					product.setProduct_price(rs.getInt("product_price"));
//					product.setProduct_release_price(rs.getInt("product_release_price"));
//					product.setProduct_buy_price(rs.getInt("product_buy_price"));
//					product.setProduct_amount(rs.getInt("product_amount"));
//					product.setProduct_sell_count(rs.getInt("product_sell_count"));
//					product.setProduct_exp(rs.getString("product_exp"));
//					product.setProduct_detail_exp(rs.getString("product_detail_exp"));
//					product.setProduct_color(rs.getString("product_color"));
//					product.setProduct_discount_price(rs.getInt("product_discount_price"));
					product.setProduct_img(rs.getString("image_main_file"));
//					product.setProduct_date(rs.getTimestamp("product_date"));
					
					productSearchList.add(product);
					System.out.println(productSearchList);
				}
			} catch (SQLException e) {
				System.out.println("SQL 구문 오류 - selectKeywordProductList()");
				e.printStackTrace();
			} finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
			return productSearchList;
		}

        //주문 하기
		public int insertOrder(OrderBean vo) {
			int insertOrder = 0;
			PreparedStatement pstmt = null,pstmt2 = null,pstmt3 = null,pstmt4=null;
			ResultSet rs = null,rs2=null;
			
			try {
				String sql = "SELECT MAX(order_idx) FROM orderlist";
				int idx = 1; // 새 글 번호
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				if(rs.next()) { 
					 //true -> 조회결과가 있을 경우 (= 게시물이 하나라도 존재할 경우)
					 //존재하지 않을 경우 rs.next는 false , DB에서는 NULL이 표기된다.
					idx = rs.getInt(1) + 1;
				}
				System.out.println(idx);
				sql = "INSERT INTO orderlist VALUES(?,now(),?,?,?,?,?)";
				pstmt2 = con.prepareStatement(sql);
				pstmt2.setInt(1,idx );
				pstmt2.setInt(2,vo.getOrder_product_price());
				pstmt2.setString(3,vo.getOrder_category());
				pstmt2.setString(4, vo.getOrder_progress());
				pstmt2.setInt(5, vo.getOrder_member_idx());
				pstmt2.setInt(6, vo.getOrder_product_idx());
				insertOrder=pstmt2.executeUpdate();
				
				if(insertOrder >0) {
					sql = "INSERT INTO order_detail VALUES(?,?,?)";
					pstmt3 = con.prepareStatement(sql);
					pstmt3.setInt(1, idx);
					pstmt3.setInt(2, vo.getOrder_member_idx());
					pstmt3.setInt(3, vo.getOrder_product_idx());
					insertOrder=pstmt3.executeUpdate();
					
					sql="UPDATE product SET product_amount = ?,product_sell_count=? WHERE product_idx=?";
					pstmt4 = con.prepareStatement(sql);
					pstmt4.setInt(1,  vo.getOrder_product_amount()-1);
					pstmt4.setInt(2,  vo.getOrder_product_sell_count()+1);
					pstmt4.setInt(3, vo.getOrder_product_idx());
					pstmt4.executeUpdate();
					
					sql="UPDATE member_coupon set isUse= ? WHERE coupon_idx =?";
					pstmt4 = con.prepareStatement(sql);
					pstmt4.setInt(1, vo.getOrder_isUse()+1);
					pstmt4.setInt(2, vo.getOrder_coupon_idx());
					pstmt4.executeUpdate();
					
					sql="DELETE FROM member_coupon WHERE isUse=1";
					pstmt4 = con.prepareStatement(sql);
					pstmt4.executeUpdate();
				}
				
			
			} catch (SQLException e) {
				e.printStackTrace();
			
			}finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt4);
				JdbcUtil.close(pstmt3);
				JdbcUtil.close(pstmt2);
				JdbcUtil.close(pstmt);
			}
			
			
			return insertOrder;
		}

		// 주문 리스트
		public List<OrderBean> getOrderList(int member_idx, int startRow, int listLimit) {
			List<OrderBean> orderlist = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String sql="SELECT i.image_main_file,m.member_id,o.order_price,o.order_category,o.order_progress,o.order_date,p.product_idx,p.product_size,p.product_color,o.order_idx "
					+ "from orderlist o join product p join member m join image i "
					+ "on o.product_idx = p.product_idx and o.member_idx = m.member_idx and o.product_idx = i.product_idx "
					+ "where m.member_idx=? "
					+ "LIMIT ?,?";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1,member_idx );
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, listLimit);
				rs = pstmt.executeQuery();
				orderlist = new ArrayList<OrderBean>();
				while(rs.next()) {
					OrderBean vo = new OrderBean();
					vo.setOrder_main_image(rs.getString("image_main_file"));
					vo.setOrder_member_id(rs.getString("member_id"));
					vo.setOrder_product_price(rs.getInt("order_price"));
					vo.setOrder_category(rs.getString("order_category"));
					vo.setOrder_progress(rs.getString("order_progress"));
					vo.setOrder_date(rs.getTimestamp("order_date"));
					vo.setOrder_product_idx(rs.getInt("product_idx"));
					vo.setOrder_product_size(rs.getString("product_size"));
					vo.setOrder_product_color(rs.getString("product_color"));
					vo.setOrder_idx(rs.getInt("order_idx"));
					orderlist.add(vo);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
			
			return orderlist;
		}

		//주문 총 수량
		public int selectOrderListCount(int member_idx) {
			int listCount = 0;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "SELECT COUNT(order_idx) "
									+ "FROM orderlist "
									+ "where member_idx = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, member_idx);
				rs = pstmt.executeQuery();
				// 조회 결과가 있을 경우 listCount 변수에 저장
				if(rs.next()) {
					listCount = rs.getInt(1);
				}
			} catch (SQLException e) {
				System.out.println("BoardDAO - selectBoardListCount()");
				e.printStackTrace();
			} finally {
				// DB 자원 반환
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
			
			return listCount;
		}

		// 관리자 페이지 - 주문 내역
		public List<OrderBean> getAdminOrderList() {
			List<OrderBean> orderlist = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String sql="SELECT i.image_main_file,m.member_id,p.product_price,o.order_category,o.order_progress,o.order_date,o.order_idx "
					+ "from orderlist o join product p join member m join image i "
					+ "on o.product_idx = p.product_idx and o.member_idx = m.member_idx and o.product_idx = i.product_idx";
					
			
			try {
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				orderlist = new ArrayList<OrderBean>();
				while(rs.next()) {
					OrderBean vo = new OrderBean();
					vo.setOrder_main_image(rs.getString("image_main_file"));
					vo.setOrder_member_id(rs.getString("member_id"));
					vo.setOrder_product_price(rs.getInt("product_price"));
					vo.setOrder_category(rs.getString("order_category"));
					vo.setOrder_progress(rs.getString("order_progress"));
					vo.setOrder_date(rs.getTimestamp("order_date"));
					vo.setOrder_idx(rs.getInt("order_idx"));
					orderlist.add(vo);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
			
			return orderlist;
		}
		//--------------장바구니 삭제----------------
		public boolean isDeleteCart(int cart_idx) {
			int deleteCount =0;
			boolean isDeleteSuccess = false;
			PreparedStatement pstmt = null;
			
			String sql = "DELETE FROM cart WHERE cart_idx=?";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, cart_idx);
				deleteCount = pstmt.executeUpdate();
				if(deleteCount > 0) {
					isDeleteSuccess=true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JdbcUtil.close(pstmt);
			}
		
			return isDeleteSuccess;
		}
		//---------------상품 삭제(관리자)---------------
		public int deleteProduct(int product_idx) {
			int deleteCount = 0;
			PreparedStatement pstmt = null;
			
			String sql = "DELETE FROM product WHERE product_idx=?";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, product_idx);
				deleteCount = pstmt.executeUpdate();
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				JdbcUtil.close(pstmt);
			}
			
			return deleteCount;
		}

		
		// 좋아요(찜하기) 버튼 클릭
		public int InsertLike(int member_idx, int product_idx) {
			int insertCount = 0;
			
			PreparedStatement pstmt1 = null, pstmt2 = null;
			ResultSet rs = null;
			
			try {
				// wish_idx 증가
				String sql = "SELECT MAX(wish_idx) FROM wish";
				int wish_idx = 1;
				pstmt1 = con.prepareStatement(sql);
				rs = pstmt1.executeQuery();
				
				if(rs.next()) { 
					wish_idx = rs.getInt(1) + 1;
				}
				System.out.println("wish_idx : " + wish_idx);
				
				// ----------------------------------------------
				
				// 찜하기 추가
				sql = "INSERT INTO wish VALUES(?,?,?)";	
				pstmt2 = con.prepareStatement(sql);
				pstmt2.setInt(1, wish_idx);
				pstmt2.setInt(2, member_idx);	
				pstmt2.setInt(3, product_idx);	
				insertCount = pstmt2.executeUpdate();
			} catch (SQLException e) {
				System.out.println("SQL 구문 오류 - InsertLike()");
				e.printStackTrace();
			} finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt2);
				JdbcUtil.close(pstmt1);
			}
			return insertCount;
		}

		
		// 상품별 좋아요 수 누적
		public int updateWishCount(int product_idx) {
			int wishCount = 0;
			
			PreparedStatement pstmt = null;
			
			try {
			String sql="UPDATE product p INNER JOIN wish w "
					+ "ON p.product_idx = w.product_idx "
					+ "SET p.product_wishcount = p.product_wishcount + 1 "
					+ "WHERE p.product_idx = ?";
			
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1,product_idx );
				
				wishCount = pstmt.executeUpdate();
				
//				System.out.println("wishCount : " + wishCount);
				
			} catch (SQLException e) {
				System.out.println("SQL 구문 오류 - updateWishCount()");
				e.printStackTrace();
			} finally {
				JdbcUtil.close(pstmt);
			}
			
			return wishCount;
		}
		
		
		
		public int isDeleteList(int product_idx) {
			int deleteCount = 0;
			boolean isDeleteProduct = false;
			PreparedStatement pstmt = null;
			
			String sql = "DELETE FROM product WHERE product_idx=?";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, product_idx);
				deleteCount = pstmt.executeUpdate();
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				JdbcUtil.close(pstmt);
			}
			
			return deleteCount;
		}
		//-------------------------상품수정 쿼리-------------------------------
		public int updateProduct(int idx, ProductBean product) {
			int updateProduct = 0;
//			int updateImage = 0;
			
			PreparedStatement pstmt =null;
			
			System.out.println(product);
			
			try {
				String sql ="UPDATE product "
						+ "SET product_name=?,  product_brand=?,  product_price=?, product_size=? , product_amount=?, product_color=?,  product_exp=?,  product_detail_exp=?,  product_discount_price=? "
						+ "WHERE product_idx =?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, product.getProduct_name());
				pstmt.setString(2, product.getProduct_brand());
				pstmt.setInt(3, product.getProduct_price());
				pstmt.setString(4, product.getProduct_size());
				pstmt.setInt(5, product.getProduct_amount());
				pstmt.setString(6, product.getProduct_color());
				pstmt.setString(7, product.getProduct_exp());
				pstmt.setString(8, product.getProduct_detail_exp());
				pstmt.setDouble(9, product.getProduct_discount_price());
				pstmt.setInt(10, idx);
				updateProduct = pstmt.executeUpdate();
				
//				if(updateProduct > 0) {
//					//--------------이미지 테이블 업데이트 작업--------------------
//					sql = "UPDATE image SET image_main_file =?, image_real_file1 =?, image_real_file2 =? WHERE product_idx = ?";
//					
//					pstmt2 = con.prepareStatement(sql);
//					pstmt2.setString(1, image.getImage_main_file());
//					pstmt2.setString(2, image.getImage_real_file1());
//					pstmt2.setString(3, image.getImage_real_file2());
//					pstmt2.setInt(4, idx);
//					updateImage = pstmt2.executeUpdate();
//					
//				}
				
			} catch (SQLException e) {
				System.out.println("sql 구문오류 - updateProduct");
				e.printStackTrace();
			}finally {
				JdbcUtil.close(pstmt);
			} 
		
		return updateProduct;
}
		public ProductBean getProduct(int idx) {
			ProductBean bean = null;
			PreparedStatement pstmt = null;
			ResultSet rs  = null;
			String sql = "SELECT * FROM product WHERE product_idx=?";
			try {
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, idx);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					bean = new ProductBean();
					bean.setProduct_name(rs.getString("product_name"));
					bean.setProduct_brand(rs.getString("product_brand"));
					bean.setProduct_price(rs.getInt("product_price"));
					bean.setProduct_size(rs.getString("product_size"));
					bean.setProduct_amount(rs.getInt("product_amount"));
					bean.setProduct_color(rs.getString("product_color"));
					bean.setProduct_exp(rs.getString("product_exp"));
					bean.setProduct_detail_exp(rs.getString("product_detail_exp"));
					bean.setProduct_discount_price(rs.getInt("product_discount_price"));
					bean.setProduct_img(rs.getString("product_img"));
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
			
			return bean;
		}


//--------- 이미지 정보 가져오는 메서드 -------------
		public imageBean selectImage(int product_idx) {
			imageBean image = null;
			PreparedStatement pstmt = null;
			ResultSet rs  = null;
			//--------------------이미지 이름 가져오기 작업--------------
			try {
				String sql = "SELECT image_main_file, image_real_file1, image_real_file2  FROM image WHERE product_idx = ?";

				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, product_idx);
				rs = pstmt.executeQuery();
				
				
				if(rs.next()) {
					image = new imageBean();
					image.setImage_main_file(rs.getString("image_main_file")); //메인 이미지 가져오기
					image.setImage_real_file1(rs.getString("image_real_file1")); //상세 이미지1 가져오기
					image.setImage_real_file2(rs.getString("image_real_file2")); //상세 이미지2 가져오기

				}
			} catch (SQLException e) {
				System.out.println("SQL 구문 오류 - selectImage");
				e.printStackTrace();
			}finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
				
			}
			return image;
		}
		
		//--------- 이미지 정보 가져오는 메서드 -------------
				public List<imageBean> selectImageList(String product_name ) {
					List<imageBean> imagelist = null;
					PreparedStatement pstmt = null;
					ResultSet rs  = null;
					//--------------------이미지 이름 가져오기 작업--------------
					try {
						String sql = "SELECT i.image_main_file,i.image_real_file1,i.image_real_file2 FROM image i join product p "
								+ "ON i.product_idx = p.product_idx "
								+ "WHERE p.product_name = ?";

						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, product_name);
						rs = pstmt.executeQuery();
						
						imagelist = new ArrayList<imageBean>();
						while(rs.next()) {
							imageBean image = new imageBean();
							image.setImage_main_file(rs.getString("image_main_file")); //메인 이미지 가져오기
							image.setImage_real_file1(rs.getString("image_real_file1")); //상세 이미지1 가져오기
							image.setImage_real_file2(rs.getString("image_real_file2")); //상세 이미지2 가져오기
							imagelist.add(image);
						}
						System.out.println(imagelist);
					} catch (SQLException e) {
						System.out.println("SQL 구문 오류 - selectImage");
						e.printStackTrace();
					}
					return imagelist;
				}

		public boolean isDeleteOrder(int order_idx) {
			int isDeleteOrderList = 0;
			boolean isDeleteSuccess = false;
			PreparedStatement pstmt =null;
			String sql ="DELETE FROM orderlist WHERE order_idx=?"; 
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, order_idx);
				isDeleteOrderList =pstmt.executeUpdate();
				
				if(isDeleteOrderList >0) {
					isDeleteSuccess = true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JdbcUtil.close(pstmt);
			}
			return isDeleteSuccess;
			
		}

		// 찜한 상품 조회
		public WishBean selectWish(int product_idx, int member_idx) {
				WishBean wish = null;
				
				ResultSet rs  = null;
				PreparedStatement pstmt = null;
				
				try {
					String sql = "SELECT * FROM wish WHERE product_idx=? AND member_idx=?";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, product_idx);
					pstmt.setInt(2, member_idx);
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


		// 찜하기 취소
		public int deleteWish(int member_idx, int product_idx) {
			int deleteCount = 0;
			
			PreparedStatement pstmt = null;
			
			try {
				String sql = "DELETE FROM wish WHERE member_idx=? AND product_idx=?";	
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, member_idx);	
				pstmt.setInt(2, product_idx);	
				deleteCount = pstmt.executeUpdate();
				
				System.out.println("deleteCount : " + deleteCount);
				
			} catch (SQLException e) {
				System.out.println("SQL 구문 오류 - deleteWish()");
				e.printStackTrace();
			} finally {
				JdbcUtil.close(pstmt);
			}
			return deleteCount;
		}

		
		// 찜하기 취소 시 누적 수 감소
		public int DecWishCount(int product_idx) {
			int updateCount = 0;
			
			PreparedStatement pstmt = null;
			
			try {
//			String sql="UPDATE product p INNER JOIN wish w "
//					+ "ON p.product_idx = w.product_idx "
//					+ "SET p.product_wishcount = p.product_wishcount - 1 "
//					+ "WHERE p.product_idx = ?";
				String sql="UPDATE product "
						+ "SET product_wishcount = product_wishcount - 1 "
						+ "WHERE product_idx = ?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1,product_idx );
				System.out.println(pstmt);
				updateCount = pstmt.executeUpdate();
				
				System.out.println("updateCount111 : " + updateCount);
				
			} catch (SQLException e) {
				System.out.println("SQL 구문 오류 - DecWishCount()");
				e.printStackTrace();
			} finally {
				JdbcUtil.close(pstmt);
			}
			
			return updateCount;
		}


		// 찜 목록 조회
		public List<ProductBean> getWishList(int member_idx, int startRow, int listLimit) {
			List<ProductBean> wishlist = null;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String sql="SELECT w.wish_idx, i.image_main_file,m.member_id,p.product_price,p.product_name,"
					+ "p.product_brand,p.product_size,p.product_color,p.product_idx "
					+ "FROM wish w JOIN product p JOIN member m JOIN image i "
					+ "ON w.product_idx = p.product_idx AND w.member_idx = m.member_idx AND w.product_idx = i.product_idx "
					+ "WHERE m.member_idx=? "
					+ "LIMIT ?,?";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1,member_idx );
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, listLimit);
				rs = pstmt.executeQuery();
				
				wishlist = new ArrayList<ProductBean>();
				while(rs.next()) {
					ProductBean vo = new ProductBean();
					vo.setWish_idx(rs.getInt("wish_idx"));
					vo.setProduct_name(rs.getString("product_name"));
					vo.setProduct_idx(rs.getInt("product_idx")); // 찜 삭제 위해 필요함
					vo.setProduct_size(rs.getNString("product_size"));
					vo.setProduct_price(rs.getInt("product_price"));
					vo.setProduct_brand(rs.getNString("product_brand"));
					vo.setProduct_color(rs.getNString("product_color"));
					vo.setProduct_img(rs.getString("image_main_file"));
					wishlist.add(vo);
				}
			} catch (SQLException e) {
				System.out.println("SQL 구문 오류 - getWishList()");
				e.printStackTrace();
			}finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
			
			return wishlist;
		}


		public int selectWishListCount(int member_idx) {
			int listCount = 0;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "SELECT COUNT(wish_idx) "
									+ "FROM wish "
									+ "WHERE member_idx = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, member_idx);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					listCount = rs.getInt(1);
				}
				
			} catch (SQLException e) {
				System.out.println("SQL 구문 오류! - selectWishListCount()");
				e.printStackTrace();
			} finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
			
			return listCount;
		}


		public List<ReviewBean> selectReviewList(int startRow, int listLimit,int product_idx) { // 리뷰 리스트 출
			List<ReviewBean> reviewList = null;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ReviewBean review = new ReviewBean();
			try {
				String sql = "SELECT * FROM review WHERE product_idx=? ORDER BY review_idx DESC LIMIT ?,? ";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, product_idx);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, listLimit);
				rs = pstmt.executeQuery();
				System.out.println("productDao-review: "+product_idx);
				reviewList = new ArrayList<ReviewBean>();
				while(rs.next()) {
					review = new ReviewBean();
					review.setReview_idx(rs.getInt("review_idx"));
					review.setProduct_idx(rs.getInt("product_idx"));
					review.setMember_idx(rs.getInt("member_idx"));
					review.setReview_content(rs.getString("review_content"));
					review.setReview_img(rs.getString("review_img"));
					review.setReview_real_img(rs.getString("review_real_img"));
					review.setReview_date(rs.getDate("review_date"));
					review.setRe_order_detail(rs.getString("re_order_detail"));
					
					reviewList.add(review);
					System.out.println("review 확인" + review);
					System.out.println("reviewList확인"+reviewList);
				}
			} catch (SQLException e) {
				System.out.println("sql구문 - reviewList 오류");
				e.printStackTrace();
			} finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
			
			return reviewList;
		}


		public int selectReviewListCount() { // 리뷰의 페이징 처리 작업
			int listCount = 0;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				
				String sql = "SELECT COUNT(*) FROM review";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					listCount = rs.getInt(1);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}  finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
			
			return listCount;
		}

		
		public int deleteBoard(int review_idx) {
			int deleteCount = 0;
			PreparedStatement pstmt = null;
		
			try {
				String sql = "DELETE FROM review WHERE review_idx=?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, review_idx);
				deleteCount = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JdbcUtil.close(pstmt);
			}
			
			return deleteCount;
		}


		// 관리자 쿠폰 등록
		public int insertCoupon(CouponBean coupon) {
			int insertCount = 0;
			
			PreparedStatement pstmt = null, pstmt2 = null;
			ResultSet rs = null;
			
			try {
				int coupon_idx = 1; // 쿠폰 idx 처리
				String sql = "SELECT MAX(coupon_idx) FROM coupon";
				pstmt= con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					coupon_idx = rs.getInt(1) + 1;
				} 
				
				sql = "INSERT INTO coupon VALUES(?,?,?,?,?,?,now())";
				pstmt2= con.prepareStatement(sql);
				
				pstmt2.setInt(1, coupon_idx);
				pstmt2.setString(2, coupon.getCoupon_name());
				pstmt2.setInt(3, coupon.getCoupon_price());
				pstmt2.setString(4, coupon.getCoupon_content());
				pstmt2.setString(5, coupon.getCoupon_start());
				pstmt2.setString(6, coupon.getCoupon_end());
				System.out.println(pstmt2);
				insertCount = pstmt2.executeUpdate();
				
			} catch (SQLException e) {
				System.out.println("SQL 구문 오류! - insertCoupon()");
				e.printStackTrace();
			} finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
        JdbcUtil.close(pstmt2);
			}
			return insertCount;
		}

		
		// 관리자 쿠폰 조회
		public List<CouponBean> selectCouponList() {
			List<CouponBean> couponList = null;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "SELECT * FROM coupon";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				couponList = new ArrayList<CouponBean>();
				
				while(rs.next()) {
					CouponBean coupon = new CouponBean();
					coupon.setCoupon_idx(rs.getInt("coupon_idx"));
					coupon.setCoupon_name(rs.getString("coupon_name"));
					coupon.setCoupon_content(rs.getString("coupon_content"));
					coupon.setCoupon_price(rs.getInt("coupon_price"));
					coupon.setCoupon_start(rs.getString("coupon_start"));
					coupon.setCoupon_end(rs.getString("coupon_end"));
					coupon.setCoupon_date(rs.getDate("coupon_date"));
					
					couponList.add(coupon);
				}
			} catch (SQLException e) {
				System.out.println("sql구문 - selectCouponList 오류");
				e.printStackTrace();
			} finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
			
			return couponList;
		}

		
		// 관리자 쿠폰 수정 폼에 필요한 상세정보
		public CouponBean selectCoupon(int coupon_idx) {
			CouponBean coupon = null;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "SELECT * FROM coupon WHERE coupon_idx=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, coupon_idx);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					coupon = new CouponBean();
					coupon.setCoupon_idx(rs.getInt("coupon_idx"));
					coupon.setCoupon_name(rs.getString("coupon_name"));
					coupon.setCoupon_content(rs.getString("coupon_content"));
					coupon.setCoupon_price(rs.getInt("coupon_price"));
					coupon.setCoupon_start(rs.getString("coupon_start"));
					coupon.setCoupon_end(rs.getString("coupon_end"));
				}
			} catch (SQLException e) {
				System.out.println("SQL구문 오류 - selectCoupon()");
				e.printStackTrace();
			} finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
			return coupon;
		}
		
		// 사용자 쿠폰 조회
		public List<CouponBean> selectUserCouponList(int member_idx) {
			List<CouponBean> couponList = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "SELECT * FROM member_coupon where member_idx = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, member_idx);
				rs = pstmt.executeQuery();
				
				couponList = new ArrayList<CouponBean>();
				
				while(rs.next()) {
					CouponBean coupon = new CouponBean();
					coupon.setCoupon_idx(rs.getInt("coupon_idx"));
					coupon.setCoupon_name(rs.getString("coupon_name"));
					coupon.setCoupon_price(rs.getInt("coupon_price"));
					coupon.setCoupon_isUse(rs.getInt("isUse"));
					coupon.setCoupon_start(rs.getString("coupon_start"));
					coupon.setCoupon_end(rs.getString("coupon_end"));
					couponList.add(coupon);
				}
			} catch (SQLException e) {
				System.out.println("sql구문 - selectCouponList 오류");
				e.printStackTrace();
			} finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
			
			return couponList;
		}


		// 쿠폰 수정 작업
		public int updateCoupon(int coupon_idx, CouponBean coupon) {
			int updatecount = 0;
			
			PreparedStatement pstmt =null;
			
			try {
				String sql ="UPDATE coupon "
						+ "SET coupon_name=?, coupon_price=?, coupon_content=?, coupon_start=? , coupon_end=? "
						+ "WHERE coupon_idx =?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, coupon.getCoupon_name());
				pstmt.setInt(2, coupon.getCoupon_price());
				pstmt.setString(3, coupon.getCoupon_content());
				pstmt.setString(4, coupon.getCoupon_start());
				pstmt.setString(5, coupon.getCoupon_end());
				pstmt.setInt(6, coupon_idx);
				
				updatecount = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				System.out.println("sql 구문오류 - updateCoupon");
				e.printStackTrace();
			}finally {
				JdbcUtil.close(pstmt);
			} 
			
			return updatecount;
		}

		
		// 관리자 쿠폰 삭제
		public int deleteCoupon(int coupon_idx) {
			int deleteCount = 0;
			PreparedStatement pstmt = null;
			
			String sql = "DELETE FROM coupon WHERE coupon_idx=?";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, coupon_idx);
				deleteCount = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				System.out.println("sql 구문오류 - deleteCoupon");
				e.printStackTrace();
			}finally {
				JdbcUtil.close(pstmt);
			}
			
			return deleteCount;
		}
		
		// 쿠폰 사용하기
		public int CouponUsePrice(int idx) {
			int Coupon_price = 0;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				String sql = "SELECT coupon_price FROM member_coupon where coupon_idx=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, idx);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					Coupon_price = rs.getInt(1);
				}
				System.out.println(Coupon_price);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return Coupon_price;
		}
		
		// 메인 쿠폰 목록 조회
		public List<CouponBean> selectCouponMainList(String coupon_content) {
			ArrayList<CouponBean> couponList = null;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "SELECT coupon_name, coupon_price, coupon_start, coupon_end, coupon_content "
						+ "FROM coupon WHERE coupon_content LIKE ? ORDER BY coupon_price ASC";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+coupon_content+"%");
				
				System.out.println(pstmt);
				
				rs = pstmt.executeQuery();
				
				couponList = new ArrayList<CouponBean>();
				
				while(rs.next()) {
					CouponBean coupon = new CouponBean();
					coupon.setCoupon_name(rs.getString("coupon_name"));
					coupon.setCoupon_price(rs.getInt("coupon_price"));
					coupon.setCoupon_start(rs.getString("coupon_start"));
					coupon.setCoupon_end(rs.getString("coupon_end"));
					coupon.setCoupon_content(rs.getString("coupon_content"));
					
					couponList.add(coupon);
//					System.out.println(couponList);
				}
			} catch (SQLException e) {
				System.out.println("SQL 구문 오류 - selectCouponMainList()");
				e.printStackTrace();
			} finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
			return couponList;

		}

		// 쿠폰 배너 다운
		public int memberDownCoupon(int member_idx, String coupon_content) {
			int insertCount = 0;
			
			PreparedStatement pstmt = null, pstmt2 = null;
			ResultSet rs = null;
			
			try {
				 int coupon_idx = 0;
	              String coupon_name = "";
	              int coupon_price = 0;
	              String coupon_start = "";
	              String coupon_end = "";
				
				String sql = "SELECT coupon_idx,coupon_name,coupon_price, coupon_start, coupon_end "
						+ "FROM coupon WHERE coupon_content LIKE ?";
				
				pstmt= con.prepareStatement(sql);
				pstmt.setString(1, "%"+coupon_content+"%");
				rs = pstmt.executeQuery();
//				System.out.println("쿠폰 다운 검색: " + pstmt);
				
				if(rs.next()) {
					coupon_idx = rs.getInt(1);
					coupon_name = rs.getString(2);
					coupon_price = rs.getInt(3);
					coupon_start = rs.getString(4);
					coupon_end = rs.getString(5);
				} 
				
				sql = "INSERT INTO member_coupon VALUES(?,?,?,?,0,?,?)";
				pstmt2= con.prepareStatement(sql);
				
				pstmt2.setInt(1, member_idx);
				pstmt2.setInt(2, coupon_idx);
				pstmt2.setString(3, coupon_name);
				pstmt2.setInt(4, coupon_price);
				pstmt2.setString(5, coupon_start);
				pstmt2.setString(6, coupon_end);
//				System.out.println(pstmt2);
				insertCount = pstmt2.executeUpdate();
				
			} catch (SQLException e) {
				System.out.println("SQL 구문 오류! - memberDownCoupon()");
				e.printStackTrace();
			} finally {
				JdbcUtil.close(rs); 
				JdbcUtil.close(pstmt);
				JdbcUtil.close(pstmt2);
			}
			return insertCount;
		}
		
		
		public boolean reportcount(int idx) {
			int declareplus = 0;
			boolean reportplus = false;
			
			PreparedStatement pstmt = null;
			
			String sql = "UPDATE member SET member_dec=member_dec+1 WHERE member_idx=?";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, idx);
				declareplus=pstmt.executeUpdate();
				if(declareplus>0) {
					reportplus = true;
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				JdbcUtil.close(pstmt);
			}
			return reportplus;
		}

		
		public int OrderDeleteList(int order_idx) {
			int deleteCount = 0;
			boolean isDeleteProduct = false;
			PreparedStatement pstmt = null;
			
			String sql = "DELETE FROM orderlist WHERE order_idx=?";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, order_idx);
				deleteCount = pstmt.executeUpdate();
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				JdbcUtil.close(pstmt);
			}
			
			return deleteCount;
		}
	
  public OrderBean selectOrderProgress() { // 배송상태를 전달받기
			OrderBean order = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "SELECT * FROM orderlist WHERE order_progress=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "order_progress");
				
				rs = pstmt.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
			return order;
		}


  public int selectProductListCount() {
	int listCount = 0;
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	try {
		String sql = "SELECT COUNT(product_idx) "
							+ "FROM product ";
//							+ "where member_idx = ?";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			listCount = rs.getInt(1);
		}
		
	} catch (SQLException e) {
		System.out.println("BoardDAO - selectProductListCount()");
		e.printStackTrace();
	} finally {
		// DB 자원 반환
		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);
	}
	
	return listCount;
}


	public int selectProductCgListCount(String cg) {
		int listCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT COUNT(product_idx) "
								+ "FROM product "
								+ "WHERE product_brand LIKE ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+cg+"%");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			System.out.println("BoardDAO - selectProductListCount()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return listCount;
	}


	public int selectProductKeywordListCount(String keyword) {
		int listCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT COUNT(product_idx) "
								+ "FROM product "
								+ "WHERE product_brand LIKE ? OR product_name LIKE ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setString(2, "%"+keyword+"%");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			System.out.println("BoardDAO - selectProductListCount()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return listCount;
	}
	
}//DAO 끝
