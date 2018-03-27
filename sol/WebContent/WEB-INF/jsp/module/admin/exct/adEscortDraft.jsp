<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type='text/javascript' src="<c:url value='/dwr/interface/dwrManagement.js'/>"></script>
<script type="text/javaScript" src="/admin/sitemap/common/OnetimeSubmit.js"></script>
<script type="text/javaScript" src="/common/exct/datepicker.js"></script>
<script type="text/javascript" src="<c:url value='/js/chkValidate.js'/>"></script>
<style type="text/css"> label.error{display:block;color:red;}</style>
<style type="text/css">
.ui-datepicker{
	width : 180px; 
}
button.ui-datepicker-close {display: none;}​
</style>
<script type="text/javaScript" language="javascript" defer="defer">

  var d = new Date();
  var t_date = leadingZeros(d.getFullYear(), 4) + '-' +  leadingZeros(d.getMonth() + 1, 2) + '-' +  leadingZeros(d.getDate(), 2);
  var t_hour = leadingZeros(d.getHours(), 2);
  var t_minute = leadingZeros(d.getMinutes(), 2);
	
  
$(document).ready(function(){
  $("#proofChk").click(function(){
      /* $("#openDate").val("");
      $("#closeDate").val("");
      $("#closeDate").attr("disabled", true); */
    if($("input[name=proofChk]").is( ":checked") == true ){
           $("#adProof").attr("disabled", false);
           $("#chkDay").val("1");
         }
         if($("input[name=proofChk]").is( ":checked") != true ){
           $("#adProof").attr("disabled", true);
           $("#chkDay").val("0");
         }
    });

  //메뉴출력
  openMenu('02');

  //증빙서류 첨부여부
  var addProofYn = $("#addProofYn").val();
  if( addProofYn == "Y"){
    $("#trProofYn").show();
  }

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
  
  $("#startHour").val(t_hour).attr("selected", "selected");
  
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
  
  $("#startMin").val(t_minute).attr("selected", "selected");
  $("#openDate").val(t_date);

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
  //결재상신
  $("#btn_appr").click(function(){
	  /* if($(".trPc").length < 1){
	         alert("*예외대상PC를 선택해주세요.");
	         return false;
	  }
	  if($(".tr").length < 1){
	         alert("*결재자를 선택해주세요.");
	         return false;
	  } */
    $.prompt("신청 하시겠습니까?", {
          buttons: { "예": true, "아니오": false },
          submit: function(e,v,m,f){
            if (v) {
              var radioLength = $('input:radio[name=userRadio]').length;
              var objNmLength = $(".trPc").length;
              var apprNmLength = $(".tr").length;
              var exctIdLength = $("input:checkbox[name=chkExctId]:checked").length;
              var chkApprover = "";
              var chkPc = "";
              var chkObjNms = "";
              var chkExctIds = "";
              var chkExctNms = "";
              var permitGubuns = "";
              var chkEmpNos = "";
              var chkDeptNms = "";
              $("#chkValidateGubun").hide();
              $("#errorDate").hide();
              
              $("input:radio[name=userRadio]").each(function(){
                chkApprover = chkApprover + $(this).attr('userId') + ",";
                $("#chkApprover").val(chkApprover);
              });
              
              if(apprNmLength < 1){
              	$("#chkValidateAppr").show();
                  $("#chkValidateAppr").attr("tabindex", -1).focus();
                  $.prompt.close(true);
                  alert("*결재자를 선택해주세요.");
                  return false;
              }
             var gubun = 0;
             var j = 1;
             for(var i = 0; i < apprNmLength; i++){
            	 //alert($("#gubunCheck_"+j).val());
            	 if($("#gubunCheck_"+j).val() == "03" ){
            		 gubun += 1;
            	 }
            	 j += 1;
             }
             //alert("apprNmLength : " + apprNmLength);
             //alert("gubun : " + gubun);
              if(gubun == apprNmLength){
            	  $("#chkValidateGubun").show();
                  $("#chkValidateGubun").attr("tabindex", -1).focus();
                  $.prompt.close(true);
                  return false;
              }
            	  
              if (objNmLength > 1){
               $(".trPc").each(function(){   
                 chkPc = chkPc + $(this).attr('serial') + ",";
                   $("#chkPc").val(chkPc);
                 chkObjNms = chkObjNms + $(this).attr('chkObjNms') + ",";
                   $("#chkObjNms").val(chkObjNms);
                 chkEmpNos = chkEmpNos + $(this).attr('chkEmpNo') + ",";
                   $("#chkEmpNo").val(chkEmpNos);
                  chkDeptNms = chkDeptNms + $(this).attr('chkDeptNm') + ",";
                   $("#chkDeptNms").val(chkDeptNms);
               });
              } else if(objNmLength < 1){
                  $("#chkValidateExctPc").show();
                  $("#chkValidateExctPc").attr("tabindex", -1).focus();
                  $.prompt.close(true);
                  alert("*예외대상PC를 선택해주세요.");
                  return false;
              } else{
                 chkPc = $(".trPc").attr('serial');
                   $("#chkPc").val(chkPc);
                 chkObjNms = $(".trPc").attr('chkObjNms');
                   $("#chkObjNms").val(chkObjNms);
                 chkEmpNos = $(".trPc").attr('chkEmpNo');
                   $("#chkEmpNo").val(chkEmpNos);
                 chkDeptNms= $(".trPc").attr('chkDeptNm');
                   $("#chkDeptNms").val(chkDeptNms);
              }
              if (exctIdLength > 1){
                $("input:checkbox[name=chkExctId]:checked").each(function(){
                  chkExctIds = chkExctIds + $(this).attr('exctId') + ",";
                    $("#chkExctIds").val(chkExctIds);
                  chkExctNms = chkExctNms + $(this).attr('exctNms') + ",";
                    $("#chkExctNms").val(chkExctNms);
                });
              }else if(exctIdLength < 1) {
                var termManageList = $("#termManageList").val();
                $("#chkValidateExctList").show();
                $("#chkValidateExctListTr").show();
                  $("#chkValidateExctList").attr("tabindex", 0).focus();
                  $("#apprItems").attr('rowspan', termManageList);
                  $.prompt.close(true);
                  return false;
              } else {
                chkExctIds = $("input:checkbox[name=chkExctId]:checked").attr('exctId');
                  $("#chkExctIds").val(chkExctIds);
                chkExctNms = $("input:checkbox[name=chkExctId]:checked").attr('exctNms');
                  $("#chkExctNms").val(chkExctNms);
              }

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
              if(openDate == "" || openDate == null){
                $("#errorDate").html("*시작일을 입력해주세요.");
                $("#errorDate").show();
                $("#openDate").focus();
                $.prompt.close(true);
                return false;
              }else if(closeDate == "" || closeDate == null){
                $("#errorDate").html("*종료일을 입력해주세요.");
                $("#errorDate").show();
                $("#closeDate").focus();
                $.prompt.close(true);
                return false;
              }
              var tempOpenDate = parseInt(startDate);
              var tempCloseDate = parseInt(endDate);
              if(tempOpenDate >= tempCloseDate){
                $("#errorDate").html("*적용기간을 올바르게 입력해주세요.");
                  $("#errorDate").show();
                  $("#openDate").focus();
                  $.prompt.close(true);
                  return false;
              }
              $("#checkMessage").val("success_appr");
                $("#exctForm").attr("action", "<c:url value='/adEscortApproval.do'/>");
                $("#exctForm").submit();
            }
          }
        });
    return false;
  });

  //신청항목 유효성 검사 제거
  $( "input[name=chkExctId]").click(function(){
	$("#chkAllBtn").attr("checked", false);
    var termManageListBack = $("#termManageListBack").val();
    $("#chkValidateExctList").hide();
    $("#chkValidateExctListTr").hide();
    $("#apprItems").attr('rowspan', termManageListBack);
    var row = $(this).attr("row");
    if($(this).is( ":checked") == true)
    {
    	$("#Exct_tr"+row).attr("class", "Exct_tr selectedRow4");
    }
    else
   	{
    	$("#Exct_tr"+row).attr("class", "Exct_tr");
   	}
  });
  
  /* checkbox 전체선택,해제 function */
  $("#chkAllBtn").click(function(){
    if($("#chkAllBtn").attr("checked") == true){
      $("input[name=chkExctId]").attr("checked", true);
	  $(".Exct_tr").attr("class", "Exct_tr selectedRow4");
    }
    else
    {
      $('input[name=chkExctId]').attr("checked", false);
      $(".Exct_tr").attr("class", "Exct_tr");
    }
  });
  
  /* 권한처리 */
  <c:if test="${mnuLvlCd != 'write'}">
  		$("#btn_appr").attr("disabled", "disabled");
  		$("input:text").attr("disabled", true);
  		$("input:checkbox").attr("disabled", true);
  		$("input:button").attr("disabled", true);
  		$("select").attr("disabled", true);
  		$("textarea").attr("disabled", true);
  	    $("input:button").attr("style", "color:#ABABAB");
  </c:if>   
  
});

  //resetForm
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
  var compUserId = "";
  var chkComp = 0;
  var max_id = 0;
  var this_id =0;
  function userClick(userNm,userPosition,deptNm,userId) {
	$(".appRadio").each(function(){
		max_id = $(this).attr("value");
		if(this_id < max_id)
		{
			this_id = max_id;
		}
	});
	cnt = parseInt(max_id)+parseInt(1);
    $("input:radio[name=userRadio]").each(function(){
        compUserId = $(this).attr('userId');
      if(compUserId == userId){
        chkComp = 1;
      }
    });
    if(chkComp == 1){
        $("#selectApproverErrorMsg").html("*결재자를 중복해서 입력할 수 없습니다.")
        $("#selectApproverErrorMsg").show();
        chkComp = 0;
        return false;
      }
    $("#chkValidateAppr").hide();
    $("#chkValidateGubun").hide();
    
     div += "<tr id=\"tr_"+cnt+"\" class=\"tr\">";
     div += "<td><input type=\"radio\" class=\"appRadio\" name=\"userRadio\" value="+cnt+" userId="+userId+"></td>";
     div += "<td><select style=\"width:60px\" class=\"permitGubun\" id=\"gubunCheck_"+cnt+"\" name=\"gubunCheck\"><option value=\"01\">결재</option><option value=\"02\">합의</option><option value=\"03\">통보</option></select></td>";
     div += "<td>"+ userNm +"</td><td>"+userPosition+"</td><td>"+deptNm+"</td><td><img src=\"/image/admin/btn_01.gif\" class=\"img_verti\" onClick=\"removeAppUser("+cnt+")\"></td>";
     div += "</tr>";
    $('#approvalUser').append(div);
    cnt ++;
    div ="";
    $('#selectUser_area').dialog('close');
    resetForm();
  }

  // 대상PC 선택한 값 부모창에 설정
  var divPc ="";
  var cntPc = 1;
  var compSerial = "";
  function pcClick(hName,sdeptNm,serial,ipAddr,empNo) {
    $("#selectPcErrorMsg").hide();
    $(".trPc").each(function(){
      compSerial = $(this).attr("serial");
      if(compSerial == serial){
        chkComp = 1;
      }
    });
    if(chkComp == 1){
      $("#selectPcErrorMsg").html("*대상PC를 중복해서 입력할 수 없습니다.");
      $("#selectPcErrorMsg").show();
      chkComp = 0;
      return false;
    }

    $("#chkValidateExctPc").hide();
     divPc += "<tr id=\"trPc_"+cntPc+"\" class=\"trPc\" name=\"trPc\" serial="+serial+" chkObjNms="+hName+" chkEmpNo="+empNo+" chkDeptNm="+sdeptNm+">";
     divPc += "<td>"+sdeptNm+"</td><td>"+ hName +"</td><td>"+serial+"</td><td>"+ipAddr+"</td><td><img src=\"/image/admin/btn_01.gif\" class=\"img_verti\" onClick=\"removeExctUser("+cntPc+")\"></td>";
     divPc += "</tr>";
    $('#approvalPc').append(divPc);
    cntPc ++;
    divPc ="";
    $('#selectExctPc_area').dialog('close');
    resetForm();
    $("#selectPcErrorMsg").hide();
  }

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
	}
  
	function removeExctUser(num){
		$('#trPc_' + num).remove();
	}

  function removeErrorMsg(){
   $("#errorDate").hide();
  }
	
  function leadingZeros(n, digits) {
	  var zero = '';
	  n = n.toString();

	  if (n.length < digits) {
	    for (i = 0; i < digits - n.length; i++)
	      zero += '0';
	  }
	  return zero + n;
	}
  
  
  
