package dd2.local.busi.program.service.dao;

import dd2.com.dao.GenericDAO;
import dd2.com.jqgrid.JqGridRequest;
import dd2.local.entity.AdProgramEntity;
import dd2.local.entity.AdProgramEntityPK;

import java.util.List;
import java.util.Map;

/**
 * Created by KDJ on 13. 12. 4.
 */
public interface ProgramDAO extends GenericDAO<AdProgramEntity, AdProgramEntityPK> {
    List<Map<String,Object>> list(JqGridRequest request);
    List<Map<String,Object>> jqGridSelect(Long adCompanyId);
}
