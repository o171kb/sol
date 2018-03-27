<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javaScript" src="/admin/sitemap/common/OnetimeSubmit.js"></script>
<script type="text/javascript" src="<c:url value='/js/chkValidate.js'/>"></script>
<style type="text/css"> label.error{ display:block; color:red; } </style>
<script type="text/javaScript" defer="defer">

	//결재의견 디폴트값 입력 처리
	var clearChk = true;
	
	function clearMessage(frm){
		if(clearChk){ 
			frm.resultReason.value = "";
			clearChk = false;
		}
	}	
  $(document).ready(function() {
  //메뉴설정
    openMenu('05');

    var status = $("#status").val();
    if (status != 02){
      $("#apprDiv").css("display","none");
      $("#btn_appr").css("display","none");
      $("#btn_disAppr").css("display","none");
    }

    //액션완료 메시지 띄우기
    var checkMessage = $("#checkMessage").val();
    if(checkMessage == "success_appr"){
        $.prompt("신청이 완료 되었습니다.", {
          buttons: { "확인": true },
          submit: function(e,v,m,f){
            if (v) {
                $("#checkMessage").val("");
                location.href = "<c:url value='/adHome.do'/>";
            }
          }
        });
    }

    //결재반려 액션
    $("#btn_disAppr").click(function(){
      $.prompt("반려 하시겠습니까?", {
            buttons: { "예": true, "아니오": false },
            submit: function(e,v,m,f){
              if (v) {
                $("#checkMessage").val("success_appr");
                $("#infoForm").attr("action","<c:url value='/adMyEscortReject.do'/>");
                $("#infoForm").submit();
              }
            }
          });
      return false;
    });

    //결재승인 액션
    $("#btn_appr").click(function(){
      $.prompt("승인 하시겠습니까?", {
            buttons: { "예": true, "아니오": false },
            submit: function(e,v,m,f){
              if (v) {
                $("#checkMessage").val("success_appr");
                $("#infoForm").attr("action","<c:url value='/adMySiteAppr.do'/>");
                $("#infoForm").submit();
              }
            }
          });
      return false;
    });

    $("#btn_list").click(function(){
      $("#infoForm").attr("action","<c:url value='/adMyApprovalList.do'/>");
      $("#infoForm").submit();
      return false;
    });
  });
</script>

<h3>MyPage &gt; 나의결재함 &gt; 상세 &gt; 사이트예외정책</h3>

<!-- CONTENT START -->
<form:form commandName="voApprInfo" id="infoForm" name="infoForm" method="post" onsubmit="return onetimeSubmit(this)">
 <input type="hidden" id="apprId" name="apprId" value="${voApprInfo.apprId}" />
 <input type="hidden" id="pageIndex" name="pageIndex" value="${voApprInfo.pageIndex}" />
 <input type="hidden" id="status" name="status" value="${userDtl.status}" />
 <input type="hidden" id="checkMessage" name="checkMessage" value="${voApprInfo.checkMessage}"/>
 <input type="hidden" id="exctAppId" name="exctAppId" value="${voApprInfo.exctAppId}"/>
 <input type="hidden" id="turnNo" name="turnNo" value="${userDtl.turnNo}"/>

 <!-- 상단문구 -->
 <div class="img_va">
  <img src="<c:url value="/image/admin/btn_right.gif"/>" />기안자정보
 </div>
  <table class="tbws1" style="margin: 0 0 30px 0;">
  <tr>
   <th rowspan="2" width="150px;">신청자</th>
   <th width="100px;">성명</th>
   <td width="275px;">${userDtl2.userNm}</td>
   <th width="100px;">직급</th>
   <td><c:forEach var="medCode" items="${tt:getCodeValues('010')}">
		<c:if test="${userDtl2.userPosition == medCode[0]}">${medCode[1]}</c:if>
	</c:forEach></td>
  </tr>
  <tr>
   <th>부서</th>
   <td>${userDtl2.deptNm}</td>
   <th>전화번호</th>
   <td>${userDtl2.userTel}</td>
  </tr>
 </table>
 <div class="img_va">
  <img src="<c:url value="/image/admin/btn_right.gif"/>" />결재자정보
 </div>
 <table class="tbws1" id="approvalUser" style="margin: 0px 0 10px 0;">
  <tr id="trHeader">
   <th style="width: 20px;">결재구분</th>
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
    <td>${approverList.userNm }</td>
    <td><c:forEach var="medCode" items="${tt:getCodeValues('010')}">
		<c:if test="${approverList.userPosition == medCode[0]}">${medCode[1]}</c:if>
	</c:forEach></td>
    <td>${approverList.deptNm }</td>
   </tr>
  </c:forEach>
 </table>
 <%-- <div class="img_va">
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
 </table> --%>

 <%-- <div class="img_va">
  <img src="<c:url value="/image/admin/btn_right.gif"/>" />신청정보
 </div>
 <table class="tbws1" style="margin: 0px 0 30px 0;">
  <tr>
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
  </tr>
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
       <a href="<c:url value='board/download.do?fid=${voApprInfo.apprId}&pid=fileNm'/>" style="cursor: pointer;">${voApprInfo.addProofNm}</a>
      </c:when>
      <c:otherwise>첨부파일없음</c:otherwise>
     </c:choose>
   </td>
  </tr>
 </table> --%>

 <div class="img_va"><img src="<c:url value="/image/admin/btn_right.gif"/>" />신청정보</div>
  <table class="tbws1" style="margin:0px 0 30px 0;">
  <c:forEach var="approvalPc" items="${approvalPcList}" varStatus="status">
  <tr>
   <th rowspan="2">사 용 자</th>
   <th>성 명</th>
   <td style="width:300px;" class="align_l">${approvalPc.userNm}</td>
   <th>ID</th>
   <td class="align_l">${approvalPc.userId}<input type="hidden" id="appId" name="appId" value="${approvalPc.userId}"/></td>
  </tr>
  <tr>
   <th>부 서</th>
   <td class="align_l" colspan="3">${approvalPc.deptNm}</td>
   <%-- <th>ID</th>
   <td class="align_l">${approvalPc.userId}
      <input type="hidden" id="appId" name="appId" value="${approvalPc.userId}"/>
   </td> --%>
  </tr>
  <tr>
   <th style="width:40px;">용&nbsp;&nbsp;&nbsp;&nbsp;도 </th>
     <td class="align_l" colspan="4">
     ${approvalPc.permitGubun}
      <c:forEach var="code" items="${tt:getCodeValues('002') }">
      <c:if test="${voApprInfo.purpose == code[0]}">${code[1]}</c:if>
     </c:forEach>
     </td>
  </tr>
  <tr>
   <th style="width:40px;">만 료 일</th>
   <td colspan="4" class="align_l">
    <c:out value="${fn:substring(voApprInfo.endDm,0,10)}" />
    <input type="hidden" id="endDm" name="endDm" value="${voApprInfo.endDm}"/>
   </td>
  </tr>
  <tr>
   <th style="width:40px;">규&nbsp;&nbsp;&nbsp;&nbsp;칙</th>
   <td colspan="4" class="align_l">
    <c:forEach var="exctList" items="${apprDetailList}" varStatus="status">
     ${exctList.ruleName}
     <input type="hidden" id="ruleGUID" name="ruleGUID" value="${exctList.ruleGUID}"/>
     <input type="hidden" id="timeGUID" name="timeGUID" value="${exctList.timeGUID}"/>
     <input type="hidden" id="deptGUID" name="deptGUID" value="${exctList.deptGUID}"/>
     <input type="hidden" id="empGUID" name="empGUID" value="${exctList.empGUID}"/>
    </c:forEach>
   </td>
  </tr>
  <tr>
   <th style="width:40px;">시 간 대</th>
   <td colspan="4" class="align_l">
    <c:forEach var="exctList" items="${apprDetailList}" varStatus="status">${exctList.timeName}</c:forEach>
