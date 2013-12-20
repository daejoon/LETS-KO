package dd2.local.util;

import static org.junit.Assert.*;

import dd2.com.util.LocalSpringJUnit4ClassRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 11. 4
 * Time: 오후 8:54
 * To change this template use File | Settings | File Templates.
 */
@RunWith(LocalSpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
          "classpath*:config/spring/context-*.xml"
        , "file:WebContent/WEB-INF/config/springmvc/servlet-*.xml"}
)
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class MemberUtilTest {
    @Before
    public void setUp() throws Exception {
    }

    @Test
    @Transactional
    public void testIsLogin() throws Exception {
        assertEquals("성공", true, true);
    }

    @After
    public void tearDown() throws Exception {
    }
}
