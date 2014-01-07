package dd2.local.busi.member.web;

import dd2.local.util.MemberUtil;
import dd2.local.busi.com.web.CommonController;
import dd2.local.entity.Role;
import dd2.local.entity.Member;
import dd2.local.busi.member.service.MemberService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 8. 14
 * Time: 오후 12:17
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/member/*")
public class MemberController extends CommonController {
    private static final Log logger = LogFactory.getLog(MemberController.class);
    private static final String BASE_URL = "member/";

    @Autowired
    private MemberService memberService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String getBaseUrl() {
        return BASE_URL;
    }

    @RequestMapping("login")
    public String doLogin() {
        if ( MemberUtil.isLogin() == false ) {
            return BASE_URL + "login.indexTpl";
        }
        return BASE_URL + "welcome.defaultTpl";
    }

    @RequestMapping("logout")
    public String doLogout() {
        return BASE_URL + "logout.indexTpl";
    }

    @RequestMapping("welcome")
    public String doWelcome() {
        return BASE_URL + "welcome.defaultTpl";
    }

    @RequestMapping("addMember")
    public String addMember(
                @RequestParam(value="action", required = false, defaultValue = "false" ) Boolean action
            ,   @RequestParam(value="enabled", required = false, defaultValue = "false" ) Boolean enabled
            ,   @RequestParam(value="authority", required = false) Long[] authorities
            ,   @ModelAttribute Member member
            ,   ModelMap model
    ) {

        String target_tiles = "";
        model.put("codeRoles", commonService.getCodeRoles());

        if ( action == true ) {
            if ( authorities != null ) {
                for( Long authorityId : authorities) {
                    member.addRole(new Role(authorityId));
                }
            }
            member.setPassword(passwordEncoder.encode(member.getPassword()));
            member.setEnabled(enabled);
            member.setCreateDate(new Date());
            memberService.saveOrUpdate(member);

            target_tiles = "login.indexTpl";
        } else {
            target_tiles = "addMember.indexTpl";
        }

        return BASE_URL + target_tiles;
    }

    @RequestMapping("getMember")
    public String getMember(
                @RequestParam(value="action", required = false, defaultValue = "false" ) Boolean action
            ,   @RequestParam(value="id", required = false) Long id
            ,   ModelMap model
    ) {
        if ( action == true ) {
            model.put("member", memberService.getMemberWithRoles(id));
        }

        return BASE_URL + "getMember.defaultTpl";
    }

    @RequestMapping("deleteMember")
    public String deleteMember(
                @RequestParam(value="action", required = false, defaultValue = "false" ) Boolean action
            ,   @RequestParam(value="id", required = false) Long id
    ) {
        if ( action == true ) {
            memberService.deleteById(id);
        }
        return BASE_URL + "deleteMember.defaultTpl";
    }

    @RequestMapping("listMember")
    public String listMember(ModelMap model) {
        model.put("list", memberService.getMemberAll());
        return BASE_URL + "listMember.defaultTpl";
    }
}
