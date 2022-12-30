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

	public int getMemberIdx(String sId) {
		int member_idx = 0;
		
		Connection con = JdbcUtil.getConnection();
		
		MemberDAO dao = MemberDAO.getInstance();
		
		dao.setConnection(con);
		
		member_idx = dao.selectMemberIdx(sId);
		
		return member_idx;
	}

}
