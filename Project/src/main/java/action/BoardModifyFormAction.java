package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardDetailService;
import vo.ActionForward;
import vo.BoardBean;

public class BoardModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		int board_num = Integer.parseInt(request.getParameter("notice_idx"));
//		System.out.println(board);
		
		BoardDetailService service = new BoardDetailService();
		BoardBean board = service.getBoard(board_num, false);
		request.setAttribute("board", board);
		
		forward = new ActionForward();
		forward.setPath("admin/board_modify_form.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
