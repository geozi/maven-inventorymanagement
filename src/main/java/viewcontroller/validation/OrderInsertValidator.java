package viewcontroller.validation;

import java.util.HashMap;
import java.util.Map;

import service.dto.OrderDTO;

/**
 * The {@link OrderInsertValidator} class is a sub-class
 * of the {@link BaseValidator} class. It implements 
 * validation rules for the {@link OrderInsertForm} class.
 */
public class OrderInsertValidator extends BaseValidator {
	public static Map<String, String> validate(OrderDTO dto){
		Map<String, String> errors = new HashMap<>();
		
		String customerIdAsString = String.valueOf(dto.getCustomerID());
		String quantityAsString = String.valueOf(dto.getQuantity());
		String priceAsString = String.valueOf(dto.getPrice());
		String shippingAddress = dto.getShippingAddress();
		String city = dto.getCity();
		
		// CustomerID
		
		if(customerIdAsString.isEmpty()) {
			errors.put("CustomerID", "is empty");
		} else {
			
			if(!customerIdAsString.matches("^[0-9]+$")) {
				errors.put("CustomerID", "contains non-digit characters");
			} 
			
			if(customerIdAsString.matches("^.*\\s+.*$")) {
				errors.put("CustomerID", "contains whitespaces");
			}
		}
		
		// Quantity
		
		if(quantityAsString.isEmpty()) {
			errors.put("Quantity", "is empty");
		} else {
			
			if(!quantityAsString.matches("^[0-9]+$")) {
				errors.put("Quantity", "contains non-digit characters");
			} 
			
			if(quantityAsString.matches("^.*\\s+.*$")) {
				errors.put("Quantity", "contains whitespaces");
			}
		}
		
		// Price
		
		if(priceAsString.isEmpty()) {
			errors.put("Price", "is empty");
		} else {
			
			if(!priceAsString.matches("[0-9]{1,10}(\\.[0-9]*)?")) {
				errors.put("Price", "has wrong format");
			} 
			
			if(priceAsString.matches("^.*\\s+.*$")) {
				errors.put("Price", "contains whitespaces");
			}
		}
		
		// Shipping Address
		
		if(shippingAddress.isEmpty()) {
			errors.put("Shipping Address", "is empty");
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
