package dd2.local.busi.com.web;

import dd2.local.busi.com.service.CommonService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 7. 23
 * Time: 오후 11:10
 * To change this template use File | Settings | File Templates.
 * 공통으로 사용하는 비지니스 서비스를 포함하는 컨트롤러
 * CommonCntroller를 상속받지 않고 바로 POJO (Plain Old Java Class) Class로 Controller를 사용해도 된다.
 */
public abstract class CommonController {
    private static final Log logger = LogFactory.getLog(CommonController.class);

    /**
     * 공통 서비스
     */
    @Autowired
    protected CommonService commonService;

    protected abstract String getBaseUrl();

    /**
     * ../comm/{titles} 의 url 호출
     * @param request
     * @param model
     * @param tilesName
     * @return
     */
    @RequestMapping( value = "comm/{tilesName}")
    public String doCommPage(
            HttpServletRequest request,
            ModelMap model,
            @PathVariable(value = "tilesName") String tilesName
    ) {
        return getBaseUrl() + tilesName + ".tiles";
    }

    /**
     * ../comm/{titles}/{name}/{value} 의 url 호출
     * @param request
     * @param model
     * @param tilesName
     * @param name
     * @param value
     * @return
     */
    @RequestMapping( value = "comm/{tilesName}/{name}/{value}")
    public String doCommPageAndSingleVar(
            HttpServletRequest request,
            ModelMap model,
            @PathVariable(value = "tilesName") String tilesName,
            @PathVariable(value = "name") String name,
            @PathVariable(value = "value") String value
    ) {
        model.put(name, value);
        return getBaseUrl() + tilesName + ".tiles";
    }

    @RequestMapping("comm/createGUID")
    public @ResponseBody Map<String,Object> createGUID() {
        Map<String,Object> result = new HashMap<>();
        result.put("status", true);
        result.put("message", "");
        result.put("guid", UUID.randomUUID().toString().toUpperCase());
        return result;
    }
}
