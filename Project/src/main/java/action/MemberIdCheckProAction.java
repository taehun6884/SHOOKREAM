package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberIdCheckService;
import svc.MemberJoinProService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberIdCheckProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		String id = request.getParameter("member_id");
//		System.out.println(id);

		MemberIdCheckService service = new MemberIdCheckService();
		
		int result = service.idCheck(id);
		
		HttpSession session = request.getSession();
		session.setAttribute("result", result);
		
		forward = new ActionForward();
		forward.setPath("member/member_checkId.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
