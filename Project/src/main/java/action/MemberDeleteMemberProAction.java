package action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.DeleteMemberProService;
import svc.LoginMemberService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberDeleteMemberProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
//비밀번호 일치 여부 확인 후 action으로 감 		
		DeleteMemberProService service = new DeleteMemberProService();
		boolean isDeleteSuccess = service.isDeleteSuccess(id,pass);
					if(!isDeleteSuccess) { // 삭제 권한 없음
						try {
							response.setContentType("text/html; charset=UTF-8");
							PrintWriter out = response.getWriter();
							out.println("<script>");
							out.println("alert('삭제 권한이 없습니다!')");
							out.println("history.back()");
							out.println("</script>");
						} catch (IOException e) {
							e.printStackTrace();
						}
						
						
						} else { // 삭제 성공 시
							HttpSession session = request.getSession();
							session.invalidate();
							forward = new ActionForward();
							forward.setPath("./main.MAIN");
							forward.setRedirect(true);
								
							}
							
				
				return forward;
			}

	}
