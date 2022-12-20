package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.CartListProService;
import vo.ActionForward;
import vo.ProductBean;

public class CartListProAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		List<ProductBean> cartList = null;
		
		CartListProService service = new CartListProService();
		List<ProductBean> cartlist = service.getCartlist();
		System.out.println(cartlist);
		request.setAttribute("cartlist", cartlist);
		
		forward = new ActionForward();
		forward.setPath("product/Product_cart.jsp");
		forward.setRedirect(false);
		
		return forward;
	}
 
	
}
