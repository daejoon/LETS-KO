package dd2.local.busi.member.service.dao;

import dd2.com.dao.hibernate.GenericHibernateDAO;
import dd2.local.entity.Member;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
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
public class MemberDAOHibernate extends GenericHibernateDAO<Member, Long> implements MemberDAO {
    private static final Log logger = LogFactory.getLog(MemberDAOHibernate.class);

    @SuppressWarnings("unchecked")
    public List<Member> getMemberAll() {
        return getCriteria()
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .setFetchMode("roles", FetchMode.JOIN)
                .list();
    }

    /**
     * Username으로 Member 오브젝트를 찾는다.
     * @param username
     * @return
     */
    @SuppressWarnings("unchecked")
    public Member getMemberByName(String username) {
        return (Member)getCriteria()
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .setFetchMode("roles", FetchMode.JOIN)
                .add(Restrictions.eq("username", username))
                .uniqueResult();
    }
}
