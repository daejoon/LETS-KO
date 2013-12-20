package dd2.local.entity;

import dd2.com.util.LocalSpringJUnit4ClassRunner;
import dd2.local.busi.com.service.dao.ADSeqDAO;
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
 * Date: 13. 11. 11
 * Time: 오후 5:42
 * To change this template use File | Settings | File Templates.
 */
@RunWith(LocalSpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
        "classpath*:config/spring/context-*.xml"
        , "file:WebContent/WEB-INF/config/springmvc/servlet-*.xml"}
)
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class ADSeqTest {
    @Autowired
    private ADSeqDAO adSeqDAO;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    @Transactional
    public void testGetSeq() throws Exception {
//        assertTrue("실패", adSeqDAO.getSeq( "AD_Company" ) > 0);
    }

    @After
    public void tearDown() throws Exception {
    }
}
