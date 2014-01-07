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
<%@ taglib uri="http://www.springframework.org/security/tags"   prefix="sec"    %>

<c:choose>
    <c:when test="${ type== 'modify'}">
        <c:set var="id"         value="${board.id}" />
        <c:set var="parentId"   value="${board.parentBoard.id}" />
        <c:set var="level"      value="${board.level}" />
        <c:set var="title"      value="${board.title}" />
        <c:set var="contents"   value="${board.contents}" />
    </c:when>
    <c:when test="${ type == 'reply' }">
        <c:set var="id"         value="" />
        <c:set var="parentId"   value="${board.id}" />
        <c:set var="level"      value="${board.level+1}" />
        <c:set var="title"      value="[RE]${board.title}" />
        <c:set var="contents"   value=">${board.contents}" />
    </c:when>
    <c:otherwise>
        <c:set var="id"         value="" />
        <c:set var="parentId"   value="" />
        <c:set var="level"      value="1" />
        <c:set var="title"      value="${board.title}" />
        <c:set var="contents"   value="${board.contents}" />
    </c:otherwise>
</c:choose>

<div class="panel panel-default">
    <div class="panel-heading">등록</div>
    <div class="panel-body">
    <form method="post" action="/board/writeOk.html">
        <input type="hidden" name="boardName"   value="${boardName}" />
        <input type="hidden" name="id"          value="${id}" />
        <input type="hidden" name="parentId"    value="${parentId}" />
        <input type="hidden" name="level"       value="${level}" />
        <div class="row">
            <div class="col-md-12">
                <input type="text" class="form-control" placeholder="Title" name="title" value="${title}" />
                <p></p>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <textarea rows="10" class="form-control" placeholder="Contents" name="contents">${contents}</textarea>
                <p></p>
            </div>
        </div>
        <div class="row">
            <div class="col-md-2"></div>
            <div class="col-md-10 text-right">
                <input id="btnList" type="button" class="btn btn-default btn-primary" value=" List " />
                <input type="submit" class="btn btn-default btn-primary" value=" Submit " />
            </div>
        </div>
    </form>
    </div>
</div>

<script type="text/javascript">
;require([
    "jquery",
    "local",
    "ckeditor"
],
function($, LOCAL, CKEDITOR) { $(document).ready(function() {
    CKEDITOR.replace("contents", {
        'filebrowserUploadUrl' : LOCAL.url('/board/uploadFile')
    });

    $("#btnList").click(function() {
        location.href = LOCAL.url("/board/listBoard.html?boardName=${boardName}&pageNumber=${pageNumber}&pageSize=${pageSize}&pageBlockSize=${pageBlockSize}");
    });
});});
</script>
