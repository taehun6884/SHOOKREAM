package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MemberJoinProService;
import svc.AuthEmailProService;
import vo.ActionForward;
import vo.AuthBean;
import vo.MemberBean;

public class CompareAddrProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("CompareAddrProAction");
		ActionForward forward = null;
		
		String id = request.getParameter("id");
		String authCode = request.getParameter("authCode");
		System.out.println("id : " + id + " || authCode : " + authCode);
		
//		AuthBean auth = new AuthBean();
//		auth.setAuth_id(id);
//		auth.setAuth_authCode(authCode);
//		System.out.println("auth.getAuth_authCode : " + auth.getAuth_authCode());
		
		
		AuthEmailProService service = new AuthEmailProService();
		boolean authEmail = service.selectMember(authCode, id);
		System.out.println("결과 : " + authEmail);
		
		try {
			if(authEmail == false) { // 인증코드가 불일치할 경우
				System.out.println("불일치");
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("false");
				forward = new ActionForward();
				forward.setPath("MemberJoinForm.me");
				forward.setRedirect(true);
			} else { // 인증 코드가 일치 할 경우
				System.out.println("일치");
				
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("true");
				
			}
			
		} catch (IOException e) {
			System.out.println("CompareAddrProAction");
			e.printStackTrace();
		}
		
		
//		String authResult = request.getParameter(authCode);
//		System.out.println("authResult : " + authResult);
		
		return forward;
		
	}

}
