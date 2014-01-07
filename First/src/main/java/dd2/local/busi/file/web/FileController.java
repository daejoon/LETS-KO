package dd2.local.busi.file.web;

import dd2.com.util.ConfigUtil;
import dd2.local.web.view.ViewSelector;
import dd2.local.busi.com.web.CommonController;
import dd2.local.entity.TempUploadFile;
import dd2.local.busi.file.service.FileService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 10. 30
 * Time: 오후 1:07
 * To change this template use File | Settings | File Templates.
 * 이미지 경로를 숨긴다.
 */
@Controller
@RequestMapping("/file/*")
public class FileController extends CommonController {
    private static final Log logger = LogFactory.getLog(FileController.class);
    private static final String BASE_URL = "file/";

    @Autowired
    private FileService fileService;

    @Override
    public String getBaseUrl() {
        return BASE_URL;
    }

    @RequestMapping(value = "{fileName}")
    public ModelAndView renderImage(
              @PathVariable(value = "fileName") String fileName
            , ModelMap model
    ) {
        String tempFilePath   = ConfigUtil.getString("path.temp");
        String filePath = tempFilePath + "/" + fileName;
        File file = new File(filePath);

        if ( file.exists() == false ) {
            if ( logger.isDebugEnabled() ) {
                logger.debug(filePath + " file does not exist.");
            }
        }

        TempUploadFile tempUploadFile = fileService.findByFileName(fileName);
        model.put("filePath", filePath);
        model.put("fileType", tempUploadFile.getContentType());
        model.put("orignalFileName", tempUploadFile.getOrignalFileName());

        return ViewSelector.getImageView();
    }
}
