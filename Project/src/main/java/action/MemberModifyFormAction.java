package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ModifyMemberService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		String id = request.getParameter("id");
		
		ModifyMemberService service = new ModifyMemberService();
		MemberBean vo = service.getMemberInfo(id);
		
		request.setAttribute("member", vo);
		
		forward = new ActionForward();
		forward.setPath("member/MemberModifyForm.jsp");
		forward.setRedirect(false);
		
		return forward;
	}
}
