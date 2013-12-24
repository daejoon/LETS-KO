package dd2.com.jqgrid.convertors;

import dd2.com.jqgrid.exceptions.JqGridParsingException;

public interface Converter<T> {

	T from(Object input) throws JqGridParsingException;
}
