package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ProductDeleteProService;
import vo.ActionForward;

public class ProductDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = null;
		
		int idx = Integer.parseInt(request.getParameter("product_idx"));
		System.out.println(idx);
		ProductDeleteProService service = new ProductDeleteProService();
		
		boolean isDeleteProduct = service.isDeleteProduct(idx);
		if(!isDeleteProduct) { // 삭제 권한 없음
			try {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제 권한이 없습니다!')");
				out.println("history.back()");
				out.println("</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
		} else { // 삭제 성공 시
				
			forward = new ActionForward();
			forward.setPath("./ProductList.po");
			forward.setRedirect(true);
			
				
		}
		return forward;
	}

}
