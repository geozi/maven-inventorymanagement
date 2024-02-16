package viewcontroller.validation;

import java.util.HashMap;
import java.util.Map;

import service.dto.CustomerDTO;

/**
 * The {@link CustomerInsertValidator} class is a sub-class
 * of the {@link BaseValidator} class. It implements 
 * validation rules for the {@link CustomerInsertForm} class.
 */
public class CustomerInsertValidator extends BaseValidator {
	
	public static Map<String, String> validate(CustomerDTO dto) {
		Map<String, String> errors = new HashMap<>();
		
		String firstname = dto.getFirstname();
		String lastname = dto.getLastname();
		String city = dto.getCity();
		String mobilePhone = dto.getMobilePhone();
		String billingAddress = dto.getBillingAddress();
		String email = dto.getEmail();
		
		// Firstname
		
		if(firstname.isEmpty()) {
			errors.put("Firstname", "is empty");
		} else {
			if(firstname.length() < 3) {
				errors.put("Firstname", "is too short");
			}
			
			if(!firstname.matches("^[a-zA-Z]+$")) {
				errors.put("Firstname", "contains digits");
			}
			
			if(firstname.matches("^.*\\s+.*$")) {
				errors.put("Firstname", "contains whitespaces");
			}
		}
		
		
		// Lastname
		
		if(lastname.isEmpty()) {
			errors.put("Lastname", "is empty");
		} else {
			if(lastname.length() < 3) {
				errors.put("Lastname", "is too short");
			}
			
			if(!lastname.matches("^[a-zA-Z]+$")) {
				errors.put("Lastname", "contains digits");
			}
			
			if(lastname.matches("^.*\\s+.*$")) {
				errors.put("Lastname", "contains whitespaces");
			}
		}
		

		// City
		
		if(city.isEmpty()) {
			errors.put("City", "is empty");
		} else {
			if(city.length() < 3) {
				errors.put("City", "is too short");
			}
			
			if(!city.matches("^[a-zA-Z ]+$")) {
				errors.put("City", "contains invalid characters");
			}
		}
		

		// Email
		
		if(email.isEmpty()) {
			errors.put("Email", "is empty");
		} else {
			if(!email.matches("^(.+)@(.+)\\.(.+)$")) {
				errors.put("Email", "has wrong format");
			}
		}
		
		// Billing Address
		if(billingAddress.isEmpty()) {
			errors.put("Billing Address", "is empty");
		} else {
			if(billingAddress.length() < 5) {
				errors.put("Billing Address", "is too short");
			}
		}
		
		
		// Mobile Phone
		
		if(mobilePhone.isEmpty()) {
			errors.put("Mobile Phone", "is empty");
		} else {
			
			if(mobilePhone.length() < 10) {
				errors.put("Mobile Phone", "is too short");
			}
			
			if(!mobilePhone.matches("^[0-9]+$")) {
				errors.put("Mobile Phone", "contains non-digit characters");
			}
			
			if(mobilePhone.matches("^.*\\s+.*$")) {
				errors.put("Mobile Phone", "contains whitespaces");
			}
		}
		
		return errors;
	}

}
