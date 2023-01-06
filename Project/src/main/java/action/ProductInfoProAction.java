package action;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberIdCheckService;
import svc.ProductInfoProService;
import svc.ReviewListService;
import vo.ActionForward;
import vo.BoardBean;
import vo.PageInfo;
import vo.ProductBean;
import vo.ReviewBean;
import vo.WishBean;
import vo.imageBean;

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
//		System.out.println(product);
		imageBean image = service.getImage(product_idx);
		
		String cp = request.getContextPath();
		System.out.println("product.getProduct_name() : " + product.getProduct_name());
		Cookie c1 = new Cookie("product_name",product.getProduct_name());
		c1.setMaxAge(600);
		response.addCookie(c1);
		//상품별 카테고리 가져오기
		List<String>categorylist =  service.getCategoryList(product.getProduct_name());
		List<String> colorlist = service.ProductColorCategory(product.getProduct_name());
		
		HttpSession session = request.getSession();
//		int member_idx = Integer.parseInt(request.getParameter("member_idx"));
		
		String sId = (String)session.getAttribute("sId");
		// session 에 저장된 id로 member_idx 조회
		MemberIdCheckService service2 = new MemberIdCheckService();
		int member_idx = service2.getMemberIdx(sId);
		System.out.println("member_idx : " + member_idx);
		
		// 멤버 wish 저장하기
		WishBean wish = service.getWishInfo(product_idx, member_idx);
		
		if(wish != null) {
			request.setAttribute("wish", wish);
		} else {
			request.setAttribute("wish", null);
		}
		
		request.setAttribute("product", product);
		request.setAttribute("image", image);
		request.setAttribute("categorylist", categorylist);
		request.setAttribute("colorlist", colorlist);
		request.setAttribute("c1", c1);
		request.setAttribute("cp", cp);
		// 상품 리뷰 출력 시작
		System.out.println(c1);
		System.out.println(cp);
		ReviewListService service3 = new ReviewListService();
		// 리뷰 페이징 처리
		int listLimit = 5; 
		int pageNum = 1; // 현재 페이지 번호 설정(pageNum 파라미터 사용)
		if(request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		
		
		int startRow = (pageNum - 1) * listLimit;
				
		List<ReviewBean> reviewList = service3.getReviewList(startRow, listLimit,product_idx);
		
		int listCount = service3.getReviewListCount();
		
		int pageListLimit = 3; 
		
		int maxPage = listCount / listLimit 
						+ (listCount % listLimit == 0 ? 0 : 1); 

		
		int startPage = (pageNum - 1) / pageListLimit * pageListLimit + 1;
		
		int endPage = startPage + pageListLimit - 1;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pageInfo = new PageInfo(listCount, pageListLimit, maxPage, startPage, endPage);

		request.setAttribute("reviewList", reviewList);
		request.setAttribute("pageInfo", pageInfo);
		
		
		forward = new ActionForward();
		forward.setPath("product/Product_info.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
