<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javaScript" src="/admin/sitemap/common/OnetimeSubmit.js"></script>


<script type="text/javaScript" defer="defer">
  $(document).ready(function() {
    //메뉴설정
    openMenu('04');

    /* 목록으로 돌아가기 */
    $("#btn_list").click(function(){
      $("#dataForm").attr("action","<c:url value='/adDataList.do'/>");
      $("#dataForm").submit();
      return false;
    });

  });


</script>

<h3>고객센터 &gt; 자료실 &gt; 상세</h3>

<!-- CONTENT START -->
<form:form commandName="voBoard" id="dataForm" name="dataForm" method="post" onsubmit="return onetimeSubmit(this)">
<input type="hidden" id="pageIndex" name="pageIndex" value="${voBoard.pageIndex}"/>

 <table class="tbws1">
 <tr>
  <th>제 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;목&nbsp;</th>
  <td class="align_l" colspan="3"><c:out value="${voBoard.boardTitle}"/></td>
 </tr>
 <tr>
  <th>등록자</th>
  <td class="align_l"><c:out value="${voBoard.userId}"/></td>
  <th>게시일</th>
  <td class="align_l"><c:out value="${voBoard.regDm}"/></td>
 </tr>
 <tr>
  <td class="align_l" colspan="4" style="min-height:100px;"><c:out value="${voBoard.contents}"/></td>
 </tr>
 <tr>
  <th>첨부파일</th>
  <td class="align_l" colspan="3">
   <c:choose>
    <c:when test="${!empty voBoard.fileNm}">
     <a href="<c:url value='board/download.do?fid=${voBoard.boardNo}&pid=fileNm'/>" style="cursor: pointer;">${voBoard.fileNm}</a>
    </c:when>
    <c:otherwise>첨부파일없음</c:otherwise>
   </c:choose>
  </td>
 </tr>
</table>
<div class="btn_area" style="margin-top:20px;">
 <div class="right" style="margin:0px 3px 0px 3px"><input type="button" id="btn_list" name="btn_list" class="nwhite" value="목록"/></div>
</div>

</form:form>
<!-- //CONTENT START -->