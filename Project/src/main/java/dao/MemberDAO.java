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
			
			sql = "INSERT INTO member VALUES(?,?,?,?,?,now(),?,?,?,?)";
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
	// 회원가입
	
	public boolean isDeleteUser(String pass) {
		int deleteCount = 0;
		boolean isDeleteSuccess = false;
		PreparedStatement pstmt = null;
		
		try {
			
			String sql = "DELETE FROM member WHERE member_pass=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pass);
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
	
	
}
