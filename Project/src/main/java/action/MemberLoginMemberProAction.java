package action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.LoginMemberService;
import svc.ModifyMemberService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberLoginMemberProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		System.out.println(id+","+pass);
		
		LoginMemberService service = new LoginMemberService();
		boolean isLogintUser = service.isLoginUser(id,pass);
		
		ModifyMemberService service2 = new ModifyMemberService();
		MemberBean vo = service2.getMemberInfo(id);
//		System.out.println(vo);
		
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
			session.setAttribute("member_idx", vo.getMember_idx());
			
			forward = new ActionForward();
			forward.setPath("./");
			forward.setRedirect(true);
		}
		
		return forward;
	}

}
