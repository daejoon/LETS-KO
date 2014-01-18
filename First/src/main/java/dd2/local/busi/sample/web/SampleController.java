package dd2.local.busi.sample.web;

import dd2.local.busi.com.web.CommonController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by kdj on 2014. 1. 18..
 */
@Controller
@RequestMapping("/sample/*")
public class SampleController extends CommonController {
    private static final Log logger = LogFactory.getLog(SampleController.class);
    private static final String BASE_URL = "sample/";

    @Override
    protected String getBaseUrl() {
        return BASE_URL;
    }
}
