package dd2.local.busi.com.service;

import dd2.com.dao.GenericDAO;
import dd2.com.service.GenericHibernateService;
import dd2.local.busi.com.service.dao.ADSeqDAO;
import dd2.local.busi.com.service.dao.CodeRoleDAO;
import dd2.local.entity.CodeRole;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 8. 20
 * Time: 오전 11:01
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CommonServiceImpl extends GenericHibernateService<CodeRole, Long> implements CommonService {
    private static final Log logger = LogFactory.getLog(CommonServiceImpl.class);

    @Autowired
    private CodeRoleDAO codeRole;

    @Autowired
    private ADSeqDAO adSeqDAO;

    @Override
    protected GenericDAO<CodeRole, Long> getGenericDAO() {
        return codeRole;
    }

    @Transactional
    @Override
    public List<CodeRole> getCodeRoles() {
        return codeRole.getCodeRoles();
    }

    @Transactional
    @Override
    public Long getSeq(String tableName) {
        return adSeqDAO.getSeq(tableName);
    }
}
