package dao.exceptions;

/**
 * The {@link BaseDAOException} class is the
 * super class and the main triggering point
 * for all DAO-layer sub-classes.
 */
public class BaseDAOException extends Exception{
	private static final long serialVersionUID = 1L;

	public BaseDAOException(String message) {
		super(message);
	}
}
