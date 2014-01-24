package dd2.local.busi.programAccess.service;

import dd2.com.dao.GenericDAO;
import dd2.com.jqgrid.JqGridRequest;
import dd2.com.jqgrid.JqGridResponse;
import dd2.com.service.hibernate.GenericHibernateService;
import dd2.local.busi.programAccess.service.dao.ProgramAccessDAO;
import dd2.local.entity.AdProgramAccessEntity;
import dd2.local.entity.AdProgramAccessEntityPK;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by KDJ on 13. 12. 10.
 */
@Service
public class ProgramAccessServiceImpl extends GenericHibernateService<AdProgramAccessEntity, AdProgramAccessEntityPK> implements ProgramAccessService {
    private static final Log logger = LogFactory.getLog(ProgramAccessServiceImpl.class);

    @Autowired
    private ProgramAccessDAO programAccessDAO;

    @Override
    protected GenericDAO<AdProgramAccessEntity, AdProgramAccessEntityPK> getGenericDAO() {
        return programAccessDAO;
    }

    @Transactional
    @Override
    public JqGridResponse list(JqGridRequest request) {
        return new JqGridResponse(request, programAccessDAO.list(request));
    }
}
