package svc;

import java.sql.Connection;
import java.util.List;

import dao.MemberDAO;
import dao.ProductDAO;
import db.JdbcUtil;
import vo.ReviewBean;

public class ReviewWriteProService {
	
	// 리뷰 작성 insert
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
	
	// 상품 색상카테고리 가져오기
	public List<String> ProductColorCategory(String product_name) {
		List<String> colorlist = null;
		Connection con = JdbcUtil.getConnection();
		
		ProductDAO dao = ProductDAO.getInstance();
		
		dao.setConnection(con);			
		
		colorlist = dao.ProductColorCategory(product_name);
		
		return colorlist;
	}
	
	// 상품 사이즈 카테고리 가져오기
	public List<String> getCategoryList(String product_name) {
		List<String> categorylist = null;
		Connection con = JdbcUtil.getConnection();
		
		ProductDAO dao = ProductDAO.getInstance();
		
		dao.setConnection(con);			
		
		categorylist = dao.ProductCategory(product_name);
		
		return categorylist;
	}
	
}
