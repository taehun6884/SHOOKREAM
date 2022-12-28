package svc;

import java.sql.Connection;

import dao.ProductDAO;
import db.JdbcUtil;

public class OrderDeleteService {

	public boolean isDeleteSuccess(int order_idx) {
		boolean isDeleteOrder = false;
				
		Connection con = JdbcUtil.getConnection();
		ProductDAO dao = ProductDAO.getInstance();
		dao.setConnection(con);
		
		isDeleteOrder = dao.isDeleteOrder(order_idx);
		if(isDeleteOrder==true) {
			JdbcUtil.commit(con);
		}else {
			JdbcUtil.rollback(con);
		}
			JdbcUtil.close(con);
		
		return isDeleteOrder;
	}
}
