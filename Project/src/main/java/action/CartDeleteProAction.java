package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.CartDeleteProService;
import vo.ActionForward;

public class CartDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = null;
		int idx = Integer.parseInt(request.getParameter("cart_idx"));
		int member_idx = Integer.parseInt(request.getParameter("member_idx"));
		CartDeleteProService service = new CartDeleteProService();
		System.out.println(idx);
		boolean isDeleteSuccess = service.isDeleteSuccess(idx);
		if(!isDeleteSuccess) { // 삭제 권한 없음
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
				forward.setPath("./CartList.ca?member_idx=" + member_idx + "&pageNum=1");
				forward.setRedirect(true);
				
					
				}
		
		return forward;
	}

}
