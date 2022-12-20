package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ProductInfoProService;
import vo.ActionForward;
import vo.ProductBean;

public class ProductInfoProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("ProductInfoProAction");
		
		ActionForward forward = null;
		
		// 상품 상세 정보 조회에 필요한 상품 번호 가져오기
		int product_idx = Integer.parseInt(request.getParameter("product_idx"));
		System.out.println("product_idx = " + product_idx);
		System.out.println(request.getParameter("product_idx"));
		
		ProductInfoProService service = new ProductInfoProService();
		ProductBean product = service.getProduct(product_idx);
		System.out.println(product);
		
		request.setAttribute("product", product);
		
		forward = new ActionForward();
		forward.setPath("product/Product_info.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
