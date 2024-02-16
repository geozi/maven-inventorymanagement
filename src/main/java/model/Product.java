package model;

/**
 * The {@link Product} class is an in-app representation 
 * of the Product primary table.
 */
public class Product {
	private int id;
	private String name;
	private String description;
	private double unitPrice;
	private int quantity;
	private int supplierID;
	
	// Constructors
	
	public Product() {
	}
	
	public Product(String name, String description, double unitPrice, int quantity, int supplierID) {
		this.name = name;
		this.description = description;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.supplierID = supplierID;
	}

	public Product(int id, String name, String description, double unitPrice, int quantity, int supplierID) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.supplierID = supplierID;
	}
	

	// Getters and Setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(int supplierID) {
		this.supplierID = supplierID;
	}
	
	// Object state display
	
	@Override
	public String toString() {
		return id + ", " + name + ", " + description + ", " + unitPrice + ", " + quantity +", " + supplierID;
	}
	
}
