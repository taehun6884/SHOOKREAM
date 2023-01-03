package svc;

import java.sql.Connection;
import java.util.List;

import dao.ProductDAO;
import db.JdbcUtil;
import vo.CouponBean;

public class CouponListService {

	public List<CouponBean> getCouponList() {
		List<CouponBean> couponList = null;
		
		Connection con = JdbcUtil.getConnection();
		
		ProductDAO dao = ProductDAO.getInstance();
		
		dao.setConnection(con);
		
		couponList = dao.selectCouponList();
		
		JdbcUtil.close(con);
		
		return couponList;
	}

}
