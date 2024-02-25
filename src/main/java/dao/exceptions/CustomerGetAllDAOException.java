package dao.exceptions;

/**
 * The {@link CustomerGetAllDAOException} is triggered
 * when there is an error in the SQL SELECT FROM operation.
 */
public class CustomerGetAllDAOException extends BaseDAOException{
	
	private static final long serialVersionUID = 1L;

	public CustomerGetAllDAOException() {
		super("SQL Error in retrieving Customer records.");
	}

}
