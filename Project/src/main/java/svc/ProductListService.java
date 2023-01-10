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
		
		JdbcUtil.close(con);
		
		return productList;
	}

	// 메인 - 베스트 상품 목록 조회
	public List<ProductBean> getProductBestList(int startRow, int listLimit) {
		List<ProductBean> productBestList = null;
		
		Connection con = JdbcUtil.getConnection();
		
		ProductDAO dao = ProductDAO.getInstance();
		
		dao.setConnection(con);
		
		productBestList = dao.selectBestProductList(startRow, listLimit);
		
		JdbcUtil.close(con);
		
		return productBestList;
	}


	// 메인 - 최근 등록 상품 목록 조회
	public List<ProductBean> getProdoctNewList(int startRow, int listLimit) {
		List<ProductBean> productNewList = null;
		
		Connection con = JdbcUtil.getConnection();
		
		ProductDAO dao = ProductDAO.getInstance();
		
		dao.setConnection(con);
		
		productNewList = dao.selectNewProductList(startRow, listLimit);
		
		JdbcUtil.close(con);
		
		return productNewList;
	}

	// 메인 - 세일 상품 목록 조회
	public List<ProductBean> getProdoctSaleList(int startRow, int listLimit) {
		List<ProductBean> productSaleList = null;
		
		Connection con = JdbcUtil.getConnection();
		
		ProductDAO dao = ProductDAO.getInstance();
		
		dao.setConnection(con);
		
		productSaleList = dao.selectSaleProductList(startRow, listLimit);
		
		JdbcUtil.close(con);
		
		return productSaleList;
	}

	// 메인 - 카테고리별 상품 목록 조회
	public List<ProductBean> getProdoctCGList(String cg,int startRow, int listLimit) {
		List<ProductBean> productCGList = null;
		
		Connection con = JdbcUtil.getConnection();
		
		ProductDAO dao = ProductDAO.getInstance();
		
		dao.setConnection(con);
		
		productCGList = dao.selectCGProductList(cg,startRow, listLimit);
		
		JdbcUtil.close(con);
		
		return productCGList;
	}

	// 메인 - 검색어 상품 목록 조회
	public List<ProductBean> getProdoctSearchList(String keyword,int startRow, int listLimit) {
		List<ProductBean> productSearchList = null;
		
		Connection con = JdbcUtil.getConnection();
		
		ProductDAO dao = ProductDAO.getInstance();
		
		dao.setConnection(con);
		
		productSearchList = dao.selectKeywordProductList(keyword,startRow, listLimit);
		
		JdbcUtil.close(con);
		
		return productSearchList;
	}

	public List<ProductBean> getProductWish() {
		List<ProductBean> productWish = null;
		
		Connection con = JdbcUtil.getConnection();
		
		ProductDAO dao = ProductDAO.getInstance();
		
		dao.setConnection(con);
		
//		productWish = dao.selectProductWish();
		JdbcUtil.close(con);
		
		return productWish;
	}

	// 상품 수 조회
	public int getProductListCount() {
		int listCount = 0;
		
		Connection con = JdbcUtil.getConnection();
		
		ProductDAO dao = ProductDAO.getInstance();
		
		dao.setConnection(con);
		
		listCount = dao.selectProductListCount();
		if(listCount > 0) {
			JdbcUtil.commit(con);
		}else {
			JdbcUtil.rollback(con);
		}
		// 공통작업-4. Connection 객체 반환하기
		JdbcUtil.close(con);
		
		return listCount;
	}

	public int getProductCgListCount(String cg) {
		int listCount = 0;
		
		Connection con = JdbcUtil.getConnection();
		
		ProductDAO dao = ProductDAO.getInstance();
		
		dao.setConnection(con);
		
		listCount = dao.selectProductCgListCount(cg);
		if(listCount > 0) {
			JdbcUtil.commit(con);
		}else {
			JdbcUtil.rollback(con);
		}
		// 공통작업-4. Connection 객체 반환하기
		JdbcUtil.close(con);
		
		return listCount;
	}

	public int getProductKeywordListCount(String keyword) {
		int listCount = 0;
		
		Connection con = JdbcUtil.getConnection();
		
		ProductDAO dao = ProductDAO.getInstance();
		
		dao.setConnection(con);
		
		listCount = dao.selectProductKeywordListCount(keyword);
		if(listCount > 0) {
			JdbcUtil.commit(con);
		}else {
			JdbcUtil.rollback(con);
		}
		// 공통작업-4. Connection 객체 반환하기
		JdbcUtil.close(con);
		
		return listCount;
	}

}
