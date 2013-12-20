package dd2.com.excel.convertors;

import dd2.com.excel.exceptions.UnparsbleException;

public interface Converter<T> {

	T from(Object input) throws UnparsbleException;
}
