package service.dto;

/**
 * The {@link OrderDTO} class is the DTO equivalent of 
 * the Order model class.
 */
public class OrderDTO extends BaseDTO {
	
	private int id;
	private String customerID;
	private String quantity;
	private String price;
	private String shippingAddress;
	private String city;
	
	// Constructors
	
	public OrderDTO() {
	}

	public OrderDTO(String customerID, String quantity, String price, String shippingAddress, String city) {
		this.customerID = customerID;
		this.quantity = quantity;
		this.price = price;
		this.shippingAddress = shippingAddress;
		this.city = city;
	}
	
	public OrderDTO(int id, String customerID, String quantity, String price, String shippingAddress, String city) {
		this.id = id;
		this.customerID = customerID;
		this.quantity = quantity;
		this.price = price;
		this.shippingAddress = shippingAddress;
		this.city = city;
	}

	// Getters and Setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	// Object State Display
	
	@Override
	public String toString() {
		return "OrderDTO [customerID=" + customerID + ", quantity=" + quantity + ", price=" + price
				+ ", shippingAddress=" + shippingAddress + ", city=" + city + "]";
	}
}
