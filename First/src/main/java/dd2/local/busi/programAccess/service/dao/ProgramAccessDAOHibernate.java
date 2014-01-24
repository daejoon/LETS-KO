package dd2.local.busi.programAccess.service.dao;

import dd2.com.dao.hibernate.GenericHibernateDAO;
import dd2.com.jqgrid.JqGridQueryBuilder;
import dd2.com.jqgrid.JqGridRequest;
import dd2.local.entity.AdProgramAccessEntity;
import dd2.local.entity.AdProgramAccessEntityPK;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by KDJ on 13. 12. 10.
 */
@SuppressWarnings("unchecked")
@Repository
public class ProgramAccessDAOHibernate extends GenericHibernateDAO<AdProgramAccessEntity, AdProgramAccessEntityPK> implements ProgramAccessDAO {
    private static final Log logger = LogFactory.getLog(ProgramAccessDAOHibernate.class);

    @Override
    public List<Map<String, Object>> list(JqGridRequest request) {
        String adCompanyId = request.getUserPostData().get("adCompanyId").toString();
        StringBuffer sbQuery = new StringBuffer();
        sbQuery
                .append(" SELECT CONVERT(VARCHAR(19), A.AD_Program_ID) + '_' + CONVERT(VARCHAR(19), A.AD_Function_ID) + '_' + CONVERT(VARCHAR(19), A.AD_Role_ID) + '_' + CONVERT(VARCHAR(19), A.AD_Company_ID)   AS id ")
                .append("      , A.AD_Program_ID                                                                    AS adProgramId          ")
                .append("      , A.AD_Function_ID                                                                   AS adFunctionId         ")
                .append("      , A.AD_Role_ID                                                                       AS adRoleId             ")
                .append("      , A.AD_Company_ID                                                                    AS adCompanyId          ")
                .append("      , A.hasPermission                                                                    AS hasPermission        ")
                .append("      , A.Created                                                                          AS created              ")
                .append("      , A.CreatedBy                                                                        AS createdBy            ")
                .append("      , A.Updated                                                                          AS updated              ")
                .append("      , A.UpdatedBy                                                                        AS updatedBy            ")
                .append("      , B.Name                                                                             AS createdByName        ")
                .append("      , C.Name                                                                             AS updatedByName        ")
                .append("  FROM AD_Program_Access              AS A                                                                         ")
                .append("       LEFT JOIN AD_User B                                                                                         ")
                .append("            ON   B.AD_User_ID    = A.CreatedBy                                                                     ")
                .append("       LEFT JOIN AD_User C                                                                                         ")
                .append("            ON   C.AD_User_ID    = A.UpdatedBy                                                                     ")
                .append(" WHERE 1 = 1                                                                                                       ")
                //.append("   AND A.AD_Program_ID = ").append(adProgramId).append(" ")
                .append("   AND A.AD_Company_ID = ").append(adCompanyId).append(" ")
        ;

        JqGridQueryBuilder jqGridQueryBuilder = new JqGridQueryBuilder(sbQuery, request);
        return this.getSession()
                .createSQLQuery(jqGridQueryBuilder.toString())
                .setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
                .list();
    }
}
