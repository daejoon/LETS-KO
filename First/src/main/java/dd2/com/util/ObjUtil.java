package dd2.com.util;

import dd2.com.context.AppContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 8. 5
 * Time: 오전 11:37
 * To change this template use File | Settings | File Templates.
 */
public final class ObjUtil {
    private static final Log logger = LogFactory.getLog(ObjUtil.class);

    /**
     * Spring ApplicationContext에서 생성된 Bean을 가져온다.
     * @param name 빈이름
     * @return 생성된 빈을 리턴
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        T obj = null;
        ApplicationContext ctx = AppContext.getContext();
        if ( ctx != null ) {
            obj = (T)ctx.getBean(name);
        }
        return obj;
    }

    /**
     * JSON String으로 변경해 준다.
     * @param 'JSON' 을 생성할 object
     * @return 'JSON' String을 리턴
     */
    public static String toJsonString(Object obj) throws Exception {
        return JsonUtil.toJsonString(obj);
    }
}
