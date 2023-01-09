package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.OrderListProService;
import vo.ActionForward;
import vo.OrderBean;

public class MyPageDeliveryAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		int member_idx = Integer.parseInt(request.getParameter("member_idx"));
		OrderListProService service = new OrderListProService();
		
//		System.out.println(member_idx);
		
		OrderBean order = service.selectProgress(member_idx);
		
//		System.out.println("order_progress 확인: " + order);
		request.setAttribute("order", order);
		
		forward = new ActionForward();
		forward.setPath("member/my_page.jsp");
		forward.setRedirect(false);
		
		
		
		return forward;
	}

}
