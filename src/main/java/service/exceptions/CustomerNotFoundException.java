package service.exceptions;

/**
 * The {@link CustomerNotFoundException} class is triggered
 * when the specified Customer record is not found in the database.
 */
public class CustomerNotFoundException extends BaseServiceException {

	private static final long serialVersionUID = 1L;
	
	public CustomerNotFoundException() {
		super("Customer record not found");
	}

}
