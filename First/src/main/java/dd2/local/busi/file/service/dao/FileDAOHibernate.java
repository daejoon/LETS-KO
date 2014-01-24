package dd2.local.busi.file.service.dao;

import dd2.com.dao.hibernate.GenericHibernateDAO;
import dd2.local.entity.TempUploadFile;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 11. 4
 * Time: 오전 11:13
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class FileDAOHibernate extends GenericHibernateDAO<TempUploadFile, Long> implements FileDAO {
    private static final Log logger = LogFactory.getLog(FileDAOHibernate.class);

    @Override
    public TempUploadFile findByFileName(String fileName) {
        Object file = this.getCriteria()
                .add(Restrictions.eq("fileName", fileName))
                .uniqueResult();

        if ( file == null ) {
            return null;
        }
        return (TempUploadFile)file;
    }
}
