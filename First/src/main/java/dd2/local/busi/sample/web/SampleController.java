package dd2.local.busi.sample.web;

import dd2.com.jqgrid.JqGridCrudUtil;
import dd2.com.jqgrid.JqGridRequest;
import dd2.com.jqgrid.JqGridResponseGeneric;
import dd2.local.busi.com.web.CommonController;
import dd2.local.busi.sample.service.SampleService;
import dd2.local.entity.SampleEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kdj on 2014. 1. 18..
 */
@Controller
@RequestMapping("/sample/*")
public class SampleController extends CommonController {
    private static final Log logger = LogFactory.getLog(SampleController.class);
    private static final String BASE_URL = "sample/";

    @Autowired
    private SampleService sampleService;

    @Override
    protected String getBaseUrl() {
        return BASE_URL;
    }

    @RequestMapping( value = "records", method = RequestMethod.POST )
    public @ResponseBody
    JqGridResponseGeneric<SampleEntity> records( @RequestBody JqGridRequest jqGridRequest ) {
        return sampleService.list(jqGridRequest);
    }

    @RequestMapping( value = "recordEdit", method = RequestMethod.POST )
    public @ResponseBody
    Map<String,Object> recordEdit( @RequestBody Map<String, Object> params ) {
        if ( params != null ) {
            JqGridCrudUtil<SampleEntity, Long>
                    jqgrid = new JqGridCrudUtil<>(SampleEntity.class, Long.class, params);

            switch (jqgrid.getOper()) {
                case ADD:
                {
                    SampleEntity newEntity = jqgrid.createEntityWithDataBinding();
                    sampleService.save(newEntity);
                }
                break;
                case EDIT:
                {
                    Long id = jqgrid.getId();
                    SampleEntity entity = jqgrid.createEntityWithDataBinding();

                    SampleEntity updateEntity = sampleService.findById(id);

                    updateEntity.setAge(entity.getAge());
                    updateEntity.setName(entity.getName());
                    updateEntity.setDescription(entity.getDescription());

                    sampleService.deleteById(id);
                    sampleService.save(updateEntity);
                }
                break;
                case DELETE:
                {
                    Long[] ids = jqgrid.getIds();
                    sampleService.deleteByIds(ids);
                }
                break;
                default: break;
            }
        }// if ( params != null ) {

        return new HashMap<>();
    }
}
