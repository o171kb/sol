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
      if(checkMessage == "success_mod"){
        $.prompt("수정 완료 되었습니다.", {
              buttons: { "확인": true },
              submit: function(e,v,m,f){
                if (v) {
                    $("#checkMessage").val("");
                    location.href = "<c:url value='/adExctDraftModifyList.do'/>";
                }
              }
            });
      }
    if($("#addProofYn").val() == "Y"){
      $("#addProofUseYn").show();
    }
      /*수정*/
      $("#btn_modify").click(function(){
      if($("input:checkbox[name='chk_each']").is(":checked") == true){
        $("#addProofYn").val("Y");
      }else{
        $("#addProofYn").val("N");
      }
        $.prompt("수정 하시겠습니까?", {
          buttons: { "예": true, "아니오": false },
          submit: function(e,v,m,f){
            if (v) {
              $("#checkMessage").val("success_mod");
              $("#exctForm").attr("action","<c:url value='/adExctDraftModify.do'/>");
              $("#exctForm").submit();
            }
          }
        });
        return false;
      });
      /* cancle function */
    $("#btn_cancle").click(function(){
      location.href = "<c:url value='/adExctDraftModifyList.do'/>";
      return false;
    });
      
    /* 권한처리 */
    <c:if test="${mnuLvlCd != 'write'}">
    		$("#btn_modify").attr("disabled", "disabled").attr("style", "color:#ABABAB");
    		//$("input:checkbox[name='chk_each']").attr("disabled", true);
    		$("input:text").attr("disabled", true);
    		$("input:checkbox").attr("disabled", true);
    		$("textarea").attr("disabled", true);
    	   	//$(".board_detail").unbind();
    </c:if>


  });
</script>
<!-- HEADER START -->
<h3>예외 &gt; 예외신청문구수정</h3>
<!-- HEADER AND -->

<!-- CONTENT START -->
<form:form commandName="voExct" id="exctForm" name="exctForm" method="post" >
<input type="hidden" id="exctAppId" name="exctAppId" value="${exctDraftDtl.exctAppId}">
<input type="hidden" id="addProofYn" name="addProofYn" value="${exctDraftDtl.addProofYn}">
<input type="hidden" id="checkMessage" name="checkMessage" value="${exctDraftDtl.checkMessage}"/>
 <!-- 검색 -->
 <!-- 검색 -->
 <table class="tbws1">
   <tr>
    <th width="150px;">예외신청구분</th>
    <td colspan="2" style="text-align:left;">${exctDraftDtl.exctAppNm}</td>
<%--     <td></td> --%>
   </tr>
   <tr>
    <th>신청사유기본문구</th>
    <td colspan="2" style="text-align:left;"><input type="text" id="exctBasicTerms" name="exctBasicTerms" value="${exctDraftDtl.exctBasicTerms}"></td>
   </tr>
   <tr>
    <th  rowspan="2">신청기간설정<p> 및 안내문구</th>
    <th width="150px;">최대신청기간</th>
    <td style="text-align:left;"><input type="text" id="maxAppTerm" name="maxAppTerm" value="${exctDraftDtl.maxAppTerm}" style="text-align:right;width:50px;"> 개월<label class="error" name="error" for="maxAppTerm" generated="true" style="display:none;">에러메시지</label></td>
   </tr>
   <tr>
    <th>신청기간안내문구</th>
    <td style="text-align:left;"><input type="text" id="appTermEx" name="appTermEx" value="${exctDraftDtl.appTermEx}"></td>
   </tr>
   <tr style="display:none;">
    <th>증빙서류첨부</th>
    <td style="text-align:left; height:30px;">
    <input type="hidden" id="chk_use" name="chk_each" style="cursor: pointer;" exctId = "${exctDraftDtl.exctAppId}" <c:if test="${exctDraftDtl.addProofYn == 'Y'}">checked</c:if>  />사용
    <span id="addProofUseYn" ><input type="text" id="addProofMonth" name="addProofMonth" value=""  style="text-align:right;width:50px;"> 개월<input type="text" id="addProofDay" name="addProofDay" value=""  style="text-align:right;width:50px;"> 일</span>
    <label class="error" name="error" for="addProofMonth" generated="true" style="display:none;">에러메시지</label>
    <label class="error" name="error" for="addProofDay" generated="true" style="display:none;">에러메시지</label>
    </td>
   </tr>
   <tr>
    <th>상단안내문구</th>
    <td colspan="2" style="text-align:left;"><textarea id="topTerms" name="topTerms" style="width:400px; height:100px;">${exctDraftDtl.topTerms}</textarea></td>
   </tr>
   <tr>
   <th>결재안내문구</th>
   <td colspan="2" style="text-align:left;"><textarea id="permitTerms" name="permitTerms" style="width:400px; height:100px;">${exctDraftDtl.permitTerms}</textarea></td>
   </tr>
   <tr>
   <th>하단안내문구</th>
   <td colspan="2" style="text-align:left;"><textarea id="bottomTerms" name="bottomTerms"  style="width:400px; height:100px;">${exctDraftDtl.bottomTerms}</textarea></td>
   </tr>
 </table>
<div class="btn_area" style="margin-top:20px;">
 <div class="right" style="margin:0px 3px 0px 3px"><input type="button" id="btn_cancle" class="nwhite" value="취소"/></div>
 <div class="right" style="margin:0px 3px 0px 3px"><input type="button" id="btn_modify" class="nwhite" value="수정"/></div>
</div>
</form:form>
<!-- CONTENT END -->