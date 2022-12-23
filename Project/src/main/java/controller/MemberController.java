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

import action.MemberJoinProAction;
import action.MemberListAction;
import action.MemberDeleteMemberProAction;
import action.MemberIdCheckProAction;
import action.MemberLoginMemberProAction;
import action.MemberLogoutProAction;
import action.MemberModifyFormAction;
import action.MemberModifyProAction;
import svc.memberService;
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

	
	// 아이디 찾기
	public String doFindLoginId(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("member_name");
		String email = request.getParameter("member_email");

		MemberBean member = memberService.getMemberByNameAndEmail(name, email);

		// 해당 이름과 이메일주소를 가진 회원이 존재하는지 확인
		if (member == null) {
			request.setAttribute("alertMsg", "일치하는 회원이 존재하지 않습니다.");
			request.setAttribute("historyBack", true); // historyBack: 뒤로 돌아가기
			return "common/redirect";
		}

		// 로그인아이디 알림창 보여주고 로그인화면으로 이동
		request.setAttribute("alertMsg", name + "회원님의 아이디는 \"" + member.getLoginId() + "\"입니다.");
		request.setAttribute("replaceUrl", "../member/doLoginForm");
		return "common/redirect";
	}
	// 비밀번호 찾기 폼
		public String doFindLoginPwForm(HttpServletRequest request, HttpServletResponse response) {
			return "usr/member/doFindLoginPwForm";
		}

		// 비밀번호 찾기
		public String doFindLoginPw(HttpServletRequest request, HttpServletResponse response) {
			String loginId = request.getParameter("loginId");
			String email = request.getParameter("email");

			Member member = memberService.getMemberByLoginId(loginId);

			// 해당 loginId가 등록된 id인지 확인
			if (member == null) {
				request.setAttribute("alertMsg", "일치하는 회원이 존재하지 않습니다.");
				request.setAttribute("historyBack", true); // historyBack: 뒤로 돌아가기
				return "common/redirect";
			}

			// 해당 email이 일치하는지 확인
			if (member.getEmail().equals(email) == false) {
				request.setAttribute("alertMsg", "이메일주소가 일치하지 않습니다.");
				request.setAttribute("historyBack", true); // historyBack: 뒤로 돌아가기
				return "common/redirect";
			}

			// 임시 비밀번호 생성 후 회원 email로 발송
			// memberService.sendTempLoginPwToEmail(member);

			// 임시 비밀번호 생성 후 회원 email로 발송(개선)
			/*
			 * //ResultData 객체 도입으로 삭제 Map<String, Object> sendTempLoginPwToEmailRs =
			 * memberService.sendTempLoginPwToEmail(member);
			 * 
			 * String resultCode = (String) sendTempLoginPwToEmailRs.get("resultCode");
			 * String resultMsg = (String) sendTempLoginPwToEmailRs.get("resultMsg");
			 */

			// 임시 비밀번호 생성 후 회원 email로 발송(개선)
			ResultData sendTempLoginPwToEmailRs = memberService.sendTempLoginPwToEmail(member);

			/// 만약 메일 발송 실패인 경우
			// if(resultCode.contains("F")) {
			if (sendTempLoginPwToEmailRs.isFail()) {
				request.setAttribute("alertMsg", sendTempLoginPwToEmailRs.getMsg());
				request.setAttribute("historyBack", true);
				return "common/redirect";
			}

			// 임시패스워드 발급 알림창 보여주고 메인화면으로 이동
			request.setAttribute("alertMsg", sendTempLoginPwToEmailRs.getMsg());
			request.setAttribute("replaceUrl", "../home/main");
			return "common/redirect";
		}
}

