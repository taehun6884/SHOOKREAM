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
//		member.setMember_address(request.getParameter("address"));
		member.setMember_email(request.getParameter("email"));
//		member.setMember_phone(request.getParameter("phone"));
		String newpass1 = request.getParameter("newpass1");
		String newpass2 = request.getParameter("newpass2");
		
//		System.out.println(request.getParameter("newpass1"));
//		System.out.println(request.getParameter("newpass2"));
//		System.out.println(request.getParameter("name"));
//		System.out.println(newpass1);
		
//		if(newpass1.equals(newpass2)) {
//			member.setMember_pass(request.getParameter("newpass1"));
//		} else {
//			member.setMember_pass(request.getParameter("oldpass"));
//		}
		
		if(newpass1.equals("") && newpass2.equals("")) {
			member.setMember_pass(request.getParameter("oldpass"));
		}else {
			member.setMember_pass(request.getParameter("newpass1"));
		}
		
		if(request.getParameter("address").equals("")) {
			member.setMember_address(request.getParameter("oldaddress"));
		} else {
			member.setMember_address(request.getParameter("address") + " " + request.getParameter("address_detail"));
		}
		
		if(request.getParameter("phone").equals("")) {
			member.setMember_phone(request.getParameter("oldphone"));
		} else {
			member.setMember_phone(request.getParameter("phone"));
		}
		
		ModifyMemberService service = new ModifyMemberService();
		
		boolean updateMember = service.updateMember(member); 
		
		int member_idx = service.selectMemberIdx(request.getParameter("id"));

		try {
		if(updateMember) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('정보를 수정했습니다!')");
			out.println("</script>");	
			
			forward = new ActionForward();
			forward.setPath("MemberMyPage.me?member_id="+member.getMember_id()+"&member_idx="+member_idx);
			forward.setRedirect(true);
		} else{
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('정보 수정을 실패했습니다!')");
			out.println("history.back()");
			out.println("</script>");	
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return forward;
		
	}
	

}
