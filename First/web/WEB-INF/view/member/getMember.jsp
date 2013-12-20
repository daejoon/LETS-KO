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
<c:if test="${empty member}">
    <form method="post" action="/member/getMember.html?action=true">
        id <input type="text" name="id" /> <br />
        <input type="submit" value="전송" />
    </form>
</c:if>
<c:if test="${not empty member}">
    <div class="panel panel-default">
        <div class="panel-heading">회원 정보</div>
        <table class="table">
        <tbody>
            <tr>
                <td class="col-md-2">이름</td>
                <td class="col-md-9">${member.username}</td>
            </tr>
            <tr>
                <td class="col-md-2">비밀번호</td>
                <td class="col-md-9" style="word-break: break-all; word-wrap: break-word">${member.password}</td>
            </tr>
            <tr>
                <td class="col-md-2">ROLE</td>
                <td class="col-md-9">
                    <c:forEach var="role" items="${member.roles}">
                        ${role.codeRole.name}&nbsp;
                    </c:forEach>
                </td>
            </tr>
        </tbody>
        </table>
    </div>
</c:if>


