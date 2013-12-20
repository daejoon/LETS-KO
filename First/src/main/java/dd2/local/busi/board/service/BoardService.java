package dd2.local.busi.board.service;

import dd2.com.service.GenericService;
import dd2.local.entity.Board;
import dd2.local.entity.Comment;
import dd2.local.entity.Member;
import dd2.local.entity.TempUploadFile;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 8. 14
 * Time: 오후 12:19
 * To change this template use File | Settings | File Templates.
 */
public interface BoardService extends GenericService<Board, Long> {
    List getTopBoardList(String boardName, int pageNumber, int pageSize);
    List getReplyList(Long parentBoardId, Long topBoardId);
    int getTopBoardListCount(String boardName);

    void addComment(Long boardId, Comment comment);
    Comment getCommentById(Long id);
    void updateComment(Comment comment);
    void deleteCommentById(Long id);
    void deleteComment(Comment comment);
    List getCommentList(Long boardId);

    void addTempUploadFile(TempUploadFile tempUploadFile);
    void deleteTempUploadFile(TempUploadFile tempUploadFile);
    void deleteTempUploadFileById(Long id);
    List getTempUploadFileList(Member member);
}
