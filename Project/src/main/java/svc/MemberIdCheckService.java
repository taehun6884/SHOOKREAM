package svc;

import java.sql.Connection;

import dao.MemberDAO;
import db.JdbcUtil;

public class MemberIdCheckService {

	public int idCheck(String id) {
		
		int result = 0;
		
		Connection con = JdbcUtil.getConnection();
		
		MemberDAO dao = MemberDAO.getInstance();
		
		dao.setConnection(con);
		
		result = dao.selectAllId(id);
		
		return result;
	}

}
