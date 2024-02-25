package dao.exceptions;

/**
 * The {@link SupplierDeleteDAOException} class is triggered
 * when there is an error in the SQL DELETE FROM operation.
 */
public class SupplierDeleteDAOException extends BaseDAOException{
	
	private static final long serialVersionUID = 1L;

	public SupplierDeleteDAOException() {
		super("SQL Error in Supplier record deletion.");
	}

}
