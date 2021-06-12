package exceptions;

public class StatusException extends Exception {
	private String message;

	public StatusException() {
		super();
	}

	public StatusException(String message) {
		super(message);
		this.message = message;
	}
}
