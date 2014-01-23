package dd2.local.busi.valid.web;

import dd2.local.busi.com.web.CommonController;
import dd2.local.entity.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.Date;

/**
 * Created by kdj on 2014. 1. 21..
 */
@RequestMapping(value = "/valid/user")
@SessionAttributes("user")
@Controller
public class ValidController extends CommonController {
    private static final Log logger = LogFactory.getLog(ValidController.class);
    private static final String BASE_URL = "valid/";

    @Override
    protected String getBaseUrl() {
        return BASE_URL;
    }


    /**
     * @ModelAttribute("user")
     *  어노테이션을 메소드에 선언하면 해당 클래스이 메소드들이 리턴을 할때 usr() 메소드 결과를 호출한다.
     *  만약 파라미터로 받는다면 파라리터로 받는게 우선한다.
     *
     * @return
     */
    @ModelAttribute("user")
    public User user() {
        User user = new User();
        user.setName("Sample User");
        user.setAge("");
        user.setEmail("sample@sample.co.kr");
        user.setCreateDate(new Date());
        user.setDescription("Description");

        return user;
    }


    @RequestMapping(method = RequestMethod.GET)
    public String getUser(ModelMap model) {
        if ( logger.isInfoEnabled() ) {
            logger.info("getUser");
        }

        return getBaseUrl() + "user.defaultTpl";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String postUser(@Valid @ModelAttribute User user,
                           BindingResult bindingResult,
                           ModelMap model,
                           SessionStatus status) {
        if ( logger.isInfoEnabled() ) {
            logger.info("postUser");
        }

        model.put("method", "POST");

        if ( bindingResult.hasErrors() ) {
            if ( logger.isDebugEnabled() ) {
                logger.debug("검증 에러 발생");
            }
            return getBaseUrl() + "user.defaultTpl";
        }

        // 저장성공 했을때
        status.setComplete();
        return getBaseUrl() + "result.defaultTpl";
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String putUser(@Valid @ModelAttribute User user,
                          BindingResult bindingResult,
                          ModelMap model,
                          SessionStatus status) {
        if ( logger.isInfoEnabled() ) {
            logger.info("putUser");
        }

        model.put("method", "PUT");

        if ( bindingResult.hasErrors() ) {
            if ( logger.isDebugEnabled() ) {
                logger.debug("검증 에러 발생");
            }
            return getBaseUrl() + "user.defaultTpl";
        }

        // 수정 성공했을때
        status.setComplete();
        return getBaseUrl() + "result.defaultTpl";
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public String deleteUser(@Valid @ModelAttribute User user,
                             BindingResult bindingResult,
                             ModelMap model,
                             SessionStatus status) {
        if ( logger.isInfoEnabled() ) {
            logger.info("deleteUser");
        }

        model.put("method", "DELETE");

        if ( bindingResult.hasErrors() ) {
            if ( logger.isDebugEnabled() ) {
                logger.debug("검증 에러 발생");
            }
            return getBaseUrl() + "user.defaultTpl";
        }

        // 삭제 성공했을때
        status.setComplete();
        return getBaseUrl() + "result.defaultTpl";
    }
}
