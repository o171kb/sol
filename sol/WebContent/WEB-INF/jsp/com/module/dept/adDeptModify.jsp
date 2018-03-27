<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javaScript" src="/admin/sitemap/common/OnetimeSubmit.js"></script>
<script type="text/javascript" src="<c:url value='/js/chkValidate.js'/>"></script>
<style type="text/css"> label.error{ display:block; color:red; } </style>
<script type="text/javaScript" defer="defer">

  $(document).ready(function(){

      //메뉴설정
      openMenu('01');

      //액션완료 메시지 띄우기
      var checkMessage = $("#checkMessage").val();
      if(checkMessage == "success_mod"){
          $.prompt("수정 완료 되었습니다.", {
            buttons: { "확인": true },
            submit: function(e,v,m,f){
              if (v) {
                  $("#checkMessage").val("");
                  $("#deptForm").attr("action","<c:url value='/adDeptSearchList.do'/>");
                  $("#deptForm").submit();
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
              $("#checkMessage").val("success_mod");
              $("#deptForm").attr("action","<c:url value='/adDeptModify.do'/>");
              $("#deptForm").submit();
            }
          }
        });
        return false;
      });

      /*목록*/
      $("#btn_list").click(function(){
        $("#checkMessage").val("");
        location.href = "<c:url value='/adDeptSearchList.do'/>";
        return false;
      });
      
      /* 권한처리 */
      <c:if test="${mnuLvlCd != 'write'}">
      		$("#btn_modify").attr("disabled", "disabled").attr("style", "color:#ABABAB");
      		//$("#btn_modify_menu").attr("disabled", "disabled");
      		//$("input:checkbox[name='chk_each']").attr("disabled", true);
      		$("input:text").attr("disabled", true);
      		$("input:checkbox").attr("disabled", true);
      		$("textarea").attr("disabled", true);
      	   	//$("#btn_next").unbind();
      </c:if>
      

  });
</script>

<!-- HEADER START -->
<h3>시스템 &gt; 부서관리 &gt; 수정</h3>
<!-- HEADER AND -->

<!-- CONTENT START -->
<form:form commandName="voCoDept" id="deptForm" name="deptForm" method="post" onsubmit="return onetimeSubmit(this)">
  <input type="hidden" id="deptNo" name="deptNo" value="${voCoDept.deptNo}">
  <input type="hidden" id="checkMessage" name="checkMessage" value="${voCoDept.checkMessage}"/>

  <!-- 검색 -->
  <table class="tbws1">
    <tr>
      <th style="width:200px;">부서코드</th>
      <td class="align_l">${voCoDept.deptCd}</td>
    </tr>
    <tr>
      <th>부서명<em class="red">*</em></th>
      <td class="align_l"><form:input path="deptNm" type="text" value="${voCoDept.deptNm}" style="width:300px;"/></td>
    </tr>
    <tr>
      <th>부서영문명</th>
      <td class="align_l"><form:input path="deptNmEn" type="text" value="${voCoDept.deptNmEn}" style="width:300px;"/></td>
    </tr>
    <tr>
      <th>부서설명</th>
      <td class="align_l"><form:input path="deptExp" type="text" value="${voCoDept.deptExp}" style="width:300px;"/></td>
    </tr>
  </table>
  <div class="btn_area">
    <div class="right" style="margin:0px 3px 0px 3px"><input type="button" id="btn_list" class="nwhite" value="목록"/></div>
    <div class="right" style="margin:0px 3px 0px 3px"><input type="button" id="btn_modify" class="nwhite" value="수정"/></div>
  </div>

</form:form>
<!-- CONTENT END -->