package dd2.com.web.view;

import dd2.com.util.ObjUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 8. 5
 * Time: 오전 11:13
 * To change this template use File | Settings | File Templates.
 */
public final class JSONPView extends AbstractView {
    private static final Log logger = LogFactory.getLog(JSONPView.class);
    private static final String EXCLUDE_BINDING_RESULT_PREFIX = "org.springframework.validation.BindingResult";


    private Map<String,Object> getModelKeyFilter(Map<String,Object> model) {
        Map<String, Object> result = new HashMap<String,Object>();;

        Iterator<String> iter = model.keySet().iterator();
        while (iter.hasNext() ) {
            String keyName = iter.next();
            if ( keyName.startsWith(EXCLUDE_BINDING_RESULT_PREFIX) == false ) {
                result.put(keyName, model.get(keyName));
            }
        }
        return result;
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType(this.getContentType());
        response.setHeader("Cache-Control", "no-cache");

        PrintWriter writer = null;
        try {
            writer = response.getWriter();

            String callback = request.getParameter("callback");
            if ( callback == null || callback.equals("") ) {
                callback = "callback";
            }

            String json = ObjUtil.toJsonString(getModelKeyFilter(model));
            if ( logger.isDebugEnabled() ) {
                logger.debug("JSON=[" + json + "]");
            }
            writer.append(callback).append("(").append(json).append(")");
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        } finally {
            if ( writer != null ) writer.close();
        }
    }
}
