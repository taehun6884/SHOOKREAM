package svc;

import java.sql.Connection;

import dao.MemberDAO;
import dao.ProductDAO;
import db.JdbcUtil;
import vo.ProductBean;
import vo.WishBean;
import vo.imageBean;

public class ProductInfoProService {
//상품 상세정보 가져오기
	public ProductBean getProduct(int product_idx) {
		ProductBean product = null;
		
			Connection con = JdbcUtil.getConnection();
			ProductDAO dao = ProductDAO.getInstance();
			dao.setConnection(con);
			
			product = dao.selectProduct(product_idx);
			
				
			JdbcUtil.close(con);
			
			return product;
	}
//상품에 맞는 이미지 가져오기
	public imageBean getImage(int product_idx) {
		imageBean image = null;
		
		Connection con = JdbcUtil.getConnection();
		ProductDAO dao = ProductDAO.getInstance();
		dao.setConnection(con);
		
		image = dao.selectImage(product_idx);
		
		
		JdbcUtil.close(con);
		
		return image;
	}

	// 좋아요 한 상품 조회
		public WishBean getWishInfo(int product_idx, int member_idx) {
			WishBean wish = null;
			
			Connection con = JdbcUtil.getConnection();
			
			ProductDAO dao = ProductDAO.getInstance();
			
			dao.setConnection(con);
			
			wish = dao.selectWish(product_idx, member_idx);
			
			JdbcUtil.close(con);
			
			return wish;
		}

}
