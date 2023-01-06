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
//			int member_idx = member.getMember_idx();
//			int insertCount2 = dao.insertWelcomCoupon();
			
//				if(insertCount2 > 0) { // 회원가입 쿠폰 지급
					JdbcUtil.commit(con);
					joinMember = true;
//				}
		} else { // 실패 시
			JdbcUtil.rollback(con);
		}
		
		JdbcUtil.close(con);
		
		return joinMember;
	}

}
