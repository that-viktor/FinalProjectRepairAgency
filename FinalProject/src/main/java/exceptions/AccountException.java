package exceptions;

public class AccountException extends Exception {
	private String message;

	public AccountException() {
		super();
	}

	public AccountException(String message) {
		super(message);
		this.message = message;
	}
}
