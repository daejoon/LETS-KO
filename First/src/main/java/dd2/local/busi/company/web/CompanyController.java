package dd2.local.busi.company.web;

import dd2.com.ajax.AjaxResponse;
import dd2.com.jqgrid.JqGridCrudUtil;
import dd2.com.jqgrid.JqGridRequest;
import dd2.com.jqgrid.JqGridResponse;
import dd2.local.busi.com.web.CommonController;
import dd2.local.busi.company.service.CompanyService;
import dd2.local.entity.AdCompanyEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 11. 11
 * Time: 오후 6:24
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/company/*")
public class CompanyController extends CommonController {
    private static final Log logger = LogFactory.getLog(CompanyController.class);
    private static final String BASE_URL = "company/";

    @Autowired
    private CompanyService companyService;

    @Override
    public String getBaseUrl() {
        return BASE_URL;
    }

    @RequestMapping( value = "records", method = RequestMethod.POST )
    public @ResponseBody
    JqGridResponse records( @RequestBody JqGridRequest jqGridRequest ) {
        return companyService.list(jqGridRequest);
    }

    @RequestMapping( value = "recordEdit", method = RequestMethod.POST )
    public @ResponseBody
    Map<String,Object> recordEdit( @RequestBody Map<String, Object> params ) {
        if ( params != null ) {
            JqGridCrudUtil<AdCompanyEntity, Long> jqgrid = new JqGridCrudUtil<>(AdCompanyEntity.class, Long.class, params);

            Date date = new Date();
            Long adminID = new Long(1);

            switch (jqgrid.getOper()) {
                case ADD:
                {
                    AdCompanyEntity newEntity = jqgrid.createEntityWithDataBinding();
                    newEntity.setAdCompanyId(commonService.getSeq("AD_Company"));
                    newEntity.setCreated    (date);
                    newEntity.setCreatedBy  (adminID);
                    newEntity.setUpdated    (date);
                    newEntity.setUpdatedBy  (adminID);
                    companyService.save(newEntity);
                }
                    break;
                case EDIT:
                {
                    Long id = jqgrid.getId();
                    AdCompanyEntity entity = jqgrid.createEntityWithDataBinding();
                    AdCompanyEntity updateEntity = companyService.findById(id);
                    updateEntity.setName        (entity.getName());
                    updateEntity.setValue       (entity.getValue());
                    updateEntity.setDescription (entity.getDescription());
                    updateEntity.setIsActive    (entity.getIsActive());
                    updateEntity.setUpdated     (date);
                    updateEntity.setUpdatedBy   (adminID);
                    companyService.update(updateEntity);
                }
                    break;
                case DELETE:
                {
                    Long[] longIDs = jqgrid.getIds();
                    companyService.deleteByIds(longIDs);
                }
                    break;
                default: break;
            }
        }// if ( params != null ) {

        return new HashMap<>();
    }

    @RequestMapping( value = "jqGridSelect", method = RequestMethod.POST )
    public @ResponseBody
    AjaxResponse<List<Map<String,Object>>> jqGridSelect() {
        AjaxResponse<List<Map<String,Object>>> result = new AjaxResponse<>();
        try {
            result = companyService.jqGridSelect();
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage(e.getMessage());
        }
        return result;
    }
}
