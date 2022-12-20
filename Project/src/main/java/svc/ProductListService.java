package svc;

import java.sql.Connection;
import java.util.List;

import dao.ProductDAO;
import db.JdbcUtil;
import vo.ProductBean;

public class ProductListService {

	// 관리자 - 상품 목록 조회
	public List<ProductBean> getProdoctList() {
		List<ProductBean> productList = null;
		
		Connection con = JdbcUtil.getConnection();
		
		ProductDAO dao = ProductDAO.getInstance();
		
		dao.setConnection(con);
		
		productList = dao.selectProductList();
		
		return productList;
	}

	// 메인 - 베스트 상품 목록 조회
	public List<ProductBean> getProdoctBestList() {
		List<ProductBean> productBestList = null;
		
		Connection con = JdbcUtil.getConnection();
		
		ProductDAO dao = ProductDAO.getInstance();
		
		dao.setConnection(con);
		
		productBestList = dao.selectBestProductList();
		
		return productBestList;
	}

}
