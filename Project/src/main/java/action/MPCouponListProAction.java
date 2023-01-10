package action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.CouponListService;
import vo.ActionForward;
import vo.CouponBean;

public class MPCouponListProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		int member_idx = Integer.parseInt(request.getParameter("member_idx"));
		System.out.println(member_idx);
		CouponListService service = new CouponListService();
		List<CouponBean> couponList = new ArrayList<CouponBean>();
		
		couponList = service.MemberCouponList(member_idx);
		
		request.setAttribute("couponList", couponList);
		
		forward = new ActionForward();
		forward.setPath("product/Product_mp_couponlist.jsp");
		forward.setRedirect(false);
		
		
		
		return forward;
	}

}
