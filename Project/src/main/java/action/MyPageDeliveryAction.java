package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.OrderProService;
import vo.ActionForward;
import vo.OrderBean;

public class MyPageDeliveryAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		
		OrderProService service = new OrderProService();
		
		OrderBean order = service.selectProgress();
		
		request.setAttribute("order", order);
		
		forward = new ActionForward();
		forward.setPath("member/my_page.jsp");
		forward.setRedirect(false);
		
		
		
		return forward;
	}

}
