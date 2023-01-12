package svc;

import java.sql.Connection;

import dao.ProductDAO;
import db.JdbcUtil;
import vo.ProductBean;
import vo.imageBean;

public class ProductModifyProService {

	public boolean modifyProduct(int idx, ProductBean product) {
		boolean isUpdateSuccess = false;

		Connection con = JdbcUtil.getConnection();
		ProductDAO dao = ProductDAO.getInstance();
		dao.setConnection(con);
		
		int updateCount = dao.updateProduct(idx, product);
		
		if(updateCount > 0) {
			JdbcUtil.commit(con);
			isUpdateSuccess = true;
		}else {
			JdbcUtil.rollback(con);
			isUpdateSuccess = false;
		}
		
		JdbcUtil.close(con);

		return isUpdateSuccess;
	}
	
	



}
