package dd2.local.busi.board.service.dao;

import dd2.com.dao.GenericDAO;
import dd2.local.entity.Comment;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 10. 25
 * Time: 오전 10:08
 * To change this template use File | Settings | File Templates.
 */
public interface CommentDAO extends GenericDAO<Comment, Long> {
    List getCommentList(Long boardId);
}
