package svc;

import java.sql.Connection;

import dao.ProductDAO;
import db.JdbcUtil;

public class CartPlusProService {
	//-------체크박스 선택 시 cart_price 증가 작업-----------
		public boolean CartPlusTotal(int cart_idx) {
			int plusTotal = 0;
			boolean isPlusTotal = false;
			
			// 공통작업-1. Connection 객체 가져오기
			Connection con = JdbcUtil.getConnection();
			
			// 공통작업-2. BoardDAO 객체 가져오기
			ProductDAO dao = ProductDAO.getInstance();
			
			// 공통작업-3. BoardDAO 객체에 Connection 객체 전달하기
			dao.setConnection(con);
			plusTotal = dao.plusTotal(cart_idx);
			if(plusTotal > 0) { // 성공 시
				JdbcUtil.commit(con);
				isPlusTotal = true;
			} else { // 실패 시
				JdbcUtil.rollback(con);
			}
			
			JdbcUtil.close(con);
			
			return isPlusTotal;
		}
}
