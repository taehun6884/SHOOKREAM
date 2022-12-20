package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ProductListService;
import vo.ActionForward;
import vo.ProductBean;

public class ProductListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
	
		ProductListService service = new ProductListService();
		List<ProductBean> productList = service.getProdoctList();
		
		request.setAttribute("productList", productList);
		
		forward = new ActionForward();
		forward.setPath("product/ProductList.jsp");
		forward.setRedirect(false); //
		
		return forward;
	}

}
