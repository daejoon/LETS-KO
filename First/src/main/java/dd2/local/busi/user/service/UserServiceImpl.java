package dd2.local.busi.user.service;

import dd2.com.dao.GenericDAO;
import dd2.com.jqgrid.JqGridRequest;
import dd2.com.jqgrid.JqGridResponse;
import dd2.com.service.GenericHibernateService;
import dd2.local.busi.user.service.dao.UserDAO;
import dd2.local.entity.AdUserEntity;
import dd2.local.entity.AdUserEntityPK;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by KDJ on 13. 12. 4.
 */
@Service
public class UserServiceImpl extends GenericHibernateService<AdUserEntity, AdUserEntityPK> implements UserService {
    private static final Log logger = LogFactory.getLog(UserServiceImpl.class);

    @Autowired
    private UserDAO userDAO;

    @Override
    protected GenericDAO<AdUserEntity, AdUserEntityPK> getGenericDAO() {
        return userDAO;
    }

    @Transactional
    @Override
    public JqGridResponse list(JqGridRequest request) {
        return new JqGridResponse(request, userDAO.list(request));
    }
}
