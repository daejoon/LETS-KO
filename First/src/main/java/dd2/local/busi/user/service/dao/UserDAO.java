package dd2.local.busi.user.service.dao;

import dd2.com.dao.GenericDAO;
import dd2.com.jqgrid.JqGridRequest;
import dd2.local.entity.AdUserEntity;
import dd2.local.entity.AdUserEntityPK;

import java.util.List;
import java.util.Map;

/**
 * Created by KDJ on 13. 12. 4.
 */
public interface UserDAO extends GenericDAO<AdUserEntity, AdUserEntityPK> {
    List<Map<String,Object>> list(JqGridRequest request);
}
