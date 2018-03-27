<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javaScript" src="/admin/sitemap/common/OnetimeSubmit.js"></script>
<script type="text/javascript" src="<c:url value='/js/chkValidate.js'/>"></script>
<style type="text/css"> label.error{ display:block; color:red; } </style>
<script type="text/javaScript" defer="defer">
  $(document).ready(function() {

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
                 $("#writeForm").attr("action","<c:url value='/adBoardSearchList.do'/>");
                 $("#writeForm").submit();
             }
           }
         });
     }

    /* 목록으로 돌아가기 */
    $("#btn_list").click(function(){
      location.href = "<c:url value='/adBoardSearchList.do'/>";
      return false;
    });

    /* select change */
    $(".boardTp").change(function(){
     var val = $(this).val();
     $("#boardTp").val(val);
    });

    /* 등록하기 */
    $("#btn_write").click(function(){
      $.prompt("등록 하시겠습니까?", {
        buttons: { "예": true, "아니오": false },
        submit: function(e,v,m,f){
          if (v) {
            $("#writeForm").attr("action","<c:url value='/adBoardSearchWriteAction.do'/>");
            $("#writeForm").submit();
          }
        }
      });
      return false;
    });
  });


</script>

<h3>시스템 &gt; 게시관리</h3>

<!-- CONTENT START -->
<form:form commandName="voBoard" id="writeForm" name="writeForm" method="post" onsubmit="return onetimeSubmit(this)"  enctype="multipart/form-data">
<input type="hidden" id="pageIndex" name="pageIndex" value="${voBoard.pageIndex}"/>
<input type="hidden" id="boardTp" name="boardTp" value="01"/>
<input type="hidden" id="checkMessage" name="checkMessage" value="${voBoard.checkMessage}"/>
 <table class="tbws0">
 <tr>
  <th>구 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;분&nbsp;</th>
  <td class="align_l" colspan="3">
   <select style="width:100px;" class="boardTp">
    <c:forEach var="code" items="${tt:getCodeValues('001') }">
     <option value="${code[0]}" >${code[1]}</option>
    </c:forEach>
   </select>
  </td>
 </tr>
 <tr>
  <th>제 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;목&nbsp;</th>
  <td class="align_l" colspan="3"><input type="text" id="boardTitle" name="boardTitle" class="long" style="width:95%;" value="${voBoard.boardTitle}"/></td>
 </tr>
 <tr>
  <th>내 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;용&nbsp;</th>
  <td class="align_l" colspan="3"><textarea class="contets" id="contents" name="contents" cols="110" rows="20" style="width:95%;">${voBoard.contents}</textarea></td>
 </tr>
 <tr>
  <th>첨부파일</th>
  <td class="align_l" style="padding-bottom:5px;">
   <div><input type="file" class="long" name="_attachFile" style="vertical-align: middle;width:95%;"/></div>
  </td>
 </tr>
</table>
<div class="btn_area" style="margin-top:20px;">
 <div class="right" style="margin:0px 3px 0px 3px"><input type="button" id="btn_list" name="btn_list" class="nwhite" value="목록"/></div>
 <div class="right" style="margin:0px 3px 0px 3px"><input type="button" id="btn_write" name="btn_write" class="nwhite" value="등록"/></div>
</div>

</form:form>
<!-- //CONTENT START -->