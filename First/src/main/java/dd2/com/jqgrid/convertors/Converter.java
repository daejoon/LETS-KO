package dd2.com.jqgrid.convertors;

import dd2.com.jqgrid.exceptions.JqGridTypeParsingException;

public interface Converter<T> {

	T from(Object input) throws JqGridTypeParsingException;
}
