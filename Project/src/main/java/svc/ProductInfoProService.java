package svc;

import java.sql.Connection;

import dao.ProductDAO;
import db.JdbcUtil;
import vo.ProductBean;
import vo.imageBean;

public class ProductInfoProService {
//상품 상세정보 가져오기
	public ProductBean getProduct(int product_idx) {
		ProductBean product = null;
		
			Connection con = JdbcUtil.getConnection();
			ProductDAO dao = ProductDAO.getInstance();
			dao.setConnection(con);
			
			product = dao.selectProduct(product_idx);
			
				
			JdbcUtil.close(con);
			
			return product;
	}
//상품에 맞는 이미지 가져오기
	public imageBean getImage(int product_idx) {
		imageBean image = null;
		
		Connection con = JdbcUtil.getConnection();
		ProductDAO dao = ProductDAO.getInstance();
		dao.setConnection(con);
		
		image = dao.selectImage(product_idx);
		
		
		JdbcUtil.close(con);
		
		return image;
	}

	

}
