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
<%@ taglib uri="http://www.springframework.org/security/tags"   prefix="sec"    %>
<!-- Fixed navbar -->
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">Let's Ko</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <sec:authorize ifAnyGranted="ROLE_ANONYMOUS">
                    <li class=""><a href="/member/login">로그인</a></li>
                    <li class=""><a href="/member/addMember">회원가입</a></li>
                </sec:authorize>
                <sec:authorize ifNotGranted="ROLE_ANONYMOUS">
                    <li class=""><a href="/main/dashboard">내 페이지</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><sec:authentication property="principal.username" />&nbsp;&nbsp;<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="/member/getMember?action=true&id=<sec:authentication property="principal.id" />">정보수정</a></li>
                            <li class="divider"></li>
                            <li><a href="/member/logout_check">로그아웃</a></li>
                        </ul>
                    </li>
                </sec:authorize>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</div>
