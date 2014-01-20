package dd2.local.web.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 7. 23
 * Time: 오후 4:19
 * To change this template use File | Settings | File Templates.
 * @Valid 처리를 위해서 ConfigurableWebBindingInitializer를 상속 받았다.
 */
public class UserWebBindingInitializer extends ConfigurableWebBindingInitializer {
    private static final Log logger = LogFactory.getLog(UserWebBindingInitializer.class);

    @Override
    public void initBinder(WebDataBinder binder, WebRequest request) {
        super.initBinder(binder, request);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
    }
}
