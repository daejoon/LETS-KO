package dd2.local.busi.test;

import dd2.local.busi.com.web.CommonController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by KDJ on 13. 12. 18.
 */
@RequestMapping("/test/*")
@Controller
public class TestController extends CommonController {
    private static final Log logger = LogFactory.getLog(TestController.class);
    private static final String BASE_URL = "test/";

    @Override
    protected String getBaseUrl() {
        return BASE_URL;
    }
}
