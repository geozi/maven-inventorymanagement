package viewcontroller.validation;

import java.util.HashMap;
import java.util.Map;
import service.dto.ProductDTO;

/**
 * The {@link ProductInsertValidator} class is a sub-class
 * of the {@link BaseValidator} class. It implements 
 * validation rules for the {@link ProductInsertForm} class.
 */
public class ProductInsertValidator extends BaseValidator {
	public static Map<String, String> validate(ProductDTO dto){
		Map<String, String> errors = new HashMap<>();
		
		String name = String.valueOf(dto.getName());
		String description = String.valueOf(dto.getDescription());
		String unitPriceAsString = String.valueOf(dto.getUnitPrice());
		String quantityAsString = String.valueOf(dto.getQuantity());
		String supplierIdAsString =String.valueOf(dto.getSupplierID());
		
		// Name
		
		if(name.isEmpty()) {
			errors.put("Name", "is empty");
		}
		
		// Description
		
		if(description.isEmpty()) {
			errors.put("Description", "is empty");
		}
		
		// Unit Price
		
		if(unitPriceAsString.isEmpty()) {
			errors.put("Unit Price", "is empty");
		} else {
			
			if(!unitPriceAsString.matches("[0-9]{1,10}(\\.[0-9]*)?")) {
				errors.put("Unit Price", "has wrong format");
			} 
			
			if(unitPriceAsString.matches("^.*\\s+.*$")) {
				errors.put("Unit Price", "contains whitespaces");
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
		
		// SupplierID
		
		if(supplierIdAsString.isEmpty()) {
			errors.put("SupplierID", "is empty");
		} else {
			
			if(!supplierIdAsString.matches("^[0-9]+$")) {
				errors.put("SupplierID", "contains non-digit characters");
			} 
			
			if(supplierIdAsString.matches("^.*\\s+.*$")) {
				errors.put("SupplierID", "contains whitespaces");
			}
		}
		
		return errors;
	}

}
