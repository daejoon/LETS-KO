package dd2.com.context;

import org.springframework.context.ApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 7. 29
 * Time: 오후 1:37
 * To change this template use File | Settings | File Templates.
 */
public final class AppContext {
    private static ApplicationContext context;

    public static ApplicationContext getContext() {
        return context;
    }

    public static void setContext(ApplicationContext context) {
        AppContext.context = context;
    }
}
