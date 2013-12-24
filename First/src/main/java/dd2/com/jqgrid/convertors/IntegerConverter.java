package dd2.com.jqgrid.convertors;

import dd2.com.jqgrid.exceptions.JqGridParsingException;

public class IntegerConverter implements Converter<Integer> {

	public Integer fromString(String value) {
		if (value == null || value.length() == 0)
			return 0;
		return Integer.parseInt(value);
	}

	public Integer from(Object input) throws JqGridParsingException {
		if (input instanceof String)
			return this.fromString((String) input);
		return null;
	}

}
