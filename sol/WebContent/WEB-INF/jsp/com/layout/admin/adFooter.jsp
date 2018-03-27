<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="tt.com.bean.VoCoMenu"%>
<%@page import="tt.com.constant.CsCoConstDef"%>
<%@page import="tt.base.TtSession"%>
<%@page import="tt.base.TtHttpSessionManager"%>
<%
  TtSession tts = (TtSession) TtHttpSessionManager.getTtSession(request);
%>
<script type="text/javaScript" defer="defer">

  $(document).ready(function(){

  });


</script>
<!-- footer -->
<div style="border: 1px; border-color: #919191"></div>
<address>
 (150-972) 서울시 영등포구 문래 3가 55-20 에이스하이테크시티 1-1동 411호 TEL : 070 - 4610 - 7000 / FAX : 02 - 3439 - 1260 <br />
 Copyright (c) 2008 SOLUPIA All rights reserved.

</address>
<!-- //footer -->


<%
    try{
    %>
   <!--  <div style="border:1px solid #00F;background-color:#CCF;color:#00F;padding:8px">
       <h1 style="font-family:Verdana">Debug Information</h1>
        <table style="border-collapse:collapse;width:100%">
        <caption style="font-family:Verdana;font-size:200%;margin:4px;text-align:left">ses</caption> -->
        <%
       /* Iterator itr = tts.keySet().iterator();
        while(itr.hasNext()){
               String s = (String)itr.next();
               String v = tts.get(s).toString();
               if(v.matches("\\[.+\\]")){
                   v = "[Collection]:<br>" + v.substring(1, v.length() - 1).replaceAll(", +", ",<br>");
               }else if(v.matches("\\{.+\\}")){
                   v = "[Map]:<br>" + v.substring(1, v.length() - 1).replaceAll(", +", ",<br>");
               }
               out.println(
                   "<tr><th style='background-color:#66F;font-weight:noraml;border:1px solid #00F'>" + s +
                   "</th><td style='background-color:#CCC;border:1px solid #00F;padding:2px 4px'>" + v + "</td></tr>"
                   );
        }  *///풀기
        %>
       <%--  </table>

        <table style="border-collapse:collapse;width:100%">
        <caption style="font-family:Verdana;font-size:200%;margin:4px;text-align:left">Validation</caption>
        <tr>
        <th style='background-color:#66F;font-weight:noraml;border:1px solid #00F'>$｛errMsg｝</th>
        <td style='background-color:#CCC;border:1px solid #00F;padding:2px 4px'>${errMsg}</td>
        </tr>
        <tr>
        <th style='background-color:#66F;font-weight:noraml;border:1px solid #00F'>$｛data｝</th>
        <td style='background-color:#CCC;border:1px solid #00F;padding:2px 4px'>${data}</td>
        </tr>
        </table>

        <table style="border-collapse:collapse;width:100%">
        <caption style="font-family:Verdana;font-size:200%;margin:4px;text-align:left">Etc.</caption>
        <tr>
        <th style='background-color:#66F;font-weight:noraml;border:1px solid #00F'>sslAccessCheck</th>
        <th style='background-color:#66F;font-weight:noraml;border:1px solid #00F'>TMP_ORD_NO</th>
        </tr>
        </table>

    </div> --%>
    <%
    }catch(Exception e){
        //何もしない
    }
%>