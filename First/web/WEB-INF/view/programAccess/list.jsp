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
        <p class="text-left"></p>
        <table id="list1"><tr><td></td></tr></table>
        <div id="pager1"></div>
    </div>
</div>

<script type="text/javascript">
;require([
    "jquery",
    "local"
],
function($, LOCAL) { $(document).ready(function() {
    var $grid = $("#list"),
         $detail = $("#list1"),
         curData = {};

    $grid.jqGrid({
        userCache: true,// mongoDB를 Cache를 사용하기 위한 옵션
        caption: "Company List",
        multiselect: true,
        url: LOCAL.url("/company/records"),
        editurl: LOCAL.url("/company/recordEdit"),
        cellurl: LOCAL.url("/company/recordEdit"),
        height: 180,
        colNames:['CompanyID', 'Name', 'Value', 'Description', 'IsActive', 'Created', 'CreatedBy', 'CreatedByName', 'Updated', 'UpdatedBy', 'UpdatedByName'],
        colModel:[
            {name:'adCompanyId', index:'adCompanyId', hidden: true },
            {name:'name', index:'name', width:100, editable: true, frozen: true },
            {name:'value', index:'value', width:150, editable: true },
            {name:'description', index:'description', width:150, editable: true},
            {name:'isActive', index:'isActive', width:20, editable: true, formatter: 'select', edittype: 'select', editoptions: {value: 'Y:Yes;N:No', defaultValue: 'Y'}},
            $.extend({name:'created', index:'created', width:40}, $.jqGridStatic.userDateSetting, {editable: false}),
            {name:'createdBy', index:'createdBy', hidden:true, sorttype:'int', editable: false},
            {name:'createdByName', index:'createdByName', width:40, editable: false},
            $.extend({name:'updated', index:'updated', width:40}, $.jqGridStatic.userDateSetting, {editable: false}),
            {name:'updatedBy', index:'updatedBy', hidden:true, sorttype:'int', editable: false},
            {name:'updatedByName', index:'updatedByName', width:40 , editable: false}
        ],
        pager : "#pager",
        sortname: "adCompanyId",
        sortorder: "desc",
        loadComplete: function(data) {
            var $t = $(this);
            if ( data.rows.length > 0 ) {
                $t.setSelectedRowById($t.getDataIDs()[0]);
                //$t.setSelection($t.getDataIDs()[0]);
            }
        },
        onSelectRow: function (id) {
            var $t = $(this);
            var row = $t.getRowData(id);

            var SelectComplete = function(data) {
                curData = $.extend(curData, data);
                // program, function, role 세가지 데이터가 다 도착해야지 리스트를 뿌려준다.
                if ( curData["program"] && curData["function"] && curData["role"] ) {
                    var colProgramId = $detail.getColModel("adProgramId");
                    var programIdEditOption = $.jqGridStatic.selectEditOptions({
                        data: curData["program"],
                        valueName: "adProgramId",
                        displayName: "name"
                    }, {
                        disabled: false
                    });

                    var colFunctionId = $detail.getColModel("adFunctionId");
                    var functionIdEditOption = $.jqGridStatic.selectEditOptions({
                        data: curData["function"],
                        valueName: "adFunctionId",
                        displayName: "name"
                    }, {
                        disabled: false
                    });

                    var colRoleId = $detail.getColModel("adRoleId");
                    var roleIdEditOption = $.jqGridStatic.selectEditOptions({
                        data: curData["role"],
                        valueName: "adRoleId",
                        displayName: "name"
                    }, {
                        disabled: false
                    });

                    var colCompanyId = $detail.getColModel("adCompanyId");
                    var companyIdEditOption = $.jqGridStatic.selectEditOptions({
                        data: [row],
                        valueName: "adCompanyId",
                        displayName: "name",
                        defaultValue: row["adCompanyId"]
                    }, {
                        disabled: true
                    });

                    $detail.setColModel([
                                $.extend(colProgramId, {editable: true, formatter: 'select', edittype: 'select', editoptions: programIdEditOption}),
                                $.extend(colFunctionId, {editable: true, formatter: 'select', edittype: 'select', editoptions: functionIdEditOption}),
                                $.extend(colRoleId, {editable: true, formatter: 'select', edittype: 'select', editoptions: roleIdEditOption}),
                                $.extend(colCompanyId, {editable: true, formatter: 'select', edittype: 'select', editoptions: companyIdEditOption})
                    ]).setGridParam({
                        datatype: "json",
                        userPostData: {
                            adCompanyId: id
                        }
                    }).trigger("reloadGrid");
                }
            };

            curData = {};
            $.ajax({
                url: LOCAL.url("/program/jqGridSelect"),
                data: {
                    adCompanyId: row["adCompanyId"]
                },
                success: function(result) {
                    if ( result.status == true ) {
                        SelectComplete({program: result.data});
                    }
                }
            });

            $.ajax({
                url: LOCAL.url("/function/jqGridSelect"),
                data: {
                    adCompanyId: row["adCompanyId"]
                },
                success: function(result) {
                    if ( result.status == true ) {
                        SelectComplete({function: result.data});
                    }
                }
            });

            $.ajax({
                url: LOCAL.url("/role/jqGridSelect"),
                data: {
                    adCompanyId: row["adCompanyId"]
                },
                success: function(result) {
                    if ( result.status == true ) {
                        SelectComplete({role: result.data});
                    }
                }
            });
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

    $detail.jqGrid({
        userCache: true,// mongoDB를 Cache를 사용하기 위한 옵션
        caption: "Program Access List",
        multiselect: true,
        url: LOCAL.url("/programAccess/records"),
        editurl: LOCAL.url("/programAccess/recordEdit"),
        cellurl: LOCAL.url("/programAccess/recordEdit"),
        height: 250,
        colNames:['id', "adProgramId", "adFunctionId", "adRoleId", 'adCompanyId', "hasPermission", 'Created', 'CreatedBy', 'CreatedByName', 'Updated', 'UpdatedBy', 'UpdatedByName'],
        colModel:[
            {name:'id', index:'id', hidden: true },
            {name:'adProgramId', index:'adProgramId', width:80, sorttype:'int', editable: false, frozen: true },
            {name:'adFunctionId', index:'adFunctionId', width:80, sorttype:'int', editable: false, frozen: true },
            {name:'adRoleId', index:'adRoleId', width:80, sorttype:'int', editable: false, frozen: true },
            {name:'adCompanyId', index:'adCompanyId', width:80, sorttype:'int', editable: true, frozen: true },
            {name:'hasPermission', index:'hasPermission', width:60, editable: true, formatter: 'select', edittype: 'select', editoptions: {value: 'Y:Yes;N:No', defaultValue: 'Y'}},

            $.extend({name:'created', index:'created', width:60}, $.jqGridStatic.userDateSetting, {editable: false}),
            {name:'createdBy', index:'createdBy', hidden:true, sorttype:'int', editable: false},
            {name:'createdByName', index:'createdByName', width:60, editable: false},
            $.extend({name:'updated', index:'updated', width:60}, $.jqGridStatic.userDateSetting, {editable: false}),
            {name:'updatedBy', index:'updatedBy', hidden:true, sorttype:'int', editable: false},
            {name:'updatedByName', index:'updatedByName', width:60 , editable: false}
        ],
        pager : "#pager1",
        sortname: "adRoleId",
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
    }).jqGrid('navGrid', '#pager1', {
            edit: true,
            add: true,
            del: true,
            search: true,
            refresh: true
        }, { // edit option
            beforeShowForm: function($form) {
                $form.find("#adProgramId, #adFunctionId, #adRoleId, #adCompanyId").prop("disabled", true);
            }
        }
    );

    // #list 호출
    $grid.setGridParam({
        datatype: "json"
    }).trigger("reloadGrid");
});});
</script>
