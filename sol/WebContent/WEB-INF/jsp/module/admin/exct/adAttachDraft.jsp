<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type='text/javascript' src="<c:url value='/dwr/interface/dwrManagement.js'/>"></script>
<script type="text/javaScript" src="/admin/sitemap/common/OnetimeSubmit.js"></script>
<script type="text/javaScript" src="/common/exct/datepicker.js"></script>
<script type="text/javascript" src="<c:url value='/js/chkValidate.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/html5placeholder.jquery.js'/>"></script>
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
  var gubunValue ="";
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
      $.prompt("신청 하시겠습니까?", {
            buttons: { "예": true, "아니오": false },
            submit: function(e,v,m,f){
              if (v) {
                   $("#objIpErrorMsg").hide();
                   $("#ip1ErrorMsg").hide();
                    $("#chkExctListError").hide();
                    $("#chkValidateGubun").hide();
                    $("#value1").val("");
                    var radioLength = $('input:radio[name=userRadio]').length;
                    var objNmLength = $(".trPc").length;
                    var apprNmLength = $(".trAppr").length;
                    var chkApprover = "";
                    var chkPc = "";
                    var chkObjNms = "";
                    var permitGubuns = "";
                    var chkExctNms= "";
                    var value1 = "";
                    var value2 = "";
                    var chkReturn = "";
                    var attachGubun = "";
                    var exctNms = "";
                    var chkEmpNos = "";
                    var chkDeptNms = "";
                    $("#errorDate").hide();
                    //예외대상PC validate
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
                  $("input:radio[name=userRadio]").each(function(){
                    chkApprover = chkApprover + $(this).attr('userId') + ",";
                    $("#chkApprover").val(chkApprover);
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
                  //신청기간 validate
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

                    
                    //통제예외목록 합치기
                    $(".value1").each(function(){
                    var val1Idx = $(".value1").index(this);
                    var result = $(this).val();
                    var result2 = $(".value2").eq(val1Idx).val();
                    var gubunName = $(this).attr("exctGubun");
                    var ip = /^(1|2)?\d?\d([.](1|2)?\d?\d){3}$/;
                      if(result != "" && result2 != "" && ip.test(result) && ip.test(result2)){
                        value1 = value1 + result + ",";
                        $("#value1").val(value1);
                        value2 = value2 + result2 + ",";
                        $("#value2").val(value2);
                        attachGubun = attachGubun + gubunName + ",";
                        $("#attachGubun").val(attachGubun);
                        chkExctNms = chkExctNms + "IP" + ",";
                        $("#chkExctNms").val(chkExctNms);
                        exctNms =exctNms + result + " ~ " + result2 + ",";
                        $("#exctNms").val(exctNms);
                        
                      } else if( result == "" && result2 == "" ){
                          return true;
                      } else if(result == "" || result2 == "" || !ip.test(result) || !ip.test(result2)){
                    	  $("#ip1ErrorMsg").html("*유효하지 않은 IP 입니다.");
	                        $("#ip1ErrorMsg").show();  
	                        $.prompt.close(true);
	                        chkReturn = 1;
                      } else {
                          $("#objIpErrorMsg").html("*해제대상 IP는 범위로 입력해주세요.");
                          $("#objIpErrorMsg").show();
                          chkReturn = 1;
                          return false;
                      }
                  });
                   if(chkReturn == 1){
                     chkReturn = "";
                     return false;
                   }
                  $(".objSite").each(function(){
                    var result = $(this).val();
                    var gubunName = $(this).attr("exctGubun");
                    if (result != null && result != ""){
                      value1 = value1 + result + ",";
                       $("#value1").val(value1);
                       attachGubun = attachGubun + gubunName + ",";
                       $("#attachGubun").val(attachGubun);
                       chkExctNms = chkExctNms + "사이트" + ",";
                       $("#chkExctNms").val(chkExctNms);
                       exctNms =exctNms + result + ",";
                       $("#exctNms").val(exctNms);
                    }
                  });
                  $(".objMsger").each(function(){
                    var result = $(this).val();
                    var gubunName = $(this).attr("exctGubun");
                    var value = $(this).attr("valueExe");
                    if (result != null && result != ""){
                       attachGubun = attachGubun + gubunName + ",";
                       $("#attachGubun").val(attachGubun);
                       value1 = value1 + value + ",";
                       $("#value1").val(value1);
                       chkExctNms = chkExctNms + "메신저" + ",";
                       $("#chkExctNms").val(chkExctNms);
                       exctNms =exctNms + result + ",";
                       $("#exctNms").val(exctNms);
                    }
                  });
                  $(".objActive").each(function(){
                    var result = $(this).val();
                    var gubunName = $(this).attr("exctGubun");
                    var value = $(this).attr("valueExe");
                    if (result != null && result != ""){
                        attachGubun = attachGubun + gubunName + ",";
                        $("#attachGubun").val(attachGubun);
                        value1 = value1 + value + ",";
                        $("#value1").val(value1);
                        chkExctNms = chkExctNms + "ActiveX" + ",";
                        $("#chkExctNms").val(chkExctNms);
                        exctNms =exctNms + result + ",";
                        $("#exctNms").val(exctNms);
                    }
                   });
                  if(gubunValue == '02'){
                    if($("#value1").val() == ""){
                      $("#chkExctListError").html("*통제예외목록은 1개 이상 입력해주세요.");
                      $("#chkExctListError").show();
                      return false;
                    }
                  }
                  else {
                     $("#chkExctNms").val("첨부통제예외");
                  }
                  $("#checkMessage").val("success_appr");
                  $("#exctForm").attr("action", "<c:url value='/adAttachApproval.do'/>");
                  $("#exctForm").submit();
              }
            }
          });
      return false;
  });

  //통제예외 전채/선택 구분
  $("input[name=allowGubun]").change(function(){
    gubunValue = $(this).val();
    if(gubunValue == '02'){
      $(".allowGubun").show();
    } else if (gubunValue == '01') {
      $(".allowGubun").hide();
    }
  });
  
  $('input[placeholder]').placeholder();
  
  
  
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
    $("#selectPcErrorMsg").html("*대상PC를 중복해서 입력할 수 없습니다.")
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
  //해제대상 IP 추가
  var divObjIp = "";
  function addObjIp(){
   var cntObjIp = $(".objIp").length;
   if(cntObjIp > 3){
    $("#objIpErrorMsg").html("*해제대상 IP는 5개 이하로 추가해주세요.");
    $("#objIpErrorMsg").show();
   }else{
     divObjIp += "<div style=\"clear:both; text-align: left; text-indent: 100px;\" id=\"objIp_"+cnt+"\" class=\"objIp\" >";
     divObjIp += "<input type=\"text\" class=\"value1\" exctGubun=\"IP\"> ~ ";
     divObjIp += "<input type=\"text\" class=\"value2\">";
     divObjIp += "<img src=\"/image/admin/btn_01.gif\" class=\"img_verti\" onClick=\"removeAppIp("+cnt+")\" style=\"margin-left: 30px;\">";
     divObjIp += "</div>";
     $('#objIp').append(divObjIp);
     divObjIp = "";
     cnt++;
   }

  };
  //통제예외목록 추가
  var divObj = "";
  function addObj(obj){
  var cntObj = $("."+obj+"Length").length;
   if(cntObj > 3){
       $("#objSiteErrorMsg").html("*해제대상 사이트는 5개 이하로 추가해주세요.");
       $("#objSiteErrorMsg").show();
   }else{
    divObj += "<div style=\"clear:both; text-align: left; text-indent: 100px;\" id=\""+obj+"_"+cnt+"\" class=\""+obj+"Length\" >";
    divObj += "<input type=\"text\" class="+obj+" id=\""+obj+cnt+"\" style=\"width:280px; \" exctGubun=\"HOST\">";
    divObj += "<img src=\"/image/admin/btn_01.gif\" class=\"img_verti\" onClick=\"removeAppUserObj("+cnt+",'"+obj+"')\" style=\"margin-left: 30px;\">";
    divObj += "</div>";
    $('#'+obj+'').append(divObj);
    divObj = "";
    cnt++;
   }

  };
  //해제대상 메신저 입력창 추가
  function addObjMsg(obj, num){
    var cntObj = $("."+obj+"Length").length;
    var gubun = "";
    if(obj == 'objMsger'){
      gubun = 'MSGR';
    } else {
      gubun = 'CLSID';
    }
     if(cntObj > 3){
       if(obj == 'objMsger'){
          $("#objMsgerErrorMsg").html("*해제대상 메신저는 5개 이하로 추가해주세요.");
          $("#objMsgerErrorMsg").show();
       }else{
          $("#objActiveErrorMsg").html("*해제대상 ActiveX는 5개 이하로 추가해주세요.");
          $("#objActiveErrorMsg").show();
       }
     }else{
      divObj += "<div style=\"clear:both; text-align: left; text-indent: 100px;\" id=\""+obj+"_"+cnt+"\" class=\""+obj+"Length\" >";
      divObj += "<span style=\"position:relative; display:block;\"><input type=\"text\" class="+obj+" id=\""+obj+cnt+"\" style=\"width:280px; cursor:text;\" onClick=\"selectObjMsgerList(this,'"+num+"')\" exctGubun=\""+gubun+"\" placeholder=\"클릭하세요\" readonly>";
      divObj += "<img src=\"/image/admin/btn_01.gif\" class=\"img_verti\" onClick=\"removeAppUserObj("+cnt+",'"+obj+"')\" style=\"margin-left: 30px;\">";
      divObj += "</span></div>";
      $('#'+obj+'').append(divObj);
      divObj = "";
      cnt++;
     }
     $('input[placeholder]').placeholder();

   };
 //해제대상 메신저 목록 추가
   var arrAppName = "";
   function msgClick(appName, appExe, objMsg) {
     var msgName = $("#tempObj").val();
     /* arrAppName = arrAppName + appName + ":";
     alert(arrAppName);
     alert(arrAppName.length);
     for(var i = 0; i > arrAppName.length; i++){
    	 if(arrAppName[i] == appName){
    		 alert(1);
    	 }
     } */
     $("#"+msgName+"").val(appName);
     $("#"+msgName+"").attr("valueExe", appExe);
     $('#selectExctMsg_area').dialog('close');
     resetForm();
   }

 //해제대상 ActiveX 목록 추가
   function actXClick(appName, appExe, objMsg) {
    var activeName = $("#tempObj").val();
    $("#"+activeName+"").val(appName);
    $("#"+activeName+"").attr("valueExe", appExe);
    $('#selectExctActX_area').dialog('close');
    resetForm();
  }

  function removeAppUser(num){
    $('#tr_' + num).remove();
    //$('#objIp_' + num).remove();
    //$("#objIpErrorMsg").hide();
  }
  
  function removeExctUser(num){
    $('#trPc_' + num).remove();
  }
  
  function removeAppIp(num){
	    $('#objIp_' + num).remove();
	    $("#objIpErrorMsg").hide();
	  }
  //통제예외목록 삭제
  function removeAppUserObj(num,obj){
    $('#'+obj+'_' + num).remove();
    $("#"+obj+"ErrorMsg").hide();
  }
  function selectObjMsgerList(obj, num){
	  
     var objId = obj.getAttribute('id');
     $("#tempObj").val(objId);
     if(num == '1'){
       selectObjMsgList(1,1);
     }else{
       selectObjActXList(1,1);
     }
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
  
  function checkIp(strip){
	  var ip = /^(1|2)?\d?\d([.](1|2)?\d?\d){3}$/;
	  return ip.test(strip);
  }
  </script>

<!-- HEADER START -->
<h3>예외 &gt; 첨부통제예외신청</h3>
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
<input type="hidden" id="chkObjNms" name="chkObjNms">
<input type="hidden" id="startDm" name="startDm">
<input type="hidden" id="endDm" name="endDm">
<input type="hidden" id="permitGubuns" name="permitGubuns">
<input type="hidden" id="value1" name="value1">
<input type="hidden" id="value2" name="value2">
<input type="hidden" id="chkExctNms" name="chkExctNms">
<input type="hidden" id="exctAppId" name="exctAppId"  value="${exctDraftDtl.exctAppId}">
<input type="hidden" id="tempObj" name="tempObj">
<input type="hidden" id="madeCd" name="madeCd" value="${userDtl.madeCd}"/>
<input type="hidden" id="attachGubun" name="attachGubun">
<input type="hidden" id="checkMessage" name="checkMessage" value="${voApprInfo.checkMessage}"/>
<input type="hidden" id="chkEmpNo" name="chkEmpNo">
<input type="hidden" id="chkDeptNms" name="chkDeptNms">
<input type="hidden" id="exctNms" name="exctNms">
<input type="hidden" id="currentDm" name="currentDm" value="${currentDm}"/>
<input type="hidden" id="deptCd" name="deptCd" value="${userDtl.deptCd}"/>
<%-- <input type="hidden" id="userPosition" name="userPosition" value="${userDtl.userPosition}"/> --%>

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
   <td><input type="radio" class="appRadio" name="userRadio" value="${cnt.count}" userId="${approverList.userId }"></td>
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

  <!-- 예외대상PC 선택 시작-->
  <div class="img_va"><img src="<c:url value="/image/admin/btn_right.gif"/>" />예외대상PC</div>
  <table class="tbws1" id="approvalPc" style="margin:0px 0 10px 0;">
   <tr>
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
  <table class="noborder" style="margin-bottom:30px;">
  <tr>
   <td align="right"><input type="button" name="" id="btn_modify" onClick="approvalPcList_notSearch()" value="추가" class="swhite" style="cursor: pointer;" /></td>
  </tr>
 </table>
<!-- 예외대상PC 선택 끝-->

<!-- 신청정보 시작-->
 <div class="img_va"><img src="<c:url value="/image/admin/btn_right.gif"/>" />신청정보</div>
  <span id="chkExctListError" style="color:red; display:none;"></span>
  <table class="tbws1" style="margin:0px 0 30px 0;">
   <tr>
    <th style="width:40px;">용&nbsp;&nbsp;&nbsp;&nbsp;도 </th>
      <td class="align_l" colspan="3">
       <c:forEach var="medCode" items="${tt:getCodeValues('002')}">
         <input type="radio" name="purpose" value="${medCode[0] }" <c:if test="${medCode[0] == '01'}">checked</c:if>/><span>${medCode[1] }</span>
       </c:forEach>
      </td>
   </tr>
   <tr>
    <th>신청기간</th>
    <td colspan="3" class="align_l">
     <input type="text" id="openDate" name="" class="middle" readonly /> <select id="startHour"></select> 시&nbsp;&nbsp; <select id="startMin"></select>분&nbsp;&nbsp;~&nbsp;&nbsp;
     <input type="text" id="closeDate" name="" class="middle" readonly/> <select id="endHour"></select> 시&nbsp;&nbsp;<select id="endMin"></select> 분<p>
     <span id="errorDate" style="color:red; display:none;" value="">error</span>
    </td>
   </tr>
   <tr>
    <th>통제구분</th>
    <td colspan="3" class="align_l">
       <c:forEach var="medCode" items="${tt:getCodeValues('008')}">
         <input type="radio" name="allowGubun" value="${medCode[0] }" <c:if test="${medCode[0] == '01'}">checked</c:if>/><span>${medCode[1] }</span>
       </c:forEach>
    </td>
   </tr>
   <tr class="allowGubun" style="display:none;">
    <th rowspan="4">통제예외목록</th>
    <th style="width:200px;">해제대상 IP</th>
    <td id="objIp">
    <span id="objIpErrorMsg" style="color:red; display:none; text-indent: 100px;" ></span><p>
    <span id="ip1ErrorMsg" style="color:red; display:none; text-indent: 100px;" ></span><p>
     <div style="float:left; text-indent: 100px;"><input type="text" class="value1" exctGubun="IP"> ~ <input type="text" class="value2"><img src="/image/admin/btn_02.gif" class="img_verti" style="margin-left: 30px;" onclick="addObjIp()"></div>
    </td>
   </tr>
   <tr class="allowGubun" style="display:none;">
    <th>해제대상 사이트</th>
    <td id="objSite">
       <span id="objSiteErrorMsg" style="color:red; display:none;" ></span><p>
      <div style="float:left; text-indent: 100px;"><input type="text" class="objSite" style="width:280px;" exctGubun="HOST"><img src="/image/admin/btn_02.gif" class="img_verti" style="margin-left: 30px;" onclick="addObj('objSite')"></div>
    </td>
   </tr>
   <tr class="allowGubun" style="display:none;">
    <th>해제대상 메신저</th>
    <td id="objMsger">
    <span id="objMsgerErrorMsg" style="color:red; display:none;" ></span>
    <div style="clear:both; text-align: left; text-indent: 100px;"><span style="position:relative; display:block;"><input type="text" class="objMsger" id="objMsger9" style="width:280px; cursor:text;" onClick="selectObjMsgerList(this, '1');" exctGubun="MSGR" placeholder="클릭하세요" readonly><img src="/image/admin/btn_02.gif" class="img_verti" style="margin-left: 30px;" onclick="addObjMsg('objMsger','1')"></span></div><p>
    </td>
   </tr>
   <tr class="allowGubun" style="display:none;">
    <th>해제대상 ActiveX</th>
    <td id="objActive">
    <span id="objActiveErrorMsg" style="color:red; display:none;" ></span>
    <div style="clear:both; text-align: left; text-indent: 100px;"><span style="position:relative; display:block;"><input type="text" class="objActive" id="objActiveX9" style="width:280px; cursor:text;" onClick="selectObjMsgerList(this, '2');" exctGubun="CLSID" placeholder="클릭하세요" readonly><img src="/image/admin/btn_02.gif" class="img_verti" style="margin-left: 30px;" onclick="addObjMsg('objActive','2')"></span></div>
    </td>
   </tr>
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
<%//해제대항 메신저 선택%>
<jsp:include page="../popup/selectObjMsg.jsp" />
<%//해제대항 ActiveX 선택%>
<jsp:include page="../popup/selectObjActiveX.jsp" />