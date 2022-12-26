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
		List<ProductBean> productBestList = service.getProdoctBestList();
		request.setAttribute("productBestList", productBestList);
		
		forward = new ActionForward();
		forward.setPath("main_best.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
