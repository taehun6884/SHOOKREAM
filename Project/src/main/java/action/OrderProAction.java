package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.CartListProService;
import svc.OrderProService;
import vo.ActionForward;
import vo.OrderBean;
import vo.PageInfo;
import vo.ProductBean;

public class OrderProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
			ActionForward forward = null;
			int listLimit = 10; // 한 페이지에서 표시할 게시물 목록을 10개로 제한
			int pageNum = 1; // 현재 페이지 번호 설정(pageNum 파라미터 사용)
			
			OrderBean vo = new OrderBean();
			vo.setOrder_category(request.getParameter("order_category"));
			vo.setOrder_progress(request.getParameter("order_progress"));
			vo.setOrder_member_idx(Integer.parseInt(request.getParameter("member_idx")));
			vo.setOrder_product_idx(Integer.parseInt(request.getParameter("product_idx")));
			vo.setOrder_product_sell_count(Integer.parseInt(request.getParameter("product_sell_count")));
			vo.setOrder_product_amount(Integer.parseInt(request.getParameter("product_amount")));
			
			System.out.println(vo);
			
			OrderProService service = new OrderProService();
			boolean result = service.InsertOrder(vo);
			
			if(!result) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out;
				try {
					out = response.getWriter();
					out.println("<script>");
					out.println("alert('주문 실패!');");
					out.println("</script>");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else {
				forward = new ActionForward();
				forward.setPath("./ProductOrderList.po?member_idx="+vo.getOrder_member_idx()+"&product_idx"+vo.getOrder_product_idx());
				forward.setRedirect(true);
			}
		
		return forward;
	}

}
