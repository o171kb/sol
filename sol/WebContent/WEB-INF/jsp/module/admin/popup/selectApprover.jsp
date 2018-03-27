<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link type="text/css" href="<c:url value='/module/jquery/ui/css/modal.css'/>" rel="stylesheet" />
<link type="text/css" href="<c:url value='/module/jquery/plugin/css/screen.css'/>" rel="stylesheet" />
<script type='text/javascript' src="<c:url value='/dwr/interface/dwrManagementDept.js'/>"></script>
<script type='text/javascript' src="<c:url value='/dwr/interface/dwrUserGrpIdManagement.js'/>"></script>
<script type='text/javascript' src="<c:url value='/dwr/interface/dwrUserManagement.js'/>"></script>

<% //##### 결재자 검색 STARE ##### -- %>
<div id="selectUser_area" style="display:none;" >
  <div id="main2">
  <!-- search start-->
    <div style="margin-bottom: 20px;">
     <table class="tbws1" style="width: 100%; margin-bottom: 10px;">
       <tr>
         <th style="width:20%">부서명</th>
         <td>
           <input type="text" id="searchdeptnm" name="searchdeptnm" path="searchdeptnm"  />
         </td>
         <th style="width:20%">결재자</th>
         <td>
           <input type="text" id="searchapprusernm" name="searchapprusernm" path="searchapprusernm"  />
         </td>
       </tr>
     </table>
     <table style="width: 100%; ">
     	<tr>
     		<td align="left"><span style="font-weight:bold;">결재자를 추가하려면, 이름을 클릭하십시오.</span></td>
     		<td align="right"><input type="button" id="searchForm" name="" value="검색" class="swhite" onClick="approverListDwr(1, 1)" style="cursor:pointer;"/></td>
     	</tr>
     </table>
     <!-- <div style="text- align: right; width:50px;">
       <input type="button" id="searchForm" name="" value="검색" class="swhite" onClick="approverListDwr(1, 1)" style="cursor:pointer;"/>
       <input type="button" id="resetForm" name="" value="초기화" class="swhite" onClick="resetForm()" style="cursor:pointer;"/>
     </div> -->
     <!-- search end-->
    </div>
      <div class="usertable" style="overflow: auto;">
      <span id="selectApproverErrorMsg" name="selectApproverErrorMsg" value="" style="color:red; display:none;">error</span>
      <table id="userList" class="tbws1">
        <tr>
         <th>부서명</th>
         <th>직급</th>
         <th>결재자</th>
        </tr>
      </table>
      </div>
  </div>
</div>
<% //##### 결재자 검색 END ##### -- %>

<script type="text/javaScript" defer="defer">

//layer popup dwr
var pageNo = 1;
var groupNo = 1;
function approverListDwr(pageNo, groupNo){
  $("#selectApproverErrorMsg").hide();
  var searchapprusernm = $("#searchapprusernm").val();
  var searchdeptnm = $("#searchdeptnm").val();
  var madecd = $("#madeCd").val();
  var deptCd = $("#deptCd").val();
  var userPosition = $("#userPosition").val();
  //alert(deptCd + "//" + userPosition);
  var entity = {
    pageNo:pageNo,
    groupNo:groupNo,
    searchApprUserNm:searchapprusernm,
    searchDeptNm:searchdeptnm,
    madeCd:madecd,
    deptCd:deptCd,
    userPosition:userPosition
  };

  $(".tr_all").remove();

  $("#pop_bg").css("display","block");
  dwrManagement.getApprovalUserListAll(entity, function resultReserveInfo(result) {
      var userObj;
      var userMap = new Map();
      for(var i=0 ; i < result.length ; i++){
        userObj = result[i];
          $("#userList").append("<tr class=\"tr_all\"><td>"+ userObj.deptNm +"</td><td>"+ userObj.userPositionNm +"</td><td><a style=\"cursor:pointer\" onclick=\"userClick('"+ userObj.userNm +"','" +userObj.userPositionNm +"','" + userObj.deptNm +"','"+ userObj.userId +"')\"><b>"+ userObj.userNm +"</b></a></td></tr>");
      }

      var center_name = '결재자 선택';
      $("#pop_bg").css("display","block");
      $("#selectUser_area").dialog({
        title:center_name,
        autoOpen: false,
        width: 600,
        height: 560,
        modal: false,
        close: function(ev, ui) {
           $("#pop_bg").hide();
           resetForm();
        }
      });
      $('#selectUser_area').dialog('open');
      return false;
    });

}


</script>