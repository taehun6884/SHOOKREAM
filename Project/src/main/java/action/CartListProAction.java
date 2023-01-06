package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.CartListProService;
import vo.ActionForward;
import vo.PageInfo;
import vo.ProductBean;
import vo.cartBean;

public class CartListProAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		List<cartBean> cartList = null;
		
		int member_idx = Integer.parseInt(request.getParameter("member_idx"));
		// 페이징 처리를 위한 변수 선언
		int listLimit = 10; // 한 페이지에서 표시할 게시물 목록을 10개로 제한
		int pageNum = 1; // 현재 페이지 번호 설정(pageNum 파라미터 사용)
		
		if(request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}

		int startRow = (pageNum - 1) * listLimit; // 조회 시작 행번호 계산
		//	System.out.println("startRow = " + startRow);
		// ---------------------------------------------------------
		// 파라미터로 전달받은 검색어(keyword) 가져와서 변수에 저장

			CartListProService service = new CartListProService();
			List<cartBean> cartlist = service.getCartlist(member_idx,startRow,listLimit);
			
			//합계 가격
			int total = service.CartTotalPrice(member_idx);
			
//			int total = service.CartTotalPrice(member_idx, product_idx);
			// 페이징 처리
			// 한 페이지에서 표시할 페이지 목록(번호) 갯수 계산
			// 1. BoardListService - selectBoardListCount() 메서드를 호출하여 전체 게시물 수 조회(페이지 목록 계산에 사용)
			// => 파라미터 : 검색어   리턴타입 : int(listCount)
			int listCount = service.getCartListCount(member_idx);
			//	System.out.println("총 게시물 수 : " + listCount);
			
			// 2. 한 페이지에서 표시할 페이지 목록 갯수 설정
			int pageListLimit = 10; // 한 페이지에서 표시할 페이지 목록을 3개로 제한
			
			// 3. 전체 페이지 목록 수 계산
			int maxPage = listCount / listLimit 
							+ (listCount % listLimit == 0 ? 0 : 1);
			
			// 4. 시작 페이지 번호 계산
			int startPage = (pageNum - 1) / pageListLimit * pageListLimit + 1;
			
			// 5. 끝 페이지 번호 계산
			int endPage = startPage + pageListLimit - 1;
			
			// 6. 만약, 끝 페이지 번호(endPage)가 전체(최대) 페이지 번호(maxPage) 보다
			//    클 경우, 끝 페이지 번호를 최대 페이지 번호로 교체
			if(endPage > maxPage) {
				endPage = maxPage;
			}
				
				// PageInfo 객체 생성 후 페이징 처리 정보 저장
		PageInfo pageInfo = new PageInfo(listCount, pageListLimit, maxPage, startPage, endPage);
		request.setAttribute("pageInfo", pageInfo);
		System.out.println(pageInfo);
		request.setAttribute("cartlist", cartlist);
		System.out.println(cartlist);
		request.setAttribute("total", total);
		forward = new ActionForward();
		forward.setPath("product/Product_cart.jsp");
		forward.setRedirect(false);
		
		return forward;
	}
 
	
}
