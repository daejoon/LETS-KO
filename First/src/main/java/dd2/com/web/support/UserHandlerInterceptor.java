package dd2.com.web.support;

import dd2.com.constants.Const;
import dd2.com.util.ConfigUtil;
import dd2.com.util.New;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 8. 21
 * Time: 오후 2:59
 * To change this template use File | Settings | File Templates.
 * config.xml의 정보를 읽어서 View쪽에 전달한다.
 */
public class UserHandlerInterceptor extends HandlerInterceptorAdapter {
    private static final Log logger = LogFactory.getLog(UserHandlerInterceptor.class);
    private static Map<String, Object> configMap = New.map();

    public UserHandlerInterceptor() {
        try {
            configMap.put("type"    , ConfigUtil.getString("mode.type"));
            configMap.put("language", ConfigUtil.getString("viewinfo.language"));
        } catch (Exception ex) {
            if ( logger.isErrorEnabled() ) {
                logger.error(ex.getMessage());
            }
        }
    }

    /**
     * 컨트롤 호출전에 불려진다.
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return super.preHandle(request, response, handler);
    }

    /**
     * 컨트롤로 호출후에 불려진다.
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if ( modelAndView != null ) {
            ModelMap model = modelAndView.getModelMap();
            if ( model.containsKey(Const.CONFIG_KEY) == false ) {
                model.put(Const.CONFIG_KEY, configMap);
            }
        }
        super.postHandle(request, response, handler, modelAndView);
    }
}
