package action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.CouponUseProService;
import vo.ActionForward;

public class CouponResultAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		int idx = Integer.parseInt(request.getParameter("coupon_idx"));
		System.out.println("CouponUseProAction - 도착");
		
		System.out.println(idx);
		CouponUseProService service = new CouponUseProService();
		int coupon_price = service.CouponUsePrice(idx); 
		System.out.println(coupon_price);
		
		request.setAttribute("coupon_price", coupon_price);
		
		if(coupon_price > 0) {
			forward = new ActionForward();
			forward.setPath("product/order_form.jsp");
			forward.setRedirect(false);
		}
		return forward;
	}

}
