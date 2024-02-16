package service.exceptions;

public class OrderNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public OrderNotFoundException() {
		super("Order record not found");
	}
	
}
