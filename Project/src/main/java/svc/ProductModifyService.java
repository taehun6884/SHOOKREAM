package svc;

import java.sql.Connection;

import dao.MemberDAO;
import dao.ProductDAO;
import db.JdbcUtil;
import vo.ProductBean;
import vo.imageBean;

public class ProductModifyService {

	public ProductBean getProduct(int idx) {
		ProductBean product = null;
		Connection con = JdbcUtil.getConnection();
		ProductDAO dao = ProductDAO.getInstance();
		dao.setConnection(con);
		product = dao.selectProduct(idx);
		JdbcUtil.close(con);
		
		return product;
	}

	public imageBean getImages(int idx) {
		imageBean image = null;
		Connection con = JdbcUtil.getConnection();
		ProductDAO dao = ProductDAO.getInstance();
		dao.setConnection(con);
		image = dao.selectImage(idx);
		JdbcUtil.close(con);
		
		return image;
	}
}
