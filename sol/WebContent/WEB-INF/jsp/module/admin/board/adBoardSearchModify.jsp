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
     if(checkMessage == "success_modi"){
         $.prompt("수정 완료 되었습니다.", {
           buttons: { "확인": true },
           submit: function(e,v,m,f){
             if (v) {
                 $("#checkMessage").val("");
                 $("#modifyForm").attr("action","<c:url value='/adBoardSearchList.do'/>");
                 $("#modifyForm").submit();
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

    /* 파일 삭제 */
    $("#file_del").click(function(){
      $("#fileNm").val("");
      $("#fileUrl").val("");
      $("#file_area").html("");
      $("#file_del").hide();
      $("#fileDelYn").val("Y");
      return false;
    });

    /* 수정하기 */
    $("#btn_modify").click(function(){
      $.prompt("수정 하시겠습니까?", {
        buttons: { "예": true, "아니오": false },
        submit: function(e,v,m,f){
          if (v) {
            $("#modifyForm").attr("action","<c:url value='/adBoardSearchModifyAction.do'/>");
            $("#modifyForm").submit();
          }
        }
      });
      return false;
    });
    
    /* 권한처리 */
    <c:if test="${mnuLvlCd != 'write'}">
    		$("#btn_modify").attr("disabled", "disabled").attr("style", "color:#ABABAB");
    		//$("#btn_modify_menu").attr("disabled", "disabled");
    		//$("input:checkbox[name='chk_each']").attr("disabled", true);
    		$("input:text").attr("disabled", true);
    		$("input:checkbox").attr("disabled", true);
    		$("select").attr("disabled", true);
    		$("textarea").attr("disabled", true);
    	   	//$("#btn_next").unbind();
    	   	//$("input:button[value='검색']").attr("disabled", true);
    	   	$("#file_del").hide();
    	   	$("input[name='_attachFile']").attr("disabled", "disabled");
    </c:if>    

  });


</script>

<h3>시스템 &gt; 게시관리</h3>

<!-- CONTENT START -->
<form:form commandName="voBoard" id="modifyForm" name="modifyForm" method="post" onsubmit="return onetimeSubmit(this)" enctype="multipart/form-data">
<input type="hidden" id="pageIndex" name="pageIndex" value="${voBoard.pageIndex}"/>
<input type="hidden" id="boardNo" name="boardNo" value="${voBoard.boardNo}"/>
<input type="hidden" id="boardTp" name="boardTp" value="${voBoard.boardTp}"/>
<input type="hidden" id="fileNm" name="fileNm" value="${voBoard.fileNm}"/>
<input type="hidden" id="fileUrl" name="fileUrl" value="${voBoard.fileUrl}"/>
<input type="hidden" id="checkMessage" name="checkMessage" value="${voBoard.checkMessage}"/>
<input type="hidden" id="fileDelYn" name="fileDelYn" value="N"/>

 <table class="tbws1">
 <tr>
  <th>구 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;분&nbsp;</th>
  <td class="align_l" colspan="3">
   <select style="width:100px;" class="boardTp">
    <c:forEach var="code" items="${tt:getCodeValues('001') }">
     <option value="${code[0]}" <c:if test="${voBoard.boardTp == code[0]}">selected</c:if>>${code[1]}</option>
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
  <td class="align_l" colspan="3"><textarea class="contets" id="contents" name="contents" cols="110" rows="20" style="width:95%;"><c:out value="${voBoard.contents}"/></textarea></td>
 </tr>
 <tr>
  <th>첨부파일</th>
  <td class="align_l" style="padding-bottom:5px;">
   <div><input type="file" class="long" id="" name="_attachFile" style="vertical-align: middle;width:95%;"/></div>
   <c:choose>
    <c:when test="${!empty voBoard.fileNm}">
    <div style="padding:5px 0px 0px 0px">
     <a href="<c:url value='board/download.do?fid=${voBoard.boardNo}&pid=fileNm'/>" style="cursor: pointer;"><span id="file_area">${voBoard.fileNm}</span></a>
     <img src="<c:url value="/image/admin/btn_00_1.gif"/>" style="cursor:pointer; vertical-align: middle;" id="file_del" name="file_del"/>
    </div>
    </c:when>
   </c:choose>
  </td>
 </tr>
</table>
<div class="btn_area" style="margin-top:20px;">
 <div class="right" style="margin:0px 3px 0px 3px"><input type="button" id="btn_list" name="btn_list" class="nwhite" value="목록"/></div>
 <div class="right" style="margin:0px 3px 0px 3px"><input type="button" id="btn_modify" name="btn_modify" class="nwhite" value="수정"/></div>
</div>

</form:form>
<!-- //CONTENT START -->