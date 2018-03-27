<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javaScript" src="/admin/sitemap/common/OnetimeSubmit.js"></script>
<script type="text/javaScript" src="/common/exct/datepicker.js"></script>

<script type="text/javaScript" defer="defer">

  $(document).ready(function(){
  //메뉴설정
      openMenu('05');
	
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
      
    $("#exctAppId_select").change(function(){
      var attr = $(this).val();
      $("#exctAppId").val(attr);
      return false;
    });

  //검색버튼
    $("#btn_search").click(function(){
      $("#pageIndex").val(1);
      $("#infoForm").attr("action","<c:url value='/adMyDraftList.do'/>");
      $("#infoForm").submit();
      return false;
    });
    //검색조건 초기화
    $("#btn_search_init").click(function(){
     $("#searchGubun").val("");
     $("#searchUserNm").val("");
     $("#searchStatus").val("");
     $('#deptCd').val("");
     $('#deptNm').val("");
     $('#deptNo').val("");
     $('#searchOpenDate').val("");
     $('#searchCloseDate').val("");
    });

    $("#btn_excel").click(function(){
      $("#infoForm").attr("action","<c:url value='/adMyDraftExcelList.do'/>");
      $("#infoForm").submit();
      return false;
    });
    
    $("#myReq").click(function(){
    	$("#myReq").attr("class", "tab_cho_sub");
    	$("#deptReq").attr("class", "tab_no_sub");
    	$("#allReq").attr("class", "tab_no_sub");
        $("#tabDiv").val("myReq");
        $("#infoForm").attr("action","<c:url value='/adMyDraftList.do'/>");
        $("#infoForm").submit();
        return false;
    });
    $("#deptReq").click(function(){
    	$("#myReq").attr("class", "tab_no_sub");
    	$("#deptReq").attr("class", "tab_cho_sub");
    	$("#allReq").attr("class", "tab_no_sub");
        $("#tabDiv").val("deptReq");
        $("#infoForm").attr("action","<c:url value='/adMyDraftList.do'/>");
        $("#infoForm").submit();
        return false;
    });
    $("#allReq").click(function(){
    	$("#myReq").attr("class", "tab_no_sub");
    	$("#deptReq").attr("class", "tab_no_sub");
    	$("#allReq").attr("class", "tab_cho_sub");
        $("#tabDiv").val("allReq");
        $("#infoForm").attr("action","<c:url value='/adMyDraftList.do'/>");
        $("#infoForm").submit();
        return false;
    });

  });

  /* pagination 페이지 링크 function */
  function linkPage(pageNo){
    $("#pageIndex").val(pageNo);
    $("#infoForm").attr("action","<c:url value='/adMyDraftList.do'/>");
    $("#infoForm").submit();
  }
  //상위부서선택(팝업)에서 불려지는 함수
  function setParentDeptParams(deptcd,deptnm,deptno){
    $('#deptCd').val(deptcd);
    $('#deptNm').val(deptnm);
    $('#deptNo').val(deptno);
  };
  
	function board_detail(id, exctAppNm, userId){
		$("#apprId").val(id);
		$("#userId").val(userId);
		$("#exctAppNm").val(exctAppNm);
		$("#infoForm").attr("action","<c:url value='/adMyDraftView.do'/>");
		$("#infoForm").submit();
		return false;
	}

</script>

<h3>MyPage &gt; 나의신청현황 &gt; 리스트</h3>

<!-- CONTENT START -->
<form:form commandName="voApprInfo" id="infoForm" name="infoForm" method="post" onsubmit="return onetimeSubmit(this)">
<input type="hidden" id="apprId" name="apprId" />
<input type="hidden" id="exctAppId" name="exctAppId" value="${voApprInfo.exctAppId}"/>
<input type="hidden" id="exctAppNm" name="exctAppNm"/>
<input type="hidden" id="tabDiv" name="tabDiv" value="${voApprInfo.tabDiv}"/>
<input type="hidden" id="userId" name="userId" />

<div class="img_va"><img src="<c:url value="/image/admin/btn_right.gif"/>" />검색</div>
<table class="tbws0">
 <tr>
  <th>구분</th>
  <td class="align_l">
  <select id="searchGubun"  name="searchGubun">
  <option value="">전체</option>
  <c:forEach var="code" items="${tt:getCodeValues('007') }">
  <option value="${code[0]}" <c:if test="${voApprInfo.searchGubun == code[0]}">selected</c:if>>${code[1]}</option>
  </c:forEach>
  </select>
  </td>
  <th>결재상태</th>
  <td class="align_l">
  <select id="searchStatus"  name="searchStatus">
  <option value="">전체</option>
  <c:forEach var="code" items="${tt:getCodeValues('004') }" end="2">
  <option value="${code[0]}" <c:if test="${voApprInfo.searchStatus == code[0]}">selected</c:if>>${code[1]}</option>
  </c:forEach>
  </select>
  </td>
 </tr>
 <%-- <tr>
  <th>성명</th>
  <td class="align_l"><input type="text" class="middle2" id="searchUserNm" name="searchUserNm" value="${voApprInfo.searchUserNm}"/></td>
  <th>사용자부서</th>
  <td class="align_l">
  <input type="hidden" id="deptCd" name="deptCd">
  <input type="hidden" id="deptNo" name="deptNo" value="${voApprInfo.deptNo}">
  <input type="text" id="deptNm" name="deptNm" readonly="readonly" value="${voApprInfo.deptNm}">
  <input type="button"  style="text-align:center;" value="조회" id="selectDept_bt" class="swhite" ><p>
  <label class="error" name="error" for="highDeptCd" generated="true" style="display:none;">에러메시지</label>
  </td>
 </tr> --%>
 <tr>
  <th>등록일</th>
  <td colspan="3" class="align_l">
   <input type="text" id="searchOpenDate" name="searchStartDm" class="middle130" readonly="readonly" value="${voApprInfo.searchStartDm}"/>&nbsp;&nbsp;~&nbsp;&nbsp;
   <input type="text" id="searchCloseDate" name="searchEndDm" class="middle130" readonly="readonly" value="${voApprInfo.searchEndDm}"/>
  </td>
 </tr>
