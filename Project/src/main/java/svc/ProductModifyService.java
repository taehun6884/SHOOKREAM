package svc;

import java.sql.Connection;

import dao.MemberDAO;
import dao.ProductDAO;
import db.JdbcUtil;
import vo.ProductBean;

public class ProductModifyService {

	public ProductBean getProduct(int idx) {
		ProductBean vo = null;
		
		Connection con = JdbcUtil.getConnection();
		ProductDAO dao = ProductDAO.getInstance();
		dao.setConnection(con);
		
		
		
		return vo;
	}

}
