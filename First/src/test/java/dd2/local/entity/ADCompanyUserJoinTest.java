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

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 11. 11
 * Time: 오후 2:16
 * To change this template use File | Settings | File Templates.
 */
@RunWith(LocalSpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
        "classpath*:config/spring/context-*.xml"
        , "file:WebContent/WEB-INF/config/springmvc/servlet-*.xml"}
)
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class ADCompanyUserJoinTest {
    @Autowired
    private SessionFactory sessionFactory;

    private AdCompanyEntity adCompanyEntity = new AdCompanyEntity();
    private AdUserEntity adUserEntity = new AdUserEntity();

    @Before
    public void setUp() throws Exception {
//        Long adminId = new Long(1);
//        Date date = new Date();
//
//        adCompanyEntity.setCreatedBy(adminId);
//        adCompanyEntity.setUpdatedBy(adminId);
//        adCompanyEntity.setName("Test Company name");
//        adCompanyEntity.setDescription(" 테스트 컴페니 입니다. ");
//        adCompanyEntity.setValue("Test Company value");
//        adCompanyEntity.setActive("Y");
//        adCompanyEntity.setCreated(date);
//        adCompanyEntity.setUpdated(date);
//
//        adCompanyEntity = (AdCompanyEntity)sessionFactory.getCurrentSession().get(AdCompanyEntity.class, new Long(21));
//
//        adUserEntity.setActive("Y");
//        adUserEntity.setCreated(date);
//        adUserEntity.setCreatedBy(adminId);
//        adUserEntity.setUpdated(date);
//        adUserEntity.setUpdatedBy(adminId);
//        adUserEntity.setDescription("테스트 유저");
//        adUserEntity.seteMail("test@test.co.kr");
//        adUserEntity.setPhone("010-1111-1234");
//        adUserEntity.setPhone2("010-1111-1234");
//        adUserEntity.setFax("02-1111-1111");
//        adUserEntity.setName("테스트");
//        adUserEntity.setPassword("Password");
//        adUserEntity.setTitle("사원");
//        adUserEntity.setAdCompanyId(adCompanyEntity.getAdCompanyId());
//
//        sessionFactory.getCurrentSession().saveOrUpdate(adUserEntity);
    }

    @Test
    @Transactional
    public void testSelect() {
        assertEquals(true, true);
    }

    @After
    public void tearDown() throws Exception {
    }
}
