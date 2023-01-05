package vo;


public class cartBean {

	private int cart_price;
	private int cart_count;
	private String cart_size;
	private String cart_color;
	private String cart_product_name;
	
	
	//-------setter, getter--------
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
	public String getCart_product_name() {
		return cart_product_name;
	}
	public void setCart_product_name(String cart_product_name) {
		this.cart_product_name = cart_product_name;
	}
	//-----------------toString--------------------
	@Override
	public String toString() {
		return "cartBean [cart_price=" + cart_price + ", cart_count=" + cart_count + ", cart_size=" + cart_size
				+ ", cart_color=" + cart_color + ", cart_product_name=" + cart_product_name + "]";
	}



	
	






}
