package model;

/**
 * The {@link Order} class is an in-app representation 
 * of the Order primary table.
 */
public class Order {
	private int id;
	private int customerID;
	private int quantity;
	private double price;
	private String shippingAddress;
	private String city;
	
	// Constructors 
	
	public Order() {
	}
	
	public Order(int customerID, int quantity, double price, String shippingAddress, String city) {
		this.customerID = customerID;
		this.quantity = quantity;
		this.price = price;
		this.shippingAddress = shippingAddress;
		this.city = city;
	}

	public Order(int id, int customerID, int quantity, double price, String shippingAddress, String city) {
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


	public int getCustomerID() {
		return customerID;
	}


	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public String getShippingAddress() {
		return shippingAddress;
	}


	public void setShipping_address(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}
	
	// Object state display
	
	@Override
	public String toString() {
		return id + ", " + customerID + ", " + quantity + ", " + price + ", " + shippingAddress + ", " + city;
	}

}
