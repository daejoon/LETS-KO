package dd2.local.busi.userRole.service;

import dd2.com.dao.GenericDAO;
import dd2.com.jqgrid.JqGridRequest;
import dd2.com.jqgrid.JqGridResponse;
import dd2.com.service.GenericHibernateService;
import dd2.local.busi.userRole.service.dao.UserRoleDAO;
import dd2.local.entity.AdUserRoleEntity;
import dd2.local.entity.AdUserRoleEntityPK;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by KDJ on 13. 12. 10.
 */
@Service
public class UserRoleServiceImpl extends GenericHibernateService<AdUserRoleEntity, AdUserRoleEntityPK> implements UserRoleService {
    private static final Log logger = LogFactory.getLog(UserRoleServiceImpl.class);

    @Autowired
    private UserRoleDAO userRoleDAO;

    @Override
    protected GenericDAO<AdUserRoleEntity, AdUserRoleEntityPK> getGenericDAO() {
        return userRoleDAO;
    }

    @Transactional
    @Override
    public JqGridResponse list(JqGridRequest request) {
        return new JqGridResponse(request, userRoleDAO.list(request));
    }
}
