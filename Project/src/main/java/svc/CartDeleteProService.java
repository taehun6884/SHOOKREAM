package svc;

import java.sql.Connection;

import dao.ProductDAO;
import db.JdbcUtil;

public class CartDeleteProService {

	public boolean isDeleteSuccess(int cart_idx) {
		
		boolean isDeleteCart = false;
		Connection con = JdbcUtil.getConnection();
		ProductDAO dao = ProductDAO.getInstance();
		dao.setConnection(con);
		
		isDeleteCart = dao.isDeleteCart(cart_idx);
		if(isDeleteCart == true) {
			JdbcUtil.commit(con);
		}else {
			JdbcUtil.rollback(con);
		}
		JdbcUtil.close(con);
		
		return isDeleteCart;
	}
}
