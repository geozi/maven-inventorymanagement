package dao.exceptions;

/**
 * The {@link InvalidKeywordDAOException} is triggered
 * when there is an error after an incorrectly-formed SQL query.
 */
public class InvalidKeywordDAOException extends BaseDAOException {
	
	private static final long serialVersionUID = 1L;

	public InvalidKeywordDAOException() {
		super("SQL Error in forming query statement");
	}

}
