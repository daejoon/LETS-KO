package dd2.local.busi.programAccess.service.dao;

import dd2.com.dao.GenericDAO;
import dd2.com.jqgrid.JqGridRequest;
import dd2.local.entity.AdProgramAccessEntity;
import dd2.local.entity.AdProgramAccessEntityPK;

import java.util.List;
import java.util.Map;

/**
 * Created by KDJ on 13. 12. 10.
 */
public interface ProgramAccessDAO extends GenericDAO<AdProgramAccessEntity, AdProgramAccessEntityPK> {
    List<Map<String,Object>> list(JqGridRequest request);
}
