package viewcontroller.validation;

import java.util.HashMap;
import java.util.Map;

import service.dto.SupplierDTO;

/**
 * The {@link SupplierInsertValidator} class is a sub-class
 * of the {@link BaseValidator} class. It implements 
 * validation rules for the {@link SupplierInsertForm} class.
 */
public class SupplierInsertValidator extends BaseValidator {

	public static Map<String, String> validate(SupplierDTO dto){
		Map<String, String> errors = new HashMap<>();
		
		String name = dto.getName();
		String mobilePhone = dto.getMobilePhone();
		String email = dto.getEmail();
		String address = dto.getAddress();
		String city = dto.getCity();
		
		// Name
		
		if(name.isEmpty()) {
			errors.put("Name", "is empty");
		} else {
			if(name.length() < 2) {
				errors.put("Name", "is too short");
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
		
		// Email
		
		if(email.isEmpty()) {
			errors.put("Email", "is empty");
		} else {
			if(!email.matches("^(.+)@(.+)\\.(.+)$")) {
				errors.put("Email", "has wrong format");
			}
		}
		
		// Address
		
		if(address.isEmpty()) {
			errors.put("Address", "is empty");
		} else {
			if(address.length() < 5) {
				errors.put("Address", "is too short");
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
		
		return errors;
	}
}
