<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="tt.com.utils.*"%>
<%
String ip = "192.168.1.1";
String userIp = "";
String tempIp[] = {};
String[] arr_userIp = ip.split("\\.");

String aaa = (String)UtCoStringUtils.leftPadZero(arr_userIp[2], 3);


System.out.println("ip ::::" + aaa);
System.out.println("arr_userIp ::::" + arr_userIp);
System.out.println("arr_userIp.length ::::" + arr_userIp.length);
//System.out.println("String.format(arr_userIp[i] , %03d) ::::" + String.format(arr_userIp[2] , "%03d"));
//System.out.println("String.format(%03d, arr_userIp[i]) ::::" + String.format("%03d", arr_userIp[1]));

%>
<script type="text/javaScript" src="/admin/sitemap/common/OnetimeSubmit.js"></script>
<script type="text/javaScript" defer="defer">

  $(document).ready(function(){
    //메뉴설정
    openMenu('00');
	
    

    $('.tbws1 tr').mouseover(function(){
    	$(this).attr("class",'selectedRow2');
		//$(this).addClass('selectedRow2');

    }).mouseout(function() {
    	/* vif($(this).attr("rowType") == "W")
    	{
       		$(this).attr('class', '');
    	}
    	else if($(this).attr("rowType") == "P")
   		{
    		$(this).attr('class', 'selectedRow3');
   		} */
    	$(this).attr("class",'');
    });
    
    $("#myReq").click(function(){
    	$("#myReq").attr("class", "tab_cho_main");
    	$("#deptReq").attr("class", "tab_no_main");
    	$("#allReq").attr("class", "tab_no_main");
    	$("#myApp").attr("class", "tab_no_main");
    	$("#deptApp").attr("class", "tab_no_main");
    	$("#allApp").attr("class", "tab_no_main");
        $("#tabDiv").val("myReq");
        $("#mainForm").attr("action","<c:url value='/adHome.do'/>");
        $("#mainForm").submit();
        return false;
    });
    $("#deptReq").click(function(){
    	$("#myReq").attr("class", "tab_no_main");
    	$("#deptReq").attr("class", "tab_cho_main");
    	$("#allReq").attr("class", "tab_no_main");
    	$("#myApp").attr("class", "tab_no_main");
    	$("#deptApp").attr("class", "tab_no_main");
    	$("#allApp").attr("class", "tab_no_main");
        $("#tabDiv").val("deptReq");
        $("#mainForm").attr("action","<c:url value='/adHome.do'/>");
        $("#mainForm").submit();
        return false;
    });
    $("#allReq").click(function(){
    	$("#myReq").attr("class", "tab_no_main");
    	$("#deptReq").attr("class", "tab_no_main");
    	$("#allReq").attr("class", "tab_cho_main");
    	$("#myApp").attr("class", "tab_no_main");
    	$("#deptApp").attr("class", "tab_no_main");
    	$("#allApp").attr("class", "tab_no_main");
        $("#tabDiv").val("allReq");
        $("#mainForm").attr("action","<c:url value='/adHome.do'/>");
        $("#mainForm").submit();
        return false;
    });
    
    $("#myApp").click(function(){
    	$("#myReq").attr("class", "tab_no_main");
    	$("#deptReq").attr("class", "tab_no_main");
    	$("#allReq").attr("class", "tab_no_main");
    	$("#myApp").attr("class", "tab_cho_main");
    	$("#deptApp").attr("class", "tab_no_main");
    	$("#allApp").attr("class", "tab_no_main");
        $("#tabDiv2").val("myApp");
        $("#mainForm").attr("action","<c:url value='/adHome.do'/>");
        $("#mainForm").submit();
        return false;
    });
    $("#deptApp").click(function(){
    	$("#myReq").attr("class", "tab_no_main");
    	$("#deptReq").attr("class", "tab_no_main");
    	$("#allReq").attr("class", "tab_no_main");
    	$("#myApp").attr("class", "tab_no_main");
    	$("#deptApp").attr("class", "tab_cho_main");
    	$("#allApp").attr("class", "tab_no_main");
        $("#tabDiv2").val("deptApp");
        $("#mainForm").attr("action","<c:url value='/adHome.do'/>");
        $("#mainForm").submit();
        return false;
    });
    $("#allApp").click(function(){
    	$("#myReq").attr("class", "tab_no_main");
    	$("#deptReq").attr("class", "tab_no_main");
    	$("#allReq").attr("class", "tab_no_main");
    	$("#myApp").attr("class", "tab_no_main");
    	$("#deptApp").attr("class", "tab_no_main");
    	$("#allApp").attr("class", "tab_cho_main");
        $("#tabDiv2").val("allApp");
        $("#mainForm").attr("action","<c:url value='/adHome.do'/>");
        $("#mainForm").submit();
        return false;
    });
    
    //공지
   /*  $("input[name=btn_board_detail]").click(function(){
        var boardNo = $(this).attr("boardNo");
        $("#boardNo").val(boardNo);
        $("#mainForm").attr("action","<c:url value='/adNoticeView.do'/>");
        $("#mainForm").submit();
        return false;
    }); */
    
    //상신함
   /*  $("input[name=btn_draft_detail]").click(function(){
        var id = $(this).attr("apprId");
        var exctAppNm = $(this).attr("exctAppNm");
        $("#apprId").val(id);
        $("#exctAppNm").val(exctAppNm);
        $("#mainForm").attr("action","<c:url value='/adMyDraftView.do'/>");
        $("#mainForm").submit();
        return false;
    }); */

    //결제함
    /* $("input[name=btn_appr_detail]").click(function(){
        var id = $(this).attr("apprId");
        $("#apprId").val(id);
        $("#mainForm").attr("action","<c:url value='/adMyApprovalView.do'/>");
        $("#mainForm").submit();
        return false;
    }); */

  });
