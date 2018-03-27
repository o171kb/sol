<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javaScript" language="javascript" defer="defer">
  $(document).ready(function(){

$(".lwhite").click(function(){
  var exceptionName = "";
  var gubunAllow = "";
  var reason = "";
    for(var i = 1 ; i < 15; i++){
      if($("#chk"+i).attr( "checked") == true){
        exceptionName = exceptionName + $("#exceptionName"+i).attr('name') + ",";
        gubunAllow = gubunAllow + $("#gubunAllow"+i).val() + ",";
        reason = reason + $("#reason"+i).val() + ",";
        $("#exceptionName").val(exceptionName);
        $("#gubunAllow").val(gubunAllow);
        $("#reason").val(reason);
      }
    }
    $("#exceptionForm").attr("action","<c:url value='/adExceptionAction.do'/>");
    $("#exceptionForm").submit();
    return false;
});



$("#openDate").datepicker({
    dateFormat:'yy-mm-dd',
    showOn: 'focus',
    changeYear: true,
    changeMonth: true,
    showMonthAfterYear: true ,
    currentText: '오늘' ,
    dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'], // 요일의 한글 형식.
    monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] ,
    showButtonPanel: true, // 하단 today, done  버튼기능 추가 표시 (기본은 false)
    onSelect : function(dateText, inst){
      var toDateText = $("#closeDate").val();
      if(toDateText != ""){
        if(dateText > toDateText){
          $.prompt('"' + toDateText + '" 보다 이전일자를 입력하십시오.');
          $("#openDate").val($("#closeDate").val());
          return false;
        }
      }
    }

  });

  $("#closeDate").datepicker({
    dateFormat:'yy-mm-dd',
    showOn: 'focus',
    changeMonth: true,
    changeYear: true,
    showMonthAfterYear: true ,
    currentText: '오늘',
    dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'], // 요일의 한글 형식.
    monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] ,
       showButtonPanel: true, // 하단 today, done  버튼기능 추가 표시 (기본은 false)
       onSelect : function(dateText, inst){
      var fromDateText = $("#openDate").val();
      if(fromDateText != ""){
        if (dateText < fromDateText){
          $.prompt('"' + fromDateText + '" 보다 이후일자를 입력하십시오.');
          $("#closeDate").val($("#openDate").val());
          return false;
        }
      }
    }
  });

  $.datepicker.setDefaults($.datepicker.regional['ko']);
  });
</script>
<!-- HEADER START -->
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="text1">
 <tr>
  <td width="100%" height="10" align="RIGHT" bgcolor="#ffff00"></td>
 </tr>
 <tr>
  <td height="30" valign="BOTTOM" class="text2">&nbsp;&nbsp;<b>예외처리관리</b></td>
 </tr>
 <tr>
  <td height="10"><br />
  <br />
  <br /></td>
 </tr>
</table>
<!-- HEADER AND -->

<!-- CONTENT START -->
<form:form commandName="voException" id="exceptionForm" name="exceptionForm" method="post" onsubmit="return onetimeSubmit(this)">
<input type="hidden" id="arrChk" name="arrChk" value="">
<input type="hidden" id="exceptionName" name="exceptionName" value="">
<input type="hidden" id="gubunAllow" name="gubunAllow" value="">
<input type="hidden" id="gubunLog" name="gubunLog" value="">
<input type="hidden" id="reason" name="reason" value="">

