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
		String keyword = request.getParameter("keyword");
		
//		System.out.println("검색어" + request.getParameter("keyword"));
//		System.out.println("카테고리 : " + cg);
		
		if(cg != null) { // 브랜드별 카테고리 이동
			
			ProductListService service = new ProductListService();
			List<ProductBean> productList = service.getProdoctCGList(cg);
			request.setAttribute("productList", productList);
			
			forward = new ActionForward();
			forward.setPath("main_cg.jsp?cg="+cg);
			forward.setRedirect(false);
			
		} else if (keyword != null) { // 메인 검색 결과
			
			ProductListService service = new ProductListService();
			List<ProductBean> productList = service.getProdoctSearchList(keyword);
			request.setAttribute("productList", productList);
			
			forward = new ActionForward();
			forward.setPath("main_cg.jsp?keyword="+keyword);
			forward.setRedirect(false);
		}
		
		
		return forward;
	}

}
