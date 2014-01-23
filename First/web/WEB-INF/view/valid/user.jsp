<%--
  Created by IntelliJ IDEA.
  User: KDJ
  Date: 13. 7. 29
  Time: 오전 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"              prefix="c"      %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"         prefix="fn"     %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"               prefix="fmt"    %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles"             prefix="tiles"  %>
<%@ taglib uri="http://www.springframework.org/security/tags"   prefix="sec"    %>
<%@ taglib uri="http://www.springframework.org/tags/form"       prefix="form"   %>

<div class="panel panel-default">
    <div class="panel-heading">User</div>
    <div class="panel-body">
        <form:form modelAttribute="user" method="POST">
        <div class="row">
            <div class="col-md-12">
                <form:input path="name" name="name" cssClass="form-control" placeholder="name" />
                <form:errors path="name" cssStyle="color: #ff0000"/>
                <p></p>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <form:input path="age" name="age" cssClass="form-control" placeholder="age" />
                <form:errors path="age" cssStyle="color: #ff0000"/>
                <p></p>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <form:input path="email" name="email" cssClass="form-control" placeholder="email" />
                <form:errors path="email" cssStyle="color: #ff0000"/>
                <p></p>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <form:input path="createDate" name="createDate" cssClass="form-control" placeholder="createDate" />
                <form:errors path="createDate" cssStyle="color: #ff0000"/>
                <p></p>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <form:input path="description" name="description" cssClass="form-control" placeholder="description" />
                <form:errors path="description" cssStyle="color: #ff0000"/>
                <p></p>
            </div>
        </div>
        <div class="row">
            <div class="col-md-2"></div>
            <div class="col-md-10 text-right">
                <button id="POST"    class="btn btn-default btn-primary">등록</button>
                <button id="PUT"     class="btn btn-default btn-primary">수정</button>
                <button id="DELETE"  class="btn btn-default btn-primary">삭제</button>
            </div>
        </div>
        </form:form>
    </div>
</div>
<script type="text/javascript">
;require([
    "jquery",
    "local"
],
function($, LOCAL) { $(document).ready(function() {
    var $form = $("#user");

    $("button").on("click", function (event) {
        var $self = $(this);
        var methodName = $self.prop("id");

        var $methods = $form.find("input[type=hidden][name=_method]");
        if ( $methods.length > 0 ) {
            $methods.each(function(index, element) {
                $self = $(this);
                $self.val(methodName);
            });
        } else {
            $("<input />")
                    .prop("type", "hidden")
                    .prop("name", "_method")
                    .prop("value", methodName)
                    .appendTo($form);
        }
        $form.submit();
    });
});});
</script>
