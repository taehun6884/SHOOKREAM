package svc;

import java.sql.Connection;

import dao.MemberDAO;
import db.JdbcUtil;
import vo.MemberBean;

public class MemberJoinProService {

	public boolean joinMember(MemberBean member) {
		boolean joinMember = false;
		
		Connection con = JdbcUtil.getConnection();
		
		MemberDAO dao = MemberDAO.getInstance();
		
		dao.setConnection(con);
		
		int insertCount = dao.insertMember(member);
		
		if(insertCount > 0) { // 성공 시
			JdbcUtil.commit(con);
			joinMember = true;
		} else { // 실패 시
			JdbcUtil.rollback(con);
		}
		
		JdbcUtil.close(con);
		
		return joinMember;
	}

}
