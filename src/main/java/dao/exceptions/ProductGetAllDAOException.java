package dao.exceptions;

/**
 * The {@link ProductGetAllDAOException} is triggered
 * when there is an error in the SQL SELECT FROM operation.
 */
public class ProductGetAllDAOException extends BaseDAOException {
	
	private static final long serialVersionUID = 1L;

	public ProductGetAllDAOException() {
		super("SQL Error in retrieving Product records.");
	}
}
