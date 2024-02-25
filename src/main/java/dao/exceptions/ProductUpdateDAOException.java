package dao.exceptions;

/**
 * The {@link ProductUpdateDAOException} class is triggered
 * when there is an error in the SQL UPDATE operation.
 */
public class ProductUpdateDAOException extends BaseDAOException {
	
	private static final long serialVersionUID = 1L;

	public ProductUpdateDAOException() {
		super("SQL Error in Product record update.");
	}
}
