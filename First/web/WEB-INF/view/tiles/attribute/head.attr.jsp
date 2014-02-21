<%--
  Created by IntelliJ IDEA.
  User: KDJ
  Date: 13. 8. 6
  Time: 오전 11:49
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
    <!-- meta data -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="letsko">
    <meta name="author" content="ddoong2">

    <!-- icon -->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/image/common/daemon.ico">

    <!-- stylesheet -->
    <link href="${pageContext.request.contextPath}/static/lib/bootstrap/3.0.3/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/lib/bootstrap/3.0.3/css/bootstrap-theme.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/lib/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/lib/highlight/7.5/styles/default.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/lib/jqgrid/4.5.4/src/css/ui.jqgrid.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/lib/jqgrid/4.5.4/src/css/ui.multiselect.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/lib/jquery-ui/1.10.3/themes/base/jquery-ui.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/default.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/jqgrid.bootstrap.css" rel="stylesheet">

    <!-- script -->
    <script src="${pageContext.request.contextPath}/static/lib/require/2.1.9/require.src.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/main.js"></script>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="${pageContext.request.contextPath}/static/lib/html5shiv/3.7.0/html5shiv.src.js"></script>
    <script src="${pageContext.request.contextPath}/static/lib/respond/1.4.0/respond.src.js"></script>
    <![endif]-->

    <!-- initialize -->
    <script type="text/javascript">
    ;require([
        "jquery",
        "local"
    ],
    function($, LOCAL) {
        $(document).ready(function() {
            LOCAL.contextPath.set("${pageContext.request.contextPath}");
            LOCAL.mode.set("${config.type}");
<sec:authorize ifNotGranted="ROLE_ANONYMOUS"><sec:authentication property="principal" var="memberJs"/>
            LOCAL.session.setId( parseInt("${memberJs.id}", 10) || "" );
            LOCAL.session.setUsername("${memberJs.username}" || "");
</sec:authorize>
        });
    });
    </script>
