package dd2.local.busi.com.service.dao;

import dd2.com.dao.GenericHibernateDAO;
import dd2.local.entity.AdSeqEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 11. 11
 * Time: 오후 5:28
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class ADSeqDAOHibernate extends GenericHibernateDAO<AdSeqEntity, String> implements ADSeqDAO {
    private static final Log logger = LogFactory.getLog(ADSeqDAOHibernate.class);

    @Override
    public Long getSeq(String id) {
        AdSeqEntity adSeqEntity = this.findById(id);

        // 신규 생성
        if ( adSeqEntity == null ) {
            Date date = new Date();
            Long adminId = new Long(1);

            adSeqEntity = new AdSeqEntity();
            adSeqEntity.setAdTableName(id);
            adSeqEntity.setSeq(new Long(1));
            adSeqEntity.setCreated(date);
            adSeqEntity.setCreatedBy(adminId);
            adSeqEntity.setUpdated(date);
            adSeqEntity.setUpdatedBy(adminId);
            this.save(adSeqEntity);
            this.flush();
        }
        Long seq = adSeqEntity.getSeq() + 1;
        adSeqEntity.setSeq(seq);
        return seq;
    }
}
