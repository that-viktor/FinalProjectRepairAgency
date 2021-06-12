package exceptions;

public class ReceiptException extends Exception {
	private String message;

	public ReceiptException() {
		super();
	}

	public ReceiptException(String message) {
		super(message);
		this.message = message;
	}
}
