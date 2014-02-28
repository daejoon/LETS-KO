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
        String driverClassName  = ConfigUtil.getString("dataSources.ds02.driverClassName");
        String url              = ConfigUtil.getString("dataSources.ds02.url");
        String userName         = ConfigUtil.getString("dataSources.ds02.username");
        String password         = ConfigUtil.getString("dataSources.ds02.password");
        String jndiName         = ConfigUtil.getString("dataSources.ds02.jndiName");

        SimpleNamingContextBuilder bulider = SimpleNamingContextBuilder.emptyActivatedContextBuilder();

        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(driverClassName);
        ds.setUrl(url);
        ds.setUsername(userName);
        ds.setPassword(password);
        bulider.bind(jndiName, ds);
    }
}
