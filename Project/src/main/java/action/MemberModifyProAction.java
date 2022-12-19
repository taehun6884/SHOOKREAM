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
//		member.setMember_pass(request.getParameter("oldpass"));
		member.setMember_address(request.getParameter("address"));
		member.setMember_email(request.getParameter("email"));
		member.setMember_phone(request.getParameter("phone"));
		String newpass1 = request.getParameter("newpass1");
		String newpass2 = request.getParameter("newpass2");
		
//		System.out.println(request.getParameter("newpass1"));
//		System.out.println(request.getParameter("newpass2"));
//		System.out.println(request.getParameter("name"));
//		System.out.println(newpass1);
		
		if(newpass1.equals(newpass2)) {
			member.setMember_pass(request.getParameter("newpass1"));
		} else {
			member.setMember_pass(request.getParameter("oldpass"));
		}
		
		ModifyMemberService service = new ModifyMemberService();
		
		boolean updateMember = service.updateMember(member); 

		if(updateMember) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println("<script>");
				out.println("alert('회원 수정 성공!');");
				out.println("</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			forward = new ActionForward();
			forward.setPath("./");
			forward.setRedirect(true);
		} else{
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out;
				try {
					out = response.getWriter();
					out.println("<script>");
					out.println("alert('회원 수정 실패!');");
					out.println("history.back()");
					out.println("</script>");
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return forward;
		
	}
	

}
