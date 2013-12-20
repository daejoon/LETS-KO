package dd2.local.busi.file.service;

import dd2.com.dao.GenericDAO;
import dd2.com.service.GenericHibernateService;
import dd2.local.entity.TempUploadFile;
import dd2.local.busi.file.service.dao.FileDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 11. 4
 * Time: 오전 11:10
 * To change this template use File | Settings | File Templates.
 */
@Service
public class FileServiceImpl extends GenericHibernateService<TempUploadFile, Long> implements FileService {
    private static final Log logger = LogFactory.getLog(FileServiceImpl.class);

    @Autowired
    private FileDAO fileDAO;

    @Override
    protected GenericDAO<TempUploadFile, Long> getGenericDAO() {
        return fileDAO;
    }

    @Override
    public TempUploadFile findByFileName(String fileName) {
        return fileDAO.findByFileName(fileName);
    }
}
