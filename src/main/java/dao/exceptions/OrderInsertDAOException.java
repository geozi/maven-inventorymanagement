package dao.exceptions;

/**
 * The {@link OrderInsertDAOException} class is triggered
 * when there is an error in the SQL INSERT operation.
 */
public class OrderInsertDAOException extends BaseDAOException {

	private static final long serialVersionUID = 1L;

	public OrderInsertDAOException() {
		super("SQL Error in Order record addition.");
	}
	
}
