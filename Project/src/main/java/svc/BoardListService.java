package svc;

import java.sql.Connection;
import java.util.List;

import dao.BoardDAO;
import db.JdbcUtil;
import vo.BoardBean;

public class BoardListService {

	public List<BoardBean> getBoardList(String keyword, int startRow, int listLimit) {
		List<BoardBean> boardList = null; 
		
		Connection con = JdbcUtil.getConnection();
		
		BoardDAO dao = BoardDAO.getInstance();
		
		dao.setConnection(con);
		
		// BoardDAO 객체의 selectBoardList() 메서드를 호출하여 글목록 작업수행
		// commit rollback? select이므로 안해도 됨(작업이 수행되는게 아니라 데이터가 선택되는것이므로)
		boardList = dao.selectBoardList(keyword, startRow, listLimit);
		
		JdbcUtil.close(con);
		
		
		return boardList;
	}

	public int getBoardListCount(String keyword) {
		int listCount = 0;
		
		Connection con = JdbcUtil.getConnection();
		
		BoardDAO dao = BoardDAO.getInstance();
		
		dao.setConnection(con);
		
		listCount = dao.selectBoardListCount(keyword);
		
		JdbcUtil.close(con);
		
		return listCount;
	}

}
