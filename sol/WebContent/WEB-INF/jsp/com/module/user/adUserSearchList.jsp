<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javaScript" src="/admin/sitemap/common/OnetimeSubmit.js"></script>
<script type="text/javaScript" defer="defer">

  $(document).ready(function(){

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
    
    //액션완료 메시지 띄우기
    var checkMessage = $("#checkMessage").val();
    if(checkMessage == "success_del"){
      $.prompt("삭제가 완료 되었습니다.", {
          buttons: { "예": true },
          submit: function(e,v,m,f){
            if (v) {
                $("#checkMessage").val("");
                $("#userForm").attr("action","<c:url value='/adUserSearchList.do'/>");
                $("#userForm").submit();
            }
          }
      });
      return false;
    }

    //검색버튼
    $("#searchForm").click(function(){
      $("#pageIndex").val(1);
      $("#userForm").attr("action","<c:url value='/adUserSearchList.do'/>");
      $("#userForm").submit();
      return false;
    });

    $("#searchKeyword").keyup(function(e){
        if(e.keyCode == "13"){
          $("#pageIndex").val(1);
          $("#userForm").attr("action","<c:url value='/adUserSearchList.do'/>");
          $("#userForm").submit();
          return false;
      }
    });

    //초기화버튼
    $("#resetForm").click(function(){
        $("#searchCd").val("01");
        $("#searchKeyword").val("");
        $("#searchUserGrpNo").val("");
        $("#searchGrpNm").val("");
    });

    /*삭제버튼 클릭 팝업 이벤트*/
    $("#btn_delete").click(function(){
      if($("input[name=chk_each]:checkbox:checked").length <= 0){
        $.prompt("삭제 할 사용자를 선택 하세요.");
        return false;
      }else{

        $.prompt("선택된 사용자를 삭제 하시겠습니까?", {
          buttons: { "예": true, "아니오": false },
          submit: function(e,v,m,f){
            if (v) {
                $("#checkMessage").val("success_del");
                var delArr = "";
                $("input[name=chk_each]:checked").each(function(){
                  delArr = delArr + $(this).attr('userId') + ",";
                  $("#delArr").val(delArr);
                });
                $("#userForm").attr("action","<c:url value='/adUserDelete.do'/>");
                $("#userForm").submit();
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
      }
      else
      {
        $('input[name=chk_each]').attr("checked", false);
      }
    });

    /* 별도 선택시 전체체크 해제 function */
    $("input[name=chk_each]").click(function(){
      $("input[name=chk_all]").attr("checked", false);
    });

   /* 사용자등록 */
   $("#btn_regist").click(function(){
     $("#userForm").attr("action","<c:url value='/adUserRegist.do'/>");
     $("#userForm").submit();
     return false;
   });

   $("#btn_excel").click(function(){
     $("#userForm").attr("action","<c:url value='/adUserExcelList.do'/>");
     $("#userForm").submit();
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
    $("#userForm").attr("action","<c:url value='/adUserSearchList.do'/>");
    $("#userForm").submit();
  }

  //사용자그룹선택에서 불려지는 함수
  function setParentUserGrpParams(userGrpNo,grpNm){
    $('#searchUserGrpNo').val(userGrpNo);
    $('#searchGrpNm').val(grpNm);
  }

  /* 사용자상세 */
  function board_detail(userId){
    $("#userId").val(userId);
    $("#userForm").attr("action","<c:url value='/adUserModify.do'/>");
    $("#userForm").submit();
    return false;
  }
</script>

<!-- HEADER START -->
<h3>시스템 &gt; 사용자관리 &gt; 검색</h3>
<!-- HEADER AND -->

<!-- CONTENT START -->
<form:form commandName="searchVoCoUser" id="userForm" name="userForm" method="post" onsubmit="return onetimeSubmit(this)">
  <input type="hidden" id="userId" name="userId">
  <input type="hidden" id="delArr" name="delArr"/>
  <input type="hidden" id="dept_cd" name="dept_cd" />
  <input type="hidden" id="checkMessage" name="checkMessage" value="${searchVoCoUser.checkMessage}"/>

    <!-- 검색 -->
    <div class="img_va"><img src="<c:url value="/image/admin/btn_right.gif"/>" />검색</div>
    <table class="tbws0">
      <tr>
        <th>조회구분</th>
        <td class="align_l" style="width:100px">
          <form:select id="searchCd" name="searchCd" path="searchCd" >
            <form:option value="01">사번</form:option>
            <form:option value="02">성명</form:option>
            <form:option value="03">부서</form:option>
            <form:option value="04">사용자그룹</form:option>
          </form:select>
        </td>
        <th>검색어</th>
        <td class="align_l">
          <form:input id="searchKeyword" name="searchKeyword" path="searchKeyword" style="width:200px;" />
        </td>
      </tr>
      <tr>
        <th>사용자그룹</th>
        <td colspan="3" class="align_l">
          <input type="hidden" id="searchUserGrpNo" name="searchUserGrpNo" value="${searchVoCoUser.searchUserGrpNo}" />
          <input type="text" id="searchGrpNm" name="searchGrpNm" value="${searchVoCoUser.searchGrpNm}" readonly="readonly" />
          <input type="button"  style="text-align:center;" value="검색" id="selectUserGrp_bt" class="swhite" /><p>
        </td>
      </tr>
    </table>
    <div class="btn_area">
     <!-- <div class="right" style="margin:0px 3px 0px 3px"><input type="button" id="resetForm" class="nwhite" value="초기화"/></div> -->
     <div class="right" style="margin:0px 3px 0px 3px"><input type="button" id="searchForm" class="nwhite" value="검색"/></div>
    </div>
    <br /><input type="hidden" name="actionId">
	
	<div class="btn_area">
      <div class="left" style="margin:0px 3px 5px 3px"><input type="button" id="btn_delete" class="nwhite" value="삭제"/></div>

      <div class="right" style="margin:0px 3px 5px 3px"><input type="button" id="btn_regist" class="nwhite" value="등록"/></div>
      <div class="right" style="margin:0px 3px 5px 3px"><input type="button" id="btn_excel" class="nwhite" value="엑셀다운로드"/></div>
   </div>
    <!-- 검색 -->
    <table class="tbws1">
      <tr>
        <th scope="col" class="lcheck" style="width:5%;" ><input type="checkbox" name="chk_all" id="chk_all" style="cursor:pointer;"/></th>
        <th style="width:25%;">사번</th>
        <th style="width:25%;">성명</th>
        <th style="width:45%;">사용자그룹</th>
        <!-- <th style="width:5%;">상세</th> -->
      </tr>

      <c:choose>
        <c:when test="${fn:length(rsUserList)>0 }">
          <c:forEach var="user_info" items="${rsUserList}" varStatus="status">
            <c:set var="sips" value="${status.count }"/>
    		<tr>
              <td><input type="checkbox" name="chk_each" style="cursor:pointer;" userId="${user_info.userId}"/></td>
              <td class="align_l" onClick="board_detail('${user_info.userId}')" userId="${user_info.userId}">${user_info.userId}</td>
              <td class="align_l" onClick="board_detail('${user_info.userId}')" userId="${user_info.userId}">${user_info.userNm}</td>
              <td class="align_l" onClick="board_detail('${user_info.userId}')" userId="${user_info.userId}">${user_info.grpNm}</td>
              <%-- <td>
                <a class="btn_detail" style="cursor: pointer;" userId="${user_info.userId}" >
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
    <input type="hidden" name="listSize" id="listSize" value="${fn:length(rsUserList)}"/>
	
	<!-- paginate-->
    <div class="page_area">
      <div class="paginate">
        페이지:<ui:pagination paginationInfo = "${voCoUser}" type="ad_image" jsFunction="linkPage"/>
        <form:hidden path="pageIndex" id="pageIndex" />
      </div>
      <div class="pagetotal">총건수:<span>${totCnt}</span>건</div>
    </div>
    <!-- //paginate -->

</form:form>
<!-- CONTENT END -->
<div class="mskbg" id="pop_bg" style="display:none;"><div class="msk"></div></div> <!-- 흰색 마스크 레이어 display로 제어-->
<%//그룹선택%>
<jsp:include page="../../popup/selectUserGroup.jsp" />