package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.BoardListAction;
import action.BoardWriteProAction;
import vo.ActionForward;



@WebServlet("*.bo") // xxx.bo 로 끝나는 모든 주소 매핑
public class BoardFrontController extends HttpServlet {
	// GET or POST 방식 요청을 공통으로 처리할 doProcess() 메서드 정의
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("BoardFrontController");
		
		// POST 방식 요청에 대한 한글 인코딩 처리
		request.setCharacterEncoding("UTF-8");
		
		// 서블릿 주소 추출
		String command = request.getServletPath();
		System.out.println("command : " + command);
		
		// 공통으로 사용할 변수 선언
		Action action = null; // XXXAction 클래스를 공통으로 관리할 Action 인터페이스 타입 변수
		ActionForward forward = null; // 포워딩 정보를 저장할 ActionForward 타입 변수
		

		if(command.equals("/BoardWriteForm.bo")) {//공지 폼화면
			forward = new ActionForward();
			forward.setPath("admin/boardWriteForm.jsp");
			forward.setRedirect(false); 
		} else if(command.equals("/BoardWritePro.bo")) { //공지쓰기 pro
			action = new BoardWriteProAction();
			forward = action.execute(request, response);		
		}else if(command.equals("/BoardList.bo")) { //공지 목록
			action = new BoardListAction();
			forward = action.execute(request, response);
		}else if(command.equals("/BoardInfo.bo")) { //공지 상세 정보
		
		}else if(command.equals("/BoardModifyForm.bo")) { //공지 정보 수정 창
		
		}else if(command.equals("/BoardModifyPro.bo")) { //공지 정보 수정 pro
		
		}else if(command.equals("/BoardDeleteForm.bo")) { //공지 삭제 창
		
		}else if(command.equals("/BoardDeletePro.bo")) { //공지 삭제 pro
			

	}
		
		
		//-----------------------------------------------------
		//문의사항
		if(command.equals("/AskInsertForm.me")) {//문의사항 폼화면

		}else if(command.equals("/AskJoinPro.me")) {//문의사항 pro
		
		}else if(command.equals("/AskList.me")) { //문의사항 목록
		
		}else if(command.equals("/AskInfo.me")) { //문의사항 정보
		
		}else if(command.equals("/AskModifyForm.me")) { //문의사항 정보 수정 창
		
		}else if(command.equals("/AskModifyPro.me")) { //문의사항 정보 수정 pro
			
		}else if(command.equals("/AskDeleteForm.me")) { //문의사항 삭제 창
			
		}else if(command.equals("/AskDeletePro.me")) { //문의사항 삭제 pro
				
		}
		
		//FAQ
		if(command.equals("/FAQInsertForm.me")) {//FAQ 폼화면

		}else if(command.equals("/FAQJoinPro.me")) {//FAQ pro
		
		}else if(command.equals("/FAQList.me")) { //FAQ 목록
		
		}else if(command.equals("/FAQInfo.me")) { //FAQ 상세 정보
		
		}else if(command.equals("/FAQModifyForm.me")) { //FAQ 정보 수정 창
		
		}else if(command.equals("/FAQModifyPro.me")) { //FAQ 정보 수정 pro
		
		}else if(command.equals("/FAQDeleteForm.me")) { //FAQ 삭제 창
		
		}else if(command.equals("/FAQDeletePro.me")) { //FAQ 삭제 pro
			
		}
		// ----------------------------------------------------------------------
		// ActionForward 객체 내용에 따라 각각 다른 방식의 포워딩 작업 수행(공통)
		// 1. ActionForward 객체가 null 이 아닐 경우 판별
		if(forward != null) {
			// 2. ActionForward 객체에 저장된 포워딩 방식 판별
			if(forward.isRedirect()) { // Redirect 방식
				// Redirect 방식의 포워딩 작업 수행
				// => 포워딩 경로는 ActionForward 객체의 getPath() 메서드 활용
				response.sendRedirect(forward.getPath());
			} else { // Dispatch 방식
				// Dispatch 방식의 포워딩 작업 수행
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
		
//		System.out.println("doProcess() 메서드 끝");
	} // doProcess() 메서드 끝(응답데이터 전송)
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
