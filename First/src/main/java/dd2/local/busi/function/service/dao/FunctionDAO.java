package dd2.local.busi.function.service.dao;

import dd2.com.dao.GenericDAO;
import dd2.com.jqgrid.JqGridRequest;
import dd2.local.entity.AdFunctionEntity;
import dd2.local.entity.AdFunctionEntityPK;

import java.util.List;
import java.util.Map;

/**
 * Created by KDJ on 13. 12. 4.
 */
public interface FunctionDAO extends GenericDAO<AdFunctionEntity, AdFunctionEntityPK> {
    List<Map<String,Object>> list(JqGridRequest request);
    List<Map<String,Object>> jqGridSelect(Long adCompanyId);
}
