package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ProductListService;
import vo.ActionForward;
import vo.ProductBean;

public class MainNewListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		ProductListService service = new ProductListService();
		
		// 최근 등록 상품 가져오기
		List<ProductBean> productNewList = service.getProdoctNewList();
		request.setAttribute("productNewList", productNewList);
		
		forward = new ActionForward();
		forward.setPath("main_new.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
