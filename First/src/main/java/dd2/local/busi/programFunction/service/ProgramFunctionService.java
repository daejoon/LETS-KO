package dd2.local.busi.programFunction.service;

import dd2.com.ajax.AjaxResponse;
import dd2.com.jqgrid.JqGridRequest;
import dd2.com.jqgrid.JqGridResponse;
import dd2.com.service.GenericService;
import dd2.local.entity.AdProgramFunctionEntity;
import dd2.local.entity.AdProgramFunctionEntityPK;

import java.util.List;
import java.util.Map;

/**
 * Created by KDJ on 13. 12. 10.
 */
public interface ProgramFunctionService extends GenericService<AdProgramFunctionEntity, AdProgramFunctionEntityPK> {
    JqGridResponse list(JqGridRequest request);
    AjaxResponse<List<Map<String,Object>>> jqGridSelect(Long adProgramId, Long adCompanyId);
}
