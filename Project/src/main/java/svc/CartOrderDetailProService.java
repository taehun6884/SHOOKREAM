package svc;

import java.sql.Connection;
import java.util.List;

import dao.ProductDAO;
import db.JdbcUtil;
import vo.cartBean;

public class CartOrderDetailProService {

	//-----------장바구니 -> 구매 이동 시 리스트 -----------------
	public List<cartBean> getCartOrderlist(int member_idx, String cart_idx) {
		List<cartBean> cartlist = null;
		Connection con = JdbcUtil.getConnection();
		
		ProductDAO dao = ProductDAO.getInstance();
		
		dao.setConnection(con);
			
		cartlist = dao.getCartList(member_idx, cart_idx);
		if(cartlist.isEmpty()) {//리스트 없을 때 
			JdbcUtil.rollback(con);
		}else {//리스트가 존재할 때 
			JdbcUtil.commit(con);
		}
		
		JdbcUtil.close(con);
		
		return cartlist;
	}
}
