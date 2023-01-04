package action;

import java.awt.im.InputMethodRequests;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ModifyMemberService;
import svc.ProductInfoProService;
import vo.ActionForward;
import vo.MemberBean;
import vo.ProductBean;
import vo.imageBean;

public class OrderDetailProAtion implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		int product_idx = Integer.parseInt(request.getParameter("product_idx"));
		int member_idx = Integer.parseInt(request.getParameter("member_idx"));
		
		ProductInfoProService service = new ProductInfoProService();
		ProductBean vo = service.getProduct(product_idx);
		imageBean image = service.getImage(product_idx);
		ModifyMemberService service2 = new ModifyMemberService();
		MemberBean vo2 = service2.getMemberInfo(member_idx);
		
		System.out.println(vo);
		request.setAttribute("product", vo);
		request.setAttribute("member", vo2);
		request.setAttribute("image", image);
		
		
		
		forward = new ActionForward();
		forward.setPath("product/order_form.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
