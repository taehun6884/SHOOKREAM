package svc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.MemberDAO;
import db.JdbcUtil;
import vo.MemberBean;

public class ModifyMemberService {

	public boolean updateMember(MemberBean member) {
		boolean updateMember = false;
		
		Connection con = JdbcUtil.getConnection();
		MemberDAO dao = MemberDAO.getInstance();
		dao.setConnection(con);
		
		updateMember = dao.updateMember(member, true);
		
		if(updateMember) {
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
	
	public boolean isRightUser(MemberBean member) {
		boolean isRightUser = false;
		
		Connection con = JdbcUtil.getConnection();
		MemberDAO dao = MemberDAO.getInstance();
		dao.setConnection(con);
		
		isRightUser = dao.isRightUser(member);
		
		if(isRightUser == true) {
			JdbcUtil.commit(con);
			
		} else {
			JdbcUtil.rollback(con);
		}
		return isRightUser;
	}
	
}
