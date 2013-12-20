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
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h1></h1>
        </div>
    </div>
    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <form method="post" action="/member/addMember?action=true">
                <div class="panel panel-default">
                    <div class="panel-heading">등록</div>
                    <table class="table">
                        <tbody>
                        <tr>
                            <td class="col-md-2">Username</td>
                            <td class="col-md-9">
                                <input type="text" class="form-control" placeholder="Username" name="username" />
                            </td>
                        </tr>
                        <tr>
                            <td class="col-md-2">Password</td>
                            <td class="col-md-9">
                                <input type="password" class="form-control" placeholder="Password" name="password" />
                            </td>
                        </tr>
                        <tr>
                            <td class="col-md-2">Enabled</td>
                            <td class="col-md-9">
                                <input type="radio" name="enabled" value="true" checked="true"/> TRUE
                                <input type="radio" name="enabled" value="false" /> FALSE
                            </td>
                        </tr>
                        <tr>
                            <td class="col-md-2">Authority</td>
                            <td class="col-md-9">
                                <select name="authority" class="form-control">
                                    <c:forEach var="role" items="${codeRoles}">
                                        <option value="${role.id}">${role.name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td class="col-md-2"></td>
                            <td class="col-md-9" style="text-align: right">
                                <input type="submit" class="btn btn-default btn-primary" value=" Submit " />
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </form>
        </div>
        <div class="col-md-3"></div>
    </div>
</div>
