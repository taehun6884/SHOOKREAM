package action;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;
import vo.CouponBean;

public class CouponInsertProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		CouponBean coupon = new CouponBean();
		
		
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-mm");
		
		coupon.setCoupon_name(request.getParameter("coupon_name"));
//		coupon.setCoupon_price(Integer.parseInt(request.getParameter("coupon_price")));
		coupon.setCoupon_content(request.getParameter("coupon_content"));
		
		System.out.println("쿠폰 이름 : " + request.getParameter("coupon_name"));
//		coupon.setCoupon_start(simpleDateFormat.format(request.getParameter("coupon_start")));
//		coupon.setCoupon_end(request.getParameter("coupon_end"));
		
		return forward;
	}

}
