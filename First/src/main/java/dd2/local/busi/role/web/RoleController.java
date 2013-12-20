package dd2.local.busi.role.web;

import dd2.com.ajax.AjaxResponse;
import dd2.com.jqgrid.JqGridCrudUtil;
import dd2.com.jqgrid.JqGridRequest;
import dd2.com.jqgrid.JqGridResponse;
import dd2.local.busi.com.web.CommonController;
import dd2.local.busi.role.service.RoleService;
import dd2.local.entity.AdRoleEntity;
import dd2.local.entity.AdRoleEntityPK;
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
@RequestMapping("/role/*")
public class RoleController extends CommonController {
    private static final Log logger = LogFactory.getLog(RoleController.class);
    private static final String BASE_URL = "role/";

    @Autowired
    private RoleService roleService;

    @Override
    public String getBaseUrl() {
        return BASE_URL;
    }

    @RequestMapping( value = "records", method = RequestMethod.POST )
    public @ResponseBody
    JqGridResponse records( @RequestBody JqGridRequest jqGridRequest ) {
        return roleService.list(jqGridRequest);
    }

    @RequestMapping( value = "recordEdit", method = RequestMethod.POST )
    public @ResponseBody
    Map<String,Object> recordEdit( @RequestBody Map<String, Object> params ) {
        if ( params != null ) {
            JqGridCrudUtil<AdRoleEntity, AdRoleEntityPK> jqgrid = new JqGridCrudUtil<>(AdRoleEntity.class, AdRoleEntityPK.class, params);

            Date date = new Date();
            Long adminID = new Long(1);

            switch (jqgrid.getOper()) {
                case ADD:
                {
                    AdRoleEntity newEntity = jqgrid.createEntityWithDataBinding();
                    newEntity.setAdRoleId(commonService.getSeq("AD_Role"));
                    newEntity.setCreated    (date);
                    newEntity.setCreatedBy  (adminID);
                    newEntity.setUpdated    (date);
                    newEntity.setUpdatedBy  (adminID);
                    roleService.save(newEntity);
                }
                break;
                case EDIT:
                {
                    AdRoleEntityPK id = jqgrid.getId(new String[] {"adRoleId", "adCompanyId"});
                    AdRoleEntity entity = jqgrid.createEntityWithDataBinding();

                    AdRoleEntity updateEntity = roleService.findById(id);
                    updateEntity.setIsActive(entity.getIsActive());
                    updateEntity.setAdCompanyId(entity.getAdCompanyId());
                    updateEntity.setDescription(entity.getDescription());
                    updateEntity.setName(entity.getName());
                    updateEntity.setValue(entity.getValue());
                    updateEntity.setUpdated(date);
                    updateEntity.setUpdatedBy(adminID);
                    roleService.update(updateEntity);
                }
                break;
                case DELETE:
                {
                    AdRoleEntityPK[] ids = jqgrid.getIds(new String[] {"adRoleId", "adCompanyId"});
                    roleService.deleteByIds(ids);
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
        Long adCompanyId = Long.parseLong(params.get("adCompanyId").toString());
        return roleService.jqGridSelect(adCompanyId);
    }
}
