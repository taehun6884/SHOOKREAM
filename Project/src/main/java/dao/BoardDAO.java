package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.JdbcUtil;
import vo.BoardBean;

public class BoardDAO {
private BoardDAO() {}
	
	private static BoardDAO instance = new BoardDAO();

	public static BoardDAO getInstance() {
		return instance;
	}
	
	private Connection con;

	public void setConnection(Connection con) {
		this.con = con;
	}
	
	public int insertBoard(BoardBean board) {
//		System.out.println("BoardDAO - insertBoard()");
		int insertCount = 0;
		
		// 데이터베이스 작업에 필요한 변수 선언
		PreparedStatement pstmt=null, pstmt2=null;
		ResultSet rs = null; // select문 쓸 때 필요
		
		try {
			int notice_idx = 1; // 새 글 번호
			
			String sql = "SELECT MAX(notice_idx) FROM notice";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(); 
			
			if(rs.next()) { // 조회 결과가 있을 경우
				notice_idx = rs.getInt(1) + 1; // 기존 게시물 번호 중 가장 큰 번호(= 조회 결과) + 1
			}
			
			System.out.println("새 글 번호 : " + notice_idx);
			
			sql = "INSERT INTO notice VALUES(?, ?, ?, ?, ?, now(), ?)";
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setInt(1, notice_idx);
			pstmt2.setString(2, board.getNotice_category());
			pstmt2.setString(3, board.getNotice_subject());
			pstmt2.setString(4, board.getNotice_content());
			pstmt2.setInt(5, board.getNotice_readcount());
			pstmt2.setString(6, board.getNotice_type());
			
			insertCount = pstmt2.executeUpdate();
			System.out.println(pstmt2);
			
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류! - insertBoard()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
//			JdbcUtil.close(pstmt);
			JdbcUtil.close(pstmt2);

		}
		return insertCount;
	}
	
	public List<BoardBean> selectBoardList(String keyword, int startRow, int listLimit) {
		List<BoardBean> boardList = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			String sql = "SELECT * FROM notice "
					+ "WHERE notice_subject "
					+ "LIKE ? "
					+ "ORDER BY notice_idx DESC" 
					+ "LIMIT ?,?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, listLimit);
			rs = pstmt.executeQuery();

			boardList = new ArrayList<BoardBean>();
			
			while(rs.next()) {
				BoardBean board = new BoardBean();
				board.setNotice_idx(rs.getInt("notice_idx"));
				board.setNotice_category(rs.getString("notice_category"));
				board.setNotice_subject(rs.getString("notice_subject"));
				board.setNotice_content(rs.getString("notice_content"));
				board.setNotice_readcount(rs.getInt("notice_readcount"));
				board.setNotice_date(rs.getDate("notice_date"));
				board.setNotice_type(rs.getString("notice_type"));
				
				boardList.add(board);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return boardList;
	}
	
	public int selectBoardListCount(String keyword) {
		int listCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			String sql = "SELECT COUNT(*) FROM notice WHERE notice_subject LIKE ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");

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
	
	
	
}
