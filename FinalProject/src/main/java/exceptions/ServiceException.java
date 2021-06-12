package exceptions;

public class ServiceException extends Exception {
	private String message;

	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(message);
		this.message = message;
	}
}
