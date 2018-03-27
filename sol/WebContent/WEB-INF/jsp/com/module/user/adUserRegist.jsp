<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<c:url value='/dwr/interface/dwrUserManagement.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/chkValidate.js'/>"></script>
<style type="text/css"> label.error{ display:block; color:red; } </style>
<script type="text/javaScript" src="/admin/sitemap/common/OnetimeSubmit.js"></script>
<script type="text/javascript" src="<c:url value='/module/encrypt/sha512.js'/>"></script>

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
                  location.href = "<c:url value='/adUserSearchList.do'/>";
              }
            }
          });
      }

      /*추가*/
      $("#btn_regist").click(function(){

        $.prompt("등록 하시겠습니까?", {
          buttons: { "예": true, "아니오": false },
          submit: function(e,v,m,f){
            if (v) {
              if($("#userId").val() == null || $("#userId").val() == ""){
                  $(".chkId").html("*사번을 입력해주세요.");
                  $(".chkId").show();
                  $.prompt.close(true);
                  return false;
                };
              if( $("#userId").val() != $("#compairId").val()){
                  $(".chkId").html("*부서코드 중복검사를 해주세요.");
                  $(".chkId").show();
                  $.prompt.close(true);
                  return false;
              }
              if($("#deptNm").val() == null || $("#deptNm").val() == ""){
                  $(".deptCd").html("*부서를 입력해주세요.");
                  $(".deptCd").show();
                  $.prompt.close(true);
                  return false;
              };
              if($("#userGrpNo").val() == null || $("#userGrpNo").val() == ""){
                  $(".deptGrpNm").html("*사용자그룹을 입력해주세요.");
                  $(".deptGrpNm").show();
                  $.prompt.close(true);
                  return false;
                };
              if($("#password1").val() != ""){
                var pwd = $.trim($("#password1").val());
                pwd = hex_sha512(pwd);
                $("#userPwd").val(pwd);
              }
              $("#checkMessage").val("success_reg");
              $("#userForm").attr("action","<c:url value='/adUserRegist.do'/>");
              $("#userForm").submit();
            }
          }
        });
        return false;
      });

      /*목록*/
      $("#btn_cancel").click(function(){
        $("#checkMessage").val("");
        location.href = "<c:url value='/adUserSearchList.do'/>";
        return false;
      });

      /*사번 중복 체크*/
      $("#id_check").click(function(){
        $(".chkId").html("");
//         if ($(".error").css("display") !="none"){
//           return false;
//         }
        var userId = $("#userId").val();
        var userIdLength = $("#userId").val().length;
        if(userId == null || userId == ""){
           $(".chkId").html("*사번을 입력해주세요.");
           $(".chkId").show();
           return false;
         };
         if(userIdLength > 10){
           $(".chkId").html("*사번은 10자 이하로 입력해주세요.");
           $(".chkId").show();
           return false;
         }
        var entity = {
            userId:userId
        };
        dwrUserManagement.checkUserId(entity, function appendFormList(data){
        $("#checkResult").val(data);
        if(data == 1) {
           var compairId = $("#userId").val();
           $(".chkId").html("<font style='color:black;'>*등록 할 수 있는 사번 입니다.</font>");
           $(".chkId").show();
           $("#checkConfirm").val('1');
           $("#compairId").val(compairId);
           return false;
        } else if(data == -1) {
          $(".chkId").html("*이미사용중인 사번입니다.");
          $(".chkId").show();
          return false;
        }
        });
     });


    $(".proxyApprYn").click(function(){
      var chk = $(this).attr("checked");
      if(chk == true){
         $(".proxyApprYn").attr("checked", true);
         $("#proxyApprYn").val("1");
      }else if(chk == false){
        $(".proxyApprYn").attr("checked", false);
        $("#proxyApprYn").val("0");
      }
    });

  });

  //상위부서선택(팝업)에서 불려지는 함수
  function setParentDeptParams(deptcd,deptnm,deptno){
    $('#deptCd').val(deptcd);
    $('#deptNm').val(deptnm);
    $('#deptNo').val(deptno);
  };

  //사용자그룹선택(팝업)에서 불려지는 함수
  function setParentUserGrpParams(userGrpNo,grpNm,apprYn){
    $('#userGrpNo').val(userGrpNo);
    $('#grpNm').val(grpNm);

    //대행결재자란 표시제어
    if(apprYn == '1'){
        $('#proxyApprIdTh').show();
        $('#proxyApprIdTd').show();
        $("#userNmTd").attr('colspan','1');
    }else{
        $("#userNmTd").attr('colspan','4');
        $('#proxyApprIdTh').css('display','none');
        $('#proxyApprIdTd').css('display','none');
    }
  }

  //대행결재자선택(팝업)에서 불려지는 함수
  function setProxyUserParams(userId, userNm){
    $('#proxyApprId').val(userId);
    $('#proxyApprNm').val(userNm);
  };

</script>

<!-- HEADER START -->
<h3>시스템 &gt; 사용자관리 &gt; 등록</h3>
<!-- HEADER AND -->

