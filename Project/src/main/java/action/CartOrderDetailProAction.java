package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.CartListProService;
import svc.CartOrderDetailProService;
import vo.ActionForward;
import vo.cartBean;

public class CartOrderDetailProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward= null;
		//member_idx 받기
		int member_idx = Integer.parseInt(request.getParameter("member_idx"));
//		System.out.println(member_idx);
		//cart_idx 받기
		String cart_idx = request.getParameter("cart_idx");
//		System.out.println(cart_idx);
		
		//주문페이지로 넘기는 카트 리스트 뽑기(카트 빈에 담기)
		CartOrderDetailProService service = new CartOrderDetailProService();
		//리스트
		List<cartBean> cartOrder = service.getCartOrderlist(member_idx, cart_idx);
		
		request.setAttribute("cartOrder", cartOrder);
		System.out.println("cartOrder : " + cartOrder);
		//총 금액
		int total = service.CartTotalPrice(member_idx);
		request.setAttribute("total", total);

		
		forward = new ActionForward();
		forward.setPath("product/order_form_cart.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
