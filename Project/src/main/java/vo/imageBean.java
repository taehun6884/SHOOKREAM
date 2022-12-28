package vo;



public class imageBean {
//----------멤버변수-------------
private int image_idx; //이미지 번호
private int product_idx; //이미지 번호
private int review_idx; //이미지 번호
private int member_idx; //이미지 번호
private String image_main_file; // 섬네일 이미지
private String image_real_file1; // 상세 이미지1
private String image_real_file2;// 상세 이미지2
private int order_idx; // 주문번호


//---------------getter, setter-------------
public int getImage_idx() {
	return image_idx;
}
public void setImage_idx(int image_idx) {
	this.image_idx = image_idx;
}
public int getProduct_idx() {
	return product_idx;
}
public void setProduct_idx(int product_idx) {
	this.product_idx = product_idx;
}
public int getReview_idx() {
	return review_idx;
}
public void setReview_idx(int review_idx) {
	this.review_idx = review_idx;
}
public int getMember_idx() {
	return member_idx;
}
public void setMember_idx(int member_idx) {
	this.member_idx = member_idx;
}
public String getImage_main_file() {
	return image_main_file;
}
public void setImage_main_file(String image_main_file) {
	this.image_main_file = image_main_file;
}
public String getImage_real_file1() {
	return image_real_file1;
}
public void setImage_real_file1(String image_real_file1) {
	this.image_real_file1 = image_real_file1;
}
public String getImage_real_file2() {
	return image_real_file2;
}
public void setImage_real_file2(String image_real_file2) {
	this.image_real_file2 = image_real_file2;
}
public int getOrder_idx() {
	return order_idx;
}
public void setOrder_idx(int order_idx) {
	this.order_idx = order_idx;
}
//------------toString--------------

@Override
public String toString() {
	return "imageBean [image_idx=" + image_idx + ", product_idx=" + product_idx + ", review_idx=" + review_idx
			+ ", member_idx=" + member_idx + ", image_main_file=" + image_main_file + ", image_real_file1="
			+ image_real_file1 + ", image_real_file2=" + image_real_file2 + ", order_idx=" + order_idx + "]";
}







}
