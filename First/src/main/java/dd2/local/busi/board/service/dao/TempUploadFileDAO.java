package dd2.local.busi.board.service.dao;

import dd2.com.dao.GenericDAO;
import dd2.local.entity.TempUploadFile;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 11. 1
 * Time: 오후 3:48
 * To change this template use File | Settings | File Templates.
 */
public interface TempUploadFileDAO extends GenericDAO<TempUploadFile, Long> {
    List getTempUploadList(Long memberId);
}
