package dao.exceptions;

/**
 * The {@link CustomerGetDAOException} is triggered
 * when there is an error in the SQL SELECT FROM WHERE operation.
 */
public class CustomerGetDAOException extends BaseDAOException {
	
	private static final long serialVersionUID = 1L;

	public CustomerGetDAOException() {
		super("SQL Error in Customer record retrieval.");
	}

}
