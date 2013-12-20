package dd2.local.busi.role.service.dao;

import dd2.com.dao.GenericDAO;
import dd2.com.jqgrid.JqGridRequest;
import dd2.local.entity.AdRoleEntity;
import dd2.local.entity.AdRoleEntityPK;

import java.util.List;
import java.util.Map;

/**
 * Created by KDJ on 13. 12. 4.
 */
public interface RoleDAO extends GenericDAO<AdRoleEntity, AdRoleEntityPK> {
    List<Map<String,Object>> list(JqGridRequest request);
    List<Map<String,Object>> jqGridSelect(Long adCompanyId);
}
