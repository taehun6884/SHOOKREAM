package svc;

import java.sql.Connection;

import dao.ProductDAO;
import db.JdbcUtil;

public class ProductDeleteProService {
	
	public boolean isDeleteProduct(int product_idx) {
	boolean isDeleteList = false;
	
	Connection con = JdbcUtil.getConnection();
	
	ProductDAO dao = ProductDAO.getInstance();
	
	dao.setConnection(con);
	
	
	isDeleteList = dao.isDeleteList(product_idx);
	if(isDeleteList == true) {
		JdbcUtil.commit(con);
	}else {
		JdbcUtil.rollback(con);
	}
	JdbcUtil.close(con);
	return isDeleteList;
	
}
}

