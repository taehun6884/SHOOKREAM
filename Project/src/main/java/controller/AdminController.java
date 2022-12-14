package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.AdminBoardListAction;
import action.AdminFAQListAction;
import action.AdminOrderListDeleteProAction;
import action.AdminOrderListProAction;
import action.BoardDetailAction;
import vo.ActionForward;

@WebServlet("*.ad")//관리자
public class AdminController extends HttpServlet{
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
//		System.out.println("AdminController()");
		
		String command = request.getServletPath();
//		System.out.println("현재 주소 :" + command);
		
		ActionForward forward = null;
		Action action = null;
		
		 if(command.equals("/Admin.ad")) { //관리자 메인보드
			 forward = new ActionForward();
			 forward.setPath("admin/admin.jsp");
			 forward.setRedirect(false);
		} else if(command.equals("/AdminProduct.ad")) { //관리자 메인보드 -> 상품관리
			 forward = new ActionForward();
			 forward.setPath("admin/admin_product.jsp");
			 forward.setRedirect(false);
		} else if(command.equals("/AdminBoard.ad")) { // 관리자 메인보드 -> 게시판 관리
			 forward = new ActionForward();
			 forward.setPath("admin/admin_board.jsp");
			 forward.setRedirect(false);
		} else if(command.equals("/AdminNoticeManage.ad")) { // 관리자 게시판-공지사항 -> 관리자용 뷰페이지
			 action = new AdminBoardListAction();
			 forward = action.execute(request, response);
		} else if(command.equals("/AdminFAQManage.ad")) { // 관리자 게시판 - FAQ -> 관리자용 뷰페이지
			 action = new AdminFAQListAction();
			 forward = action.execute(request, response);
		}else if(command.equals("/AdminProductOrderList.ad")) { //관리자 주문 상세 페이지
			action = new AdminOrderListProAction();
			forward = action.execute(request, response);
		}else if(command.equals("/AdminProductOrderListDelete.ad")) { // 주문내역 삭제
			action = new AdminOrderListDeleteProAction();
			forward = action.execute(request, response);
		}else if(command.equals("/AdminMemberCoupon.ad")) { //관리자 메인보드 -> 회원 및 쿠폰 관리
			 forward = new ActionForward();
			 forward.setPath("admin/admin_coupon.jsp");
			 forward.setRedirect(false);
		}
			
		if(forward != null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}else {
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}
		}
}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}

