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


	// 메인 - 최근 등록 상품 목록 조회
	public List<ProductBean> getProdoctNewList() {
		List<ProductBean> productNewList = null;
		
		Connection con = JdbcUtil.getConnection();
		
		ProductDAO dao = ProductDAO.getInstance();
		
		dao.setConnection(con);
		
		productNewList = dao.selectNewProductList();
		
		return productNewList;
	}

	// 메인 - 세일 상품 목록 조회
	public List<ProductBean> getProdoctSaleList() {
		List<ProductBean> productSaleList = null;
		
		Connection con = JdbcUtil.getConnection();
		
		ProductDAO dao = ProductDAO.getInstance();
		
		dao.setConnection(con);
		
		productSaleList = dao.selectSaleProductList();
		
		return productSaleList;
	}

	// 메인 - 카테고리별 상품 목록 조회
	public List<ProductBean> getProdoctCGList(String cg) {
		List<ProductBean> productCGList = null;
		
		Connection con = JdbcUtil.getConnection();
		
		ProductDAO dao = ProductDAO.getInstance();
		
		dao.setConnection(con);
		
		productCGList = dao.selectCGProductList(cg);
		
		return productCGList;
	}

	// 메인 - 검색어 상품 목록 조회
	public List<ProductBean> getProdoctSearchList(String keyword) {
		List<ProductBean> productSearchList = null;
		
		Connection con = JdbcUtil.getConnection();
		
		ProductDAO dao = ProductDAO.getInstance();
		
		dao.setConnection(con);
		
		productSearchList = dao.selectKeywordProductList(keyword);
		
		return productSearchList;
	}

}
