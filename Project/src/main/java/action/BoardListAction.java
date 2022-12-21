package action;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardListService;
import vo.ActionForward;
import vo.BoardBean;
import vo.PageInfo;

public class BoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
//		System.out.println("BoardListAction");
		ActionForward forward = null;
		// BoardListService 객체를 통해 게시물 목록 조회 후
		// 조회 결과(list객체)를 request객체를 통해 qna_board_list.jsp 페이지 전달
		
		// 페이징 처리를 위한 변수 선언
		int listLimit = 10; 
		int pageNum = 1; // 현재 페이지 번호 설정(pageNum 파라미터 사용)
		if(request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}

		int startRow = (pageNum - 1) * listLimit;

		String keyword = request.getParameter("keyword");

		// 만약, 전달받은 검색어가 null 이면 널스트링으로 변경(일반 목록일 경우 전체 검색 수행)
		if(keyword == null) {
			keyword = "";
		}
		
		BoardListService service = new BoardListService();
		
		List<BoardBean> boardList = service.getBoardList(keyword, startRow, listLimit);

		int listCount = service.getBoardListCount(keyword);
		
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
		forward.setPath("board/board_list.jsp");
		forward.setRedirect(false);
		
		// action클래스들은 redirect/ dispatcher 인지 모르므로 actionforward객체 필요.
		return forward;
	}

}
