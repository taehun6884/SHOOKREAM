package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;

public class LikeInsertProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		String member_idx = request.getParameter("member_idx");
		String product_idx = request.getParameter("product_idx");
		
		System.out.println(member_idx + "/" + product_idx);
		
		return forward;
	}

}
