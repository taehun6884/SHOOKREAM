package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.CheckEmailAddrProAction;
import action.FindMemberIdProAction;
import action.FindMemberPassProAction;
import action.MemberJoinProAction;
import action.MemberListAction;
import action.MemberDeleteMemberProAction;
import action.MemberIdCheckProAction;
import action.MemberLoginMemberProAction;
import action.MemberLogoutProAction;
import action.MemberModifyFormAction;
import action.MemberModifyProAction;
import action.ReviewWriteProAction;
import svc.LoginMemberService;
import vo.ActionForward;
import vo.MemberBean;

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
			action = new MemberListAction();
			forward = action.execute(request, response);
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
		}else if(command.equals("/MemberDeleteMemberProAction.me")) { //회원 삭제 창
			action = new MemberDeleteMemberProAction();
			forward = action.execute(request, response);
		}else if(command.equals("/MemberModifyProAction.me")) { 
			action = new MemberModifyProAction();
		}else if(command.equals("/dbCheckId.me")) { // id 중복체크
//			System.out.println(request.getParameter("member_id"));
//			action = new MemberIdCheckProAction();
//			forward = action.execute(request, response);
			action = new MemberIdCheckProAction();
			forward = action.execute(request, response);
		} else if(command.equals("/ReviewWriteForm.me")) {	// 회원 리뷰 작성 폼(구매목록에서)
			forward = new ActionForward();
			forward.setPath("product/review_write_form.jsp");
			forward.setRedirect(false);
		} else if(command.equals("/ReviewWrite.me")) {  // 리뷰작성
			action = new ReviewWriteProAction();
			forward = action.execute(request, response);
		}else if(command.equals("/FindMemberIdForm.me")) { // 아이디 찾기
			forward = new ActionForward(); 
			forward.setPath("member/findIDForm.jsp");
			forward.setRedirect(false);
		}else if(command.equals("/FindMemberIdResult.me")) { // 아이디 찾기 결과
			forward = new ActionForward(); 
			forward.setPath("member/findID_result.jsp");
			forward.setRedirect(false);
		}else if(command.equals("/FindIdFormAction.me")) { // 아이디 찾기 pro
			action = new FindMemberIdProAction();
			forward = action.execute(request, response);
		}else if(command.equals("/FindPwForm.me")) { // 비번 찾기
			forward = new ActionForward(); 
			forward.setPath("member/findPassForm.jsp");
			forward.setRedirect(false);
		}else if(command.equals("/FindPwProAction.me")) { // 비번 찾기 pro
			action = new FindMemberPassProAction();
			forward = action.execute(request, response);
		}else if(command.equals("/CheckEmailAddress.me")) { // 이메일 인증
			action = new CheckEmailAddrProAction();
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

