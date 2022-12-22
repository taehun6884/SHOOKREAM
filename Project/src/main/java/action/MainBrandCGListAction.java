package action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ProductListService;
import vo.ActionForward;
import vo.ProductBean;

public class MainBrandCGListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String cg = request.getParameter("cg");
		System.out.println("카테고리 : " + cg);
		
		ProductListService service = new ProductListService();
		List<ProductBean> productCGList = service.getProdoctCGList(cg);
		request.setAttribute("productCGList", productCGList);
		
		forward = new ActionForward();
		forward.setPath("main_cg.jsp?cg="+cg);
		forward.setRedirect(false);
		
		return forward;
	}

}
