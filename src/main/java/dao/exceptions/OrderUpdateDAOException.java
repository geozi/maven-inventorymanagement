package dao.exceptions;

/**
 * The {@link OrderUpdateDAOException} class is triggered
 * when there is an error in the SQL UPDATE operation.
 */
public class OrderUpdateDAOException extends BaseDAOException {
	
	private static final long serialVersionUID = 1L;

	public OrderUpdateDAOException() {
		super("SQL Error in Order record update.");
	}

}
