package dd2.local.busi.main.web;

import dd2.local.busi.com.web.CommonController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by KDJ on 13. 12. 4.
 */
@Controller
@RequestMapping("/main/*")
public class MainController extends CommonController {
    private static final Log logger = LogFactory.getLog(MainController.class);
    private static final String BASE_URL = "main/";

    @Override
    public String getBaseUrl() {
        return BASE_URL;
    }

    @RequestMapping("index")
    public String doIndex() {
        return getBaseUrl() + "index.tiles";
    }

    @RequestMapping("dashboard")
    public String doDashboard() {
        return getBaseUrl() + "dashboard.tiles";
    }
}
