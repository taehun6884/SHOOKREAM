package svc;

import java.sql.Connection;
import java.util.List;

import dao.MemberDAO;
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


	public List<ReviewBean> getMyReviewList(int startRow, int listLimit, int member_idx) {
		List<ReviewBean> myReviewList = null; 
		
		Connection con = JdbcUtil.getConnection();
		
		MemberDAO dao = MemberDAO.getInstance();
		
		dao.setConnection(con);

		myReviewList = dao.selectMyReviewList(startRow, listLimit, member_idx);
		
//		System.out.println("svc - reviewList" + myReviewList);
		
		JdbcUtil.close(con);
		
		
		return myReviewList;
	}
	

}
