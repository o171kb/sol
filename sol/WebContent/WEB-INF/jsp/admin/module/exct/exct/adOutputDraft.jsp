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
  //메뉴출력
  openMenu('02');

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

  var resultBackDate = $("#resultDay").val();
  $("#proofChk").click(function(){
      //$("#openDate").val("");
      //$("#closeDate").val("");
      //$("#closeDate").attr("disabled", true);
    if($("input[name=proofChk]").is( ":checked") == true ){
       $("#adProof").attr("disabled", false);
       $("#chkDay").val("1");
     }
     if($("input[name=proofChk]").is( ":checked") != true ){
       $("#adProof").attr("disabled", true);
       $("#chkDay").val("0");
     }
   });
  $("#openDate").val(t_date);
 
  //증빙서류 첨부여부
  var addProofYn = $("#addProofYn").val();
  if( addProofYn == "Y"){
    $("#trProofYn").show();
  }

  //결재상신
  $("#btn_appr").click(function(){
      $.prompt("신청 하시겠습니까?", {
            buttons: { "예": true, "아니오": false },
            submit: function(e,v,m,f){
              if (v) {
            	  $("#chkValidateGubun").hide();
                  $("#itemsError").hide();
                  var apprNmLength = $(".trAppr").length;
                  var chkApprover = "";
                  var chkPc = "";
                  var permitGubuns = "";
                  var exctItems = "";
                  var exctNms = "";
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
                $("input:radio[name=userRadio]").each(function(){
                  chkApprover = chkApprover + $(this).attr('userId') + ",";
                  $("#chkApprover").val(chkApprover);
                });
                $("#chkPc").val($("#objUserNm").attr('serial'));
                $(".permitGubun").each(function(){
                  var eachGubun = $(this).val();
                  permitGubuns = permitGubuns + eachGubun + ",";
                  $("#permitGubuns").val(permitGubuns);
                });
                $("#chkEmpNo").val($("#objUserId").html());
                $("#chkObjNms").val($("#objUserNm").val());
                $("#chkDeptNms").val($("#objUserDept").html());

                var startDate = $("#openDate").val().replace(/-/g,"");
                $("#startDm").val(startDate);
                var endDate = $("#closeDate").val().replace(/-/g,"");
                $("#endDm").val(endDate);
                //신청기간 validate
                if(startDate == "" || startDate == null){
                    $("#errorDate").html("*시작일을 입력해주세요.");
                    $("#errorDate").show();
                    $("#openDate").focus();
                    $.prompt.close(true);
                    return false;
                  }else if(endDate == "" || endDate == null){
                    $("#errorDate").html("*종료일을 입력해주세요.");
                    $("#errorDate").show();
                    $("#closeDate").focus();
                    $.prompt.close(true);
                    return false;
                  }

                  if($("input:radio[name=items]:checked").length == 0){
                    $("#itemsError").html("신청항목을 선택해 주세요.");
                    $("#itemsError").show();
                  }
                  $("input:radio[name=items]:checked").each(function(){
                    var exctItem = $(this).val();
                    var exctNm = $(this).attr('exctNm');
                        exctItems = exctItems + exctItem + ",";
                        $("#chkExctIds").val(exctItems);
                        exctNms = exctNms + exctNm + ",";
                        $("#exctNms").val(exctNms);
                    });
                  $("#checkMessage").val("success_appr");
                  $("#exctForm").attr("action", "<c:url value='/adOutputApproval.do'/>");
                  $("#exctForm").submit();

              }
            }
          });
      return false;
  });
  
  
  /* 권한처리 */
  <c:if test="${mnuLvlCd != 'write'}">
  		$("#btn_appr").attr("disabled", "disabled");
  		$("input:button").attr("style", "color:#ABABAB");
  		//$("#btn_modify_menu").attr("disabled", "disabled");
  		//$("input:checkbox[name='chk_each']").attr("disabled", true);
  		$("input:text").attr("disabled", true);
  		$("input:checkbox").attr("disabled", true);
  		$("input:button").attr("disabled", true);
  		$("input:radio").attr("disabled", true);
  		$("select").attr("disabled", true);
  		$("textarea").attr("disabled", true);
  	   	//$("#btn_next").unbind();
  	   	//$("input:button[value='추가']").attr("disabled", true);
  	   	//$("#file_del").hide();
  	   	//$("input[name='_attachFile']").attr("disabled", "disabled");
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
	     div += "<tr id=\"tr_"+cnt+"\" class=\"trAppr\">";
	     div += "<td><input type=\"radio\" class=\"appRadio\" name=\"userRadio\" value="+cnt+" userId="+userId+"></td>";
	     div += "<td><select style=\"width:60px\" class=\"permitGubun\" id=\"gubunCheck_"+cnt+"\"><option value=\"01\">결재</option><option value=\"02\">합의</option><option value=\"03\">통보</option></select></td>";
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
    $("#objUserNm").attr('serial', serial);
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
<h3>예외 &gt; 출력보안예외신청</h3>
<!-- HEADER AND -->

