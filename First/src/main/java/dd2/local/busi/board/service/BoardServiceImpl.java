package dd2.local.busi.board.service;

import dd2.com.dao.GenericDAO;
import dd2.com.service.hibernate.GenericHibernateService;
import dd2.local.busi.board.service.dao.BoardDAO;
import dd2.local.busi.board.service.dao.CommentDAO;
import dd2.local.busi.board.service.dao.TempUploadFileDAO;
import dd2.local.entity.Board;
import dd2.local.entity.Comment;
import dd2.local.entity.Member;
import dd2.local.entity.TempUploadFile;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 8. 14
 * Time: 오후 12:19
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class BoardServiceImpl extends GenericHibernateService<Board, Long> implements BoardService {
    private static final Log logger = LogFactory.getLog(BoardServiceImpl.class);

    @Autowired
    private BoardDAO boardDAO;

    @Autowired
    private CommentDAO commentDAO;

    @Autowired
    private TempUploadFileDAO tempUploadFileDAO;

    @Override
    protected GenericDAO<Board, Long> getGenericDAO() {
        return boardDAO;
    }

    @Override
    public List getTopBoardList(String boardName, int pageNumber, int pageSize) {
        return boardDAO.getTopBoardList(boardName, pageNumber, pageSize);
    }

    @Override
    public List getReplyList(Long parentBoardId, Long topBoardId) {
        return boardDAO.getReplyList(parentBoardId, topBoardId);
    }

    @Override
    public int getTopBoardListCount(String boardName) {
        return boardDAO.getTopBoardListCount(boardName);
    }

    @Override
    public void addComment(Long boardId, Comment comment) {
        Board board = boardDAO.findById(boardId);
        board.setCommentCount(board.getCommentCount()+1);
        comment.setBoard(board);
        commentDAO.save(comment);
    }

    @Override
    public Comment getCommentById(Long id) {
        return commentDAO.findById(id);
    }

    @Override
    public void updateComment(Comment comment) {
        commentDAO.update(comment);
    }

    @Override
    public void deleteCommentById(Long id) {
        Comment comment = commentDAO.findById(id);
        Board board = comment.getBoard();
        board.setCommentCount((board.getCommentCount() > 0 ? board.getCommentCount() - 1 : 0));
        commentDAO.delete(comment);
    }

    @Override
    public void deleteComment(Comment comment) {
        Board board = comment.getBoard();
        board.setCommentCount( (board.getCommentCount() > 0 ? board.getCommentCount()-1 : 0) );
        boardDAO.update(board);
        commentDAO.delete(comment);
    }

    @Override
    public List getCommentList(Long boardId) {
        return commentDAO.getCommentList(boardId);
    }


    @Override
    public void addTempUploadFile(TempUploadFile tempUploadFile) {
        tempUploadFileDAO.saveOrUpdate(tempUploadFile);
    }

    @Override
    public void deleteTempUploadFile(TempUploadFile tempUploadFile) {
        tempUploadFileDAO.delete(tempUploadFile);
    }

    @Override
    public void deleteTempUploadFileById(Long id) {
        tempUploadFileDAO.deleteById(id);
    }

    @Override
    public List getTempUploadFileList(Member member) {
        return tempUploadFileDAO.getTempUploadList(member.getId());
    }
}
