package dd2.local.busi.programAccess.service;

import dd2.com.jqgrid.JqGridRequest;
import dd2.com.jqgrid.JqGridResponse;
import dd2.com.service.GenericService;
import dd2.local.entity.AdProgramAccessEntity;
import dd2.local.entity.AdProgramAccessEntityPK;

/**
 * Created by KDJ on 13. 12. 10.
 */
public interface ProgramAccessService extends GenericService<AdProgramAccessEntity, AdProgramAccessEntityPK> {
    JqGridResponse list(JqGridRequest request);
}
