package model;

/**
 * The {@link Customer} class is an in-app representation 
 * of the Customer primary table.
 */
public class Customer {
	private int id;
	private String firstname;
	private String lastname;
	private String mobilePhone;
	private String email;
	private String billingAddress;
	private String city;
	
	// Constructors
	
	public Customer() {
	}
	
	public Customer(String firstname, String lastname, String mobilePhone, String email, String billingAddress,
			String city) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.mobilePhone = mobilePhone;
		this.email = email;
		this.billingAddress = billingAddress;
		this.city = city;
	}

	public Customer(int id, String firstname, String lastname, String mobilePhone, String email, String billingAddress,
			String city) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.mobilePhone = mobilePhone;
		this.email = email;
		this.billingAddress = billingAddress;
		this.city = city;
	}

	// Getters and Setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
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

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
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
		return id + ", " + firstname + ", " + lastname + ", " + mobilePhone + ", " + email + ", " + billingAddress + ", " + city;
	}
	
}
