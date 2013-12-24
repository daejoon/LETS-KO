package dd2.com.jqgrid.convertors;

import dd2.com.jqgrid.exceptions.JqGridParsingException;

public class LongConverter implements Converter<Long> {

	public Long fromString(String value) {
		if (value == null || value.length() == 0)
			return 0L;
		return Long.parseLong(value);
	}

	public Long from(Object input) throws JqGridParsingException {
		if (input instanceof String)
			return this.fromString((String) input);
		return null;
	}

}
