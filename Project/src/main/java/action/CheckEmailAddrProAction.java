package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;

public class CheckEmailAddrProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("CheckEmailAddrProAction");
		ActionForward forward = null;
		
		String id = request.getParameter("id");
		System.out.println("id = " + id);
		
		
		
		
		return forward;
	}

}
