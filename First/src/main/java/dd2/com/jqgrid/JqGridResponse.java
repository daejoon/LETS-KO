package dd2.com.jqgrid;

import dd2.com.util.JsonUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 11. 13
 * Time: 오후 5:53
 * To change this template use File | Settings | File Templates.
 */
public class JqGridResponse extends JqGridResponseGeneric<Map<String,Object>> {

    public JqGridResponse(JqGridRequest request, List<Map<String,Object>> rows) {
        int records = 0;
        if ( rows.size() > 0 && rows.get(0).containsKey(JqGridQueryBuilder.RECORD_COUNT_NAME) == true ) {
            Map<String,Object> row = rows.get(0);
            records = Integer.parseInt(row.get(JqGridQueryBuilder.RECORD_COUNT_NAME).toString());
        }
        int total = (int)Math.ceil((float) records / request.getPageSize());
        setting(total, request.getPageNumber(), records, rows);
    }
}
