package service.dto;

/**
 * The {@link SupplierDTO} class is the DTO equivalent of 
 * the Supplier model class.
 */
public class SupplierDTO extends BaseDTO{
	
	private int id;
	private String name;
	private String mobilePhone;
	private String email;
	private String address;
	private String city;
	
	
	// Constructors
	
	public SupplierDTO() {
	}

	public SupplierDTO(String name, String mobilePhone, String email, String address, String city) {
		this.name = name;
		this.mobilePhone = mobilePhone;
		this.email = email;
		this.address = address;
		this.city = city;
	}
	
	public SupplierDTO(int id, String name, String mobilePhone, String email, String address, String city) {
		super();
		this.id = id;
		this.name = name;
		this.mobilePhone = mobilePhone;
		this.email = email;
		this.address = address;
		this.city = city;
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

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
		return "SupplierDTO [name=" + name + ", mobilePhone=" + mobilePhone + ", email=" + email + ", address="
				+ address + ", city=" + city + "]";
	}
	
	
	
	
	

}
