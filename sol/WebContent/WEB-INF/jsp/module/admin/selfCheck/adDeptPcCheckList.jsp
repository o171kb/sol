<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="tt.base.TtSession"%>
<%@page import="tt.base.TtHttpSessionManager"%>
<%@page import="tt.com.constant.CsCoConstDef"%>
<%@page import="tt.com.bean.VoCoMenu"%>
<%@page import="tt.com.utils.UtCoFileNameUtils"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
//############################################################################
//메뉴클릭시 서브메뉴 OPEN  : 다른 액션에서 열고 닫기 문제가 있음 사용시 수정후 사용할것
//############################################################################

  TtSession tts = (TtSession) TtHttpSessionManager.getTtSession(request);
  List<VoCoMenu> userMenuList = (List<VoCoMenu>)tts.get(CsCoConstDef.SS_KEY_018);
  if (null == userMenuList) {
      throw new Exception("로그인정보가 없습니다.");
  }
  pageContext.setAttribute("userMenuList", userMenuList);
  //메뉴초기설정
  String url = request.getAttribute("javax.servlet.forward.request_uri").toString();
  pageContext.setAttribute("mu_url", UtCoFileNameUtils.getBaseName(url));
  pageContext.setAttribute("user_id", (String)tts.get(CsCoConstDef.SS_KEY_003));
  pageContext.setAttribute("user_ip", request.getRemoteAddr());
  pageContext.setAttribute("dept_cd", (String)tts.get("deptCd"));
  pageContext.setAttribute("dept_nm", (String)tts.get("deptNm"));

%>
<script type="text/javaScript" src="/admin/sitemap/common/OnetimeSubmit.js"></script>
<script type='text/javascript' src="<c:url value='/dwr/interface/dwrSelfPcChkManagement.js'/>"></script>


<h3>자가진단 &gt; 부서PC점검현황</h3>
<form:form commandName="voPcBasic" id="selfForm" name="selfForm" method="post" onsubmit="return onetimeSubmit(this)">
<input type="hidden" id="searchDate" name="searchDate" value="${voPcBasic.searchDate}"/>
<input type="hidden" id="safeStandard" name="safeStandard" value="1"/>
<div style="background-color: #f0f4f7; margin:20px 0px 20px 0px; height:90px; vertical-align: middle;">
   <table style="padding:10px 0px 10px 0px;"> <!--  border: 1px solid red;"> -->
     <tr height="30px;">
       <th><b>점검률 산출 :</b> </th>
       <td>
          <input type="radio" id="" name="date_radio" value="6" <c:if test="${voPcBasic.searchDate == '6'}">checked="checked"</c:if>/> 최근 6개월내 점검
          <input type="radio" id="" name="date_radio" value="3" <c:if test="${voPcBasic.searchDate == '3'}">checked="checked"</c:if>/> 최근 3개월내 점검
          <input type="radio" id="" name="date_radio" value="1" <c:if test="${voPcBasic.searchDate == '1'}">checked="checked"</c:if>/> 최근 1개월내 점검
          <input type="radio" id="" name="date_radio" value="0" <c:if test="${voPcBasic.searchDate == '0'}">checked="checked"</c:if>/> 기간상관없이 전체
       </td>
     </tr>
     <tr  height="30px;">
       <th><b>안전율 산출 :</b> </th>
       <td>
          <input type="radio" id="" name="safety_radio" value="1" checked="checked"/> 안전한 항목기준
          <input type="radio" id="" name="safety_radio" value="0" /> 안전한 PC기준
       </td>
     </tr>
   </table>
</div>

