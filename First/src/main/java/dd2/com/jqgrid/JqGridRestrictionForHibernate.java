package dd2.com.jqgrid;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 * Created by kdj on 2014. 1. 18..
 */
public final class JqGridRestrictionForHibernate {
    public static Criterion create(JqGridRequest request) {
        Criterion criterion = null;
        if ( request.is_search() == true ) {
            String propertyName  = request.getSearchField();
            String propertyValue = request.getSearchString();

            switch (request.getSearchOper().toLowerCase()) {
                case "eq": //같다.
                    criterion = Restrictions.eq(propertyName, propertyValue);
                    break;
                case "ne": //같지않다.
                    criterion = Restrictions.ne(propertyName, propertyValue);
                    break;
                case "lt": //작다
                    criterion = Restrictions.lt(propertyName, propertyValue);
                    break;
                case "le": //작거나 같다.
                    criterion = Restrictions.le(propertyName, propertyValue);
                    break;
                case "gt": //크다.
                    criterion = Restrictions.gt(propertyName, propertyValue);
                    break;
                case "ge": //크거나 같다.
                    criterion = Restrictions.ge(propertyName, propertyValue);
                    break;
                case "bw": //~로 시작한다.
                    criterion = Restrictions.like(propertyName, propertyValue, MatchMode.START);
                    break;
                case "bn": //~로 시작하지 않는다.
                    criterion = Restrictions.not(Restrictions.like(propertyName, propertyValue, MatchMode.START));
                    break;
                case "ew": //~로 끝난다.
                    criterion = Restrictions.like(propertyName, propertyValue, MatchMode.END);
                    break;
                case "en": //~로 끝나지 않는다.
                    criterion = Restrictions.not( Restrictions.like(propertyName, propertyValue, MatchMode.END) );
                    break;
                case "in": // 내에 있다.
                    criterion = Restrictions.in(propertyName, new Object[]{propertyValue});
                    break;
                case "ni": // 내에 있지 않다.
                    criterion = Restrictions.not( Restrictions.in(propertyName, new Object[]{propertyValue}) );
                    break;
                default:
                    criterion = null;
                    break;
            }
        }

        return criterion;
    }
}
