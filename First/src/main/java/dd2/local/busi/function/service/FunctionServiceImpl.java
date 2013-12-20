package dd2.local.busi.function.service;

import dd2.com.ajax.AjaxResponse;
import dd2.com.dao.GenericDAO;
import dd2.com.jqgrid.JqGridRequest;
import dd2.com.jqgrid.JqGridResponse;
import dd2.com.service.GenericHibernateService;
import dd2.local.entity.AdFunctionEntity;
import dd2.local.entity.AdFunctionEntityPK;
import dd2.local.busi.function.service.dao.FunctionDAO;
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
public class FunctionServiceImpl extends GenericHibernateService<AdFunctionEntity, AdFunctionEntityPK> implements FunctionService {
    private static final Log logger = LogFactory.getLog(FunctionServiceImpl.class);

    @Autowired
    private FunctionDAO functionDAO;

    @Override
    protected GenericDAO<AdFunctionEntity, AdFunctionEntityPK> getGenericDAO() {
        return functionDAO;
    }

    @Transactional
    @Override
    public JqGridResponse list(JqGridRequest request) {
        return new JqGridResponse(request, functionDAO.list(request));
    }

    @Transactional
    @Override
    public AjaxResponse<List<Map<String, Object>>> jqGridSelect(Long adCompanyId) {
        AjaxResponse<List<Map<String, Object>>> result = new AjaxResponse<>();
        result.setData(functionDAO.jqGridSelect(adCompanyId));
        result.setStatus(true);
        return result;
    }
}
