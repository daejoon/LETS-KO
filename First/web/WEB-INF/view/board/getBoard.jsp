<%--
  Created by IntelliJ IDEA.
  User: KDJ
  Date: 13. 7. 29
  Time: 오전 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"              prefix="c"	     %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"         prefix="fn"     %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"               prefix="fmt"    %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles"             prefix="tiles"  %>
<%@ taglib uri="http://www.springframework.org/tags"            prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags"   prefix="sec"    %>
<div class="panel panel-default">
    <div class="panel-heading">${board.title}&nbsp;<fmt:formatDate value="${board.createDate}" pattern="[yyyy/MM/dd a hh:mm]" /></div>
    <div class="panel-body">
        <div class="row">
            <div class="col-md-12" style="word-break: break-all; word-wrap: break-word">${board.contents}</div>
        </div>
        <table class="table">
            <tbody id="commentBody">
            </tbody>
        </table>
        <p class="text-right">
            <sec:authentication property="principal" var="member" />
            <c:if test="${member.id == board.member.id}">
                <button id="btnModify" class="btn btn-default">
                    <i class="icon-fixed-width icon-pencil"></i>
                    Modify
                </button>
                <button id="btnDelete" class="btn btn-default">
                    Delete
                </button>
            </c:if>
            <button id="btnReply" class="btn btn-default">
                Reply
            </button>
            <button id="btnComment" class="btn btn-default">
                Comment
            </button>
            <button id="btnList" class="btn btn-default">
                List
            </button>
        </p>
    </div>
</div>
<script type="text/template" class="comment-tpl">
    <? _.each(ds, function(val) { ?>
    <tr class="comment-tr">
        <input type="hidden" name="id"          value="<?=val.id?>" />
        <input type="hidden" name="contents"    value="<?=val.contents?>" />
        <td class="col-md-12">
            <div class="row">
                <div class="col-md-10">
                    <?=val.username?> [<?=(new Date(val.createDate)).format("yyyy/MM/dd a/p HH:mm")?>]
                </div>
                <div class="col-md-2 text-right">
                    <? if ( val.memberId == LOCAL.session.getId() )  { ?>
                    <a href="#" class="comment-modify">수정</a> / <a href="#" class="comment-delete">삭제</a>
                    <? } ?>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12 comment-contents" style="padding-left:8.333333333333332%"><?=val.contents?></div>
            </div>
        </td>
    </tr>
    <? }); ?>
