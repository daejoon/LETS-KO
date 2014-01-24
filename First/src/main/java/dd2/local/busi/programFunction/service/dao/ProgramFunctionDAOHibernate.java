package dd2.local.busi.programFunction.service.dao;

import dd2.com.dao.GenericHibernateDAO;
import dd2.com.jqgrid.JqGridQueryBuilder;
import dd2.com.jqgrid.JqGridRequest;
import dd2.local.entity.AdProgramFunctionEntity;
import dd2.local.entity.AdProgramFunctionEntityPK;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by KDJ on 13. 12. 10.
 */
@SuppressWarnings("unchecked")
@Repository
public class ProgramFunctionDAOHibernate extends GenericHibernateDAO<AdProgramFunctionEntity, AdProgramFunctionEntityPK> implements ProgramFunctionDAO {
    private static final Log logger = LogFactory.getLog(ProgramFunctionDAOHibernate.class);

    @Override
    public List<Map<String, Object>> list(JqGridRequest request) {
        String adProgramId = request.getUserPostData().get("adProgramId").toString();
        String adCompanyId = request.getUserPostData().get("adCompanyId").toString();

        StringBuffer sbQuery = new StringBuffer();
        sbQuery
                .append(" SELECT CONVERT(VARCHAR(19), A.AD_Program_ID) + '_' + CONVERT(VARCHAR(19), A.AD_Function_ID) + '_' + CONVERT(VARCHAR(19), A.AD_Company_ID)   AS id ")
                .append("      , A.AD_Program_ID                                                                    AS adProgramId          ")
                .append("      , A.AD_Function_ID                                                                   AS adFunctionId         ")
                .append("      , A.AD_Company_ID                                                                    AS adCompanyId          ")
                .append("      , A.Created                                                                          AS created              ")
                .append("      , A.CreatedBy                                                                        AS createdBy            ")
                .append("      , A.Updated                                                                          AS updated              ")
                .append("      , A.UpdatedBy                                                                        AS updatedBy            ")
                .append("      , B.Name                                                                             AS createdByName        ")
                .append("      , C.Name                                                                             AS updatedByName        ")
                .append(" FROM AD_Program_Function              AS A                                                                        ")
                .append("      LEFT JOIN AD_User B                                                                                          ")
                .append("           ON   B.AD_User_ID    = A.CreatedBy                                                                      ")
                .append("      LEFT JOIN AD_User C                                                                                          ")
                .append("           ON   C.AD_User_ID    = A.UpdatedBy                                                                      ")
                .append(" WHERE 1 = 1                                                                                                       ")
                .append("   AND A.AD_Program_ID = ").append(adProgramId).append(" ")
                .append("   AND A.AD_Company_ID = ").append(adCompanyId).append(" ")
        ;

        JqGridQueryBuilder jqGridQueryBuilder = new JqGridQueryBuilder(sbQuery, request);
        return this.getSession()
                .createSQLQuery(jqGridQueryBuilder.toString())
                .setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
                .list();
    }

    @Override
    public List<Map<String, Object>> jqGridSelect(Long adProgramId, Long adCompanyId) {
        return this.getCriteria()
                .add(Restrictions.eq("adProgramId", adProgramId))
                .add(Restrictions.eq("adCompanyId", adCompanyId))
                .setProjection(Projections.projectionList()
                        .add(Projections.sqlProjection(
                                "CONVERT(VARCHAR(19), A.AD_Program_ID) + '_' + CONVERT(VARCHAR(19), A.AD_Function_ID) + '_' + CONVERT(VARCHAR(19), A.AD_Company_ID)   AS id",
                                new String[] { "id" },
                                new Type[] { StandardBasicTypes.STRING }
                        ))
                        .add(Projections.property("adProgramId").as("adProgramId"))
                        .add(Projections.property("adFunctionId").as("adFunctionId"))
                        .add(Projections.property("adCompanyId").as("adCompanyId"))
                )
                .setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
                .list();
    }
}
