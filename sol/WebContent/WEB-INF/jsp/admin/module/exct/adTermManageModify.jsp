<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javaScript" language="javascript" defer="defer">
  $(document).ready(function(){

$(".lwhite").click(function(){
  if($("input:checkbox[name='chk_each']").is(":checked") == true){
    $("#useYn").val("Y");
  }else{
    $("#useYn").val("N");
  }
    $("#exctForm").attr("action","<c:url value='/adTermModifyAction.do'/>");
    $("#exctForm").submit();
    return false;
});
  });
</script>
<!-- HEADER START -->
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="text1">
 <tr>
  <td width="100%" height="10" align="RIGHT" bgcolor="#ffff00"></td>
 </tr>
 <tr>
  <td height="30" valign="BOTTOM" class="text2">&nbsp;&nbsp;<b>용어관리 수정</b></td>
 </tr>
 <tr>
  <td height="10"><br />
  <br />
  <br /></td>
 </tr>
</table>
<!-- HEADER AND -->

<!-- CONTENT START -->
<form:form commandName="voExct" id="exctForm" name="exctForm" method="post" >
<input type="hidden" id="exctId" name="exctId" value="${exctList.exctId}">
<input type="hidden" id="useYn" name="useYn" value="${exctList.useYn}">
<div style="margin: 0px 20px; width: 900px">
 <!-- 검색 -->
 <!-- 검색 -->
 <table class="border01" style="width: 900px">
  <tr>
   <th scope="col" width="300px;">예외명</th>
   <th scope="col">상세설명</th>
   <th scope="col" width="30px;">사용</th>
  </tr>
   <tr>
    <td><input type="text" id="exctNm" name="exctNm" value="${exctList.exctNm}"></td>
    <td><input type="text" id="exctDtl" name="exctDtl" value="${exctList.exctDtl}"></td>
    <td><input type="checkbox" name="chk_each" style="cursor: pointer;" exctId = "${exctList.exctId}" <c:if test="${exctList.useYn == 'Y'}">checked</c:if>/></td>
   </tr>
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