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
import action.BoardDetailAction;
import vo.ActionForward;

@WebServlet("*.ad")//관리자
public class AdminController extends HttpServlet{
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("AdminController()");
		
		String command = request.getServletPath();
		System.out.println("현재 주소 :" + command);
		
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

