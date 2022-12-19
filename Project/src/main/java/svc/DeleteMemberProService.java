package svc;

import java.sql.Connection;

import dao.MemberDAO;
import db.JdbcUtil;

public class DeleteMemberProService {
	
	public boolean isDeleteSuccess(String id,String pass) {
		boolean isDeleteUser = false;
		
		Connection con = JdbcUtil.getConnection();
		
		MemberDAO dao = MemberDAO.getInstance();
		
		dao.setConnection(con);
		
		isDeleteUser = dao.isDeleteUser(id,pass);
		if(isDeleteUser ==true) {
			JdbcUtil.commit(con);
		}else {
			JdbcUtil.rollback(con);
		}
		JdbcUtil.close(con);
		return isDeleteUser;
		
	}
}

