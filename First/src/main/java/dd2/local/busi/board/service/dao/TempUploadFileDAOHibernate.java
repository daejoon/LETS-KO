package dd2.local.busi.board.service.dao;

import dd2.com.dao.hibernate.GenericHibernateDAO;
import dd2.local.entity.TempUploadFile;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 11. 1
 * Time: 오후 3:49
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class TempUploadFileDAOHibernate extends GenericHibernateDAO<TempUploadFile, Long> implements TempUploadFileDAO {
    private static final Log logger = LogFactory.getLog(TempUploadFileDAOHibernate.class);

    @Override
    public List getTempUploadList(Long memberId) {
        return this.getCriteria()
                .add(Restrictions.eq("member.id", memberId))
                .list();
    }
}
