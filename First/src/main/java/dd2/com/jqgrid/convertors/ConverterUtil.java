package dd2.com.jqgrid.convertors;

import dd2.com.jqgrid.exceptions.JqGridTypeParsingException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kdj on 2013. 12. 24..
 */
public final class ConverterUtil {
    private static Map<String, Converter<?>> map = new HashMap<>();

    static {
        map.put("java.lang.Integer" , new IntegerConverter());
        map.put("java.lang.Long"    , new LongConverter());
        map.put("java.lang.Float"   , new FloatConverter());
        map.put("java.lang.Double"  , new DoubleConverter());
        map.put("java.lang.Boolean" , new BooleanConverter());
        map.put("java.util.Date"    , new DateConverter());
        map.put("java.lang.String"  , new StringConverter());
    }

    public static Object getValue(String typeName, Object convertValue) {
        Converter<?> converter = map.get(typeName);
        Object ret = null;
        try {
            ret = converter.from(convertValue);
        } catch (JqGridTypeParsingException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static Object getValue(Class klass, Object convertValue) {
        return getValue(klass.getName(), convertValue);
    }
}
