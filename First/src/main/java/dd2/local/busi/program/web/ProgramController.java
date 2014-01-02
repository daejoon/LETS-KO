package dd2.local.busi.program.web;

import dd2.com.ajax.AjaxResponse;
import dd2.com.jqgrid.JqGridCrudUtil;
import dd2.com.jqgrid.JqGridRequest;
import dd2.com.jqgrid.JqGridResponse;
import dd2.local.busi.com.web.CommonController;
import dd2.local.busi.program.service.ProgramService;
import dd2.local.entity.AdProgramEntity;
import dd2.local.entity.AdProgramEntityPK;
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
@RequestMapping("/program/*")
public class ProgramController extends CommonController {
    private static final Log logger = LogFactory.getLog(ProgramController.class);
    private static final String BASE_URL = "program/";

    @Autowired
    private ProgramService programService;

    @Override
    public String getBaseUrl() {
        return BASE_URL;
    }

    @RequestMapping( value = "records", method = RequestMethod.POST )
    public @ResponseBody
    JqGridResponse records( @RequestBody JqGridRequest jqGridRequest ) {
        return programService.list(jqGridRequest);
    }

    @RequestMapping( value = "recordEdit", method = RequestMethod.POST )
    public @ResponseBody
    Map<String,Object> recordEdit( @RequestBody Map<String, Object> params ) {
        if ( params != null ) {
            JqGridCrudUtil<AdProgramEntity, AdProgramEntityPK> jqgrid = new JqGridCrudUtil<>(AdProgramEntity.class, AdProgramEntityPK.class, params);

            Date date = new Date();
            Long adminID = Long.valueOf(1);

            switch (jqgrid.getOper()) {
                case ADD:
                {
                    AdProgramEntity newEntity = jqgrid.createEntityWithDataBinding();
                    newEntity.setAdProgramId(commonService.getSeq("AD_Program"));
                    newEntity.setCreated    (date);
                    newEntity.setCreatedBy  (adminID);
                    newEntity.setUpdated    (date);
                    newEntity.setUpdatedBy  (adminID);
                    programService.save(newEntity);
                }
                break;
                case EDIT:
                {
                    AdProgramEntityPK id = jqgrid.getId(new String[] {"adProgramId", "adCompanyId"});
                    AdProgramEntity entity = jqgrid.createEntityWithDataBinding();

                    AdProgramEntity updateEntity = programService.findById(id);
                    updateEntity.setIsActive(entity.getIsActive());
                    updateEntity.setAdCompanyId(entity.getAdCompanyId());
                    updateEntity.setDescription(entity.getDescription());
                    updateEntity.setName(entity.getName());
                    updateEntity.setUrl(entity.getUrl());
                    updateEntity.setUpdated(date);
                    updateEntity.setUpdatedBy(adminID);

                    if ( id.getAdCompanyId().equals(entity.getAdCompanyId()) == true ) {
                        programService.update(updateEntity);
                    } else {
                        programService.deleteById(id);
                        programService.save(updateEntity);
                    }
                }
                break;
                case DELETE:
                {
                    AdProgramEntityPK[] ids = jqgrid.getIds(new String[] {"adProgramId", "adCompanyId"});
                    programService.deleteByIds(ids);
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
        return programService.jqGridSelect(adCompanyId);
    }
}
