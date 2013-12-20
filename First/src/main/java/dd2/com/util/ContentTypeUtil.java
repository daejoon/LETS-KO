package dd2.com.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 11. 4
 * Time: 오후 12:01
 * To change this template use File | Settings | File Templates.
 */
public final class ContentTypeUtil {
    private static final Log logger = LogFactory.getLog(ContentTypeUtil.class);
    private static Map<String,String> typeMap = new HashMap<>();

    static {
        typeMap.put("pdf"  , "application/pdf");
        typeMap.put("txt"  , "text/plain");
        typeMap.put("html" , "text/html");
        typeMap.put("exe"  , "application/octet-stream");
        typeMap.put("zip"  , "application/zip");
        typeMap.put("doc"  , "application/msword");
        typeMap.put("xls"  , "application/vnd.ms-excel");
        typeMap.put("xlsx" , "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        typeMap.put("ppt"  , "application/vnd.ms-powerpoint");
        typeMap.put("gif"  , "image/gif");
        typeMap.put("png"  , "image/png");
        typeMap.put("jpeg" , "image/jpg");
        typeMap.put("jpg"  , "image/jpg");
    }

    public static String getContentType(String ext) {
        if ( typeMap.containsKey(ext) == false ) {
            return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
        }
        return typeMap.get(ext);
    }
}
