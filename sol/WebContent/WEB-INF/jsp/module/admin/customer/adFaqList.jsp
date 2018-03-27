<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javaScript" src="/admin/sitemap/common/OnetimeSubmit.js"></script>
<script type="text/javaScript" src="/common/customer/datepicker.js"></script>

<script type="text/javaScript" defer="defer">
  $(document).ready(function() {
    //메뉴설정
    openMenu('04');
	
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
    
    $("#btn_search").click(function(){
      $("#pageIndex").val(1);
      $("#faqForm").attr("action","<c:url value='/adFaqList.do'/>");
      $("#faqForm").submit();
      return false;
    });

    $("#searchTitle").keyup(function(e){
      if(e.keyCode == "13"){
        $("#pageIndex").val(1);
        $("#faqForm").attr("action","<c:url value='/adFaqList.do'/>");
        $("#faqForm").submit();
        return false;
      }
    });

    $("#searchUserId").keyup(function(e){
      if(e.keyCode == "13"){
        $("#pageIndex").val(1);
        $("#faqForm").attr("action","<c:url value='/adFaqList.do'/>");
        $("#faqForm").submit();
        return false;
      }
    });

    $("#btn_search_init").click(function(){
        $("#searchTitle").val("");
        $("#searchUserId").val("");
        $("#searchStartDm").val("");
        $("#searchEndDm").val("");
        $("#faqForm").attr("action","<c:url value='/adFaqList.do'/>");
        $("#faqForm").submit();
        return false;
    });
    
  });

  /* pagination 페이지 링크 function */
  function linkPage(pageNo){
    $("#pageIndex").val(pageNo);
    $("#faqForm").attr("action","<c:url value='/adFaqList.do'/>");
    $("#faqForm").submit();
  }
  
	function board_detail(boardNo){
		$("#boardNo").val(boardNo);
		$("#faqForm").attr("action","<c:url value='/adFaqView.do'/>");
		$("#faqForm").submit();
		return false;
	}
</script>

<h3>고객센터 &gt; FAQ &gt; 리스트</h3>

<!-- CONTENT START -->
<form:form commandName="searchVoBoard" id="faqForm" name="faqForm" method="post" onsubmit="return onetimeSubmit(this)">
<input type="hidden" id="boardNo" name="boardNo"/>

<!-- paginate-->
<div class="page_area">
 <div class="paginate">
   페이지:<ui:pagination paginationInfo = "${searchVoBoard}" type="ad_image" jsFunction="linkPage"/>
   <form:hidden path="pageIndex" id="pageIndex" />
 </div>
 <div class="pagetotal">총건수:<span>${searchVoBoard.totalRecordCount}</span>건</div>
</div>
<!-- //paginate -->

<table class="tbws1">
 <tr>
  <th style=" width:10%;">번호</th>
  <th style="width:*;">제목</th>
  <th style="width:15%;">등록일</th>
  <th style="width:15%;">등록자</th>
  <!-- <th style="width:15%;">상세</th> -->
 </tr>
 <c:choose>
  <c:when test="${fn:length(faqList)>0}">
   <c:forEach var="faq" items="${faqList}" varStatus="status">
    <c:set var="sips" value="${status.count }"/>
    <tr onClick="board_detail('${faq.boardNo}')" boardNo="${faq.boardNo}">
     <td><c:out value="${(searchVoBoard.pageIndex-1)*searchVoBoard.pageUnit + status.count}"/></td>
     <td class="align_l"><c:out value="${faq.boardTitle}"/></td>
     <td><c:out value="${faq.regDm}"/></td>
     <td><c:out value="${faq.userId}"/></td>
     <%-- <td><input type="button" class="swhite" value="상세" name="btn_detail" boardNo="${faq.boardNo}"/></td> --%>
    </tr>
   </c:forEach>
  </c:when>
  <c:otherwise>
   <tr><td colspan="4"></td></tr>
  </c:otherwise>
 </c:choose>
</table>
<div class="img_va"><img src="<c:url value="/image/admin/btn_right.gif"/>" />검색</div>
<table class="tbws0">
 <tr>
 	<th colspan="2" style="width:150px"><input type="radio" name="searchStatus" class="searchStatus"<c:if test="${searchVoBoard.searchStatus == '0'}">checked</c:if> value="0" checked> 제목 
 	<input type="radio" style="margin-left:10px" name="searchStatus" class="searchStatus" <c:if test="${searchVoBoard.searchStatus == '1'}">checked</c:if> value="1"> 등록자 </th>
 	<td class="align_l"><input type="text" class="middle2" id="searchTitle" name="searchTitle" value="${searchVoBoard.searchTitle}"/></td>
  <%-- <th>제 목</th>
  <td class="align_l"><input type="text" class="middle2" id="searchTitle" name="searchTitle" value="${searchVoBoard.searchTitle}"/></td>
  <th>등록자</th>
  <td class="align_l"><input type="text" class="middle2" id="searchUserId" name="searchUserId" value="${searchVoBoard.searchUserId}"/></td>
 </tr>
 <tr>
  <th>등록일</th>
  <td colspan="3" class="align_l">
   <input type="text" id="searchStartDm" name="searchStartDm" class="middle130" readonly="readonly" value="${searchVoBoard.searchStartDm}"/>&nbsp;&nbsp;~&nbsp;&nbsp;
   <input type="text" id="searchEndDm" name="searchEndDm" class="middle130" readonly="readonly" value="${searchVoBoard.searchEndDm}"/>
  </td> --%>
 </tr>
</table>
<div class="btn_area" style="margin:7px 5px 0px 0px;">
 <div class="right" style="margin:0px 3px 0px 3px"><input type="button" id="btn_search" name="btn_search" class="nwhite" value="검색"/></div>
 <!-- <div class="right" style="margin:0px 3px 0px 3px"><input type="button" id="btn_search_init" name="btn_search_init" class="nwhite" value="초기화"/></div> -->
</div><br />
</form:form>
<!-- //CONTENT START -->