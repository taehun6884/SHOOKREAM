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
import vo.ProductBean;

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
			JdbcUtil.close(pstmt);
			JdbcUtil.close(pstmt2);

		}
		return insertCount;
	}
	
	public List<BoardBean> selectBoardList(String keyword, int startRow, int listLimit, String type) {
		List<BoardBean> boardList = null;
		
	
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			String sql = "SELECT * FROM notice "
					+ "WHERE notice_type=? AND notice_subject" 
					+ " LIKE ?"
					+ " ORDER BY notice_idx DESC" 
					+ " LIMIT ?,?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, type);
			pstmt.setString(2, "%" + keyword + "%");
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, listLimit);
			rs = pstmt.executeQuery();
			
//			System.out.println("pstmt= " + pstmt);
			boardList = new ArrayList<BoardBean>();
			
			while(rs.next()) {
				BoardBean board = new BoardBean(); //
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
	
	public BoardBean selectBoard(int notice_idx) {
		BoardBean board = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			String sql = "SELECT * FROM notice WHERE notice_idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, notice_idx);
			rs = pstmt.executeQuery(); // SQL 구문 실행
			
			if(rs.next()) { 
				board = new BoardBean(); 
				board.setNotice_idx(rs.getInt("notice_idx"));
				board.setNotice_category(rs.getString("notice_category"));
				board.setNotice_subject(rs.getString("notice_subject"));
				board.setNotice_content(rs.getString("notice_content"));
				board.setNotice_readcount(rs.getInt("notice_readcount"));
				board.setNotice_date(rs.getDate("notice_date"));
				board.setNotice_type(rs.getString("notice_type"));
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류! - selectBoard()");
			e.printStackTrace();
		} finally {
			// 자원 반환
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return board; 
	}
	
	public int updateReadcount(int notice_idx) {
		int updateCount = 0;
		
		PreparedStatement pstmt = null;
		
		try {
			// 글번호가 일치하는 레코드의 조회수(readcount) 1만큼 증가
			String sql = "UPDATE notice "
								+ "SET notice_readcount=notice_readcount+1 "
								+ "WHERE notice_idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, notice_idx);
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("BoardDAO - updateReadcount()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환
			JdbcUtil.close(pstmt);
		}
		return updateCount;
	}

	public int updateBoard(BoardBean board) {
		int updateCount = 0;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "UPDATE notice SET notice_subject=?, notice_content=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getNotice_subject());
			pstmt.setString(2, board.getNotice_content());
			
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		
		return updateCount;
	}
	
	
	
	
}
