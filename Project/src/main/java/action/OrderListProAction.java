package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.OrderListProService;
import vo.ActionForward;
import vo.OrderBean;

public class OrderListProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		int member_idx = Integer.parseInt(request.getParameter("member_idx"));
		
		
		OrderListProService service = new OrderListProService();
		List<OrderBean>orderlist = service.getOrderList(member_idx);
		request.setAttribute("orderlist", orderlist);
		
		forward = new ActionForward();
		forward.setPath("product/Product_orderlist.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
