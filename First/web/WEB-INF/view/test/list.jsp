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
        datatype: "local",
        height: 250,
        colNames:['Inv No','Date', 'Client', 'Amount','Tax','Total','Notes', 'test'],
        colModel:[
            {name:'id',index:'id', width:60, sorttype:"int"},
            {name:'invdate',index:'invdate', width:90, sorttype:"date"},
            {name:'name',index:'name', width:100},
            {name:'amount',index:'amount', width:80, align:"right",sorttype:"float"},
            {name:'tax',index:'tax', width:80, align:"right",sorttype:"float"},
            {name:'total',index:'total', width:80,align:"right",sorttype:"float"},
            {name:'note',index:'note', width:150, sortable:false},
            {
                name: 'test',
                index: 'test',
                width:150,
                sorttype: function(cellvalue) {
                    return cellvalue['name'];
                },
                formatter: function(cellvalue, options, rowObject) {
                    return cellvalue['name'];
                }
            }
        ],
        multiselect: true,
        caption: "Manipulating Array Data"
    });
    var mydata = [
        {id:"1",invdate:"2007-10-01",name:"test",note:"note",amount:"200.00",tax:"10.00",total:"210.00"     , test: {name: 'testName1'}},
        {id:"2",invdate:"2007-10-02",name:"test2",note:"note2",amount:"300.00",tax:"20.00",total:"320.00"   , test: {name: 'testName2'}},
        {id:"3",invdate:"2007-09-01",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"   , test: {name: 'testName3'}},
        {id:"4",invdate:"2007-10-04",name:"test",note:"note",amount:"200.00",tax:"10.00",total:"210.00"     , test: {name: 'testName4'}},
        {id:"5",invdate:"2007-10-05",name:"test2",note:"note2",amount:"300.00",tax:"20.00",total:"320.00"   , test: {name: 'testName5'}},
        {id:"6",invdate:"2007-09-06",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"   , test: {name: 'testName6'}},
        {id:"7",invdate:"2007-10-04",name:"test",note:"note",amount:"200.00",tax:"10.00",total:"210.00"     , test: {name: 'testName7'}},
        {id:"8",invdate:"2007-10-03",name:"test2",note:"note2",amount:"300.00",tax:"20.00",total:"320.00"   , test: {name: 'testName8'}},
        {id:"9",invdate:"2007-09-01",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"   , test: {name: 'testName9'}}
    ];

    for(var i=0;i<=mydata.length;i++)
        $grid.jqGrid('addRowData',i+1,mydata[i]);
});});
</script>
