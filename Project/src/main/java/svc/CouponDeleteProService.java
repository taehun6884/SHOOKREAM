package svc;

import java.sql.Connection;

import dao.ProductDAO;
import db.JdbcUtil;

public class CouponDeleteProService {

	public boolean deleteCoupon(int coupon_idx) {
		boolean deleteCoupon = false;
		
		Connection con = JdbcUtil.getConnection();
		
		ProductDAO dao = ProductDAO.getInstance();
		
		dao.setConnection(con);

		int updatecount = dao.deleteCoupon(coupon_idx);
		
		if(updatecount > 0) {
			deleteCoupon = true;
			JdbcUtil.commit(con);
		} else {
			JdbcUtil.rollback(con);
		}
		
		JdbcUtil.close(con);
		
		return deleteCoupon;
	}

}
