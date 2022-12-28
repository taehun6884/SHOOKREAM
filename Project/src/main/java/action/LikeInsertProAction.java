package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.LikeInsertProService;
import vo.ActionForward;

public class LikeInsertProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		int member_idx = Integer.parseInt(request.getParameter("member_idx"));
		int product_idx = Integer.parseInt(request.getParameter("product_idx"));
		
		System.out.println("member_idx : " + member_idx + " / product_idx : "+ product_idx);
		
		LikeInsertProService servie = new LikeInsertProService();
		boolean isSuccess = servie.InserLike(member_idx, product_idx);
		
		try {
			if(isSuccess) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();

				out.println("<script>");
				out.println("alert('찜한 상품에 추가되었습니다')");
				out.println("</script>");
				
				System.out.println("결과 : " + isSuccess);
			} else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				
				out.println("<script>");
				out.println("alert('찜하기를 실패했습니다')");
				out.println("</script>");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}
