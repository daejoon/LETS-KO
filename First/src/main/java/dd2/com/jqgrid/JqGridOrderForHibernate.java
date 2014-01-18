package dd2.com.jqgrid;

import org.hibernate.criterion.Order;

/**
 * Created by kdj on 2014. 1. 18..
 */
public final class JqGridOrderForHibernate {

    public static Order create(JqGridRequest request) {
        String sidx = request.getSidx();
        String sord = request.getSord();

        Order order = null;

        switch (sord.toLowerCase()) {
            case "asc":
                order = Order.asc(sidx);
                break;
            case "desc":
                order = Order.desc(sidx);
                break;
            default:
                order = null;
        }

        return order;
    }
}
