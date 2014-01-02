package dd2.local.busi.programFunction.web;

import dd2.com.ajax.AjaxResponse;
import dd2.com.jqgrid.JqGridCrudUtil;
import dd2.com.jqgrid.JqGridRequest;
import dd2.com.jqgrid.JqGridResponse;
import dd2.local.busi.com.web.CommonController;
import dd2.local.busi.programFunction.service.ProgramFunctionService;
import dd2.local.entity.AdProgramFunctionEntity;
import dd2.local.entity.AdProgramFunctionEntityPK;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by KDJ on 13. 12. 4.
 */
@Controller
@RequestMapping("/programFunction/*")
public class ProgramFunctionController extends CommonController {
    private static final Log logger = LogFactory.getLog(ProgramFunctionController.class);
    private static final String BASE_URL = "programFunction/";

    @Autowired
    private ProgramFunctionService programFunctionService;

    @Override
    public String getBaseUrl() {
        return BASE_URL;
    }

    @RequestMapping( value = "records", method = RequestMethod.POST )
    public @ResponseBody
    JqGridResponse records( @RequestBody JqGridRequest jqGridRequest ) {
        return programFunctionService.list(jqGridRequest);
    }

    @RequestMapping( value = "recordEdit", method = RequestMethod.POST )
    public @ResponseBody
    Map<String,Object> recordEdit( @RequestBody Map<String, Object> params ) {
        if ( params != null ) {
            JqGridCrudUtil<AdProgramFunctionEntity, AdProgramFunctionEntityPK>
                    jqgrid = new JqGridCrudUtil<>(AdProgramFunctionEntity.class, AdProgramFunctionEntityPK.class, params);

            Date date = new Date();
            Long adminID = Long.valueOf(1);

            switch (jqgrid.getOper()) {
                case ADD:
                {
                    AdProgramFunctionEntity newEntity = jqgrid.createEntityWithDataBinding();
                    newEntity.setCreated    (date);
                    newEntity.setCreatedBy  (adminID);
                    newEntity.setUpdated    (date);
                    newEntity.setUpdatedBy  (adminID);
                    programFunctionService.save(newEntity);
                }
                break;
                case EDIT:
                {
                    AdProgramFunctionEntityPK id = jqgrid.getId(new String[] {"adProgramId", "adFunctionId", "adCompanyId"});
                    AdProgramFunctionEntity entity = jqgrid.createEntityWithDataBinding();

                    AdProgramFunctionEntity updateEntity = programFunctionService.findById(id);

                    updateEntity.setAdFunctionId(entity.getAdFunctionId());
                    updateEntity.setUpdated(date);
                    updateEntity.setUpdatedBy(adminID);

                    programFunctionService.deleteById(id);
                    programFunctionService.save(updateEntity);
                }
                break;
                case DELETE:
                {
                    AdProgramFunctionEntityPK[] ids = jqgrid.getIds(new String[] {"adProgramId", "adFunctionId", "adCompanyId"});
                    programFunctionService.deleteByIds(ids);
                }
                break;
                default: break;
            }
        }// if ( params != null ) {

        return new HashMap<>();
    }

    @RequestMapping( value = "jqGridSelect", method = RequestMethod.POST )
    public @ResponseBody
    AjaxResponse<List<Map<String,Object>>> jqGridSelect( @RequestBody Map<String, Object> params ) {
        Long adProgramId = Long.parseLong(params.get("adProgramId").toString());
        Long adCompanyId = Long.parseLong(params.get("adCompanyId").toString());
        return programFunctionService.jqGridSelect(adProgramId, adCompanyId);
    }
}
