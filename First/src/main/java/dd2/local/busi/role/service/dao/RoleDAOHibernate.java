package dd2.local.busi.role.service.dao;

import dd2.com.dao.hibernate.GenericHibernateDAO;
import dd2.com.jqgrid.JqGridQueryBuilder;
import dd2.com.jqgrid.JqGridRequest;
import dd2.local.entity.AdRoleEntity;
import dd2.local.entity.AdRoleEntityPK;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by KDJ on 13. 12. 4.
 */
@SuppressWarnings("unchecked")
@Repository
public class RoleDAOHibernate extends GenericHibernateDAO<AdRoleEntity, AdRoleEntityPK> implements RoleDAO {
    private static final Log logger = LogFactory.getLog(RoleDAOHibernate.class);

    @Override
    public List<Map<String, Object>> list(JqGridRequest request) {
        String adCompanyId = request.getUserPostData().get("adCompanyId").toString();
        StringBuffer sbQuery = new StringBuffer();
        sbQuery
                .append(" SELECT CONVERT(VARCHAR(19), A.AD_Role_ID) + '_' + CONVERT(VARCHAR(19), A.AD_Company_ID)   AS id                   ")
                .append("      , A.AD_Role_ID                                                                       AS adRoleId             ")
                .append("      , A.AD_Company_ID                                                                    AS adCompanyId          ")
                .append("      , A.Value                                                                            AS value                ")
                .append("      , A.Name                                                                             AS name                 ")
                .append("      , A.Description                                                                      AS description          ")
                .append("      , A.isActive                                                                         AS isActive             ")
                .append("      , A.Created                                                                          AS created              ")
                .append("      , A.CreatedBy                                                                        AS createdBy            ")
                .append("      , A.Updated                                                                          AS updated              ")
                .append("      , A.UpdatedBy                                                                        AS updatedBy            ")
                .append("      , B.Name                                                                             AS createdByName        ")
                .append("      , C.Name                                                                             AS updatedByName        ")
                .append("  FROM AD_Role              AS A                                                                                   ")
                .append("       LEFT JOIN AD_User B                                                                                         ")
                .append("            ON   B.AD_User_ID    = A.CreatedBy                                                                     ")
                .append("       LEFT JOIN AD_User C                                                                                         ")
                .append("            ON   C.AD_User_ID    = A.UpdatedBy                                                                     ")
                .append(" WHERE A.AD_Company_ID = ").append(adCompanyId).append(" ")
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
                .add(Restrictions.eq("adCompanyId", adCompanyId))
                .add(Restrictions.eq("isActive", "Y"))
                .setProjection(Projections.projectionList()
                        .add(Projections.property("adRoleId").as("adRoleId"))
                        .add(Projections.property("adCompanyId").as("adCompanyId"))
                        .add(Projections.property("value").as("value"))
                        .add(Projections.property("name").as("name"))
                )
                .setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
                .list();
    }
}
