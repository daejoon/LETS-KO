<%--
  Created by IntelliJ IDEA.
  User: KDJ
  Date: 13. 8. 6
  Time: 오전 11:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"              prefix="c"	    %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"         prefix="fn"     %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"               prefix="fmt"    %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles"             prefix="tiles"  %>
<%@ taglib uri="http://www.springframework.org/tags"            prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags"   prefix="sec"    %>
<div id="left-menu" class="list-group">
    <%--<a class="list-group-item" href="<c:url value='/member/addMember.html' />">구 회원등록</a>--%>
    <%--<a class="list-group-item" href="<c:url value='/member/getMember.html' />">회원조회</a>--%>
    <%--<a class="list-group-item" href="<c:url value='/member/deleteMember.html' />">회원삭제</a>--%>
    <%--<a class="list-group-item" href="<c:url value='/member/listMember.html' />">회원목록</a>--%>
    <a class="list-group-item" href="<c:url value='/board/write.html?boardName=QnA' />">글쓰기</a>
    <a class="list-group-item" href="<c:url value='/board/listBoard.html?boardName=QnA' />">글목록</a>
    <a class="list-group-item" href="<c:url value='/test/comm/list/title/테스트' />">테스트</a>
    <a class="list-group-item" href="<c:url value='/company/comm/list/title/회사관리' />">회사관리</a>
    <a class="list-group-item" href="<c:url value='/role/comm/list/title/회사권한관리' />">회사권한관리</a>
    <a class="list-group-item" href="<c:url value='/user/comm/list/title/회원관리' />">회원관리</a>
    <a class="list-group-item" href="<c:url value='/userRole/comm/list/title/회원권한관리' />">회원권한관리</a>
    <a class="list-group-item" href="<c:url value='/program/comm/list/title/프로그램관리' />">프로그램관리</a>
    <a class="list-group-item" href="<c:url value='/function/comm/list/title/기능관리' />">기능관리</a>
    <a class="list-group-item" href="<c:url value='/programFunction/comm/list/title/프로그램별기능관리' />">프로그램별기능관리</a>
    <a class="list-group-item" href="<c:url value='/programAccess/comm/list/title/프로그램기능별권한관리' />">프로그램기능별권한관리</a>
    <a class="list-group-item" href="<c:url value='/sample/comm/list/title/Sample' />">Sample</a>
    <a class="list-group-item" href="<c:url value='/valid/user' />">Validation Test</a>
</div>
<script type="text/javascript">
;require([
    "jquery",
    "local"
],
function($, LOCAL) { $(document).ready(function() {
    var cur_url = location.pathname;
    if ( /[가-힣]/gi.test(cur_url) ) {
        cur_url = encodeURI( cur_url );
    }

    $("#left-menu a").each(function(idx) {
        var $this = $(this),
            href  = $this.attr("href");

        if ( /[가-힣]/gi.test(href) ) {
            href = encodeURI( href );
        }

        if ( href.toUpperCase().indexOf( cur_url.toUpperCase() ) >= 0 ) {
            $this.addClass("active");
        } else {
            $this.removeClass("active");
        }
    });
});});
</script>

