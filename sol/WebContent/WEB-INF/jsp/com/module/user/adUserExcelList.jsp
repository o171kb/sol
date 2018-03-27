<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.net.URLEncoder"%>
<%
  String fileName = "Solupia_사용자 리스트";
  request.setCharacterEncoding("UTF-8");
  response.setContentType("application/vnd.ms-excel; charset=UTF-8;" );
  String header = request.getHeader("User-Agent");
  String dfn="";
   if (header.indexOf("MSIE") > -1) {
   dfn = URLEncoder.encode(fileName, "UTF-8");
      }else{
      dfn = new String(fileName.getBytes("UTF-8"),"8859_1");
      }

  response.setHeader("Content-Disposition", "attachment; filename=" + dfn+ ".xls");
  response.setHeader("Content-Description", "JSP Generated Data");
%>
<script type="text/javaScript" defer="defer">

  $(document).ready(function(){


  });

</script>

<!-- HEADER START -->
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
      <style type="text/css">
        .tdcenter {text-align:center; background: #f3f3f3;}
        .tdcolor {background:#000000; text-align:center;}
        @page {margin: 1in .2in .1in .25in; mso-header-margin:5in; mso-footer-margin:5in}
      </style>
  </head>
  <body>
<!-- HEADER AND -->
    <!-- CONTENT START -->
      <input type="hidden" id="apprId" name="apprId" />
      <input type="hidden" id="exctAppId" name="exctAppId" value="${voApprInfo.exctAppId}"/>
      <input type="hidden" id="exctAppNm" name="exctAppNm"/>

        <table border=1 style="width:800px;">
          <tr>
            <th align="center" style="width:10%; background-color:#eeeeee;">No</th>
            <th align="center" style="width:25%; background-color:#eeeeee;">사번</th>
            <th align="center" style="width:25%; background-color:#eeeeee;">성명</th>
            <th align="center" style="width:40%; background-color:#eeeeee;">사용자그룹</th>
            <th align="center" style="width:*; background-color:#eeeeee;">e-mail</th>
            <th align="center" style="width:40%; background-color:#eeeeee;">전화번호</th>
            <th align="center" style="width:40%; background-color:#eeeeee;">휴대폰</th>
          </tr>

          <c:choose>
            <c:when test="${fn:length(rsUserList)>0 }">
              <c:forEach var="user_info" items="${rsUserList}" varStatus="status">
                <tr>
                  <td align="center">${status.count}</td>
                  <td align="center">${user_info.userId}</td>
                  <td align="center">${user_info.userNm}</td>
                  <td align="center">${user_info.grpNm}</td>
                  <td align="center">${user_info.userEmail}</td>
                  <td align="center">${user_info.userTel}</td>
                  <td align="center">${user_info.userMobile}</td>
                </tr>
              </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                  <td colspan="6">검색결과가 없습니다.</td>
                </tr>
            </c:otherwise>
          </c:choose>
        </table>
</body>
</html>