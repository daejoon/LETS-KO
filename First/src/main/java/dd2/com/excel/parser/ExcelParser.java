package dd2.com.excel.parser;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import dd2.com.excel.convertors.Converter;
import dd2.com.excel.exceptions.UnparsbleException;

/**
 * @Class Name  : ExcelParser.java
 * @Description : xls, xlsx 둘다 지원하게 수정
 * @author KDJ
 * @since 2012. 9. 20.
 * @version 1.0
 */

public class ExcelParser<T> {
	private final static Logger logger = Logger.getLogger(ExcelParser.class);

	private int skipNoOfRows = 0;

    @SuppressWarnings("rawtypes")
	private final Map<String, Converter> converterMap = new HashMap<String, Converter>();

	public ExcelParser(int skipNoOfRows) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		this.skipNoOfRows = skipNoOfRows;
		this.init();
	}

	@SuppressWarnings("rawtypes")
	public void registerConverter(String type, Converter objInstance) throws InstantiationException, IllegalAccessException {
		this.converterMap.put(type, objInstance);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void init() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Class<Converter> obj = (Class<Converter>) Class.forName("dd2.excel.convertors.IntegerConverter");
		Converter objInstance = obj.newInstance();
		this.converterMap.put("java.lang.Integer", objInstance);

		obj = (Class<Converter>) Class.forName("dd2.excel.convertors.FloatConverter");
		objInstance = obj.newInstance();
		this.converterMap.put("java.lang.Float", objInstance);

		obj = (Class<Converter>) Class.forName("dd2.excel.convertors.BooleanConverter");
		objInstance = obj.newInstance();
		this.converterMap.put("java.lang.Boolean", objInstance);

		obj = (Class<Converter>) Class.forName("dd2.excel.convertors.DateConverter");
		objInstance = obj.newInstance();
		this.converterMap.put("java.util.Date", objInstance);

		obj = (Class<Converter>) Class.forName("dd2.excel.convertors.StringConverter");
		objInstance = obj.newInstance();
		this.converterMap.put("java.lang.String", objInstance);

		obj = (Class<Converter>) Class.forName("dd2.excel.convertors.DoubleConverter");
		objInstance = obj.newInstance();
		this.converterMap.put("java.lang.Double", objInstance);
	}

	public List<T> getObjects(Sheet sheet, Class<T> className, Map<Integer, String> map) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException, UnparsbleException {
		return this.getObjects(sheet, className, map, null);
	}
	
	public List<T> getObjects(Sheet sheet, Class<T> className, Map<Integer, String> map,Map<String,Object> replMap) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException, UnparsbleException {
		final List<T> listObjects = new ArrayList<T>();
		for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
			if (i < this.skipNoOfRows) continue;
			final T obj = this.createObject(className, sheet.getRow(i), map, replMap);
			listObjects.add(obj);
		}
		return listObjects;
	}
	
	@SuppressWarnings("unchecked")
	private T createObject(Class<T> className, Row row, Map<Integer, String> map,Map<String,Object> replMap) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException, UnparsbleException {

		final Class<T> obj = (Class<T>) Class.forName(className.getName());
		final T objInstance = obj.newInstance();
		if (row == null) return null;
		int cellsOfRow = map.size();//row.getLastCellNum();
		for (int i = 0; i < cellsOfRow; i++) {
			final String propertyName = map.get(new Integer(i + 1));
			final Cell cell = row.getCell(i);
			if (cell == null){
				//빈값이지만 일괄적용 컬럼일경우
				if(replMap!=null && replMap.containsKey(propertyName)){
					PropertyUtils.setSimpleProperty(objInstance, propertyName, replMap.get(propertyName));
				}
				continue;
			}
			if (propertyName == null || propertyName.trim().equalsIgnoreCase("")) {
				continue;
			}
			Object value = null;
			final Object cellValue = this.getCellValue(cell, propertyName, replMap);
			if (cellValue == null) continue;
			final PropertyDescriptor descriptor = PropertyUtils.getPropertyDescriptor(objInstance, propertyName);
			if (descriptor == null) throw new UnparsbleException("Check if property" + propertyName + "exists");

			final Class<?> type = descriptor.getPropertyType();
			final Converter<?> converter = this.converterMap.get(type.getName());
			if (converter == null) throw new UnparsbleException("No Converters for" + type.getName() + "exists");

			try {
				value = converter.from(cellValue);
			} catch (final Exception e) {
				logger.error(e);
				value = null;
			}
			PropertyUtils.setSimpleProperty(objInstance, propertyName, value);
		}
		return objInstance;
	}
	
	private Object getCellValue(Cell cell, String propName, Map<String,Object> replMap) {
		if (cell == null) return null;
		//일괄적용 우선처리
		if(replMap!=null && replMap.containsKey(propName)){
			return replMap.get(propName);
		}
		if (cell.getCellType() == Cell.CELL_TYPE_BLANK) return "";
		if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			cell.setCellType(Cell.CELL_TYPE_STRING);
			return cell.getRichStringCellValue().getString();
		}
		if (cell.getCellType() == Cell.CELL_TYPE_STRING) return cell.getStringCellValue().replaceAll("'", "");
		return null;
	}
	
    public List<Map<String, Object>> getObjects2(Sheet sheet, Map<Integer, String> map,Map<String,Object> replMap) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException, UnparsbleException {
        final List<Map<String, Object>> listObjects = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
            if (i < this.skipNoOfRows) continue;
            final Map<String, Object> obj = this.createObject2(sheet.getRow(i), map, replMap);
            listObjects.add(obj);
        }
        return listObjects;
    }
    
    private Map<String, Object> createObject2(Row row, Map<Integer, String> map,Map<String,Object> replMap) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException, UnparsbleException {
        Map<String, Object> obj = new HashMap<String, Object>();
        if (row == null) return null;
        int cellsOfRow = row.getLastCellNum();
        for (int i = 0; i < cellsOfRow; i++) {
            final String propertyName = map.get(new Integer(i + 1));
            final Cell cell = row.getCell(i);
            if (cell == null){
                //빈값이지만 일괄적용 컬럼일경우
                if(replMap!=null && replMap.containsKey(propertyName)){
                    PropertyUtils.setSimpleProperty(obj, propertyName, replMap.get(propertyName));
                }
                continue;
            }
            if (propertyName == null || propertyName.trim().equalsIgnoreCase("")) {
                continue;
            }
            Object value = null;
            final Object cellValue = this.getCellValue(cell, propertyName, replMap);
            if (cellValue == null) continue;
            final PropertyDescriptor descriptor = PropertyUtils.getPropertyDescriptor(obj, propertyName);
            if (descriptor == null) throw new UnparsbleException("Check if property" + propertyName + "exists");

            final Class<?> type = descriptor.getPropertyType();
            final Converter<?> converter = this.converterMap.get(type.getName());
            if (converter == null) throw new UnparsbleException("No Converters for" + type.getName() + "exists");

            try {
                value = converter.from(cellValue);
            } catch (final Exception e) {
                logger.error(e);
                value = null;
            }
            PropertyUtils.setSimpleProperty(obj, propertyName, value);
        }
        return obj;
    }    
}