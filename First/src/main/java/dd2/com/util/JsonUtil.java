package dd2.com.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 8. 5
 * Time: 오전 11:42
 * To change this template use File | Settings | File Templates.
 */
public final class JsonUtil {
    private static final Log logger = LogFactory.getLog(JsonUtil.class);

    /**
     * 해당 객체로 부터 JSON String을 생성한다.
     * @param obj
     * @return
     */
    public static String toJsonString(Object obj) throws Exception {
        String json = "";
        ObjectMapper om = new ObjectMapper();
        json = om.writeValueAsString(obj);
        return json;
    }

    public static Map<String,Object> fromJsonString(String jsonStr)  throws Exception {
        Map<String,Object> obj = null;
        ObjectMapper om = new ObjectMapper();
        obj = om.readValue(jsonStr, new TypeReference<Map<String,Object>>() {} );
        return obj;
    }
}
