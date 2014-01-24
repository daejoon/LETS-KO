package dd2.local.busi.sample.service;

import dd2.com.dao.GenericDAO;
import dd2.com.jqgrid.JqGridRequest;
import dd2.com.jqgrid.JqGridResponseGeneric;
import dd2.com.service.GenericHibernateService;
import dd2.local.busi.sample.service.dao.SampleDAO;
import dd2.local.entity.SampleEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kdj on 2014. 1. 18..
 */
@Service
public class SampleServiceHibernate extends GenericHibernateService<SampleEntity, Long> implements SampleService  {
    private static final Log logger = LogFactory.getLog(SampleServiceHibernate.class);

    @Autowired
    private SampleDAO sampleDAO;

    @Override
    protected GenericDAO<SampleEntity, Long> getGenericDAO() {
        return sampleDAO;
    }

    @Transactional
    @Override
    public JqGridResponseGeneric<SampleEntity> list(JqGridRequest request) {
        return sampleDAO.list(request);
    }
}
