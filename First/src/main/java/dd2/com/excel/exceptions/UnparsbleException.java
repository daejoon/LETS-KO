package dd2.com.excel.exceptions;

@SuppressWarnings("serial")
public class UnparsbleException extends Exception {

	public UnparsbleException(String msg) {
		super(msg);
	}

	public UnparsbleException(Exception e) {
		super(e);
	}

}
