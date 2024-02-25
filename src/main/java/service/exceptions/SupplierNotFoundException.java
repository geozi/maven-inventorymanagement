package service.exceptions;

/**
 * The {@link SupplierNotFoundException} class is triggered
 * when the specified Supplier record is not found in the database.
 */
public class SupplierNotFoundException extends BaseServiceException {

	private static final long serialVersionUID = 1L;
	
	public SupplierNotFoundException() {
		super("Supplier record not found");
	}
	
}
