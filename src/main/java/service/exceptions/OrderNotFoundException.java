package service.exceptions;

/**
 * The {@link OrderNotFoundException} class is triggered
 * when the specified Order record is not found in the database.
 */
public class OrderNotFoundException extends BaseServiceException{

	private static final long serialVersionUID = 1L;
	
	public OrderNotFoundException() {
		super("Order record not found");
	}
}
