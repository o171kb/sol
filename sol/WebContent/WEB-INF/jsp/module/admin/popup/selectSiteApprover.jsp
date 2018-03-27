<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link type="text/css" href="<c:url value='/module/jquery/ui/css/modal.css'/>" rel="stylesheet" />
<link type="text/css" href="<c:url value='/module/jquery/plugin/css/screen.css'/>" rel="stylesheet" />
<script type='text/javascript' src="<c:url value='/dwr/interface/dwrManagement.js'/>"></script>
<script type='text/javascript' src="<c:url value='/dwr/interface/dwrManagementDept.js'/>"></script>
<script type='text/javascript' src="<c:url value='/dwr/interface/dwrUserGrpIdManagement.js'/>"></script>
<script type='text/javascript' src="<c:url value='/dwr/interface/dwrUserManagement.js'/>"></script>


<% //##### 규칙검색 STARE ##### -- %>
<div id="selectRules_area" style="display:none;" >
   <div style="overflow: auto; margin-top:15px;">
   <span id="selectRulesErrorMsg" name="selectRulesErrorMsg" value="" style="color:red; display:none;">error</span>
   <table id="rulesList" class="tbws1">
     <tr>
      <th>규&nbsp;&nbsp;&nbsp;&nbsp;칙</th>
     </tr>
   </table>
   </div>
</div>
<% //##### 규칙검색 END ##### -- %>

<script type="text/javaScript" defer="defer">

//layer popup dwr
var pageNo = 1;
var groupNo = 1;
function rulesListDwr(pageNo, groupNo){
//   var searchapprusernm = $("#searchapprusernm").val();
//   var searchdeptnm = $("#searchdeptnm").val();
//   var madecd = $("#madeCd").val();
   var ruleName = $("#ruleName").val();
   var ruleGUID = $("#ruleGUID").val();
   var entity = {
    pageNo:pageNo,
    groupNo:groupNo,
    ruleName:ruleName,
    ruleGUID:ruleGUID
  };

  $(".tr_all").remove();
  $("#pop_bg").css("display","block");
  dwrManagement.getRulesListDwrAll(entity, function resultReserveInfo(result) {
      var rulesObj;
      var rulesMap = new Map();
      for(var i=0 ; i < result.length ; i++){
        rulesObj = result[i];
          $("#rulesList").append("<tr class=\"tr_all\"><td><a style=\"cursor:pointer;\" onclick=\"ruleClick('"+rulesObj.ruleName+"','"+rulesObj.ruleGUID+"')\">"+ rulesObj.ruleName +"</a></td></tr>");
      }
      var center_name = '규칙 선택';
      $("#pop_bg").css("display","block");
      $("#selectRules_area").dialog({
        title:center_name,
        autoOpen: false,
        width: 300,
        height: 300,
        modal: false,
        close: function(ev, ui) {
           $("#pop_bg").hide();
           resetForm();
        }
      });
      $('#selectRules_area').dialog('open');
      return false;
    });

}
</script>

<% //##### 시간대검색 STARE ##### -- %>
<div id="selectTime_area" style="display:none;" >
   <div style="overflow: auto; margin-top:15px;">
   <span id="selectTimeErrorMsg" name="selectTimeErrorMsg" value="" style="color:red; display:none;">error</span>
   <table id="timeList" class="tbws1">
     <tr>
      <th>시&nbsp;&nbsp;간&nbsp;&nbsp;대</th>
     </tr>
   </table>
   </div>
</div>
<% //##### 시간대검색 END ##### -- %>

<script>
//layer popup dwr
var pageNo = 1;
var groupNo = 1;
function timeListDwr(pageNo, groupNo){
//   var searchapprusernm = $("#searchapprusernm").val();
//   var searchdeptnm = $("#searchdeptnm").val();
//   var madecd = $("#madeCd").val();
   var timeName = $("#timeName").val();
   var timeGUID = $("#timeGUID").val();
   var entity = {
    pageNo:pageNo,
    groupNo:groupNo,
    timeGUID:timeGUID,
    timeName:timeName
  };
  $(".tr_all").remove();
  $("#pop_bg").css("display","block");
  dwrManagement.getTimeListDwrAll(entity, function resultReserveInfo(result) {
      var timeObj;
      var timeMap = new Map();
      for(var i=0 ; i < result.length ; i++){
          timeObj = result[i];
          $("#timeList").append("<tr class=\"tr_all\"><td><a style=\"cursor:pointer;\" onclick=\"timeClick('"+timeObj.timeName+"','"+timeObj.timeGUID+"')\">"+ timeObj.timeName +"</a></td></tr>");
      }
      var center_name = '시간대 선택';
      $("#pop_bg").css("display","block");
      $("#selectTime_area").dialog({
        title:center_name,
        autoOpen: false,
        width: 300,
        height: 300,
        modal: false,
        close: function(ev, ui) {
           $("#pop_bg").hide();
           resetForm();
        }
      });
      $('#selectTime_area').dialog('open');
      return false;
    });

}

