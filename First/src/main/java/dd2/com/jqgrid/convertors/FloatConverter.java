package dd2.com.jqgrid.convertors;

import dd2.com.jqgrid.exceptions.JqGridParsingException;

public class FloatConverter implements Converter<Float> {

	public Float fromString(String value) {
		if (value == null || value.length() == 0)
			return 0F;
		if (value.indexOf(",") != -1)
			return Float.parseFloat(value.replace(",", "."));
		else
			return Float.parseFloat(value);
	}

	public Float from(Object input) throws JqGridParsingException {
		if (input instanceof String)
			return this.fromString((String) input);
		return null;
	}

}
