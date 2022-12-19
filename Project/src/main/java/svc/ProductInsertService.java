package svc;

import java.sql.Connection;

import dao.ProductDAO;
import db.JdbcUtil;
import vo.ProductBean;

public class ProductInsertService {

	public int insertProduct(ProductBean product) {
		int insertCount = 0;
		//공통 작업1
		Connection con = JdbcUtil.getConnection();
		//공통 작업2
		ProductDAO dao = ProductDAO.getInstance();
		dao.setConnection(con);
		//공통 작업3
		
		//dao에서 insert 결과 받아오기
		insertCount = dao.insertProduct(product);
		
		if(insertCount > 0) {
			JdbcUtil.commit(con);
		}else {
			JdbcUtil.rollback(con);
		}
		
		//공통 작업4
		JdbcUtil.close(con);
		
		
		
		
		return insertCount;
	}

}
