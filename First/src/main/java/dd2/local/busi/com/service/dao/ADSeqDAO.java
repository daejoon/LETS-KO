package dd2.local.busi.com.service.dao;

import dd2.com.dao.GenericDAO;
import dd2.local.entity.AdSeqEntity;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 11. 11
 * Time: 오후 5:11
 * To change this template use File | Settings | File Templates.
 */
public interface ADSeqDAO extends GenericDAO<AdSeqEntity, String> {
    Long getSeq(String id);
}
