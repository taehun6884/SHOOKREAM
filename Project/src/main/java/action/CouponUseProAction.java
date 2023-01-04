package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.CouponUseProService;
import vo.ActionForward;

public class CouponUseProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		int idx = Integer.parseInt(request.getParameter("coupon_idx"));
		
		CouponUseProService service = new CouponUseProService();
		service.CouponUsePrice(idx); 
		
		
		return forward;
	}

}
