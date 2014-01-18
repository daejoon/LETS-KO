package dd2.local.busi.sample.service.dao;

import dd2.com.dao.GenericHibernateDAO;
import dd2.com.jqgrid.JqGridOrderForHibernate;
import dd2.com.jqgrid.JqGridRequest;
import dd2.com.jqgrid.JqGridResponseGeneric;
import dd2.com.jqgrid.JqGridRestrictionForHibernate;
import dd2.local.entity.SampleEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.*;
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

        Criterion criterion = JqGridRestrictionForHibernate.create(request);
        Order order = JqGridOrderForHibernate.create(request);

        Criteria rowCriteria = this.getCriteria()
                .setProjection(Projections.projectionList()
                        .add(Projections.rowCount())
                );
        if ( criterion != null ) {
            rowCriteria = rowCriteria.add(criterion);
        }
        if ( order != null ) {
            rowCriteria = rowCriteria.addOrder(order);
        }
        int total = Integer.parseInt(rowCriteria.uniqueResult().toString());


        Criteria listCriteria = this.getCriteria();
        if (criterion != null) {
            listCriteria = listCriteria.add(criterion);
        }
        if (order != null) {
            listCriteria = listCriteria.addOrder(order);
        }
        List<SampleEntity> sampleEntityList = listCriteria
                .setFirstResult(request.getPageNumber() - 1)
                .setMaxResults(request.getPageSize())
                .list();

        response.setTotal(total);
        response.setPage(request.getPageNumber());
        response.setRecords(request.getPageSize());
        response.setRows(sampleEntityList);

        return response;
    }
}
