package vo;

public class WishBean {
	private int wish_idx;
	private int member_idx;
	private int product_idx;
	
	
	public int getWish_idx() {
		return wish_idx;
	}
	public void setWish_idx(int wish_idx) {
		this.wish_idx = wish_idx;
	}
	public int getMember_idx() {
		return member_idx;
	}
	public void setMember_idx(int member_idx) {
		this.member_idx = member_idx;
	}
	public int getProduct_idx() {
		return product_idx;
	}
	public void setProduct_idx(int product_idx) {
		this.product_idx = product_idx;
	}
	
	@Override
	public String toString() {
		return "WishBean [wish_idx=" + wish_idx + ", member_idx=" + member_idx + ", product_idx=" + product_idx + "]";
	}
	
	
}
