package dao.exceptions;

/**
 * The {@link CustomerUpdateDAOException} class is triggered
 * when there is an error in the SQL UPDATE operation.
 */
public class CustomerUpdateDAOException extends BaseDAOException {
	
	private static final long serialVersionUID = 1L;

	public CustomerUpdateDAOException() {
		super("SQL Error in Customer record update.");
	}
}
