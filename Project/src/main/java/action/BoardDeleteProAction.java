package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardDeleteProService;
import vo.ActionForward;

public class BoardDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		String notice_type = request.getParameter("notice_type");
		int notice_idx = Integer.parseInt(request.getParameter("notice_idx"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
//		System.out.println("DeleteProAction - notice_idx = " + request.getParameter("notice_idx"));
		System.out.println("DeleteProAction - pageNum = " + request.getParameter("pageNum"));
		
		BoardDeleteProService service = new BoardDeleteProService();
		
		boolean isDeleteSuccess = service.removeBoard(notice_idx);
		
		try {
			if(!isDeleteSuccess) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제에 실패하였습니다.')");
				out.println("history.back()");
				out.println("</script>");
			} else {
				if(notice_type == "Notice") {
					forward = new ActionForward();
					forward.setPath("AdminNoticeManage.ad?pageNum=" + pageNum);
					forward.setRedirect(true);
				} else {
					forward = new ActionForward();
					forward.setPath("AdminFAQManage.ad?pageNum=" + pageNum);
					forward.setRedirect(true);
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return forward;
	}

}
