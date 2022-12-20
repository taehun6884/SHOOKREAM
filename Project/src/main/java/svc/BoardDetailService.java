package svc;

import java.sql.Connection;

import dao.BoardDAO;
import db.JdbcUtil;
import vo.BoardBean;

public class BoardDetailService {

	public BoardBean getBoard(int notice_idx, boolean isUpdateReadcount) {
		BoardBean board = null;
		
		Connection con = JdbcUtil.getConnection(); 
		
		BoardDAO dao = BoardDAO.getInstance(); 
		
		dao.setConnection(con); 
		
		board = dao.selectBoard(notice_idx);

		if(board != null && isUpdateReadcount) {
			int updateCount = dao.updateReadcount(notice_idx);
			
			if(updateCount > 0) { 
				JdbcUtil.commit(con);

			board.setNotice_readcount(board.getNotice_readcount() + 1);
			}
		}
		
		JdbcUtil.close(con);
		
		
		return board;
	}

}
