package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.LikeDeleteProService;
import svc.LikeInsertProService;
import vo.ActionForward;

public class LikeDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		int member_idx = Integer.parseInt(request.getParameter("member_idx"));
		int product_idx = Integer.parseInt(request.getParameter("product_idx"));
		
		System.out.println("member_idx : " + member_idx + " / product_idx : "+ product_idx);
		
		LikeDeleteProService servie = new LikeDeleteProService();
		boolean isSuccess = servie.DeleteWish(member_idx, product_idx);
		
		try {
			if(isSuccess) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();

				out.println("<script>");
				out.println("alert('찜한 상품에서 삭제되었습니다')");
				out.println("</script>");
				
				System.out.println("idDeleteSuccess? : " + isSuccess);
				
				forward = new ActionForward();
				forward.setPath("./LikeList.ca?member_idx=" + member_idx + "&pageNum=1");
				forward.setRedirect(true);
			} else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				
				out.println("<script>");
				out.println("alert('찜 삭제 실패')");
				out.println("</script>");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}
