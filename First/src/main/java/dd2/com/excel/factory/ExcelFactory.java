package dd2.com.excel.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * @Class Name : ExcelFactory.java
 * @Description : Worksheet 팩토리 클래스
 * @author KDJ
 * @since 2012. 9. 22.
 * @version 1.0
 */

public class ExcelFactory {
    private final static String HSSFWorkbook_EXTNAME = "xls";
    private final static String XSSFWorkbook_EXTNAME = "xlsx";


	public static Workbook CreateWorkbook(String fullFilePath) throws FileNotFoundException, IOException {
		Workbook wb = null;
    	if ( fullFilePath != null ) {
    		String[] files = fullFilePath.split("\\.");
    		if ( files != null && files.length > 0 ) {
                String fileExt = files[files.length-1];
    			
    			if ( fileExt.toLowerCase().equals(HSSFWorkbook_EXTNAME) ) {
    				wb = new HSSFWorkbook(new FileInputStream(fullFilePath));
    			}
    			else if ( fileExt.toLowerCase().equals(XSSFWorkbook_EXTNAME) ) {
    				wb = new XSSFWorkbook(new FileInputStream(fullFilePath));
    			}
    		}
    	}

    	return wb;
	}
}
