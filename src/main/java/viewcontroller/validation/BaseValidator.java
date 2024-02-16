package viewcontroller.validation;

import java.util.HashMap;
import java.util.Map;
import service.dto.BaseDTO;

/**
 * The {@link BaseValidator} class is an abstract class
 * offering common methods for all its sub-classes.
 */
public abstract class BaseValidator {
	
	public static Map<String, String> validate(BaseDTO dto){
		return new HashMap<String,String>();
	}
	
	public static String createErrorMessage(String... params) {
		StringBuilder messageBuilder = new StringBuilder();
		for (String param : params ) {
			if(!param.isEmpty()) {
				messageBuilder.append(param + "\n");
			}
		}
		return messageBuilder.toString();
	}
}
