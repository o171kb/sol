<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@page import="org.springframework.security.userdetails.User"%>
<%@page import="org.springframework.security.context.SecurityContextHolder"%>
<%@page import="tt.com.module.code.SvCoCodeService"%>

<%

  //String url = request.getAttribute("javax.servlet.forward.request_uri").toString();
  //String authority = (String)session.getAttribute("AUTHORITY");
  //System.out.println("여기는 헤더 입니다. authority:" +authority);
  SvCoCodeService codeManager = SvCoCodeService.getInstance();
%>

<script type="text/javaScript" language="javascript" defer="defer">
	function showPage(action,id) {
    $("#id").val(id);
    $("#menuForm").attr("action","<c:url value='/" + action + "'/>");
    $("#menuForm").submit();
    return false;
  }	
</script>
<c:set var="context" value="${pageContext.request.contextPath}"/>
 <%-- <img style="margin: 10px 0 0 0;cursor:pointer;" src="<c:url value="/image/admin/ci.gif"/>" onClick="javascript:showPage('adHome.do','0');" />
 <a href="<c:url value='/logout'/>" class="mout" style="float:right;margin: 10px 10px 0 0;"><img src="<c:url value="/image/admin/logout.gif"/>"/></a> --%>
<img style="margin: 10px 0 0 20px; cursor:pointer;" src="<c:url value="/image/admin/logo.png"/>" onClick="javascript:showPage('adHome.do','0');" />
<style>
@font-face {    
 font-family: 'NanumGothic';    
 src: url('/css/font/NanumGothic.eot'); /* IE9 Compat Modes */    
 src: url('/css/font/NanumGothic.eot?#iefix') format('embedded-opentype'), /* IE6-IE8 */        
 url('/css/font/NanumGothic.woff') format('woff'), /* Modern Browsers */        
 url('/css/font/NanumGothic.ttf')  format('truetype'), /* Safari, Android, iOS */        
 }
body{ font-family:NanumGothic; }
</style>