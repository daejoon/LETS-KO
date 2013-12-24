package dd2.com.excel.factory;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelFactory {
    private final static String HSSFWorkbook_EXTNAME = "xls";
    private final static String XSSFWorkbook_EXTNAME = "xlsx";

	public static Workbook CreateWorkbook(String fullFilePath) throws IOException {
		Workbook workbook = null;

    	if ( fullFilePath != null ) {
    		String[] files = fullFilePath.split("\\.");
    		if ( files.length > 0 ) {
                String fileExt = files[files.length-1];
    			
    			if ( fileExt.toLowerCase().equals(HSSFWorkbook_EXTNAME) ) {
    				workbook = new HSSFWorkbook(new FileInputStream(fullFilePath));
    			}
    			else if ( fileExt.toLowerCase().equals(XSSFWorkbook_EXTNAME) ) {
    				workbook = new XSSFWorkbook(new FileInputStream(fullFilePath));
    			}
    		}
    	}

    	return workbook;
	}
}
