package svc;

import java.sql.Connection;

import dao.BoardDAO;
import db.JdbcUtil;
import vo.BoardBean;

public class BoardWriteProService {

	public boolean registBoard(BoardBean board) {
		
		boolean isWriteSuccess = false;
	
		Connection con = JdbcUtil.getConnection();
	
		BoardDAO dao = BoardDAO.getInstance();

		dao.setConnection(con);
	
		int insertCount = dao.insertBoard(board);
		
		if(insertCount > 0) {
			JdbcUtil.commit(con);
			isWriteSuccess = true;
		} else {
			JdbcUtil.rollback(con);
		}
		
		JdbcUtil.close(con);

		return isWriteSuccess;
	}
	
}
