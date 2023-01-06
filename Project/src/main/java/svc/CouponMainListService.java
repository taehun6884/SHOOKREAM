package svc;

import java.sql.Connection;
import java.util.List;

import dao.ProductDAO;
import db.JdbcUtil;
import vo.CouponBean;
import vo.MemberCouponBean;
import vo.WishBean;

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

	public MemberCouponBean getMemberCouponInfo(String coupon_content, int member_idx) {
		MemberCouponBean member_coupon = null;
		
		Connection con = JdbcUtil.getConnection();
		
		ProductDAO dao = ProductDAO.getInstance();
		
		dao.setConnection(con);
		
		member_coupon = dao.selectMemberCoupon(coupon_content, member_idx);
		
		JdbcUtil.close(con);
		
		return member_coupon;
	}

}
