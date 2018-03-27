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
                $("#deptForm").attr("action","<c:url value='/adDeptDelete.do'/>");
                $("#deptForm").submit();
            }
          }
      });
      return false;
    }else if(checkMessage == "fail_del"){
       $.prompt("사용자가 소속되있는 그룹은 삭제하실 수 없습니다.", {
           buttons: { "확인": true },
           submit: function(e,v,m,f){
             if (v) {
               $("#checkMessage").val("");
               $("#deptForm").attr("action","<c:url value='/adDeptSearchList.do'/>");
               $("#deptForm").submit();
             }
           }
         });
     }

    //검색버튼
    $("#searchForm").click(function(){
      $("#pageIndex").val(1);
      $("#deptForm").attr("action","<c:url value='/adDeptSearchList.do'/>");
      $("#deptForm").submit();
      return false;
    });

    $("#searchDeptCd").keyup(function(e){
        if(e.keyCode == "13"){
          $("#pageIndex").val(1);
          $("#deptForm").attr("action","<c:url value='/adDeptSearchList.do'/>");
          $("#deptForm").submit();
          return false;
      }
    });

    $("#searchDeptNm").keyup(function(e){
        if(e.keyCode == "13"){
          $("#pageIndex").val(1);
          $("#deptForm").attr("action","<c:url value='/adDeptSearchList.do'/>");
          $("#deptForm").submit();
          return false;
      }
    });

    //초기화버튼
    $("#resetForm").click(function(){
        $("#searchDeptCd").val("");
        $("#searchDeptNm").val("");
    });

    /*삭제버튼 클릭 팝업 이벤트*/
    $("#btn_delete").click(function(){
      if($("input[name=chk_each]:checkbox:checked").length <= 0){
        $.prompt("삭제 할 부서를 선택 하세요.");
        return false;
      }else{

        $.prompt("선택된 부서를 삭제 하시겠습니까?", {
          buttons: { "예": true, "아니오": false },
          submit: function(e,v,m,f){
            if (v) {
                $("#checkMessage").val("success_del");
                var delArr = "";
                $("input[name=chk_each]:checked").each(function(){
                  delArr = delArr + $(this).attr('deptNo') + ",";
                  $("#delArr").val(delArr);
                });
                $("#deptForm").attr("action","<c:url value='/adDeptDelete.do'/>");
                $("#deptForm").submit();
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

    //검색하기(엔터키)
    $("#searchDeptNm").keypress(function(e){
      if (e.keyCode == 13){
        $("#pageIndex").val(1);
        $("#deptForm").attr("action","<c:url value='/adDeptSearchList.do'/>");
        $("#deptForm").submit();
        return false;
      }
    });

   /* 부서추가 */
   $("#btn_regist").click(function(){
     $("#deptForm").attr("action","<c:url value='/adDeptRegist.do'/>");
     $("#deptForm").submit();
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
    $("#deptForm").attr("action","<c:url value='/adDeptSearchList.do'/>");
    $("#deptForm").submit();
  }
  
  /* 부서상세 */
  function board_detail(deptNo){
    $("#deptNo").val(deptNo);
    $("#deptForm").attr("action","<c:url value='/adDeptModify.do'/>");
    $("#deptForm").submit();
    return false;
  }

</script>

<!-- HEADER START -->
<h3>시스템 &gt; 부서관리 &gt; 검색</h3>
<!-- HEADER AND -->

<!-- CONTENT START -->
<form:form commandName="searchVoCoDept" id="deptForm" name="deptForm" method="post" onsubmit="return onetimeSubmit(this)">
  <input type="hidden" id="deptNo" name="deptNo">
  <input type="hidden" id="delArr" name="delArr"/>
  <input type="hidden" id="dept_cd" name="dept_cd" />
  <input type="hidden" id="checkMessage" name="checkMessage" value="${searchVoCoDept.checkMessage}"/>

   <!-- 검색 -->
   <div class="img_va"><img src="<c:url value="/image/admin/btn_right.gif"/>" />검색</div>
   <table class="tbws0">
     <tr>
     	<th colspan="2" ><input type="radio" name="searchStatus" class="searchStatus" value="0" checked> 부서코드 <input type="radio" style="margin-left:10px" name="searchStatus" class="searchStatus" value="1"> 부서명 </th>
 		<td class="align_l"><input type="text" class="middle2" id="searchDeptNm" name="searchDeptNm" value="${searchVoCoDept.searchDeptNm}"/></td>
       
       <%-- <th>부서코드</th>
       <td class="align_l">
         <form:input id="searchDeptCd" name="searchDeptCd" path="searchDeptCd" style="width:200px;" />
       </td>
       <th>부서명</th>
       <td class="align_l">
         <form:input id="searchDeptNm" name="searchDeptNm" path="searchDeptNm" style="width:200px;" />
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
       <th scope="col" class="lcheck" style="width:5%;" ><input type="checkbox" name="chk_all" id="chk_all" /></th>
       <th style="width:40%;">부서코드</th>
       <th style="width:55%;">부서명</th>
       <!-- <th style="width:40%;">설명</th> -->
       <!-- <th style="width:5%;">상세</th> -->
     </tr>

     <c:choose>
       <c:when test="${fn:length(rsDeptList)>0 }">
         <c:forEach var="dept" items="${rsDeptList}" varStatus="status">
           <c:set var="sips" value="${status.count }"/>
    		<tr>
             <td><input type="checkbox" name="chk_each" style="cursor:pointer;" deptNo="${dept.deptNo}"/></td>
             <td class="align_l" onClick="board_detail('${dept.deptNo}')" deptNo="${dept.deptNo}">${dept.deptCd}</td>
             <td class="align_l" onClick="board_detail('${dept.deptNo}')" deptNo="${dept.deptNo}">${dept.deptNm}</td>
             <%-- <td class="align_l">${dept.deptExp}</td> --%>
             <%-- <td>
               <a class="btn_detail" style="cursor: pointer;" deptNo="${dept.deptNo}" >
                 <input type="button" name="" value="상세" class="swhite" />
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
   <input type="hidden" name="listSize" id="listSize" value="${fn:length(rsDeptList)}"/>

	<!-- paginate-->
   <div class="page_area">
     <div class="paginate">
       페이지:<ui:pagination paginationInfo = "${voCoDept}" type="ad_image" jsFunction="linkPage"/>
       <form:hidden path="pageIndex" id="pageIndex" />
     </div>
     <div class="pagetotal">총건수:<span>${totCnt}</span>건</div>
   </div>
   <!-- //paginate -->
   

</form:form>
<!-- CONTENT END -->