package dd2.local.auth;

import dd2.com.constants.Const;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 8. 19
 * Time: 오후 4:14
 * To change this template use File | Settings | File Templates.
 */
public class LoginAuthHandler implements AuthenticationSuccessHandler
                                       , AuthenticationFailureHandler
                                       , LogoutSuccessHandler {
    private static final Log logger = LogFactory.getLog(LoginAuthHandler.class);

    /**
     * 로그인 성공했을때
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if ( logger.isDebugEnabled() ) {
            logger.debug("onAuthenticationSuccess");
        }
        request.getSession().removeAttribute(Const.LOGIN_MSG_KEY);
        response.sendRedirect(request.getContextPath() + "/member/welcome");
    }

    /**
     * 로그인 실패했을때
     * @param request
     * @param response
     * @param exception
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if ( logger.isDebugEnabled() ) {
            logger.debug("onAuthenticationFailure");
        }
        request.getSession().setAttribute(Const.LOGIN_MSG_KEY, exception.getMessage());
        response.sendRedirect(request.getContextPath() + "/member/login");
    }

    /**
     * 로그아웃 성공했을때
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.debug("onLogoutSuccess");
        request.getSession().removeAttribute(Const.LOGIN_MSG_KEY);
        response.sendRedirect(request.getContextPath() + "/");
    }
}
