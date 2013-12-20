package dd2.local.busi.userRole.service;

import dd2.com.jqgrid.JqGridRequest;
import dd2.com.jqgrid.JqGridResponse;
import dd2.com.service.GenericService;
import dd2.local.entity.AdUserRoleEntity;
import dd2.local.entity.AdUserRoleEntityPK;

/**
 * Created by KDJ on 13. 12. 10.
 */
public interface UserRoleService extends GenericService<AdUserRoleEntity, AdUserRoleEntityPK> {
    JqGridResponse list(JqGridRequest request);
}
