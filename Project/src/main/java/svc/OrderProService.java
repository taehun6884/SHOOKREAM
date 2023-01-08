package svc;

import java.sql.Connection;
import java.util.List;

import dao.MemberDAO;
import dao.ProductDAO;
import db.JdbcUtil;
import vo.OrderBean;
import vo.ProductBean;

public class OrderProService {

	public boolean InsertOrder(OrderBean vo) {
		boolean result = false;
		
		Connection con = JdbcUtil.getConnection();
		
		ProductDAO dao = ProductDAO.getInstance();
		
		dao.setConnection(con);
		
		int insertCount = dao.insertOrder(vo);
		
		if(insertCount > 0) { // 성공 시
			JdbcUtil.commit(con);
			result = true;
		} else { // 실패 시
			JdbcUtil.rollback(con);
		}
		
		JdbcUtil.close(con);
		
		
		return result;
	}

	public OrderBean selectProgress() {
		OrderBean order = null;
		
		Connection con = JdbcUtil.getConnection();
		
		ProductDAO dao = ProductDAO.getInstance();
		
		dao.setConnection(con);
		
		order = dao.selectOrderProgress();
		
		return order;
	}


}
