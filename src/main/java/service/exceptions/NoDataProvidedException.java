package service.exceptions;

/**
 * The {@link NoDataProvidedException} class is triggered
 * when users provide no data.
 */
public class NoDataProvidedException extends BaseServiceException {

	private static final long serialVersionUID = 1L;
	
	public NoDataProvidedException() {
		super("Input Error: no data found for database request");
	}

}
