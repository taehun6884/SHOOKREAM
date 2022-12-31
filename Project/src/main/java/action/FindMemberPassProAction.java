package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.FindMemberIdService;
import svc.FindMemberPassService;
import vo.ActionForward;
import vo.MemberBean;

public class FindMemberPassProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		
		MemberBean member = new MemberBean();
		
		member.setMember_id(request.getParameter("member_id"));
		member.setMember_name(request.getParameter("member_name"));
		member.setMember_email(request.getParameter("member_email"));
		
		System.out.println(member);
		FindMemberPassService service = new FindMemberPassService();
		String isFindSuccess = service.findPass(member);
		
		request.setAttribute("member", isFindSuccess);
		
		forward = new ActionForward();
		forward.setPath("member/MemberLoginForm.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
