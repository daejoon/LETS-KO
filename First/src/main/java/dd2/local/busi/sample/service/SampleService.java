package dd2.local.busi.sample.service;

import dd2.com.jqgrid.JqGridRequest;
import dd2.com.jqgrid.JqGridResponseGeneric;
import dd2.com.service.GenericService;
import dd2.local.entity.SampleEntity;

/**
 * Created by kdj on 2014. 1. 18..
 */
public interface SampleService extends GenericService<SampleEntity, Long> {
    JqGridResponseGeneric<SampleEntity> list(JqGridRequest request);
}
