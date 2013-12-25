;define([
    "jquery",
    "jquery.local",
    "bootstrap"
],
function($) { "use strict";
    $.Confirm = function confirm(title, question, cancelBtnText, okBtnText, callback) {
        var opt = {};

        if ( $.isPlainObject(title) == false ) {
            opt = {
                title         : title                   || '',
                question      : question                || '',
                cancelBtnText : cancelBtnText           || 'Cancel',
                okBtnText     : okBtnText               || 'Ok',
                callback      : callback                || null
            };
        } else {
            opt = {
                title         : title.title             || '',
                question      : title.question          || '',
                cancelBtnText : title.cancelBtnText     || 'Cancel',
                okBtnText     : title.okBtnText         || 'Ok',
                callback      : title.callback          || null
            };
        }

        var confirmModal =
            $('<div class="modal fade" id="localConfirmModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">        '
                +'    <div class="modal-dialog">                                                                                                       '
                +'        <div class="modal-content">                                                                                                  '
                +'            <div class="modal-header">                                                                                               '
                +'                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>                         '
                +'                <h4 class="modal-title">' + opt.title + '</h4>                                                                       '
                +'            </div>                                                                                                                   '
                +'            <div class="modal-body">                                                                                                 '
                +'                <p>' + opt.question + '</p>                                                                                          '
                +'            </div>                                                                                                                   '
                +'            <div class="modal-footer">                                                                                               '
                +'                <button type="button" class="btn btn-default" data-dismiss="modal">' + opt.cancelBtnText + '</button>                '
                +'                <button type="button" class="btn btn-primary" id="localConfirmOK">' + opt.okBtnText + '</button>                     '
                +'            </div>                                                                                                                   '
                +'        </div>                                                                                                                       '
                +'    </div>                                                                                                                           '
                +'</div>                                                                                                                               ');

        confirmModal.find('#localConfirmOK').click(function(event) {
            if ( $.isFunction(opt.callback) ) {
                opt.callback();
            }
            confirmModal.modal('hide');
        });

        confirmModal.modal('show');
    };
});