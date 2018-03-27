<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type='text/javascript' src="<c:url value='/dwr/interface/dwrUserGrpIdManagement.js'/>"></script>

<% //##### 그룹 검색 STARE ##### -- %>
<div id="selectUser_area" style="display:none;" >
  <div id="main2">
  <!-- search start-->
    <div style="margin-bottom: 20px;">
     <table class="tbws1">
       <tr>
         <th style="width:100px;">사용자그룹ID</th>
         <td>
           <input type="text" id="popupSearchGrpId" name="searchGrpId" style="width:100px;" />
         </td>
         <th style="width:100px;">사용자그룹명</th>
         <td>
           <input type="text" id="popupSearchGrpNm" name="searchGrpNm" style="width:100px;" />
         </td>
       </tr>
     </table>
     <div class="btn_area">
      <!-- <div class="right" style="margin:0px 3px 0px 3px"><input type="button" id="popupResetForm" class="nwhite" value="초기화"/></div> -->
      <div class="right" style="margin:0px 3px 0px 3px"><input type="button" id="popupSearchForm" class="nwhite" value="검색"/></div>
     </div>

     <!-- paginate-->
     <div class="page_area_ext">
     </div>
     <!-- //paginate -->
    </div>
      <!-- search end-->
      <div class="usertable">
      <table id="userList" class="tbws1">
        <tr>
         <th width="210px;">그룹ID</th>
         <th>그룹명</th>
         <th>그룹설명</th>
        </tr>
      </table>
      </div>
  </div>
</div>

<% //##### 사용자그룹 검색 START ##### -- %>
<script type="text/javaScript" defer="defer">

  $(document).ready(function(){
    // 부모창 검색 버튼
    $("#selectUserGrp_bt").click(function() {
      //$("#selectDept_area").css({'visibility':'visible'});
      selectUserGrpListDwr(1,1);
    });

    //검색버튼
    $("#popupSearchForm").click(function(){
      selectUserGrpListDwr(1, 1);
    });

    //검색버튼
    $("#popupResetForm").click(function(){
      popupResetForm();
    });

  });

  //layer popup dwr
  var pageNo = 1;
  var groupNo = 1;

  function selectUserGrpListDwr(pageNo, groupNo){
    var popupSearchGrpId = $("#popupSearchGrpId").val();
    var popupSearchGrpNm = $("#popupSearchGrpNm").val();
    var entity = {
      pageNo:pageNo,
      groupNo:groupNo,
      searchGrpId:popupSearchGrpId,
      searchGrpNm:popupSearchGrpNm
    };

    $(".tr_all").remove();

    $("#pop_bg").css("display","block");
    var dwrCoTtObjParams = {//확장용
    };
    dwrUserGrpIdManagement.getSearchUserGroupListForDwr(entity, function resultReserveInfo(result) {
      var userObj;
      var userMap = new Map();
      for(var i=0 ; i < result.length ; i++){
        userObj = result[i];
          $("#userList").append("<tr class=\"tr_all\"><td>"+ userObj.userGrpId +"</td><td>"+ userObj.grpNm +"</td><td><a style=\"cursor:pointer\" onclick=\"selectGrp('"+ userObj.userGrpNo +"','" + userObj.grpNm +"','" + userObj.apprYn +"')\"><b>"+ userObj.grpExp +"</b></a></td></tr>");
      }

      var center_name = '사용자그룹 선택';
      $("#pop_bg").css("display","block");
      $("#selectUser_area").dialog({
        title:center_name,
        autoOpen: false,
        width: 520,
        height: 530,
        modal: false,
        close: function(ev, ui) {
           $("#pop_bg").hide();
           //popupResetForm();
        }
      });
      $('#selectUser_area').dialog('open');
      return false;
    });

    dwrUserGrpIdManagement.paginateUserGroupListForDwr(entity, function resultPagerInfo(result) {
      $(".page_area_ext").html(result);
    });
  }
  //popupResetForm
  function popupResetForm(){
    $("#popupSearchGrpId").val("");
    $("#popupSearchGrpNm").val("");
  }
  function selectGrp(userGrpNo,grpNm,apprYn) {
    //부모창에 함수를 작성한다.
    setParentUserGrpParams(userGrpNo,grpNm,apprYn);
    popupResetForm();
    $('#selectUser_area').dialog('close');
  }
</script>