<!-- CONTENT START -->
<div class="img_va"><img src="<c:url value="/image/admin/btn_right.gif"/>" />사용자등록정보</div>
<form:form commandName="voCoUser" id="userForm" name="userForm" method="post" onsubmit="return onetimeSubmit(this)">
  <input type="hidden" id="highDeptCd" name="highDeptCd">
  <input type="hidden" id="checkMessage" name="checkMessage" value="${voCoUser.checkMessage}"/>
  <input type="hidden" id="userPwd" name="userPwd" />
  <input type="hidden" id="compairId" name="compairId" value=""/>
    <!-- 검색 -->
    <table class="tbws1">
      <tr>
        <th style="width:10%;">사번<em class="red">*</em></th>
        <td class="align_l" style="width:40%;">
          <form:input type="text" path="userId" />
          <input type="button"  style="text-align:center;" value="중복검사" id="id_check" class="swhite"><p>
<!--           <label class="error" name="error" for="userId" generated="true" style="display:none;">에러메시지</label> -->
          <span class="chkId" style="color:red; display:none;"></span>
        </td>
        <th style="width:10%;">부서<em class="red">*</em></th>
        <td class="align_l">
          <input type="hidden" id="deptCd" name="deptCd">
          <input type="hidden" id="deptNo" name="deptNo">
          <input type="text" id="deptNm" name="deptNm" readonly="readonly" >
          <input type="button"  style="text-align:center;" value="검색" id="selectDept_bt" class="swhite" ><p>
<!--           <label class="error" name="error" for="highDeptCd" generated="true" style="display:none;">에러메시지</label> -->
          <span class="deptCd" style="color:red; display:none;"></span>
        </td>
      </tr>
      <tr>
        <th>사용자그룹<em class="red">*</em></th>
        <td class="align_l">
          <input type="hidden" id="userGrpNo" name="userGrpNo" value="${voCoUser.userGrpNo}" />
          <input type="text" id="grpNm" name="grpNm" value="${voCoUser.grpNm}" readonly="readonly" />
          <input type="button"  style="text-align:center;" value="검색" id="selectUserGrp_bt" class="swhite" /><p>
          <span class="deptGrpNm" style="color:red; display:none;"></span>
        </td>
        <th>직급 <em class="red">*</em></th>
        <td class="align_l">
          <%-- <input type="text" id="userPosition" name="userPosition" value="${voCoUser.userPosition}" /> --%>
          <select name="userPosition" id="userPosition" class="userPosition">
			<option value="">선택</option>
			<c:forEach var="medCode" items="${tt:getCodeValues('010')}">
				<option value="${medCode[0]}"<c:if test="${voCoUser.userPosition == medCode[0]}">selected</c:if>>${medCode[1]}</option>
			</c:forEach>
		 </select>
        </td>
      </tr>
      <tr>
        <th>성명<em class="red">*</em></th>
        <td id="userNmTd" colspan="3" class="align_l"><form:input path="userNm" id="userNm" name="userNm"  type="text" value="${voCoUser.userNm}" style="width:300px;"/></td>
        <th id="proxyApprIdTh" style="width:100px; display:none;" >대행결재자<em class="red">*</em></th>
        <td id="proxyApprIdTd" class="align_l" style="display:none;" >
          <input type="hidden" id="proxyApprId" name="proxyApprId">
          <input type="text" id="proxyApprNm" readonly="readonly" >
          <input type="hidden" id="proxyApprYn" name="proxyApprYn" value="0"/>
          <input type="button"  style="text-align:center;" value="조회" id="selectApprDeptUser_bt" class="swhite" >&nbsp;&nbsp;
          <input type="checkbox" class="proxyApprYn" <c:if test="${voCoUser.proxyApprYn eq '1'}">checked</c:if>/>&nbsp;&nbsp;사용
        </td>
      </tr>
      <tr>
        <th>e-mail<em class="red">*</em></th>
        <td colspan="4" class="align_l"><form:input path="userEmail" id="userEmail" name="userEmail" type="text" value="${voCoUser.userEmail}" style="width:300px;"/></td>
      </tr>
      <tr>
        <th>전화번호</th>
        <td class="align_l"><form:input path="userTel" id="userTel" name="userTel" type="text" value="${voCoUser.userTel}" style="width:300px;"/></td>
        <th>휴대폰</th>
        <td class="align_l"><form:input path="userMobile" id="userMobile" name="userMobile" type="text" value="${voCoUser.userMobile}" style="width:300px;"/></td>
      </tr>
      <tr>
        <th style="height:50px">비밀번호 <em class="red">*</em></th>
        <td colspan="4" class="align_l" heigth="20px"><input id="password1" name="password1" type="password" style="width:300px;"/>
          <div>※  영문 , 숫자로 8~20자리이어야 합니다.</div></td>
      </tr>
      <tr>
        <th>비밀번호확인<em class="red">*</em></th>
        <td colspan="4" class="align_l"><input id="password2" name="password2" type="password" style="width:300px;"/></td>
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
<%//그룹선택%>
<jsp:include page="../../popup/selectUserGroup.jsp" />
<%//대행결재자선택%>
<%-- <jsp:include page="../../popup/selectApprUser.jsp" /> --%>