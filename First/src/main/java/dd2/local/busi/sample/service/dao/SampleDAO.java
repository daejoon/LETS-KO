package dd2.local.busi.sample.service.dao;

import dd2.com.dao.GenericDAO;
import dd2.com.jqgrid.JqGridRequest;
import dd2.local.entity.SampleEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by kdj on 2014. 1. 18..
 */
public interface SampleDAO extends GenericDAO<SampleEntity, Long> {
    List<Map<String,Object>> list(JqGridRequest request);
    List<Map<String,Object>> jqGridSelect(Long adCompanyId);
}
