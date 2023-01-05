package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.CartInsertProService;
import vo.ActionForward;
import vo.cartBean;

public class CartInsertProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		int product_idx = Integer.parseInt(request.getParameter("product_idx"));
		int member_idx = Integer.parseInt(request.getParameter("member_idx"));
		//장바구니 정보 담는 객체 생성
		cartBean cart = new cartBean();
		cart.setCart_price(Integer.parseInt(request.getParameter("cart_price")));
		cart.setCart_count(Integer.parseInt(request.getParameter("cart_count")));
		cart.setCart_product_name(request.getParameter("cart_product_name"));
		cart.setCart_size(request.getParameter("cart_size"));
		cart.setCart_color(request.getParameter("cart_color"));
		
		
		System.out.println("product_idx= " + product_idx+"member_id= " +member_idx);
		System.out.println(cart);
		
		
		CartInsertProService service = new CartInsertProService();
		
		boolean result = service.InsertCart(product_idx,member_idx, cart);
		
		if(result == false) {
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
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println("<script>");
				out.println("alert('카트 담기 성공!');");
				out.println("</script>");
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
