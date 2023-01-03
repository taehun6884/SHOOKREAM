package svc;

import java.sql.Connection;

import dao.ProductDAO;
import db.JdbcUtil;
import vo.CouponBean;

public class CouponInsertProService {

	public boolean insertCoupon(CouponBean coupon) {
		boolean insertCoupon = false;
		
		Connection con = JdbcUtil.getConnection();
		
		ProductDAO dao = ProductDAO.getInstance();
		
		dao.setConnection(con);
		
		int insertCount = dao.insertCoupon(coupon);
		
		if(insertCount > 0) { // 성공 시
			JdbcUtil.commit(con);
			insertCoupon = true;
		} else { // 실패 시
			JdbcUtil.rollback(con);
		}
		
		JdbcUtil.close(con);
		
		return insertCoupon;
	}
	
}
