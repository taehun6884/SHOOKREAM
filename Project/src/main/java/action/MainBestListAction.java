package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ProductListService;
import vo.ActionForward;
import vo.ProductBean;

public class MainBestListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		ProductListService service = new ProductListService();
		
		// 베스트 상품 가져오기
		List<ProductBean> productBestList = service.getProductBestList();
		request.setAttribute("productBestList", productBestList);
		
		// 찜하기(좋아요) 누른 회원, 상품별 찜한 횟수 출력
//		List<ProductBean> productWish = service.getProductWish();
		
		forward = new ActionForward();
		forward.setPath("main_best.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
