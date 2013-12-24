package dd2.com.jqgrid.convertors;

import dd2.com.jqgrid.exceptions.JqGridTypeParsingException;

public class BooleanConverter implements Converter<Boolean> {

	private static String trueString  = "true";
	private static String falseString = "false";

	public Boolean fromString(String value) {
		if (value.equalsIgnoreCase(BooleanConverter.trueString))
			return true;
		else if (value.equalsIgnoreCase(BooleanConverter.falseString))
			return false;
		else
			return false;
	}

	public Boolean from(Object input) throws JqGridTypeParsingException {
		if (input instanceof String)
			return this.fromString((String) input);
		if (input instanceof Boolean)
			return (Boolean) input;
		throw new JqGridTypeParsingException("Unable to convert from " + input + "to Boolean");
	}
}
