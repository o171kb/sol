<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link type="text/css" href="<c:url value='/module/jquery/ui/css/modal.css'/>" rel="stylesheet" />
<link type="text/css" href="<c:url value='/module/jquery/plugin/css/screen.css'/>" rel="stylesheet" />
<script type='text/javascript' src="<c:url value='/dwr/interface/dwrManagementDept.js'/>"></script>
<script type='text/javascript' src="<c:url value='/dwr/interface/dwrUserGrpIdManagement.js'/>"></script>
<script type='text/javascript' src="<c:url value='/dwr/interface/dwrUserManagement.js'/>"></script>

<% //##### 예외대상PC 검색 STARE ##### -- %>
<div id="selectExctPc_area" style="display:none;" >
  <div id="main3">
  <!-- search start-->
    <div style="margin-bottom: 20px;">
     <table class="tbws1" style="width: 100%; margin-bottom: 10px;">
       <tr>
         <th>사용자</th>
         <td class="align_l">
           <input type="text" id="hName" name="hName" path="hName" />
         </td>
       </tr>
     </table>
     <div style="text-align: right">
       <input type="button" id="" name="" value="검색" class="swhite" onClick="approvalPcList(1, 1)" style="cursor:pointer;"/>
       <!-- <input type="button" id="" name="" value="초기화" class="swhite" onClick="resetForm()" style="cursor:pointer;"/> -->
     </div>
     <div class="page_area_ext2"></div>
    </div>
      <!-- search end-->
      <div class="exctPctable">
      <table id="pcList" class="tbws1">
        <tr>
         <th>부서명</th>
         <th>사용자</th>
         <th>제조번호</th>
         <th>IP</th>
        </tr>
      </table>
      <span id="selectPcErrorMsg" name="selectPcErrorMsg" value="" style="color:red; display:none;">error</span>
      </div>
  </div>
</div>
<% //##### 예외대상PC  검색 END ##### -- %>

<script type="text/javaScript" defer="defer">
$("#hName").keypress(function(e){
	if (e.keyCode == 13){
		approvalPcList(1, 1);
	}
});
var pageNo = 1;
var groupNo = 1;

function approvalPcList_notSearch(){
	var pageNo = 1;
	var groupNo = 1;
	 $("#selectPcErrorMsg").hide();
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
	//   var dwrCoTtObjParams = {//확장용
	//   };
	      dwrManagement.getApprovalPcListAll(entity, function resultReserveInfo(result) {
	         var pcObj;
	         var pcMap = new Map();

	         for(var i=0 ; i < result.length ; i++){
	           pcObj = result[i];
	             //$("#pcList").append("<tr class=\"tr_all\"><td>"+ pcObj.sdeptNm +"</td><td><a style=\"cursor:pointer\" onclick=\"pcClick('"+ pcObj.hName +"','" +pcObj.sdeptNm +"','" + pcObj.serial +"','"+ pcObj.ipAddr +"','"+ pcObj.empNo +"')\"><b>"+ pcObj.hName +"</b></a></td><td>"+ pcObj.serial +"</td><td>"+ pcObj.ipAddr +"</td></tr>");
	         }

	        var center_name = '예외대상 PC 선택';
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

	      //dwrManagement.paginate_pc(entity, function resultPagerInfo(result) {
	         //$(".page_area_ext2").html(result);
	      //});
}

function approvalPcListAddId_notSearch(){
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
      dwrManagement.getApprovalPcListAll(entity, function resultReserveInfo(result) {
         var pcObj;
         var pcMap = new Map();
         for(var i=0 ; i < result.length ; i++){
           pcObj = result[i];
             //$("#pcList").append("<tr class=\"tr_all\"><td>"+ pcObj.sdeptNm +"</td><td><a style=\"cursor:pointer\" onclick=\"pcClick('"+ pcObj.hName +"','" +pcObj.sdeptNm +"','" + pcObj.serial +"','"+ pcObj.ipAddr +"','"+ pcObj.empNo +"')\"><b>"+ pcObj.hName +"</b></a></td><td>"+ pcObj.serial +"</td><td>"+ pcObj.ipAddr +"</td></tr>");
         }

        var center_name = '예외대상 PC 선택';
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

      dwrManagement.paginate_pc(entity, function resultPagerInfo(result) {
         //$(".page_area_ext2").html(result);
      });
  }

function approvalPcList(pageNo, groupNo){
    $("#selectPcErrorMsg").hide();
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
//   var dwrCoTtObjParams = {//확장용
//   };
      dwrManagement.getApprovalPcListAll(entity, function resultReserveInfo(result) {
         var pcObj;
         var pcMap = new Map();
         for(var i=0 ; i < result.length ; i++){
           pcObj = result[i];
             $("#pcList").append("<tr class=\"tr_all\"><td>"+ pcObj.sdeptNm +"</td><td>"+ pcObj.hName +"</td><td><a style=\"cursor:pointer\" onclick=\"pcClick('"+ pcObj.hName +"','" +pcObj.sdeptNm +"','" + pcObj.serial +"','"+ pcObj.ipAddr +"','"+ pcObj.empNo +"')\"><b>"+ pcObj.serial +"</b></a></td><td>"+ pcObj.ipAddr +"</td></tr>");
         }

        var center_name = '예외대상 PC 선택';
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

      dwrManagement.paginate_pc(entity, function resultPagerInfo(result) {
         $(".page_area_ext2").html(result);
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
      dwrManagement.getApprovalPcListAll(entity, function resultReserveInfo(result) {
         var pcObj;
         var pcMap = new Map();
         for(var i=0 ; i < result.length ; i++){
           pcObj = result[i];
             $("#pcList").append("<tr class=\"tr_all\"><td>"+ pcObj.sdeptNm +"</td><td>"+ pcObj.hName +"</td><td><a style=\"cursor:pointer\" onclick=\"pcClick('"+ pcObj.hName +"','" +pcObj.sdeptNm +"','" + pcObj.serial +"','"+ pcObj.ipAddr +"','"+ pcObj.empNo +"')\"><b>"+ pcObj.serial +"</b></a></td><td>"+ pcObj.ipAddr +"</td></tr>");
         }

        var center_name = '예외대상 PC 선택';
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

      dwrManagement.paginate_pc(entity, function resultPagerInfo(result) {
         $(".page_area_ext2").html(result);
      });
  }


</script>