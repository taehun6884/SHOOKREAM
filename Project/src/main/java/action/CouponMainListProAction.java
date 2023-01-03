package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.CouponMainListService;
import svc.ProductListService;
import vo.ActionForward;
import vo.CouponBean;
import vo.ProductBean;

public class CouponMainListProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		String coupon_content = request.getParameter("coupon_content");
		
		CouponMainListService service = new CouponMainListService();
		List<CouponBean> couponList = service.getCouponMainList(coupon_content);
		request.setAttribute("couponList", couponList);
		
		forward = new ActionForward();
		forward.setPath("CouponDownloadForm.po?coupon_content="+coupon_content);
		forward.setRedirect(false);
		
		return forward;
	}

}
