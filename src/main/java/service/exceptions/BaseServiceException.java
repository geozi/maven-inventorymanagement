package service.exceptions;

/**
 * The {@link BaseServiceException} class is the
 * super class and the main triggering point
 * for all Service-layer sub-classes.
 */
public class BaseServiceException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public BaseServiceException() {}
	
	public BaseServiceException(String message) {
		super(message);
	}
}