</script>

<!-- HEADER START -->
<h3>예외 &gt; ESCORT정책예외신청</h3>
<!-- HEADER AND -->

<!-- CONTENT START -->
<form:form commandName="voExct" id="exctForm" name="exctForm" method="post" onsubmit="return onetimeSubmit(this)" enctype="multipart/form-data">
<input type="hidden" id="appCnt" value="">
<input type="hidden" id="maxAppTerm" name="maxAppTerm" value="${exctDraftDtl.maxAppTerm}" >
<input type="hidden" id="addProofMonth" name="addProofMonth" value="${exctDraftDtl.addProofMonth}" >
<input type="hidden" id="addProofDay" name="addProofDay" value="${exctDraftDtl.addProofDay}" >
<input type="hidden" id="chkDay" name="chkDay" value="0" >
<input type="hidden" id="addProofYn" name="addProofYn" value="${exctDraftDtl.addProofYn}" >
<input type="hidden" id="userNm" name="userNm" value="${userDtl.userNm}" >
<input type="hidden" id="userPosition" name="userPosition" value="${userDtl.userPosition}" >
<input type="hidden" id="deptNm" name="deptNm" value="${userDtl.deptNm}" >
<input type="hidden" id="userTel" name="userTel" value="${userDtl.userTel}" >
<input type="hidden" id="chkApprover" name="chkApprover">
<input type="hidden" id="chkPc" name="chkPc">
<input type="hidden" id="chkEmpNo" name="chkEmpNo">
<input type="hidden" id="chkDeptNms" name="chkDeptNms">
<input type="hidden" id="chkObjNms" name="chkObjNms">
<input type="hidden" id="chkExctIds" name="chkExctIds">
<input type="hidden" id="chkExctNms" name="chkExctNms">
<input type="hidden" id="startDm" name="startDm">
<input type="hidden" id="endDm" name="endDm">
<input type="hidden" id="permitGubuns" name="permitGubuns">
<input type="hidden" id="checkMessage" name="checkMessage" value="${voApprInfo.checkMessage}"/>
<input type="hidden" id="exctAppId" name="exctAppId" value="${exctDraftDtl.exctAppId}"/>
<input type="hidden" id="termManageList" name="termManageList" value="${fn:length(termManageList) + 2}"/>
<input type="hidden" id="termManageListBack" name="termManageListBack" value="${fn:length(termManageList) + 1}"/>
<input type="hidden" id="madeCd" name="madeCd" value="${userDtl.madeCd}"/>
<input type="hidden" id="currentDm" name="currentDm" value="${currentDm}"/>
<input type="hidden" id="deptCd" name="deptCd" value="${userDtl.deptCd}"/>
<%-- <input type="hidden" id="userPosition" name="userPosition" value="${userDtl.userPosition}"/> --%>


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
  <th width="100px;">ID(사번)</th>
  <td>${userDtl.userId}</td>
 </tr>
 <tr>
  <th>직급</th>
  <td>
  	<c:forEach var="medCode" items="${tt:getCodeValues('010')}">
		<c:if test="${userDtl.userPosition == medCode[0]}">${medCode[1]}</c:if>
	</c:forEach>
 </td>
  <th>부서</th>
  <td>${userDtl.deptNm}</td>
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
  <tr>
    <td colspan="6" style="display:none; color:red;" id="chkValidateAppr">*결재자를 선택해주세요.</td>
  </tr>
  <tr>
    <td colspan="6" style="display:none; color:red;" id="chkValidateGubun">*결재구분의 결재가 없습니다.</td>
  </tr>
  <c:forEach var="approverList" items="${approverList}" varStatus="cnt">
  <tr  id="tr_${cnt.count}" class="tr">
   <td><input type="radio" class="appRadio" name="userRadio" value="${cnt.count}" userId="${approverList.userId }" ></td>
   <td>
   <select class="permitGubun" style="width:60px;"  name="gubunCheck" id="gubunCheck_${cnt.count}">
    <c:forEach var="code" items="${tt:getCodeValues('003') }">
    <option value="${code[0]}" <c:if test="${approverList.compAdCode == code[0]}">selected</c:if> >${code[1]}</option>
    </c:forEach>
   </select>
   </td>
   <td>${approverList.userNm }</td>
   <td><c:forEach var="medCode" items="${tt:getCodeValues('010')}">
		<c:if test="${approverList.userPosition == medCode[0]}">${medCode[1]}</c:if>
	</c:forEach></td>
   <td>${approverList.deptNm }</td>
   <td><img src="/image/admin/btn_01.gif" class="img_verti" onClick="removeAppUser(${cnt.count})"></td>
   <!-- <td colspan="6" style="display:none; color:red;" id="errorTd">*결재자를 입력해주세요.</td> -->
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
  <div class="img_va"><img src="<c:url value="/image/admin/btn_right.gif"/>" />예외대상PC</div>
  <table class="tbws1" id="approvalPc" style="margin:0px 0 10px 0;">
   <tr name="trPc">
    <th>부서명</th>
    <th>사용자</th>
    <th>제조번호</th>
    <th>IP</th>
    <th style="width:7%">삭제</th>
   </tr>
   <tr>
    <td colspan="5" style="display:none; color:red;" id="chkValidateExctPc">*예외대상PC를 선택해주세요.</td>
   </tr>
 
	<c:forEach var="myPcList" items="${myPcList}" varStatus="cnt">
	<tr id="trPc_${cnt.count}" class="trPc" name="trPc" serial="${myPcList.serial}" chkObjNms="${myPcList.hName}" chkEmpNo="${myPcList.empNo}" chkDeptNm="${myPcList.sdeptNm}">
		<td>${myPcList.sdeptNm}</td>
		<td>${myPcList.hName}</td>
		<td>${myPcList.serial}</td>
		<td>${myPcList.ipAddr}</td>
		<td><img src="/image/admin/btn_01.gif" class="img_verti" onClick="removeExctUser(${cnt.count})"></td>
	</tr>
	</c:forEach>

  </table>
  <label class="error" name="error" for="trPc" generated="true" style="display:none;">에러메시지</label>
  <table class="noborder" style="margin-bottom:30px;">
  <tr>
   <td align="right"><input type="button" name="" id="btn_modify" onClick="approvalPcList_notSearch()" value="추가" class="swhite" style="cursor: pointer;" /></td>
  </tr>
 </table>
 <div class="img_va"><img src="<c:url value="/image/admin/btn_right.gif"/>" />신청정보</div>
  <table class="tbws1" style="margin:0px 0 30px 0;">
   <%-- <tr>
    <th style="width:40px;">용&nbsp;&nbsp;&nbsp;&nbsp;도 </th>
      <td class="align_l" colspan="3">
       <c:forEach var="medCode" items="${tt:getCodeValues('002')}">
         <input type="radio" name="purpose" value="${medCode[0] }" <c:if test="${medCode[0] == '01'}">checked</c:if>><span>${medCode[1] }</span>
       </c:forEach>
      </td>
   </tr> --%>
   <tr>
    <th>신청기간</th>
    <td colspan="3" class="align_l">
     <input type="text" id="openDate" name="openDate" class="middle" style="cursor:text;" readonly/> <select id="startHour" ></select> 시&nbsp;&nbsp; <select id="startMin"></select>분&nbsp;&nbsp;~&nbsp;&nbsp;
     <input type="text" id="closeDate" name="closeDate" class="middle" style="cursor:text;"  readonly/> <select id="endHour" ></select> 시&nbsp;&nbsp;<select id="endMin"></select> 분<p>
     <span id="errorDate" style="color:red; display:none;" value="">error</span>
    </td>
   </tr>
   <tr>
    <th id="apprItems" rowspan="${fn:length(termManageList)+1}">신청항목</th>
    <th style="width:20px;"><input type="checkbox" id="chkAllBtn" style="cursor:pointer;"/></th>
    <th>항목명</th>
    <th>상세설명</th>
   </tr>
   <tr id="chkValidateExctListTr" style="display:none;">
    <td colspan="3" style="color:red;display:none;" id="chkValidateExctList" >*신청항목을 선택해주세요.</td>
   </tr>
   <c:forEach var="exctList" items="${termManageList}" varStatus="status" >
    <tr id="Exct_tr${status.count}" class="Exct_tr">
     <td><input type="checkbox" name="chkExctId" id="chkExctId" style="cursor: pointer;" row="${status.count}" exctId = "${exctList.exctId}" exctNms="${exctList.exctNm}"/></td>
     <td>${exctList.exctNm}</td>
     <td>${exctList.exctDtl}</td>
    </tr>
   </c:forEach>
   <tr>
    <th>신청사유</th>
    <td colspan="3" class="align_l"><input type="text" id="reason" name="reason" style="width:400px;"></td>
   </tr>
   <tr id="trProofYn" >
    <th>증빙서류</th>
    <td colspan="3" class="align_l" style="padding-bottom:5px;">
     <div><input type="checkbox" id="proofChk" name="proofChk">사용 <input type="file" class="long" name="_attachFile" id="adProof" style="vertical-align: middle;" disabled/></div>
   </td>
   </tr>
  </table>
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