package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.CartMinusProService;
import svc.CartPlusProService;
import vo.ActionForward;

public class CartMinusProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		CartMinusProService service = new CartMinusProService();
		int cart_idx = Integer.parseInt(request.getParameter("cart_idx"));
		System.out.println("cart_idx : " + cart_idx);
		//마이너스 결과 받아오기
		boolean isPlusTotal = service.CartMinusTotal(cart_idx);
		
		//금액 감소 후 CartList.ca처리를 위해 member_idx 값 받아오기
		
		if(isPlusTotal == false) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println("<script>");
				out.println("alert('장바구니 금액 감소 실패!');");
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
			forward.setPath("/CartList.ca");
			forward.setRedirect(true);
		}
		
		return forward;
	}

}
