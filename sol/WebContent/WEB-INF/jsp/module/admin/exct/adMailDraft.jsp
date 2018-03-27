<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type='text/javascript' src="<c:url value='/dwr/interface/dwrManagement.js'/>"></script>
<script type="text/javaScript" src="/admin/sitemap/common/OnetimeSubmit.js"></script>
<script type="text/javaScript" src="/common/exct/datepicker.js"></script>
<style type="text/css">
.ui-datepicker{
	width : 180px; 
}
button.ui-datepicker-close {display: none;}​
</style>
<script type="text/javaScript" language="javascript" defer="defer">
$(document).ready(function(){
  //메뉴출력
  openMenu('02');
  var resultBackDate = $("#resultDay").val();
  $("#closeDate").datepicker("option", "maxDate", resultBackDate);
  $("#proofChk").click(function(){
    var resultDate = $("#maxDay").val();
    if($("input[name=proofChk]").is( ":checked") == true ){
           $("#adProof").attr("disabled", false);
           $("#closeDate").datepicker("option", "maxDate", resultDate);
         }
         if($("input[name=proofChk]").is( ":checked") != true ){
           $("#adProof").attr("disabled", true);
           $("#closeDate").datepicker("option", "maxDate", resultBackDate);
         }
    });


  //증빙서류 첨부여부
  var addProofYn = $("#addProofYn").val();
  if( addProofYn == "Y"){
    $("#trProofYn").show();
  }
  //시간 풀다운 메뉴
  var startHour = "";
  for (var i = 0 ; i < 24 ; i++){
    startHour = $("<option />");
    if(i < 10){
      startHour.text("0"+i);
      }else{
        startHour.text(i);
      }
    $("#startHour").append(startHour);
  }
  var startMin = "";
  for (var i = 0 ; i < 60 ; i++){
    startMin = $("<option />");
    if(i < 10){
      startMin.text("0"+i);
    }else{
      startMin.text(i);
    }
    $("#startMin").append(startMin);
    $("#startMin").val("01").attr("selected", "selected");
  }
  var endHour = "";
  for (var i = 0 ; i < 24 ; i++){
    endHour = $("<option />");
    if(i < 10){
        endHour.text("0"+i);
      }else{
        endHour.text(i);
      }
    $("#endHour").append(endHour);
    $("#endHour").val("23").attr("selected", "selected");
  }
  var endMin = "";
  for (var i = 0 ; i < 60 ; i++){
    endMin = $("<option />");
    if(i < 10){
        endMin.text("0"+i);
      }else{
        endMin.text(i);
      }
    $("#endMin").append(endMin);
    $("#endMin").val("59").attr("selected", "selected");
  }
  //결재상신
  $("#btn_appr").click(function(){
    var radioLength = $('input:radio[name=userRadio]').length;
    var chkApprover = "";
    var chkPc = "";
    var chkObjNms = "";
    var permitGubuns = "";
  $("input:radio[name=userRadio]").each(function(){
    chkApprover = chkApprover + $(this).attr('userId') + ",";
    $("#chkApprover").val(chkApprover);
  });
  $(".trPc").each(function(){
    chkPc = chkPc + $(this).attr('serial') + ",";
      $("#chkPc").val(chkPc);
    chkObjNms = chkObjNms + $(this).attr('chkObjNms') + ",";
      $("#chkObjNms").val(chkObjNms);
  });
  $(".permitGubun").each(function(){
    var eachGubun = $(this).val();
    permitGubuns = permitGubuns + eachGubun + ",";
    $("#permitGubuns").val(permitGubuns);
  });
  var openDate = $("#openDate").val().replace(/-/g,"");
  var startDate = openDate + $("#startHour").val() + $("#startMin").val();
  $("#startDm").val(startDate);
  var closeDate = $("#closeDate").val().replace(/-/g,"");
  var endDate = closeDate + $("#endHour").val() + $("#endMin").val();
  $("#endDm").val(endDate);
  return false;
    $("#exctForm").attr("action", "<c:url value='/adOutputApproval.do'/>");
    $("#exctForm").submit();
    return false;
  });
  
  /* 권한처리 */
  <c:if test="${mnuLvlCd != 'write'}">
  		$("#btn_appr").attr("disabled", "disabled");
  		$("input:button").attr("style", "color:#ABABAB");
  		$("input:text").attr("disabled", true);
  		$("input:checkbox").attr("disabled", true);
  		$("input:button").attr("disabled", true);
  		$("input:radio").attr("disabled", true);
  		$("select").attr("disabled", true);
  		$("textarea").attr("disabled", true);
  </c:if>   
  
});

  //검색 조건 resetForm
  function resetForm(){
    $("#searchapprusernm").val("");
    $("#searchdeptnm").val("");
    $("#sdeptNm").val("");
    $("#hName").val("");
  }

  // 결재자 선택한 값 부모창에 설정
  var div ="";

  var chk="";
  var cnt = 0;
  function userClick(userNm,userPosition,deptNm,userId) {

     div += "<tr id=\"tr_"+cnt+"\">";
     div += "<td><input type=\"radio\" name=\"userRadio\" value="+cnt+" userId="+userId+"></td>";
     div += "<td><select style=\"width:60px\" class=\"permitGubun\"><option value=\"01\">결재</option><option value=\"02\">합의</option><option value=\"03\">통보</option></select></td>";
     div += "<td>"+ userNm +"</td><td>"+userPosition+"</td><td>"+deptNm+"</td><td><img src=\"/image/admin/btn_01.gif\" class=\"img_verti\" onClick=\"removeAppUser("+cnt+")\"></td>";
     div += "</tr>";
    $('#approvalUser').append(div);
    cnt ++;
    div ="";
    $('#selectUser_area').dialog('close');
    resetForm();
  }

  // 대상PC 선택한 값 부모창에 설정
  function pcClick(hName,sdeptNm,serial,ipAddr,empNo) {
    $("#objUserNm").val(hName);
    $("#objUserIp").html(ipAddr);
    $("#objUserDept").html(sdeptNm);
    $("#objUserId").html(empNo);

    $('#selectExctPc_area').dialog('close');
    resetForm();
  }
  //결재자정보 리스트 이동
  function moveRowUpDown(option) {
    var num = $('input[name=userRadio]:checked').val();
    if(num==undefined){
     return;
    }
    var element = $("#tr_"+num);
    if(option=="up"){
     if( element.prev().html() != null  && element.prev().attr("id") != "trHeader"){
      element.insertBefore(element.prev());
     }
    }else{
     if( element.next().html() != null ){
         element.insertAfter(element.next());
     }
    }
  }

  function removeAppUser(num){
    $('#tr_' + num).remove();
    $('#trPc_' + num).remove();
    $('#objIp_' + num).remove();
  }
