package dd2.local.entity;

import dd2.com.util.LocalSpringJUnit4ClassRunner;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.RootEntityResultTransformer;
import org.hibernate.transform.Transformers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 11. 11
 * Time: 오전 10:26
 * To change this template use File | Settings | File Templates.
 */
@RunWith(LocalSpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
        "classpath*:config/spring/context-*.xml",
        "file:WebContent/WEB-INF/config/springmvc/servlet-*.xml"
})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class AdUser2EntityTEST {
    @Autowired
    private SessionFactory sessionFactory;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    @Transactional
    public void testInsert() throws Exception {
        AdUser2Entity user = (AdUser2Entity)this.sessionFactory
                .getCurrentSession().get(AdUser2Entity.class, new Long(1));

        Example exampleUser = Example.create(user)
                .ignoreCase()
                .enableLike(MatchMode.ANYWHERE)
                .excludeZeroes();

        List result = this.sessionFactory.getCurrentSession()
                .createCriteria(AdUser2Entity.class)
                .add(exampleUser)
                .list();
    }

    @After
    public void tearDown() throws Exception {
    }
}