<table class="tbws1" style="width:100%">
 <tr>
   <th rowspan="2">부서명</th>
   <th rowspan="2">대상자</th>
   <th rowspan="2">점검자</th>
   <th rowspan="2">점검률</th>
   <th rowspan="2">점검PC</th>
   <th colspan="3">점검결과(23항목, PC 1대당 평균)</th>
 </tr>
 <tr>
   <th>안전항목</th>
   <th>취약항목</th>
   <th>안전율</th>
 </tr>
    <tr>
       <td>${voCoDept.deptNm}</td>
       <td><a style="cursor:pointer; font-weight:bold" class="registerPopup">${register}</a></td>
       <td><a style="cursor:pointer; font-weight:bold" class="pcChkUserPopup">${selfChkResult}</a></td>
       <td>
          <c:choose>
           <c:when test="${register != '0' && selfChkResult != '0'}">
            <fmt:formatNumber value="${selfChkResult/register*100}" pattern="###"/>%
           </c:when>
           <c:otherwise>
               0%
           </c:otherwise>
          </c:choose>
       </td>
       <td><a style="cursor:pointer; font-weight:bold" class="pcChkPopup">${selfChkPcResult}</a></td>
       <td>
         <c:choose>
           <c:when test="${safetyIdx != '0' && selfChkPcResult != '0' }">
             <fmt:formatNumber value="${safetyIdx/selfChkPcResult}" pattern="0.0"/>
           </c:when>
           <c:otherwise>
              0
           </c:otherwise>
         </c:choose>
       </td>
       <td>
          <c:choose>
           <c:when test="${notSafetyIdx != '0' && selfChkPcResult != '0' }">
             <a style="cursor:pointer; font-weight:bold" class="noSafetyPopup">
               <fmt:formatNumber value="${notSafetyIdx/selfChkPcResult}" pattern="0.0"/>
             </a>
           </c:when>
           <c:otherwise>
              0
           </c:otherwise>
         </c:choose>
       </td>
       <td>
          <span id="safety_html" style="margin-left:40px;"></span>
       </td>
    </tr>
</table>
</form:form>

<div class="mskbg" id="pop_bg" style="display:none;"><div class="msk"></div></div> <!-- 흰색 마스크 레이어 display로 제어-->




<form:form commandName="searchVoCoUser" id="deptPcForm" name="deptPcForm" method="post" onsubmit="return onetimeSubmit(this)">
<input type="hidden" id="deptCd" name="deptCd" value="${dept_cd}"/>

<input type="hidden" id="macAddList" name="macAddList" value="${macAddList}" size="100"/>

<!-- 대상자 -->
<div id="registerPopup" style="display: none;">
 <div id="main2">
  <div id="search_area">
    <b><span style="color: blue">점검율 산출 :</span></b><span class="searchDate_html"></span>
    <b><span style="color: blue; padding-left: 20px;">안전율 산출 :</span></b><span class="safeStandard_html"></span>
    <b><span style="color: blue; padding-left: 20px;">점검방식 :</span></b> 자율
  </div>
  <div class="btn_area">
   <div class="right" style="margin:0px 3px 5px 3px"><input type="button" name="btn_excel" val="adDeptPcExcelRegiPopup" class="nwhite" value="엑셀다운로드"/></div>
  </div>
  <table border=1 class="tbws1" id="registerList">
     <tr>
       <th>No.</th>
       <th>부서명</th>
       <th>성명</th>
       <th>직급/직책</th>
       <th>사번</th>
       <th>최종점검일</th>
       <th>점검결과</th>
       <th>점검PC수</th>
     </tr>
   </table>
    <!-- paginate-->
     <div class="page_area_ext">
     </div>
    <!-- //paginate -->
  </div>
</div>
<!-- //대상자 -->


<!-- 점검자 -->
<input type="hidden" id="pcUser" name="pcUser" value="${pcUser}">
<div id="pcChkUserPopup" style="display: none;">
 <div id="main2">

  <div id="search_area">
    <b><span style="color: blue">점검율 산출 :</span></b><span class="searchDate_html"></span>
    <b><span style="color: blue; padding-left: 20px;">안전율 산출 :</span></b><span class="safeStandard_html"></span>
    <b><span style="color: blue; padding-left: 20px;">점검방식 :</span></b> 자율
  </div>
  <div class="btn_area">
   <div class="right" style="margin:0px 3px 5px 3px"><input type="button" name="btn_excel" val="adDeptPcExcelPcChkUserPopup" class="nwhite" value="엑셀다운로드"/></div>
  </div>
  <table border=1 class="tbws1" id="pcChkUserList">
     <tr>
       <th>No.</th>
       <th>부서명</th>
       <th>성명</th>
       <th>직급/직책</th>
       <th>사번</th>
       <th>최종점검일</th>
       <th>점검결과</th>
       <th>시스템 제조번호</th>
       <th>IP주소</th>
     </tr>
   </table>
    <!-- paginate-->
     <div class="page_area_ext">
     </div>
    <!-- //paginate -->
  </div>
</div>
<!-- //점검자 -->


