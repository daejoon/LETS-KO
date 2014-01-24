package dd2.local.busi.user.service.dao;

import dd2.com.dao.GenericHibernateDAO;
import dd2.com.jqgrid.JqGridQueryBuilder;
import dd2.com.jqgrid.JqGridRequest;
import dd2.local.entity.AdUserEntity;
import dd2.local.entity.AdUserEntityPK;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by KDJ on 13. 12. 4.
 */
@SuppressWarnings("unchecked")
@Repository
public class UserDAOHibernate extends GenericHibernateDAO<AdUserEntity, AdUserEntityPK> implements UserDAO {
    private static final Log logger = LogFactory.getLog(UserDAOHibernate.class);

    @Override
    public List<Map<String, Object>> list(JqGridRequest request) {
        StringBuffer sbQuery = new StringBuffer();
        sbQuery
                .append(" SELECT CONVERT(VARCHAR(19), A.AD_User_ID) + '_' + CONVERT(VARCHAR(19), A.AD_Company_ID)   AS id                   ")
                .append("      , A.AD_User_ID                                                                       AS adUserId             ")
                .append("      , A.AD_Company_ID                                                                    AS adCompanyId          ")
                .append("      , A.Name                                                                             AS name                 ")
                .append("      , A.Password                                                                         AS password             ")
                .append("      , A.Description                                                                      AS description          ")
                .append("      , A.Title                                                                            AS title                ")
                .append("      , A.EMail                                                                            AS eMail                ")
                .append("      , A.Fax                                                                              AS fax                  ")
                .append("      , A.Phone                                                                            AS phone                ")
                .append("      , A.Phone2                                                                           AS phone2               ")
                .append("      , A.isActive                                                                         AS isActive             ")
                .append("      , A.Created                                                                          AS created              ")
                .append("      , A.CreatedBy                                                                        AS createdBy            ")
                .append("      , A.Updated                                                                          AS updated              ")
                .append("      , A.UpdatedBy                                                                        AS updatedBy            ")
                .append("      , B.Name                                                                             AS createdByName        ")
                .append("      , C.Name                                                                             AS updatedByName        ")
                .append("  FROM AD_User              AS A                                                                                   ")
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
}
