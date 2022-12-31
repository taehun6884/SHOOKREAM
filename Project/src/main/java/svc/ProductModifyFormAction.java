package svc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.ProductModifyService;
import vo.ActionForward;
import vo.ProductBean;

public class ProductModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		int idx = Integer.parseInt(request.getParameter("product_idx"));
		
		ProductModifyService product = new ProductModifyService();
		ProductBean bean = product.getProduct(idx);
		request.setAttribute("bean", bean);
		forward = new ActionForward();
		forward.setPath("product/ProductModifyForm.jsp");
		forward.setRedirect(false);
		
		
		return forward;
	}

}
