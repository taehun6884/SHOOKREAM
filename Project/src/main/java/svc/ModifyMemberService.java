package svc;

import java.sql.Connection;

import dao.MemberDAO;
import db.JdbcUtil;
import vo.MemberBean;

public class ModifyMemberService {

	public int updateMember(MemberBean member) {
		int updateMember = 0;
		
		Connection con = JdbcUtil.getConnection();
		MemberDAO dao = MemberDAO.getInstance();
		dao.setConnection(con);
		
		updateMember = dao.updateMember(member);
		
		if(updateMember > 0) {
			JdbcUtil.commit(con);
			
		} else {
			JdbcUtil.rollback(con);
		}
		
		return updateMember;
	}

	public MemberBean getMemberInfo(String id) {
		MemberBean member2 = null;
		
		Connection con = JdbcUtil.getConnection();
		MemberDAO dao = MemberDAO.getInstance();
		dao.setConnection(con);
		
		member2 = dao.getInfo(id);
		
		return member2;
	}
	
	
	
}
