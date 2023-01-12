package svc;

import java.sql.Connection;

import dao.ProductDAO;
import db.JdbcUtil;
import vo.imageBean;

public class ProductDeleteProService {
	
	// ----------- 상품 삭제 ------------
	public boolean isDeleteProduct(int product_idx) {
	boolean isDeleteProduct = false;
	
	Connection con = JdbcUtil.getConnection();
	
	ProductDAO dao = ProductDAO.getInstance();
	
	dao.setConnection(con);
	
	int deleteCount = dao.deleteProduct(product_idx);
	
	if(deleteCount > 0 ) { // 삭제 성공 시 
		JdbcUtil.commit(con);
		isDeleteProduct = true;
	}else {//삭제 실패 시
		JdbcUtil.rollback(con);
		isDeleteProduct = false;
	}
	JdbcUtil.close(con);
	
	return isDeleteProduct;
	
}
	//----------삭제할 상품의 이미지 정보 가져오기 --------
	public imageBean getImages(int idx) {
		imageBean image = null;
		Connection con = JdbcUtil.getConnection();
		ProductDAO dao = ProductDAO.getInstance();
		dao.setConnection(con);
		image = dao.selectImage(idx);
		
		JdbcUtil.close(con);
		return image;
	}
	
	//주문 내역 삭제
	public boolean OrderDeletePro(int order_idx) {
		boolean isDeleteProduct = false;
		
		Connection con = JdbcUtil.getConnection();
		
		ProductDAO dao = ProductDAO.getInstance();
		
		dao.setConnection(con);
		
		
		int deleteCount = dao.OrderDeleteList(order_idx);
		if(deleteCount > 0 ) { // 삭제 성공 시 
			JdbcUtil.commit(con);
			isDeleteProduct = true;
		}else {//삭제 실패 시
			JdbcUtil.rollback(con);
			isDeleteProduct = false;
		}
		JdbcUtil.close(con);
		
		return isDeleteProduct;
		
	}
}

