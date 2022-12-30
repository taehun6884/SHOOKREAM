package svc;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.mysql.cj.util.Util;

import dao.MemberDAO;
import db.JdbcUtil;
import vo.MemberBean;
import vo.ResultData;
import vo.WishBean;

public class LoginMemberService {

	public boolean isLoginUser(String id, String pass) {
		boolean isLoginUser = false;
		
		Connection con = JdbcUtil.getConnection();
		
		MemberDAO dao = MemberDAO.getInstance();
		
		dao.setConnection(con);
		
		isLoginUser = dao.isLoginUser(id,pass);
		
		JdbcUtil.close(con);
		
		return isLoginUser;
	}

	public static ResultData sendTempLoginPwToEmail(MemberBean member) {
		// 메일의 제목과 내용 생성
		String siteName = "SHOOKREAM";
		String siteLoginUrl = App.getLoginUrl();
		String title = "[" + siteName + "]" + member.getMember_name() + " 님, 임시 패스워드가 발급되었습니다.";
		String tempPassword = Util.getTempPassword(6);
		String body = "<h1>임시 패스워드 : " + tempPassword + "</h1>";
		body += "<a href=\"" + siteLoginUrl + "\" target=\"_blank\">로그인하러 가기</a>";

		// 메일 발송(결과를 알 수 없음)
//		 emailService.send(actor.getEmail(), title, body);

		// 메일 발송 결과를 int값으로 받음(개선)
		int sendRs = emailService.send(member.getMember_email(), title, body);

		Map<String, Object> rs = new HashMap()<>();

		/*  //ResultData객체 도입으로 삭제
		 * if (sendRs == 1) { // 발송 성공인 경우 rs.put("resultCode", "S-1");
		 * rs.put("resultMsg", "회원님의 임시 비밀번호가 \"" + actor.getEmail() + "\"로 발송되었습니다.");
		 * 
		 * // 회원의 패스워드를 방금 생성한 임시 패스워드로 변경 setTempPassword(actor, tempPassword); } else
		 * { // 발송 실패인 경우 rs.put("resultCode", "F-1"); rs.put("resultMsg",
		 * "메일 발송에 실패했습니다.");
		 * }
		 * return rs;
		 */
		
		if(sendRs != 1) {
			return new ResultData("F-1", "메일 발송에 실패했습니다.");
		}
		
		setTempPassword(member, tempPassword);
		String resultMsg = "회원님의 임시 비밀번호가 \"" + member.getMember_email() + "\"로 발송되었습니다.";
		return new ResultData("S-1", resultMsg, "email", member.getMember_email());
		
	}

	private static void setTempPassword(MemberBean member, String tempPassword) {
		Map<String, Object> modifyArg = new HashMap<>();
		modifyArg.put("id", member.getMember_id());
		modifyArg.put("loginPw", Util.sha256(tempPassword));

		modify(modifyArg);
	}

	private static void modify(Map<String, Object> modifyArg) {
		MemberDAO.modify(modifyArg);

	}

	public static MemberBean getMemberByLoginId(String loginId) {
		return null;
		
	}

	public static MemberBean getMemberByNameAndEmail(String name, String email) {
		return null;
	}
}
