package dao.exceptions;

/**
 * The {@link SupplierUpdateDAOException} class is triggered
 * when there is an error in the SQL UPDATE operation.
 */
public class SupplierUpdateDAOException extends BaseDAOException {
	
	private static final long serialVersionUID = 1L;

	public SupplierUpdateDAOException() {
		super("SQL Error in Supplier record update.");
	}
}
