package svc;

import java.sql.Connection;

import dao.MemberDAO;
import db.JdbcUtil;
import vo.AuthBean;
import vo.MemberBean;

public class AuthEmailProService {
	
	public boolean selectMember(String authCode, String id) { // 인증코드 비교하여 일치 시 회원가입 완료
		boolean selectCode = false;
		
		Connection con = JdbcUtil.getConnection();
		MemberDAO dao = MemberDAO.getInstance();
		dao.setConnection(con);
		
		String code = dao.selectMember(authCode, id);
		System.out.println("authCode:"+ authCode);
		System.out.println("code:"+ code);
		
		if(authCode.equals(code)) {
			JdbcUtil.commit(con);
			selectCode = true;
		} else {
			JdbcUtil.close(con);
			
		}
//		System.out.println(selectCode);
		
		return selectCode;
	}

}