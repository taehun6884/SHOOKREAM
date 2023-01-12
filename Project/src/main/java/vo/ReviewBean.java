package vo;

import java.sql.Date;

public class ReviewBean {
	private int review_idx; //리뷰 번호
	private int product_idx; // 리뷰가 작성되는 상품 번호
	private int member_idx; // 리뷰작성하는 멤버
	private String review_content; // 리뷰 내용
	private String review_img; // 리뷰 이미지
	private String review_real_img; // 리뷰 실제 이미지 파일
	private Date review_date; // 리뷰 작성일
	private String re_order_detail; // 리뷰 - 구매 상세(사이즈, 색상)
	private String re_product_name;
	// 이미지??? && 작성일
	
	
	
	public int getReview_idx() {
		return review_idx;
	}
	public String getRe_product_name() {
		return re_product_name;
	}
	public void setRe_product_name(String re_product_name) {
		this.re_product_name = re_product_name;
	}
	public void setReview_idx(int review_idx) {
		this.review_idx = review_idx;
	}
	public int getProduct_idx() {
		return product_idx;
	}
	public void setProduct_idx(int product_idx) {
		this.product_idx = product_idx;
	}
	public int getMember_idx() {
		return member_idx;
	}
	public void setMember_idx(int member_idx) {
		this.member_idx = member_idx;
	}
	public String getReview_content() {
		return review_content;
	}
	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}
	public String getReview_img() {
		return review_img;
	}
	public void setReview_img(String review_img) {
		this.review_img = review_img;
	}
	public String getReview_real_img() {
		return review_real_img;
	}
	public void setReview_real_img(String review_real_img) {
		this.review_real_img = review_real_img;
	}
	public Date getReview_date() {
		return review_date;
	}
	public void setReview_date(Date review_date) {
		this.review_date = review_date;
	}
	public String getRe_order_detail() {
		return re_order_detail;
	}
	public void setRe_order_detail(String order_detail) {
		this.re_order_detail = order_detail;
	}
	@Override
	public String toString() {
		return "ReviewBean [review_idx=" + review_idx + ", product_idx=" + product_idx + ", member_idx=" + member_idx
				+ ", review_content=" + review_content + ", review_img=" + review_img + ", review_real_img="
				+ review_real_img + ", review_date=" + review_date + ", re_order_detail=" + re_order_detail
				+ ", re_product_name=" + re_product_name + "]";
	}
	
	
}
