package action;

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
		ProductBean vo = product.getProduct(idx);
		request.setAttribute("product", vo);
		forward = new ActionForward();
		forward.setPath("admin/ProductModifyForm.jsp");
		forward.setRedirect(false);
		
		
		return forward;
	}

}
