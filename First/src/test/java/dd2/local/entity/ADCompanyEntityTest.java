package dd2.local.entity;

import dd2.com.util.LocalSpringJUnit4ClassRunner;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

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
public class ADCompanyEntityTest {
    @Autowired
    private SessionFactory sessionFactory;
    @Before
    public void setUp() throws Exception {
    }

    @Test
    @Transactional
    public void testSelect() throws Exception {
        Long id = Long.valueOf(1);
        AdCompanyEntity entity = (AdCompanyEntity)sessionFactory.getCurrentSession().get(AdCompanyEntity.class, id);
    }

    @After
    public void tearDown() throws Exception {
    }
}
