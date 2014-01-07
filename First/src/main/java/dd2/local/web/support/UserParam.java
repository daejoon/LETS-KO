package dd2.local.web.support;

import dd2.com.vo.BaseVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 7. 23
 * Time: 오후 10:56
 * To change this template use File | Settings | File Templates.
 */
public class UserParam extends BaseVO {
    private static final Log logger = LogFactory.getLog(UserParam.class);
    private static final long serialVersionUID = -2715067134264197596L;

    @Override
    public String toString() {
        return super.toString() + "UserParam";
    }
}
