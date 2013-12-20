package dd2.local.util;

import dd2.local.entity.Member;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 10. 28
 * Time: 오후 12:14
 * To change this template use File | Settings | File Templates.
 */
public final class MemberUtil {
    private static final Log logger = LogFactory.getLog(MemberUtil.class);

    /**
     * 사용자 인증을 가져온다. (SpringSecurity)
     * @return Authentication 리턴
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 현재 로그인한 사용자를 가져온다.
     * 익명사용자인 경우는 null을 리턴한다.
     * @return
     */
    public static Member getCurrentMember() {
        Object member = getAuthentication().getPrincipal();
        if ( member != null &&  member instanceof Member ) {
            return (Member)member;
        }
        return null;
    }

    /**
     * 현재 로그인 여부를 알수 있다.
     * @return
     */
    public static boolean isLogin() {
        return ( getCurrentMember()!=null ? true:false );
    }
}
