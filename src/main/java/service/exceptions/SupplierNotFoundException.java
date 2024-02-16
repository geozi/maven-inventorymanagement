package service.exceptions;

public class SupplierNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public SupplierNotFoundException() {
		super("Supplier record not found");
	}
	
}
