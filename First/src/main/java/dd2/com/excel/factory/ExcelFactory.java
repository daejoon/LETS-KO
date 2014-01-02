package dd2.com.excel.factory;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelFactory {
    private final static String HSSFWORKBOOK_EXTNAME = "xls";
    private final static String XSSFWORKBOOK_EXTNAME = "xlsx";

	public static Workbook createWorkbook(String fullFilePath) throws IOException {
		Workbook workbook = null;

    	if ( fullFilePath != null ) {
    		String[] files = fullFilePath.split("\\.");
    		if ( files.length > 0 ) {
                String fileExt = files[files.length-1].toLowerCase();
    			
    			if ( fileExt.equals(HSSFWORKBOOK_EXTNAME) ) {
    				workbook = new HSSFWorkbook(new FileInputStream(fullFilePath));
    			}
    			else if ( fileExt.equals(XSSFWORKBOOK_EXTNAME) ) {
    				workbook = new XSSFWorkbook(new FileInputStream(fullFilePath));
    			}
    		}
    	}

    	return workbook;
	}
}
