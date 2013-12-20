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
<div class="panel panel-default">
    <div class="panel-heading">${title}</div>
    <div class="panel-body">
        <p class="text-left hidden">
            <button id="btnAdd" class="btn btn-default btn-xs"><i class="fa fa-plus fa-fw"></i> 행삽입</button>
            <button id="btnSaveOrUpdate" class="btn btn-default btn-xs"><i class="fa fa-pencil fa-fw"></i> 행수정</button>
            <button id="btnDelete" class="btn btn-default btn-xs"><i class="fa fa-trash-o fa-fw"></i> 행삭제</button>
            <button id="btnRefresh" class="btn btn-default btn-xs"><i class="fa fa-refresh fa-fw"></i> 새로고침</button>
        </p>
        <table id="list"><tr><td></td></tr></table>
        <div id="pager"></div>
    </div>
</div>

<script type="text/javascript">
;require([
    "jquery",
    "local"
],
function($, LOCAL) { $(document).ready(function() {
    var $grid = $("#list");

    $grid.jqGrid({
        userCache: true,// mongoDB를 Cache를 사용하기 위한 옵션
        caption: "Program List",
        multiselect: true,
        url: LOCAL.url("/program/records"),
        editurl: LOCAL.url("/program/recordEdit"),
        cellurl: LOCAL.url("/program/recordEdit"),
        height: 300,
        colNames:['id', 'CompanyID', 'ProgramID', 'Name', 'Url', 'Description', 'IsActive', 'Created', 'CreatedBy', 'CreatedByName', 'Updated', 'UpdatedBy', 'UpdatedByName'],
        colModel:[
            {name:'id', index:'id', hidden: true },
            {name:'adCompanyId', index:'adCompanyId', width:80, editable: false, frozen: true },
            {name:'adProgramId', index:'adProgramId', hidden: true},
            {name:'name', index:'name', width:100, editable: true, frozen: true },
            {name:'url', index:'url', width:200, editable: true },
            {name:'description', index:'description', width:100, editable: true},
            {name:'isActive', index:'isActive', width:40, editable: true, formatter: 'select', edittype: 'select', editoptions: {value: 'Y:Yes;N:No', defaultValue: 'Y'}},
            $.extend({name:'created', index:'created', width:60}, $.jqGridStatic.userDateSetting, {editable: false}),
            {name:'createdBy', index:'createdBy', hidden:true, sorttype:'int', editable: false},
            {name:'createdByName', index:'createdByName', width:40, editable: false},
            $.extend({name:'updated', index:'updated', width:60}, $.jqGridStatic.userDateSetting, {editable: false}),
            {name:'updatedBy', index:'updatedBy', hidden:true, sorttype:'int', editable: false},
            {name:'updatedByName', index:'updatedByName', width:40 , editable: false}
        ],
        pager : "#pager",
        sortname: "adProgramId",
        sortorder: "desc",
        loadComplete: function(data) {
            var $t = $(this);
            if ( data.rows.length > 0 ) {
                $t.setSelectedRowById($t.getDataIDs()[0]);
            }
        },
        onSelectRow: function (id) {
        },
        ondblClickRow: function(id, iRow, iCol, e) {
        }
    }).jqGrid('navGrid', '#pager', {
        edit: true,
        add: true,
        del: true,
        search: true,
        refresh: true
    });
    //$grid.jqGrid('setFrozenColumns');

    $.ajax({
        url: LOCAL.url("/company/jqGridSelect"),
        success: function(result) {
            if ( result.status == true ) {
                var companyId = $grid.getColModel("adCompanyId");
                var companyIdEditOption = $.jqGridStatic.selectEditOptions({
                    data: result.data,
                    valueName: "adCompanyId",
                    displayName: "name"
                });
                $grid.setColModel([
                    $.extend(companyId, {editable: true, formatter: 'select', edittype: 'select', editoptions: companyIdEditOption})
                ]).setGridParam({
                    datatype: "json"
                }).trigger("reloadGrid");
            }
        }
    });
});});
</script>
