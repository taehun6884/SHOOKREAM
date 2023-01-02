package svc;

import java.sql.Connection;

import dao.ProductDAO;
import db.JdbcUtil;
import vo.CouponBean;

public class CouponModifyService {

	public CouponBean getCoupon(int coupon_idx) {
		CouponBean coupon = null;
		
		Connection con = JdbcUtil.getConnection();
		
		ProductDAO dao = ProductDAO.getInstance();
		
		dao.setConnection(con);

		coupon = dao.selectCoupon(coupon_idx);
		
		
		JdbcUtil.close(con);
		
		return coupon;
	}

}
