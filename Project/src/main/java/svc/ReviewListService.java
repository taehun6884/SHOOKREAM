package svc;

import java.sql.Connection;
import java.util.List;

import dao.ProductDAO;
import db.JdbcUtil;
import vo.ReviewBean;

public class ReviewListService {

	public List<ReviewBean> getReviewList(int startRow, int listLimit, int product_idx) {
		List<ReviewBean> reviewList = null; 
		
		Connection con = JdbcUtil.getConnection();
		
		ProductDAO dao = ProductDAO.getInstance();
		
		dao.setConnection(con);

		reviewList = dao.selectReviewList(startRow, listLimit,product_idx);

		
		JdbcUtil.close(con);
		
		
		return reviewList;
	}
		

	public int getReviewListCount() {
		int listCount = 0;
		
		Connection con = JdbcUtil.getConnection();
		
		ProductDAO dao = ProductDAO.getInstance();
		
		dao.setConnection(con);
		
		listCount = dao.selectReviewListCount();
		
		JdbcUtil.close(con);
		
		return listCount;
	}

}
