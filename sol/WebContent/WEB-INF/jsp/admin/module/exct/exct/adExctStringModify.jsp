<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<c:url value='/js/chkValidate.js'/>"></script>
<style type="text/css">
  label.error{
    display:block;
    color:red;
  }
</style>
<script type="text/javaScript" language="javascript" defer="defer">
  $(document).ready(function(){
    openMenu('01');
      //액션완료 메시지 띄우기
      var checkMessage = $("#checkMessage").val();
      if(checkMessage == "success"){
        $.prompt("수정 완료 되었습니다.", {
              buttons: { "확인": true },
              submit: function(e,v,m,f){
                if (v) {
                    $("#checkMessage").val("");
                    location.href = "<c:url value='/adExctStringModifyList.do'/>";
                }
              }
            });
      }

      /*수정*/
      $(".nwhite").click(function(){
      if($("input:checkbox[name='chk_each']").is(":checked") == true){
        $("#useYn").val("Y");
      }else{
        $("#useYn").val("N");
      }

        $.prompt("수정 하시겠습니까?", {
          buttons: { "예": true, "아니오": false },
          submit: function(e,v,m,f){
            if (v) {
              $("#checkMessage").val("success");
              $("#exctForm").attr("action","<c:url value='/adExctStringModify.do'/>");
              $("#exctForm").submit();
            }
          }
        });
        return false;
      });
  });
</script>
<!-- HEADER START -->
<h3>예외 &gt; 예외용어문구수정</h3>
<!-- HEADER AND -->

<!-- CONTENT START -->
<form:form commandName="voExct" id="exctForm" name="exctForm" method="post" >
<input type="hidden" id="exctId" name="exctId" value="${exctList.exctId}">
<input type="hidden" id="useYn" name="useYn" value="${exctList.useYn}">
<input type="hidden" id="checkMessage" name="checkMessage" value="${exctList.checkMessage}"/>
 <!-- 검색 -->
 <!-- 검색 -->
 <table class="tbws1" >
  <tr>
   <th scope="col" style="width:35%">예외명</th>
   <th scope="col">상세설명</th>
   <th scope="col" style="width:7%">사용</th>
  </tr>
   <tr>
    <td><input type="text" id="exctNm" name="exctNm" value="${exctList.exctNm}" style="width:200px;"></td>
    <td><input type="text" id="exctDtl" name="exctDtl" value="${exctList.exctDtl}" style="width:400px;"></td>
    <td><input type="checkbox" name="chk_each" style="cursor: pointer;" exctId = "${exctList.exctId}" <c:if test="${exctList.useYn == 'Y'}">checked</c:if>/></td>
   </tr>
 </table>
<div class="btn_area" style="margin-top:20px;">
 <div class="right" style="margin:0px 3px 0px 3px"><input type="button" class="nwhite" value="수정"/></div>
</div>
</form:form>
<!-- CONTENT END -->