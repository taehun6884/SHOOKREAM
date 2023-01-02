package svc;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.mysql.cj.util.Util;

import dao.MemberDAO;
import db.JdbcUtil;
import vo.MemberBean;
import vo.WishBean;

public class LoginMemberService {

	public boolean isLoginUser(String id, String pass) {
		boolean isLoginUser = false;
		
		Connection con = JdbcUtil.getConnection();
		
		MemberDAO dao = MemberDAO.getInstance();
		
		dao.setConnection(con);
		
		isLoginUser = dao.isLoginUser(id,pass);
		
		JdbcUtil.close(con);
		
		return isLoginUser;
	}


	
}