</script>


<% //##### 신청정보 검색 STARE ##### -- %>
<div id="selectExctUser_area" style="display:none;" >
  <div id="main3">
<!--   search start -->
    <div style="margin-bottom: 20px;">
     <table class="tbws1" style="width: 100%; margin-bottom: 10px;">
       <tr>
         <th>사용자</th>
         <td class="align_l">
           <input type="text" id="hName" name="hName" />
         </td>
       </tr>
     </table>
     <div style="text-align: right">
       <input type="button" id="" name="" value="검색" class="swhite" onClick="approvalPcList2(1, 1)" style="cursor:pointer;"/>
       <!-- <input type="button" id="" name="" value="초기화" class="swhite" onClick="resetForm()" style="cursor:pointer;"/> -->
     </div>
     <div class="page_area_ext2"></div>
    </div>
<!--       search end -->
      <div class="exctPctable">
      <table id="pcList2" class="tbws1">
        <tr>
         <th>부서</th>
         <th>사용자</th>
         <th>Email</th>
         <!-- <th>IP</th> -->
        </tr>
      </table>
      <span id="selectPcErrorMsg" name="selectPcErrorMsg" value="" style="color:red; display:none;">error</span>
      </div>
  </div>
</div>
<% //##### 신청정보  검색 END ##### -- %>

<script type="text/javaScript" defer="defer">
$("#hName").keypress(function(e){
	if (e.keyCode == 13){
		approvalPcList2(1, 1);
	}
});
var pageNo = 1;
var groupNo = 1;
function approvalPcList2(pageNo, groupNo){

    var sdeptNm = $("#sdeptNm").val();
    var hName = $("#hName").val();
    var deptnm = $("#deptNm").val();
    var entity = {
      pageNo:pageNo,
      groupNo:groupNo,
      sdeptNm:sdeptNm,
      hName:hName,
      deptNm:deptnm,
    };
    $(".tr_all").remove();
    $("#pop_bg2").css("display","block");
    dwr.engine.setAsync(false);//false가 동기로 선택한 것.
    dwrManagement.getApprovalSitePcListAll(entity, function resultReserveInfo(result) {
         var pcObj;
         var userId;
         var hName;
         var html;         
         var pcMap = new Map();
         for(var i=0 ; i < result.length ; i++){
           userId = result[i].userId;
           hName = result[i].hName;
           var entity = {
        		userId:userId
   		    };
           
           dwrManagement.getApprovalSitePcListInfo(entity, function resultReserveInfo(resultInfo) {
        	   pcObj = resultInfo;
        	    /* for(var j=0; j<resultInfo.length; j++ ){
        		   pcObj = resultInfo[j];
        		  // html += "<tr class=\"tr_all\"><td>"+ pcObj.sdeptNm +"</td><td><a style=\"cursor:pointer\" onclick=\"infoClick('"+ pcObj.hName +"','"+ pcObj.userId +"','"+ pcObj.sdeptNm +"')\"><b>"+ pcObj.hName +"</b></a></td><td>"+ pcObj.eMail +"</td></tr>";
        	   }   */      	   
           });
           if(pcObj.length > 0)
           {
			   $("#pcList2").append("<tr class=\"tr_all\"><td>"+ pcObj[0].sdeptNm +"</td><td><a style=\"cursor:pointer\" onclick=\"infoClick('"+ hName +"','"+ userId +"','"+ pcObj[0].sdeptNm +"')\"><b>"+ hName +"</b></a></td><td>"+ pcObj[0].eMail +"</td></tr>");
           }
           else
       	   {
        	   $("#pcList2").append("<tr class=\"tr_all\"><td>"+ "" +"</td><td><a style=\"cursor:pointer\" onclick=\"infoClick('"+ hName  +"','"+ userId +"','"+ "" +"')\"><b>"+ hName +"</b></a></td><td>"+ "" +"</td></tr>");
       	   }
           //$("#pcList2").append("<tr class=\"tr_all\"><td>"+ pcObj.sdeptNm +"</td><td><a style=\"cursor:pointer\" onclick=\"infoClick('"+ pcObj.hName +"','"+ pcObj.userId +"','"+ pcObj.sdeptNm +"')\"><b>"+ pcObj.hName +"</b></a></td><td>"+ pcObj.eMail +"</td></tr>");
           /*사이트 예외 사용자 변경*/
         }
         $("#pcList2").append(html);
        var center_name = '사용자 선택';
        $("#pop_bg2").css("display","block");
        $("#selectExctUser_area").dialog({
          title:center_name,
          autoOpen: false,
          width: 600,
          height: 550,
          modal: false,
          close: function(ev, ui) {
             $("#pop_bg2").hide();
             resetForm();
          }
        });
        $('#selectExctUser_area').dialog('open');
        return false;
      });

      dwrManagement.paginate_sitePc(entity, function resultPagerInfo(result) {
         $(".page_area_ext2").html(result);
      });
  }

