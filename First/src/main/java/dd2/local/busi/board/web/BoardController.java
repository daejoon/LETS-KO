package dd2.local.busi.board.web;

import dd2.com.util.ConfigUtil;
import dd2.local.util.MemberUtil;
import dd2.com.util.New;
import dd2.com.util.UUIDUtil;
import dd2.local.busi.board.service.BoardService;
import dd2.local.busi.com.web.CommonController;
import dd2.local.entity.Board;
import dd2.local.entity.Comment;
import dd2.local.entity.Member;
import dd2.local.entity.TempUploadFile;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 8. 14
 * Time: 오후 12:17
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/board/*")
public class BoardController extends CommonController {
    private static final Log logger = LogFactory.getLog(BoardController.class);
    private static final String BASE_URL = "board/";

    @Autowired
    private BoardService boardService;

    @Override
    public String getBaseUrl() {
        return BASE_URL;
    }

    @RequestMapping("write")
    public String write(
              @RequestParam(value = "id", required = false) Long id
            , @RequestParam(value = "boardName", required = true) String boardName
            , @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber
            , @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize
            , @RequestParam(value = "pageBlockSize", required = false, defaultValue = "5") int pageBlockSize
            , @RequestParam(value = "type", required = false, defaultValue = "write") String type
            , ModelMap model
    ) {
        Board board = null;

        if ( id == null ) {
            board = new Board();
        } else {
            board = boardService.findById(id);
        }

        model.put("boardName"       , boardName);
        model.put("pageNumber"      , pageNumber);
        model.put("pageSize"        , pageSize);
        model.put("pageBlockSize"   , pageBlockSize);
        model.put("board"           , board);
        model.put("type"            , type);

        return BASE_URL + "write.tiles";
    }

    @RequestMapping("writeOk")
    public String writeOk(
              @ModelAttribute Board board
            , @RequestParam(value = "parentId", required = false) Long parentId
            , @RequestParam(value = "boardName", required = true) String boardName
            , @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber
            , @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize
            , @RequestParam(value = "pageBlockSize", required = false, defaultValue = "5") int pageBlockSize
            , ModelMap model
    ) {
        Member member = MemberUtil.getCurrentMember();
        Date currentDate = new Date();

        // write
        if ( board.getId() == null  && parentId == null ) {
            board.setMember(member);
            board.setCreateDate(currentDate);
            board.setUpdateDate(currentDate);
            boardService.saveOrUpdate(board);
        }
        // reply
        else if ( board.getId() == null && parentId != null ) {
            // 부모의 답글 개수를 업데이트 해준다.
            Board parentBoard = boardService.findById(parentId);
            parentBoard.setReplyCount(parentBoard.getReplyCount()+1);
            boardService.saveOrUpdate(parentBoard);

            // 답글을 저장한다.
            board.setParentBoard(parentBoard);
            board.setMember(member);
            board.setCreateDate(currentDate);
            board.setUpdateDate(currentDate);
            boardService.saveOrUpdate(board);
        }
        // modify
        else if (board.getId() != null ) {
            Board updateBoard = boardService.findById(board.getId());
            updateBoard.setTitle(board.getTitle());
            updateBoard.setContents(board.getContents());
            updateBoard.setUpdateDate(currentDate);
            boardService.saveOrUpdate(updateBoard);
            model.put("board", updateBoard);
        }

        model.put("boardName"       , boardName);
        model.put("pageNumber"      , pageNumber);
        model.put("pageSize"        , pageSize);
        model.put("pageBlockSize"   , pageBlockSize);
        return BASE_URL + "getBoard.tiles";
    }

    @RequestMapping("getBoard")
    public String getBoard(
              @RequestParam(value = "id", required = true) Long id
            , @RequestParam(value = "boardName", required = true) String boardName
            , @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber
            , @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize
            , @RequestParam(value = "pageBlockSize", required = false, defaultValue = "5") int pageBlockSize
            , ModelMap model
    ) {
        Board board = boardService.findById(id);
        model.put("board"           , board);
        model.put("boardName"       , boardName);
        model.put("pageNumber"      , pageNumber);
        model.put("pageSize"        , pageSize);
        model.put("pageBlockSize"   , pageBlockSize);

        return BASE_URL + "getBoard.tiles";
    }

    @RequestMapping("listBoard")
    public String listBoard(
              @RequestParam(value = "boardName", required = true) String boardName
            , @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber
            , @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize
            , @RequestParam(value = "pageBlockSize", required = false, defaultValue = "5") int pageBlockSize
            , ModelMap model
    ) {

        int count = boardService.getTopBoardListCount(boardName);
        List list = boardService.getTopBoardList(boardName, (pageNumber - 1) * pageSize, pageSize);

        model.put("boardName"       , boardName);
        model.put("pageNumber"      , pageNumber);
        model.put("pageSize"        , pageSize);
        model.put("pageBlockSize"   , pageBlockSize);
        model.put("listCount"       , count);
        model.put("list"            , list);

        return BASE_URL + "listBoard.tiles";
    }

    @RequestMapping("replyList")
    public @ResponseBody Map<String, Object> replyList(
              @RequestParam(value = "id", required = true) Long id
    ) {
        Map<String,Object> result = New.map();

        List list = boardService.getReplyList(id, id);
        result.put("list", list);

        return result;
    }


    @RequestMapping("delete")
    public String delete(
              @RequestParam(value = "id", required = true) Long id
            , @RequestParam(value = "boardName", required = true) String boardName
            , @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber
            , @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize
            , @RequestParam(value = "pageBlockSize", required = false, defaultValue = "5") int pageBlockSize
            , ModelMap model
    ) {
        Member member = MemberUtil.getCurrentMember();
        Board board = boardService.findById(id);

        if ( member.getId().equals(board.getMember().getId()) == true ) {
            boardService.delete(board);
        }

        int count = boardService.getTopBoardListCount(boardName);
        List list = boardService.getTopBoardList(boardName, (pageNumber - 1) * pageSize, pageSize);

        model.put("boardName"       , boardName);
        model.put("pageNumber"      , pageNumber);
        model.put("pageSize"        , pageSize);
        model.put("pageBlockSize"   , pageBlockSize);
        model.put("listCount"       , count);
        model.put("list"            , list);

        return BASE_URL + "listBoard.tiles";
    }

    @RequestMapping(
              value = "comment/{boardId}"
            , method = RequestMethod.POST
    )
    public @ResponseBody Map<String,Object> comment(
            @PathVariable(value = "boardId") Long boardId
          , @RequestBody(required = false) Comment comment
    ) {
        Map<String,Object> result = New.map();

        result.put("status" , true);
        result.put("list"   , new ArrayList<Map<String,Object>>());
        result.put("message", "");

        if ( comment != null ) {
            Member member = MemberUtil.getCurrentMember();
            Date currentDate = new Date();
            comment.setMember(member);
            comment.setCreateDate(currentDate);
            boardService.addComment(boardId, comment);
        }

        List list = boardService.getCommentList(boardId);
        result.put("status" , true);
        result.put("list"   , list);

        return result;
    }

    @RequestMapping(
            value = "commentDelete/{commentId}"
            , method = RequestMethod.POST
    )
    public @ResponseBody Map<String,Object> commentDelete(
            @PathVariable(value = "commentId") Long commentId
    ) {
        Map<String,Object> result = New.map();

        Member member = MemberUtil.getCurrentMember();
        Comment comment = boardService.getCommentById(commentId);

        result.put("status"     , true);
        result.put("message"    , "");

        if ( member == null || comment == null ) {
            result.put("status"     , false);
            result.put("message"    , "정상적인 사용자가 아닙니다.");
            return result;
        }

        if ( comment.getMember().getId().equals(member.getId()) == false ) {
            result.put("status"     , false);
            result.put("message"    , "글을 작성한 사용자가 아닙니다.");
            return result;
        }

        boardService.deleteComment(comment);
        return result;
    }

    @RequestMapping(value = "commentUpdate", method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> commentUpdate(
            @RequestBody Comment comment
    ) {
        Map<String,Object> result = New.map();

        result.put("status"     , true);
        result.put("message"    , "");

        if ( comment.getId() == null ) {
            result.put("status"     , false);
            result.put("message"    , "Comment object is null.");
            return result;
        }

        Member member = MemberUtil.getCurrentMember();
        if ( member == null ) {
            result.put("status"     , false);
            result.put("message"    , "Member object is null.");
            return result;
        }

        Comment updateComment = boardService.getCommentById(comment.getId());
        if ( updateComment.getMember().getId().equals(member.getId()) == false ) {
            result.put("status"     , false);
            result.put("message"    , "글을 작성한 사용자가 아닙니다.");
            return result;
        }

        updateComment.setContents(comment.getContents());
        updateComment.setUpdateDate(new Date());
        boardService.updateComment(updateComment);
        return result;
    }

    @RequestMapping(value = "uploadFile", method = { RequestMethod.POST })
    public @ResponseBody String uploadFile(
              @RequestParam(value = "CKEditorFuncNum", required = true) String ckeditorFuncNum
            , @RequestParam(value = "CKEditor", required = true) String ckeditor
            , @RequestParam(value = "langCode", required = true) String langCode
            , @RequestParam(value = "upload", required = true) MultipartFile upload
            , HttpServletRequest request
    ) throws IOException, IllegalStateException
    {
        Member member = MemberUtil.getCurrentMember();
        String uploadPath  = ConfigUtil.getString("path.upload");
        String tempPath    = ConfigUtil.getString("path.temp");
        String newFileName = UUIDUtil.getUUID();
        String newFilePath = tempPath + "\\" + newFileName;

        TempUploadFile tempUploadFile = new TempUploadFile();
        tempUploadFile.setMember(member);
        tempUploadFile.setContentType(upload.getContentType());
        tempUploadFile.setCreateDate(new Date());
        tempUploadFile.setFileName(newFileName);
        tempUploadFile.setOrignalFileName(upload.getOriginalFilename());
        tempUploadFile.setFileSize(upload.getSize());

        boardService.addTempUploadFile(tempUploadFile);
        upload.transferTo(new File(newFilePath));

        StringBuilder sb = new StringBuilder();
        sb.append("<script type='text/javascript'>")
                .append("window.parent.CKEDITOR.tools.callFunction(")
                .append("'").append(ckeditorFuncNum).append("', ")
                .append("'").append(request.getContextPath()).append("/file/").append(newFileName).append("', ")
                .append("'").append("업로드 성공").append("'")
                .append(");")
                .append("</script>");

        // 성공했을때 작업처리
        return sb.toString();
    }
}
