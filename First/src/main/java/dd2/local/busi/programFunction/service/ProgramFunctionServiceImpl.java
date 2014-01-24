package dd2.local.busi.programFunction.service;

import dd2.com.ajax.AjaxResponse;
import dd2.com.dao.GenericDAO;
import dd2.com.jqgrid.JqGridRequest;
import dd2.com.jqgrid.JqGridResponse;
import dd2.com.service.GenericHibernateService;
import dd2.local.busi.programFunction.service.dao.ProgramFunctionDAO;
import dd2.local.entity.AdProgramFunctionEntity;
import dd2.local.entity.AdProgramFunctionEntityPK;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by KDJ on 13. 12. 10.
 */
@Service
public class ProgramFunctionServiceImpl extends GenericHibernateService<AdProgramFunctionEntity, AdProgramFunctionEntityPK> implements ProgramFunctionService {
    private static final Log logger = LogFactory.getLog(ProgramFunctionServiceImpl.class);

    @Autowired
    private ProgramFunctionDAO programFunctionDAO;

    @Override
    protected GenericDAO<AdProgramFunctionEntity, AdProgramFunctionEntityPK> getGenericDAO() {
        return programFunctionDAO;
    }

    @Transactional
    @Override
    public JqGridResponse list(JqGridRequest request) {
        return new JqGridResponse(request, programFunctionDAO.list(request));
    }

    @Transactional
    @Override
    public AjaxResponse<List<Map<String, Object>>> jqGridSelect(Long adProgramId, Long adCompanyId) {
        AjaxResponse<List<Map<String, Object>>> result = new AjaxResponse<>();
        result.setData(programFunctionDAO.jqGridSelect(adProgramId, adCompanyId));
        result.setStatus(true);
        return result;
    }
}
