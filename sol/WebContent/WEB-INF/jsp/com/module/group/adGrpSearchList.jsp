<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javaScript" src="/admin/sitemap/common/OnetimeSubmit.js"></script>
<script type="text/javaScript" defer="defer">

  $(document).ready(function(){

    //메뉴설정
    openMenu('01');


    //액션완료 메시지 띄우기
    var checkMessage = $("#checkMessage").val();
    if(checkMessage == "success_del"){
      $.prompt("삭제가 완료 되었습니다.", {
          buttons: { "예": true },
          submit: function(e,v,m,f){
            if (v) {
                $("#checkMessage").val("");
                $("#grpForm").attr("action","<c:url value='/adGrpSearchList.do'/>");
                $("#grpForm").submit();
            }
          }
      });
      return false;
    }else if(checkMessage == "success_reg"){
        $.prompt("등록 완료 되었습니다.", {
          buttons: { "확인": true },
          submit: function(e,v,m,f){
            if (v) {
              $("#checkMessage").val("");
            }
          }
        });
    }else if(checkMessage == "fail_del"){
        $.prompt("사용자가 소속되있는 그룹은 삭제하실 수 없습니다.", {
            buttons: { "확인": true },
            submit: function(e,v,m,f){
              if (v) {
                $("#checkMessage").val("");
                $("#grpForm").attr("action","<c:url value='/adGrpSearchList.do'/>");
                $("#grpForm").submit();
              }
            }
          });
      }

    //검색버튼
    $("#searchForm").click(function(){
      $("#pageIndex").val(1);
      $("#grpForm").attr("action","<c:url value='/adGrpSearchList.do'/>");
      $("#grpForm").submit();
      return false;
    });

    $("#searchGrpId").keyup(function(e){
        if(e.keyCode == "13"){
          $("#pageIndex").val(1);
          $("#grpForm").attr("action","<c:url value='/adGrpSearchList.do'/>");
          $("#grpForm").submit();
          return false;
      }
    });

    $("#searchGrpNm").keyup(function(e){
        if(e.keyCode == "13"){
          $("#pageIndex").val(1);
          $("#grpForm").attr("action","<c:url value='/adGrpSearchList.do'/>");
          $("#grpForm").submit();
          return false;
      }
    });

    //초기화버튼
    $("#resetForm").click(function(){
        $("#searchGrpId").val("");
        $("#searchGrpNm").val("");
    });

    /*삭제버튼 클릭 팝업 이벤트*/
    $("#btn_delete").click(function(){
      if($("input[name=chk_each]:checkbox:checked").length <= 0){
        $.prompt("삭제 할 그룹을 선택 하세요.");
        return false;
      }else{

        $.prompt("선택된 그룹을 삭제 하시겠습니까?", {
          buttons: { "예": true, "아니오": false },
          submit: function(e,v,m,f){
            if (v) {
                $("#checkMessage").val("success_del");
                var delArr = "";
                $("input[name=chk_each]:checked").each(function(){
                  delArr = delArr + $(this).attr('userGrpNo') + ",";
                  $("#delArr").val(delArr);
                });
                $("#grpForm").attr("action","<c:url value='/adGrpDelete.do'/>");
                $("#grpForm").submit();
            }
          }
        });
        return false;
      }
    });

    /* checkbox 전체선택,해제 function */
    $("#chk_all").click(function(){
      if($("#chk_all").attr("checked") == true){
        $("input[name=chk_each]").attr("checked", true);
        $('.tbws1 tr').attr("class",'selectedRow2');
      }
      else
      {
        $('input[name=chk_each]').attr("checked", false);
        $('.tbws1 tr').attr("class",'');
      }
    });

    /* 별도 선택시 전체체크 해제 function */
    $("input[name=chk_each]").click(function(){
      $("input[name=chk_all]").attr("checked", false);
    });

    //검색하기(엔터키)
    $("#searchDeptNm").keypress(function(e){
      if (e.keyCode == 13){
        $("#pageIndex").val(1);
        $("#grpForm").attr("action","<c:url value='/adGrpSearchList.do'/>");
        $("#grpForm").submit();
        return false;
      }
    });

   $('.tbws1 tr').mouseover(function(){
   	$(this).attr("class",'selectedRow2');
		//$(this).addClass('selectedRow2');

   }).mouseout(function() {
	$(this).attr("class",'');
   	/* if($(this).attr("rowType") == "W")
   	{
      		$(this).attr('class', '');
   	}
   	else if($(this).attr("rowType") == "P")
  		{
   		$(this).attr('class', 'selectedRow3');
  		} */
	   //$(this).attr("class",'');
   });

   /* 부서추가 */
   $("#btn_regist").click(function(){
     $("#grpForm").attr("action","<c:url value='/adGrpRegist.do'/>");
     $("#grpForm").submit();
     return false;
   });
   
   /* 권한처리 */
   <c:if test="${mnuLvlCd != 'write'}">
   		$("#btn_regist").attr("disabled", "disabled").attr("style", "color:#ABABAB");
   		$("#btn_delete").attr("disabled", "disabled").attr("style", "color:#ABABAB");
   </c:if>


  });
  /* pagination 페이지 링크 function */
  function linkPage(pageNo){
    $("#pageIndex").val(pageNo);
    $("#grpForm").attr("action","<c:url value='/adGrpSearchList.do'/>");
    $("#grpForm").submit();
  }
	
  /* 부서상세 */
  function board_detail(userGrpNo){
    $("#userGrpNo").val(userGrpNo);
    $("#grpForm").attr("action","<c:url value='/adGrpModify.do'/>");
    $("#grpForm").submit();
    return false;
  }
  
  function chkBoard(i){
	if($("input[name=chk_each]").attr("checked") == true){
		$("#tr_"+i).attr("class",'selectedRow2');
	}else{
		$("#tr_"+i).attr("class",'');
	}
    return false;
  }
