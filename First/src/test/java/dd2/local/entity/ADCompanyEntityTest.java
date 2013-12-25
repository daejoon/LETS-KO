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
    public void testInsert() throws Exception {
//        Long userId = new Long(1);
//        Date date = new Date();
//
//        for (int idx = 2; idx < 21; idx++) {
//            AdCompanyEntity adCompanyEntity = new AdCompanyEntity();
//            adCompanyEntity.setAdCompanyId(new Long(idx));
//            adCompanyEntity.setCreatedBy(userId);
//            adCompanyEntity.setUpdatedBy(userId);
//            adCompanyEntity.setName("Test Company name " + idx);
//            adCompanyEntity.setDescription(" 테스트 컴페니 입니다.");
//            adCompanyEntity.setValue("Test Company value " + idx);
//            adCompanyEntity.setActive("Y");
//            adCompanyEntity.setCreated(date);
//            adCompanyEntity.setUpdated(date);
//            this.sessionFactory.getCurrentSession().saveOrUpdate(adCompanyEntity);
//        }
    }

    @After
    public void tearDown() throws Exception {
    }
}
