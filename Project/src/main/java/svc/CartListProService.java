package svc;

import java.sql.Connection;
import java.util.List;

import dao.BoardDAO;
import dao.ProductDAO;
import db.JdbcUtil;
import vo.ProductBean;
import vo.cartBean;

public class CartListProService {

	public List<cartBean> getCartlist(int member_idx, int startRow, int listLimit) {
		List<cartBean> cartlist = null;
		Connection con = JdbcUtil.getConnection();
		
		ProductDAO dao = ProductDAO.getInstance();
		
		dao.setConnection(con);
			
		cartlist = dao.getCartList(member_idx,startRow,listLimit);
		if(cartlist.isEmpty()) {//리스트 없을 때 
			JdbcUtil.rollback(con);
		}else {//리스트가 존재할 때 
			JdbcUtil.commit(con);
		}
		
		JdbcUtil.close(con);
		
		return cartlist;
	}

	public int getCartListCount(int member_idx) {
		int listCount = 0;
		
		// 공통작업-1. Connection 객체 가져오기
		Connection con = JdbcUtil.getConnection();
		
		// 공통작업-2. BoardDAO 객체 가져오기
		ProductDAO dao = ProductDAO.getInstance();
		
		// 공통작업-3. BoardDAO 객체에 Connection 객체 전달하기
		dao.setConnection(con);
		
		// BoardDAO 객체의 selectBoardListCount() 메서드를 호출하여 글목록 갯수 조회 작업 수행
		// => 파라미터 : 검색어     리턴타입 : int(listCount)
		listCount = dao.selectCartListCount(member_idx);
		if(listCount > 0) {
			JdbcUtil.commit(con);
		}else {
			JdbcUtil.rollback(con);
		}
		// 공통작업-4. Connection 객체 반환하기
		JdbcUtil.close(con);
		
		return listCount;
	}

	
	public int CartTotalPrice(int member_idx) {
		int total =0;
		
		// 공통작업-1. Connection 객체 가져오기
		Connection con = JdbcUtil.getConnection();
		
		// 공통작업-2. BoardDAO 객체 가져오기
		ProductDAO dao = ProductDAO.getInstance();
		
		// 공통작업-3. BoardDAO 객체에 Connection 객체 전달하기
		dao.setConnection(con);
		
		// BoardDAO 객체의 selectBoardListCount() 메서드를 호출하여 글목록 갯수 조회 작업 수행
		// => 파라미터 : 검색어     리턴타입 : int(listCount)
		total = dao.totalPrice(member_idx);
		if(total > 0) {
			JdbcUtil.commit(con);
		}else {
			JdbcUtil.rollback(con);
		}
		
		return total;
	}

	public List<ProductBean> getWishlist(int member_idx, int startRow, int listLimit) {
		List<ProductBean> wishlist = null;
		Connection con = JdbcUtil.getConnection();
		
		ProductDAO dao = ProductDAO.getInstance();
		
		dao.setConnection(con);
			
		wishlist = dao.getWishList(member_idx,startRow,listLimit);
		
		JdbcUtil.close(con);
		
		return wishlist;
	}

	public int getWishListCount(int member_idx) {
		int listCount = 0;
		
		Connection con = JdbcUtil.getConnection();
		
		ProductDAO dao = ProductDAO.getInstance();
		
		dao.setConnection(con);
		
		listCount = dao.selectWishListCount(member_idx);
		
		JdbcUtil.close(con);
		
		return listCount;
	}
	

	//-------체크박스 선택 시 cart_price 감소 작업-----------
	public int CartMinusTotal(int member_idx) {
		int minusTotal = 0;
		
		// 공통작업-1. Connection 객체 가져오기
		Connection con = JdbcUtil.getConnection();
		
		// 공통작업-2. BoardDAO 객체 가져오기
		ProductDAO dao = ProductDAO.getInstance();
		
		// 공통작업-3. BoardDAO 객체에 Connection 객체 전달하기
		dao.setConnection(con);
		minusTotal = dao.minusTotal(member_idx);

		return minusTotal;
	}
	
	
}
