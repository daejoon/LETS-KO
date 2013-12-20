<%--
  Created by IntelliJ IDEA.
  User: KDJ
  Date: 13. 7. 22
  Time: 오후 1:56
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
<div class="container">
    <div class="row">
        <div class="col-md-12"></div>
    </div>
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <form class="form-signin" name="f"  action="<c:url value='/member/login_check' />" method="POST">
                <c:if test="${not empty sessionScope.login_msg}">
                    <h4 class="form-signin-heading"><span style="color: red">${sessionScope.login_msg}</span></h4>
                </c:if>
                <h2 class="form-signin-heading">Please sign in</h2>
                <input type="text" name="username" class="form-control" placeholder="Username" autofocus>
                <input type="password" name="password" class="form-control" placeholder="Password">
                <label class="checkbox">
                    <input type="checkbox" name="remember_me" value="true" checked> Remember me
                </label>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
            </form>
        </div>
        <div class="col-md-4"></div>
    </div>
</div>