<!-- CONTENT START -->
<form:form commandName="voExct" id="exctForm" name="exctForm" method="post" onsubmit="return onetimeSubmit(this)" enctype="multipart/form-data">
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
<input type="hidden" id="startDm" name="startDm">
<input type="hidden" id="endDm" name="endDm">
<input type="hidden" id="permitGubuns" name="permitGubuns">
<input type="hidden" id="exctAppId" name="exctAppId"  value="${exctDraftDtl.exctAppId}">
<input type="hidden" id="madeCd" name="madeCd" value="${userDtl.madeCd}"/>
<input type="hidden" id="chkExctIds" name="chkExctIds">
<input type="hidden" id="exctNms" name="exctNms">
<input type="hidden" id="checkMessage" name="checkMessage" value="${voApprInfo.checkMessage}"/>
<input type="hidden" id="chkEmpNo" name="chkEmpNo">
<input type="hidden" id="chkObjNms" name="chkObjNms">
<input type="hidden" id="chkDeptNms" name="chkDeptNms">
<input type="hidden" id="currentDm" name="currentDm" value="${currentDm}"/>
<input type="hidden" id="deptCd" name="deptCd" value="${userDtl.deptCd}"/>

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
  <tr id="tr_${cnt.count}" class="trAppr">
   <td><input type="radio" class="appRadio" name="userRadio" value="${cnt.count}" userId="${approverList.userId }" ></td>
   <td>
   <select class="permitGubun" style="width:60px;" id="gubunCheck_${cnt.count}">
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
   <td style="width:30%;" class="align_l"><input type="text" id="objUserNm" name="objUserNm" onfocus="approvalPcListAddId_notSearch()"> <input type="button" name="" id="btn_modify" onClick="approvalPcListAddId_notSearch()" value="검색" class="swhite" style="cursor: pointer;" /><p>
   <label class="error" name="error" for="objUserNm" generated="true" style="display:none;">에러메시지</label></td>
   <th>IP</th>
   <td><span id="objUserIp"></span></td>
  </tr>
  <tr>
   <th>부 서</th>
   <td style="text-align:left; padding-left:8px"><span id="objUserDept"></span></td>
   <th>ID</th>
   <td><span id="objUserId"></span></td>
  </tr>
   <tr>
    <th style="width:40px;">용&nbsp;&nbsp;&nbsp;&nbsp;도 </th>
      <td class="align_l" colspan="4">
       <c:forEach var="medCode" items="${tt:getCodeValues('002')}">
         <input type="radio" name="purpose" value="${medCode[0] }" <c:if test="${medCode[0] == '01'}">checked</c:if>/><span>${medCode[1] }</span>
       </c:forEach>
      </td>
   </tr>
   <tr>
    <th>신청기간</th>
    <td colspan="4" class="align_l">
     <input type="text" id="openDate" name="" class="middle" readonly/> &nbsp;&nbsp;~&nbsp;&nbsp;
     <input type="text" id="closeDate" name="" class="middle" readonly/> <p>
     <span id="errorDate" style="color:red; display:none;" value="">error</span>
    </td>
   </tr>
   <tr>
    <th>신청항목</th>
    <td colspan="4" class="align_l">
    <c:forEach var="medCode" items="${tt:getCodeValues('005')}">
      <input type="radio" name="items" value="${medCode[0] }" exctNm="${medCode[1]}" <c:if test="${medCode[1] == '공문서(무조건 예외)'}">checked</c:if>/><span>${medCode[1] }</span>
    </c:forEach>
    <p><span id="itemsError" style="color:red; display:none;"></span>
    </td>
   </tr>
   <tr>
    <th>신청사유</th>
    <td colspan="4" class="align_l"><input type="text" id="reason" name="reason" style="width:400px;"></td>
   </tr>
   <tr id="trProofYn" >
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
