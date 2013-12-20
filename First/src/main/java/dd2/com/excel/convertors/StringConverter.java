package dd2.com.excel.convertors;

import dd2.com.excel.exceptions.UnparsbleException;

public class StringConverter implements Converter<String> {

	public String fromString(String value) {
		return value;
	}

	public String from(Object input) throws UnparsbleException {
		if (input instanceof String)
			return this.fromString((String) input);
		return null;
	}

}
