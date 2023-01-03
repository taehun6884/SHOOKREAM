package action;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.CouponInsertProService;
import svc.MemberJoinProService;
import vo.ActionForward;
import vo.CouponBean;

public class CouponInsertProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		CouponBean coupon = new CouponBean();
		
		coupon.setCoupon_name(request.getParameter("coupon_name"));
		coupon.setCoupon_price(Integer.parseInt(request.getParameter("coupon_price")));
		coupon.setCoupon_content(request.getParameter("coupon_content"));
		coupon.setCoupon_start(request.getParameter("coupon_start"));
		coupon.setCoupon_end(request.getParameter("coupon_end"));
		
		CouponInsertProService service = new CouponInsertProService();
		boolean insertCoupon = service.insertCoupon(coupon);
		
		try {
			if(!insertCoupon) { // 실패 시
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				
				out.println("<script>");
				out.println("alert('쿠폰 등록 실패!')");
				out.println("history.back()");
				out.println("</script>");
			} else { // 성공 시
				forward = new ActionForward();
				forward.setPath("admin/admin_product.jsp");
				forward.setRedirect(false);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}
