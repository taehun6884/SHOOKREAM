package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.FindMemberIdService;
import vo.ActionForward;
import vo.MemberBean;

public class FindMemberIdProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		
		MemberBean member = new MemberBean();
		
		member.setMember_email(request.getParameter("member_email"));
		member.setMember_name(request.getParameter("member_name"));
		
		System.out.println(member);
		FindMemberIdService service = new FindMemberIdService();
		String isFindSuccess = service.findId(member);
		
		request.setAttribute("member", isFindSuccess);
		
		forward = new ActionForward();
		forward.setPath("member/findID_result.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
