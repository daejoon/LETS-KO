package dd2.local.busi.programAccess.web;

import dd2.com.jqgrid.JqGridCrudUtil;
import dd2.com.jqgrid.JqGridRequest;
import dd2.com.jqgrid.JqGridResponse;
import dd2.local.busi.com.web.CommonController;
import dd2.local.busi.programAccess.service.ProgramAccessService;
import dd2.local.entity.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by KDJ on 13. 12. 4.
 */
@Controller
@RequestMapping("/programAccess/*")
public class ProgramAccessController extends CommonController {
    private static final Log logger = LogFactory.getLog(ProgramAccessController.class);
    private static final String BASE_URL = "programAccess/";

    @Autowired
    private ProgramAccessService programAccessService;

    @Override
    public String getBaseUrl() {
        return BASE_URL;
    }

    @RequestMapping( value = "records", method = RequestMethod.POST )
    public @ResponseBody
    JqGridResponse records( @RequestBody JqGridRequest jqGridRequest ) {
        return programAccessService.list(jqGridRequest);
    }

    @Transactional
    @RequestMapping( value = "recordEdit", method = RequestMethod.POST )
    public @ResponseBody
    Map<String,Object> recordEdit( @RequestBody Map<String, Object> params ) {
        if ( params != null ) {
            JqGridCrudUtil<AdProgramAccessEntity, AdProgramAccessEntityPK>
                    jqgrid = new JqGridCrudUtil<>(AdProgramAccessEntity.class, AdProgramAccessEntityPK.class, params);

            Date date = new Date();
            Long adminID = new Long(1);

            switch (jqgrid.getOper()) {
                case ADD:
                {
                    AdProgramAccessEntity newEntity = jqgrid.createEntityWithDataBinding();
                    newEntity.setCreated    (date);
                    newEntity.setCreatedBy  (adminID);
                    newEntity.setUpdated    (date);
                    newEntity.setUpdatedBy  (adminID);
                    programAccessService.save(newEntity);
                }
                break;
                case EDIT:
                {
                    AdProgramAccessEntityPK id = jqgrid.getId(new String[] {"adProgramId", "adFunctionId", "adRoleId", "adCompanyId"});
                    AdProgramAccessEntity entity = jqgrid.createEntityWithDataBinding();

                    AdProgramAccessEntity updateEntity = programAccessService.findById(id);
                    updateEntity.setHasPermission(entity.getHasPermission());
                    updateEntity.setUpdated(date);
                    updateEntity.setUpdatedBy(adminID);

                    programAccessService.update(updateEntity);
                }
                break;
                case DELETE:
                {
                    AdProgramAccessEntityPK[] ids = jqgrid.getIds(new String[] {"adProgramId", "adFunctionId", "adRoleId", "adCompanyId"});
                    programAccessService.deleteByIds(ids);
                }
                break;
                default: break;
            }
        }// if ( params != null ) {

        return new HashMap<>();
    }
}
