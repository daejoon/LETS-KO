package dd2.local.busi.sample.service.dao;

import dd2.com.dao.GenericDAO;
import dd2.com.jqgrid.JqGridRequest;
import dd2.com.jqgrid.JqGridResponseGeneric;
import dd2.local.entity.SampleEntity;

/**
 * Created by kdj on 2014. 1. 18..
 */
public interface SampleDAO extends GenericDAO<SampleEntity, Long> {
    JqGridResponseGeneric<SampleEntity> list(JqGridRequest request);
}
