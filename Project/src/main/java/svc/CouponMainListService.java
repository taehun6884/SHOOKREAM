package svc;

import java.sql.Connection;
import java.util.List;

import dao.ProductDAO;
import db.JdbcUtil;
import vo.CouponBean;

public class CouponMainListService {

	public List<CouponBean> getCouponMainList(String coupon_content) {
		List<CouponBean> couponList = null;
		
		Connection con = JdbcUtil.getConnection();
		
		ProductDAO dao = ProductDAO.getInstance();
		
		dao.setConnection(con);
		
		couponList = dao.selectCouponMainList(coupon_content);
		
		JdbcUtil.close(con);
		
		return couponList;
	}

}
