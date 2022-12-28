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
import vo.OrderBean;
import vo.ProductBean;
import vo.imageBean;

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
		int insertCount2 = 0;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
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

			sql = "INSERT INTO product VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,now())";
			
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setInt(1, idx); //idx
			pstmt2.setString(2, product.getProduct_name()); //name
			pstmt2.setString(3, product.getProduct_brand()); //brand
			pstmt2.setString(4, product.getProduct_size()); // size
			pstmt2.setInt(5, product.getProduct_price()); // price
			pstmt2.setInt(6, 0); // release price(쿠폰적용가격)
			pstmt2.setInt(7, 0); // buy price(누적가격)
			pstmt2.setInt(8, product.getProduct_amount()); // amount
			pstmt2.setInt(9, 0); // sell_count
			pstmt2.setString(10, product.getProduct_exp()); //요약 설명
			pstmt2.setString(11, product.getProduct_detail_exp()); //상세 설명
			pstmt2.setString(12, product.getProduct_color()); //색상
			pstmt2.setDouble(13, product.getProduct_discount_price()); //할인율
			
			insertCount = pstmt2.executeUpdate();
			
			//----------------이미지 등록------------------
			// image_idx 작업
			if(insertCount > 0) {
				sql = "SELECT MAX(image_idx) FROM image";
				int idx2 = 1; // 새 글 번호
				pstmt3 = con.prepareStatement(sql);
				rs = pstmt3.executeQuery();
				
				if(rs.next()) { 
					 //true -> 조회결과가 있을 경우 (= 게시물이 하나라도 존재할 경우)
					 //존재하지 않을 경우 rs.next는 false , DB에서는 NULL이 표기된다.
					idx2 = rs.getInt(1) + 1;
				}
				
				sql = "INSERT INTO image (image_idx, product_idx, image_main_file, image_real_file1, image_real_file2) VALUES(?,?,?,?,?)";
				pstmt4 = con.prepareStatement(sql);
				pstmt4.setInt(1, idx2);
				pstmt4.setInt(2, idx);
				pstmt4.setString(3, product.getProduct_img());
				pstmt4.setString(4, product.getProduct_img2());
				pstmt4.setString(5, product.getProduct_img3());
				
				insertCount2 = pstmt4.executeUpdate();
			}
			
			
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 - 상품등록: 관리자");
			e.printStackTrace();
		} 
		return insertCount2;
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
//				product.setProduct_img(rs.getString("product_img"));
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
			String sql = "SELECT p.product_idx, p.product_brand, p.product_name, p.product_price, p.product_date, p.product_amount, p.product_color, i.image_main_file "
					+ "FROM shookream.product p join shookream.image i "
					+ "on p.product_idx = i.product_idx ORDER BY product_date desc";
			
