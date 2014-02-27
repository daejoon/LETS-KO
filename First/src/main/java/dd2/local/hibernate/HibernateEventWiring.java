package dd2.local.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * Created by kdj on 2014. 2. 26..
 */
public final class HibernateEventWiring {
    private static final Log logger = LogFactory.getLog(HibernateEventWiring.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateLoadListener listener;


    @PostConstruct
    public void setListener() {
        EventListenerRegistry registry = ((SessionFactoryImpl) sessionFactory).getServiceRegistry().getService(
                EventListenerRegistry.class);
        registry.getEventListenerGroup(EventType.LOAD).appendListener(listener);
    }
}
