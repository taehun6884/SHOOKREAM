package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.CouponDeleteProService;
import svc.CouponModifyProService;
import vo.ActionForward;

public class CouponDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		int coupon_idx = Integer.parseInt(request.getParameter("coupon_idx"));
		
		CouponDeleteProService service = new CouponDeleteProService();
		boolean deleteCoupon = service.deleteCoupon(coupon_idx);
		
		try {
			if(!deleteCoupon) { // 실패 시
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				
				out.println("<script>");
				out.println("alert('쿠폰 삭제 실패!')");
				out.println("history.back()");
				out.println("</script>");
			} else { // 성공 시
				forward = new ActionForward();
				forward.setPath("CouponList.po");
				forward.setRedirect(false);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}
