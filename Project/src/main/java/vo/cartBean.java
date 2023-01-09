package vo;


public class cartBean {
	
	private int member_idx; 
	private int product_idx; 
	private int cart_idx; 
	private int cart_price;//hidden
	private int cart_discount; //hidden
	private int cart_order_price; //hidden
	private int cart_count; //hidden
	private String cart_size; //select
	private String cart_color; //select
	private String cart_product_name; //hidden
	private String cart_product_image; //hidden
	
	
	//-------setter, getter--------

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
	public int getCart_idx() {
		return cart_idx;
	}
	public void setCart_idx(int cart_idx) {
		this.cart_idx = cart_idx;
	}
	public String getCart_color() {
		return cart_color;
	}
	public void setCart_color(String cart_color) {
		this.cart_color = cart_color;
	}
	public String getCart_size() {
		return cart_size;
	}
	public void setCart_size(String cart_size) {
		this.cart_size = cart_size;
	}
	public int getCart_count() {
		return cart_count;
	}
	public void setCart_count(int cart_count) {
		this.cart_count = cart_count;
	}
	
	public int getCart_price() {
		return cart_price;
	}
	public void setCart_price(int cart_price) {
		this.cart_price = cart_price;
	}
	public int getCart_discount() {
		return cart_discount;
	}
	public void setCart_discount(int cart_discount_price) {
		this.cart_discount = cart_discount_price;
	}
	public int getCart_order_price() {
		return cart_order_price;
	}
	public void setCart_order_price(int cart_order_price) {
		this.cart_order_price = cart_order_price;
	}
	public String getCart_product_name() {
		return cart_product_name;
	}
	public void setCart_product_name(String cart_product_name) {
		this.cart_product_name = cart_product_name;
	}
	public String getCart_product_image() {
		return cart_product_image;
	}
	public void setCart_product_image(String cart_product_image) {
		this.cart_product_image = cart_product_image;
	}
	//-----------------toString--------------------
	@Override
	public String toString() {
		return "cartBean [member_idx=" + member_idx + ", product_idx=" + product_idx + ", cart_idx=" + cart_idx
				+ ", cart_price=" + cart_price + ", cart_discount=" + cart_discount + ", cart_order_price="
				+ cart_order_price + ", cart_count=" + cart_count + ", cart_size=" + cart_size + ", cart_color="
				+ cart_color + ", cart_product_name=" + cart_product_name + ", cart_product_image=" + cart_product_image
				+ "]";
	}





	
	






}
