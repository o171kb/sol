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
      <input type="hidden" id="userId" name="userId">
      <input type="hidden" id="delArr" name="delArr"/>
      <input type="hidden" id="dept_cd" name="dept_cd" />
      <input type="hidden" id="checkMessage" name="checkMessage" value="${searchVoCoUser.checkMessage}"/>

        <table border=1 style="width:800px;">
          <tr>
           <th>No</th>
           <th style=" width:15%;">신청일</th>
           <th style="width:15%;">구분</th>
           <th style="width:10%;">사번</th>
           <th style="width:20%;">성명</th>
           <th style="width:*%;">부서</th>
           <th style="width:10%;">결재상태</th>
          </tr>
          <c:choose>
           <c:when test="${fn:length(list)>0}">
            <c:forEach var="list" items="${list}" varStatus="status">
             <tr>
              <td><c:out value="${(voApprInfo.pageIndex-1)*voApprInfo.pageUnit + status.count}"/></td>
              <td><c:out value="${list.apprDm}"/></td>
              <td><c:out value="${list.exctAppNm}"/></td>
              <td><c:out value="${list.appId}"/></td>
              <td><c:out value="${list.approbjUser}"/></td>
              <td><c:out value="${list.deptNm}"/></td>
              <td><c:out value="${list.cdDtlNm}"/></td>
             </tr>
            </c:forEach>
           </c:when>
           <c:otherwise>
            <tr><td colspan="7">데이터가 없습니다.</td></tr>
           </c:otherwise>
          </c:choose>
        </table>
  </body>
</html>