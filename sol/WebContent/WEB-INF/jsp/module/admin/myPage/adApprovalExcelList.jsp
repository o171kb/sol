<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.net.URLEncoder"%>
<%
  String fileName = "Solupia_나의 결재 리스트";
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
    <form:form commandName="searchVoCoUser" id="userForm" name="userForm" method="post">
      <input type="hidden" id="userId" name="userId">
      <input type="hidden" id="delArr" name="delArr"/>
      <input type="hidden" id="dept_cd" name="dept_cd" />

        <table border=1 style="width:800px;">
          <tr>
            <th align="center" style="width:10%; background-color:#eeeeee;">No</th>
            <th align="center" style="width:25%; background-color:#eeeeee;">신청일</th>
            <th align="center" style="width:25%; background-color:#eeeeee;">구분</th>
            <th align="center" style="width:40%; background-color:#eeeeee;">기안자</th>
            <th align="center" style="width:*; background-color:#eeeeee;">성명</th>
            <th align="center" style="width:40%; background-color:#eeeeee;">사용기간</th>
            <th align="center" style="width:40%; background-color:#eeeeee;">결재상태</th>
          </tr>

          <c:choose>
            <c:when test="${fn:length(list)>0}">
               <c:forEach var="list" items="${list}" varStatus="status">
                <tr>
                  <td align="center">${status.count}</td>
                  <td align="center"><c:out value="${list.apprDm}"/></td>
                  <td align="center"><c:out value="${list.exctAppNm}"/></td>
                  <td align="center"><c:out value="${list.drafter}"/></td>
                  <td align="center"><c:out value="${list.approbjUser}"/></td>
                  <td align="center"><c:out value="${list.startDm}"/> ~ <c:out value="${list.endDm}"/></td>
                  <td align="center"><c:out value="${list.cdDtlNm}"/></td>
                </tr>
              </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                  <td colspan="7">검색결과가 없습니다.</td>
                </tr>
            </c:otherwise>
          </c:choose>
        </table>
    </form:form>
</body>
</html>