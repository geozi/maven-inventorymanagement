package dao.exceptions;

/**
 * The {@link SupplierGetDAOException} is triggered
 * when there is an error in the SQL SELECT FROM WHERE operation.
 */
public class SupplierGetDAOException extends BaseDAOException {
	
	private static final long serialVersionUID = 1L;

	public SupplierGetDAOException() {
		super("SQL Error in Supplier record retrieval.");
	}
}
