package dd2.local.busi.file.service.dao;

import dd2.com.dao.GenericDAO;
import dd2.local.entity.TempUploadFile;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 11. 4
 * Time: 오전 11:12
 * To change this template use File | Settings | File Templates.
 */
public interface FileDAO extends GenericDAO<TempUploadFile, Long> {
    TempUploadFile findByFileName(String fileName);

}
