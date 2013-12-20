package dd2.local.auth;

import dd2.local.busi.member.service.MemberService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 8. 9
 * Time: 오전 10:53
 * To change this template use File | Settings | File Templates.
 */
public class AuthUserDetailsService implements UserDetailsService {
    private static final Log logger = LogFactory.getLog(AuthUserDetailsService.class);

    @Autowired
    private MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = memberService.getMemberByName(username);
        if ( userDetails == null
                || userDetails.getUsername() == null
                    || userDetails.getUsername().equals("") == true ) {

            String msg = username + "유저가 존재하지 않습니다.";
            if ( logger.isDebugEnabled() ) {
                logger.debug(msg);
            }

            throw new UsernameNotFoundException(msg);
        }
        return memberService.getMemberByName(username);
    }
}
