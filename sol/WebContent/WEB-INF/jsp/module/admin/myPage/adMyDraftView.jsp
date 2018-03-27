<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javaScript" src="/admin/sitemap/common/OnetimeSubmit.js"></script>
<script type="text/javaScript" defer="defer">
  $(document).ready(function() {
    //메뉴설정
    openMenu('05');

    $("#btn_list").click(function(){
      $("#infoForm").attr("action","<c:url value='/adMyDraftList.do'/>");
      $("#infoForm").submit();
      return false;
    });
  });
</script>

<h3>MyPage &gt; 나의신청현황 &gt; 상세 &gt; ESCORT예외정책</h3>

<!-- CONTENT START -->
<form:form commandName="voApprInfo" id="infoForm" name="infoForm" method="post" onsubmit="return onetimeSubmit(this)">
 <input type="hidden" id="apprId" name="apprId" value="${voApprInfo.apprId}" />
<%--  <input type="hidden" id="pageIndex" name="pageIndex" value="${voApprInfo.pageIndex}" /> --%>

 <!-- 상단문구 -->
 <div class="img_va">
  <img src="<c:url value="/image/admin/btn_right.gif"/>" />기안자정보
 </div>
 <table class="tbws1" style="margin: 0 0 30px 0;">
  <tr>
   <th rowspan="2" width="150px;">신청자</th>
   <th width="100px;">성명</th>
   <td width="275px;">${userDtl.userNm}</td>
   <th width="100px;">직급</th>
   <td><c:forEach var="medCode" items="${tt:getCodeValues('010')}">
		<c:if test="${userDtl.userPosition == medCode[0]}">${medCode[1]}</c:if>
	</c:forEach></td>
  </tr>
  <tr>
   <th>부서</th>
   <td>${userDtl.deptNm}</td>
   <th>전화번호</th>
   <td>${userDtl.userTel}</td>
  </tr>
 </table>
 <div class="img_va">
  <img src="<c:url value="/image/admin/btn_right.gif"/>" />결재자정보
 </div>
 <table class="tbws1" id="approvalUser" style="margin: 0px 0 10px 0;">
  <tr id="trHeader">
   <th style="width: 20px;">결재구분</th>
   <th>결재상태</th>
   <th>결재자</th>
   <th>직급</th>
   <th>부서</th>
  </tr>
  <c:forEach var="approverList" items="${approverList}">
   <tr>
    <td>
      <c:forEach var="code" items="${tt:getCodeValues('003') }">
        <c:if test="${approverList.permitGubun == code[0]}">${code[1]}</c:if>
      </c:forEach>
    </td>
    <td>
      <c:choose>
        <c:when test="${approverList.permitGubun == '03'}">
        통보
        </c:when>
        <c:otherwise>
        <c:forEach var="code" items="${tt:getCodeValues('004') }">
          <c:if test="${approverList.status == code[0]}">${code[1]}</c:if>
        </c:forEach>
        </c:otherwise>
      </c:choose>
    </td>
    <td>${approverList.userNm }</td>
    <td><c:forEach var="medCode" items="${tt:getCodeValues('010')}">
		<c:if test="${approverList.userPosition == medCode[0]}">${medCode[1]}</c:if>
	</c:forEach></td>
    <td>${approverList.deptNm }</td>
   </tr>
  </c:forEach>
 </table>
 <div class="img_va">
  <img src="<c:url value="/image/admin/btn_right.gif"/>" />예외대상PC
 </div>
 <table class="tbws1" id="approvalPc" style="margin: 0px 0 10px 0;">
  <tr>
   <th style="width: 10px;">번호</th>
   <th>사용자</th>
   <th>부서명</th>
   <th>연락처</th>
   <th>제조번호</th>
   <th>IP</th>
  </tr>
  <c:forEach var="approvalPc" items="${approvalPcList}" varStatus="status">
   <tr>
    <td><c:out value="${(voApprInfo.pageIndex-1)*voApprInfo.pageUnit + status.count}"/></td>
    <td>${approvalPc.userNm}</td>
    <td>${approvalPc.deptNm}</td>
    <td>${approvalPc.userMobile}</td>
    <td>${approvalPc.serial}</td>
    <td>${approvalPc.ipAddr}</td>
   </tr>
  </c:forEach>
 </table>

 <div class="img_va">
  <img src="<c:url value="/image/admin/btn_right.gif"/>" />신청정보
 </div>
 <table class="tbws1" style="margin: 0px 0 30px 0;">
  <%-- <tr>
   <th style="width: 40px;">구&nbsp;&nbsp;&nbsp;&nbsp;분</th>
   <td class="align_l" colspan="2">${voApprInfo.exctAppNm}</td>
  </tr>
  <tr>
   <th style="width: 40px;">용&nbsp;&nbsp;&nbsp;&nbsp;도</th>
    <td class="align_l" colspan="2">
     <c:forEach var="code" items="${tt:getCodeValues('002') }">
      <c:if test="${voApprInfo.purpose == code[0]}">${code[1]}</c:if>
     </c:forEach>
    </td>
  </tr> --%>
  <tr>
   <th>신청기간</th>
   <td class="align_l" colspan="2">${voApprInfo.startDm} &nbsp;&nbsp;~&nbsp;&nbsp; ${voApprInfo.endDm}</td>
  </tr>
  <tr>
   <th rowspan="${fn:length(apprDetailList)+1}">신청항목</th>
   <th>항목명</th>
   <th>상세설명</th>
  </tr>
  <c:forEach var="exctList" items="${apprDetailList}" varStatus="status">
   <tr>
    <td>${exctList.exctNm}</td>
    <td>${exctList.exctDtl}</td>
   </tr>
  </c:forEach>
  <tr>
   <th>신청사유</th>
   <td class="align_l" colspan="2">${voApprInfo.reason}</td>
  </tr>
  <tr>
   <th>증빙서류</th>
   <td class="align_l" colspan="2">
     <c:choose>
      <c:when test="${!empty voApprInfo.addProofNm}">
       <a href="<c:url value='appr/download.do?fid=${voApprInfo.apprId}&pid=fileNm'/>" style="cursor: pointer;">${voApprInfo.addProofNm}</a>
      </c:when>
      <c:otherwise>첨부파일없음</c:otherwise>
     </c:choose>
   </td>
  </tr>
 </table>
 <div class="right">
  <input type="button" class="nwhite" id="btn_list" value="목록">
 </div>
</form:form>
<!-- //CONTENT START -->