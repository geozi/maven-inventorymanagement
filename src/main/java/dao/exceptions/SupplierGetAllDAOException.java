package dao.exceptions;

/**
 * The {@link SupplierGetAllDAOException} is triggered
 * when there is an error in the SQL SELECT FROM operation.
 */
public class SupplierGetAllDAOException extends BaseDAOException {
	
	private static final long serialVersionUID = 1L;

	public SupplierGetAllDAOException() {
		super("SQL Error in retrieving Supplier records.");
	}
	
}
