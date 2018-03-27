<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javaScript" src="/admin/sitemap/common/OnetimeSubmit.js"></script>
<script type="text/javascript" src="<c:url value='/module/encrypt/sha512.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/chkValidate.js'/>"></script>
<style type="text/css"> label.error{ display:block; color:red; } </style>
<script type="text/javaScript" defer="defer">

  $(document).ready(function(){
    //메뉴설정
    openMenu('05');

    //액션완료 메시지 띄우기
      var checkMessage = $("#checkMessage").val();
      if(checkMessage == "success_mod"){
          $.prompt("수정 완료 되었습니다.", {
            buttons: { "확인": true },
            submit: function(e,v,m,f){
              if (v) {
                  $("#checkMessage").val("");
                  location.href = "<c:url value='/adMyPage.do'/>";

              }
            }
          });
      }

      /*수정*/
      $("#btn_modify").click(function(){

        $.prompt("수정 하시겠습니까?", {
          buttons: { "예": true, "아니오": false },
          submit: function(e,v,m,f){
            if (v) {
              if($("#password1").val() == "" && $("#password2").val() == ""){
                $(".pwdDel").removeAttr("name");
              }
              if($("#password1").val() != ""){
                $("#password1").attr("name", "password1");
                $("#password2").attr("name", "password2");
                var pwd = $.trim($("#password1").val());
                pwd = hex_sha512(pwd);
                $("#userPwd").val(pwd);
              }
              
              $("#checkMessage").val("success_mod");
              $("#userForm").attr("action","<c:url value='/adMyPage.do'/>");
              $("#userForm").submit();
            }
          }
        });
        return false;
      });

      var apprYn = $("#apprYn").val();
      if(apprYn == '1'){
        $('#proxyApprIdTh').show();
        $('#proxyApprIdTd').show();
        $("#userNmTd").attr('colspan','1');
      }else{
          $("#userNmTd").attr('colspan','4');
          $('#proxyApprIdTh').css('display','none');
          $('#proxyApprIdTd').css('display','none');
      }

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


      /* 권한처리 */
      <c:if test="${mnuLvlCd != 'write'}">
      		$("#btn_modify").attr("disabled", "disabled");
      		$("input:button").attr("style", "color:#ABABAB");
      		$("input:text").attr("disabled", true);
      		$("input:checkbox").attr("disabled", true);
      		$("input:button").attr("disabled", true);
      		$("input:radio").attr("disabled", true);
      		$("select").attr("disabled", true);
      		$("textarea").attr("disabled", true);
      </c:if>   
            
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
<h3>MyPage &gt; 나의정보수정</h3>
<!-- HEADER AND -->
<!-- CONTENT START -->
<div class="img_va"><img src="<c:url value="/image/admin/btn_right.gif"/>" />사용자등록정보</div>
<form:form commandName="voCoUser" id="userForm" name="userForm" method="post" onsubmit="return onetimeSubmit(this)">
  <input type="hidden" id="highDeptCd" name="highDeptCd"/>
  <input type="hidden" id="userId" name="userId" value="${voCoUser.userId}"/>
  <input type="hidden" id="checkMessage" name="checkMessage" value="${voCoUser.checkMessage}"/>
  <input type="hidden" id="apprYn" name="apprYn" value="${voCoUser.apprYn}"/>
  <input type="hidden" id="userPwd" name="userPwd" />
    <!-- 검색 -->
    <table class="tbws1">
      <tr>
        <th style="width:10%;">사번<em class="red">*</em></th>
        <td class="align_l" style="width:40%;">
          <c:out value="${voCoUser.userId}"/>
        </td>
        <th style="width:10%;">부서<em class="red">*</em></th>
        <td class="align_l">
          <input type="hidden" id="deptCd" name="deptCd" value="${voCoUser.deptCd}">
          <input type="hidden" id="deptNo" name="deptNo" value="${voCoUser.deptNo}">
          <c:out value="${voCoUser.deptNm}" />
          <input type="hidden" id="deptNm" name="deptNm" value="${voCoUser.deptNm}" readonly="readonly" >
          <!-- <input type="button"  style="text-align:center;" value="call" id="selectDept_bt" class="swhite" > --><p>
          <label class="error" name="error" for="highDeptCd" generated="true" style="display:none;">에러메시지</label>
          <span class="deptCd" style="color:red;"></span>
        </td>
      </tr>
      <tr>
        <th>사용자그룹<em class="red">*</em></th>
        <td class="align_l">
          <input type="hidden" id="userGrpNo" name="userGrpNo" value="${voCoUser.userGrpNo}" />
          <c:out value="${voCoUser.grpNm}"/>
          <input type="hidden" id="grpNm" name="grpNm" value="${voCoUser.grpNm}" readonly="readonly" />
          <!-- <input type="button"  style="text-align:center;" value="call" id="selectUserGrp_bt" class="swhite" /><p> -->
        </td>
        <th>직급 <em class="red">*</em></th>
        <td class="align_l">
          <%-- <c:out value="${voCoUser.userPosition}"/>
          <input type="hidden" id="userPosition" name="userPosition" value="${voCoUser.userPosition}" /> --%>
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
        <td id="userNmTd" colspan="3" class="align_l">
        	<input type="hidden" id="userNm" name="userNm"  type="text" value="${voCoUser.userNm}" style="width:300px;"/>
        	<c:out value="${voCoUser.userNm}"/>
        </td>
        <th id="proxyApprIdTh" style="width:100px; display:none;" >대행결재자<em class="red">*</em></th>
        <td id="proxyApprIdTd" class="align_l" style="display:none;" >
          <input type="hidden" id="proxyApprId" name="proxyApprId" value="${voCoUser.proxyApprId}"/>
          <input type="text" id="proxyApprNm" readonly="readonly" value="${voCoUser.proxyApprNm}"/>
          <input type="button"  style="text-align:center;" value="조회" id="selectApprDeptUser_bt" class="swhite" >&nbsp;&nbsp;
          <input type="hidden" id="proxyApprYn" name="proxyApprYn" value="${voCoUser.proxyApprYn}"/>
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
        <td colspan="4" class="align_l" heigth="20px"><input class="pwdDel" id="password1" type="password" style="width:300px;"/>
          <div>※  영문 , 숫자를 조합하여 8~20자리이어야 합니다.</div></td>
      </tr>
      <tr>
        <th>비밀번호확인<em class="red">*</em></th>
        <td colspan="4" class="align_l"><input class="pwdDel" id="password2" type="password" style="width:300px;"/></td>
      </tr>
    </table>
    <div class="btn_area">
      <div class="right" style="margin:0px 3px 0px 3px"><input type="button" id="btn_modify" class="nwhite" value="수정"/></div>
    </div>
</form:form>
<!-- CONTENT END -->

<div class="mskbg" id="pop_bg" style="display:none;"><div class="msk"></div></div> <!-- 흰색 마스크 레이어 display로 제어-->

<%//부서선택%>
<jsp:include page="../popup/selectDeptAll.jsp" />
<%//그룹선택%>
<jsp:include page="../popup/selectUserGroup.jsp" />
<%//대행결재자선택%>
<%-- <jsp:include page="../../popup/selectApprUser.jsp" /> --%>
