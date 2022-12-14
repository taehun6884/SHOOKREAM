package svc;

import java.sql.Connection;
import java.util.List;

import dao.ProductDAO;
import db.JdbcUtil;
import vo.OrderBean;

public class AdminOrderListProService {

	public List<OrderBean> getOrderList() {
		List<OrderBean> orderlist = null;
		
		Connection con = JdbcUtil.getConnection();
		
		ProductDAO dao = ProductDAO.getInstance();
		
		dao.setConnection(con);
			
		orderlist = dao.getAdminOrderList();
		
		JdbcUtil.close(con);
		
		
		return orderlist;
	}
	
	
}