<div style="margin: 0px 20px; width: 900px">
 <!-- 검색 -->
 <table class="border02" style="width: 100%">
  <tr>
   <th>부서명</th>
   <td><input type="text" id="searchKeyword" style="width: 200px;" /></td>
  </tr>
 </table>
 <div style="text-align: right">
  <!-- <input type="button" id="searchForm" value="검색" class="lwhite" style="cursor: pointer;" /> <input type="button"
   id="resetForm" value="초기화" class="lwhite" style="cursor: pointer;" /> -->
 </div>
 <br />
 <!-- 검색 -->
 <table class="border01" style="width: 900px">
  <tr>
   <th scope="col">선택</th>
   <th>예외처리</th>
   <th width="20px;">허가구분</th>
   <th>로깅구분</th>
   <th>사유</th>
  </tr>

  <tr>
   <td><input type="checkbox" name="chk_each" style="cursor: pointer;" id="chk1" /></td>
   <td id="exceptionName1" name="SetPFWSTORERULE_REMOVALDRIVE">이동식저장장치</td>
   <td><select style="width:120px;" id="gubunAllow1">
     <option value="A">Read/Write</option>
     <option value="R">Read Only</option>
     <option value="B">block</option>
   </select></td>
   <td><select id="gubunLog1">
     <option value="L">로깅</option>
     <option value="N">미로깅</option>
   </select></td>
   <td><input type="text" id="reason1"></td>
  </tr>
  <tr>
   <td><input type="checkbox" name="chk_each" style="cursor: pointer;" id="chk2" /></td>
   <td id="exceptionName2" name="SetPFWSTORERULE_FDD">FDD</td>
   <td><select style="width:120px;" id="gubunAllow2">
     <option value="A">Read/Write</option>
     <option value="R">Read Only</option>
     <option value="B">block</option>
   </select></td>
   <td><select id="gubunLog2">
     <option value="L">로깅</option>
     <option value="N">미로깅</option>
   </select></td>
   <td><input type="text" id="reason2"></td>
  </tr>
  <tr>
   <td><input type="checkbox" name="chk_each" style="cursor: pointer;" id="chk3" /></td>
   <td id="exceptionName3" name="SetPFWSTORERULE_CDRW">CD/DVD-RW 예외처리</td>
   <td><select style="width:120px;" id="gubunAllow3">
     <option value="A">Read/Write</option>
     <option value="R">Read Only</option>
     <option value="B">block</option>
   </select></td>
   <td><select id="gubunLog3">
     <option value="L">로깅</option>
     <option value="N">미로깅</option>
   </select></td>
   <td><input type="text" id="reason3"></td>
  </tr>
  <tr>
   <td><input type="checkbox" name="chk_each" style="cursor: pointer;" id="chk4" /></td>
   <td id="exceptionName4" name="SetPCHDDEXCEPTION">추가 HDD장착</td>
   <td><select style="width:120px;" id="gubunAllow4">
     <option value="0">사용허가</option>
     <option value="1">사용통제</option>
   </select></td>
   <td >N/A</td>
   <td><input type="text" id="reason4"></td>
  </tr>
  <tr>
   <td><input type="checkbox" name="chk_each" style="cursor: pointer;" id="chk5" /></td>
   <td id="exceptionName5" name="SetPCEXCEPTION_W">무선랜</td>
   <td><select style="width:120px;" id="gubunAllow5">
     <option value="0">사용허가</option>
     <option value="1">사용통제</option>
   </select></td>
   <td>N/A</td>
   <td><input type="text" id="reason5"></td>
  </tr>
  <tr>
   <td><input type="checkbox" name="chk_each" style="cursor: pointer;" id="chk6" /></td>
   <td id="exceptionName6" name="SetPCEXCEPTION_U">USB모뎀(EVDO/HSDPA)</td>
   <td><select style="width:120px;" id="gubunAllow6">
     <option>사내:사용허가</option>
     <option>사내:사용통제</option>
   </select> <select style="width:120px;">
     <option>사외:사용허가</option>
     <option>사외:사용통제</option>
   </select></td>
   <td>N/A</td>
   <td><input type="text" id="reason6"></td>
  </tr>
  <tr>
   <td><input type="checkbox" name="chk_each" style="cursor: pointer;" id="chk7" /></td>
   <td id="exceptionName7" name="SetPCEXCEPTION_R">와이브로</td>
   <td><select style="width:120px;" id="gubunAllow7">
     <option>사내:사용허가</option>
     <option>사내:사용통제</option>
   </select> <select style="width:120px;">
     <option>사외:사용허가</option>
     <option>사외:사용통제</option>
   </select></td>
   <td>N/A</td>
   <td><input type="text" id="reason7"></td>
  </tr>
  <tr>
   <td><input type="checkbox" name="chk_each" style="cursor: pointer;" id="chk8" /></td>
   <td id="exceptionName8" name="SetPFWSTORERULE_FDD">등록 AP</td>
   <td><select style="width:120px;" id="gubunAllow8">
     <option>허용+차단AP목록</option>
     <option>차단+허용AP목록</option>
     <option>허용</option>
     <option>차단</option>
   </select></td>
   <td><select id="gubunLog8">
     <option value="L">로깅</option>
     <option value="N">미로깅</option>
   </select></td>
   <td><input type="text" id="reason8"></td>
  </tr>
