package svc;

import java.sql.Connection;

import dao.BoardDAO;
import db.JdbcUtil;
import vo.BoardBean;

public class BoardModifyProService {

	public boolean modifyBoard(BoardBean board) {
		boolean isModifySuccess = false;
		
		Connection con = JdbcUtil.getConnection();
		BoardDAO dao = BoardDAO.getInstance();
		dao.setConnection(con);

		int updateCount = dao.updateBoard(board);
		
		if(updateCount > 0) {
			JdbcUtil.commit(con);
			isModifySuccess = true;
		} else {
			JdbcUtil.rollback(con);
		}
			
		JdbcUtil.close(con);
		
		return isModifySuccess;

	}
	
}
