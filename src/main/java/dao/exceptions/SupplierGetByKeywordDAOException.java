package dao.exceptions;

/**
 * The {@link SupplierGetByKeywordDAOException} is triggered
 * when there is an error in the SQL SELECT FROM WHERE operation.
 */
public class SupplierGetByKeywordDAOException extends BaseDAOException {
	
	private static final long serialVersionUID = 1L;

	public SupplierGetByKeywordDAOException() {
		super("SQL Error in retrieving specified Supplier records.");
	}

}
