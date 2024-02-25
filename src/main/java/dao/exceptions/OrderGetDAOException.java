package dao.exceptions;

/**
 * The {@link OrderGetDAOException} is triggered
 * when there is an error in the SQL SELECT FROM WHERE operation.
 */
public class OrderGetDAOException extends BaseDAOException {
	
	private static final long serialVersionUID = 1L;

	public OrderGetDAOException() {
		super("SQL Error in Order record retrieval.");
	}
}
