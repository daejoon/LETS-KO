package dd2.local.busi.board.service.dao;

import dd2.com.dao.GenericDAO;
import dd2.local.entity.Board;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 10. 25
 * Time: 오전 10:00
 * To change this template use File | Settings | File Templates.
 */
public interface BoardDAO extends GenericDAO<Board, Long> {
    List getTopBoardList(String boardName, int pageNumber, int pageSize);
    List getReplyList(Long parentBoardId, Long topBoardId);
    int getTopBoardListCount(String boardName);
}