//상신함
  function draft_detail(id, exctAppNm, userId){
	$("#apprId").val(id);
	$("#userId").val(userId);
	$("#exctAppNm").val(exctAppNm);
	$("#mainForm").attr("action","<c:url value='/adMyDraftView.do'/>");
	$("#mainForm").submit();
	return false;
  }
//결제함
  function appr_detail(id, appid, userId, permitGubun){
	$("#apprId").val(id);
	$("#appId").val(appid);
	$("#userId").val(userId);
	$("#permitGubun").val(permitGubun);
	$("#mainForm").attr("action","<c:url value='/adMyApprovalView.do'/>");
	$("#mainForm").submit();
	return false;
  }
//공지
  function board_detail(boardNo){
	$("#boardNo").val(boardNo);
	$("#mainForm").attr("action","<c:url value='/adNoticeView.do'/>");
	$("#mainForm").submit();
	return false;
  }
  
</script>


<!-- CONTENT START -->
<form:form commandName="voBoard" id="mainForm" name="mainForm" method="post" onsubmit="return onetimeSubmit(this)">
<input type="hidden" id="apprId" name="apprId" />
<input type="hidden" id="appId" name="appId" />
<input type="hidden" id="userId" name="userId" />
<input type="hidden" id="tabDiv" name="tabDiv" value="${voBoard.tabDiv}"/>
<input type="hidden" id="tabDiv2" name="tabDiv2" value="${voBoard.tabDiv}"/>
<input type="hidden" id="permitGubun" name="permitGubun" />

<!-- 신청함 -->
<c:if test="${grpYn eq '1'}">
	<a id="myReq" <c:if test="${voBoard.tabDiv eq 'myReq' or voBoard.tabDiv == null}">class="tab_cho_main"</c:if><c:if test="${voBoard.tabDiv ne 'myReq'}">class="tab_no_main"</c:if>>나의 신청현황</a>
	<a id="deptReq" <c:if test="${voBoard.tabDiv eq 'deptReq'}">class="tab_cho_main"</c:if><c:if test="${voBoard.tabDiv ne 'deptReq'}">class="tab_no_main"</c:if>>부서 신청현황</a>
	<c:if test="${grpYn eq '1' and mstYn eq '1'}">
		<a id="allReq" <c:if test="${voBoard.tabDiv eq 'allReq'}">class="tab_cho_main"</c:if><c:if test="${voBoard.tabDiv ne 'allReq'}">class="tab_no_main"</c:if>>전체 신청현황</a>
	</c:if>
