<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javaScript" language="javascript" defer="defer">
$(document).ready(function(){
	
  openMenu('01');
   //액션완료 메시지 띄우기
   
    $("input:checkbox[name='chk_each']").each(function(){
    	if($(".board_detail").attr("checked") == true){
    		$(".board_detail").addClass('selectedRow2');
    	}
    });
   
    $('.tbws1 tr').mouseover(function(){
    	$(this).attr("class",'selectedRow2');
		//$(this).addClass('selectedRow2');

    }).mouseout(function() {
    	/* if($(this).attr("rowType") == "W")
    	{
       		$(this).attr('class', '');
    	}
    	else if($(this).attr("rowType") == "P")
   		{
    		$(this).attr('class', 'selectedRow3');
   		} */
    	$(this).attr("class",'');
    });
   
   var checkMessage = $("#checkMessage").val();
   if(checkMessage == "success"){
     $.prompt("수정 완료 되었습니다.",{
       buttons: { "예": true },
         submit: function(e,v,m,f){
           if (v) {
               $("#checkMessage").val("");
               $("#exctForm").attr("action","<c:url value='/adExctStringModifyList.do'/>");
               $("#exctForm").submit();
           }
         }
     });
   }

   $("#btn_upate").click(function(){
     var eachChk = "";
     var arrChk = "";
     var exctId = "";
     $("input:checkbox[name='chk_each']").each(function(){
       if(this.checked == true){
         eachChk = "Y";
       }else{
         eachChk = "N";
       }
    arrChk += eachChk + "," ;
    exctId = exctId + $(this).attr('exctId') + ",";
    $("#arrChk").val(arrChk);
    $("#exctId").val(exctId);
     });
     $.prompt("저장 하시겠습니까?", {
         buttons: { "예": true, "아니오": false },
         submit: function(e,v,m,f){
           if (v) {
             $("#checkMessage").val("success");
             $("#exctForm").attr("action","<c:url value='/adExctStringUseYn.do'/>");
             $("#exctForm").submit();
           }
         }
       });
     return false;
   });
  
   //삭제
   $(".delete").click(function(){
       var exctId = $(this).attr("keyName");
       $("#exctId").val(exctId);
       $("#exctForm").attr("action","<c:url value='/adExctStringDelete.do'/>");
       $("#exctForm").submit();
       return false;
     });
 	//추가
   $("#btn_insert").click(function(){
       var exctId = $(this).attr("keyName");
       $("#exctId").val(exctId);
       $("#exctForm").attr("action","<c:url value='/adExctStringInsert.do'/>");
       $("#exctForm").submit();
       return false;
     });
 	
   /* 권한처리 */
   <c:if test="${mnuLvlCd != 'write'}">
   		$("#btn_upate").attr("disabled", "disabled").attr("style", "color:#ABABAB");
   		$("input:checkbox[name='chk_each']").attr("disabled", true);
   	   	$(".board_detail").unbind();
   </c:if>

  
});
//수정페이지로 이동
	 function board_detail(exctId){
		$("#exctId").val(exctId);
		$("#exctForm").attr("action","<c:url value='/adExctStringModify.do'/>");
		$("#exctForm").submit();
		return false;
	}

</script>

<!-- HEADER START -->
<h3>예외 &gt; 예외용어문구수정</h3>
<!-- HEADER AND -->

<!-- CONTENT START -->
<form:form commandName="voExct" id="exctForm" name="exctForm" method="post" >
<input type="hidden" id="exctId" name="exctId" value="">
<input type="hidden" id="arrChk" name="arrChk" value="">
<input type="hidden" id="checkMessage" name="checkMessage" value="${voExct.checkMessage}"/>

 <table class="tbws1">
  <tr>
   <th scope="col" style="width:7%">사용</th>
   <th scope="col" style="width:30%;">예외명</th>
   <th scope="col" >상세설명</th>
   <!-- <th scope="col" style="width:10%;">관리</th> -->
  </tr>
  <c:forEach var="exctList" items="${termManageList}" varStatus="status" >
   <c:set var="sips" value="${status.count }"/>
    <tr>
    <td><input type="checkbox" name="chk_each" style="cursor: pointer;" exctId = "${exctList.exctId}" <c:if test="${exctList.useYn == 'Y'}">checked</c:if>/></td>
    <td onClick="board_detail('${exctList.exctId}')"  keyName="${exctList.exctId}">${exctList.exctNm}</td>
    <td onClick="board_detail('${exctList.exctId}')"  keyName="${exctList.exctId}">${exctList.exctDtl}</td>
    <%-- <td>
    	<a href="#" class="modify"  keyName="${exctList.exctId}" ><input type="button" value="수정" class="swhite" style="cursor: pointer;"></a>
    	<!--<a href="#" class="delete"  keyName="${exctList.exctId}" ><input type="button" value="삭제" class="swhite" style="cursor: pointer;"></a>-->
    </td> --%>
   </tr>
  </c:forEach>
 </table>

 <div class="btn_area" style="margin-top:20px;">
 <div class="left" style="margin:0px 3px 0px 3px"><input type="button" class="bwhite" id="btn_upate" value="사용 저장"/><!--&nbsp;&nbsp;<input type="button" class="bwhite" id="btn_insert" value="추가"/>--></div>
</div>
</form:form>
<!-- CONTENT END -->