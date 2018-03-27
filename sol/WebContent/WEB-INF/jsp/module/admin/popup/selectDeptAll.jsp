<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link type="text/css" href="<c:url value='/module/jquery/ui/css/modal.css'/>" rel="stylesheet" />
<link type="text/css" href="<c:url value='/module/jquery/plugin/css/screen.css'/>" rel="stylesheet" />
<script type='text/javascript' src="<c:url value='/dwr/interface/dwrManagementDept.js'/>"></script>
<script type='text/javascript' src="<c:url value='/dwr/interface/dwrUserGrpIdManagement.js'/>"></script>
<script type='text/javascript' src="<c:url value='/dwr/interface/dwrUserManagement.js'/>"></script>

<% //##### 부서 검색 STARE ##### -- %>

<div id="selectDept_area" style="display:none;" >
  <div id="main2">
    <div id="sidetree" style="width:428px; height:410px; float:left; border: 1px solid #5290cd; overflow-y:scroll">
      <div class="treeheader">&nbsp;</div>
      <div id="sidetreecontrol">
        <a href="?#">모두닫기</a> | <a href="?#">모두열기</a>
      </div>
      <ul id="tree">
        <!-- ######부서데이타추가영역##### -->
      </ul>
    </div>
    <div id="selectUserList" style="width:520px; height:410px; float:left; border-top: 1px solid #5290cd; border-right: 1px solid #5290cd; border-bottom: 1px solid #5290cd; display:none;">
     <!-- paginate-->
     <div class="page_area_ext2" style="margin-left:10px; margin-right:10px;">
       <div class="pagetotal_ext">총건수:<span>0</span>건</div>
     </div>
     <!-- //paginate -->

      <div class="usertable" style="margin-left:10px; margin-right:10px; width:500px;">
        <table id="userList2" class="tbws6">
          <tr>
           <th style="width:30%;">사번</th>
           <th style="width:30%;">직급명</th>
           <th style="width:40%;">성명</th>
          </tr>
        </table>
      </div>

    </div>
  </div>
</div>
<div class='hid' style='display:none;' id="tempTree">
<!-- ######임시부서데이타추가영역##### -->
</div>
<% //##### 부서 검색 END ##### -- %>

