package dd2.com.jqgrid;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 11. 14
 * Time: 오후 2:39
 * To change this template use File | Settings | File Templates.
 */
public class JqGridRequest implements Serializable {
    private boolean _search = false;
    private String nd = "";
    private String _ = "";
    private int pageNumber = 1;
    private int pageSize = 200;
    private String sidx = "";
    private String sord = "";
    private String filters = "";
    private String searchOper = "";
    private String searchString = "";   // 검색 문자
    private String searchField = "";    // 검색 필드
    private String _local_jqgrid_guid_ = "";
    private boolean userCache = false;
    private Map<String, Object> userPostData = new HashMap<>();

    public boolean is_search() {
        return _search;
    }

    public void set_search(boolean _search) {
        this._search = _search;
    }

    public String getNd() {
        return nd;
    }

    public void setNd(String nd) {
        this.nd = nd;
    }

    public String get_() {
        return _;
    }

    public void set_(String _) {
        this._ = _;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public String getSord() {
        return sord;
    }

    public void setSord(String sord) {
        this.sord = sord;
    }

    public String getFilters() {
        return filters;
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }

    public String getSearchOper() {
        return searchOper;
    }

    public void setSearchOper(String searchOper) {
        this.searchOper = searchOper;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    public String get_local_jqgrid_guid_() {
        return _local_jqgrid_guid_;
    }

    public void set_local_jqgrid_guid_(String _local_jqgrid_guid_) {
        this._local_jqgrid_guid_ = _local_jqgrid_guid_;
    }

    public boolean isUserCache() {
        return userCache;
    }

    public void setUserCache(boolean userCache) {
        this.userCache = userCache;
    }

    public Map<String, Object> getUserPostData() {
        return userPostData;
    }

    public void setUserPostData(Map<String, Object> userPostData) {
        this.userPostData = userPostData;
    }
}
