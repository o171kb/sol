<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<c:url value='/dwr/interface/dwrDeptCdManagement.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/chkValidate.js'/>"></script>
<style type="text/css"> label.error{ display:block; color:red; } </style>
<script type="text/javaScript" src="/admin/sitemap/common/OnetimeSubmit.js"></script>
<script type="text/javaScript" defer="defer">

  $(document).ready(function(){

      //메뉴설정
      openMenu('01');

      //액션완료 메시지 띄우기
      var checkMessage = $("#checkMessage").val();
      if(checkMessage == "success_reg"){
          $.prompt("등록 완료 되었습니다.", {
            buttons: { "확인": true },
            submit: function(e,v,m,f){
              if (v) {
                  $("#checkMessage").val("");
                  location.href = "<c:url value='/adDeptSearchList.do'/>";
              }
            }
          });
      }

      /*추가*/
      $("#btn_regist").click(function(){
        $(".chkId").hide();
        $(".chkId").html("");
        $.prompt("등록 하시겠습니까?", {
          buttons: { "예": true, "아니오": false },
          submit: function(e,v,m,f){
            if (v) {
              var deptCd = $("#deptCd").val();
              var highDeptNm = $("#highDeptNm").val();
              if(deptCd == null || deptCd == ""){
                  $(".chkId").html("*부서코드를 입력해주세요.");
                  $(".chkId").show();
                  $.prompt.close(true);
                  return false;
                };
              if( $("#deptCd").val() != $("#compairId").val()){
                 $(".chkId").html("*부서코드 중복검사를 해주세요.");
                 $(".chkId").show();
                 $.prompt.close(true);
                 return false;
               }
              if(highDeptNm == null || highDeptNm == ""){
                  $(".chkHighDeptNm").html("*상위부서를 입력해주세요.");
                  $(".chkHighDeptNm").show();
                  $.prompt.close(true);
                  return false;
               };
              $("#checkMessage").val("success_reg");
              $("#deptForm").attr("action","<c:url value='/adDeptRegist.do'/>");
              $("#deptForm").submit();
            }
          }
        });
        return false;
      });

      /*목록*/
      $("#btn_cancel").click(function(){
        $("#checkMessage").val("");
        location.href = "<c:url value='/adDeptSearchList.do'/>";
        return false;
      });

      /*아이디 중복 체크*/
      $("#id_check").click(function(){
        $(".chkId").html("");
        $(".chkId").hide("");
//         if ($(".error").css("display") !="none"){
//           return false;
//         }
        var deptCd = $("#deptCd").val();
        var userDeptIdLength = $("#deptCd").val().length;
        var entity = {
            deptCd:deptCd
        };
        if(deptCd == null || deptCd == ""){
           $(".chkId").html("*부서코드를 입력해주세요.");
           $(".chkId").show();
           return false;
         };
         if(userDeptIdLength > 10){
           $(".chkId").html("*부서코드는 10자 이하로 입력해주세요.");
           $(".chkId").show();
           return false;
         }
        dwrDeptCdManagement.checkDeptCd(entity, function appendFormList(data){
        $("#checkResult").val(data);
          if(data == 1) {
              var compairId = $("#deptCd").val();
              $(".chkId").show();
              $(".chkId").html("<font style='color:black;'>*등록 할 수 있는 부서코드 입니다.</font>");
              $("#checkConfirm").val('1');
              $("#compairId").val(compairId);
              chkValidateGrpId = 1;
              return false;
          } else if(data == -1) {
              $(".chkId").html("*이미사용중인 부서코드입니다.");
              $(".chkId").show();
              chkValidateGrpId = 0;
              return false;
          }
        });
      });

  });

  //상위부서선택에서 불려지는 함수
  function setParentDeptParams(deptcd,deptnm){
    $('#highDeptCd').val(deptcd);
    $('#highDeptNm').val(deptnm);
  };
</script>

<!-- HEADER START -->
<h3>시스템 &gt; 부서관리 &gt; 등록</h3>
<!-- HEADER AND -->


<!-- CONTENT START -->
<div class="img_va"><img src="<c:url value="/image/admin/btn_right.gif"/>" />등록</div>
<form:form commandName="voCoDept" id="deptForm" name="deptForm" method="post" onsubmit="return onetimeSubmit(this)">
  <input type="hidden" id="deptNo" name="deptNo" value="${voCoDept.deptNo}">
  <input type="hidden" id="highDeptCd" name="highDeptCd">
  <input type="hidden" id="checkMessage" name="checkMessage" value="${voCoDept.checkMessage}"/>
  <input type="hidden" id="compairId" name="compairId" value=""/>

    <!-- 검색 -->
    <table class="tbws1">
      <tr>
        <th style="width:100px;">부서코드<em class="red">*</em></th>
        <td class="align_l" style="width:40%;">
          <form:input type="text" path="deptCd" />
          <input type="button"  style="text-align:center;" value="중복검사" id="id_check" class="swhite"><p>
<!--           <label class="error" name="error" for="userId" generated="true" style="display:none;">에러메시지</label> -->
          <span class="chkId" style="color:red; display:none;"></span>
        </td>
        <th style="width:100px;">상위부서<em class="red">*</em></th>
        <td class="align_l">
          <input type="text" id="highDeptNm" name="highDeptNm" readonly="readonly"  style="width:200px;">
          <input type="button"  style="text-align:center;" value="조회" id="selectDept_bt" class="swhite" ><p>
<!--           <label class="error" name="error" for="highDeptNm" generated="true" style="display:none;">에러메시지</label> -->
          <span class="chkHighDeptNm" style="color:red; display:none;"></span>
        </td>
      </tr>
      <tr>
        <th>부서명<em class="red">*</em></th>
        <td colspan="4" class="align_l"><form:input path="deptNm" type="text" value="${voCoDept.deptNm}" style="width:300px;"/></td>
      </tr>
      <tr>
        <th>부서영문명</th>
        <td colspan="4" class="align_l"><form:input path="deptNmEn" type="text" value="${voCoDept.deptNmEn}" style="width:300px;"/></td>
      </tr>
      <tr>
        <th>부서설명</th>
        <td colspan="4" class="align_l"><form:input path="deptExp" type="text" value="${voCoDept.deptExp}" style="width:300px;"/></td>
      </tr>
    </table>
    <div class="btn_area">
      <div class="right" style="margin:0px 3px 0px 3px"><input type="button" id="btn_cancel" class="nwhite" value="취소"/></div>
      <div class="right" style="margin:0px 3px 0px 3px"><input type="button" id="btn_regist" class="nwhite" value="등록"/></div>
    </div>
</form:form>
<!-- CONTENT END -->

<div class="mskbg" id="pop_bg" style="display:none;"><div class="msk"></div></div> <!-- 흰색 마스크 레이어 display로 제어-->

<%//부서선택%>
<jsp:include page="../../popup/selectDeptAll.jsp" />