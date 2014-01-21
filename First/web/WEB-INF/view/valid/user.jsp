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
<%@ taglib uri="http://www.springframework.org/tags/form"       prefix="form"   %>

<div class="panel panel-default">
    <div class="panel-heading">등록</div>
    <div class="panel-body">
        <form:form modelAttribute="user" method="POST">
        <div class="row">
            <div class="col-md-12">
                <form:input path="name" name="name" cssClass="form-control" placeholder="name" />
                <form:errors path="name" />
                <p></p>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <form:input path="age" name="age" cssClass="form-control" placeholder="age" />
                <form:errors path="age" />
                <p></p>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <form:input path="email" name="email" cssClass="form-control" placeholder="email" />
                <form:errors path="email" />
                <p></p>
            </div>
        </div>
        <div class="row">
            <div class="col-md-2"></div>
            <div class="col-md-10 text-right">
                <input type="submit" class="btn btn-default btn-primary" value=" Submit " />
            </div>
        </div>
        </form:form>
    </div>

    <div class="panel-heading">수정</div>
    <div class="panel-body">
        <form:form modelAttribute="user" method="PUT">
            <div class="row">
                <div class="col-md-12">
                    <form:input path="name" name="name" cssClass="form-control" placeholder="name" />
                    <form:errors path="name" />
                    <p></p>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <form:input path="age" name="age" cssClass="form-control" placeholder="age" />
                    <form:errors path="age" />
                    <p></p>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <form:input path="email" name="email" cssClass="form-control" placeholder="email" />
                    <form:errors path="email" />
                    <p></p>
                </div>
            </div>
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-10 text-right">
                    <input type="submit" class="btn btn-default btn-primary" value=" Submit " />
                </div>
            </div>
        </form:form>
    </div>

    <div class="panel-heading">삭제</div>
    <div class="panel-body">
        <form:form modelAttribute="user" method="DELETE">
            <div class="row">
                <div class="col-md-12">
                    <form:input path="name" name="name" cssClass="form-control" placeholder="name" />
                    <form:errors path="name" />
                    <p></p>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <form:input path="age" name="age" cssClass="form-control" placeholder="age" />
                    <form:errors path="age" />
                    <p></p>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <form:input path="email" name="email" cssClass="form-control" placeholder="email" />
                    <form:errors path="email" />
                    <p></p>
                </div>
            </div>
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-10 text-right">
                    <input type="submit" class="btn btn-default btn-primary" value=" Submit " />
                </div>
            </div>
        </form:form>
    </div>
</div>
