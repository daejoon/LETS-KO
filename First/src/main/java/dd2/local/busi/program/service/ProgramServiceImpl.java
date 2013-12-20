package dd2.local.busi.program.service;

import dd2.com.ajax.AjaxResponse;
import dd2.com.dao.GenericDAO;
import dd2.com.jqgrid.JqGridRequest;
import dd2.com.jqgrid.JqGridResponse;
import dd2.com.service.GenericHibernateService;
import dd2.local.entity.AdProgramEntity;
import dd2.local.entity.AdProgramEntityPK;
import dd2.local.busi.program.service.dao.ProgramDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

/**
 * Created by KDJ on 13. 12. 4.
 */
@Service
public class ProgramServiceImpl extends GenericHibernateService<AdProgramEntity, AdProgramEntityPK> implements ProgramService {
    private static final Log logger = LogFactory.getLog(ProgramServiceImpl.class);

    @Autowired
    private ProgramDAO programDAO;

    @Override
    protected GenericDAO<AdProgramEntity, AdProgramEntityPK> getGenericDAO() {
        return programDAO;
    }

    @Transactional
    @Override
    public JqGridResponse list(JqGridRequest request) {
        return new JqGridResponse(request, programDAO.list(request));
    }

    @Transactional
    @Override
    public AjaxResponse<List<Map<String, Object>>> jqGridSelect(Long adCompanyId) {
        AjaxResponse<List<Map<String, Object>>> result = new AjaxResponse<>();
        result.setData(programDAO.jqGridSelect(adCompanyId));
        result.setStatus(true);
        return result;
    }
}