</script>
<script type="text/javascript">
;require([
    "jquery",
    "local",
    "underscore"
],
function($, LOCAL, _) { $(document).ready(function() {

    var ShowCommentList = function(list) {
        var $commentBody = $("#commentBody");
        if ( list.length > 0 ) {
            $commentBody.children().remove();
            $commentBody.append( _.template($("script.comment-tpl").html(), { LOCAL:LOCAL, ds : list}) );
        }
    };

    /**
     * 코멘트 팝업창 생성
     * @param id
     * @param contents
     * @param callback
     * @constructor
     */
    var CommentForm = function(id, contents, callback) {
        var opt = {}, data = {};
        if ( $.isPlainObject(id) == false ) {
            opt = {
                id       : id            || "",
                contents : contents      || "",
                callback : callback      || null
            };
        } else {
            opt = {
                id       : id.id         || "",
                contents : id.contents   || "",
                callback : id.callback   || null
            };
        }

        var $modal =
                $('<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">              '
                        +'   <form id="commentForm" name="commentForm" method="post">                                                                      '
                        +'   <input type="hidden" name="id" value="" />                                                                                    '
                        +'       <div class="modal-dialog">                                                                                                '
                        +'           <div class="modal-content">                                                                                           '
                        +'               <div class="modal-header">                                                                                        '
                        +'                   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>                  '
                        +'                   <h4 class="modal-title">Comment</h4>                                                                          '
                        +'               </div>                                                                                                            '
                        +'           <div class="modal-body">                                                                                              '
                        +'               <textarea rows="8" class="form-control" placeholder="Contents" name="contents">' + opt.contents + '</textarea>    '
                        +'           </div>                                                                                                                '
                        +'           <div class="modal-footer">                                                                                            '
                        +'               <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>                                 '
                        +'               <button id="commentBtnOk" type="button" class="btn btn-primary">Save changes</button>                             '
                        +'           </div>                                                                                                                '
                        +'       </div>                                                                                                                    '
                        +'   </div>                                                                                                                        '
                        +'   </form>                                                                                                                       '
                        +'</div>                                                                                                                           ');

        $modal.find("#commentForm input[name=id]").val(opt.id);
        $modal.find("#commentForm textarea[name=contents]").val(opt.contents);
        $modal.find("#commentBtnOk").click(function() {
            data = $modal.find("#commentForm").serializeObject();
            if ($.isFunction(opt.callback)) {
                opt.callback(data);
            }
            $modal.modal("hide");
        });
        $modal.modal("show");
    };

    $("#btnModify").click(function() {
        location.href = LOCAL.url("/board/write.html?type=modify&id=${board.id}&boardName=${boardName}&pageNumber=${pageNumber}&pageSize=${pageSize}&pageBlockSize=${pageBlockSize}");
    });

    $("#btnDelete").click(function() {
        location.href = LOCAL.url("/board/delete.html?id=${board.id}&boardName=${boardName}&pageNumber=${pageNumber}&pageSize=${pageSize}&pageBlockSize=${pageBlockSize}");
    });

    $("#btnReply").click(function() {
        location.href = LOCAL.url("/board/write.html?type=reply&id=${board.id}&boardName=${boardName}&pageNumber=${pageNumber}&pageSize=${pageSize}&pageBlockSize=${pageBlockSize}");
    });

    $("#btnList").click(function() {
        location.href = LOCAL.url("/board/listBoard.html?boardName=${boardName}&pageNumber=${pageNumber}&pageSize=${pageSize}&pageBlockSize=${pageBlockSize}");
    });

    $("#btnComment").click(function() {
        CommentForm({ callback : function(data) {
            $.ajax({
                url : LOCAL.url("/board/comment/${board.id}")
                , data : JSON.stringify(data)
                , success : function(result) {
                    if ( result.status == true ) {
                        ShowCommentList(result.list);
                    }
                    else {
                        alert(result.message || "Unkonwn Error.");
                    }
                }
            });
        }});
    });

    /**
     * 코멘트 삭제
     */
    $(document).on("click", "a.comment-delete", function() {
        var $this = $(this),
                $tr =  $this.parents("tr.comment-tr:eq(0)"),
                deleteData = {};

        deleteData.id       = $tr.find("input[name=id]").val();
        deleteData.contents = $tr.find("input[name=contents]").val();

        $.Confirm("확인", "정말로 삭제 하시겠습니까?", "취소", "삭제", function() {
            $.ajax({
                url : LOCAL.url("/board/commentDelete/" + deleteData.id)
                , success : function(result) {
                    if ( result.status == true ) {
                        $tr.remove();
                    } else {
                        alert(result.message || "Unknown Error.");
                    }
                }
            });
        });
    });

    /**
     * 코멘트 수정
     */
    $(document).on("click", "a.comment-modify", function() {
        var $this = $(this),
             $tr = $this.parents("tr.comment-tr:eq(0)"),
             updateData   = {};
        updateData.id       = $tr.find("input[name=id]").val();
        updateData.contents = $tr.find("input[name=contents]").val();

        CommentForm({
            id: updateData.id,
            contents: updateData.contents,
            callback: function(data) {
                $.ajax({
                    url: LOCAL.url("/board/commentUpdate"),
                    data: JSON.stringify(data),
                    success : function(result) {
                        if ( result.status == true ) {
                            $tr.find("input[type=hidden][name=id]").val(data.id);
                            $tr.find("input[type=hidden][name=contents]").val(data.contents);
                            $tr.find(".comment-contents").html(data.contents);
                        } else {
                            alert(result.message || "Unknown error.");
                        }
                    }
                });
            }
        });
    });

    /**
     * 처름 화면을 렌더링 할때 코멘트리스트를 보여준다.
     */
    $.ajax({
        url: LOCAL.url("/board/comment/${board.id}"),
        success: function(data) {
            if ( data.status == true ) {
                ShowCommentList(data.list);
            }
        }
    });
});});
</script>