</c:if>
<c:if test="${grpYn == '0' and mstYn eq '1'}">
	<a id="myReq" <c:if test="${voBoard.tabDiv eq 'myReq' or voBoard.tabDiv == null}">class="tab_cho_main"</c:if><c:if test="${voBoard.tabDiv ne 'myReq'}">class="tab_no_main"</c:if>>나의 신청현황</a>
	<a id="allReq" <c:if test="${voBoard.tabDiv eq 'allReq'}">class="tab_cho_main"</c:if><c:if test="${voBoard.tabDiv ne 'allReq'}">class="tab_no_main"</c:if>>전체 신청현황</a>
</c:if>
<c:if test="${mnuReqLvl == 'read'}">
<div class="img_va1"><img src="<c:url value="/image/admin/btn_right.gif"/>" />나의 신청현황
	<span style="float:right; margin-right:5px">미확인: <span style="color:#FF0000; ">${reqDoCount}</span> 건</span>
	<span style="float:right; margin-right:15px">결재완료: <span style="color:#FF0000; ">${reqCount}</span> 건</span>
</div>

<table class="tbws1"> 
 <tr>
  <!--<th style=" width:10%;">번호</th>-->
  <th style=" width:15%;">신청일</th>
  <th style="width:15%;">구분</th>
  <th style="width:10%;">사번</th>
  <th style="width:20%;">성명</th>
  <th style="width:*%;">부서</th>
  <th style="width:10%;">결재상태</th>
  <!-- <th style="width:10%;">상세</th> -->
 </tr>
 <c:choose>
  <c:when test="${fn:length(draftList)>0}">
   <c:forEach var="draft" items="${draftList}" varStatus="status">
    <c:set var="sips" value="${status.count }"/>
    <%-- <tr <c:if test="${sips % 2 == 0}">class="selectedRow3" rowType="P"</c:if> <c:if test="${sips % 2 != 0}">rowType="W"</c:if> onClick="draft_detail('${draft.apprId}','${draft.exctAppNm}','${draft.userId}');" exctAppNm="${draft.exctAppNm}" apprId="${draft.apprId}" userId="${draft.userId}"> --%>
    <tr onClick="draft_detail('${draft.apprId}','${draft.exctAppNm}','${draft.userId}');" exctAppNm="${draft.exctAppNm}" apprId="${draft.apprId}" userId="${draft.userId}">
     <!--<td><c:out value="${(voBoard.pageIndex-1)*voBoard.pageUnit + status.count}"/></td>-->
     <td><c:out value="${draft.apprDm}"/></td>
     <td><c:out value="${draft.exctAppNm}"/></td>
     <td><c:out value="${draft.appId}"/></td>
     <td><c:out value="${draft.approbjUser}"/></td>
     <td><c:out value="${draft.deptNm}"/></td>
     <td><c:out value="${draft.cdDtlNm}"/></td>
     <%-- <td><input type="button" class="swhite" value="상세" name="btn_draft_detail" exctAppNm="${draft.exctAppNm}" apprId="${draft.apprId}"/></td> --%>
    </tr>
   </c:forEach>
  </c:when>
  <c:otherwise>
   <tr><td colspan="6">신청한 내역이 없습니다.</td></tr>
  </c:otherwise>
 </c:choose>
</table>
</c:if>
<!-- //신청함 -->

<!-- 결제함 -->
<%-- <c:if test="${grpYn == '1'}">
	<a id="myApp" class="tab_cho_main">나의 결재현황</a>
	<a id="deptApp" class="tab_no_main">부서 결재현황</a>
	<c:if test="${grpYn == '1' && mstYn == '1'}">
		<a id="allApp" class="tab_no_main">전체 결재현황</a>
	</c:if>
	<c:if test="${grpYn == '0' && mstYn == '1'}">
		<a id="myAPP" class="tab_cho_main">나의 결재현황</a>
		<a id="allApp" class="tab_no_main">전체 결재현황</a>
	</c:if>
</c:if>	 --%>
	
