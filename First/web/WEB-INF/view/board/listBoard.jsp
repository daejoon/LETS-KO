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
    <div class="panel-heading">${boardName} 게시판</div>
    <div class="panel-body">
        <p>현재 글 리스트 입니다.</p>
        <table class="table table-bordered table-hover">
            <thead>
            <tr>
                <th class="col-md-1">순서</th>
                <th class="col-md-2">작성자</th>
                <th class="col-md-12">제목</th>
                <th class="col-md-2">작성일</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="board" items="${list}" varStatus="rowCnt">
                <c:set var="indent" value="" />
                <c:if test="${board.level > 1}">
                    <c:forEach begin="1" end="${board.level}" step="1">
                        <c:set var="indent" value="&nbsp;&nbsp;&nbsp&nbsp${indent}" />
                    </c:forEach>
                </c:if>
                <tr class="boardRow">
                    <td style="text-align: right;">${listCount-((pageNumber-1)*pageSize)-rowCnt.index}</td>
                    <td>${board.member.username}</td>
                    <td style="word-break: break-all; word-wrap: break-word;">
                            ${indent}
                        <a href="<c:url value="/board/getBoard.html" />?id=${board.id}&boardName=${boardName}&pageNumber=${pageNumber}&pageSize=${pageSize}&pageBlockSize=${pageBlockSize}">
                                ${board.title}
                            <c:if test="${board.commentCount > 0}">
                                [${board.commentCount}]
                            </c:if>
                        </a>
                        <c:if test="${board.replyCount > 0}">
                            답글 <a href="#" class="replyAnchorOff" data-local='{ "id":${board.id}, "boardName":"${boardName}", "pageNumber":${pageNumber}, "pageSize":${pageSize}, "pageBlockSize":${pageBlockSize} }' >${board.replyCount}개</a>
                        </c:if>
                    </td>
                    <td>${board.createDate}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <p class="text-right">
            <button id="btnWrite" class="btn btn-default btn-xs">
                <span class="glyphicon glyphicon-plus"></span> Write
            </button>
        </p>
    </div>
    <div class="panel-body text-center">
        <c:set var="baseUrl"    value="${requestScope['javax.servlet.forward.servlet_path']}" />
        <c:set var="pageCount"  value="${(listCount%pageSize==0) ? listCount/pageSize:listCount/pageSize+1}" />
        <c:set var="start"      value="${pageNumber < pageBlockSize ? 1 : pageNumber}" />
        <c:set var="end"        value="${start+pageBlockSize-1 < pageCount ? start+pageBlockSize-1:pageCount}" />
        <c:set var="previous"   value="${pageNumber>1 ? pageNumber-1:1}" />
        <c:set var="next"       value="${pageNumber<pageCount-1 ? pageNumber+1:pageNumber}" />
        <ul class="pagination pagination-sm">
            <li><a href="#">&laquo;</a></li>
            <li><a href="<c:url value="${baseUrl}" />?boardName=${boardName}&pageNumber=${previous}&pageSize=${pageSize}&pageBlockSize=${pageBlockSize}">Previous</a></li>
            <c:forEach begin="${start}" end="${end}" step="1" varStatus="rowStatus">
                <li ${pageNumber==rowStatus.index ? "class='active'":""}><a href="<c:url value="${baseUrl}" />?boardName=${boardName}&pageNumber=${rowStatus.index}&pageSize=${pageSize}&pageBlockSize=${pageBlockSize}">${rowStatus.index}</a></li>
            </c:forEach>
            <li><a href="<c:url value="${baseUrl}" />?boardName=${boardName}&pageNumber=${next}&pageSize=${pageSize}&pageBlockSize=${pageBlockSize}">Next</a></li>
            <li><a href="#">&raquo;</a></li>
        </ul>
    </div>
</div>

<script type="text/template" class="reply-tpl">
    <? _.each(ds, function(val) { ?>
    <tr class="replyRow<?=val.topBoardId?>">
        <td style="text-align: right;"></td>
        <td><?=val.username?></td>
        <td style="word-break: break-all; word-wrap: break-word;">
            <? for(var idx = 0; idx < val.level; idx++) { ?><?= "&nbsp;&nbsp;&nbsp;&nbsp;" ?><? } ?>
            <a href="<?=LOCAL.url('/board/getBoard.html')?>?id=<?=val.id?>&boardName=${boardName}&pageNumber=${pageNumber}&pageSize=${pageSize}&pageBlockSize=${pageBlockSize}">
                <?=val.title?>
                <? if ( val.commentCount > 0 ) { ?>
                [<?=val.commentCount?>]
                <? } ?>
            </a>
        </td>
        <td><?=(new Date(val.createDate)).format("yyyy/MM/dd a/p HH:mm")?></td>
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
    $("#btnWrite").click(function() {
        location.href = LOCAL.url("/board/write.html?boardName=${boardName}&pageNumber=${pageNumber}&pageSize=${pageSize}&pageBlockSize=${pageBlockSize}");
    });

    $(".replyAnchorOn, .replyAnchorOff").bind("click", function() {
        var $this = $(this),
            aData = $.parseJSON($this.attr("data-local")),
            $tr   = $this.parents("tr:eq(0)"),
            parentId = aData.id;

        if ( $this.prop("class").indexOf("replyAnchorOff") >= 0 ) {
            $.ajax({
                url: LOCAL.url("/board/replyList.html?id="+parentId),
                success: function(result) {
                    $tr.after( _.template($("script.reply-tpl").html(), {LOCAL: LOCAL, ds : result.list}) );
                    $this.removeClass("replyAnchorOff").addClass("replyAnchorOn");
                }
            });//$.ajax({
        } else if ( $this.prop("class").indexOf("replyAnchorOn") >= 0 ) {
            $(".replyRow"+parentId).remove();
            $this.removeClass("replyAnchorOn").addClass("replyAnchorOff");
        }
    });
});});
</script>
