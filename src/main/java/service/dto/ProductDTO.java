package service.dto;

/**
 * The {@link ProductDTO} class is the DTO equivalent of 
 * the Product model class.
 */
public class ProductDTO extends BaseDTO{
	
	private int id;
	private String name;
	private String description;
	private String unitPrice;
	private String quantity;
	private String supplierID;
	
	// Constructors
	
	public ProductDTO() {
	}

	public ProductDTO(String name, String description, String unitPrice, String quantity, String supplierID) {
		this.name = name;
		this.description = description;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.supplierID = supplierID;
	}
	
	public ProductDTO(int id, String name, String description, String unitPrice, String quantity, String supplierID) {
		super();
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

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(String supplierID) {
		this.supplierID = supplierID;
	}

	
}
