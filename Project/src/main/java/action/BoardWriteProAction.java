package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardWriteProService;
import vo.ActionForward;
import vo.BoardBean;

public class BoardWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("BoardWriteProAction");
		
		ActionForward forward = null;
		
		try {
			BoardBean board = new BoardBean();
			board.setNotice_category(request.getParameter("notice_category"));
			board.setNotice_subject(request.getParameter("notice_subject"));
			board.setNotice_content(request.getParameter("noctice_content"));
//			board.setNotice_type(request.getParameter("notice_type"));
			
			 
			BoardWriteProService service = new BoardWriteProService();
			boolean isWriteSuccess = service.registBoard(board);
			
			// 글쓰기 요청 처리 결과 판별
			if(!isWriteSuccess) {
				response.setContentType("text/html; charset=UTF-8");

				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('글쓰기 실패')");
				out.println("history.back()");
				out.println("</script>");
			} else { // 성공 시
				// 서버에서 명령. 자바스크립트 사용시 자바스크립트로 이동하는 서블릿을 설정해야함
				forward = new ActionForward();
				forward.setPath("BoardList.bo");
				forward.setRedirect(true);
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	
		
		return forward;
	}

}
