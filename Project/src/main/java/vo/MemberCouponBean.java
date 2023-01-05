package vo;

import java.sql.Date;

public class MemberCouponBean {
	private int coupon_idx;
	private int member_idx;
	private String coupon_name;
	private int coupon_price;
	private String coupon_content;
	private String coupon_start;
	private String coupon_end;
	private int coupon_isUse;
	
	
	public int getCoupon_isUse() {
		return coupon_isUse;
	}
	public void setCoupon_isUse(int coupon_isUse) {
		this.coupon_isUse = coupon_isUse;
	}
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
	
	public String getCoupon_start() {
		return coupon_start;
	}
	public void setCoupon_start(String coupon_start) {
		this.coupon_start = coupon_start;
	}
	public String getCoupon_end() {
		return coupon_end;
	}
	public void setCoupon_end(String coupon_end) {
		this.coupon_end = coupon_end;
	}
	public int getMember_idx() {
		return member_idx;
	}
	public void setMember_idx(int member_idx) {
		this.member_idx = member_idx;
	}
	public String getCoupon_content() {
		return coupon_content;
	}
	public void setCoupon_content(String coupon_content) {
		this.coupon_content = coupon_content;
	}
	@Override
	public String toString() {
		return "MemberCouponBean [coupon_idx=" + coupon_idx + ", member_idx=" + member_idx + ", coupon_name="
				+ coupon_name + ", coupon_price=" + coupon_price + ", coupon_content=" + coupon_content
				+ ", coupon_start=" + coupon_start + ", coupon_end=" + coupon_end + ", coupon_isUse=" + coupon_isUse
				+ "]";
	}
	
	
	
	
	
	
}