<!-- 점검PC -->
<div id="pcChkPopup" style="display: none;">
 <div id="main2">

  <div id="search_area">
    <b><span style="color: blue">점검율 산출 :</span></b><span class="searchDate_html"></span>
    <b><span style="color: blue; padding-left: 20px;">안전율 산출 :</span></b><span class="safeStandard_html"></span>
    <b><span style="color: blue; padding-left: 20px;">점검방식 :</span></b> 자율
  </div>
  <div class="btn_area">
   <div class="right" style="margin:0px 3px 5px 3px"><input type="button" name="btn_excel" val="adDeptPcExcelChkPcPopup" class="nwhite" value="엑셀다운로드"/></div>
  </div>
  <table border=1 class="tbws1" id="pcChkList">
     <tr>
       <th>No.</th>
       <th>부서명</th>
       <th>성명</th>
       <th>직급/직책</th>
       <th>사번</th>
       <th>최종점검일</th>
       <th>점검결과</th>
       <th>시스템 제조번호</th>
     </tr>
   </table>
    <!-- paginate-->
     <div class="page_area_ext">
     </div>
    <!-- //paginate -->  </div>
</div>
<!-- //점검PC -->


<!-- 취약항목 -->
<div id="noSafetyPopup" style="display: none;">
 <div id="main2">

  <div id="search_area">
    <b><span style="color: blue">점검율 산출 :</span></b><span class="searchDate_html"></span>
    <b><span style="color: blue; padding-left: 20px;">안전율 산출 :</span></b><span class="safeStandard_html"></span>
    <b><span style="color: blue; padding-left: 20px;">점검방식 :</span></b> 자율
  </div>
  <div class="btn_area">
   <div class="left"><a id="noSafety_userSearch" href="#"><b>점검자별 보기</b></a> | <a id="noSafety_attrSearch" href="#" style="color:black;">취약항목별 보기</a></div>
   <div class="right" style="margin:0px 3px 5px 3px"><input type="button" name="btn_excel" val="adDeptPcExcelNoSafePopup" class="nwhite" value="엑셀다운로드"/></div>
  </div>
  <table border=1 class="tbws1" id="noSafetyList">
     <tr>
       <th>No.</th>
       <th>부서명</th>
       <th>성명</th>
       <th>사번</th>
       <th>최종점검일</th>
       <th>취약건수</th>
       <th>취약항목</th>
       <th>시스템 제조번호</th>
       <th>IP</th>
     </tr>
   </table>
    <!-- paginate-->
     <div class="page_area_ext">
     </div>
    <!-- //paginate -->  </div>
</div>
<!-- //취약항목 -->


</form:form>



