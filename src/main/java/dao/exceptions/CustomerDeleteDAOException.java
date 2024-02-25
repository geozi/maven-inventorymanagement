package dao.exceptions;

/**
 * The {@link CustomerDeleteDAOException} class is triggered
 * when there is an error in the SQL DELETE FROM operation.
 */
public class CustomerDeleteDAOException extends BaseDAOException {
	
	private static final long serialVersionUID = 1L;

	public CustomerDeleteDAOException() {
		super("SQL Error in Customer record deletion.");
	}

}
