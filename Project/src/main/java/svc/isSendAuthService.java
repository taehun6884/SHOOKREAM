package svc;

import java.sql.Connection;

import dao.MemberDAO;
import db.JdbcUtil;
import vo.AuthBean;

public class isSendAuthService {

	public boolean isAuthUser(AuthBean auth) {
		boolean isRightAuth = false;
		
		
		Connection con = JdbcUtil.getConnection();
		MemberDAO dao = MemberDAO.getInstance();
		dao.setConnection(con);
		
		isRightAuth = dao.insertAuth(auth);
		
		if(isRightAuth) {
			JdbcUtil.commit(con);
			isRightAuth = true;
		}else {
			JdbcUtil.rollback(con);
			isRightAuth = false;
			
		}
		JdbcUtil.close(con);
		
		return isRightAuth;
	}

}