package vo;

import java.sql.Date;

public class CouponBean {
	private int coupon_idx;
	private String coupon_name;
	private int coupon_price;
	private String coupon_content;
	private Date coupon_start;
	private Date coupon_end;
	private Date coupon_date;
	public int getCoupon_idx() {
		return coupon_idx;
	}
	public void setCoupon_idx(int coupon_idx) {
		this.coupon_idx = coupon_idx;
	}
	public String getCoupon_name() {
		return coupon_name;
	}
	public void setCoupon_name(String coupon_name) {
		this.coupon_name = coupon_name;
	}
	public int getCoupon_price() {
		return coupon_price;
	}
	public void setCoupon_price(int coupon_price) {
		this.coupon_price = coupon_price;
	}
	public String getCoupon_content() {
		return coupon_content;
	}
	public void setCoupon_content(String coupon_content) {
		this.coupon_content = coupon_content;
	}
	public Date getCoupon_start() {
		return coupon_start;
	}
	public void setCoupon_start(Date coupon_start) {
		this.coupon_start = coupon_start;
	}
	public Date getCoupon_end() {
		return coupon_end;
	}
	public void setCoupon_end(Date coupon_end) {
		this.coupon_end = coupon_end;
	}
	public Date getCoupon_date() {
		return coupon_date;
	}
	public void setCoupon_date(Date coupon_date) {
		this.coupon_date = coupon_date;
	}
	
	
	@Override
	public String toString() {
		return "CouponBean [coupon_idx=" + coupon_idx + ", coupon_name=" + coupon_name + ", coupon_price="
				+ coupon_price + ", coupon_content=" + coupon_content + ", coupon_start=" + coupon_start
				+ ", coupon_end=" + coupon_end + ", coupon_date=" + coupon_date + "]";
	}
	
	
}
