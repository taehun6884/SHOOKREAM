package svc;

import java.sql.Connection;

import dao.ProductDAO;
import db.JdbcUtil;
import vo.ProductBean;

public class ProductInfoProService {

	public ProductBean getProduct(int product_idx) {
		ProductBean product = null;
		
			Connection con = JdbcUtil.getConnection();
			ProductDAO dao = ProductDAO.getInstance();
			dao.setConnection(con);
			
			product = dao.selectProduct(product_idx);
			
				
			JdbcUtil.close(con);
			
			return product;
	}

	

}
