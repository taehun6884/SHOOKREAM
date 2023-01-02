package svc;

import java.sql.Connection;

import dao.MemberDAO;
import db.JdbcUtil;
import vo.MemberBean;

public class isRightUserService {

	public boolean isLoginUser(MemberBean member) {
		boolean isRightUser = false;
		
		Connection con = JdbcUtil.getConnection();
		MemberDAO dao = MemberDAO.getInstance();
		dao.setConnection(con);
		
		isRightUser = dao.findPass(member);
		JdbcUtil.close(con);
		
		return isRightUser;
	}

	public boolean updatePass(MemberBean member,StringBuilder imsiPw) {
		boolean result = false;
		
		Connection con = JdbcUtil.getConnection();
		MemberDAO dao = MemberDAO.getInstance();
		dao.setConnection(con);
		
		result = dao.updatePass(member, imsiPw);
		
		if(result) {
			JdbcUtil.commit(con);
		}else {
			JdbcUtil.rollback(con);
			result = false;
			
		}
		JdbcUtil.close(con);
		
		return result;
	}

}
