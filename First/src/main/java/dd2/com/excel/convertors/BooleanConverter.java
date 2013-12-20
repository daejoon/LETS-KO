package dd2.com.excel.convertors;

import dd2.com.excel.exceptions.UnparsbleException;

public class BooleanConverter implements Converter<Boolean> {

	private static String trueString  = "TRUE";
	private static String falseString = "FALSE";

	public Boolean fromString(String value) {
		if (value.equalsIgnoreCase(BooleanConverter.trueString))
			return true;
		else if (value.equalsIgnoreCase(BooleanConverter.falseString))
			return false;
		else
			return false;
	}

	public Boolean from(Object input) throws UnparsbleException {
		if (input instanceof String)
			return this.fromString((String) input);
		if (input instanceof Boolean)
			return (Boolean) input;
		throw new UnparsbleException("Unable to convert from " + input + "to Boolean");

	}

}
