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
		List<ProductBean> vo = null;
		
		CartListProService service = new CartListProService();
		service.getCartlist();
		
		
		
		return forward;
	}
 
	
}
