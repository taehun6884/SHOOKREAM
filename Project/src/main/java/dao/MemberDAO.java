package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.JdbcUtil;
import vo.MemberBean;

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isLogintUser;
	}
	// 로그인
	
	// 회원 정보 수정 updateMember()
	public int updateMember(MemberBean member) {
		int updateMember = 0;
		
		PreparedStatement pstmt = null;
		
		try {
			String sql = "UPDATE member "
									+ "SET "
									+ "member_name=?,"
									+ "member_id=?,"
									+ "member_pass=?,"
									+ "member_address=?,"
									+ "member_email=?,"
									+ "member_phone=?"
									+ "WHERE member_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getMember_name());
			pstmt.setString(2, member.getMember_id());
			pstmt.setString(3, member.getMember_pass());
			pstmt.setString(4, member.getMember_address());
			pstmt.setString(5, member.getMember_email());
			pstmt.setString(6, member.getMember_phone());
			pstmt.setString(7, member.getMember_id());
			
			updateMember = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("sql 구문 오류 - updateMember()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return updateMember;
		
	} // 회원 정보 수정 끝


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
}
