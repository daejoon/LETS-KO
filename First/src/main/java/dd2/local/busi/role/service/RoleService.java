package dd2.local.busi.role.service;

import dd2.com.ajax.AjaxResponse;
import dd2.com.jqgrid.JqGridRequest;
import dd2.com.jqgrid.JqGridResponse;
import dd2.com.service.GenericService;
import dd2.local.entity.AdRoleEntity;
import dd2.local.entity.AdRoleEntityPK;

import java.util.List;
import java.util.Map;

/**
 * Created by KDJ on 13. 12. 4.
 */
public interface RoleService extends GenericService<AdRoleEntity, AdRoleEntityPK> {
    JqGridResponse list(JqGridRequest request);
    AjaxResponse<List<Map<String, Object>>> jqGridSelect(Long adCompanyId);
}
