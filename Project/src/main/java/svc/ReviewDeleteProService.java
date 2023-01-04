package svc;

import java.sql.Connection;

import dao.ProductDAO;
import db.JdbcUtil;

public class ReviewDeleteProService {

	public boolean deleteReview(int review_idx) {
		boolean isDeleteSuccess = false;
		
		Connection con = JdbcUtil.getConnection();
		ProductDAO dao = ProductDAO.getInstance();
		dao.setConnection(con);
		
		int deleteCount = dao.deleteBoard(review_idx);
		
		if(deleteCount > 0) {
			JdbcUtil.commit(con);
			isDeleteSuccess = true;
		} else {
			JdbcUtil.rollback(con);
		}
		
		JdbcUtil.close(con);
		return isDeleteSuccess;
	}
	
}
