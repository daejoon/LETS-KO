/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 10. 17
 * Time: 오후 5:06
 * To change this template use File | Settings | File Templates.
 */
;define([
    "underscore"
],
function(_) { "use strict";
    _.templateSettings = {
        evaluate    : /<\?([\s\S]+?)\?>/g,
        interpolate : /<\?=([\s\S]+?)\?>/g,
        escape      : /<\?-([\s\S]+?)\?>/g
    };
});
