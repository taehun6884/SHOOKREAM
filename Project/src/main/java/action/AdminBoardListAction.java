package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardListService;
import vo.ActionForward;
import vo.BoardBean;
import vo.PageInfo;

public class AdminBoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;

		int listLimit = 10; 
		int pageNum = 1; // 현재 페이지 번호 설정(pageNum 파라미터 사용)
		if(request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		
//		System.out.println("리스트에서 확인하는 pageNum " + pageNum);
		int startRow = (pageNum - 1) * listLimit;

		String keyword = request.getParameter("keyword");

		if(keyword == null) {
			keyword = "";
		}
		
		String type = "Notice";
		
		BoardListService service = new BoardListService();
		
		List<BoardBean> boardList = service.getBoardList(keyword, startRow, listLimit, type);

		int listCount = service.getBoardListCount(keyword,type);
		
		int pageListLimit = 3; 
		
		int maxPage = listCount / listLimit 
						+ (listCount % listLimit == 0 ? 0 : 1); 

		
		int startPage = (pageNum - 1) / pageListLimit * pageListLimit + 1;
		
		int endPage = startPage + pageListLimit - 1;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pageInfo = new PageInfo(listCount, pageListLimit, maxPage, startPage, endPage);

		request.setAttribute("boardList", boardList);
		request.setAttribute("pageInfo", pageInfo);

		forward = new ActionForward();
		forward.setPath("admin/admin_notice_manage.jsp");
		forward.setRedirect(false);		

		
		
		
		return forward;
	}

}