</script>

<!-- HEADER START -->
<h3>예외 &gt; 웹메일예외신청</h3>
<!-- HEADER AND -->

<!-- CONTENT START -->
<form:form commandName="voExct" id="exctForm" name="exctForm" method="post" onsubmit="return onetimeSubmit(this)">
<input type="hidden" id="resultDay" name="resultDay" value="${resultDay}" >
<input type="hidden" id="maxDay" name="maxDay" value="${maxDay}" >
<input type="hidden" id="addProofYn" name="addProofYn" value="${exctDraftDtl.addProofYn}" >
<input type="hidden" id="userNm" name="userNm" value="${userDtl.userNm}" >
<input type="hidden" id="userPosition" name="userPosition" value="${userDtl.userPosition}" >
<input type="hidden" id="deptNm" name="deptNm" value="${userDtl.deptNm}" >
<input type="hidden" id="userTel" name="userTel" value="${userDtl.userTel}" >
<input type="hidden" id="chkApprover" name="chkApprover">
<input type="hidden" id="chkPc" name="chkPc">
<input type="hidden" id="chkObjNms" name="chkObjNms">
<input type="hidden" id="startDm" name="startDm">
<input type="hidden" id="endDm" name="endDm">
<input type="hidden" id="permitGubuns" name="permitGubuns">
<input type="hidden" id="exctAppId" name="exctAppId" value="OUTPUTDRAFT">

 <!-- 상단문구 -->
 <div>
    ${exctDraftDtl.topTerms}
 </div>
 <!-- 상단문구 -->
 <div class="img_va"><img src="<c:url value="/image/admin/btn_right.gif"/>" />기안자정보</div>
 <table class="tbws1" style="margin:0 0 30px 0;">
 <tr>
  <th rowspan="2" width="150px;">신청자</th>
  <th width="100px;">성명</th>
  <td width="275px;">${userDtl.userNm}</td>
  <th width="100px;">직급</th>
  <td>${userDtl.userPosition}</td>
 </tr>
 <tr>
  <th>부서</th>
  <td>${userDtl.deptNm}</td>
  <th>전화번호</th>
  <td>${userDtl.userTel}</td>
 </tr>
 </table>
