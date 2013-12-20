package dd2.local.busi.company.service.dao;

import dd2.com.dao.GenericDAO;
import dd2.com.jqgrid.JqGridRequest;
import dd2.local.entity.AdCompanyEntity;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 11. 11
 * Time: 오후 6:36
 * To change this template use File | Settings | File Templates.
 */
public interface CompanyDAO extends GenericDAO<AdCompanyEntity, Long> {
    List<Map<String,Object>> list(JqGridRequest request);
    List<Map<String,Object>> jqGridSelect();
}
