package dao.exceptions;

/**
 * The {@link SupplierInsertDAOException} class is triggered
 * when there is an error in the SQL INSERT operation.
 */
public class SupplierInsertDAOException extends BaseDAOException {

	private static final long serialVersionUID = 1L;

	public SupplierInsertDAOException() {
		super("SQL Error in Supplier record addition.");
	}
}
