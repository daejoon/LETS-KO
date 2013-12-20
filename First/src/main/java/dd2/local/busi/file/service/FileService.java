package dd2.local.busi.file.service;

import dd2.com.service.GenericService;
import dd2.local.entity.TempUploadFile;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 11. 4
 * Time: 오전 11:09
 * To change this template use File | Settings | File Templates.
 */
public interface FileService extends GenericService<TempUploadFile, Long> {
    TempUploadFile findByFileName(String fileName);
}
