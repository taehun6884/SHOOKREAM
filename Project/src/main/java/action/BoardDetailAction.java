package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardDetailService;
import vo.ActionForward;
import vo.BoardBean;

public class BoardDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		int notice_idx = Integer.parseInt(request.getParameter("notice_idx"));
//		System.out.println("notice_idx : " + notice_indx);
		
		BoardDetailService service = new BoardDetailService();
		BoardBean board = service.getBoard(notice_idx, true);
//		System.out.println(board);

		
		request.setAttribute("board", board);

		if(request.getParameter("notice_type") == "Notice") {
			forward = new ActionForward();
			forward.setPath("board/board_detail.jsp");
			forward.setRedirect(false);			
		} else {
			forward = new ActionForward();
			forward.setPath("board/FAQ_detail.jsp");
			forward.setRedirect(false);
		}
		
		return forward;
	}

}
