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
		
//		/-----------------마이너스 후 포워딩을 위해 member_idx를 찾는 메서드-------------
		public int selectMember(int cart_idx) {
			int member_idx = 0;
			// 공통작업-1. Connection 객체 가져오기
			Connection con = JdbcUtil.getConnection();
			
			// 공통작업-2. BoardDAO 객체 가져오기
			ProductDAO dao = ProductDAO.getInstance();
			
			// 공통작업-3. BoardDAO 객체에 Connection 객체 전달하기
			dao.setConnection(con);
			
			member_idx = dao.selectMember(cart_idx);
			
			JdbcUtil.close(con);
			return member_idx;
		}
//-------------------페이징 리스트 개수--------------
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
}

