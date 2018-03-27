<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  //현재 시간(초로환산)
  int _time = (int)(System.currentTimeMillis());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <%String browser = request.getHeader("User-Agent");%>
  <%if(browser.indexOf("MSIE 7.0") > -1){ %>
    <meta http-equiv="X-UA-Compatible" content="IE=7"/>
  <%}else{ %>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge"/>
  <%} %>

  <title>솔루피아</title>
<%-- 	<link type="text/css" rel="stylesheet" href="<c:url value='/module/jquery/ui/css/jquery-impromptu.css'/>" /> --%>
  <!-- front 공통 layer popup -->
<%-- 	<link type="text/css" href="<c:url value='/module/jquery/ui/css/custom-theme/jquery-ui-1.8.14.custom.css'/>?d=<%=_time %>" rel="stylesheet" /> --%>
  <link type="text/css" href="<c:url value='/css/admin/common.css'/>?d=<%=_time %>" rel="stylesheet" />
  <link type="text/css" href="<c:url value='/css/admin/layout.css'/>?d=<%=_time %>" rel="stylesheet" />
  

  <script type="text/javascript" src="<c:url value='/module/jquery/jquery.js'/>?d=<%=_time %>"></script>
  <script type="text/javascript" src="<c:url value='/module/jquery/plugin/jquery.printElement.js'/>?d=<%=_time %>"></script>
  <script type="text/javascript" src="<c:url value='/module/jquery/ui/jquery-ui-1.8.14.custom.min.js'/>?d=<%=_time %>"></script>
  <script type="text/javascript" src="<c:url value='/module/jquery/plugin/jquery.xmldom.min.js'/>?d=<%=_time %>"></script>
<%-- 	<script type="text/javascript" src="<c:url value='/module/jquery/plugin/jquery.paulund_modal_box.js'/>?d=<%=_time %>"></script> --%>
  <script type="text/javascript" src="<c:url value='/module/jquery/plugin/jquery-impromptu.js'/>?d=<%=_time %>"></script>
  <script type="text/javascript" src="<c:url value='/module/jquery/plugin/jquery.cookie.js'/>?d=<%=_time %>"></script>
  <script type="text/javascript" src="<c:url value='/module/jquery/plugin/jquery.base64.min.js'/>?d=<%=_time %>"></script>

  <script type="text/javascript" src="<c:url value='/js/imgover.js'/>?d=<%=_time %>"></script>
<!-- 	<script type="text/javascript" src="/js/flash.js"></script> -->
</head>
<body onresize="parent.resizeTo(1024,687)" onload="parent.resizeTo(1024,687)">
  <tiles:insertAttribute name="body"/>
</body>
</html>