</script>

<!-- HEADER START -->
<h3>시스템 &gt; 그룹관리 &gt; 검색</h3>
<!-- HEADER AND -->

<!-- CONTENT START -->
<form:form commandName="searchVoCoUserGrp" id="grpForm" name="grpForm" method="post" onsubmit="return onetimeSubmit(this)">
  <input type="hidden" id="userGrpNo" name="userGrpNo">
  <input type="hidden" id="delArr" name="delArr"/>
  <input type="hidden" id="dept_cd" name="dept_cd" />
  <input type="hidden" id="checkMessage" name="checkMessage" value="${searchVoCoUserGrp.checkMessage}"/>

  <div class="img_va"><img src="<c:url value="/image/admin/btn_right.gif"/>" />검색</div>
    <!-- 검색 -->
    <table class="tbws0">
      <tr>
      	<th colspan="2" ><input type="radio" name="searchStatus" class="searchStatus" value="0" checked> 그룹ID <input type="radio" style="margin-left:10px" name="searchStatus" class="searchStatus" value="1"> 그룹명 </th>
 		<td class="align_l"><input type="text" class="middle2" id="searchGrpId" name="searchGrpId" value="${searchVoCoUserGrp.searchGrpId}"/></td>
 	
        <%-- <th>그룹ID</th>
        <td class="align_l">
          <form:input id="searchGrpId" name="searchGrpId" path="searchGrpId" style="width:200px;" />
        </td>
        <th>그룹명</th>
        <td class="align_l">
          <form:input id="searchGrpNm" name="searchGrpNm" path="searchGrpNm" style="width:200px;" />
        </td> --%>
      </tr>
    </table>
   <div class="btn_area">
    <!-- <div class="right" style="margin:0px 3px 0px 3px"><input type="button" id="resetForm" class="nwhite" value="초기화"/></div> -->
    <div class="right" style="margin:0px 3px 0px 3px"><input type="button" id="searchForm" class="nwhite" value="검색"/></div>
   </div>
   <br /><input type="hidden" name="actionId">
    <!-- 검색 -->
    <div class="btn_area">
      <div class="left" style="margin:0px 3px 5px 3px"><input type="button" id="btn_delete" class="nwhite" value="삭제"/></div>
      <div class="right" style="margin:0px 3px 5px 3px"><input type="button" id="btn_regist" class="nwhite" value="등록"/></div>
    </div>
    <table class="tbws1">
      <tr>
        <th scope="col" class="lcheck" style="width:5%;" ><input type="checkbox" name="chk_all" id="chk_all" style="cursor:pointer;"/></th>
        <th style="width:25%;">그룹ID</th>
        <th style="width:25%;">그룹명</th>
        <th style="width:35%;">그룹설명</th>
        <th style="width:15%;">결재권한</th>
        <!-- <th style="width:5%;">상세</th> -->
      </tr>

      <c:choose>
        <c:when test="${fn:length(rsUserGrpList)>0 }">
          <c:forEach var="grp" items="${rsUserGrpList}" varStatus="status">
             <c:set var="sips" value="${status.count }"/>
             <tr id="tr_${status.count}">
              <td><input type="checkbox" name="chk_each" style="cursor:pointer;" userGrpNo="${grp.userGrpNo}" onClick="chkBoard('${status.count}')"/></td>
              <td class="align_l" onClick="board_detail('${grp.userGrpNo}')"  userGrpNo="${grp.userGrpNo}">${grp.userGrpId}</td>
              <td class="align_l" onClick="board_detail('${grp.userGrpNo}')"  userGrpNo="${grp.userGrpNo}">${grp.grpNm}</td>
              <td class="align_l" onClick="board_detail('${grp.userGrpNo}')"  userGrpNo="${grp.userGrpNo}">${grp.grpExp}</td>
              <td>
                <c:if test="${grp.apprYn eq '1'}">○</c:if>
                <c:if test="${grp.apprYn ne '1'}">×</c:if>
              </td>
              <%-- <td>
                <a class="btn_detail" style="cursor: pointer;" userGrpNo="${grp.userGrpNo}" >
                  <input type="button" name="" value="상세" class="swhite" style="cursor:pointer;"/>
                </a>
              </td> --%>
            </tr>
          </c:forEach>
        </c:when>
        <c:otherwise>
            <tr>
              <td colspan="6">검색결과가 없습니다.</td>
            </tr>
        </c:otherwise>
      </c:choose>
    </table>
    <input type="hidden" name="listSize" id="listSize" value="${fn:length(rsUserGrpList)}"/>
	 <!-- paginate-->
   <div class="page_area">
     <div class="paginate">
       페이지:<ui:pagination paginationInfo = "${voCoUserGrp}" type="ad_image" jsFunction="linkPage"/>
       <form:hidden path="pageIndex" id="pageIndex" />
     </div>
     <div class="pagetotal">총건수:<span>${totCnt}</span>건</div>
   </div>
   <!-- //paginate -->
</form:form>
<!-- CONTENT END -->