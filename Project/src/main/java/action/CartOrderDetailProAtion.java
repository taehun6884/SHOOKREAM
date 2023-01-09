package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.CartListProService;
import vo.ActionForward;
import vo.cartBean;

public class CartOrderDetailProAtion implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward= null;
		
		//주문페이지로 넘기는 카트 리스트 뽑기
		CartListProService service = new CartListProService();
		cartBean cart = service.getCartlist();
		
		
		
		forward = new ActionForward();
		forward.setPath("product/order_form.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
