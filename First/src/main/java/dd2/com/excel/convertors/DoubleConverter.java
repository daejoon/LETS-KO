package dd2.com.excel.convertors;

import dd2.com.excel.exceptions.UnparsbleException;

public class DoubleConverter implements Converter<Double> {

	public Double fromString(String value) {
		if(value==null || value.equalsIgnoreCase("")) return 0.0;
		return new Double(value);
	}

	public Double from(Object input) throws UnparsbleException {
		if (input instanceof String){
			return this.fromString((String) input);
		}
		if (input instanceof Double){
			
			return (Double) input;
		}

		throw new UnparsbleException("Unable to convert from " + input + "to Boolean");

	}

}
