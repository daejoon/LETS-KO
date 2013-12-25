package dd2.com.db;

import dd2.com.util.ConfigUtil;
import dd2.com.util.LocalSpringJUnit4ClassRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;

/**
 * Created by kdj on 2013. 12. 25..
 */
@RunWith(LocalSpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
        "classpath*:config/spring/context-*.xml",
        "file:WebContent/WEB-INF/config/springmvc/servlet-*.xml"
})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class MariaDBConnectTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    @Transactional
    public void testConnect() throws Exception {
        Connection connect = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String driverClassName  = ConfigUtil.getString("dataSources.ds02.driverClassName");
        String url              = ConfigUtil.getString("dataSources.ds02.url");
        String userName         = ConfigUtil.getString("dataSources.ds02.username");
        String password         = ConfigUtil.getString("dataSources.ds02.password");
        String jndiName         = ConfigUtil.getString("dataSources.ds02.jndiName");

        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName(driverClassName);
            // Setup the connection with the DB
            connect = DriverManager.getConnection(url + "?user=" + userName + "&password=" + password);

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement
                    .executeQuery("select * from User");

        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }

                if (statement != null) {
                    statement.close();
                }

                if (connect != null) {
                    connect.close();
                }
            } catch (Exception e) {
            }
        }

    }

    @After
    public void tearDown() throws Exception {
    }
}
