package dd2.local.busi.userRole.service.dao;

import dd2.com.dao.GenericDAO;
import dd2.com.jqgrid.JqGridRequest;
import dd2.local.entity.AdUserRoleEntity;
import dd2.local.entity.AdUserRoleEntityPK;

import java.util.List;
import java.util.Map;

/**
 * Created by KDJ on 13. 12. 10.
 */
public interface UserRoleDAO extends GenericDAO<AdUserRoleEntity, AdUserRoleEntityPK> {
    List<Map<String,Object>> list(JqGridRequest request);
}
