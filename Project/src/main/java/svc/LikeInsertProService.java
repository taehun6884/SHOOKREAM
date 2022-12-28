package svc;

import java.sql.Connection;

import dao.ProductDAO;
import db.JdbcUtil;

public class LikeInsertProService {

	public boolean InserLike(int member_idx, int product_idx) {
		boolean InsertLike = false;

		Connection con = JdbcUtil.getConnection();

		ProductDAO dao = ProductDAO.getInstance();

		dao.setConnection(con);

		int insertCount = dao.InsertLike(member_idx, product_idx);

		if (insertCount > 0) {
//			int wish_idx = dao.selectWishIdx();
			int updateCount = dao.updateWishCount(product_idx);

			if (updateCount > 0) {
				InsertLike = true;
				JdbcUtil.commit(con);
			}
		} else {
			JdbcUtil.rollback(con);
		}

		JdbcUtil.close(con);

		return InsertLike;
	}

}
