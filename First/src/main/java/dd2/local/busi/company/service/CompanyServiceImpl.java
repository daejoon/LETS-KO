package dd2.local.busi.company.service;

import dd2.com.ajax.AjaxResponse;
import dd2.com.dao.GenericDAO;
import dd2.com.jqgrid.JqGridRequest;
import dd2.com.jqgrid.JqGridResponse;
import dd2.com.service.GenericHibernateService;
import dd2.local.busi.company.service.dao.CompanyDAO;
import dd2.local.entity.AdCompanyEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 11. 11
 * Time: 오후 6:34
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CompanyServiceImpl extends GenericHibernateService<AdCompanyEntity, Long> implements CompanyService {
    private static final Log logger = LogFactory.getLog(CompanyServiceImpl.class);

    @Autowired
    private CompanyDAO companyDAO;

    @Override
    protected GenericDAO<AdCompanyEntity, Long> getGenericDAO() {
        return companyDAO;
    }

    @Transactional
    @Override
    public JqGridResponse list(JqGridRequest request) {
        return new JqGridResponse(request, companyDAO.list(request));
    }

    @Transactional
    @Override
    public AjaxResponse<List<Map<String, Object>>> jqGridSelect() {
        AjaxResponse<List<Map<String, Object>>> result = new AjaxResponse<>();
        result.setData(companyDAO.jqGridSelect());
        result.setStatus(true);
        return result;
    }
}
