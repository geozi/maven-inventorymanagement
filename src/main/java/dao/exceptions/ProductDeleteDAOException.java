package dao.exceptions;

/**
 * The {@link ProductDeleteDAOException} class is triggered
 * when there is an error in the SQL DELETE FROM operation.
 */
public class ProductDeleteDAOException extends BaseDAOException {
	
	private static final long serialVersionUID = 1L;

	public ProductDeleteDAOException() {
		super("SQL Error in Product record deletion.");
	}

}
