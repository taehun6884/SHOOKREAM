package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.ProductModifyService;
import vo.ActionForward;
import vo.ProductBean;
import vo.imageBean;

public class ProductModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		int idx = Integer.parseInt(request.getParameter("product_idx"));
		//product_idx에 맞는 상품 목록 뿌리기(수정 전)
		ProductModifyService service = new ProductModifyService();
		ProductBean product = service.getProduct(idx);
		request.setAttribute("product", product);
		//추가로, 이미지 DB에서 가져오기
		imageBean image = new imageBean();
		image = service.getImages(idx);
		request.setAttribute("image", image);
		
		forward = new ActionForward();
		forward.setPath("admin/ProductModifyForm.jsp");
		forward.setRedirect(false);
		
		
		return forward;
	}

}
