package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardDeleteProService;
import vo.ActionForward;

public class BoardDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		int notice_idx = Integer.parseInt(request.getParameter("notice_idx"));
		String pageNum = request.getParameter("pageNum");
		BoardDeleteProService service = new BoardDeleteProService();
		System.out.println(notice_idx + "과 "+ pageNum);
		forward = service.boardDelete(notice_idx, pageNum);
		
		return forward;
	}

}
