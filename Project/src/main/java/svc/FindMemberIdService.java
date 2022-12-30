package svc;

import java.sql.Connection;

import dao.MemberDAO;
import db.JdbcUtil;
import vo.MemberBean;

public class FindMemberIdService {

	public String findId(MemberBean member) {
		 String id = "";
		 
		 Connection con = JdbcUtil.getConnection();
			MemberDAO dao = MemberDAO.getInstance();
			dao.setConnection(con);
			
			id = dao.findID(member);
			
			if(id != null) {
				
				JdbcUtil.commit(con);
				
			} else {
				JdbcUtil.rollback(con);
			}
			
			return id;
	}

}
