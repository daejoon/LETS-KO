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
<%@ taglib uri="http://www.springframework.org/security/tags"   prefix="sec" %>
<div class="panel panel-default">
    <div class="panel-heading">회원 목록</div>
    <div class="panel-body">
        <p>현재 가입되어 있는 회원 목록 리스트 입니다.</p>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th class="col-md-1">순서</th>
                <th class="col-md-2">이름</th>
                <th class="col-md-12">비밀번호</th>
                <th class="col-md-2">ROLE</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="member" items="${list}" varStatus="rowCnt">
                <tr>
                    <td style="text-align: right;">${rowCnt.index+1}</td>
                    <td>${member.username}</td>
                    <td style="word-break: break-all; word-wrap: break-word;">${member.password}</td>
                    <td>
                        <c:forEach var="role" items="${member.roles}">
                            ${role.roleName}
                        </c:forEach>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>




