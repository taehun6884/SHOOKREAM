package svc;

import java.sql.Connection;
import java.util.List;

import dao.ProductDAO;
import db.JdbcUtil;
import vo.ProductBean;

public class ProductListService {

	public List<ProductBean> getProdoctList() {
		List<ProductBean> productList = null;
		
		Connection con = JdbcUtil.getConnection();
		
		ProductDAO dao = ProductDAO.getInstance();
		
		dao.setConnection(con);
		
		productList = dao.selectProductList();
		
		JdbcUtil.close(con);
		
		return productList;
	}

}
