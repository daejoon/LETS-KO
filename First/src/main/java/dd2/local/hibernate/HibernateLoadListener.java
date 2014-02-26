package dd2.local.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.event.spi.LoadEvent;
import org.hibernate.event.spi.LoadEventListener;

/**
 * Created by kdj on 2014. 2. 26..
 */
public class HibernateLoadListener implements LoadEventListener {
    private static final Log logger = LogFactory.getLog(HibernateLoadListener.class);

    @Override
    public void onLoad(LoadEvent event, LoadType loadType) throws HibernateException {
        if ( logger.isDebugEnabled() ) {
            logger.debug("EntityClassName: " + event.getEntityClassName());
        }
    }
}
