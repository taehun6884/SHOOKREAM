package svc;

import java.sql.Connection;
import java.util.List;

import dao.BoardDAO;
import db.JdbcUtil;
import vo.ActionForward;
import vo.BoardBean;

public class BoardListService {

	public List<BoardBean> getBoardList(String keyword, int startRow, int listLimit , String type) {
		List<BoardBean> boardList = null; 
		
		Connection con = JdbcUtil.getConnection();
		
		BoardDAO dao = BoardDAO.getInstance();
		
		dao.setConnection(con);

		boardList = dao.selectBoardList(keyword, startRow, listLimit, type);

		
		JdbcUtil.close(con);
		
		
		return boardList;
	}

	public int getBoardListCount(String keyword,String notice_type) {
		int listCount = 0;
		
		Connection con = JdbcUtil.getConnection();
		
		BoardDAO dao = BoardDAO.getInstance();
		
		dao.setConnection(con);
		
		listCount = dao.selectBoardListCount(keyword,notice_type);
		
		JdbcUtil.close(con);
		
		return listCount;
	}

}
