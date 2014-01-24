package dd2.local.busi.com.service.dao;

import dd2.com.dao.GenericHibernateDAO;
import dd2.local.entity.CodeRole;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 8. 14
 * Time: 오전 11:44
 * To change this template use File | Settings | File Templates.
 */

@Repository
public class CodeRoleDAOHibernate extends GenericHibernateDAO<CodeRole, Long> implements CodeRoleDAO {
    private static final Log logger = LogFactory.getLog(CodeRoleDAOHibernate.class);

    @SuppressWarnings("unchecked")
    public List<CodeRole> getCodeRoles() {
        return getCriteria()
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .addOrder(Order.asc("id"))
                .list();
    }
}
