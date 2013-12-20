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
public class JqGridResponse implements Serializable {
    private int total;
    private int page;
    private int records;
    private String idName = "id";
    private Map<String,Object> userdata = new HashMap<>();
    private List<Map<String,Object>> rows = new ArrayList<>();

    private void setting(int total, int page, int records, List<Map<String,Object>> rows) {
        this.total      = total;
        this.page       = page;
        this.records    = records;
        this.rows       = rows;
    }

    public JqGridResponse() {
    }

    public JqGridResponse(int total, int page, int records, List<Map<String,Object>> rows) {
        setting(total, page, records, rows);
    }

    public JqGridResponse(JqGridRequest request, List<Map<String,Object>> rows) {
        int records = 0;
        if ( rows.size() > 0 && rows.get(0).containsKey(JqGridQueryBuilder.RECORD_COUNT_NAME) == true ) {
            Map<String,Object> row = rows.get(0);
            records = Integer.parseInt(row.get(JqGridQueryBuilder.RECORD_COUNT_NAME).toString());
        }
        int total = (int)Math.ceil((float) records / request.getPageSize());
        setting(total, request.getPageNumber(), records, rows);
    }

    /**
     * Total number of pages
     * @return
     */
    public int getTotal() {
        return total;
    }

    /**
     * Total number of pages
     * @return
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * The current page number
     * @return
     */
    public int getPage() {
        return page;
    }

    /**
     * The current page number
     * @return
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * Total number of records
     * @return
     */
    public int getRecords() {
        return records;
    }

    /**
     * Total number of records
     * @return
     */
    public void setRecords(int records) {
        this.records = records;
    }

    /**
     * jqGrid id Name
     * @return
     */
    public String getIdName() {
        return idName;
    }

    /**
     * jqGrid id Name
     * @return
     */
    public void setIdName(String idName) {
        this.idName = idName;
    }

    /**
     * UserData
     * @return
     */
    public Map<String, Object> getUserdata() {
        return userdata;
    }

    /**
     * UserData
     * @return
     */
    public void setUserdata(Map<String, Object> userdata) {
        this.userdata = userdata;
    }

    /**
     * The actual data
     * @return
     */
    public List<Map<String,Object>> getRows() {
        return rows;
    }

    /**
     * The actual data
     * @return
     */
    public void setRows(List<Map<String,Object>> rows) {
        this.rows = rows;
    }

    /**
     * Parsing Object to Json String
     * @return
     */
    @Override
    public String toString() {
        String result = "";
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("page", page);
            map.put("total", total);
            map.put("records", records);
            map.put("userdata", userdata);
            map.put("rows", rows);
            result = JsonUtil.toJsonString(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
