<%--
  Created by IntelliJ IDEA.
  User: KDJ
  Date: 13. 8. 6
  Time: 오전 11:51
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
<!DOCTYPE html>
<html lang="${config.language}">
<head>
    <title><tiles:insertAttribute name="title" /></title>
    <tiles:insertAttribute name="head" />
</head>
<body>
<tiles:insertAttribute name="top" />
<div class="container">
    <div class="row">
        <div class="col-md-2">
            <tiles:insertAttribute name="left" />
        </div>
        <div class="col-md-10" role="main">
            <tiles:insertAttribute name="contents" />
        </div>
    </div>
</div>
<tiles:insertAttribute name="bottom" />
<tiles:insertAttribute name="javascript" />
</body>
</html>