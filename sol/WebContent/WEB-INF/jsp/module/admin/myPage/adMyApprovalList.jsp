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
    
    //검색버튼
    $("#btn_search").click(function(){
      $("#pageIndex").val(1);
      $("#infoForm").attr("action","<c:url value='/adMyApprovalList.do'/>");
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
    //엑셀다운로드
    $("#btn_excel").click(function(){
      $("#infoForm").attr("action","<c:url value='/adMyApprovalDownLoad.do'/>");
      $("#infoForm").submit();
      return false;
    });
    
    $("#myApp").click(function(){
    	$("#myApp").attr("class", "tab_cho_sub");
    	$("#deptApp").attr("class", "tab_no_sub");
    	$("#allApp").attr("class", "tab_no_sub");
        $("#tabDiv2").val("myApp");
        $("#infoForm").attr("action","<c:url value='/adMyApprovalList.do'/>");
        $("#infoForm").submit();
        return false;
    });
    $("#deptApp").click(function(){
    	$("#myApp").attr("class", "tab_no_sub");
    	$("#deptApp").attr("class", "tab_cho_sub");
    	$("#allApp").attr("class", "tab_no_sub");
        $("#tabDiv2").val("deptApp");
        $("#infoForm").attr("action","<c:url value='/adMyApprovalList.do'/>");
        $("#infoForm").submit();
        return false;
    });
    $("#allApp").click(function(){
    	$("#myApp").attr("class", "tab_no_sub");
    	$("#deptApp").attr("class", "tab_no_sub");
    	$("#allApp").attr("class", "tab_cho_sub");
        $("#tabDiv2").val("allApp");
        $("#infoForm").attr("action","<c:url value='/adMyApprovalList.do'/>");
        $("#infoForm").submit();
        return false;
    });
 });
  /* pagination 페이지 링크 function */
  function linkPage(pageNo){
    $("#pageIndex").val(pageNo);
    $("#infoForm").attr("action","<c:url value='/adMyApprovalList.do'/>");
    $("#infoForm").submit();
  }
  //상위부서선택(팝업)에서 불려지는 함수
  function setParentDeptParams(deptcd,deptnm,deptno){
    $('#deptCd').val(deptcd);
    $('#deptNm').val(deptnm);
    $('#deptNo').val(deptno);
  };


	//상세보기
	function board_detail(id , appid, userId, permitGubun){
		$("#apprId").val(id);
		$("#appId").val(appid);
		$("#userId").val(userId);
		$("#permitGubun").val(permitGubun);
		$("#infoForm").attr("action","<c:url value='/adMyApprovalView.do'/>");
		$("#infoForm").submit();
		return false;
	}
</script>

<h3>MyPage &gt; 나의결재함 &gt; 리스트</h3>

<!-- CONTENT START -->
<form:form commandName="voApprInfo" id="infoForm" name="infoForm" method="post" onsubmit="return onetimeSubmit(this)">
<input type="hidden" id="apprId" name="apprId" />
<input type="hidden" id="appId" name="appId" />
<input type="hidden" id="tabDiv2" name="tabDiv2" value="${voApprInfo.tabDiv2}"/>
<input type="hidden" id="userId" name="userId" />
<input type="hidden" id="permitGubun" name="permitGubun" />

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
 <tr>
  <th>성명</th>
  <td class="align_l"><input type="text" class="middle2" id="searchUserNm" name="searchUserNm" value="${voApprInfo.searchUserNm}"/></td>
  <th>사용자부서</th>
  <td class="align_l">
  <input type="hidden" id="deptCd" name="deptCd">
  <input type="text" id="deptNo" name="deptNo" value="${voApprInfo.deptNo}">
  <input type="text" id="deptNm" name="deptNm" readonly="readonly" value="${voApprInfo.deptNm}">
  <input type="button"  style="text-align:center;" value="조회" id="selectDept_bt" class="swhite" ><p>
  <label class="error" name="error" for="highDeptCd" generated="true" style="display:none;">에러메시지</label>
  </td>
 </tr>
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
		<a id="myApp" <c:if test="${voApprInfo.tabDiv2 eq 'myApp' or voApprInfo.tabDiv2 == null}">class="tab_cho_sub"</c:if><c:if test="${voApprInfo.tabDiv2 ne 'myApp'}">class="tab_no_sub"</c:if>>나의 결재현황</a>
		<a id="deptApp" <c:if test="${voApprInfo.tabDiv2 eq 'deptApp'}">class="tab_cho_sub"</c:if><c:if test="${voApprInfo.tabDiv2 ne 'deptApp'}">class="tab_no_sub"</c:if>>부서 결재현황</a>
		<c:if test="${grpYn == '1' && mstYn == '1'}">
			<a id="allApp" <c:if test="${voApprInfo.tabDiv2 eq 'allApp'}">class="tab_cho_sub"</c:if><c:if test="${voApprInfo.tabDiv2 ne 'allApp'}">class="tab_no_sub"</c:if>>전체 결재현황</a>
		</c:if>
		<c:if test="${grpYn == '0' && mstYn == '1'}">
			<a id="myApp" <c:if test="${voApprInfo.tabDiv2 eq 'myApp' or voApprInfo.tabDiv2 == null}">class="tab_cho_sub"</c:if><c:if test="${voApprInfo.tabDiv2 ne 'myApp'}">class="tab_no_sub"</c:if>>나의 결재현황</a>
			<a id="allApp" <c:if test="${voApprInfo.tabDiv2 eq 'allApp'}">class="tab_cho_sub"</c:if><c:if test="${voApprInfo.tabDiv2 ne 'allApp'}">class="tab_no_sub"</c:if>>전체 결재현황</a>
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
  <th style="width:10%;">기안자</th>
  <th style="width:10%;">성명</th>
  <th style="width:15%;">부서명</th>
  <th style="width:20%;">사용기간</th>
  <th style="width:7%;">결재상태</th>
  <!-- <th style="width:7%;">상세</th> -->
 </tr>
 <c:choose>
  <c:when test="${fn:length(list)>0}">
   <c:forEach var="list" items="${list}" varStatus="status">
    <c:set var="sips" value="${status.count }"/>
    <tr onClick="board_detail('${list.apprId}', '${list.appId}', '${list.userId}' , '${list.permitGubun}')" apprId="${list.apprId}" appId="${list.appId}" userId="${list.userId}" permitGubun ="${list.permitGubun}"> 
     <td><c:out value="${list.apprDm}"/></td>
     <td><c:out value="${list.exctAppNm}"/></td>
     <td><c:out value="${list.drafter}"/></td>
     <td><c:out value="${list.approbjUser}"/></td>
     <td><c:out value="${list.apprObjDept}"/></td>
     <td><c:out value="${list.startDm}"/> ~ <c:out value="${list.endDm}"/></td>
     <td>
      <c:choose>
       <c:when test="${list.permitGubun == '03'}">
        통보
       </c:when>
       <c:otherwise>
        <c:out value="${list.cdDtlNm}"/>
       </c:otherwise>
      </c:choose>
     </td>
     <%-- <td><input type="button" class="swhite" value="상세" name="btn_detail" apprId="${list.apprId}"/></td> --%>
    </tr>
   </c:forEach>
  </c:when>
  <c:otherwise>
   <tr><td colspan="8">데이터가 없습니다.</td></tr>
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