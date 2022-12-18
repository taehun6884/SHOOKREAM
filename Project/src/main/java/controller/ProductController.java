package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;

import vo.ActionForward;

@WebServlet("*.po") // 상품 컨트롤러
public class ProductController extends HttpServlet{
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("pomberController()");
		
		String command = request.getServletPath();
		System.out.println("현재 주소 :"+command);
		
		ActionForward forward = null;
		Action action = null;
		
		
		if(command.equals("/ProductInsertForm.po")) {//Product 폼화면

		}else if(command.equals("/ProductInsertPro.po")) {//Product Pro

		}else if(command.equals("/ProductList.po")) { //Product 목록
		
		}else if(command.equals("/ProductInfoForm.po")) { //Product 상세 정보
		
		}else if(command.equals("/ProductInfoPro.po")) { //Product 상세 정보
			
		}else if(command.equals("/ProductModifyForm.po")) { //Product 정보 수정 창
		
		}else if(command.equals("/ProductModifyPro.po")) { //Product 정보 수정 pro
		
		}else if(command.equals("/ProductDeleteForm.po")) { //Product 삭제 창
		
		}else if(command.equals("/ProductDeletePro.po")) { //Product 삭제 pro
			
		}else if(command.equals("/ProductOrderForm.po")) { //Product 삭제 pro
			
		}else if(command.equals("/ProductOrderPro.po")) { //Product 삭제 pro
			
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

