package dao.exceptions;

/**
 * The {@link ProductGetDAOException} is triggered
 * when there is an error in the SQL SELECT FROM WHERE operation.
 */
public class ProductGetDAOException extends BaseDAOException {
	
	private static final long serialVersionUID = 1L;

	public ProductGetDAOException() {
		super("SQL Error in Product record retrieval.");
	}
	
}
