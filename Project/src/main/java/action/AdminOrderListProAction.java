package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.AdminOrderListProService;
import svc.OrderListProService;
import vo.ActionForward;
import vo.OrderBean;

public class AdminOrderListProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		AdminOrderListProService service = new AdminOrderListProService();
		List<OrderBean>Adminorderlist = service.getOrderList();
		
		request.setAttribute("Adminorderlist", Adminorderlist);
		
		forward = new ActionForward();
		forward.setPath("admin/admin_order_list.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
