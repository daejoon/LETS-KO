package dd2.local.busi.company.service.dao;

import dd2.com.util.LocalSpringJUnit4ClassRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;


/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 11. 13
 * Time: 오후 1:30
 * To change this template use File | Settings | File Templates.
 */
@RunWith(LocalSpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
        "classpath*:config/spring/context-*.xml",
        "file:WebContent/WEB-INF/config/springmvc/servlet-*.xml"
})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
public class CompanyDAOTest {
    @Autowired
    private CompanyDAO companyDAO;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    @Transactional
    public void testList() throws Exception {
//        List list = companyDAO.list(null);
//        assertTrue(list != null && list.size() >0);
    }

    @After
    public void tearDown() throws Exception {
    }
}
