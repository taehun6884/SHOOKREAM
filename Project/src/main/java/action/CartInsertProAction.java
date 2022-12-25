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
		
		System.out.println(product_idx+","+member_idx);
		
		
		CartInsertProService service = new CartInsertProService();
		
		boolean result = service.InsertCart(product_idx,member_idx);
		
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
			forward.setPath("./CartList.ca");
			forward.setRedirect(true);
		}
		
		return forward;
	}

}
