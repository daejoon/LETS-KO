<%--
  Created by IntelliJ IDEA.
  User: KDJ
  Date: 13. 7. 29
  Time: 오전 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"              prefix="c"	    %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"         prefix="fn"     %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"               prefix="fmt"    %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles"             prefix="tiles"  %>
<%@ taglib uri="http://www.springframework.org/security/tags"   prefix="sec"    %>
<%@ taglib uri="http://www.springframework.org/tags"            prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form"       prefix="form"   %>

<div class="panel panel-default">
    <div class="panel-heading">User Info (${method})</div>
    <div class="panel-body">
        <div class="row">
            <div class="col-md-12">
                ${user.name}
                <p></p>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                ${user.age}
                <p></p>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                ${user.email}
                <p></p>
            </div>
        </div>
        <div class="row">
            <div class="col-md-2"></div>
            <div class="col-md-10 text-right">
            </div>
        </div>
    </div>
</div>
