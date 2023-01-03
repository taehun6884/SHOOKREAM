package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.CartInsertProService;
import svc.CartPlusProService;
import vo.ActionForward;

public class CartPlusProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		CartPlusProService service = new CartPlusProService();
		int cart_idx = Integer.parseInt(request.getParameter("cart_idx"));
		System.out.println("cart_idx : " + cart_idx);
		boolean isPlusTotal = service.CartPlusTotal(cart_idx);
		
		if(isPlusTotal == false) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println("<script>");
				out.println("alert('장바구니 추가 실패!');");
				out.println("</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out;
			try {
				out = response.getWriter();
				out.print("true");
			} catch (IOException e) {	
				e.printStackTrace();
			}
			
			forward = new ActionForward();
			forward.setPath("./");
			forward.setRedirect(true);
		}
		
		return forward;
	}

}
