package dao.exceptions;

/**
 * The {@link CustomerGetByKeywordDAOException} is triggered
 * when there is an error in the SQL SELECT FROM WHERE operation.
 */
public class CustomerGetByKeywordDAOException extends BaseDAOException{
	
	private static final long serialVersionUID = 1L;

	public CustomerGetByKeywordDAOException() {
		super("SQL Error in retrieving specified Customer records.");
	}

}
