package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.MainBestListAction;
import action.MainBrandCGListAction;
import action.MainKeywordListAction;
import action.MainListAction;
import action.MainNewListAction;
import action.MainSaleListAction;
import action.ProductInsertAction;
import action.ProductListAction;
import vo.ActionForward;

@WebServlet("*.MAIN") // 메인 페이지 컨트롤러
public class MainController extends HttpServlet{
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("MainController()");
		
		String command = request.getServletPath();
		System.out.println("현재 주소 :"+command);
		
		ActionForward forward = null;
		Action action = null;
		
		
		if(command.equals("/main.MAIN")) {// Main 폼화면
			action = new MainListAction();
			forward = action.execute(request, response); // Main -> Best 카테고리
		}else if(command.equals("/Best.MAIN")) {
			action = new MainBestListAction();
			forward = action.execute(request, response);
		}else if(command.equals("/Sale.MAIN")) {  // Main -> Sale 카테고리
			action = new MainSaleListAction();
			forward = action.execute(request, response);
		}else if(command.equals("/New.MAIN")) { // Main -> New 카테고리
			action = new MainNewListAction();
			forward = action.execute(request, response);
		}else if(command.equals("/BrandCG.MAIN")) { // Main -> 브랜드별 카테고리
			action = new MainBrandCGListAction();
			forward = action.execute(request, response);
		}else if(command.equals("/Keyword.MAIN")) { // Main -> 검색
			action = new MainKeywordListAction();
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