//			String sql = "SELECT p.product_idx, p.product_brand, p.product_name, p.product_price, p.product_date, p.product_amount, p.product_color, i.image_main_file "
//					+ "FROM shookream.product p join shookream.image i "
//					+ "on p.product_idx = i.product_idx GROUP BY product_name ORDER BY product_date desc";
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			productList = new ArrayList<ProductBean>();
			
			while(rs.next()) {
				ProductBean product = new ProductBean();
				product.setProduct_idx(rs.getInt("product_idx"));
				product.setProduct_name(rs.getString("product_name"));
				product.setProduct_brand(rs.getString("product_brand"));
//				product.setProduct_size(rs.getString("product_size"));
				product.setProduct_price(rs.getInt("product_price"));
//				product.setProduct_release_price(rs.getInt("product_release_price"));
//				product.setProduct_buy_price(rs.getInt("product_buy_price"));
				product.setProduct_amount(rs.getInt("product_amount"));
//				product.setProduct_sell_count(rs.getInt("product_sell_count"));
//				product.setProduct_exp(rs.getString("product_exp"));
//				product.setProduct_detail_exp(rs.getString("product_detail_exp"));
				product.setProduct_color(rs.getString("product_color"));
//				product.setProduct_discount_price(rs.getDouble("product_discount_price"));
				product.setProduct_img(rs.getString("image_main_file"));
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
		public int CartInsert(int product_idx, int member_idx) {
			int CartInsert = 0;
			PreparedStatement pstmt1,pstmt2 = null;
			ResultSet rs = null;
			
			try {
				String sql = "SELECT MAX(cart_idx) FROM cart";
				int cart_idx = 1;
				pstmt1 = con.prepareStatement(sql);
				rs = pstmt1.executeQuery();
				
				if(rs.next()) { 
					cart_idx = rs.getInt(1) + 1;
				}
				System.out.println(cart_idx);
		 //--------------------장바구니 등록--------------------------------		
			sql = "INSERT INTO cart VALUES(?,?,?,now())";	
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setInt(1, cart_idx);
			pstmt2.setInt(2, member_idx);	
			pstmt2.setInt(3, product_idx);	
			CartInsert = pstmt2.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return CartInsert;
		}
	
	
		//장바구니 리스트 출력
		public List<ProductBean> getCartList(int member_idx, int startRow, int listLimit) {
			 List<ProductBean> cartlist = null;
			 PreparedStatement pstmt =  null;
			 ResultSet rs = null;
				
			 String sql ="SELECT c.cart_idx,p.product_name, p.product_size, p.product_price,p.product_brand,i.image_main_file,m.member_id "
			 		+ "FROM shookream.cart c join shookream.product p join shookream.image i join shookream.member m "
			 		+ "on c.product_idx = p.product_idx and c.product_idx = i.product_idx and c.member_idx = m.member_idx "
			 		+ "where m.member_idx=? "
			 		+ "LIMIT ?,?";
			 
			 try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, member_idx);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, listLimit);
				rs = pstmt.executeQuery();
				cartlist = new ArrayList<ProductBean>();
				while(rs.next()) {
					ProductBean vo = new ProductBean();
					vo.setCart_idx(rs.getInt("cart_idx"));
					vo.setProduct_name(rs.getString("product_name"));
					vo.setProduct_size(rs.getNString("product_size"));
					vo.setProduct_price(rs.getInt("product_price"));
					vo.setProduct_brand(rs.getNString("product_brand"));
					vo.setProduct_img(rs.getString("image_main_file"));
					cartlist.add(vo);
				}
			 } catch (SQLException e) {
				e.printStackTrace();
			}finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
			return cartlist;
		}//
		
		// 장바구니 총 수량 새기
		public int selectCartListCount(int member_idx) {
			int listCount = 0;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				// board 테이블의 모든 레코드 갯수 조회
				// => 제목에 검색어를 포함하는 레코드 조회(WHERE subject LIKE '%검색어%')
				//    (단, 쿼리에 직접 '%?%' 형태로 작성 시 ? 문자를 파라미터로 인식하지 못함
				//    (따라서, setXXX() 메서드에서 문자열 결합으로 "%" + "검색어" + "%" 로 처리)
				String sql = "SELECT COUNT(cart_idx) "
									+ "FROM cart "
									+ "where member_idx = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, member_idx);
				rs = pstmt.executeQuery();
				// 조회 결과가 있을 경우 listCount 변수에 저장
				if(rs.next()) {
					listCount = rs.getInt(1);
				}
				
			} catch (SQLException e) {
				System.out.println("BoardDAO - selectBoardListCount()");
				e.printStackTrace();
			} finally {
				// DB 자원 반환
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
			
			return listCount;
		}

		// 메인 - 메인화면 베스트 상품 목록 조회
		public List<ProductBean> selectBestProductList() {
			ArrayList<ProductBean> productBestList = null;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "SELECT p.product_idx, p.product_brand, p.product_name, p.product_price, i.image_main_file "
						+ "FROM shookream.product p join shookream.image i "
						+ "on p.product_idx = i.product_idx GROUP BY product_name ORDER BY p.product_idx ASC";
				
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				productBestList = new ArrayList<ProductBean>();
				
				while(rs.next()) {
					ProductBean product = new ProductBean();
					product.setProduct_idx(rs.getInt("product_idx"));
					product.setProduct_name(rs.getString("product_name"));
					product.setProduct_brand(rs.getString("product_brand"));
//					product.setProduct_size(rs.getString("product_size"));
					product.setProduct_price(rs.getInt("product_price"));
//					product.setProduct_release_price(rs.getInt("product_release_price"));
//					product.setProduct_buy_price(rs.getInt("product_buy_price"));
////					product.setProduct_amount(rs.getInt("product_amount"));
//					product.setProduct_sell_count(rs.getInt("product_sell_count"));
//					product.setProduct_exp(rs.getString("product_exp"));
//					product.setProduct_detail_exp(rs.getString("product_detail_exp"));
//					product.setProduct_color(rs.getString("product_color"));
//					product.setProduct_discount_price(rs.getDouble("product_discount_price"));
					product.setProduct_img(rs.getString("image_main_file"));
//					product.setProduct_date(rs.getTimestamp("product_date"));
					
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
				String sql = "SELECT p.product_idx, p.product_brand, p.product_name, p.product_price, p.product_date, i.image_main_file "
				+ "FROM shookream.product p join shookream.image i "
				+ "on p.product_idx = i.product_idx GROUP BY product_name ORDER BY product_date desc";
				
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				productNewList = new ArrayList<ProductBean>();
				
				while(rs.next()) {
					ProductBean product = new ProductBean();
					product.setProduct_idx(rs.getInt("product_idx"));
					product.setProduct_name(rs.getString("product_name"));
					product.setProduct_brand(rs.getString("product_brand"));
//					product.setProduct_size(rs.getString("product_size"));
					product.setProduct_price(rs.getInt("product_price"));
//					product.setProduct_release_price(rs.getInt("product_release_price"));
//					product.setProduct_buy_price(rs.getInt("product_buy_price"));
//					product.setProduct_amount(rs.getInt("product_amount"));
//					product.setProduct_sell_count(rs.getInt("product_sell_count"));
//					product.setProduct_exp(rs.getString("product_exp"));
//					product.setProduct_detail_exp(rs.getString("product_detail_exp"));
//					product.setProduct_color(rs.getString("product_color"));
//					product.setProduct_discount_price(rs.getDouble("product_discount_price"));
					product.setProduct_img(rs.getString("image_main_file"));
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

		
		// 메인 - 메인화면 세일 상품 목록 조회
		public List<ProductBean> selectSaleProductList() {
			ArrayList<ProductBean> productSaleList = null;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "SELECT p.product_idx, p.product_brand, p.product_name, p.product_price, i.image_main_file "
						+ "FROM shookream.product p join shookream.image i "
						+ "on p.product_idx = i.product_idx "
						+ "WHERE product_discount_price > 0 "
						+ "GROUP BY product_name ORDER BY product_discount_price ASC";
				
//				String sql = "SELECT * FROM product "
//						+ "WHERE product_discount_price IS NOT NULL "
//						+ "ORDER BY product_discount_price";
				
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				productSaleList = new ArrayList<ProductBean>();
				
				while(rs.next()) {
					ProductBean product = new ProductBean();
					product.setProduct_idx(rs.getInt("product_idx"));
					product.setProduct_name(rs.getString("product_name"));
					product.setProduct_brand(rs.getString("product_brand"));
//					product.setProduct_size(rs.getString("product_size"));
					product.setProduct_price(rs.getInt("product_price"));
//					product.setProduct_release_price(rs.getInt("product_release_price"));
//					product.setProduct_buy_price(rs.getInt("product_buy_price"));
//					product.setProduct_amount(rs.getInt("product_amount"));
//					product.setProduct_sell_count(rs.getInt("product_sell_count"));
//					product.setProduct_exp(rs.getString("product_exp"));
//					product.setProduct_detail_exp(rs.getString("product_detail_exp"));
//					product.setProduct_color(rs.getString("product_color"));
//					product.setProduct_discount_price(rs.getDouble("product_discount_price"));
					product.setProduct_img(rs.getString("image_main_file"));
//					product.setProduct_date(rs.getTimestamp("product_date"));
					
					productSaleList.add(product);
				}
			} catch (SQLException e) {
				System.out.println("SQL 구문 오류 - selectSaleProductList()");
				e.printStackTrace();
			} finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
			return productSaleList;
		}

		// 메인 - 카테고리별 상품 목록 조회
		public List<ProductBean> selectCGProductList(String cg) {
			ArrayList<ProductBean> productCGList = null;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "SELECT p.product_idx, p.product_brand, p.product_name, p.product_price, i.image_main_file "
						+ "FROM shookream.product p join shookream.image i "
						+ "on p.product_idx = i.product_idx "
						+ "WHERE product_brand LIKE ? "
						+ "GROUP BY product_name ORDER BY product_sell_count ASC";
				
//				String sql = "SELECT * FROM product "
//						+ "WHERE product_brand LIKE ? "
//						+ "ORDER BY product_sell_count asc";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+cg+"%");
				
				rs = pstmt.executeQuery();
				
				productCGList = new ArrayList<ProductBean>();
				
				while(rs.next()) {
					ProductBean product = new ProductBean();
					product.setProduct_idx(rs.getInt("product_idx"));
					product.setProduct_name(rs.getString("product_name"));
					product.setProduct_brand(rs.getString("product_brand"));
//					product.setProduct_size(rs.getString("product_size"));
					product.setProduct_price(rs.getInt("product_price"));
//					product.setProduct_release_price(rs.getInt("product_release_price"));
//					product.setProduct_buy_price(rs.getInt("product_buy_price"));
//					product.setProduct_amount(rs.getInt("product_amount"));
//					product.setProduct_sell_count(rs.getInt("product_sell_count"));
//					product.setProduct_exp(rs.getString("product_exp"));
//					product.setProduct_detail_exp(rs.getString("product_detail_exp"));
//					product.setProduct_color(rs.getString("product_color"));
//					product.setProduct_discount_price(rs.getDouble("product_discount_price"));
					product.setProduct_img(rs.getString("image_main_file"));
//					product.setProduct_date(rs.getTimestamp("product_date"));
					
					productCGList.add(product);
//					System.out.println(productCGList);
				}
			} catch (SQLException e) {
				System.out.println("SQL 구문 오류 - selectCGProductList()");
				e.printStackTrace();
			} finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
			return productCGList;
		}

		
		// 메인 - 검색어 상품 목록 조회
		public List<ProductBean> selectKeywordProductList(String keyword) {
			ArrayList<ProductBean> productSearchList = null;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "SELECT p.product_idx, p.product_brand, p.product_name, p.product_price, i.image_main_file "
						+ "FROM shookream.product p join shookream.image i "
						+ "on p.product_idx = i.product_idx "
						+ "WHERE product_brand LIKE ? OR product_name LIKE ?"
						+ "GROUP BY product_name ORDER BY product_sell_count ASC";
				
//				String sql = "SELECT * FROM product "
//						+ "WHERE product_brand LIKE ? "
//						+ "ORDER BY product_sell_count asc";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setString(2, "%"+keyword+"%");
				
				rs = pstmt.executeQuery();
				
				productSearchList = new ArrayList<ProductBean>();
				
				while(rs.next()) {
					ProductBean product = new ProductBean();
					product.setProduct_idx(rs.getInt("product_idx"));
					product.setProduct_name(rs.getString("product_name"));
					product.setProduct_brand(rs.getString("product_brand"));
//					product.setProduct_size(rs.getString("product_size"));
					product.setProduct_price(rs.getInt("product_price"));
//					product.setProduct_release_price(rs.getInt("product_release_price"));
//					product.setProduct_buy_price(rs.getInt("product_buy_price"));
//					product.setProduct_amount(rs.getInt("product_amount"));
//					product.setProduct_sell_count(rs.getInt("product_sell_count"));
//					product.setProduct_exp(rs.getString("product_exp"));
//					product.setProduct_detail_exp(rs.getString("product_detail_exp"));
//					product.setProduct_color(rs.getString("product_color"));
//					product.setProduct_discount_price(rs.getDouble("product_discount_price"));
					product.setProduct_img(rs.getString("image_main_file"));
//					product.setProduct_date(rs.getTimestamp("product_date"));
					
					productSearchList.add(product);
					System.out.println(productSearchList);
				}
			} catch (SQLException e) {
				System.out.println("SQL 구문 오류 - selectKeywordProductList()");
				e.printStackTrace();
			} finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
			return productSearchList;
		}

        //주문 하기
		public int insertOrder(OrderBean vo) {
			int insertOrder = 0;
			PreparedStatement pstmt = null,pstmt2 = null,pstmt3 = null,pstmt4=null;
			ResultSet rs = null,rs2=null;
			
			try {
				String sql = "SELECT MAX(order_idx) FROM orderlist";
				int idx = 1; // 새 글 번호
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				if(rs.next()) { 
					 //true -> 조회결과가 있을 경우 (= 게시물이 하나라도 존재할 경우)
					 //존재하지 않을 경우 rs.next는 false , DB에서는 NULL이 표기된다.
					idx = rs.getInt(1) + 1;
				}
				
				sql = "INSERT INTO orderlist VALUES(?,now(),?,?,?,?)";
				pstmt2 = con.prepareStatement(sql);
				pstmt2.setInt(1,idx );
				pstmt2.setString(2,vo.getOrder_category());
				pstmt2.setString(3, vo.getOrder_progress());
				pstmt2.setInt(4, vo.getOrder_member_idx());
				pstmt2.setInt(5, vo.getOrder_product_idx());
				insertOrder=pstmt2.executeUpdate();
				
				if(insertOrder >0) {
					sql = "INSERT INTO order_detail VALUES(?,?,?)";
					pstmt3 = con.prepareStatement(sql);
					pstmt3.setInt(1, idx);
					pstmt3.setInt(2, vo.getOrder_member_idx());
					pstmt3.setInt(3, vo.getOrder_product_idx());
					insertOrder=pstmt3.executeUpdate();
					
					sql="UPDATE product SET product_amount = ?,product_sell_count=? WHERE product_idx=?";
					pstmt4 = con.prepareStatement(sql);
					pstmt4.setInt(1,  vo.getOrder_product_amount()-1);
					pstmt4.setInt(2,  vo.getOrder_product_sell_count()+1);
					pstmt4.setInt(3, vo.getOrder_product_idx());
					pstmt4.executeUpdate();
				}
				
			
			} catch (SQLException e) {
				e.printStackTrace();
			
			}finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt3);
				JdbcUtil.close(pstmt2);
				JdbcUtil.close(pstmt);
			}
			
			
			return insertOrder;
		}

		// 주문 리스트
		public List<OrderBean> getOrderList(int member_idx, int startRow, int listLimit) {
			List<OrderBean> orderlist = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String sql="SELECT i.image_main_file,m.member_id,p.product_price,o.order_category,o.order_progress,o.order_date,o.order_idx "
					+ "from shookream.orderlist o join shookream.product p join shookream.member m join shookream.image i "
					+ "on o.product_idx = p.product_idx and o.member_idx = m.member_idx and o.product_idx = i.product_idx "
					+ "where m.member_idx=? "
					+ "LIMIT ?,?";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1,member_idx );
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, listLimit);
				rs = pstmt.executeQuery();
				orderlist = new ArrayList<OrderBean>();
				while(rs.next()) {
					OrderBean vo = new OrderBean();
					vo.setOrder_main_image(rs.getString("image_main_file"));
					vo.setOrder_member_id(rs.getString("member_id"));
					vo.setOrder_product_price(rs.getInt("product_price"));
					vo.setOrder_category(rs.getString("order_category"));
					vo.setOrder_progress(rs.getString("order_progress"));
					vo.setOrder_date(rs.getTimestamp("order_date"));
					vo.setOrder_idx(rs.getInt("order_idx"));
					orderlist.add(vo);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
			
			return orderlist;
		}

		//주문 총 수량
		public int selectOrderListCount(int member_idx) {
			int listCount = 0;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				// board 테이블의 모든 레코드 갯수 조회
				// => 제목에 검색어를 포함하는 레코드 조회(WHERE subject LIKE '%검색어%')
				//    (단, 쿼리에 직접 '%?%' 형태로 작성 시 ? 문자를 파라미터로 인식하지 못함
				//    (따라서, setXXX() 메서드에서 문자열 결합으로 "%" + "검색어" + "%" 로 처리)
				String sql = "SELECT COUNT(order_idx) "
									+ "FROM orderlist "
									+ "where member_idx = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, member_idx);
				rs = pstmt.executeQuery();
				// 조회 결과가 있을 경우 listCount 변수에 저장
				if(rs.next()) {
					listCount = rs.getInt(1);
				}
			} catch (SQLException e) {
				System.out.println("BoardDAO - selectBoardListCount()");
				e.printStackTrace();
			} finally {
				// DB 자원 반환
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
			
			return listCount;
		}

		// 관리자 페이지 - 주문 내역
		public List<OrderBean> getAdminOrderList() {
			List<OrderBean> orderlist = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String sql="SELECT i.image_main_file,m.member_id,p.product_price,o.order_category,o.order_progress,o.order_date,o.order_idx "
					+ "from shookream.orderlist o join shookream.product p join shookream.member m join shookream.image i "
					+ "on o.product_idx = p.product_idx and o.member_idx = m.member_idx and o.product_idx = i.product_idx";
					
			
			try {
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				orderlist = new ArrayList<OrderBean>();
				while(rs.next()) {
					OrderBean vo = new OrderBean();
					vo.setOrder_main_image(rs.getString("image_main_file"));
					vo.setOrder_member_id(rs.getString("member_id"));
					vo.setOrder_product_price(rs.getInt("product_price"));
					vo.setOrder_category(rs.getString("order_category"));
					vo.setOrder_progress(rs.getString("order_progress"));
					vo.setOrder_date(rs.getTimestamp("order_date"));
					vo.setOrder_idx(rs.getInt("order_idx"));
					orderlist.add(vo);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
			
			return orderlist;
		}

		public boolean isDeleteCart(int cart_idx) {
			int deleteCount =0;
			boolean isDeleteSuccess = false;
			PreparedStatement pstmt = null;
			
			String sql = "DELETE FROM cart WHERE cart_idx=?";
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
			}finally {
				JdbcUtil.close(pstmt);
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
				
				e.printStackTrace();
			}finally {
				JdbcUtil.close(pstmt);
			}
			
			return isDeleteProduct;
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

//상품 상세정보에서 이미지 정보 가져오는 메서드
		public imageBean selectImage(int product_idx) {
			imageBean image = null;
			PreparedStatement pstmt = null;
			ResultSet rs  = null;
			//--------------------이미지 이름 가져오기 작업--------------
			try {
				String sql = "SELECT image_main_file, image_real_file1,image_real_file2  FROM image WHERE product_idx = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, product_idx);
				rs = pstmt.executeQuery();
				
				
				if(rs.next()) {
					image = new imageBean();
					image.setImage_main_file(rs.getString("image_main_file")); //메인 이미지 가져오기
					image.setImage_real_file1(rs.getString("image_real_file1")); //상세 이미지 가져오기
					image.setImage_real_file2(rs.getString("image_real_file2")); //상세 이미지 가져오기
				}
			} catch (SQLException e) {
				System.out.println("SQL 구문 오류 - selectImage");
				e.printStackTrace();
			}
			return image;
		}
		
		public boolean isDeleteOrder(int order_idx) {
			int isDeleteOrderList = 0;
			boolean isDeleteSuccess = false;
			PreparedStatement pstmt =null;
			String sql ="DELETE FROM orderlist WHERE order_idx=?"; 
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, order_idx);
				isDeleteOrderList =pstmt.executeUpdate();
				
				if(isDeleteOrderList >0) {
					isDeleteSuccess = true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JdbcUtil.close(pstmt);
			}
			return isDeleteSuccess;
			
		}
		

	
}//DAO 끝
