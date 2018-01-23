/**
 * user strict 명령은 엄격하게 JavaScript 룰을 적용하라는 의미이다.
 * 일부 브라우저의 경우 use strict 명령을 통해 보다 빠르게 동작하는 경우도 존재하는 것 같다.
 * 잘못된 부분에 대한 검증도 보다 엄격하게 동작한다.
 * 하지만, 일부 라이브러리의 경우 use strict 명령을 사용하면 동작하지 않는 경우도 있으므로 주의해야 한다.
 */
;"use strict"
require.config({
    baseUrl: "/static/js",
    paths: {
        // local paths
        "backbone.local"            : "backbone/backbone.local",
        "bootstrap.local"           : "bootstrap/bootstrap.local",
        "dynatree.local"            : "dynatree/jquery.dynatree.local",
        "extend"                    : "extend/extendjs",
        "jqgrid.local"              : "jqgrid/jquery.jqgrid.local",
        "jquery.local"              : "jquery/jquery.local",
        "underscore.local"          : "underscore/underscore.local",
        "local"                     : "local",

        // lib paths
        "angular"                   : "../lib/angular/1.2.6/angular",
        "angular-resource"          : "../lib/angular/1.2.6/angular-resource",
        "angular-locale"            : "../lib/angular/1.2.6/i18n/angular-locale_ko",
        "backbone"                  : "../lib/backbone/1.1.0/backbone",
        "bootstrap"                 : "../lib/bootstrap/3.0.3/js/bootstrap",
        "ckeditor"                  : "../lib/ckeditor/4.3/ckeditor",
        "domReady"                  : "../lib/domReady/2.0.1/domReady",
        "dynatree"                  : "../lib/dynatree/1.2.5/src/jquery.dynatree",
        "highlight"                 : "../lib/highlight/7.5/highlight.pack",
        "holder"                    : "../lib/holder/2.2/holder",
        "html5shiv"                 : "../lib/html5shiv/3.7.0/html5shiv.src",
        "jqgrid"                    : "../lib/jqgrid/4.5.4/src/jquery.jqGrid",
        "jqgrid-locale"             : "../lib/jqgrid/4.5.4/src/i18n/grid.locale-kr",
        "jquery"                    : "../lib/jquery/1.10.2/jquery-1.10.2",
        "jquery-form-validator"     : "../lib/jquery-form-validator/2.1.27/form-validator/jquery.form-validator",
        "jquery-ui"                 : "../lib/jquery-ui/1.10.3/ui/jquery-ui",
        "jquery-ui-datepicker"      : "../lib/jquery-ui/1.10.3/ui/jquery.ui.datepicker",
        "jquery-ui-datepicker-lang" : "../lib/jquery-ui/1.10.3/ui/i18n/jquery.ui.datepicker-ko",
        "json"                      : "../lib/json/2013.05.26/json2",
        "respond"                   : "../lib/respond/1.4.0/respond.src",
        "underscore"                : "../lib/underscore/1.5.2/underscore"
    },
    shim: {
        "angular": {
            deps: ["angular-locale"],
            exports: "angular"
        },
        "backbone": {
            deps: ["underscore", "jquery"],
            exports: "Backbone",
            init: function() {
                return this.Backbone.noConflict();
            }
        },
        "bootstrap": {
            deps: ["jquery"]
        },
        "ckeditor": {
            deps: ["jquery"],
            exports: "CKEDITOR"
        },
        "dynatree": {
            deps: ["jquery", "jquery-ui"]
        },
        "highlight": {
            exports: "hljs"
        },
        "holder": {
            exports: "HOLDER"
        },
        "jqgrid": {
            deps: ["jquery", "jquery-ui", "jqgrid-locale"]
        },
        "jqgrid-locale": {
            deps: ["jquery"]
        },
        "jquery": {
            deps: ["json"],
            exports: "jQuery",
            init: function() {
                return this.jQuery.noConflict(true);
            }
        },
        "jquery-form-validator": {
            deps: ["jquery"]
        },
        "jquery-ui": {
            deps: ["jquery"]
        },
        "jquery-ui-datepicker": {
            deps: ["jquery-ui"]
        },
        "jquery-ui-datepicker-lang": {
            deps: ["jquery-ui-datepicker"]
        },
        "json": {
            exports : "JSON"
        },
        "underscore": {
            exports: "_",
            init: function() {
                return this._.noConflict();
            }
        }
    },
    waitSeconds: 15
});


