package svc;

import java.sql.Connection;

import dao.ProductDAO;
import db.JdbcUtil;
import vo.CouponBean;

public class CouponModifyProService {

	public boolean updateCoupon(int coupon_idx,CouponBean coupon) {
		boolean updateCoupon = false;
		
		Connection con = JdbcUtil.getConnection();
		
		ProductDAO dao = ProductDAO.getInstance();
		
		dao.setConnection(con);

		int updatecount = dao.updateCoupon(coupon_idx, coupon);
		
		if(updatecount > 0) {
			updateCoupon = true;
			JdbcUtil.commit(con);
		} else {
			JdbcUtil.rollback(con);
		}
		
		JdbcUtil.close(con);
		
		return updateCoupon;
	}

}
