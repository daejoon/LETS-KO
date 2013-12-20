package dd2.local.busi.program.service;

import dd2.com.ajax.AjaxResponse;
import dd2.com.jqgrid.JqGridRequest;
import dd2.com.jqgrid.JqGridResponse;
import dd2.com.service.GenericService;
import dd2.local.entity.AdProgramEntity;
import dd2.local.entity.AdProgramEntityPK;

import java.util.List;
import java.util.Map;

/**
 * Created by KDJ on 13. 12. 4.
 */
public interface ProgramService extends GenericService<AdProgramEntity, AdProgramEntityPK> {
    JqGridResponse list(JqGridRequest request);
    AjaxResponse<List<Map<String,Object>>> jqGridSelect(Long adCompanyId);
}
