package dd2.local.busi.com.service.dao;

import dd2.com.dao.GenericDAO;
import dd2.local.entity.CodeRole;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 10. 25
 * Time: 오전 10:19
 * To change this template use File | Settings | File Templates.
 */
public interface CodeRoleDAO extends GenericDAO<CodeRole, Long> {
    List<CodeRole> getCodeRoles();
}
