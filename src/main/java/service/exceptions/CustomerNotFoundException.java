package service.exceptions;

public class CustomerNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public CustomerNotFoundException() {
		super("Customer record not found");
	}

}
