package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardDeleteProService;
import svc.ReviewDeleteProService;
import vo.ActionForward;

public class ReviewDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		int review_idx = Integer.parseInt(request.getParameter("review_idx"));
		
		System.out.println("DeleteProAction - review_idx = " + request.getParameter("review_idx"));
		
		ReviewDeleteProService service = new ReviewDeleteProService();
		
		boolean isDeleteSuccess = service.deleteReview(review_idx);
		
		try {
			if(!isDeleteSuccess) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제에 실패하였습니다.')");
				out.println("history.back()");
				out.println("</script>");
			} else {
				forward = new ActionForward();
				forward.setPath("ProductInfoForm.po?product_idx=" + request.getParameter("product_idx") + "&member_idx=" + request.getParameter("member_idx"));
				forward.setRedirect(true);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return forward;
	}

}
