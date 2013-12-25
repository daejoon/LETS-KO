package dd2.local.busi.company.service;

import dd2.com.jqgrid.JqGridResponse;
import dd2.com.util.LocalSpringJUnit4ClassRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 11. 14
 * Time: 오후 2:01
 * To change this template use File | Settings | File Templates.
 */
@RunWith(LocalSpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
        "classpath*:config/spring/context-*.xml",
        "file:WebContent/WEB-INF/config/springmvc/servlet-*.xml"
})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class CompanyServiceTest {
    @Autowired
    private CompanyService companyService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    @Transactional
    public void testList() throws Exception {
//        JqGridResponse data = companyService.list(null);
//        assertTrue(data != null);
    }

    @After
    public void tearDown() throws Exception {
    }
}
