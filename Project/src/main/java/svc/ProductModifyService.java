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
		product = dao.selectProduct(idx);
		dao.setConnection(con);
		
		return product;
	}

	public imageBean getImages(int idx) {
		imageBean image = null;
		Connection con = JdbcUtil.getConnection();
		ProductDAO dao = ProductDAO.getInstance();
		image = dao.selectImage(idx);
		dao.setConnection(con);
		
		return image;
	}
}
