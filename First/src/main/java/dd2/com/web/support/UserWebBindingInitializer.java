package dd2.com.web.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 7. 23
 * Time: 오후 4:19
 * To change this template use File | Settings | File Templates.
 */
public class UserWebBindingInitializer implements WebBindingInitializer {
    private static final Log logger = LogFactory.getLog(UserWebBindingInitializer.class);

    @Override
    public void initBinder(WebDataBinder webDataBinder, WebRequest webRequest) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
        webDataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
    }
}
