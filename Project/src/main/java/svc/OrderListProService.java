package svc;

import java.sql.Connection;
import java.util.List;

import dao.ProductDAO;
import db.JdbcUtil;
import vo.OrderBean;

public class OrderListProService {

	public List<OrderBean> getOrderList(int member_idx) {
		List<OrderBean> orderlist = null;
		
		Connection con = JdbcUtil.getConnection();
		
		ProductDAO dao = ProductDAO.getInstance();
		
		dao.setConnection(con);
			
		orderlist = dao.getOrderList(member_idx);
		
		JdbcUtil.close(con);
		
		
		return orderlist;
	}

}
