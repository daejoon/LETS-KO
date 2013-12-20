package dd2.local.busi.programFunction.service.dao;

import dd2.com.dao.GenericDAO;
import dd2.com.jqgrid.JqGridRequest;
import dd2.local.entity.AdProgramFunctionEntity;
import dd2.local.entity.AdProgramFunctionEntityPK;

import java.util.List;
import java.util.Map;

/**
 * Created by KDJ on 13. 12. 10.
 */
public interface ProgramFunctionDAO extends GenericDAO<AdProgramFunctionEntity, AdProgramFunctionEntityPK> {
    List<Map<String,Object>> list(JqGridRequest request);
    List<Map<String,Object>> jqGridSelect(Long adProgramId, Long adCompanyId);
}
