package dd2.local.busi.role.service;

import dd2.com.ajax.AjaxResponse;
import dd2.com.dao.GenericDAO;
import dd2.com.jqgrid.JqGridRequest;
import dd2.com.jqgrid.JqGridResponse;
import dd2.com.service.GenericHibernateService;
import dd2.local.entity.AdRoleEntity;
import dd2.local.entity.AdRoleEntityPK;
import dd2.local.busi.role.service.dao.RoleDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by KDJ on 13. 12. 4.
 */
@Service
public class RoleServiceImpl extends GenericHibernateService<AdRoleEntity, AdRoleEntityPK> implements RoleService {
    private static final Log logger = LogFactory.getLog(RoleServiceImpl.class);

    @Autowired
    private RoleDAO roleDAO;

    @Override
    protected GenericDAO<AdRoleEntity, AdRoleEntityPK> getGenericDAO() {
        return roleDAO;
    }

    @Transactional
    @Override
    public JqGridResponse list(JqGridRequest request) {
        return new JqGridResponse(request, roleDAO.list(request));
    }

    @Transactional
    @Override
    public AjaxResponse<List<Map<String, Object>>> jqGridSelect(Long adCompanyId) {
        AjaxResponse<List<Map<String, Object>>> result = new AjaxResponse<>();
        result.setData(roleDAO.jqGridSelect(adCompanyId));
        result.setStatus(true);
        return result;
    }
}
