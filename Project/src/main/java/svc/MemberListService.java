package svc;

import java.sql.Connection;
import java.util.List;

import dao.MemberDAO;
import db.JdbcUtil;
import vo.MemberBean;

public class MemberListService {

	public List<MemberBean> getMemberList() {
		List<MemberBean> memberList = null;
		//공통 작업1
		Connection con = JdbcUtil.getConnection();
		//공통 작업2
		MemberDAO dao = MemberDAO.getInstance();
		//공통 작업3
		dao.setConnection(con);
		
		//dao에서 memberList 받아오기
		memberList = dao.selectMemberList();
		
		//공통 작업4
		JdbcUtil.close(con);
		
		
		return memberList;
	}

}
