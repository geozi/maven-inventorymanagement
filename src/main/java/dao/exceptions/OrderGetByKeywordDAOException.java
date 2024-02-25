package dao.exceptions;

/**
 * The {@link OrderGetByKeywordDAOException} is triggered
 * when there is an error in the SQL SELECT FROM WHERE operation.
 */
public class OrderGetByKeywordDAOException extends BaseDAOException {
	
	private static final long serialVersionUID = 1L;

	public OrderGetByKeywordDAOException() {
		super("SQL Error in retrieving specified Order records.");
	}

}
