package dd2.local.auth;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.InvalidCookieException;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 8. 21
 * Time: 오전 11:22
 * To change this template use File | Settings | File Templates.
 */
public class IPTokenBasedRememberMeService extends TokenBasedRememberMeServices {
    private static final Log logger = LogFactory.getLog(IPTokenBasedRememberMeService.class);
    private static final ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<HttpServletRequest>();

    public IPTokenBasedRememberMeService(String key, UserDetailsService userDetailsService) {
        super(key, userDetailsService);
    }

    public HttpServletRequest getContext() {
        return requestHolder.get();
    }

    public void setContext(HttpServletRequest context) {
        requestHolder.set(context);
    }

    protected String getUserIpAddress(HttpServletRequest request) {
        return request.getRemoteAddr();
    }

    @Override
    public void onLoginSuccess(HttpServletRequest request, HttpServletResponse response, Authentication successfulAuthentication) {
        try {
            setContext(request);
            super.onLoginSuccess(request, response, successfulAuthentication);
        } finally {
            setContext(null);
        }
    }

    @Override
    protected String makeTokenSignature(long tokenExpiryTime, String username, String password) {
        return DigestUtils.md5DigestAsHex(
                (username + ":"
                        + tokenExpiryTime + ":"
                        + password + ":"
                        + getKey() + ":"
                        + getUserIpAddress(getContext())).getBytes(Charset.forName("UTF-8"))
        );
    }

    @Override
    protected void setCookie(String[] tokens, int maxAge, HttpServletRequest request, HttpServletResponse response) {
        // 쿠키에 IP 주소 추가
        String[] tokensWithIPAddress = Arrays.copyOf(tokens, tokens.length+1);
        tokensWithIPAddress[tokensWithIPAddress.length-1] = getUserIpAddress(request);
        super.setCookie(tokensWithIPAddress, maxAge, request, response);
    }

    @Override
    protected UserDetails processAutoLoginCookie(String[] cookieTokens, HttpServletRequest request, HttpServletResponse response) {
        try {
            setContext(request);
            // 마지막 토큰을 가져온다.
            String cookieIPAddressToken = cookieTokens[cookieTokens.length-1];
            String currentIPAddress = getUserIpAddress(request);
            if ( !currentIPAddress.equals(cookieIPAddressToken) ) {
                String msg = "쿠키IP와 현재IP가 다릅니다. 쿠키IP=" + cookieIPAddressToken + ", 현재IP=" + currentIPAddress;
                if ( logger.isDebugEnabled() ) {
                    logger.debug(msg);
                }
                throw new InvalidCookieException(msg);
            }
            return super.processAutoLoginCookie(Arrays.copyOf(cookieTokens, cookieTokens.length-1), request, response);
        } finally {
            setContext(null);
        }
    }
}
