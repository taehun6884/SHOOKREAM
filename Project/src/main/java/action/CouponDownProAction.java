package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.CouponInsertProService;
import vo.ActionForward;

public class CouponDownProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		int member_idx = Integer.parseInt(request.getParameter("member_idx"));
		String coupon_content = request.getParameter("coupon_content");
		
		System.out.println("member_idx : " + member_idx + " / couopn_content : "+ coupon_content);
		
		CouponInsertProService servie = new CouponInsertProService();
		boolean isSuccess = servie.DownCoupon(member_idx, coupon_content);
		System.out.println("쿠폰 지급 결과 : " + isSuccess);
		
		try {
			if(isSuccess) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();

				out.println("<script>");
				out.println("alert('쿠폰이 지급되었습니다')");
				out.println("</script>");
				
				System.out.println("결과 : " + isSuccess);
			} else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				
				out.println("<script>");
				out.println("alert('쿠폰 지급 실패')");
				out.println("</script>");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}
