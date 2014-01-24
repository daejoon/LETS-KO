package dd2.local.busi.board.service.dao;

import dd2.com.dao.hibernate.GenericHibernateDAO;
import dd2.local.entity.Board;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.*;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 8. 14
 * Time: 오전 11:44
 * To change this template use File | Settings | File Templates.
 */

@Repository
public class BoardDAOHibernate extends GenericHibernateDAO<Board, Long> implements BoardDAO {
    private static final Log logger = LogFactory.getLog(BoardDAOHibernate.class);

    public List getTopBoardList(String boardName, int pageNumber, int pageSize) {
        return this.getCriteria()
                .setFirstResult(pageNumber)
                .setMaxResults(pageSize)
                .add(Restrictions.eq("boardName", boardName))
                .add(Restrictions.isNull("parentBoard.id"))
                .addOrder(Order.desc("id"))
                .addOrder(Order.asc("level"))
                .addOrder(Order.desc("createDate"))
                .list();
    }

    @SuppressWarnings("unchecked")
    private Map<String,Object> boardToMap(Board board, Long topBoardId) {
        Map<String,Object> map = new HashMap<>();
        map.put("id", board.getId());
        map.put("boardName", board.getBoardName());
        map.put("level", board.getLevel());
        map.put("parentId", board.getParentBoard().getId());
        map.put("topBoardId", topBoardId);
        map.put("replyCount", board.getReplyCount());
        map.put("commentCount", board.getCommentCount());
        map.put("title", board.getTitle());
        map.put("contents", board.getContents());
        map.put("createDate", board.getCreateDate());
        map.put("updateDate", board.getUpdateDate());
        map.put("username", board.getMember().getUsername());
        return map;
    }

    @SuppressWarnings("unchecked")
    public List getReplyList(Long parentBoardId, Long topBoardId) {
        List<Map<String,Object>> stack = new ArrayList<>();

        List list = this.getCriteria()
                .add(Restrictions.eq("parentBoard.id", parentBoardId))
                .addOrder(Order.asc("createDate"))
                .list();

        for (int i = 0; i < list.size(); i++) {
            Board board = (Board)list.get(i);
            stack.add(boardToMap(board, topBoardId));
            stack.addAll( getReplyList(board.getId(), topBoardId) );
        }
        return stack;
    }

    public int getTopBoardListCount(String boardName) {
        int ret = 0;
        Object result = this.getCriteria()
                .setProjection(
                        Projections.projectionList()
                                .add(Projections.rowCount())
                )
                .add(Restrictions.eq("boardName", boardName))
                .add(Restrictions.isNull("parentBoard.id"))
                .uniqueResult();

        if ( result != null ) {
            ret = Integer.parseInt(result.toString());
        }
        return ret;
    }
}
