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
		
		ActionForward forward = null;
		
		try {
			BoardBean board = new BoardBean();
			board.setNotice_category(request.getParameter("notice_category"));
			board.setNotice_subject(request.getParameter("notice_subject"));
			board.setNotice_content(request.getParameter("notice_content"));
			board.setNotice_type(request.getParameter("notice_type"));
			
//			System.out.println(board);
			 
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
				if(request.getParameter("notice_type").equals("Notice")) {
					forward = new ActionForward();
					forward.setPath("AdminNoticeManage.ad");
					forward.setRedirect(true);		
				} else {
					forward = new ActionForward();
					forward.setPath("AdminFAQManage.ad");
					forward.setRedirect(true);
				}
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}
