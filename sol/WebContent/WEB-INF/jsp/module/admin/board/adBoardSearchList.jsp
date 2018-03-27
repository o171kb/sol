<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javaScript" src="/admin/sitemap/common/OnetimeSubmit.js"></script>
<script type="text/javaScript" src="/common/customer/datepicker.js"></script>

<script type="text/javaScript" defer="defer">
  $(document).ready(function() {

    //메뉴설정
    openMenu('01');
	
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
    /* search */
    $("#btn_search").click(function(){
      $("#pageIndex").val(1);
      $("#listForm").attr("action","<c:url value='/adBoardSearchList.do'/>");
      $("#listForm").submit();
      return false;
    });

    $("#searchTitle").keyup(function(e){
      if(e.keyCode == "13"){
        $("#pageIndex").val(1);
        $("#listForm").attr("action","<c:url value='/adBoardSearchList.do'/>");
        $("#listForm").submit();
        return false;
      }
    });

    $("#searchUserId").keyup(function(e){
      if(e.keyCode == "13"){
        $("#pageIndex").val(1);
        $("#listForm").attr("action","<c:url value='/adBoardSearchList.do'/>");
        $("#listForm").submit();
        return false;
      }
    });
    /*//search */

    /* search init */
    $("#btn_search_init").click(function(){
      $("#searchTitle").val("");
      $("#searchUserId").val("");
      $("#searchStartDm").val("");
      $("#searchEndDm").val("");
      $("#searchBoardTp").val("");
      return false;
    });

    /* write page */
    $("#btn_write").click(function(){
      $("#listForm").attr("action","<c:url value='/adBoardSearchWrite.do'/>");
      $("#listForm").submit();
      return false;
    });

    /* select change */
    $(".searchBoardTp").change(function(){
     var val = $(this).val();
     $("#searchBoardTp").val(val);
    });

    /* 별도 선택시 전체체크 해제 */
    $("input[name=chk_del]").click(function(){
      $("#chk_all").attr("checked", false);
    });

    /* 전체선택,해제 */
    $("#chk_all").click(function(){
      if($("#chk_all").attr("checked") == true){
        $("input[name=chk_del]").attr("checked" , true);
      }else{
        $("input[name=chk_del]").attr("checked", false);
      }
    });

    $("#btn_delete").click(function(){
      if($("input[name=chk_del]:checkbox:checked").length <= 0){
         $.prompt("삭제 할 게시물을 선택 하세요.");
         return false;
      }else{
        $.prompt("선택된 게시물을 삭제 하시겠습니까?", {
              buttons: { "예": true, "아니오": false },
              submit: function(e,v,m,f){
                if (v) {
                    $("#checkMessage").val("success_del");
                    var delArr = "";
                    $("input[name=chk_del]:checked").each(function(){
                     delArr = delArr + $(this).attr("boardNo") + ",";
                     $("#delArr").val(delArr);
                    });
                    $("#listForm").attr("action","<c:url value='/adBoardSearchDelAction.do'/>");
                    $("#listForm").submit();
                }
              }
           });
          return false;
      }

    });
    
    
    /* 권한처리 */
    <c:if test="${mnuLvlCd != 'write'}">
    		$("#btn_write").attr("disabled", "disabled").attr("style", "color:#ABABAB");
    		$("#btn_delete").attr("disabled", "disabled").attr("style", "color:#ABABAB");
    </c:if>

  });

  /* pagination 페이지 링크 function */
  function linkPage(pageNo){
    $("#pageIndex").val(pageNo);
    $("#listForm").attr("action","<c:url value='/adBoardSearchList.do'/>");
    $("#listForm").submit();
  }
  
  /* detail page */
  function board_detail(boardNo){
    $("#boardNo").val(boardNo);
    $("#listForm").attr("action","<c:url value='/adBoardSearchModify.do'/>");
    $("#listForm").submit();
    return false;
  }
</script>

<h3>시스템 &gt; 게시관리</h3>

<!-- CONTENT START -->
<form:form commandName="searchVoBoard" id="listForm" name="listForm" method="post" onsubmit="return onetimeSubmit(this)">
<input type="hidden" id="boardNo" name="boardNo"/>
<input type="hidden" id="searchBoardTp" name="searchBoardTp" value="${searchVoBoard.searchBoardTp}"/>
<input type="hidden" id="delArr" name="delArr"/>

