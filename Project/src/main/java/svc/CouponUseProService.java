package svc;

import java.sql.Connection;

import dao.ProductDAO;
import db.JdbcUtil;

public class CouponUseProService {

	public int CouponUsePrice(int idx) {
		int coupon_price = 0;
		Connection con = JdbcUtil.getConnection();
		
		ProductDAO dao = ProductDAO.getInstance();
		
		dao.setConnection(con);
		
		coupon_price = dao.CouponUsePrice(idx);
		
		JdbcUtil.close(con);
		
		return coupon_price;
	}

}
