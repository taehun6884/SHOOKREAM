package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.OrderProService;
import vo.ActionForward;
import vo.OrderBean;

public class OrderProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		OrderBean vo = new OrderBean();
		vo.setOrder_category(request.getParameter("order_category"));
		vo.setOrder_progress(request.getParameter("order_progress"));
		vo.setOrder_member_idx(Integer.parseInt(request.getParameter("member_idx")));
		vo.setOrder_product_idx(Integer.parseInt(request.getParameter("product_idx")));
		
		OrderProService service = new OrderProService();
		boolean result = service.InsertOrder(vo);
		
		if(!result) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println("<script>");
				out.println("alert('카트 담기 실패!');");
				out.println("</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			forward = new ActionForward();
			forward.setPath("./ProductOrderList.po?member_idx="+vo.getOrder_member_idx());
			forward.setRedirect(true);
		}
		
		return forward;
	}

}
