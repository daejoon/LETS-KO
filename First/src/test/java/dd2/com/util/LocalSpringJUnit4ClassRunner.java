package dd2.com.util;

import org.junit.runners.model.InitializationError;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Log4jConfigurer;

import java.io.FileNotFoundException;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 11. 6
 * Time: 오후 5:53
 * To change this template use File | Settings | File Templates.
 */
public class LocalSpringJUnit4ClassRunner extends SpringJUnit4ClassRunner {

    static {
        try {
            Log4jConfigurer.initLogging("classpath:config/log4j/log4j.xml");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public LocalSpringJUnit4ClassRunner(Class<?> klass) throws InitializationError {
        super(klass);
        init();
    }

    private void init() {
        // jndi initialize
        try {
            bindJndi();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void bindJndi() throws Exception {
        SimpleNamingContextBuilder bulider = SimpleNamingContextBuilder.emptyActivatedContextBuilder();

        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        ds.setUrl("jdbc:sqlserver://10.211.55.7:1433;databaseName=lets_ko;integratedSecurity=false;");
        ds.setUsername("sa");
        ds.setPassword("dnfleod2");
        bulider.bind("jdbc/lets_ko_local", ds);
    }
}
