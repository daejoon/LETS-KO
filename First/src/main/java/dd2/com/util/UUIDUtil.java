package dd2.com.util;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 10. 29
 * Time: 오후 5:29
 * To change this template use File | Settings | File Templates.
 */
public final class UUIDUtil {
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
