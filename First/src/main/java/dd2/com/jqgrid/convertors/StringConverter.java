package dd2.com.jqgrid.convertors;

import dd2.com.jqgrid.exceptions.JqGridParsingException;

public class StringConverter implements Converter<String> {

	public String fromString(String value) {
		return value;
	}

	public String from(Object input) throws JqGridParsingException {
		if (input instanceof String)
			return this.fromString((String) input);
		return null;
	}

}