<%-- <a id="myApp" <c:if test="${voBoard.tabDiv2 eq 'myApp' or voBoard.tabDiv2 == null}">class="tab_cho_main"</c:if><c:if test="${voBoard.tabDiv2 ne 'myApp'}">class="tab_no_main"</c:if>>나의 신청현황</a>
<a id="deptApp" <c:if test="${voBoard.tabDiv2 eq 'deptApp'}">class="tab_cho_main"</c:if><c:if test="${voBoard.tabDiv2 ne 'deptApp'}">class="tab_no_main"</c:if>>부서 신청현황</a>
<c:if test="${grpYn == '1' && mstYn == '1'}">
	<a id="allApp" <c:if test="${voBoard.tabDiv2 eq 'allApp'}">class="tab_cho_main"</c:if><c:if test="${voBoard.tabDiv2 ne 'allApp'}">class="tab_no_main"</c:if>>전체 신청현황</a>
</c:if>
<c:if test="${grpYn == '0' && mstYn == '1'}">
	<a id="myApp" <c:if test="${voBoard.tabDiv2 eq 'myApp' or voBoard.tabDiv2 == null}">class="tab_cho_main"</c:if><c:if test="${voBoard.tabDiv2 ne 'myApp'}">class="tab_no_main"</c:if>>나의 신청현황</a>
	<a id="allApp" <c:if test="${voBoard.tabDiv2 eq 'allApp'}">class="tab_cho_main"</c:if><c:if test="${voBoard.tabDiv2 ne 'allApp'}">class="tab_no_main"</c:if>>전체 신청현황</a>
</c:if>
 --%>
 <c:if test="${grpYn eq '1'}">
	<a id="myApp" <c:if test="${voBoard.tabDiv2 eq 'myApp' or voBoard.tabDiv2 == null}">class="tab_cho_main"</c:if><c:if test="${voBoard.tabDiv2 ne 'myApp'}">class="tab_no_main"</c:if>>나의 결재현황</a>
	<a id="deptApp" <c:if test="${voBoard.tabDiv2 eq 'deptApp'}">class="tab_cho_main"</c:if><c:if test="${voBoard.tabDiv2 ne 'deptApp'}">class="tab_no_main"</c:if>>부서 결재현황</a>
	<c:if test="${grpYn eq '1' && mstYn eq '1'}">
		<a id="allApp" <c:if test="${voBoard.tabDiv2 eq 'allApp'}">class="tab_cho_main"</c:if><c:if test="${voBoard.tabDiv2 ne 'allApp'}">class="tab_no_main"</c:if>>전체 결재현황</a>
	</c:if>
</c:if>
<c:if test="${grpYn eq '0' && mstYn eq '1'}">
		<a id="myApp" <c:if test="${voBoard.tabDiv2 eq 'myApp' or voBoard.tabDiv2 == null}">class="tab_cho_main"</c:if><c:if test="${voBoard.tabDiv2 ne 'myApp'}">class="tab_no_main"</c:if>>나의 결재현황</a>
		<a id="allApp" <c:if test="${voBoard.tabDiv2 eq 'allApp'}">class="tab_cho_main"</c:if><c:if test="${voBoard.tabDiv2 ne 'allApp'}">class="tab_no_main"</c:if>>전체 결재현황</a>
	</c:if>
