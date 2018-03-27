<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.springframework.security.userdetails.User"%>
<%@page import="org.springframework.security.context.SecurityContextHolder"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%
  String url = request.getAttribute("javax.servlet.forward.request_uri").toString();
  String authority = (String)session.getAttribute("AUTHORITY");
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Solupia</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />

<link rel="stylesheet" type="text/css" href="${context}/css/layout.css" />
<script type="text/javascript" src="${context}/module/jquery/jquery.js"></script>
<script type="text/javascript" src="${context}/module/jquery/ui/jquery-ui-1.8.14.custom.min.js"></script>


</head>
<body>

  <script type="text/javascript">
     $(document).ready(function(){
       $(".btn_back").click(function(){

         location.href = "<c:url value='/adHome.do'/>";
         return false;
       });
     });
   </script>
  <div id="wrap">
    <!-- data -->
    <div id="data">
      <div class="back">
        <!-- error layout -->
        <div class="error">
          <div>
            <img src="${context}/image/front/error_txt1.png" alt="죄송합니다. 요청하신 페이지를 찾을 수 없습니다." /><br /> <img
              src="${context}/image/front/error_txt2.png"
              alt="방문하시려는 페이지의 주소가 잘못 입력되었거나, 페이지의 주소가 변경 혹은 삭제되어 요청하신 페이지를 찾을 수 없습니다. 입력하신 주소가 정확한지 다시 한번 확인해 주시기 바랍니다. 감사합니다." /><br />
            <a class="btn_back">이전화면으로 이동</a>
          </div>
        </div>
        <!-- //error -->

      </div>
    </div>
    <!-- //data -->
  </div>
</body>
</html>

