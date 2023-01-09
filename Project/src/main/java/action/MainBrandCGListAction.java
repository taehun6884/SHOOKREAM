package action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ProductListService;
import vo.ActionForward;
import vo.PageInfo;
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
		

//		System.out.println("검색어" + request.getParameter("keyword"));
//		System.out.println("카테고리 : " + cg);

		// 페이징 처리를 위한 변수 선언
		int listLimit = 16; // 한 페이지에서 표시할 게시물 목록을 10개로 제한
		int pageNum = 1; // 현재 페이지 번호 설정(pageNum 파라미터 사용)

		if (request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}

		int startRow = (pageNum - 1) * listLimit; // 조회 시작 행번호 계산

		if (cg != null) { // 브랜드별 카테고리 이동

			ProductListService service = new ProductListService();
			List<ProductBean> productList = service.getProdoctCGList(cg, startRow, listLimit);
			request.setAttribute("productList", productList);
			int listCount = service.getProductCgListCount(cg);
			
			int pageListLimit = 10; // 한 페이지에서 표시할 페이지 목록을 3개로 제한
			
			int maxPage = listCount / listLimit 
							+ (listCount % listLimit == 0 ? 0 : 1);
			
			int startPage = (pageNum - 1) / pageListLimit * pageListLimit + 1;
			
			int endPage = startPage + pageListLimit - 1;
			
			if(endPage > maxPage) {
				endPage = maxPage;
			}
				
			PageInfo pageInfo = new PageInfo(listCount, pageListLimit, maxPage, startPage, endPage);
			request.setAttribute("pageInfo", pageInfo);

			forward = new ActionForward();
			forward.setPath("main_cg.jsp?cg=" + cg);
			forward.setRedirect(false);

		}
	
		
		
		return forward;
	}

}