<!--     <input type="text" id="timeSolt" name="timeSolt" class="middle" readonly /> -->
<!--     <input type="button" name="btn_timeSolt" id="btn_timeSolt" onClick="timeListDwr(1,1)" value="검색" class="swhite" style="cursor: pointer;" /> -->
   </td>
  </tr>
  <tr>
   <th style="width:40px;">사&nbsp;&nbsp;&nbsp;&nbsp;유</th>
   <td colspan="4" class="align_l">
    <input type="hidden" id="reason" name="reason" value="${voApprInfo.reason}"/>
     ${voApprInfo.reason}
   </td>
  </tr>
  </c:forEach>
  <tr>
    <th>증빙서류</th>
    <td class="align_l" colspan="4">
      <c:choose>
     <c:when test="${!empty voApprInfo.addProofNm}">
      <a href="<c:url value='appr/download.do?fid=${voApprInfo.apprId}&pid=fileNm'/>" style="cursor: pointer;">${voApprInfo.addProofNm}</a>
     </c:when>
     <c:otherwise>첨부파일없음</c:otherwise>
    </c:choose>
    </td>
   </tr>
 </table>

 <div id="apprDiv">
 <div class="img_va">
  <img src="<c:url value="/image/admin/btn_right.gif"/>" />결재
 </div>
 <table class="tbws1" style="margin: 0px 0 30px 0;">
 <tr>
   <th style="width:10%">결재구분</th>
   <td style="width:40%" class="align_l">${userDtl.cdDtlNm }
   </td>
   <th style="width:10%">직급</th>
   <td class="align_l"><c:forEach var="medCode" items="${tt:getCodeValues('010')}">
		<c:if test="${userDtl.userPosition == medCode[0]}">${medCode[1]}</c:if>
	</c:forEach></td>
  </tr>
  <tr>
   <th>결재자</th>
   <td class="align_l">${userDtl.userNm }</td>
   <th>부서명</th>
   <td class="align_l">${userDtl.deptNm }</td>
  </tr>
  <tr>
   <th>결재의견</th>
   <td class="align_l" colspan="4"><input id="resultReason" value="재가 합니다." name="resultReason" type="text" style="width:98%;" onFocus="clearMessage(this.form);" ></td>
  </tr>
 </table>
 </div>
 <div class="right">
  <input type="button" class="nwhite" id="btn_appr" value="결재승인">
  <input type="button" class="nwhite" id="btn_disAppr" value="결재반려">
  <input type="button" class="nwhite" id="btn_list" value="목록">
 </div>
</form:form>
<!-- //CONTENT START -->