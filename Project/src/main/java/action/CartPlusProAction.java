package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.CartInsertProService;
import svc.CartPlusProService;
import vo.ActionForward;
import vo.PageInfo;

public class CartPlusProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		CartPlusProService service = new CartPlusProService();
		int cart_idx = Integer.parseInt(request.getParameter("cart_idx"));
		System.out.println("cart_idx : " + cart_idx);
		//member_idx 받아오기
		int member_idx =  service.selectMember(cart_idx);
		System.out.println("member_idx" + member_idx);
		boolean isPlusTotal = service.CartPlusTotal(cart_idx);
		
		
		//---------------------페이징 처리 시작--------------------------------
		
				int listLimit = 10; // 한 페이지에서 표시할 게시물 목록을 10개로 제한
				int pageNum = 1; // 현재 페이지 번호 설정(pageNum 파라미터 사용)
				
				if(request.getParameter("pageNum") != null) {
					pageNum = Integer.parseInt(request.getParameter("pageNum"));
				}

				int startRow = (pageNum - 1) * listLimit; // 조회 시작 행번호 계산
				
				int listCount = service.getCartListCount(member_idx);
				//	System.out.println("총 게시물 수 : " + listCount);
				
				// 2. 한 페이지에서 표시할 페이지 목록 갯수 설정
				int pageListLimit = 10; // 한 페이지에서 표시할 페이지 목록을 3개로 제한
				
				// 3. 전체 페이지 목록 수 계산
				int maxPage = listCount / listLimit 
								+ (listCount % listLimit == 0 ? 0 : 1);
				
				// 4. 시작 페이지 번호 계산
				int startPage = (pageNum - 1) / pageListLimit * pageListLimit + 1;
				
				// 5. 끝 페이지 번호 계산
				int endPage = startPage + pageListLimit - 1;
				
				// 6. 만약, 끝 페이지 번호(endPage)가 전체(최대) 페이지 번호(maxPage) 보다
				//    클 경우, 끝 페이지 번호를 최대 페이지 번호로 교체
				if(endPage > maxPage) {
					endPage = maxPage;
				}
					
					// PageInfo 객체 생성 후 페이징 처리 정보 저장
				PageInfo pageInfo = new PageInfo(listCount, pageListLimit, maxPage, startPage, endPage);
				request.setAttribute("pageInfo", pageInfo);
				System.out.println(pageInfo);
				
		if(isPlusTotal == false) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println("<script>");
				out.println("alert('장바구니 추가 실패!');");
				out.println("</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out;
			try {
				out = response.getWriter();
				out.print("true");
			} catch (IOException e) {	
				e.printStackTrace();
			}
			if(member_idx < 0) {
				System.out.println("member_idx 값을 가져오지 못함.");
			}else {
				forward = new ActionForward();
				forward.setPath("./CartList.ca?member_idx="+member_idx+"&pageNum=1");
				forward.setRedirect(true);
			}
			
		}
		
		return forward;
	}

}
