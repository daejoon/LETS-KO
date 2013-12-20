package dd2.local.busi.com.service;

import dd2.com.service.GenericService;
import dd2.local.entity.CodeRole;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 8. 20
 * Time: 오전 11:00
 * To change this template use File | Settings | File Templates.
 */
public interface CommonService extends GenericService<CodeRole, Long> {
    List<CodeRole> getCodeRoles();
    Long getSeq(String tableName);
}
