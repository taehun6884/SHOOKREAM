package svc;

import java.sql.Connection;

import dao.ProductDAO;
import db.JdbcUtil;
import vo.ProductBean;

public class ProductModifyService {
	
	public boolean updateProduct(ProductBean product) {
		boolean updateProduct = false;
		
		Connection con = JdbcUtil.getConnection();
		ProductDAO dao = ProductDAO.getInstance();
		dao.setConnection(con);
		
		updateProduct = dao.updateProduct(product, updateProduct);
		
		if(updateProduct) {
			JdbcUtil.commit(con);
			
		} else {
			JdbcUtil.rollback(con);
		}
		JdbcUtil.close(con);
		return updateProduct;
	}

	public ProductBean getProduct(int idx) {
		ProductBean bean = null;
		
		Connection con = JdbcUtil.getConnection();
		ProductDAO dao = ProductDAO.getInstance();
		dao.setConnection(con);
		
		bean = dao.getProduct(idx);
		
		JdbcUtil.close(con);
		return bean;
		
		
		
	}
}
