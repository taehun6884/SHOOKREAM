package vo;

import java.sql.Date;
import java.sql.Timestamp;

public class OrderBean {
private int order_idx;
private Timestamp order_date;
private String order_category;
private String order_progress;
private int order_member_idx;
private int order_product_idx;
private String order_main_image;
private String order_member_id;
private int order_product_price;
private int order_product_sell_count;
private int order_product_amount;
private String order_product_size;
private String order_product_color;
private int order_coupon_idx;
private int order_isUse;



public int getOrder_isUse() {
	return order_isUse;
}
public void setOrder_isUse(int order_isUse) {
	this.order_isUse = order_isUse;
}
public int getOrder_coupon_idx() {
	return order_coupon_idx;
}
public void setOrder_coupon_idx(int order_coupon_idx) {
	this.order_coupon_idx = order_coupon_idx;
}
public String getOrder_product_size() {
	return order_product_size;
}
public void setOrder_product_size(String order_product_size) {
	this.order_product_size = order_product_size;
}
public String getOrder_product_color() {
	return order_product_color;
}
public void setOrder_product_color(String order_product_color) {
	this.order_product_color = order_product_color;
}
public int getOrder_product_amount() {
	return order_product_amount;
}
public void setOrder_product_amount(int order_product_amount) {
	this.order_product_amount = order_product_amount;
}
public int getOrder_product_sell_count() {
	return order_product_sell_count;
}
public void setOrder_product_sell_count(int order_product_sell_count) {
	this.order_product_sell_count = order_product_sell_count;
}
public String getOrder_main_image() {
	return order_main_image;
}
public void setOrder_main_image(String order_main_image) {
	this.order_main_image = order_main_image;
}
public String getOrder_member_id() {
	return order_member_id;
}
public void setOrder_member_id(String order_member_id) {
	this.order_member_id = order_member_id;
}
public int getOrder_product_price() {
	return order_product_price;
}
public void setOrder_product_price(int order_product_price) {
	this.order_product_price = order_product_price;
}
public int getOrder_idx() {
	return order_idx;
}
public void setOrder_idx(int order_idx) {
	this.order_idx = order_idx;
}
public Timestamp getOrder_date() {
	return order_date;
}
public void setOrder_date(Timestamp order_date) {
	this.order_date = order_date;
}
public String getOrder_category() {
	return order_category;
}
public void setOrder_category(String order_category) {
	this.order_category = order_category;
}
public String getOrder_progress() {
	return order_progress;
}
public void setOrder_progress(String order_progress) {
	this.order_progress = order_progress;
}
public int getOrder_member_idx() {
	return order_member_idx;
}
public void setOrder_member_idx(int order_member_idx) {
	this.order_member_idx = order_member_idx;
}
public int getOrder_product_idx() {
	return order_product_idx;
}
public void setOrder_product_idx(int order_product_idx) {
	this.order_product_idx = order_product_idx;
}
@Override
public String toString() {
	return "OrderBean [order_idx=" + order_idx + ", order_date=" + order_date + ", order_category=" + order_category
			+ ", order_progress=" + order_progress + ", order_member_idx=" + order_member_idx + ", order_product_idx="
			+ order_product_idx + ", order_main_image=" + order_main_image + ", order_member_id=" + order_member_id
			+ ", order_product_price=" + order_product_price + ", order_product_sell_count=" + order_product_sell_count
			+ ", order_product_amount=" + order_product_amount + ", order_product_size=" + order_product_size
			+ ", order_product_color=" + order_product_color + ", order_coupon_idx=" + order_coupon_idx
			+ ", order_isUse=" + order_isUse + "]";
}





}
