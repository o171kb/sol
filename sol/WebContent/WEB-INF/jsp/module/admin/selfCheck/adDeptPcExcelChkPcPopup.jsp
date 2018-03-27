<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.net.URLEncoder"%>
<%
String fileName = "점검PC";
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
<html>
  <head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  </head>
  <body>
<div id="container">
  <div id="container_detail">
  <table>
    <tr>
      <td>대상자</td>
    </tr>
  </table>
  <table border=1>
    <tr>
      <th>No.</th>
      <th>부서명</th>
      <th>성명</th>
      <th>직급/직책</th>
      <th>사번</th>
      <th>최종점검일</th>
      <th>점검결과</th>
      <th>점검PC수</th>
    </tr>
      <c:choose>
        <c:when test="${fn:length(registerIdList)>0 }">
          <c:forEach var="reg" items="${registerIdList}" varStatus="status">
            <tr>
              <td>${reg.userId}</td>
              <td>${reg.userNm}</td>
              <td>${reg.userPosition}</td>
              <td>${reg.userPosition}</td>
              <td>${reg.userPosition}</td>
              <td>${reg.userPosition}</td>
              <td>${reg.userPosition}</td>
              <td>${reg.userPosition}</td>
            </tr>
          </c:forEach>
        </c:when>
        <c:otherwise>
            <tr>
              <td colspan="8">대상자가 없습니다.</td>
            </tr>
        </c:otherwise>
      </c:choose>
  </table>
  </div>
</div>
</body>
</html>