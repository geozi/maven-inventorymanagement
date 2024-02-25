package dao.exceptions;

/**
 * The {@link ProductGetByKeywordDAOException} is triggered
 * when there is an error in the SQL SELECT FROM WHERE operation.
 */
public class ProductGetByKeywordDAOException extends BaseDAOException {
	
	private static final long serialVersionUID = 1L;

	public ProductGetByKeywordDAOException() {
		super("SQL Error in retrieving specified Product records.");
	}
}