function approvalPcList2_notSearch(){

    var sdeptNm = $("#sdeptNm").val();
    var hName = $("#hName").val();
    var deptnm = $("#deptNm").val();
    var entity = {
      pageNo:pageNo,
      groupNo:groupNo,
      sdeptNm:sdeptNm,
      hName:hName,
      deptNm:deptnm,
    };
    $(".tr_all").remove();
    $("#pop_bg2").css("display","block");
      dwrManagement.getApprovalSitePcListAll(entity, function resultReserveInfo(result) {
         var pcObj;
         var pcMap = new Map();
         for(var i=0 ; i < result.length ; i++){
           pcObj = result[i];
           //$("#pcList2").append("<tr class=\"tr_all\"><td>"+ pcObj.sdeptNm +"</td><td><a style=\"cursor:pointer\" onclick=\"infoClick('"+ pcObj.hName +"','" +pcObj.sdeptNm +"','" + pcObj.serial +"','"+ pcObj.ipAddr +"','"+ pcObj.empNo +"')\"><b>"+ pcObj.hName +"</b></a></td><td>"+ pcObj.serial +"</td><td>"+ pcObj.ipAddr +"</td></tr>");
         }
        var center_name = '사용자 선택';
        $("#pop_bg2").css("display","block");
        $("#selectExctUser_area").dialog({
          title:center_name,
          autoOpen: false,
          width: 600,
          height: 550,
          modal: false,
          close: function(ev, ui) {
             $("#pop_bg2").hide();
             resetForm();
          }
        });
        $('#selectExctUser_area').dialog('open');
        return false;
      });

      dwrManagement.paginate_sitePc(entity, function resultPagerInfo(result) {
         //$(".page_area_ext2").html(result);
      });
  }

function approvalPcListAddId(pageNo, groupNo){
    var sdeptNm = $("#sdeptNm").val();
    var hName = $("#hName").val();
    var deptnm = $("#deptNm").val();
    var entity = {
      pageNo:pageNo,
      groupNo:groupNo,
      sdeptNm:sdeptNm,
      hName:hName,
      deptNm:deptnm,
    };

    $(".tr_all").remove();

    $("#pop_bg2").css("display","block");
      dwrManagement.getApprovalSitePcListAll(entity, function resultReserveInfo(result) {
         var pcObj;
         var pcMap = new Map();
         for(var i=0 ; i < result.length ; i++){
           pcObj = result[i];
//            alert(pcObj.ipAddr);
         }

        var center_name = '사용자 선택';
        $("#pop_bg2").css("display","block");
        $("#selectExctPc_area").dialog({
          title:center_name,
          autoOpen: false,
          width: 600,
          height: 550,
          modal: false,
          close: function(ev, ui) {
             $("#pop_bg2").hide();
             resetForm();
          }
        });
        $('#selectExctPc_area').dialog('open');
        return false;
      });

      dwrManagement.paginate_sitePc(entity, function resultPagerInfo(result) {
         $(".page_area_ext2").html(result);
      });
  }


</script>