package action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.CouponListService;
import svc.MemberListService;
import vo.ActionForward;
import vo.CouponBean;
import vo.MemberBean;

public class CouponListProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		CouponListService service = new CouponListService();
		List<CouponBean> couponList = new ArrayList<CouponBean>();
		
		couponList = service.getCouponList();
		
		request.setAttribute("couponList", couponList);
		
		forward = new ActionForward();
		forward.setPath("admin/admin_coupon_List.jsp");
		forward.setRedirect(false);
		
		
		
		return forward;
	}

}
