<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
             }
           }
         });
     }
  
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
   
});

	//수정페이지로 이동
	function board_detail(exctAppId){
		$("#exctAppId").val(exctAppId);
		$("#exctForm").attr("action","<c:url value='/adExctDraftModify.do'/>");
		$("#exctForm").submit();
		return false;
	}
</script>

<!-- HEADER START -->
 <h3>예외 &gt; 예외신청문구수정</h3>
<!-- HEADER AND -->

<!-- CONTENT START -->
<form:form commandName="voExct" id="exctForm" name="exctForm" method="post" >
<input type="hidden" id="exctAppId" name="exctAppId" value="">
<input type="hidden" id="checkMessage" name="checkMessage" value="${outMsg.checkMessage}"/>

 <!-- 검색 -->
 <!-- 검색 -->
 <table class="tbws1">
  <tr>
   <th scope="col" style="width:7%">번호</th>
   <th scope="col" width="300px;">예외명</th>
   <th scope="col" style="width:15%">최종수정일</th>
   <th scope="col" style="width:15%">수정자</th>
   <!-- <th scope="col"  style="width:10%" >수정</th> -->
  </tr>
  <c:forEach var="draftList" items="${draftList}" varStatus="status" >
   <c:set var="sips" value="${status.count }"/>
    <tr onClick="board_detail('${draftList.exctAppId}')" keyName="${draftList.exctAppId}">
    <td>${status.count}</td>
    <td>${draftList.exctBasicTerms}</td>
    <td>${draftList.updDm}</td>
    <td>${draftList.updUser}</td>
    <%-- <td><a href="#" class="modify"  keyName="${draftList.exctAppId}" ><input type="button" value="수정" class="swhite" style="cursor: pointer;"></a></td> --%>
   </tr>
  </c:forEach>
 </table>

</form:form>
<!-- CONTENT END -->