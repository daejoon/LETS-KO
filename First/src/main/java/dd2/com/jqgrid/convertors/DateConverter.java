package dd2.com.jqgrid.convertors;

import dd2.com.jqgrid.exceptions.JqGridParsingException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter implements Converter<Date> {

	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public DateConverter() {
	}

	public DateConverter(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}

	public DateConverter(String dateFormat) {
		this.dateFormat = new SimpleDateFormat(dateFormat);
	}

	public DateFormat getDateFormat() {
		return this.dateFormat;
	}

	public void setDateFormat(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}

	public Date from(Object value) throws JqGridParsingException {
		try {
			if (value instanceof String)
				return this.dateFormat.parse((String)value);
		} catch (final ParseException e) {
			throw new JqGridParsingException(e);
		}
		return null;
	}

}
