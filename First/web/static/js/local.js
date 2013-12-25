/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 10. 7
 * Time: 오전 10:36
 * To change this template use File | Settings | File Templates.
 */
;define([
    "require",
    "underscore",
    "underscore.local",
    "jquery",
    "jquery.local",
    "bootstrap",
    "bootstrap.local",
    "jqgrid",
    "jqgrid.local",
    "dynatree",
    "dynatree.local",
    "ckeditor",
    "domReady",
    "jquery-form-validator",
    "extend"
],
function(require) { "use strict";
    var _ = require("underscore"),
        $ = require("jquery"),
        _thisObj = _thisObj || {};
    /**
     * 버전
     */
    _thisObj.version = _thisObj.ver = "1.0.0";

    /**
     * 네임스페이스를 생성하는 메소드
     * 예)
     * 		1. var util = LOCAL.ns('LOCAL.util');
     * 		2. var util = LOCAL.ns('util');
     * LOCAL 네임스페이스는 생략해도 된다.
     * @param ns_string
     */
    _thisObj.ns = function(ns_string) {
        var parts = ns_string.split("."),
            parent = _thisObj,
            idx;

        // 처음에 중복되는 전역 객체명은 제거한다.
        if ( parts[0] === "LOCAL" ) {
            parts = parts.slice(1);
        }

        for (idx = 0; idx < parts.length; idx++) {
            // 프로퍼티가 존재하지 않는다면 생성한다.
            if ( typeof parent[parts[idx]] === "undefined" ) {
                parent[parts[idx]] = {};
            }

            // 부모에서 나를 찾아서 나를 부모로 만든다.
            // 나를 부모로 만든 이유는 자식들을 검사하기 위해서
            parent = parent[parts[idx]];
        }

        return parent;
    };

    /**
     * 경로에 contextPath를 붙여서 출력해준다.
     * @param val
     */
    _thisObj.url = function(val) {
        var contextPath = _thisObj.contextPath.get();

        // 현재 경로에 contextPath가 존재하면 붙이지 않는다.
        if ( val.search(contextPath) != 0 && val.search(/^http:\/\//i) != 0 ) {
            val = contextPath + val;
        }
        return val;
    };


    /**
     * url은 문자열 배열이나 문자열이 올수 있다.
     * 예) "LOCAL.df.DF_01.DF_HEADER_SEL"
     *     ["LOCAL.df.DF_01.DF_HEADER_SEL", "LOCAL.df.DF_01.DF_HEADER"]
     */
    _thisObj.importScript = function(url) {
        var urls = [];
        if ( url == undefined || url == null || url == "" ) {
            return;
        }

        if ( $.type(url) == "string" ) {
            urls.push(url);
        } else if ( $.type(url) == "array" ) {
            urls = url;
        } else {
            return;
        }

        $.each(urls, function(index, el) {
            if ( el !== undefined && el !== null && el !== "" ) {
                var newUrl = el.replace(/(^LETSKO\.)/gi, ""); // LETSKO가 있다면 제거한다.
                newUrl = newUrl.replace(/\./g, "/") + ".js";

                var fullUrl = _thisObj.url("/static/js/" + newUrl);

                var $script = $("<script type=\"text/javascript\" charset=\"utf-8\" src=\"" + fullUrl + "\"></script>");
                $script.bind("readystatechange", function() {
                    var $elem = $(this);
                    if ((!this.readyState || this.readyState == 'loaded' || this.readyState == 'complete')) {
                        // Handle memory leak in IE
                        this.onload = this.onreadystatechange = null;
                        $elem.remove();
                    }
                });

                $script.appendTo("body");
            }//if ( el !== undefined && el !== null && el !== "" ) {
        });//$.each(urls, function(index, el) {
    };

    /**
     * 패키지 문자열을 받아서 객체를 생성한다.
     * LOCAL.ns를 사용할수도 있지만 LOCAL.ns는 존재하지 않으면 자동으로 생성되기때문에 오류가 발생할수 있다.
     */
    _thisObj.createObject = function(str) {
        try {
            var parts = str.split(".");
            // 루트에 LOCAL 를 붙이지 않았다면 붙여준다.
            if ( parts[0] && String(parts[0]).toUpperCase() != "LOCAL" ) {
                str = "LOCAL." + str;
            }

            return eval("(" + str + ")");
        }
        catch (e) {
            alert(e.message);
            return undefined;
        }
    };

    /**
     * contextPath를 나타내는 객체
     * get, set 함수
     */
    _thisObj.contextPath = (function() {
        var _contextPath = "";
        return {
            get: function() {
                return _contextPath;
            },
            set: function(contextPath) {
                _contextPath = contextPath;
            }
        };
    })();

    /**
     * 현재 개발/운영 상태 클래스
     * mode : debug(개발), release(운영)
     * get, set 함수
     */
    _thisObj.mode = (function() {
        var _mode = "";
        return {
            get: function() {
                return _mode;
            },
            set: function(mode) {
                _mode = mode;
            },
            isDebug: function() {
                return (_mode != "release" ? true : false);
            }
        };
    })();

    /**
     * html title을 설정하는 클래스
     */
    _thisObj.title = (function() {
        var _title = "";
        return {
            get: function() {
                return _title;
            },
            set: function(title) {
                _title = title;
                if ( document.title != undefined ) {
                    var title = "";
                    if ( _thisObj.mode.isDebug() ) {
                        title = "(개발) ";
                    }
                    document.title = title + _title;
                }
            }
        };//return {
    })();

    _thisObj.session = (function() {
        var _id = 0,
            _username = "";

        return {
            setId: function(id) {
                _id = id;
            },
            getId: function() {
                return _id;
            },
            setUsername: function(username) {
                _username = username;
            },
            getUsername: function() {
                return _username;
            }
        };
    })();

    return _thisObj;
});
