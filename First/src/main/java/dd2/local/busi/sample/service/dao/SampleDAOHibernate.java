package dd2.local.busi.sample.service.dao;

import dd2.com.dao.GenericHibernateDAO;
import dd2.com.jqgrid.JqGridQueryBuilder;
import dd2.com.jqgrid.JqGridRequest;
import dd2.com.jqgrid.JqGridResponseGeneric;
import dd2.local.entity.SampleEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by kdj on 2014. 1. 18..
 */
@Repository
public class SampleDAOHibernate extends GenericHibernateDAO<SampleEntity, Long> implements SampleDAO {
    private static final Log logger = LogFactory.getLog(SampleDAOHibernate.class);

//    @Override
//    public List<Map<String, Object>> list(JqGridRequest request) {
//        StringBuffer sbQuery = new StringBuffer();
//        sbQuery
//                .append(" SELECT CONVERT(VARCHAR(29), A.AD_Company_ID)  AS id               ")
//                .append("      , A.AD_Company_ID                        AS adCompanyId      ")
//                .append("      , A.isActive                             AS isActive         ")
//                .append("      , A.Created                              AS created          ")
//                .append("      , A.CreatedBy                            AS createdBy        ")
//                .append("      , A.Description                          AS description      ")
//                .append("      , A.Name                                 AS name             ")
//                .append("      , A.Updated                              AS updated          ")
//                .append("      , A.UpdatedBy                            AS updatedBy        ")
//                .append("      , A.Value                                AS value            ")
//                .append("      , B.Name                                 AS createdByName    ")
//                .append("      , C.Name                                 AS updatedByName    ")
//                .append("   FROM AD_Company A                                               ")
//                .append("        LEFT JOIN AD_User B                                        ")
//                .append("             ON   B.AD_User_ID    = A.CreatedBy                    ")
//                .append("        LEFT JOIN AD_User C                                        ")
//                .append("             ON   C.AD_User_ID    = A.UpdatedBy                    ")
//        ;
//
//        JqGridQueryBuilder jqGridQueryBuilder = new JqGridQueryBuilder(sbQuery, request);
//        return this.getSession()
//                .createSQLQuery(jqGridQueryBuilder.toString())
//                .setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
//                .list();
//    }

    @Override
    public JqGridResponseGeneric list(JqGridRequest request) {
        JqGridResponseGeneric<SampleEntity> jqGridResponseGeneric = new JqGridResponseGeneric<>();

        return jqGridResponseGeneric;
    }
}
