package dd2.com.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 7. 29
 * Time: 오후 1:35
 * To change this template use File | Settings | File Templates.
 */
public class AppContextProvider implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AppContext.setContext(applicationContext);
    }
}
