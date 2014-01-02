package dd2.local.busi.userRole.web;

import dd2.com.jqgrid.JqGridCrudUtil;
import dd2.com.jqgrid.JqGridRequest;
import dd2.com.jqgrid.JqGridResponse;
import dd2.local.busi.com.web.CommonController;
import dd2.local.busi.userRole.service.UserRoleService;
import dd2.local.entity.AdUserRoleEntity;
import dd2.local.entity.AdUserRoleEntityPK;
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
import java.util.Map;

/**
 * Created by KDJ on 13. 12. 10.
 */
@RequestMapping("/userRole/*")
@Controller
public class UserRoleController extends CommonController {
    private static final Log logger = LogFactory.getLog(UserRoleController.class);
    private static final String BASE_URL = "userRole/";

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public String getBaseUrl() {
        return BASE_URL;
    }

    @RequestMapping( value = "records", method = RequestMethod.POST )
    public @ResponseBody
    JqGridResponse records( @RequestBody JqGridRequest jqGridRequest ) {
        return userRoleService.list(jqGridRequest);
    }

    @RequestMapping( value = "recordEdit", method = RequestMethod.POST )
    public @ResponseBody
    Map<String,Object> recordEdit( @RequestBody Map<String, Object> params ) {
        if ( params != null ) {
            JqGridCrudUtil<AdUserRoleEntity, AdUserRoleEntityPK> jqgrid = new JqGridCrudUtil<>(AdUserRoleEntity.class, AdUserRoleEntityPK.class, params);

            Date date = new Date();
            Long adminID = Long.valueOf(1);

            switch (jqgrid.getOper()) {
                case ADD:
                {
                    AdUserRoleEntity newEntity = jqgrid.createEntityWithDataBinding();
                    newEntity.setCreated    (date);
                    newEntity.setCreatedBy  (adminID);
                    newEntity.setUpdated    (date);
                    newEntity.setUpdatedBy  (adminID);
                    userRoleService.save(newEntity);
                }
                break;
                case EDIT:
                {
                    AdUserRoleEntityPK id = jqgrid.getId(new String[] {"adUserId", "adRoleId", "adCompanyId"});
                    AdUserRoleEntity entity = jqgrid.createEntityWithDataBinding();

                    AdUserRoleEntity updateEntity = userRoleService.findById(id);
                    updateEntity.setAdRoleId(entity.getAdRoleId());
                    updateEntity.setUpdated(date);
                    updateEntity.setUpdatedBy(adminID);
                    userRoleService.delete(entity);
                    userRoleService.save(updateEntity);
                }
                break;
                case DELETE:
                {
                    AdUserRoleEntityPK[] ids = jqgrid.getIds(new String[] {"adUserId", "adRoleId", "adCompanyId"});
                    userRoleService.deleteByIds(ids);
                }
                break;
                default: break;
            }
        }// if ( params != null ) {

        return new HashMap<>();
    }
}
