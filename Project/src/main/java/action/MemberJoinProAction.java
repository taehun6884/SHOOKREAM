package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MemberJoinProService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberJoinProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		MemberBean member = new MemberBean();
		
		String address = request.getParameter("address") + " " + request.getParameter("address_detail");
		
		member.setMember_id(request.getParameter("id"));
		member.setMember_name(request.getParameter("name"));
		member.setMember_pass(request.getParameter("pass"));
		member.setMember_email(request.getParameter("email"));
		member.setMember_address(address);
		member.setMember_phone(request.getParameter("phone"));
//		System.out.println("address " + address);
		System.out.println(member);
		
		MemberJoinProService service = new MemberJoinProService();
		boolean joinMember = service.joinMember(member);
		
		try {
			if(!joinMember) { // 실패 시
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				
				out.println("<script>");
				out.println("alert('회원가입 실패!')");
				out.println("history.back()");
				out.println("</script>");
			} else { // 성공 시
				forward = new ActionForward();
				forward.setPath("member/member_join_result.jsp");
				forward.setRedirect(true);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		return forward;
	}

}
