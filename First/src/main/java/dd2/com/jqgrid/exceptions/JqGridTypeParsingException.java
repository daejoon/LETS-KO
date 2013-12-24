package dd2.com.jqgrid.exceptions;

@SuppressWarnings("serial")
public class JqGridTypeParsingException extends Exception {

	public JqGridTypeParsingException(String msg) {
		super(msg);
	}

	public JqGridTypeParsingException(Exception e) {
		super(e);
	}

}
