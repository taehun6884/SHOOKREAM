package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ReviewListService;
import vo.ActionForward;
import vo.PageInfo;
import vo.ReviewBean;

public class MyReviewListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		int member_idx = Integer.parseInt(request.getParameter("member_idx"));
		
		ReviewListService service = new ReviewListService();
		
//		System.out.println(member_idx + "= member_idx");
		
		int listLimit = 5; 
		int pageNum = 1; // 현재 페이지 번호 설정(pageNum 파라미터 사용)
		if(request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		
		
		int startRow = (pageNum - 1) * listLimit;
				
		List<ReviewBean> reviewList = service.getMyReviewList(startRow, listLimit, member_idx);
		
		int listCount = service.getReviewListCount();
		
		int pageListLimit = 3; 
		
		int maxPage = listCount / listLimit 
						+ (listCount % listLimit == 0 ? 0 : 1); 

		
		int startPage = (pageNum - 1) / pageListLimit * pageListLimit + 1;
		
		int endPage = startPage + pageListLimit - 1;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pageInfo = new PageInfo(listCount, pageListLimit, maxPage, startPage, endPage);
		
//		System.out.println("action의 리뷰 리스트 : " + reviewList);
		request.setAttribute("reviewList", reviewList);
		request.setAttribute("pageInfo", pageInfo);
		
		forward = new ActionForward();
		forward.setPath("member/my_review_list.jsp?member_idx=" + member_idx);
		forward.setRedirect(false);
		
		return forward;
	}

}