<div class="img_va"><img src="<c:url value="/image/admin/btn_right.gif"/>" />검색</div>
<table class="tbws0">
 <tr>
  <th>구 분</th>
  <td class="align_l">
   <select style="width:100px;" class="searchBoardTp">
    <option value="">선택</option>
    <c:forEach var="code" items="${tt:getCodeValues('001') }">
     <option value="${code[0]}" <c:if test="${searchVoBoard.searchBoardTp == code[0]}">selected</c:if>>${code[1]}</option>
    </c:forEach>
   </select>
  </td>
  <th>제 목</th>
  <td class="align_l"><input type="text" class="middle500" id="searchTitle" name="searchTitle" value="${searchVoBoard.searchTitle}"/></td>
 </tr>
 <tr>
  <th>등록일</th>
  <td colspan="3" class="align_l">
   <input type="text" id="searchStartDm" name="searchStartDm" class="middle130" readonly="readonly" value="${searchVoBoard.searchStartDm}"/>&nbsp;&nbsp;~&nbsp;&nbsp;
   <input type="text" id="searchEndDm" name="searchEndDm" class="middle130" readonly="readonly" value="${searchVoBoard.searchEndDm}"/>
  </td>
 </tr>
 <tr>
  <th>등록자</th>
  <td class="align_l" colspan="3"><input type="text" class="middle2" id="searchUserId" name="searchUserId" value="${searchVoBoard.searchUserId}"/></td>
 </tr>
</table>
<div class="btn_area" style="margin:7px 5px 0px 0px;">
 <div class="right" style="margin:0px 3px 0px 3px"><input type="button" id="btn_search" name="btn_search" class="nwhite" value="검색"/></div>
 <!-- <div class="right" style="margin:0px 3px 0px 3px"><input type="button" id="btn_search_init" name="btn_search_init" class="nwhite" value="초기화"/></div> -->
</div><br />


<div class="btn_area" style="margin:7px 5px 0px 0px;">
 <div class="left" style="margin:0px 3px 5px 3px"><input type="button" id="btn_delete" name="btn_delete" class="nwhite" value="삭제"/></div>
 <div class="right" style="margin:0px 3px 5px 3px"><input type="button" id="btn_write" name="btn_write" class="nwhite" value="등록"/></div>
</div>
<table class="tbws1">
 <tr>
  <th style="width:5%;"><input type="checkbox" id="chk_all"/></th>
  <th style="width:10%;">구 분</th>
  <th style="width:*;">제 목</th>
  <th style="width:15%;">등록일</th>
  <th style="width:15%;">등록자</th>
  <!-- <th style="width:15%;">상 세</th> -->
 </tr>
 <c:choose>
  <c:when test="${fn:length(boardList)>0}">
   <c:forEach var="board" items="${boardList}" varStatus="status">
    <c:set var="sips" value="${status.count }"/>
    <tr>
     <td><input type="checkbox" name="chk_del" boardNo="${board.boardNo}"/></td>
     <td onClick="board_detail('${board.boardNo}')" boardNo="${board.boardNo}">
      <c:if test="${board.boardTp == '01'}">공지사항</c:if>
      <c:if test="${board.boardTp == '02'}">FAQ</c:if>
      <c:if test="${board.boardTp == '03'}">자료실</c:if>
     </td>
     <td class="align_l" onClick="board_detail('${board.boardNo}')" boardNo="${board.boardNo}"><c:out value="${board.boardTitle}"/></td>
     <td onClick="board_detail('${board.boardNo}')" boardNo="${board.boardNo}">${board.regDm}
     </td>
     <td onClick="board_detail('${board.boardNo}')" boardNo="${board.boardNo}"><c:out value="${board.userId}"/></td>
     <%-- <td><input type="button" class="swhite" value="상세" name="btn_detail" boardNo="${board.boardNo}"/></td> --%>
    </tr>
   </c:forEach>
  </c:when>
  <c:otherwise>
   <tr><td colspan="6">데이터가 없습니다.</td></tr>
  </c:otherwise>
 </c:choose>
</table>
<!-- paginate-->
<div class="page_area">
 <div class="paginate">
   페이지:<ui:pagination paginationInfo = "${searchVoBoard}" type="ad_image" jsFunction="linkPage"/>
   <form:hidden path="pageIndex" id="pageIndex" />
 </div>
 <div class="pagetotal" style="float:right">총건수:<span>${searchVoBoard.totalRecordCount}</span>건</div>
</div>
<!-- //paginate -->
</form:form>
<!-- //CONTENT START -->