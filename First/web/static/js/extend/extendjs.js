/**
 *  기본 자바스크립트의 기능을 확장시킨다.
 */
;define(function() {
    "use strict";

    /**
     * 전달받은 인자를 부모로 하는 새로운 객체를 생성한다.
     */
    if ( typeof Object.create !== 'function' ) {
        Object.create = function(o) {
            var F = function() {};
            if ( !arguments.length ) {
                F.prototype = o;
            }
            return new F();
        };
    }

    /**
     * 객체에 메소드를 추가하는 메소드
     * @param name
     * @param code
     */
    Function.prototype.method = function(name, code) {
        if ( !this.prototype[name] ) {
            this.prototype[name] = code;
        }
    };

    /**
     * 함수설명 : " abcde "  -> "abcde" 공백제거
     * 예제     : str.trim();
     */
    String.method("trim", function(){
        return this.replace(/(^\s*)|(\s*$)/g, "");
    });

    /**
     * 함수설명 : " abcd" -> "abcd"
     * 왼쪽 공백 제거
     */
    String.method("leftTrim", function() {
        return this.replace(/(^\s*)/g, "");
    });

    /**
     * 함수설명 : "abcd " -> "abcd"
     * 오른쪽 공백 제거
     */
    String.method("rightTrim", function() {
        return this.replace(/(\s*$)/g, "");
    });

    /**
     *함수설명 : cut 메소드를 추가
     * start   : 잘라낼 시작 위치   (int)
     * length  : 잘라낼 문자열 길이 (int)
     * 예제    : "abcdef"
     *           str.cut(2, 2);
     *           "abef"
     */
    String.method("cut", function(start, length){
        return this.substring(0, start) + this.substr(start + length);
    });

    /**
     * 문자열을 숫자로 바꿔준다.
     * 숫자로 변환할수 없는 문자는 0을 리턴한다.
     */
    String.method("toNumber", function() {
        var num = Number(this.replace(/[,\s]/g, ""));
        return (isNaN(num) ? 0 : num);
    });

    /**
     * 문자열을 repeatCount 만큼 반복한다.
     */
    String.method("repeat", function(repeatCount) {
        var s  = '',
            idx = 0;
        while (idx++ < repeatCount) {
            s += this;
        }

        return s;
    });

    /**
     * 문자열의 길이가 len보자 적다면 왼쪽을 0으로 채운다.
     */
    String.method("zf", function(len) {
        return "0".repeat(len - this.length) + this;
    });

    /**
     * 숫자를 문자열로 변환한후 문자열의 길이가 len보다 적다면 왼쪽을 0으로 채운다.
     */
    Number.method("zf", function(len) {
        return this.toString().zf(len);
    });

    /**
     * 숫자 부분에서 정수만 추출 하는 메소드
     */
    Number.method("integer", function() {
        return Math[this < 0 ? "ceiling" : "floor"](this);
    });

    /**
     * 날짜를 포맷에 맞게 리턴한다.
     * yyyy : 4자리 년도
     * yy   : 2자리 년도
     * MM   : 2자리 월
     * dd   : 2자리 일
     * E    : 한글요일
     * HH   : 24시간
     * hh   : 12시간
     * mm   : 분
     * ss   : 초
     * a/p  : 오전/오후
     */
    Date.method("format", function(f) {
        if ( !this.valueOf() ) return " ";

        var weekName = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"],
            d = this,
            h = null;

        return f.replace(/(yyyy|yy|MM|dd|E|hh|mm|ss|a\/p)/gi, function($1) {
            switch ($1) {
                case "yyyy": return d.getFullYear();
                case "yy"  : return (d.getFullYear() % 1000).zf(2);
                case "MM"  : return (d.getMonth() + 1).zf(2);
                case "dd"  : return d.getDate().zf(2);
                case "E"   : return weekName[d.getDay()];
                case "HH"  : return d.getHours().zf(2);
                case "hh"  : return ((h = d.getHours() % 12) ? h : 12).zf(2);
                case "mm"  : return d.getMinutes().zf(2);
                case "ss"  : return d.getSeconds().zf(2);
                case "a/p" : return d.getHours() < 12 ? "오전" : "오후";
                default: return $1;
            }
        });
    });
});
