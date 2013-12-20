package dd2.local.busi.function.service;

import dd2.com.ajax.AjaxResponse;
import dd2.com.jqgrid.JqGridRequest;
import dd2.com.jqgrid.JqGridResponse;
import dd2.com.service.GenericService;
import dd2.local.entity.AdFunctionEntity;
import dd2.local.entity.AdFunctionEntityPK;

import java.util.List;
import java.util.Map;

/**
 * Created by KDJ on 13. 12. 4.
 */
public interface FunctionService extends GenericService<AdFunctionEntity, AdFunctionEntityPK> {
    JqGridResponse list(JqGridRequest request);
    AjaxResponse<List<Map<String, Object>>> jqGridSelect(Long adCompanyId);
}
