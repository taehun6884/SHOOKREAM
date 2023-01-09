package svc;

import java.sql.Connection;

import dao.ProductDAO;
import db.JdbcUtil;
import vo.cartBean;

public class CartInsertProService {

	public boolean InsertCart(int product_idx, int member_idx, cartBean cart) {
		boolean InsertCart = false;
		
		Connection con = JdbcUtil.getConnection();
		
		ProductDAO dao = ProductDAO.getInstance();
		
		dao.setConnection(con);
		
		int insertCount = dao.CartInsert(product_idx, member_idx, cart);
		
		if(insertCount > 0) { // 성공 시
			JdbcUtil.commit(con);
			InsertCart = true;
		} else { // 실패 시
			JdbcUtil.rollback(con);
		}
		
		JdbcUtil.close(con);
		
		return InsertCart;
		
		
		
	}

}