<div class="img_va"><img src="<c:url value="/image/admin/btn_right.gif"/>" />결재자정보</div>
 <table class="tbws1" id="approvalUser" style="margin:0px 0 10px 0;">
  <tr id="trHeader">
   <th style="width:20px;">선택</th>
   <th style="width:20px;">결재구분</th>
   <th>결재자</th>
   <th>직급</th>
   <th>부서</th>
   <th style="width:20px;">삭제</th>
  </tr>
  <c:forEach var="approverList" items="${approverList}" >
  <tr>
   <td><input type="radio" name="userRadio" userId="${approverList.userId }"></td>
   <td>
   <select class="permitGubun" style="width:60px;" disabled >
    <c:forEach var="code" items="${tt:getCodeValues('003') }">
    <option value="${code[0]}" <c:if test="${approverList.compAdCode == code[0]}">selected</c:if> >${code[1]}</option>
    </c:forEach>
   </select>
   </td>
   <td>${approverList.userNm }</td>
   <td>${approverList.userPosition }</td>
   <td>${approverList.deptNm }</td>
   <td></td>
  </tr>
  </c:forEach>
 </table>
 <table class="noborder" style="margin-bottom: 15px;">
  <tr>
   <td align="left"><input type="button" name="" id="btn_up" onclick="moveRowUpDown('up')" value="순서위로" class="swhite" style="cursor: pointer;" /> <input type="button" name="" id="btn_down" onclick="moveRowUpDown('down')" value="순서아래로" class="swhite" style="cursor: pointer;" /></td>
   <td align="right"><input type="button" name="" id="btn_pop_add_user" onclick="approverListDwr(1, 1)" value="추가" class="swhite" style="cursor: pointer;" /></td>
  </tr>
 </table>
 <!-- 결재안내문구 시작-->
 <div style="margin-bottom: 30px;">
    ${exctDraftDtl.permitTerms}
 </div>
  <!-- 결재안내문구 끝-->
  <!-- 신청정보 시작-->
 <div class="img_va"><img src="<c:url value="/image/admin/btn_right.gif"/>" />신청정보</div>
  <table class="tbws1" style="margin:0px 0 30px 0;">
  <tr>
   <th rowspan="2">사 용 자</th>
   <th>성 명</th>
   <td style="width:30%;" class="align_l"><input type="text" id="objUserNm" disabled> <input type="button" name="" id="btn_modify" onClick="approvalPcListAddId(1, 1)" value="검색" class="swhite" style="cursor: pointer;" /></td>
   <th>IP</th>
   <td><span id="objUserIp"></span></td>
  </tr>
  <tr>
   <th>부 서</th>
   <td><span id="objUserDept"></span></td>
   <th>ID</th>
   <td><span id="objUserId"></span></td>
  </tr>
   <tr>
    <th style="width:40px;">용&nbsp;&nbsp;&nbsp;&nbsp;도 </th>
      <td class="align_l" colspan="4">
       <c:forEach var="medCode" items="${tt:getCodeValues('002')}">
         <input type="radio" name="purpose" value="${medCode[0] }"/><span>${medCode[1] }</span>
       </c:forEach>
      </td>
   </tr>
   <tr>
    <th>신청기간</th>
    <td colspan="4" class="align_l">
     <input type="text" id="openDate" name="" class="middle"/> <select id="startHour"></select> 시&nbsp;&nbsp; <select id="startMin"></select>분&nbsp;&nbsp;~&nbsp;&nbsp;
     <input type="text" id="closeDate" name="" class="middle"/> <select id="endHour"></select> 시&nbsp;&nbsp;<select id="endMin"></select> 분
    </td>
   </tr>
   <tr>
    <th>신청항목</th>
    <td colspan="4" class="align_l">
    <c:forEach var="medCode" items="${tt:getCodeValues('006')}">
      <input type="checkbox" name="items" value="${medCode[0] }"/><span>${medCode[1] }</span>
    </c:forEach>
    </td>
   </tr>
   <tr>
    <th>신청사유</th>
    <td colspan="4" class="align_l"><input type="text" id="reason" name="reason" style="width:400px;"></td>
   </tr>
   <tr id="trProofYn" style="display: none;">
    <th>증빙서류</th>
    <td colspan="4" class="align_l" style="padding-bottom:5px;">
     <div><input type="checkbox" id="proofChk" name="proofChk">사용 <input type="file" class="long" name="_attachFile" id="adProof" style="vertical-align: middle;" disabled/></div>
   </td>
   </tr>
  </table>
  <!-- 신청정보 끝-->
 <!-- 하단안내문구 시작-->
 <div id="abc" style="color:red;">
   ${exctDraftDtl.bottomTerms}
 </div>
 <!-- 하단안내문구 끝-->
<div class="right">
 <input type="button" class="bwhite" id="btn_appr" value="결재상신">
</div>
</form:form>
<!-- CONTENT END -->
<div class="mskbg" id="pop_bg" style="display:none;"><div class="msk"></div></div> <!-- 흰색 마스크 레이어 display로 제어-->
<div class="mskbg" id="pop_bg2" style="display:none;"><div class="msk"></div></div> <!-- 흰색 마스크 레이어 display로 제어-->

<%//결재자선택%>
<jsp:include page="../popup/selectApprover.jsp" />
<%//예외대상PC선택%>
<jsp:include page="../popup/selectObjPc.jsp" />