<c:if test="${mnuApprovLvl == 'read'}">
	
	<div class="img_va1"><img src="<c:url value="/image/admin/btn_right.gif"/>" />나의 결재현황
		<span style="float:right; margin-right:5px">미확인: <span style="color:#FF0000; ">${apprDoCount} </span>건</span>
		<span style="float:right; margin-right:15px">결재완료: <span style="color:#FF0000; "> ${apprCount} </span> 건</span>
	</div>
	<table class="tbws1">
	 <tr>
	  <!--<th style=" width:5%;">번호</th>-->
	  <th style=" width:12%;">신청일자</th>
	  <th style="width:12%;">구분</th>
	  <th style="width:10%;">기안자</th>
	  <th style="width:15%;">성명</th>
	  <th style="width:15%;">부서명</th>
	  <th style="width:15%;">사용기간</th>
	  <th style="width:7%;">결재상태</th>
	  <!-- <th style="width:7%;">상세</th> -->
	 </tr>
	 <c:choose>
	  <c:when test="${fn:length(approvalList)>0}">
	   <c:forEach var="approvalList" items="${approvalList}" varStatus="status">
	   <c:set var="sips" value="${status.count }"/>
	    <%-- <tr <c:if test="${sips % 2 == 0}">class="selectedRow3" rowType="P"</c:if> <c:if test="${sips % 2 != 0}">rowType="W"</c:if>  onClick="appr_detail('${approvalList.apprId}', '${approvalList.appId}', '${approvalList.drafterId}');"  apprId="${approvalList.apprId}" appId="${approvalList.appId}" drafterId="${approvalList.drafterId}"> --%>
	    <tr onClick="appr_detail('${approvalList.apprId}', '${approvalList.appId}', '${approvalList.drafterId}' ,'${approvalList.permitGubun}');"  apprId="${approvalList.apprId}" appId="${approvalList.appId}" drafterId="${approvalList.drafterId}">
	     <!--<td><c:out value="${(voBoard.pageIndex-1)*voBoard.pageUnit + status.count}"/></td>-->
	     <td><c:out value="${approvalList.apprDm}"/></td>
	     <td><c:out value="${approvalList.exctAppNm}"/></td>
	     <td><c:out value="${approvalList.drafter}"/></td>
	     <td><c:out value="${approvalList.approbjUser}"/></td>
	     <td><c:out value="${approvalList.apprObjDept}"/></td>
	     <td><c:out value="${approvalList.startDm}"/> ~ <c:out value="${approvalList.endDm}"/></td>
	     <td>
	      <c:choose>
	       <c:when test="${approvalList.permitGubun == '03'}">
	        통보
	       </c:when>
	       <c:otherwise>
	        <c:out value="${approvalList.cdDtlNm}"/>
	       </c:otherwise>
	      </c:choose>
	     </td>
	     <%-- <td><input type="button" class="swhite" value="상세" name="btn_appr_detail" apprId="${approvalList.apprId}"/></td> --%>
	    </tr>
	   </c:forEach>
	  </c:when>
	  <c:otherwise>
	   <tr><td colspan="7">결재할 내역이 없습니다.</td></tr>
	  </c:otherwise>
	 </c:choose>
	</table>
</c:if>
<!-- //결제함 -->


<!-- 공지사항 -->
<input type="hidden" id="boardNo" name="boardNo"/>
<div class="img_va1"><img src="<c:url value="/image/admin/btn_right.gif"/>" />공지사항</div>
<table class="tbws1">
 <tr>
  <th style=" width:10%;">번호</th>
  <th style="width:*;">제목</th>
  <th style="width:15%;">등록일</th>
  <th style="width:15%;">등록자</th>
  <!-- <th style="width:15%;">상세</th> -->
 </tr>
 <c:choose>
  <c:when test="${fn:length(noticeList)>0}">
   <c:forEach var="notice" items="${noticeList}" varStatus="status" end="9">
   <c:set var="sips" value="${status.count }"/>
    <%-- <tr <c:if test="${sips % 2 == 0}">class="selectedRow3" rowType="P"</c:if> <c:if test="${sips % 2 != 0}">rowType="W"</c:if> onClick="board_detail('${notice.boardNo}');" boardNo="${notice.boardNo}"> --%>
    <tr onClick="board_detail('${notice.boardNo}');" boardNo="${notice.boardNo}">
     <td><c:out value="${(voBoard.pageIndex-1)*voBoard.pageUnit + status.count}"/></td>
     <td class="align_l"><c:out value="${notice.boardTitle}"/></td>
     <td><c:out value="${notice.regDm}"/></td>
     <td><c:out value="${notice.userId}"/></td>
     <%-- <td><input type="button" class="swhite" value="상세" name="btn_board_detail" boardNo="${notice.boardNo}"/></td> --%>
    </tr>
   </c:forEach>
  </c:when>
  <c:otherwise>
   <tr><td colspan="4">데이터가 없습니다.</td></tr>
  </c:otherwise>
 </c:choose>
</table>
<!-- //공지사항 -->

</form:form>
<!-- //CONTENT START -->