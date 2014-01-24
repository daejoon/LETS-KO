package dd2.local.busi.program.service.dao;

import dd2.com.dao.GenericHibernateDAO;
import dd2.com.jqgrid.JqGridQueryBuilder;
import dd2.com.jqgrid.JqGridRequest;
import dd2.local.entity.AdProgramEntity;
import dd2.local.entity.AdProgramEntityPK;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by KDJ on 13. 12. 4.
 */
@SuppressWarnings("unchecked")
@Repository
public class ProgramDAOHibernate  extends GenericHibernateDAO<AdProgramEntity, AdProgramEntityPK> implements ProgramDAO {
    private static final Log logger = LogFactory.getLog(ProgramDAOHibernate.class);

    @Override
    public List<Map<String, Object>> list(JqGridRequest request) {
        StringBuffer sbQuery = new StringBuffer();
        sbQuery
                .append(" SELECT CONVERT(VARCHAR(19), A.AD_Program_ID) + '_' + CONVERT(VARCHAR(19), A.AD_Company_ID)   AS id                ")
                .append("      , A.AD_Program_ID                                                                    AS adProgramId          ")
                .append("      , A.AD_Company_ID                                                                    AS adCompanyId          ")
                .append("      , A.Name                                                                             AS name                 ")
                .append("      , A.Description                                                                      AS description          ")
                .append("      , A.Url                                                                              AS url                  ")
                .append("      , A.isActive                                                                         AS isActive             ")
                .append("      , A.Created                                                                          AS created              ")
                .append("      , A.CreatedBy                                                                        AS createdBy            ")
                .append("      , A.Updated                                                                          AS updated              ")
                .append("      , A.UpdatedBy                                                                        AS updatedBy            ")
                .append("      , B.Name                                                                             AS createdByName        ")
                .append("      , C.Name                                                                             AS updatedByName        ")
                .append("  FROM AD_Program              AS A                                                                                ")
                .append("       LEFT JOIN AD_User B                                                                                         ")
                .append("            ON   B.AD_User_ID    = A.CreatedBy                                                                     ")
                .append("       LEFT JOIN AD_User C                                                                                         ")
                .append("            ON   C.AD_User_ID    = A.UpdatedBy                                                                     ")
        ;

        JqGridQueryBuilder jqGridQueryBuilder = new JqGridQueryBuilder(sbQuery, request);
        return this.getSession()
                .createSQLQuery(jqGridQueryBuilder.toString())
                .setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
                .list();
    }

    @Override
    public List<Map<String, Object>> jqGridSelect(Long adCompanyId) {
        return this.getCriteria()
                .setProjection(Projections.projectionList()
                        .add(Projections.sqlProjection(
                            "CONVERT(VARCHAR(19), AD_Program_ID) + '_' + CONVERT(VARCHAR(19), AD_Company_ID)   AS id",
                            new String[] { "id" },
                            new Type[] { StandardBasicTypes.STRING }
                        ))
                        .add(Projections.property("adProgramId").as("adProgramId"))
                        .add(Projections.property("adCompanyId").as("adCompanyId"))
                        .add(Projections.property("name").as("name"))
                        .add(Projections.property("description").as("description"))
                        .add(Projections.property("url").as("url"))
                        .add(Projections.property("isActive").as("isActive"))
                )
                .setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
                .list();
    }
}
