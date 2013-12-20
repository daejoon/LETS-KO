/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 11. 14
 * Time: 오후 4:55
 * To change this template use File | Settings | File Templates.
 */
;define([
    "jquery",
    "jqgrid",
    "jquery-ui-datepicker-lang"
],
function($) { "use strict";
    var _id = $.createGUID();
    var _guidName = "_local_jqgrid_guid_";
    var _userCacheName = "userCache";
    var _userPostDataName = "userPostData";

    var _DataConvert = function(data) {
        var $t = $(this),
            datatype  = $t.jqGrid('getGridParam', 'datatype'),
            userCache = $t.jqGrid('getGridParam', _userCacheName) || false,
            userPostData  = $t.jqGrid('getGridParam', _userPostDataName) || {},
            newData   = "";

        if ( typeof data[_userCacheName] === "undefined" ) {
            data[_userCacheName] = userCache;
        }

        if ( typeof data[_guidName] === "undefined" ) {
            data[_guidName] = _id;
        }

        if ( typeof data[_userPostDataName] === "undefined" ) {
            data[_userPostDataName] = userPostData;
        }

        switch (datatype.toLowerCase()) {
            case "json":
                newData = JSON.stringify(data);
                break;
            default :
                newData = data;
                break;
        }
        return newData;
    };

    /**
     * jqGrid default options
     */
    $.extend($.jgrid.defaults, {
        userCache: false,
        autowidth: true,
        viewrecords: true,
        gridview: true,// true로 하면은 treeGrid, subGrid, or the afterInsertRow event. 사용할수 없다. 대신 속도는 빠르다.
        prmNames: {
            page: "pageNumber",
            rows: "pageSize"
        },
        jsonReader: {
            page: "page",
            total: "total",
            records: "records",
            root: "rows",
            id: function(obj) {return (obj.idName||"0");},
            repeatitems: false,// json을 데이터로 사용한다.
            cell: "cell",// repeatitems: false 이면 무시된다.
            userdate: "userdata"
        },
        loadError: function(xhr,st,err) {
            alert("Server Failure" + "\nType: "+st+"\nResponse: "+ xhr.status + " "+xhr.statusText );
        },
        mtype: "POST",//Defines the type of request to make (“POST” or “GET”)
        datatype: "local",
        ajaxGridOptions: { contentType: "application/json" },
        ajaxRowOptions: { contentType: "application/json" },
        ajaxCellOptions: { contentType: "application/json" },
        serializeGridData: _DataConvert,
        serializeCellData: _DataConvert,
        cellsubmit: 'clientArray',// 셀 수정했을때 자동으로 서버에 전송되지 않는다.
        cellEdit: false,
        rowNum : 10,
        rowList: [10, 15, 25, 50, 100, 200]
    });

    /**
     * jqGrid edit options
     */
    $.extend($.jgrid.edit, {
        ajaxEditOptions: { contentType: "application/json" },
        recreateForm: true,
        serializeEditData: _DataConvert
    });

    /**
     * jqGrid dell options
     */
    $.extend($.jgrid.del, {
        ajaxDelOptions: { contentType: "application/json" },
        recreateForm: true,
        serializeDelData: _DataConvert
    });

    /**
     * jqGrid 확장 메소드
     */
    $.jgrid.extend({
        addNewRow: function() {
            var $that = this;
            return $that.each(function() {
                var $t = $(this);
                $t.addRowData("", {});
            });
        },
        getSelectedRowId: function() {
            var $t = this,
                selectedId = $t.find("tr.selected-row").prop("id");

            if ( selectedId == undefined  || selectedId == null ) {
                return null;
            }
            return selectedId;
        },
        setSelectedRowById: function(rowid) {
            var $that = this;

            return  $that.each(function() {
                var $t = $(this),
                    iRow = 0,
                    iCol = 0;
                $.each($t.getDataIDs(), function(idx, value) {
                    if ( parseInt(value) == parseInt(rowid) ) {
                        iRow = idx;
                        return false;
                    }
                });
                var $tr = $t.find("tr[id=" + rowid + "]");
                $tr.addClass("selected-row ui-state-hover");
                $t[0].p.selrow = rowid;
                $t[0].p.iRow = iRow+1;
                $t[0].p.iCol = iCol;
            });
        },
        getSelectedRowData: function() {
            var $t = this,
                id = $t.getSelectedRowId();

            if ( id == null ) {
                return [];
            }
            return $t.getRowData(id);
        },
        getMultiSelectedRowIDs: function() {
            var $t = this,
                isMultiSelect = $t.getGridParam('multiselect'),
                result = [];
            if ( isMultiSelect == true ) {
                $t.find("tr[aria-selected=true]").each(function() {
                    var $tr = $(this);
                    var id = $tr.prop("id");
                    result.push(id);
                });
            }
            return result;
        },
        getMultiSelectedRowData: function() {
            var $t = this,
                isMultiSelect = $t.getGridParam('multiselect'),
                result = [];
            if ( isMultiSelect == true ) {
                var ids = $t.getMultiSelectedRowIDs();
                $.each(ids, function(idx, id) {
                    result.push( $t.getRowData(id));
                });

            }
            return result;
        },
        getColModel: function(name) {
            var $t = this,
                colModel = $t.getGridParam("colModel");
            if ( arguments.length == 0 ) {
                return colModel;
            } else if ( $.isNumeric(name)) {
                return colModel[name];
            } else {
                var colModelRow = {};
                $.each(colModel, function(idx, value) {
                    if ( value.name == name ) {
                        colModelRow = value;
                        return false;
                    }
                });
                return colModelRow;
            }
        },
        setColModel: function(updateColModel) {
            // 속도를 빠르게 하기 위해서 해쉬를 이용한다.
            var mapUpdateColModel = {};
            $.each(updateColModel, function (idx, value) {
                mapUpdateColModel[value.name] = value;
            });

            return this.each(function () {
                var $t         = $(this),
                    colModel    = $t.getGridParam("colModel"),
                    newColModel = [];
                $.each(colModel, function (idx, value) {
                    if ( mapUpdateColModel[value.name] ) {
                        newColModel.push(mapUpdateColModel[value.name]);
                    } else {
                        newColModel.push(value);
                    }
                });
                $t.setGridParam("colModel", newColModel);
            });
        }
    })

    /**
     * jqGrid Static 메소드 모음
     */
    $.extend( ($.jqGridStatic = $.jqGridStatic || {}), {
        /**
         * 날짜 포맷에 대한 설정
         */
        userDateSetting: {
            sorttype: 'date',
            formatter: function(cellValue /* cell의 값 */, option /* colModel option  */, rowObject /* 현재 row 값 */) {
                var date = null;

                if ( cellValue == undefined || cellValue == null ) {
                    return "";
                }

                if ( /^\d+$/gi.test(cellValue.toString()) == true ) {
                    date = new Date( parseInt(cellValue)  );
                    return date.format("yyyy-MM-dd");
                } else {
                    return cellValue.toString();
                }
            },
            editable: true,
            edittype: 'text',
            editoptions: {
                size: 12,
                maxlengh: 12,
                dataInit: function (element) {
                    $(element).datepicker({ dateFormat: 'yy-mm-dd' });
                }
            },
            editrules: {
                date: true
            }
        },
        /**
         * jqGrid에서 사용하는 editoptions 중에 Select를 만들어준다.
         * @param setting {defaultOptionUsed, defaultOption, data, valueName, displayName, defaultValue}
         * @param option
         * @returns {*|Object}
         */
        selectEditOptions: function(setting , option ) {
            var ret = $.extend({}, option || {});
            if ($.isPlainObject(setting)) {
                var valueString = "";
                var newSetting = $.extend({
                    defaultOptionUsed: true,
                    defaultOption: {
                        value: null,
                        display: "◆ 선택해주세요 ◆"
                    },
                    data: [],
                    valueName: "",
                    displayName: "",
                    defaultValue: null
                }, setting);

                if ( newSetting.defaultOptionUsed == true ) {
                    if ( $.isPlainObject(newSetting.defaultOption) ) {
                        valueString += "" + newSetting.defaultOption.value + ":" + newSetting.defaultOption.display;
                    } else  {
                        valueString += newSetting.defaultOption;
                    }
                }

                $.each(newSetting.data, function(idx, value) {
                    valueString += ";" + value[newSetting.valueName] + ":" + value[newSetting.displayName];
                });
                if ( valueString.charAt(0) == ";" ) {
                    valueString = valueString.substring(1);
                }

                ret["value"] = valueString;
                ret["defaultValue"] = "" + newSetting.defaultValue;
            }
            return ret;
        },
        /**
         * [{},{},{},...] 데이터셋 구조에서 해당 Row를 Key Value로 찾아서 리턴한다.
         * @param data {arrayData}
         * @param obj {key,value}
         * @returns {*|Object}
         */
        eachRowByKeyValue: function(data, obj) {
            var ret = {};
            $.each(data, function(idx, value) {
                if ( value[obj.key] && value[obj.key] == obj.value ) {
                    ret = value;
                    return false;
                }
            });
            return ret;
        }
    });
});
