package dd2.local.busi.company.service;

import dd2.com.ajax.AjaxResponse;
import dd2.com.jqgrid.JqGridRequest;
import dd2.com.jqgrid.JqGridResponse;
import dd2.com.service.GenericService;
import dd2.local.entity.AdCompanyEntity;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 11. 11
 * Time: 오후 6:32
 * To change this template use File | Settings | File Templates.
 */
public interface CompanyService extends GenericService<AdCompanyEntity, Long> {
    JqGridResponse list(JqGridRequest request);
    AjaxResponse<List<Map<String,Object>>> jqGridSelect();
}
