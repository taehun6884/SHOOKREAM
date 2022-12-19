package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDAO;
import svc.ModifyMemberService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		MemberBean member = new MemberBean();

		member.setMember_name(request.getParameter("name"));
		member.setMember_id(request.getParameter("id"));
		member.setMember_pass(request.getParameter("pass"));
		member.setMember_address(request.getParameter("address"));
		member.setMember_email(request.getParameter("email"));
		member.setMember_phone(request.getParameter("phone"));
//	 	System.out.println(member);	
		
		ModifyMemberService service = new ModifyMemberService();
		
		int updateMember = service.updateMember(member); 
		
		if(updateMember == 0) {
			response.setContentType("text/html; charset=UTF-8");
			
			try {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('회원 수정 실패!')");
				out.println("history.back()");
				out.println("</script>");
			} catch (IOException e) {
				e.printStackTrace();
			} 
			} else {
				
				forward = new ActionForward();
				forward.setPath("./");
				forward.setRedirect(true);
			}
		return forward;
		
	}
	

}
