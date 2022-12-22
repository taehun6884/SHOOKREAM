package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ProductListService;
import vo.ActionForward;
import vo.ProductBean;

public class MainSaleListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		ProductListService service = new ProductListService();
		
		// 할인된 상품 리스트 가져오기
		List<ProductBean> productSaleList = service.getProdoctSaleList();
		request.setAttribute("productSaleList", productSaleList);
		
		forward = new ActionForward();
		forward.setPath("main_sale.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