</table>
<div class="btn_area" style="margin:7px 5px 0px 0px;">
 	<c:if test="${grpYn == '1'}">
	<div class="left">
		<a id="myReq" <c:if test="${voApprInfo.tabDiv eq 'myReq' or voApprInfo.tabDiv == null}">class="tab_cho_sub"</c:if><c:if test="${voApprInfo.tabDiv ne 'myReq'}">class="tab_no_sub"</c:if>>나의 신청현황</a>
		<a id="deptReq" <c:if test="${voApprInfo.tabDiv eq 'deptReq'}">class="tab_cho_sub"</c:if><c:if test="${voApprInfo.tabDiv ne 'deptReq'}">class="tab_no_sub"</c:if>>부서 신청현황</a>
		<c:if test="${grpYn == '1' && mstYn == '1'}">
			<a id="allReq" <c:if test="${voApprInfo.tabDiv eq 'allReq'}">class="tab_cho_sub"</c:if><c:if test="${voApprInfo.tabDiv ne 'allReq'}">class="tab_no_sub"</c:if>>전체 신청현황</a>
		</c:if>
		<c:if test="${grpYn == '0' && mstYn == '1'}">
			<a id="myReq" <c:if test="${voApprInfo.tabDiv eq 'myReq' or voApprInfo.tabDiv == null}">class="tab_cho_sub"</c:if><c:if test="${voApprInfo.tabDiv ne 'myReq'}">class="tab_no_sub"</c:if>>나의 신청현황</a>
			<a id="allReq" <c:if test="${voApprInfo.tabDiv eq 'allReq'}">class="tab_cho_sub"</c:if><c:if test="${voApprInfo.tabDiv ne 'allReq'}">class="tab_no_sub"</c:if>>전체 신청현황</a>
		</c:if>
	</div>
	</c:if>
 <div class="right" style="margin:0px 3px 0px 3px"><input type="button" id="btn_search" name="btn_search" class="nwhite" value="검색"/></div>
 <!-- <div class="right" style="margin:0px 3px 0px 3px"><input type="button" id="btn_search_init" name="btn_search_init" class="nwhite" value="초기화"/></div> -->
</div><br />

<table class="tbws1">
 <tr>
  <th style=" width:15%;">신청일</th>
  <th style="width:15%;">구분</th>
  <th style="width:15%;">사번</th>
  <th style="width:20%;">성명</th>
  <th style="width:*%;">부서</th>
  <th style="width:10%;">결재상태</th>
  <!-- <th style="width:10%;">상세</th> -->
 </tr>
 <c:choose>
  <c:when test="${fn:length(list)>0}">
   <c:forEach var="list" items="${list}" varStatus="status">
    <c:set var="sips" value="${status.count }"/>
    <tr onClick="board_detail('${list.apprId}','${list.exctAppNm}', '${list.userId}')" exctAppNm="${list.exctAppNm}" apprId="${list.apprId}" userId="${list.userId}">
     <td><c:out value="${list.apprDm}"/></td>
     <td><c:out value="${list.exctAppNm}"/></td>
     <td><c:out value="${list.appId}"/></td>
     <td><c:out value="${list.approbjUser}"/></td>
     <td><c:out value="${list.deptNm}"/></td>
     <td><c:out value="${list.cdDtlNm}"/></td>
     <%-- <td><input type="button" class="swhite" value="상세" name="btn_detail" exctAppNm="${list.exctAppNm}" apprId="${list.apprId}"/></td> --%>
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
   페이지:<ui:pagination paginationInfo = "${voApprInfo}" type="ad_image" jsFunction="linkPage"/>
   <form:hidden path="pageIndex" id="pageIndex" />
 </div>
 <div class="pagetotal">총건수:<span>${voApprInfo.totalRecordCount}</span>건</div>
</div>
<!-- //paginate -->
  <!-- <div class="btn_area">
      <div class="right" style="margin:0px 3px 0px 3px"><input type="button" id="btn_excel" class="nwhite" value="엑셀다운로드"/></div>
   </div> -->
</form:form>
<!-- //CONTENT START -->
<div class="mskbg" id="pop_bg" style="display:none;"><div class="msk"></div></div> <!-- 흰색 마스크 레이어 display로 제어-->
<%//부서선택%>
<jsp:include page="../../../com/popup/selectDeptAll.jsp" />