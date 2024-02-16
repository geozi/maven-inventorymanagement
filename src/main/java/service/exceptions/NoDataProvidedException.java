package service.exceptions;

public class NoDataProvidedException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public NoDataProvidedException() {
		super("Input Error: no data found for database request");
	}

}