<script type="text/javaScript" defer="defer">

  var dialog_mode = 470 ;
  var dialog_width ;
  //처음한번 DB에서 가져오기
  var isfirstSelectDept = 1;

  $(document).ready(function(){

    // 부서선택모드
    $("#selectDept_bt").click(function() {
      $("#pop_bg").css("display","block");

      if(isfirstSelectDept == 1){
          //다이얼로그창 초기설정
         openDialog(470,'부서선택');
      }else{
          $('#selectDept_area').dialog('open');
      }

      dialog_mode = 'dept_mode';
      $("#selectDept_area").dialog({title:'부서선택', width: 470, position: [($(window).width() / 2) - (470 / 2), 150] });
      $("#selectUserList").css("display","none");
    });

    //결재자선택모드
    $("#selectApprDeptUser_bt").click(function() {
      $("#pop_bg").css("display","block");

      $("#selectUserList").css("display","block");
      if(isfirstSelectDept == 1){
          //다이얼로그창 초기설정
         openDialog(1017,'대행결재자선택');
      }else{
          $('#selectDept_area').dialog('open');
      }
      dialog_mode = 'appr_mode';

      $(".tr_all2").remove();
      $("#userList2").append("<tr class=\"tr_all2\"><td colspan=\"3\">검색내용이 없습니다. 부서를 선택해주세요.</td></tr>");

      $("#selectUserList").show();
      $("#selectDept_area").dialog({title:'대행결재자선택', width: 1017, position: [($(window).width() / 2) - (1017 / 2), 150] });
    });

  });


  //부서리스트취득후 트리구조로 표시
  function openDialog(dialog_width,title_name) {

    if(isfirstSelectDept == 1){

      $("#tree").treeview({
         collapsed : true,
         animated : "medium",
         control : "#sidetreecontrol",
         persist : "location"
      });

      var dwrCoTtObjParams = {//확장용
          };
      //부서리스트 취득
      dwrManagementDept.getDeptSearchListAll(dwrCoTtObjParams, function resultReserveInfo(result) {
        var deptObj;
        var deptMap = new Map();
        for(var i=0 ; i < result.length ; i++){
          deptObj = result[i];
          if(deptObj.highDeptCd == '0'){
            $("#tempTree").append("<li class='dept_cd' id='"+ deptObj.deptCd + "' ><span onclick=\"deptClick('"+ deptObj.deptNo + "','"+ deptObj.deptCd + "','"+ deptObj.deptNm + "')\" ><strong>"+ deptObj.deptNm + "</strong></span></li>");
          } else if(deptMap.containsKey(deptObj.highDeptCd)){
            $("#"+deptObj.highDeptCd+"_h").append("<li class='dept_cd' id='"+ deptObj.deptCd + "' ><span onclick=\"deptClick('"+ deptObj.deptNo + "','"+ deptObj.deptCd + "','"+ deptObj.deptNm + "')\" ><strong>"+ deptObj.deptNm + "</strong></span></li>");
          } else {
            $("#"+deptObj.highDeptCd).append("<ul id='"+ deptObj.highDeptCd + "_h'><li class='dept_cd' id='"+ deptObj.deptCd + "' ><span onclick=\"deptClick('"+ deptObj.deptNo + "','"+ deptObj.deptCd + "','"+ deptObj.deptNm + "')\" ><strong>"+ deptObj.deptNm + "</strong></span></li></ul>");
          }
          deptMap.put(deptObj.highDeptCd,'');
        }
        //템프리스트에서 본트리리스트로
        var branches = $($("#tempTree").html()).appendTo("#tree");
        $("#tree").treeview({
            add: branches
        });

        $("#pop_bg").css("display","block");
        $("#selectDept_area").dialog({
          title:title_name,
          resizable: false,
          autoOpen: false,
          width: dialog_width,
          height: 500,
          modal: false,
          close: function(ev, ui) {
             $("#pop_bg").hide();
          }
        });

        $('#selectDept_area').dialog('open');
        return false;
      });
      isfirstSelectDept = 0;
    }else{
      $("#pop_bg").css("display","block");
      $('#selectDept_area').dialog('open');
    }
  }

  var _deptno = 0;

  //선택한 값 부모창에 설정
  function deptClick(deptno, deptcd, deptnm) {

    if(dialog_mode == 'dept_mode' ){
      //부모창에 함수를 작성한다.
      setParentDeptParams(deptcd,deptnm,deptno);
      $('#selectDept_area').dialog('close');
    }else{
      _deptno = deptno;
      //부서별 사용자리스트
      selectUserDeptListDwr(1,1);
    }
  }

  //대행결재자 페이징 초기값
  var pageNo2 = 1;
  var groupNo2 = 1;

  //대행결재자 취득
  function selectUserDeptListDwr(pageNo2, groupNo2) {

      var entity = {
            pageNo:pageNo2,
            groupNo:groupNo2,
            deptNo:_deptno
          };

      //대행결재자 리스트초기화
      $(".tr_all2").remove();

      //대행결재자리스트취득
      dwrUserManagement.getSearchUserDeptListForDwr(entity, function resultSearchUserDeptList(result) {
        var userObj;
        //검색리스트가 1건이상일 경우
        for(var i=0 ; i < result.length ; i++){
          userObj = result[i];

          if(userObj.proxyApprYn == '0'){
            $("#userList2").append("<tr class=\"tr_all2\"><td>"+ userObj.userId +"</td><td>"+ userObj.userPosition +"</td><td><a style=\"cursor:pointer\" onclick=\"selectProxyUser('"+ userObj.userId +"','"+ userObj.userNm + "')\"><b>"+ userObj.userNm +"</b></a></td></tr>");
          }else{
            $("#userList2").append("<tr class=\"tr_all2\"><td>"+ userObj.userId +"</td><td>"+ userObj.userPosition +"</td><td><b>"+ userObj.userNm +"(부재중)</b></td></tr>");
          }

        }
        //검색리스트가 0건일 경우
        if(result.length < 1){
          $("#userList2").append("<tr class=\"tr_all2\"><td colspan=\"3\">검색내용이 없습니다. 부서를 선택해주세요.</td></tr>");
        }
      });

      //대행결재자리스트총건수취득
      dwrUserManagement.paginateUserDeptListTotCnt(entity, function resultPagerUserDeptList(result) {
        $(".page_area_ext2").html(result);
      });

  }

  //대행결재자선택한 값 부모창에 설정
  function selectProxyUser(userId, userNm) {
    setProxyUserParams(userId, userNm);
    $('#selectDept_area').dialog('close');
  }

</script>