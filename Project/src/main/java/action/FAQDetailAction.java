package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardDetailService;
import vo.ActionForward;
import vo.BoardBean;

public class FAQDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		int notice_idx = Integer.parseInt(request.getParameter("notice_idx"));
//		System.out.println("notice_idx : " + notice_indx);
		
		BoardDetailService service = new BoardDetailService();
		BoardBean board = service.getBoard(notice_idx, true);

		request.setAttribute("board", board);
		
		
		forward = new ActionForward();
		forward.setPath("board/FAQ_detail.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
