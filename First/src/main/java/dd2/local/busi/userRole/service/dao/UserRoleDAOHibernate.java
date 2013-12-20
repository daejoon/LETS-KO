package dd2.local.busi.userRole.service.dao;

import dd2.com.dao.GenericHibernateDAO;
import dd2.com.jqgrid.JqGridQueryBuilder;
import dd2.com.jqgrid.JqGridRequest;
import dd2.local.entity.AdUserRoleEntity;
import dd2.local.entity.AdUserRoleEntityPK;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.Map;

/**
 * Created by KDJ on 13. 12. 10.
 */
@SuppressWarnings("unchecked")
@Repository
public class UserRoleDAOHibernate extends GenericHibernateDAO<AdUserRoleEntity, AdUserRoleEntityPK> implements UserRoleDAO {
    private static final Log logger = LogFactory.getLog(UserRoleDAOHibernate.class);

    @Override
    public List<Map<String, Object>> list(JqGridRequest request) {
        String adUserId = request.getUserPostData().get("adUserId").toString();
        String adCompanyId = request.getUserPostData().get("adCompanyId").toString();

        StringBuffer sbQuery = new StringBuffer();
        sbQuery
                .append(" SELECT CONVERT(VARCHAR(19), A.AD_User_ID) + '_' + CONVERT(VARCHAR(19), A.AD_Role_ID) + '_' + CONVERT(VARCHAR(19), A.AD_Company_ID)   AS id ")
                .append("      , A.AD_User_ID                                AS adUserId            ")
                .append("      , A.AD_Role_ID                                AS adRoleId            ")
                .append("      , A.AD_Company_ID                             AS adCompanyId         ")
                .append("      , A.Created                                   AS created             ")
                .append("      , A.CreatedBy                                 AS createdBy           ")
                .append("      , A.Updated                                   AS updated             ")
                .append("      , A.UpdatedBy                                 AS updatedBy           ")
                .append("      , B.Name                                      AS createdByName       ")
                .append("      , C.Name                                      AS updatedByName       ")
                .append("  FROM AD_User_Role                                 AS A                   ")
                .append("      LEFT JOIN AD_User B                                                  ")
                .append("           ON   B.AD_User_ID    = A.CreatedBy                              ")
                .append("      LEFT JOIN AD_User C                                                  ")
                .append("           ON   C.AD_User_ID    = A.UpdatedBy                              ")
                .append(" WHERE A.AD_User_ID    = ").append(adUserId).append("                      ")
                .append("   AND A.AD_Company_ID = ").append(adCompanyId).append("                   ")
        ;

        JqGridQueryBuilder jqGridQueryBuilder = new JqGridQueryBuilder(sbQuery, request);
        return this.getSession()
                .createSQLQuery(jqGridQueryBuilder.toString())
                .setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
                .list();
    }
}
