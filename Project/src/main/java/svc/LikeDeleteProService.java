package svc;

import java.sql.Connection;

import dao.ProductDAO;
import db.JdbcUtil;

public class LikeDeleteProService {

	public boolean DeleteWish(int member_idx, int product_idx) {
		boolean deleteWish = false;

		Connection con = JdbcUtil.getConnection();

		ProductDAO dao = ProductDAO.getInstance();

		dao.setConnection(con);

		int deleteCount = dao.deleteWish(member_idx, product_idx);

		if (deleteCount > 0) {
			int updateCount = dao.DecWishCount(product_idx);

			if (updateCount > 0) {
				deleteWish = true;
				JdbcUtil.commit(con);
			}
		} else {
			JdbcUtil.rollback(con);
		}

		JdbcUtil.close(con);

		return deleteWish;
	}

}
