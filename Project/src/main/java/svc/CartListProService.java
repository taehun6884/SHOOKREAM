package svc;

import java.sql.Connection;
import java.util.List;

import dao.ProductDAO;
import db.JdbcUtil;
import vo.ProductBean;

public class CartListProService {

	public List<ProductBean> getCartlist() {
		List<ProductBean> cartlist = null;
		Connection con = JdbcUtil.getConnection();
		
		ProductDAO dao = ProductDAO.getInstance();
		
		dao.setConnection(con);
			
		cartlist = dao.getCartList();
		
		
		JdbcUtil.close(con);
		
		return cartlist;
	}

	
	
	
}
