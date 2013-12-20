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
<%@ taglib uri="http://www.springframework.org/security/tags"   prefix="sec" %>
<sec:authorize ifNotGranted="ROLE_ANONYMOUS">
    <sec:authentication property="principal" var="user"/>
</sec:authorize>
<div class="jumbotron">
    <div class="container">
        <h1>${user.username}님 안녕하세요.</h1>
        <p style="margin-top: 10px">&nbsp;&nbsp;&nbsp;</p>
        <p><a href="/main/dashboard" class="btn btn-primary btn-lg">내 페이지로 이동 &raquo;</a></p>
    </div>
</div>