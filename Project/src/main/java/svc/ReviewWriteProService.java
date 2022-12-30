package svc;

import java.sql.Connection;

import dao.MemberDAO;
import db.JdbcUtil;
import vo.ReviewBean;

public class ReviewWriteProService {

	public boolean insertReview(ReviewBean review) {
		boolean isReviewSuccess = false;
		
		Connection con = JdbcUtil.getConnection();
		
		MemberDAO dao = MemberDAO.getInstance();
		
		dao.setConnection(con);
		
		int insertCount = dao.insertReview(review);
		
		if(insertCount > 0) {
			JdbcUtil.commit(con);
			isReviewSuccess = true;
		} else {
			JdbcUtil.rollback(con);
		}
		
		return isReviewSuccess;
	}
	
}
