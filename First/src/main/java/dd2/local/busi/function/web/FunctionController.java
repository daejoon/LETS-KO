package dd2.local.busi.function.web;

import dd2.com.ajax.AjaxResponse;
import dd2.com.jqgrid.JqGridCrudUtil;
import dd2.com.jqgrid.JqGridRequest;
import dd2.com.jqgrid.JqGridResponse;
import dd2.local.busi.com.web.CommonController;
import dd2.local.busi.function.service.FunctionService;
import dd2.local.entity.AdFunctionEntity;
import dd2.local.entity.AdFunctionEntityPK;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by KDJ on 13. 12. 4.
 */
@Controller
@RequestMapping("/function/*")
public class FunctionController extends CommonController {
    private static final Log logger = LogFactory.getLog(FunctionController.class);
    private static final String BASE_URL = "function/";

    @Autowired
    private FunctionService functionService;

    @Override
    public String getBaseUrl() {
        return BASE_URL;
    }

    @RequestMapping( value = "records", method = RequestMethod.POST )
    public @ResponseBody
    JqGridResponse records( @RequestBody JqGridRequest jqGridRequest ) {
        return functionService.list(jqGridRequest);
    }

    @RequestMapping( value = "recordEdit", method = RequestMethod.POST )
    public @ResponseBody
    Map<String,Object> recordEdit( @RequestBody Map<String, Object> params ) {
        if ( params != null ) {
            JqGridCrudUtil<AdFunctionEntity, AdFunctionEntityPK> jqgrid = new JqGridCrudUtil<>(AdFunctionEntity.class, AdFunctionEntityPK.class, params);

            Date date = new Date();
            Long adminID = Long.valueOf(1);

            switch (jqgrid.getOper()) {
                case ADD:
                {
                    AdFunctionEntity newEntity = jqgrid.createEntityWithDataBinding();
                    newEntity.setAdFunctionId(commonService.getSeq("AD_Funtion"));
                    newEntity.setCreated    (date);
                    newEntity.setCreatedBy  (adminID);
                    newEntity.setUpdated    (date);
                    newEntity.setUpdatedBy  (adminID);
                    functionService.save(newEntity);
                }
                break;
                case EDIT:
                {
                    AdFunctionEntityPK id = jqgrid.getId(new String[] {"adFunctionId", "adCompanyId"});
                    AdFunctionEntity entity = jqgrid.createEntityWithDataBinding();

                    AdFunctionEntity updateEntity = functionService.findById(id);
                    updateEntity.setIsActive(entity.getIsActive());
                    updateEntity.setAdCompanyId(entity.getAdCompanyId());
                    updateEntity.setDescription(entity.getDescription());
                    updateEntity.setName(entity.getName());
                    updateEntity.setUpdated(date);
                    updateEntity.setUpdatedBy(adminID);

                    if ( id.getAdCompanyId().equals(entity.getAdCompanyId()) == true ) {
                        functionService.update(updateEntity);
                    } else {
                        functionService.deleteById(id);
                        functionService.save(updateEntity);
                    }
                }
                break;
                case DELETE:
                {
                    AdFunctionEntityPK[] ids = jqgrid.getIds(new String[] {"adFunctionId", "adCompanyId"});
                    functionService.deleteByIds(ids);
                }
                break;
                default: break;
            }
        }// if ( params != null ) {

        return new HashMap<>();
    }

    @RequestMapping( value = "jqGridSelect", method = RequestMethod.POST )
    public @ResponseBody
    AjaxResponse<List<Map<String,Object>>> jqGridSelect(@RequestBody Map<String, Object> params) {
        Long adCompanyId = Long.parseLong(params.get("adCompanyId").toString());
        return functionService.jqGridSelect(adCompanyId);
    }
}
