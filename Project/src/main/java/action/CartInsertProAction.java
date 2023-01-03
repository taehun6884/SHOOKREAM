package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.CartInsertProService;
import vo.ActionForward;

public class CartInsertProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		int product_idx = Integer.parseInt(request.getParameter("product_idx"));
		int member_idx = Integer.parseInt(request.getParameter("member_idx"));
		int cart_price = Integer.parseInt(request.getParameter("cart_price")); // 카트에 담을 상품 가격 가져오기
		
		System.out.println("product_idx" + product_idx+"member_idx" +member_idx+ "cart_price" + cart_price);
		
		
		CartInsertProService service = new CartInsertProService();
		
		boolean result = service.InsertCart(product_idx,member_idx, cart_price);
		
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
			forward.setPath("./CartList.ca?member_idx="+member_idx+"&product_idx="+product_idx);
			forward.setRedirect(true);
		}
		
		return forward;
	}

}
