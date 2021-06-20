package exceptions;
/**
 * DAOException class is an exception class that throws all business login exceptions on the DAO layer
 * @author Viktor
 *
 */
public class DAOException extends Exception {
	private String message;

	public DAOException() {
		super();
	}

	public DAOException(String message) {
		super(message);
		this.message = message;
	}
}
