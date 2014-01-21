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

import javax.validation.Valid;

/**
 * Created by kdj on 2014. 1. 21..
 */
@RequestMapping(value = "/valid/*")
@Controller
public class ValidController extends CommonController {
    private static final Log logger = LogFactory.getLog(ValidController.class);
    private static final String BASE_URL = "valid/";

    @Override
    protected String getBaseUrl() {
        return BASE_URL;
    }

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public String getUser(ModelMap model) {
        model.put("user", new User());
        return getBaseUrl() + "user.defaultTpl";
    }

    @RequestMapping(value = "user", method = RequestMethod.POST)
    public String postUser(@Valid @ModelAttribute User user, BindingResult bindingResult, ModelMap model) {
        model.put("method", "POST");

        if ( bindingResult.hasErrors() ) {
            if ( logger.isDebugEnabled() ) {
                logger.debug("검증 에러 발생");
            }
            return getBaseUrl() + "user.defaultTpl";
        }

        return getBaseUrl() + "result.defaultTpl";
    }

    @RequestMapping(value = "user", method = RequestMethod.PUT)
    public String putUser(@Valid @ModelAttribute User user, BindingResult bindingResult, ModelMap model) {
        model.put("method", "PUT");

        if ( bindingResult.hasErrors() ) {
            if ( logger.isDebugEnabled() ) {
                logger.debug("검증 에러 발생");
            }
            return getBaseUrl() + "user.defaultTpl";
        }

        return getBaseUrl() + "result.defaultTpl";
    }

    @RequestMapping(value = "user", method = RequestMethod.DELETE)
    public String deleteUser(@Valid @ModelAttribute User user, BindingResult bindingResult, ModelMap model) {
        model.put("method", "DELETE");

        if ( bindingResult.hasErrors() ) {
            if ( logger.isDebugEnabled() ) {
                logger.debug("검증 에러 발생");
            }
            return getBaseUrl() + "user.defaultTpl";
        }

        return getBaseUrl() + "result.defaultTpl";
    }
}
