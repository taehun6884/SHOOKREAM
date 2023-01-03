package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.CouponModifyService;
import svc.ProductModifyService;
import vo.ActionForward;
import vo.CouponBean;
import vo.ProductBean;
import vo.imageBean;

public class CouponModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		int coupon_idx = Integer.parseInt(request.getParameter("coupon_idx"));
//		System.out.println(coupon_idx);
		CouponModifyService service = new CouponModifyService();
		CouponBean coupon = service.getCoupon(coupon_idx);
		request.setAttribute("coupon", coupon);
		
		
		forward = new ActionForward();
		forward.setPath("admin/admin_coupon_ModifyForm.jsp");
		forward.setRedirect(false);
		
		
		return forward;
	}

}