<script type="text/javaScript" defer="defer">

  $(document).ready(function(){
  //메뉴설정
      openMenu('03');
      var cal1 = "${safetyIdx/(selfChkPcResult*23)*100}";
      var cal2 = "${allSafetyIdx/selfChkPcResult*100}";
      var safety = 0;
      var safetyPc = 0;
      if(!isNaN(cal1)){
        safety = Math.floor(cal1);
      }
      if(!isNaN(cal2)){
        safetyPc = Math.floor(cal2);
      }

      $("#safety_html").html(safety + "%");

      var value = "1";
      $("input[name='safety_radio']").click(function(){
          value = $("input[name='safety_radio']:checked").val();
          if(value == '1'){
              $("#safety_html").html(safety + "%");
              $("#safeStandard").val('1');
          }else if(value == '0'){
              $("#safety_html").html(safetyPc + "%");
              $("#safeStandard").val('0');
          }
      });

      var date = "0";
      $("input[name='date_radio']").click(function(){
        date = $("input[name='date_radio']:checked").val();
        $("#searchDate").val(date);
        $("#selfForm").attr("action","<c:url value='/adDeptPcCheckList.do'/>");
        $("#selfForm").submit();
        return false;
      });

      //대상자 팝업
      $(".registerPopup").click(function(){
        var center_name = '점검 대상자';
        selectUserGrpListDwr(1,1);
          $("#pop_bg").css("display","block");
          $("#registerPopup").dialog({
            title:center_name,
            autoOpen: false,
            width: 800,
            height: 500,
            modal: false,
            close: function(ev, ui) {
               $("#pop_bg").hide();
               //popupResetForm();
               $("#registerList").html("");
               $("#registerList").append("<tr><th>No.</th><th>부서명</th><th>성명</th><th>직급/직책</th><th>사번</th><th>최종점검일</th><th>점검결과</th><th>점검PC수</th></tr>");
            }
          });
          $('#registerPopup').dialog('open');
      });

      //점검자 팝업
      $(".pcChkUserPopup").click(function(){
        var center_name = '점검자';
        pcChkUser(1,1);
        $("#pop_bg").css("display","block");
        $("#pcChkUserPopup").dialog({
          title:center_name,
          autoOpen: false,
          width: 800,
          height: 500,
          modal: false,
          close: function(ev, ui) {
             $("#pop_bg").hide();
             //popupResetForm();
             $("#pcChkUserList").html("");
             $("#pcChkUserList").append("<tr><th>No.</th><th>부서명</th><th>성명</th><th>직급/직책</th><th>사번</th><th>최종점검일</th><th>점검결과</th><th>시스템 제조번호</th><th>IP주소</th></tr>");
          }
        });
        $('#pcChkUserPopup').dialog('open');
      });

      //점검PC 팝업
      $(".pcChkPopup").click(function(){
        var center_name = '점검 PC';
        pcChk(1,1);
        $("#pop_bg").css("display","block");
        $("#pcChkPopup").dialog({
          title:center_name,
          autoOpen: false,
          width: 800,
          height: 500,
          modal: false,
          close: function(ev, ui) {
             $("#pop_bg").hide();
             //popupResetForm();
             $("#pcChkList").html("");
             $("#pcChkList").append("<tr><th>No.</th><th>부서명</th><th>성명</th><th>직급/직책</th><th>사번</th><th>최종점검일</th><th>점검결과</th><th>시스템 제조번호</th></tr>");
          }
        });
        $('#pcChkPopup').dialog('open');
      });

      //취약항목 팝업
      $(".noSafetyPopup").click(function(){
        var center_name = '취약항목';
        noSafety(1,1);
        $("#pop_bg").css("display","block");
        $("#noSafetyPopup").dialog({
          title:center_name,
          autoOpen: false,
          width: 800,
          height: 500,
          modal: false,
          close: function(ev, ui) {
             $("#pop_bg").hide();
             //popupResetForm();
             $("#noSafetyList").html("");
             $("#noSafetyList").append("<tr><th>No.</th><th>부서명</th><th>성명</th><th>사번</th><th>최종점검일</th><th>취약건수</th><th>취약항목</th><th>시스템 제조번호</th><th>IP</th></tr>");
          }
        });
        $('#noSafetyPopup').dialog('open');
      });

      //점검자별, 취약항목보기
      $("#noSafety_userSearch").click(function(){
        alert("점검자별 리스트");
      });
      $("#noSafety_attrSearch").click(function(){
        alert("항목별 리스트");
      });


      /* 권한처리 */
      <c:if test="${mnuLvlCd != 'write'}">
          $("input:radio").attr("disabled", true);
      </c:if>

      $("input[name=btn_excel]").click(function(){
        var val = $(this).attr("val");

        $("#deptPcForm").attr("action","<c:url value='/"+ val +".do'/>");
        $("#deptPcForm").submit();
        return false;
      });


  });


  var pageNo = 1;
  var groupNo = 1;

  //대상자 function
  function selectUserGrpListDwr(pageNo,groupNo) {
    var deptCd = $("#deptCd").val();
    var userPosition;
    var safeStandard = $("#safeStandard").val();
    var searchDate = $("#searchDate").val();

    var entity = {
      pageNo:pageNo
      ,groupNo:groupNo
      ,deptCd:deptCd
      ,userPosition:userPosition
      ,searchDate:searchDate
      ,safeStandard:safeStandard
    };

    $(".tr_all").remove();

    dwrSelfPcChkManagement.chkDeptUser(entity, function resultReserveInfo(result) {
        var userObj;
        var userMap = new Map();
        var count = 0;
        //------------------------------------------------------
        var safeStandard = $("#safeStandard").val();
        var searchDate = $("#searchDate").val();
        if(safeStandard == "1" ){
          $(".safeStandard_html").html(" 안전한 항목기준");
        }else if(safeStandard == "0"){
          $(".safeStandard_html").html(" 안전한 PC기준");
        }
        if(searchDate != "0"){
          dateStr = " 최근 " + searchDate + " 개월내 점검";
          $(".searchDate_html").html(dateStr);
        }else{
          $(".searchDate_html").html(" 전체");
        }
        //------------------------------------------------------
        var dateStr = "";
        for(var i=0 ; i < result.length ; i++){
          count++;
          userObj = result[i];
          if((userObj.chkResult == "0") && (userObj.insDate != null)){
            userObj.chkResult = "안전";
          }else if ((userObj.chkResult > 0) && (userObj.insDate != null)){
            userObj.chkResult = userObj.chkResult + "개 항목 취약";
          }
          if(userObj.insDate == null) {
            userObj.insDate = "-";
            userObj.chkResult = "-";
          }
          if(userObj.pcCnt == null) {
            userObj.pcCnt = "-";
          }



          $("#registerList").append("<tr class=\"tr_all\"><td>"+ userObj.rownum +"</td><td>"+ userObj.deptNm +"</td><td>"+ userObj.userNm + "</td><td>"+ userObj.cdDtlNm +"</td><td>" + userObj.userId + "</td><td>" + userObj.insDate + "</td><td>" + userObj.chkResult + "</td>"+"<td>" + userObj.pcCnt + "</td></tr>");
        };

    });

    dwrSelfPcChkManagement.paginateChkDeptUser(entity, function resultPagerInfo(result) {
      $(".page_area_ext").html(result);
    });

  };





  //점검자 function
  function pcChkUser (pageNo, groupNo) {
    var safeStandard = $("#safeStandard").val();
    var searchDate = $("#searchDate").val();
    //------------------------------------------------------
    var safeStandard = $("#safeStandard").val();
    var searchDate = $("#searchDate").val();
    if(safeStandard == "1" ){
      $(".safeStandard_html").html(" 안전한 항목기준");
    }else if(safeStandard == "0"){
      $(".safeStandard_html").html(" 안전한 PC기준");
    }
    if(searchDate != "0"){
      dateStr = " 최근 " + searchDate + " 개월내 점검";
      $(".searchDate_html").html(dateStr);
    }else{
      $(".searchDate_html").html(" 전체");
    }
    //------------------------------------------------------

      var deptCd = $("#deptCd").val();
      var entity = {
      pageNo:pageNo
       ,groupNo:groupNo
       ,deptCd:deptCd
       ,searchDate:searchDate
       ,safeStandard:safeStandard
      };

      $(".tr_all").remove();

      dwrSelfPcChkManagement.chkDeptPcUser(entity, function resultReserveInfo(result){
            var userObj;
            var userMap = new Map();
            var count = 0;

            for(var i=0 ; i < result.length ; i++){
              count++;
              userObj = result[i];

              if((userObj.chkResult == "0") && (userObj.insDate != null)){
                  userObj.chkResult = "안전";
                }else if ((userObj.chkResult > 0) && (userObj.insDate != null)){
                  userObj.chkResult = userObj.chkResult + "개 항목 취약";
                }
                if(userObj.insDate == null) {
                  userObj.insDate = "-";
                  userObj.chkResult = "-";
                }
                if(userObj.pcCnt == null) {
                  userObj.pcCnt = "-";
                }

              $("#pcChkUserList").append("");
              $("#pcChkUserList").append("<tr class=\"tr_all\"><td>"+userObj.rownum +"</td><td>"+ userObj.deptNm+"</td><td>"+userObj.userNm+"</td><td>"+userObj.cdDtlNm+"</td><td>"+userObj.userId+"</td><td>"+ userObj.insDate +"</td><td>"+ userObj.chkResult +"</td><td>"+ userObj.serialNumber +"</td><td>"+ userObj.realIpAddr +"</td></tr>");
            };
      });

      dwrSelfPcChkManagement.paginateChkUser(entity, function resultPagerInfo(result) {
        $(".page_area_ext").html(result);
      });

    };

    //점검PC function
    function pcChk (pageNo, groupNo) {
        var safeStandard = $("#safeStandard").val();
        var searchDate = $("#searchDate").val();
        var deptCd = $("#deptCd").val();
        //------------------------------------------------------
        var safeStandard = $("#safeStandard").val();
        var searchDate = $("#searchDate").val();
        if(safeStandard == "1" ){
          $(".safeStandard_html").html(" 안전한 항목기준");
        }else if(safeStandard == "0"){
          $(".safeStandard_html").html(" 안전한 PC기준");
        }
        if(searchDate != "0"){
          dateStr = " 최근 " + searchDate + " 개월내 점검";
          $(".searchDate_html").html(dateStr);
        }else{
          $(".searchDate_html").html(" 전체");
        }
        //------------------------------------------------------
        var entity = {
        pageNo:pageNo
         ,groupNo:groupNo
         ,deptCd:deptCd
         ,searchDate:searchDate
         ,safeStandard:safeStandard
        };

        $(".tr_all").remove();

        dwrSelfPcChkManagement.chkDeptPc(entity, function resultReserveInfo(result){
              var userObj;
              var userMap = new Map();
              var count = 0;

              for(var i=0 ; i < result.length ; i++){
                count++;
                userObj = result[i];

                if((userObj.chkResult == "0") && (userObj.insDate != null)){
                    userObj.chkResult = "안전";
                  }else if ((userObj.chkResult > 0) && (userObj.insDate != null)){
                    userObj.chkResult = userObj.chkResult + "개 항목 취약";
                  }
                  if(userObj.insDate == null) {
                    userObj.insDate = "-";
                    userObj.chkResult = "-";
                  }
                  if(userObj.pcCnt == null) {
                    userObj.pcCnt = "-";
                  }

                $("#pcChkList").append("");
                $("#pcChkList").append("<tr class=\"tr_all\"><td>"+userObj.rownum +"</td><td>"+ userObj.deptNm+"</td><td>"+userObj.userNm+"</td><td>"+userObj.cdDtlNm+"</td><td>"+userObj.userId+"</td><td>"+ userObj.insDate +"</td><td>"+ userObj.chkResult +"</td><td>"+ userObj.serialNumber +"</td></tr>");
              };
        });

        dwrSelfPcChkManagement.paginateChkDeptPc(entity, function resultPagerInfo(result) {
          $(".page_area_ext").html(result);
        });

      };

      //취약항목 function
      function noSafety(pageNo, groupNo) {

          var safeStandard = $("#safeStandard").val();
          var searchDate = $("#searchDate").val();
          //------------------------------------------------------
          var safeStandard = $("#safeStandard").val();
          var searchDate = $("#searchDate").val();
          if(safeStandard == "1" ){
            $(".safeStandard_html").html(" 안전한 항목기준");
          }else if(safeStandard == "0"){
            $(".safeStandard_html").html(" 안전한 PC기준");
          }
          if(searchDate != "0"){
            dateStr = " 최근 " + searchDate + " 개월내 점검";
            $(".searchDate_html").html(dateStr);
          }else{
            $(".searchDate_html").html(" 전체");
          }
          //------------------------------------------------------
          var deptCd = $("#deptCd").val();
          var entity = {
          pageNo:pageNo
           ,groupNo:groupNo
           ,deptCd:deptCd
           ,searchDate:searchDate
           ,safeStandard:safeStandard
          };

          $(".tr_all").remove();

          dwrSelfPcChkManagement.chkDeptDanger(entity, function resultReserveInfo(result){
                var userObj;
                var userMap = new Map();
                var str = "";
                var strResult = [];
                for(var i=0 ; i < result.length ; i++){
                  userObj = result[i];
                  str = userObj.dangerList;
                  for(var j=0; j < userObj.chkResult; j++){
                    strResult[j] = str.replace(/,/gi, '<br/>');
                  }

                  if((userObj.chkResult == "0") && (userObj.insDate != null)){
                      userObj.chkResult = "안전";
                    }else if ((userObj.chkResult > 0) && (userObj.insDate != null)){
                      userObj.chkResult = "총 " + userObj.chkResult + " 건";
                    }
                    if(userObj.insDate == null) {
                      userObj.insDate = "-";
                      userObj.chkResult = "-";
                    }
                    if(userObj.pcCnt == null) {
                      userObj.pcCnt = "-";
                    }

                  $("#noSafetyList").append("");
                  $("#noSafetyList").append("<tr class=\"tr_all\"><td>"+userObj.rownum +"</td><td>"+ userObj.deptNm+"</td><td>"+userObj.userNm+"</td><td>"+userObj.userId+"</td><td>"+ userObj.insDate +"</td><td>"+ userObj.chkResult +"</td><td>"+ strResult[i] +"</td><td>"+ userObj.serialNumber + "</td><td>"+ userObj.realIpAddr +"</td></tr>");
                };
          });

          dwrSelfPcChkManagement.paginateChkDeptDanger(entity, function resultPagerInfo(result) {
            $(".page_area_ext").html(result);
          });

        };

</script>

<!--

<table class="tbws1" style="width:100%">
 <tr>
   <th rowspan="2">부서명</th>
   <th rowspan="2">대상자</th>
   <th rowspan="2">점검자</th>
   <th rowspan="2">점검률</th>
   <th rowspan="2">점검PC</th>
   <th colspan="3">점검결과(17항목, PC 1대당 평균)</th>
 </tr>
 <tr>
   <th>안전항목</th>
   <th>취약항목</th>
   <th>안전율</th>
 </tr>
 <tr>
    <td>솔루피아</td>
    <td>4</td>
    <td>1</td>
    <td>25</td>
    <td>1</td>
    <td>17</td>
    <td>0</td>
    <td>100%</td>
</table>



 -->