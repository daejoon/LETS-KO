package dd2.local.web.support;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 7. 24
 * Time: 오전 1:57
 * To change this template use File | Settings | File Templates.
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.*;

public class UserHttpListener implements ServletContextListener
                                        , HttpSessionListener
                                        , HttpSessionAttributeListener
                                        , HttpSessionBindingListener {
    private final static Log logger = LogFactory.getLog(UserHttpListener.class);
    private ServletContext servletContext = null;

    /**
     * ServletContext Initialized
     * 초기화 루틴
     */
    public void contextInitialized(ServletContextEvent sce) {
        servletContext = sce.getServletContext();
    }

    /**
     * ServletContext Destroyed
     */
    public void contextDestroyed(ServletContextEvent sce) {
    }


    /**
     * 새로운 세션을 생성한다.
     */
    public void sessionCreated(HttpSessionEvent hse) {
        HttpSession s = hse.getSession();
    }

    /**
     * 세션 제거
     */
    public void sessionDestroyed(HttpSessionEvent hse) {
        HttpSession s = hse.getSession();
    }

    /**
     * 속성을 세션에 추가
     */
    public void attributeAdded(HttpSessionBindingEvent hsbe) {
        HttpSession s = hsbe.getSession();
    }

    /**
     * 속성을 세션에 제거
     */
    public void attributeRemoved(HttpSessionBindingEvent hsbe) {
        HttpSession s = hsbe.getSession();
    }

    /**
     * 속성으로 사용되는 클래스가 세션에 바인딩 되었을때
     */
    public void attributeReplaced(HttpSessionBindingEvent hsbe) {
        HttpSession s = hsbe.getSession();
    }

    /**
     * 속성으로 사용되는 클래스가 세션에 제거 되었을때
     */
    public void valueBound(HttpSessionBindingEvent hsbe) {
    }

    /**
     * 세션이 제거되었을때
     */
    public void valueUnbound(HttpSessionBindingEvent hsbe) {
    }
}

