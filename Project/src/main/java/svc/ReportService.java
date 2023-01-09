package svc;

import java.sql.Connection;

import dao.ProductDAO;
import db.JdbcUtil;

public class ReportService {

	public boolean reportadd(int idx) {
		
		boolean reportplus = false;
		Connection con = JdbcUtil.getConnection();

		ProductDAO dao = ProductDAO.getInstance();

		dao.setConnection(con);
		
		reportplus = dao.reportcount(idx);
		
		if(reportplus == true) { 
			JdbcUtil.commit(con);
		}else {
			JdbcUtil.rollback(con);
		}
		return reportplus;
		
	}

	
}
