<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link type="text/css" href="<c:url value='/module/jquery/ui/css/modal.css'/>" rel="stylesheet" />
<link type="text/css" href="<c:url value='/module/jquery/plugin/css/screen.css'/>" rel="stylesheet" />
<script type='text/javascript' src="<c:url value='/dwr/interface/dwrManagementDept.js'/>"></script>
<script type='text/javascript' src="<c:url value='/dwr/interface/dwrUserGrpIdManagement.js'/>"></script>
<script type='text/javascript' src="<c:url value='/dwr/interface/dwrUserManagement.js'/>"></script>

<% //##### 해제대상메신저 검색 STARE ##### -- %>
<div id="selectExctActX_area" style="display:none;" >
  <div id="main3">
      <div class="exctActXTable">
      <table id="activeXList" class="tbws1">
        <tr>
         <th>메신저명</th>
         <th>실행파일명</th>
        </tr>
      </table>
      </div>
  </div>
</div>
<% //##### 해제대상메신저  검색 END ##### -- %>

<script type="text/javaScript" defer="defer">
function selectObjActXList(pageNo, groupNo){
    var entity = {
      pageNo:pageNo,
      groupNo:groupNo,
    };

    $(".tr_all").remove();

    $("#pop_bg2").css("display","block");
      dwrManagement.getObjActiveXListAll(entity, function resultReserveInfo(result) {
         var ObjMsg;
         var msgMap = new Map();
         for(var i=0 ; i < result.length ; i++){
           ObjMsg = result[i];
             $("#activeXList").append("<tr class=\"tr_all\"><td><a style=\"cursor:pointer\" onclick=\"actXClick('"+ ObjMsg.appName +"','" +ObjMsg.appExe +"','objMsger')\"><b>"+ ObjMsg.appName +"</b></a></td><td>"+ ObjMsg.appExe +"</td></tr>");
         }

        var center_name = '해제대상 ActiveX 선택';
        $("#pop_bg2").css("display","block");
        $("#selectExctActX_area").dialog({
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
        $('#selectExctActX_area').dialog('open');
        return false;
      });

//       dwrManagement.paginate_msg(entity, function resultPagerInfo(result) {
//          $(".page_area_ext3").html(result);
//       });
  }

</script>