package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import db.JdbcUtil;
import java.util.List;

import db.JdbcUtil;
import vo.ProductBean;

public class ProductDAO {
private ProductDAO() {}
	
	private static ProductDAO instance = new ProductDAO();

	public static ProductDAO getInstance() {
		return instance;
	}
	// ----------------------------------------------------------------------------------
	// 데이터베이스 접근에 사용할 Connection 객체를 Service 객체로부터 전달받기 위한
	// Connection 타입 멤버변수 선언 및 Setter 메서드 정의
	private Connection con;

	public void setConnection(Connection con) {
		this.con = con;
	}
	
	
	//--------상품 등록 작업--------------
	public int insertProduct(ProductBean product) {
		int insertCount = 0;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		
		try {
			//----------------idx 작업------------------
			String sql = "SELECT MAX(product_idx) FROM product";
			int idx = 1; // 새 글 번호
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { 
				 //true -> 조회결과가 있을 경우 (= 게시물이 하나라도 존재할 경우)
				 //존재하지 않을 경우 rs.next는 false , DB에서는 NULL이 표기된다.
				idx = rs.getInt(1) + 1;
			}
			System.out.println("새글 번호 :" + idx);
			
			//----------------상품 등록----------------------

			sql = "INSERT INTO product VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,now())";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx); //idx
			pstmt.setString(2, product.getProduct_name()); //name
			pstmt.setString(3, product.getProduct_brand()); //brand
			pstmt.setString(4, product.getProduct_size()); // size
			pstmt.setInt(5, product.getProduct_price()); // price
			pstmt.setInt(6, 0); // release price(쿠폰적용가격)
			pstmt.setInt(7, 0); // buy price(누적가격)
			pstmt.setInt(8, product.getProduct_amount()); // amount
			pstmt.setInt(9, 0); // sell_count
			pstmt.setString(10, product.getProduct_exp()); //요약 설명
			pstmt.setString(11, product.getProduct_detail_exp()); //상세 설명
			pstmt.setString(12, product.getProduct_color()); //색상
			pstmt.setDouble(13, product.getProduct_discount_price()); //할인율
			pstmt.setString(14, product.getProduct_img()); //메인 이미지
			
			insertCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("상품등록 - 관리자");
			e.printStackTrace();
		} 
		return insertCount;
	}



	// 상품 상세 정보 조회
	public ProductBean selectProduct(int product_idx) {
		ProductBean product = null;
		System.out.println(product_idx);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * FROM product WHERE product_idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, product_idx);
			rs = pstmt.executeQuery();
			
			// 조회 결과가 있을 경우
			if(rs.next()) {
				// ProductBean 객체 생성 후 조회 데이터 저장
				product = new ProductBean();
				product.setProduct_idx(rs.getInt("product_idx"));
				product.setProduct_name(rs.getString("product_name"));
				product.setProduct_brand(rs.getString("product_brand"));
				product.setProduct_price(rs.getInt("product_price"));
				product.setProduct_size(rs.getString("product_size"));
				product.setProduct_release_price(rs.getInt("product_release_price"));
				product.setProduct_buy_price(rs.getInt("product_buy_price"));
				product.setProduct_amount(rs.getInt("product_amount"));
				product.setProduct_sell_count(rs.getInt("product_sell_count"));
				product.setProduct_exp(rs.getString("product_exp"));
				product.setProduct_detail_exp(rs.getString("product_detail_exp"));
				product.setProduct_color(rs.getString("product_color"));
				product.setProduct_discount_price(rs.getDouble("product_discount_price"));
				product.setProduct_img(rs.getString("product_img"));
				product.setProduct_date(rs.getTimestamp("product_date"));
//				System.out.println(product);
			}
		} catch (SQLException e) {
			System.out.println("SQL구문 오류 - selectProduct()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return product;
	}
	
	
	// 관리자 - 상품 목록 조회
	public List<ProductBean> selectProductList() {
		ArrayList<ProductBean> productList = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * FROM product ORDER BY product_idx ASC";
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			productList = new ArrayList<ProductBean>();
			
			while(rs.next()) {
				ProductBean product = new ProductBean();
				product.setProduct_idx(rs.getInt("product_idx"));
				product.setProduct_name(rs.getString("product_name"));
				product.setProduct_brand(rs.getString("product_brand"));
				product.setProduct_size(rs.getString("product_size"));
				product.setProduct_price(rs.getInt("product_price"));
				product.setProduct_release_price(rs.getInt("product_release_price"));
				product.setProduct_buy_price(rs.getInt("product_buy_price"));
				product.setProduct_amount(rs.getInt("product_amount"));
				product.setProduct_sell_count(rs.getInt("product_sell_count"));
				product.setProduct_exp(rs.getString("product_exp"));
				product.setProduct_detail_exp(rs.getString("product_detail_exp"));
				product.setProduct_color(rs.getString("product_color"));
				product.setProduct_discount_price(rs.getDouble("product_discount_price"));
				product.setProduct_img(rs.getString("product_img"));
				product.setProduct_date(rs.getTimestamp("product_date"));
				
				productList.add(product);
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 - selectProductList()");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return productList;
	}
	
	//----------------장바 구니----------------------
		public List<ProductBean> getCartList() {
			 List<ProductBean> cartlist = null;
			 PreparedStatement pstmt =  null;
			 ResultSet rs = null;
				
			 String sql ="SELECT c.cart_idx,p.product_name, p.product_size, p.product_price,p.product_brand,p.product_img  "
			 		+ "FROM shookream.cart c join shookream.product p "
			 		+ "on c.product_idx = p.product_idx";
			 try {
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				cartlist = new ArrayList<ProductBean>();
				while(rs.next()) {
					ProductBean vo = new ProductBean();
					vo.setCart_idx(rs.getInt("cart_idx"));
					vo.setProduct_name(rs.getString("product_name"));
					vo.setProduct_size(rs.getNString("product_size"));
					vo.setProduct_price(rs.getInt("product_price"));
					vo.setProduct_brand(rs.getNString("product_brand"));
					vo.setProduct_img(rs.getString("product_img"));
					cartlist.add(vo);
				}
			 } catch (SQLException e) {
				e.printStackTrace();
			}finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
			return cartlist;
		}

		// 메인 - 메인화면 베스트 상품 목록 조회
		public List<ProductBean> selectBestProductList() {
			ArrayList<ProductBean> productBestList = null;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "SELECT * FROM product GROUP BY product_name ORDER BY product_sell_count asc";
				
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				productBestList = new ArrayList<ProductBean>();
				
				while(rs.next()) {
					ProductBean product = new ProductBean();
					product.setProduct_idx(rs.getInt("product_idx"));
					product.setProduct_name(rs.getString("product_name"));
					product.setProduct_brand(rs.getString("product_brand"));
					product.setProduct_size(rs.getString("product_size"));
					product.setProduct_price(rs.getInt("product_price"));
					product.setProduct_release_price(rs.getInt("product_release_price"));
					product.setProduct_buy_price(rs.getInt("product_buy_price"));
					product.setProduct_amount(rs.getInt("product_amount"));
					product.setProduct_sell_count(rs.getInt("product_sell_count"));
					product.setProduct_exp(rs.getString("product_exp"));
					product.setProduct_detail_exp(rs.getString("product_detail_exp"));
					product.setProduct_color(rs.getString("product_color"));
					product.setProduct_discount_price(rs.getDouble("product_discount_price"));
					product.setProduct_img(rs.getString("product_img"));
					product.setProduct_date(rs.getTimestamp("product_date"));
					
					productBestList.add(product);
				}
			} catch (SQLException e) {
				System.out.println("SQL 구문 오류 - selectBestProductList()");
				e.printStackTrace();
			} finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
			return productBestList;
		}

		
		// 메인 - 메인화면 최근 등록 상품 목록 조회
		public List<ProductBean> selectNewProductList() {
			ArrayList<ProductBean> productNewList = null;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "SELECT * FROM product GROUP BY product_name ORDER BY product_date desc";
				
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				productNewList = new ArrayList<ProductBean>();
				
				while(rs.next()) {
					ProductBean product = new ProductBean();
					product.setProduct_idx(rs.getInt("product_idx"));
					product.setProduct_name(rs.getString("product_name"));
					product.setProduct_brand(rs.getString("product_brand"));
					product.setProduct_size(rs.getString("product_size"));
					product.setProduct_price(rs.getInt("product_price"));
					product.setProduct_release_price(rs.getInt("product_release_price"));
					product.setProduct_buy_price(rs.getInt("product_buy_price"));
					product.setProduct_amount(rs.getInt("product_amount"));
					product.setProduct_sell_count(rs.getInt("product_sell_count"));
					product.setProduct_exp(rs.getString("product_exp"));
					product.setProduct_detail_exp(rs.getString("product_detail_exp"));
					product.setProduct_color(rs.getString("product_color"));
					product.setProduct_discount_price(rs.getDouble("product_discount_price"));
					product.setProduct_img(rs.getString("product_img"));
					product.setProduct_date(rs.getTimestamp("product_date"));
					
					productNewList.add(product);
				}
			} catch (SQLException e) {
				System.out.println("SQL 구문 오류 - selectNewProductList()");
				e.printStackTrace();
			} finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
			return productNewList;
		}

		public boolean updateProduct(ProductBean product, boolean updateProduct) {
			int updateProduct2 =0;
			boolean updateProduct1 = false;
			
			PreparedStatement pstmt =null;
			
			System.out.println(product);
			
			String sql ="UPDATE product SET product_name=?,  product_brand=?,  product_price=?, product_size=? , product_amount=?, product_color=?,  product_exp=?,  product_detail_exp=?,  product_discount_price=?, product_img=? ";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, product.getProduct_name());
				pstmt.setString(2, product.getProduct_brand());
				pstmt.setInt(3, product.getProduct_price());
				pstmt.setString(4, product.getProduct_size());
				pstmt.setInt(5, product.getProduct_amount());
				pstmt.setString(6, product.getProduct_color());
				pstmt.setString(7, product.getProduct_exp());
				pstmt.setString(8, product.getProduct_detail_exp());
				pstmt.setDouble(9, product.getProduct_discount_price());
				pstmt.setString(10, product.getProduct_img());
				updateProduct2 = pstmt.executeUpdate();
				if(updateProduct2 > 0) {
					updateProduct1 =true;
				}
					} catch (SQLException e) {
				System.out.println("sql 구문오류 -updateProduct");
				e.printStackTrace();
			}finally {
				JdbcUtil.close(pstmt);
			} 
		
		return updateProduct1;
}

		public ProductBean getProduct(int idx) {
			ProductBean bean = null;
			PreparedStatement pstmt = null;
			ResultSet rs  = null;
			String sql = "SELECT * FROM product WHERE product_idx=?";
			try {
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, idx);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					bean = new ProductBean();
					bean.setProduct_name(rs.getString("product_name"));
					bean.setProduct_brand(rs.getString("product_brand"));
					bean.setProduct_price(rs.getInt("product_price"));
					bean.setProduct_size(rs.getString("product_size"));
					bean.setProduct_amount(rs.getInt("product_amount"));
					bean.setProduct_color(rs.getString("product_color"));
					bean.setProduct_exp(rs.getString("product_exp"));
					bean.setProduct_detail_exp(rs.getString("product_detail_exp"));
					bean.setProduct_discount_price(rs.getDouble("product_discount_price"));
					bean.setProduct_img(rs.getString("product_img"));
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
			
			return bean;
		}
		
		public boolean isDeleteCart(int cart_idx) {
			int deleteCount =0;
			boolean isDeleteSuccess = false;
			PreparedStatement pstmt = null;
			
			String sql = "DELETE FROM product WHERE cart_idx=?";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, cart_idx);
				deleteCount = pstmt.executeUpdate();
				if(deleteCount > 0) {
					isDeleteSuccess=true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			return isDeleteSuccess;
		}
		
		public boolean isDeleteList(int product_idx) {
			int isDeletePro = 0;
			boolean isDeleteProduct = false;
			PreparedStatement pstmt = null;
			
			String sql = "DELETE FROM product WHERE product_idx=?";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, product_idx);
				isDeletePro = pstmt.executeUpdate();
				
				if(isDeletePro > 0) {
					isDeleteProduct =true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JdbcUtil.close(pstmt);
			}
			
			return isDeleteProduct;
		}
}//DAO 끝
