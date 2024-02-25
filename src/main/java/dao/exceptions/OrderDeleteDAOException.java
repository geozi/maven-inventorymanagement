package dao.exceptions;

/**
 * The {@link OrderDeleteDAOException} class is triggered
 * when there is an error in the SQL DELETE FROM operation.
 */
public class OrderDeleteDAOException extends BaseDAOException {
	
	private static final long serialVersionUID = 1L;

	public OrderDeleteDAOException() {
		super("SQL Error in Order record deletion.");
	}
	
}
