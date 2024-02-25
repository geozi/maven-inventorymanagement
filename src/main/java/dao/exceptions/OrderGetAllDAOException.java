package dao.exceptions;

/**
 * The {@link OrderGetAllDAOException} is triggered
 * when there is an error in the SQL SELECT FROM operation.
 */
public class OrderGetAllDAOException extends BaseDAOException {
	
	private static final long serialVersionUID = 1L;

	public OrderGetAllDAOException() {
		super("SQL Error in retrieving Order records.");
	}

}
