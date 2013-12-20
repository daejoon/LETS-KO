package dd2.com.web.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 7. 23
 * Time: 오후 9:17
 * To change this template use File | Settings | File Templates.
 */
public class UserMethodArgumentResolver implements HandlerMethodArgumentResolver {
    private static final Log logger = LogFactory.getLog(UserMethodArgumentResolver.class);

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        if ( logger.isDebugEnabled() ) {
            logger.debug("supportsParameter");
        }
        return true;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        if ( logger.isDebugEnabled() ) {
            logger.debug("resolveArgument");
        }
        // 현재는 그냥 리턴하는데 차후 가공해서 넘긴다.
        // 근데 현재까지는 특별히 사용자 파라미터를 만들 필요는 없을듯...
        return new UserParam();
    }
}
