package dd2.com.jqgrid;

/**
 * Created by KDJ on 13. 12. 12.
 * Description: 아무래도 네이티브 쿼리를 사용하다 보니까 각 DB별 종속적인 부분이 생긴다.
 *              성능에 큰 문제가 없다면 Hibernate를 이용하자
 */
public class JqGridQueryBuilder {
    private StringBuffer sb = new StringBuffer();
    public final static String RECORD_COUNT_NAME = "JQGRID_RECORD_COUNT";
    public final static String ROW_NUMBER_NAME   = "JQGRID_ROW_NUMBER";

    public JqGridQueryBuilder(StringBuffer query, JqGridRequest request) {
        setQuery(query, request);
    }

    public JqGridQueryBuilder(String query, JqGridRequest request) {
        setQuery(query,request);
    }

    public void setQuery(String query, JqGridRequest request) {
        sb.setLength(0);

        sb.append(" SELECT R.* ");
        sb.append("   FROM ( SELECT X.* ");
        sb.append("               , COUNT(1) OVER() AS ").append(RECORD_COUNT_NAME).append(" ");
        sb.append("               , ROW_NUMBER() OVER(ORDER BY X.").append(request.getSidx()).append(" ");
        sb.append(request.getSord()).append(") AS ").append(ROW_NUMBER_NAME).append(" ");
        sb.append("            FROM (").append(query).append(") AS X ");
        if ( request.is_search() == true ) {
            sb.append("       WHERE X.").append(request.getSearchField());
            switch (request.getSearchOper().toLowerCase()) {
                case "eq": //같다.
                    sb.append(" = ").append(" '").append(request.getSearchString()).append("' ");
                    break;
                case "ne": //같지않다.
                    sb.append(" <> ").append(" '").append(request.getSearchString()).append("' ");
                    break;
                case "lt": //작다
                    sb.append(" < ").append(" '").append(request.getSearchString()).append("' ");
                    break;
                case "le": //작거나 같다.
                    sb.append(" <= ").append(" '").append(request.getSearchString()).append("' ");
                    break;
                case "gt": //크다.
                    sb.append(" > ").append(" '").append(request.getSearchString()).append("' ");
                    break;
                case "ge": //크거나 같다.
                    sb.append(" >= ").append(" '").append(request.getSearchString()).append("' ");
                    break;
                case "bw": //~로 시작한다.
                    sb.append(" LIKE ").append(" '").append(request.getSearchString()).append("%' ");
                    break;
                case "bn": //~로 시작하지 않는다.
                    sb.append(" NOT LIKE ").append(" '").append(request.getSearchString()).append("%' ");
                    break;
                case "ew": //~로 끝난다.
                    sb.append(" LIKE ").append(" '%").append(request.getSearchString()).append("' ");
                    break;
                case "en": //~로 끝나지 않는다.
                    sb.append(" NOT LIKE ").append(" '%").append(request.getSearchString()).append("' ");
                    break;
                case "cn": // 내에 존재한다.
                    sb.append(" EXISTS ").append(" ('").append(request.getSearchString()).append("') ");
                    break;
                case "nc": // 내에 존재하지 않는다.
                    sb.append(" NOT EXIST ").append(" ('").append(request.getSearchString()).append("') ");
                    break;
                case "in": // 내에 있다.
                    sb.append(" IN ").append(" ('").append(request.getSearchString()).append("') ");
                    break;
                case "ni": // 내에 있지 않다.
                    sb.append(" NOT IN ").append(" ('").append(request.getSearchString()).append("') ");
                    break;
                default:
                    sb.append(" = ").append(" '").append(request.getSearchString()).append("' ");
                    break;
            }
        }
        sb.append("        ) AS R ");
        sb.append("  WHERE CEILING(R.").append(ROW_NUMBER_NAME).append("/").append(request.getPageSize()).append(") = ");
        sb.append(request.getPageNumber() - 1).append(" ");
    }

    public void setQuery(StringBuffer sbQuery, JqGridRequest request) {
        setQuery(sbQuery.toString(), request);
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}
