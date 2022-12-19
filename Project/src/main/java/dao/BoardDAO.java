package dao;

import java.sql.Connection;

public class BoardDAO {
private BoardDAO() {}
	
	private static BoardDAO instance = new BoardDAO();

	public static BoardDAO getInstance() {
		return instance;
	}
	
	private Connection con;

	public void setConnection(Connection con) {
		this.con = con;
	}
}
