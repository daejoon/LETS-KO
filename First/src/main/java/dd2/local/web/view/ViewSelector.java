package dd2.local.web.view;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 10. 30
 * Time: 오후 1:26
 * To change this template use File | Settings | File Templates.
 */
public final class ViewSelector {
    private static final Log logger = LogFactory.getLog(ViewSelector.class);
    private final static String FILEVIEW_NAME = "fileView";
    private final static String JSONPVIEW_NAME = "jsonpView";

    public static ModelAndView getImageView() {
        return new ModelAndView(FILEVIEW_NAME);
    }

    public static ModelAndView getJSONPView() {
        return new ModelAndView(JSONPVIEW_NAME);
    }
}
