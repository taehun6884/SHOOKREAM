package action;

import java.sql.Connection;

import dao.MemberDAO;
import db.JdbcUtil;
import vo.MemberBean;

public class FindMemberPwService {

	public String findPw(MemberBean member) {
		String email="";
		
		Connection con = JdbcUtil.getConnection();
		MemberDAO dao = MemberDAO.getInstance();
		dao.setConnection(con);
		
		email = dao.findPass(member);
		
		if(email != null) {
			
		}
		
		
	}

}
