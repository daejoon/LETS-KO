package dd2.local.busi.sample.service.dao;

import dd2.com.dao.GenericHibernateDAO;
import dd2.com.jqgrid.JqGridRequest;
import dd2.com.jqgrid.JqGridResponseGeneric;
import dd2.local.entity.SampleEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kdj on 2014. 1. 18..
 */
@Repository
@SuppressWarnings("unchecked")
public class SampleDAOHibernate extends GenericHibernateDAO<SampleEntity, Long> implements SampleDAO {
    private static final Log logger = LogFactory.getLog(SampleDAOHibernate.class);

    @Override
    public JqGridResponseGeneric<SampleEntity> list(JqGridRequest request) {
        JqGridResponseGeneric<SampleEntity> response = new JqGridResponseGeneric<>();

        int total = Integer.parseInt(this.getCriteria()
                .setProjection(Projections.projectionList()
                        .add(Projections.rowCount())
                )
                .uniqueResult().toString());


        List<SampleEntity> sampleEntityList = this.getCriteria()
                .setFirstResult(request.getPageNumber())
                .setMaxResults(request.getPageSize())
                .list();

        response.setTotal(total);
        response.setPage(request.getPageNumber());
        response.setRecords(request.getPageSize());
        response.setRows(sampleEntityList);

        return response;
    }
}
