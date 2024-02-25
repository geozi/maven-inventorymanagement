package dao.exceptions;

/**
 * The {@link CustomerInsertDAOException} class is triggered
 * when there is an error in the SQL INSERT operation.
 */
public class CustomerInsertDAOException extends BaseDAOException {

	private static final long serialVersionUID = 1L;

	public CustomerInsertDAOException() {
		super("SQL Error in Customer record addition.");
	}
}
