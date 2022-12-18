package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.LoginMemberService;
import vo.ActionForward;

public class MemberLoginMemberProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		System.out.println(id+","+pass);
		
		LoginMemberService service = new LoginMemberService();
		boolean isLogintUser = service.isLoginUser(id,pass);
		
		if(isLogintUser == false ) {
			response.setContentType("text/html; charset=UTF-8");
			
			try {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('로그인 실패!')");
				out.println("history.back()");
				out.println("</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("sId", id);

			forward = new ActionForward();
			forward.setPath("./");
			forward.setRedirect(true);
		}
		
		return forward;
	}

}
