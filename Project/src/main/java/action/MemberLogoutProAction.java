package action;

import javax.servlet.http.HttpSession;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;

public class MemberLogoutProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		forward = new ActionForward();
		forward.setPath("./");
		forward.setRedirect(true);
		return forward;
	}

}