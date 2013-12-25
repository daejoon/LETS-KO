/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 10. 10
 * Time: 오후 3:06
 * To change this template use File | Settings | File Templates.
 */
;define([
    "jquery",
    "json"
],
function($, JSON) { "use strict";
    /**
     * ajax 기본 셋팅
     */
    $.ajaxSetup({
        type        : 'POST',
        async       : true,
        cache       : false,
        contentType : 'application/json; charset=utf-8',
        dataType    : 'json',
        error       : function(xhr,e) {
            if (xhr.status == 0) {
                alert('You are offline!!\n Please Check Your Network.');
            }
            else if (xhr.status == 404) {
                alert('Requested URL not found.');
            }
            else if (xhr.status == 500) {
                alert('Internel Server Error.\n');
            }
            else if (e.toLowerCase() == 'parsererror') {
                alert('Error.\nParsing JSON Request failed.');
            }
            else if (e.toLowerCase() == 'timeout') {
                alert('Request Time out.');
            }
            else {
                alert('Unknow Error.\n'+xhr.responseText);
            }
        },
        beforeSend : function(xhr, setting) {
            if ( setting && setting.async == true ) {
            }
        },
        complete : function(xhr, e) {
        }
    });//$.ajaxSetup({

    $.ajaxPrefilter("json", function (options, originalOptions, jqXHR) {
        if (options.type.toUpperCase() == "POST" && originalOptions["data"] && ($.isPlainObject(originalOptions.data) || $.isArray(originalOptions.data)) ) {
            options.data = JSON.stringify(originalOptions.data);
        }
    });

    $.fn.serializeObject = function() {
        var o = {},
            a = this.serializeArray();

        $.each(a, function() {
            if ( o[this.name] ) {
                if ( !o[this.name].push ) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };

    /**
     * 토글 이벤트 구현
     * @param a 첫번째 클릭 이벤트
     * @param b 두번째 클릭 이벤트
     * @returns {*}
     */
    $.fn.clickToggle = function(a, b) {
        return this.each(function() {
            var clicked = false;
            $(this).on("click", function() {
                if (clicked) {
                    clicked = false;
                    return b.apply(this, arguments);
                } else {
                    clicked = true;
                    return a.apply(this, arguments);
                }
            });
        });
    };

    $.createGUID = function() {
        var S4 = function() {
            return ((Math.random()+1)*10000|0).toString(16).substring(1);
        };
        return "" + (new Date()).getTime() + "-" + (S4()+S4()+"-"+S4()+"-"+S4()+"-"+S4()+"-"+S4()+S4()+S4()).toUpperCase();
//        var guid = null;
//        $.ajax({
//            async       : false,
//            url         : "/common/createGUID",
//            success     : function(result) {
//                if ( result.status == true ) {
//                    guid = result.guid || null;
//                }
//            }
//        });
//        return guid;
    };
});
