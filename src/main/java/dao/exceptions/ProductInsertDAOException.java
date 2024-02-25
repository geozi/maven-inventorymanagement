package dao.exceptions;

/**
 * The {@link ProductInsertDAOException} class is triggered
 * when there is an error in the SQL INSERT operation.
 */
public class ProductInsertDAOException extends BaseDAOException{

	private static final long serialVersionUID = 1L;

	public ProductInsertDAOException() {
		super("SQL Error in Product record addition.");
	}
	
}
