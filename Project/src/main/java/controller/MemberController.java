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

@WebServlet("*.me")
public class MemberController extends HttpServlet{
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("MemberController()");
		
		String command = request.getServletPath();
		System.out.println("현재 주소 :"+command);
		
		ActionForward forward = null;
		Action action = null;
		
		if(command.equals("/MemberInsertForm.me")) {//회원가입 폼화면
			forward = new ActionForward();
			forward.setPath("member/member_join_form_sample.jsp");
			forward.setRedirect(false);
		}else if(command.equals("/MemberJoinPro.me")) {//회원가입 pro
		
		}else if(command.equals("/LoginMember.me")) { //로그인 폼
		
		}else if(command.equals("/LogintMemberPro.me")) {//로그인 pro
			
		}else if(command.equals("/MemberLogout.me")) { //로그아웃
		
		}else if(command.equals("/MemberList.me")) { //회원 목록
		
		}else if(command.equals("/MemberInfo.me")) { //회원 상세 정보
		
		}else if(command.equals("/MemberModifyForm.me")) { //회원 정보 수정 창
		
		}else if(command.equals("/MemberModifyPro.me")) { //회원 정보 수정 pro
		
		}else if(command.equals("/MemberDeleteForm.me")) { //회원 삭제 창
		
		}else if(command.equals("/MemberDeletePro.me")) { //회원 삭제 pro
			
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

