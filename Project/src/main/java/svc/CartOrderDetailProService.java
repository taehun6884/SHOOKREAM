package svc;

import java.sql.Connection;
import java.util.List;

import dao.ProductDAO;
import db.JdbcUtil;
import vo.cartBean;

public class CartOrderDetailProService {

	//-----------장바구니 -> 구매 이동 시 리스트 -----------------
	public List<cartBean> getCartOrderlist(int member_idx, String cart_idx) {
		List<cartBean> cartlist = null;
		Connection con = JdbcUtil.getConnection();
		
		ProductDAO dao = ProductDAO.getInstance();
		
		dao.setConnection(con);
			
		cartlist = dao.getCartList(member_idx, cart_idx);
		if(cartlist.isEmpty()) {//리스트 없을 때 
			JdbcUtil.rollback(con);
		}else {//리스트가 존재할 때 
			JdbcUtil.commit(con);
		}
		
		JdbcUtil.close(con);
		
		return cartlist;
	}
	//----------주문 금액-------------
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
}
