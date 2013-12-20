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
<div class="page-header">
    <h1 id="download">Member 삭제</h1>
</div>
<form method="post" action="/member/deleteMember.html?action=true">
    id <input type="text" name="id" /> <br />
    <input type="submit" value="전송" />
</form>
