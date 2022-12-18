package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;

import action.MemberJoinProAction;
import action.MemberDeleteMemberProAction;
import action.MemberLoginMemberProAction;
import action.MemberLogoutProAction;
import action.MemberModifyFormAction;
import action.MemberModifyProAction;

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
		
		if(command.equals("/MemberJoinForm.me")) {//회원가입 폼화면
			forward = new ActionForward();
			forward.setPath("member/MemberJoinForm.jsp");
			forward.setRedirect(false);
		}else if(command.equals("/MemberJoinPro.me")) {//회원가입 pro
			action = new MemberJoinProAction();
			forward = action.execute(request, response);
		}else if(command.equals("/LoginMember.me")) { //로그인 폼
			forward = new ActionForward();
			forward.setPath("member/MemberLoginForm.jsp");
			forward.setRedirect(false);
		}else if(command.equals("/LoginMemberPro.me")) {//로그인 pro
			action = new MemberLoginMemberProAction();
			forward = action.execute(request, response);
		}else if(command.equals("/MemberLogout.me")) { //로그아웃
			action = new MemberLogoutProAction();
			forward = action.execute(request, response);
		}else if(command.equals("/MemberList.me")) { //회원 목록
		
		}else if(command.equals("/MemberModifyForm.me")) { //회원 정보 수정 창
			action = new MemberModifyFormAction();
			forward = action.execute(request, response);
		}else if(command.equals("/MemberModifyPro.me")) { //회원 정보 수정 pro
			action = new MemberModifyProAction();
			forward = action.execute(request, response);
		}else if(command.equals("/MemberDeleteForm.me")) { //회원 삭제 창
			forward = new ActionForward();
			forward.setPath("member/MemberDeleteForm.jsp");
			forward.setRedirect(false);
		}else if(command.equals("/MemberDeletePro.me")) { //회원 삭제 pro
			action = new MemberDeleteMemberProAction(); 
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

