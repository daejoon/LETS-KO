<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"              prefix="c"	    %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"         prefix="fn"     %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"               prefix="fmt"    %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles"             prefix="tiles"  %>
<%@ taglib uri="http://www.springframework.org/tags"            prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags"   prefix="sec"    %>
<div class="panel panel-default">
    <div class="panel-heading">${title}</div>
    <div class="panel-body">
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
        userCache: true,
        caption: "Sample List",
        multiselect: true,
        url: LOCAL.url("/sample/records"),
        editurl: LOCAL.url("/sample/recordEdit"),
        cellurl: LOCAL.url("/sample/recordEdit"),
        height: 300,
        colNames:['id', 'name', 'age', 'description'],
        colModel:[
            {name:'id', index:'id', hidden: true },
            {name:'name', index:'name', width:100, editable: true, frozen: true },
            {name:'age', index:'name', width:100, editable: true, frozen: true },
            {name:'description', index:'description', width:200, editable: true}
        ],
        pager : "#pager",
        sortname: "name",
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

    $grid.setGridParam({
        datatype: "json"
    }).trigger("reloadGrid");
});});
</script>
