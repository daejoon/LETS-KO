package dd2.local.busi.user.web;

import dd2.com.jqgrid.JqGridCrudUtil;
import dd2.com.jqgrid.JqGridRequest;
import dd2.com.jqgrid.JqGridResponse;
import dd2.local.busi.com.web.CommonController;
import dd2.local.busi.user.service.UserService;
import dd2.local.entity.AdUserEntity;
import dd2.local.entity.AdUserEntityPK;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by KDJ on 13. 12. 4.
 */
@Controller
@RequestMapping("/user/*")
public class UserController extends CommonController {
    private static final Log logger = LogFactory.getLog(UserController.class);
    private static final String BASE_URL = "user/";

    @Autowired
    private UserService userService;

    @Override
    public String getBaseUrl() {
        return BASE_URL;
    }

    @RequestMapping( value = "records", method = RequestMethod.POST )
    public @ResponseBody
    JqGridResponse records( @RequestBody JqGridRequest jqGridRequest ) {
        return userService.list(jqGridRequest);
    }

    @RequestMapping( value = "recordEdit", method = RequestMethod.POST )
    public @ResponseBody
    Map<String,Object> recordEdit( @RequestBody Map<String, Object> params ) {
        if ( params != null ) {
            JqGridCrudUtil<AdUserEntity, AdUserEntityPK> jqgrid = new JqGridCrudUtil<>(AdUserEntity.class, AdUserEntityPK.class, params);

            Date date = new Date();
            Long adminID = new Long(1);

            switch (jqgrid.getOper()) {
                case ADD:
                {
                    AdUserEntity newEntity = jqgrid.createEntityWithDataBinding();
                    newEntity.setAdUserId(commonService.getSeq("AD_User"));
                    newEntity.setCreated    (date);
                    newEntity.setCreatedBy  (adminID);
                    newEntity.setUpdated    (date);
                    newEntity.setUpdatedBy  (adminID);
                    userService.save(newEntity);
                }
                break;
                case EDIT:
                {
                    AdUserEntityPK id = jqgrid.getId(new String[] {"adUserId", "adCompanyId"});
                    AdUserEntity entity = jqgrid.createEntityWithDataBinding();

                    AdUserEntity updateEntity = userService.findById(id);
                    updateEntity.setIsActive(entity.getIsActive());
                    updateEntity.setAdCompanyId(entity.getAdCompanyId());
                    updateEntity.setDescription(entity.getDescription());
                    updateEntity.seteMail(entity.geteMail());
                    updateEntity.setFax(entity.getFax());
                    updateEntity.setName(entity.getName());
                    updateEntity.setPassword(entity.getPassword());
                    updateEntity.setPhone(entity.getPhone());
                    updateEntity.setPhone2(entity.getPhone2());
                    updateEntity.setTitle(entity.getTitle());
                    updateEntity.setUpdated(date);
                    updateEntity.setUpdatedBy(adminID);

                    if ( id.getAdCompanyId().equals(entity.getAdCompanyId()) == true ) {
                        userService.update(updateEntity);
                    } else {
                        userService.deleteById(id);
                        userService.save(updateEntity);
                    }
                }
                break;
                case DELETE:
                {
                    AdUserEntityPK[] ids = jqgrid.getIds(new String[] {"adUserId", "adCompanyId"});
                    userService.deleteByIds(ids);
                }
                break;
                default: break;
            }
        }// if ( params != null ) {

        return new HashMap<>();
    }
}
