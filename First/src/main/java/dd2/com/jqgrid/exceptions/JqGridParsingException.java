package dd2.com.jqgrid.exceptions;

@SuppressWarnings("serial")
public class JqGridParsingException extends Exception {

	public JqGridParsingException(String msg) {
		super(msg);
	}

	public JqGridParsingException(Exception e) {
		super(e);
	}

}
