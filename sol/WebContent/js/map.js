function init(){
    tableDesign();
    var apDiv1 = document.getElementById("apDiv1");
    var apDiv2 = document.getElementById("apDiv2");
    var apDiv3 = document.getElementById("apDiv3");
    var apDiv4 = document.getElementById("apDiv4");
    var apDiv5 = document.getElementById("apDiv5");
    var apDiv6 = document.getElementById("apDiv6");
    var apDiv7 = document.getElementById("apDiv7");
    var apDiv8 = document.getElementById("apDiv8");
    var apDiv9 = document.getElementById("apDiv9");
    var apDiv10 = document.getElementById("apDiv10");
    var apDiv11 = document.getElementById("apDiv11");
    var apDiv12 = document.getElementById("apDiv12");
    var apDiv13 = document.getElementById("apDiv13");
    var apDiv14 = document.getElementById("apDiv14");
    var apDiv15 = document.getElementById("apDiv15");
    var gw = document.getElementById("gw");
    var us = document.getElementById("us");
    var su = document.getElementById("su");
    var jp = document.getElementById("jp");
    var jn = document.getElementById("jn");
    var jj = document.getElementById("jj");
    var ic = document.getElementById("ic");
    var gp = document.getElementById("gp");
    var gn = document.getElementById("gn");
    var gj = document.getElementById("gj");
    var gg = document.getElementById("gg");
    var dj = document.getElementById("dj");
    var cp = document.getElementById("cp");
    var cn = document.getElementById("cn");
    var bs = document.getElementById("bs");
    /*강원*/
    gw.getElementsByTagName("img")[0].onmouseover = function(){
      apDiv1.setAttribute("style","display:block;");
    };
    gw.getElementsByTagName("img")[0].onmouseout = function(){
      apDiv1.setAttribute("style","display:none;");
    };
    /*울산*/
    us.getElementsByTagName("img")[0].onmouseover = function(){
      apDiv2.setAttribute("style","display:block;");
    };
    us.getElementsByTagName("img")[0].onmouseout = function(){
      apDiv2.setAttribute("style","display:none;");
    };
    /*서울*/
    su.getElementsByTagName("img")[0].onmouseover = function(){
      apDiv3.setAttribute("style","display:block;");
    };
    su.getElementsByTagName("img")[0].onmouseout = function(){
      apDiv3.setAttribute("style","display:none;");
    };
    /*전북*/
    jp.getElementsByTagName("img")[0].onmouseover = function(){
      apDiv4.setAttribute("style","display:block;");
    };
    jp.getElementsByTagName("img")[0].onmouseout = function(){
      apDiv4.setAttribute("style","display:none;");
    };
    /*전남*/
    jn.getElementsByTagName("img")[0].onmouseover = function(){
      apDiv5.setAttribute("style","display:block;");
    };
    jn.getElementsByTagName("img")[0].onmouseout = function(){
      apDiv5.setAttribute("style","display:none;");
    };
    /*제주*/
    jj.getElementsByTagName("img")[0].onmouseover = function(){
      apDiv6.setAttribute("style","display:block;");
    };
    jj.getElementsByTagName("img")[0].onmouseout = function(){
      apDiv6.setAttribute("style","display:none;");
    };
    /*인천*/
    ic.getElementsByTagName("img")[0].onmouseover = function(){
      apDiv7.setAttribute("style","display:block;");
    };
    ic.getElementsByTagName("img")[0].onmouseout = function(){
      apDiv7.setAttribute("style","display:none;");
    };
    /*경북*/
    gp.getElementsByTagName("img")[0].onmouseover = function(){
      apDiv8.setAttribute("style","display:block;");
    };
    gp.getElementsByTagName("img")[0].onmouseout = function(){
      apDiv8.setAttribute("style","display:none;");
    };
    /*경남*/
    gn.getElementsByTagName("img")[0].onmouseover = function(){
      apDiv9.setAttribute("style","display:block;");
    };
    gn.getElementsByTagName("img")[0].onmouseout = function(){
      apDiv9.setAttribute("style","display:none;");
    };
    /*광주*/
    gj.getElementsByTagName("img")[0].onmouseover = function(){
      apDiv10.setAttribute("style","display:block;");
    };
    gj.getElementsByTagName("img")[0].onmouseout = function(){
      apDiv10.setAttribute("style","display:none;");
    };
    /*경기*/
    gg.getElementsByTagName("img")[0].onmouseover = function(){
      apDiv11.setAttribute("style","display:block;");
    };
    gg.getElementsByTagName("img")[0].onmouseout = function(){
      apDiv11.setAttribute("style","display:none;");
    };
    /*대전*/
    dj.getElementsByTagName("img")[0].onmouseover = function(){
      apDiv12.setAttribute("style","display:block;");
    };
    dj.getElementsByTagName("img")[0].onmouseout = function(){
      apDiv12.setAttribute("style","display:none;");
    };
    /*충북*/
    cp.getElementsByTagName("img")[0].onmouseover = function(){
      apDiv13.setAttribute("style","display:block;");
    };
    cp.getElementsByTagName("img")[0].onmouseout = function(){
      apDiv13.setAttribute("style","display:none;");
    };
    /*충남*/
    cn.getElementsByTagName("img")[0].onmouseover = function(){
      apDiv14.setAttribute("style","display:block;");
    };
    cn.getElementsByTagName("img")[0].onmouseout = function(){
      apDiv14.setAttribute("style","display:none;");
    };
    /*부산*/
    bs.getElementsByTagName("img")[0].onmouseover = function(){
      apDiv15.setAttribute("style","display:block;");
    };
    bs.getElementsByTagName("img")[0].onmouseout = function(){
      apDiv15.setAttribute("style","display:none;");
    };

}

window.onload = init;