<!--   <tr> -->
<!--    <td><input type="checkbox" name="chk_each" style="cursor: pointer;" id="chk9" /></td> -->
<!--    <td id="exceptionName2" name="SetPFWSTORERULE_FDD">반출잠김</td> -->
<!--    <td></td> -->
<!--    <td></td> -->
<!--    <td><input type="text"></td> -->
<!--   </tr> -->
<!--   <tr> -->
<!--    <td><input type="checkbox" name="chk_each" style="cursor: pointer;" id="chk10" /></td> -->
<!--    <td id="exceptionName2" name="SetPFWSTORERULE_FDD">스마트폰</td> -->
<!--    <td><select style="width:120px;" class="value1"> -->
<!--      <option value="0">사용허가</option> -->
<!--      <option value="1">사용통제</option> -->
<!--    </select></td> -->
<!--    <td>N/A</td> -->
<!--    <td><input type="text"></td> -->
<!--   </tr> -->
  <tr>
   <td><input type="checkbox" name="chk_each" style="cursor: pointer;" id="chk11" /></td>
   <td id="exceptionName11" name="SetPCEXCEPTION_T">블루투스</td>
   <td><select style="width:120px;" id="gubunAllow11">
     <option value="0">사용허가</option>
     <option value="1">사용통제</option>
   </select></td>
   <td>N/A</td>
   <td><input type="text" id="reason11"></td>
  </tr>
<!--   <tr> -->
<!--    <td><input type="checkbox" name="chk_each" style="cursor: pointer;" id="chk12" /></td> -->
<!--    <td id="exceptionName2" name="SetPFWSTORERULE_FDD">장치 프로그램 통제</td> -->
<!--    <td><select style="width:120px;"> -->
<!--      <option>사용허가</option> -->
<!--      <option>사용통제</option> -->
<!--    </select></td> -->
<!--    <td>N/A</td> -->
<!--    <td><input type="text"></td> -->
<!--   </tr> -->
<!--   <tr> -->
<!--    <td><input type="checkbox" name="chk_each" style="cursor: pointer;" id="chk13"/></td> -->
<!--    <td id="exceptionName2" name="SetPFWSTORERULE_FDD">USB 등록</td> -->
<!--    <td><select style="width:120px;" class="value1"> -->
<!--      <option value="0">사용허가</option> -->
<!--      <option value="1">사용통제</option> -->
<!--    </select></td> -->
<!--    <td><select> -->
<!--      <option value="L">로깅</option> -->
<!--      <option value="N">미로깅</option> -->
<!--    </select></td> -->
<!--    <td><input type="text"></td> -->
<!--   </tr> -->
<!--   <tr> -->
<!--    <td><input type="checkbox" name="chk_each" style="cursor: pointer;" id="chk14" /></td> -->
<!--    <td id="exceptionName2" name="SetPFWSTORERULE_FDD">일반 프로그램 통제</td> -->
<!--    <td><select style="width:120px;" class="value1"> -->
<!--      <option value="0">사용허가</option> -->
<!--      <option value="1">사용통제</option> -->
<!--    </select></td> -->
<!--    <td>N/A</td> -->
<!--    <td><input type="text"></td> -->
<!--   </tr> -->
<!--   <tr> -->
<!--    <td><input type="checkbox" name="chk_each" style="cursor: pointer;" id="chk15" /></td> -->
<!--    <td id="exceptionName2" name="SetPFWSTORERULE_FDD">파일 첨부</td> -->
<!--    <td><select style="width:120px;"> -->
<!--      <option>첨부허가</option> -->
<!--      <option>첨부통제</option> -->
<!--    </select></td> -->
<!--    <td><select> -->
<!--      <option value="L">로깅</option> -->
<!--      <option value="N">미로깅</option> -->
<!--    </select></td> -->
<!--    <td><input type="text"></td> -->
<!--   </tr> -->
 </table>



 <table class="noborder" style="width: 900px">
  <tr>
   <td></td>
   <td align="right"><input type="button" name="" value="확인" class="lwhite" style="cursor: pointer;" /></td>
  </tr>
 </table>
</div>
</form:form>
<!-- CONTENT END -->