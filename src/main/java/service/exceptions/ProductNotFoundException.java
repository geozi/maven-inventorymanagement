package service.exceptions;

/**
 * The {@link ProductNotFoundException} class is triggered
 * when the specified Product record is not found in the database.
 */
public class ProductNotFoundException extends BaseServiceException {

	private static final long serialVersionUID = 1L;
	
	public ProductNotFoundException() {
		super("Product record not found");
	}
}
