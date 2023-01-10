package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ProductListService;
import vo.ActionForward;
import vo.ProductBean;

public class MainListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		ProductListService service = new ProductListService();
		
		int listLimit = 16; // 한 페이지에서 표시할 게시물 목록을 10개로 제한
		int pageNum = 1; // 현재 페이지 번호 설정(pageNum 파라미터 사용)
		
		if(request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}

		int startRow = (pageNum - 1) * listLimit; // 조회 시작 행번호 계산
		
		// 베스트 상품 가져오기
		List<ProductBean> productBestList = service.getProductBestList(startRow, listLimit);
		request.setAttribute("productBestList", productBestList);
		
		// 최근 등록 상품 가져오기
		List<ProductBean> productNewList = service.getProdoctNewList(startRow, listLimit);
		request.setAttribute("productNewList", productNewList);
		
		forward = new ActionForward();
		forward.setPath("main.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
