package dd2.local.busi.user.service;

import dd2.com.jqgrid.JqGridRequest;
import dd2.com.jqgrid.JqGridResponse;
import dd2.com.service.GenericService;
import dd2.local.entity.AdUserEntity;
import dd2.local.entity.AdUserEntityPK;

/**
 * Created by KDJ on 13. 12. 4.
 */
public interface UserService extends GenericService<AdUserEntity, AdUserEntityPK> {
    JqGridResponse list(JqGridRequest request);
    void deleteByIds(AdUserEntityPK[] ids);
}
