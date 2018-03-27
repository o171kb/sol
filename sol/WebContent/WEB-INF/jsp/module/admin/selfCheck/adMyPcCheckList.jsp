<%@page import="tt.bean.VoSelfDiaItem"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="tt.base.TtSession"%>
<%@page import="tt.base.TtHttpSessionManager"%>
<%@page import="tt.com.constant.CsCoConstDef"%>
<%@page import="java.util.*"%>
<%@page import="tt.com.CoTtStrParams"%>

<%
  TtSession tts = (TtSession) TtHttpSessionManager.getTtSession(request);
  String userId = (String)tts.get(CsCoConstDef.SS_KEY_003);
  pageContext.setAttribute("user_id", userId);

%>

<script type="text/javaScript" defer="defer">

  $(document).ready(function(){
  //메뉴설정
      openMenu('03');
      //StartCheck();
      
      /* 권한처리 */
      <c:if test="${mnuLvlCd != 'write'}">
    		$("img[alt='점검시작']").hide();
      </c:if>         

  });

  //-자가진단 대상자이면 ActiveX를 호출하여 자가진단을 실행하고 자가진단 결과를 입력
  function StartCheck() {
      if (checkIsDiagnosticTarget()) {
        var checkCtrl;
        checkCtrl = $('#checkCtrl')[0];

          if (checkCtrl.object != null) {
              //clearIt();
              checkCtrl.StartCheck();
              setResult();
          }else {
              alert('PC 점검툴 Active X가 새로 배포되었습니다.\n\nPC 점검툴 Active X를 (새로) 설치하신 후 실행하여 주세요.');
              window.location.reload();
          }
      } else {
          alert('자가진단 대상자가 아닙니다.');
          return false;
      }
  }

  //- 자가진단 대상자 여부를 체크
    function checkIsDiagnosticTarget() {
        var isDiagnosticTarget = true;
        /*
        var ajax = $.ajax({
            type: 'post',
            url: '/adMyPcCheckInsertAction.do',
            async: false,
            success: function(msg) {
                if (msg == 'false') {
                    isDiagnosticTarget = false;
                }
            }
        });
        */
        return isDiagnosticTarget;
    }


    //- 자가진단의 이력정보를 결과를 저장
    function setResult() {
        var checkCtrl;
        checkCtrl = $('#checkCtrl').get(0);
        var selfDiagnosticPkey = 0;
        var pkey = checkCtrl.PRIMARY_KEY;

        if (pkey.substring(9) == '00000000000') {
            pkey = (checkCtrl.MAC_ADDRESS).replace(/-/gi, '');
        }

/*
        var s = 'selfDiagnosticPkey=' + selfDiagnosticPkey
        + '\nVIRUS_FILE_VERIFY=' + checkCtrl.VIRUS_FILE_VERIFY
        + '\nSPYWARE_VERIFY=' + checkCtrl.SPYWARE_VERIFY
        + '\nUPDATE_AUTO_DISC_VERIFY=' + checkCtrl.UPDATE_AUTO_DISC_VERIFY
        + '\nUPDATE_AUTO_VACCINE_VERIFY=' + checkCtrl.UPDATE_AUTO_VACCINE_VERIFY
        + '\nVIRUS_UPFILE_VERIFY=' + checkCtrl.VIRUS_UPFILE_VERIFY
        + '\nSERVICE_PACK_VERIFY=' + checkCtrl.SERVICE_PACK_VERIFY
        + '\nIE_VERSION_VERIFY=' + checkCtrl.IE_VERSION_VERIFY
        + '\nOS_UPDATE_VERIFY=' + checkCtrl.OS_UPDATE_VERIFY
        + '\nOS_PATCH_VERIFY=' + checkCtrl.OS_PATCH_VERIFY
        + '\nIE_PATCH_VERIFY=' + checkCtrl.IE_PATCH_VERIFY
        + '\nGUEST_VERIFY=' + checkCtrl.GUEST_VERIFY
        + '\nADMIN_VERIFY=' + checkCtrl.ADMIN_VERIFY
        + '\nSIMPLE_PWD_VERIFY=' + checkCtrl.SIMPLE_PWD_VERIFY
        + '\nSIMPLE_PWD_CURRENT=' + checkCtrl.SIMPLE_PWD_CURRENT
        + '\nSAVER_PASSWD_VERIFY=' + checkCtrl.SAVER_PASSWD_VERIFY
        + '\nTENMIN_VERIFY=' + checkCtrl.TENMIN_VERIFY
        + '\nREG_KEY_VERIFY=' + checkCtrl.REG_KEY_VERIFY
        + '\nRPC_SHARE_VERIFY=' + checkCtrl.RPC_SHARE_VERIFY
        + '\nSHARE_FOLDER_VERIFY=' + checkCtrl.SHARE_FOLDER_VERIFY
        + '\nEVERYONE_VERIFY=' + checkCtrl.EVERYONE_VERIFY
        + '\nNO_SERVICE_VERIFY=' + checkCtrl.NO_SERVICE_VERIFY
        + '\nRW_EXCUTE_VERIFY=' + checkCtrl.RW_EXCUTE_VERIFY
        + '\nAUTOBAT_READ_VERIFY=' + checkCtrl.AUTOBAT_READ_VERIFY
        + '\nNTFS_VERIFY=' + checkCtrl.NTFS_VERIFY
        + '\nUPDATE_AUTO_DISC_CURRENT=' + checkCtrl.UPDATE_AUTO_DISC_CURRENT

        + '\nIE_VERSION_CURRENT=' + checkCtrl.IE_VERSION_CURRENT
        + '\nOS_PATCH_CURRENT=' + checkCtrl.OS_PATCH_CURRENT
        + '\nIE_PATCH_CURRENT=' + checkCtrl.IE_PATCH_CURRENT
        + '\nTENMIN_CURRENT=' + checkCtrl.TENMIN_CURRENT
        + '\nSHARE_FOLDER_CURRENT=' + checkCtrl.SHARE_FOLDER_CURRENT

        + '\nSOFTWARE_VERIFY=' + checkCtrl.SOFTWARE_VERIFY
        + '\nNO_SERVICE_CURRENT=' + checkCtrl.NO_SERVICE_CURRENT;

      alert(s);
 */



       var idx = "";
        //바로 저장
        //SerialNumber = pkey + userid + macAddress + osType
        if (checkCtrl.PRIMARY_KEY) {
          var userId = '${user_id}';

            var ajax = $.ajax({
                type: 'post',
                url: '/adMyPcCheckInsertAction.do',
                async: false,
                data: 'serialNumber=' + pkey + '&userId=' + userId + '&macAddress=' + checkCtrl.MAC_ADDRESS + '&osType=' + checkCtrl.OS_TYPE + '&idx=' + checkCtrl.idx,
                success: function(msg) {

//                   alert("초반 저장 : "+ pkey +" : "+ msg +" idx: "+ idx);

                  if (msg == 'false') {
                        alert('자가진단 대상자가 아닙니다.');
                        return false;
                    } else {
                      idx = msg;
                    }
                }
            });
        }


     // 결과 저장
        //if (selfDiagnosticPkey != 0) {
        if (true) {
            var ajax = $.ajax({
                type: 'post',
                url: '/adMyPcCheckResultInsertAction.do',
                async: false,
                data: 'selfDiagnosticIdx=' + idx
                    + '&VIRUS_FILE_VERIFY=' + checkCtrl.VIRUS_FILE_VERIFY
                    + '&SPYWARE_VERIFY=' + checkCtrl.SPYWARE_VERIFY
                    + '&UPDATE_AUTO_DISC_VERIFY=' + checkCtrl.UPDATE_AUTO_DISC_VERIFY
                    + '&UPDATE_AUTO_VACCINE_VERIFY=' + checkCtrl.UPDATE_AUTO_VACCINE_VERIFY
                    + '&VIRUS_UPFILE_VERIFY=' + checkCtrl.VIRUS_UPFILE_VERIFY
                    + '&SERVICE_PACK_VERIFY=' + checkCtrl.SERVICE_PACK_VERIFY
                    + '&IE_VERSION_VERIFY=' + checkCtrl.IE_VERSION_VERIFY
                    + '&OS_UPDATE_VERIFY=' + checkCtrl.OS_UPDATE_VERIFY
                    + '&OS_PATCH_VERIFY=' + checkCtrl.OS_PATCH_VERIFY
                    + '&IE_PATCH_VERIFY=' + checkCtrl.IE_PATCH_VERIFY
                    + '&GUEST_VERIFY=' + checkCtrl.GUEST_VERIFY
                    + '&ADMIN_VERIFY=' + checkCtrl.ADMIN_VERIFY
                    + '&SIMPLE_PWD_VERIFY=' + checkCtrl.SIMPLE_PWD_VERIFY
                    + '&SIMPLE_PWD_CURRENT=' + checkCtrl.SIMPLE_PWD_CURRENT
                    + '&SAVER_PASSWD_VERIFY=' + checkCtrl.SAVER_PASSWD_VERIFY
                    + '&TENMIN_VERIFY=' + checkCtrl.TENMIN_VERIFY
                    + '&REG_KEY_VERIFY=' + checkCtrl.REG_KEY_VERIFY
                    + '&RPC_SHARE_VERIFY=' + checkCtrl.RPC_SHARE_VERIFY
                    + '&SHARE_FOLDER_VERIFY=' + checkCtrl.SHARE_FOLDER_VERIFY
                    + '&EVERYONE_VERIFY=' + checkCtrl.EVERYONE_VERIFY
                    + '&NO_SERVICE_VERIFY=' + checkCtrl.NO_SERVICE_VERIFY
                    + '&RW_EXCUTE_VERIFY=' + checkCtrl.RW_EXCUTE_VERIFY
                    + '&AUTOBAT_READ_VERIFY=' + checkCtrl.AUTOBAT_READ_VERIFY
                    + '&NTFS_VERIFY=' + checkCtrl.NTFS_VERIFY
                    + '&UPDATE_AUTO_DISC_CURRENT=' + checkCtrl.UPDATE_AUTO_DISC_CURRENT

                    + '&IE_VERSION_CURRENT=' + checkCtrl.IE_VERSION_CURRENT
                    + '&OS_PATCH_CURRENT=' + checkCtrl.OS_PATCH_CURRENT
                    + '&IE_PATCH_CURRENT=' + checkCtrl.IE_PATCH_CURRENT
                    + '&TENMIN_CURRENT=' + checkCtrl.TENMIN_CURRENT
                    + '&SHARE_FOLDER_CURRENT=' + checkCtrl.SHARE_FOLDER_CURRENT

                    + '&SOFTWARE_VERIFY=' + checkCtrl.SOFTWARE_VERIFY
                    + '&NO_SERVICE_CURRENT=' + checkCtrl.NO_SERVICE_CURRENT,
                success: function(msg) {
//                   alert("결과 저장 : " + msg);
                  if (msg == 'false') {
                        alert('다시 시도해 주세요.');
                        return false;
                    } else {
                      location.reload();
                    }
                }
            });
        }
    }

</script>

<h3>자가진단 &gt; 나의PC점검현황</h3>

 <div style="margin:15px 0px 30px 0px;">
   <span style="color:blue; font-size: 13px;">내PC의 보안수준을 한눈에 확인하세요 !</span>
       <img src="<c:url value="/image/admin/btn_self_start.png"/>" onclick="StartCheck();" style="cursor:pointer; vertical-align: middle; margin-left:20px;" alt="점검시작"/>
 </div>


<span style="padding-top: 20px;">
  <!-- ※ 점검항목을 Click하시면 점검항목별 PC 안전관리 가이드를 참고할 수 있습니다. -->
 <span style="float:right;"><img src="<c:url value="/image/admin/self_chk_01.png"/>" style="vertical-align: middle;" alt="안전함"/>(안전함)  <img src="<c:url value="/image/admin/self_dang_01.png"/>" style="vertical-align: middle; padding-left:7px;" alt="취약함"/>(취약함)</span>
</span>
<c:forEach var="pkList" items="${pkList}" end="0">
<table class="tbws1" style="width:100%">
 <tr>
  <th width="20%">제조번호(S/N)</th>
  <td width="80%">${pkList.serialNumber}</td>
 </tr>
 <tr>
  <th>운영체제</th>
  <td>${pkList.osType}</td>
 </tr>
</table>
</c:forEach>
<br/>

 <script type="text/javascript">
 window.onload=function(){
   mergeCell(document.all.datalist,5,0,1,0);
 };
 /****************************************************
     tbl      : 병합할 대상 table object
     startRow : 병합 시작 row, title 한 줄일 경우 1
     cNum     : 병합 실시할 컬럼번호, 0부터 시작
     length   : 병합할 row의 길이, 보통 1
     add      : 비교할 기준에 추가할 컬럼번호
               A | 1
               B | 1
              을 서로 구분하고 싶다면, add에 0번째
              컬럼을 추가
 *****************************************************/
 function mergeCell(tbl, startRow, cNum, length, add)
 {
     var isAdd = false;
     if(tbl == null) return;
     if(startRow == null || startRow.length == 0) startRow = 1;
     if(cNum == null || cNum.length == 0) return ;
     if(add  == null || add.length == 0) {
         isAdd = false;
     }else {
         isAdd = true;
         add   = parseInt(add);
     }
     cNum   = parseInt(cNum);
     length = parseInt(length);
     rows   = tbl.rows;
     rowNum = rows.length;
     tempVal  = '';
     cnt      = 0;
     startRow = parseInt(startRow);
     for( i = startRow; i < rowNum; i++ ) {
         curVal = rows[i].cells[cNum].innerHTML;
         if(isAdd) curVal += rows[i].cells[add].innerHTML;
         if( curVal == tempVal ) {
             if(cnt == 0) {
                 cnt++;
                 startRow = i - 1;
             }
             cnt++;
         }else if(cnt > 0) {
             merge(tbl, startRow, cnt, cNum, length);
             startRow = endRow = 0;
             cnt = 0;
         }else {
         }
         tempVal = curVal;
     }
     if(cnt > 0) {
         merge(tbl, startRow, cnt, cNum, length);
     }
 }
 /*******************************************
     mergeCell에서 사용하는 함수
 ********************************************/
 function merge(tbl, startRow, cnt, cellNum, length)
 {
     rows = tbl.rows;
     row  = rows[startRow];
     for( i = startRow + 1; i < startRow + cnt; i++ ) {
         for( j = 0; j < length; j++) {
             rows[i].deleteCell(cellNum);
         }
     }
     for( j = 0; j < length; j++) {
         row.cells[cellNum + j].rowSpan = cnt;
     }
 }
 </script>
<!-- itemList -->
<table id="datalist" class="tbws1" style="width:30%; float:left;">
 <tr>
  <th colspan="2">점검 항목</th>
 </tr>
 <tr>
  <th rowspan="2" width="120px;"  style="background-color: #EEEAEA;">점검결과</th>
  <th style="background-color: #EEEAEA;">안전함</th>
 </tr>
 <tr>
  <th style="background-color: #EEEAEA;">취약함</th>
 </tr>
 <tr>
  <td colspan="7" style="border-left-color: #fff; border-right-color: #fff; height:0.2px;"></td>
 </tr>
  <c:forEach var="item" items="${itemList}" varStatus="g_st">
    <tr height="35px;">
    <td>${item.groupNm}</td>
    <td class="align_l">${item.chkItem}</td>
    </tr>
  </c:forEach>
</table>

<!-- //itemList -->

<div style="position: relative; top:0 auto;">
 <div style="position:absolute; top:0px; left:480px;">
<c:forEach var="pkList" items="${pkList}" varStatus="pkST" end="2">
<%-- ${pkST.end - pkST.index} --%>
   <table class="tbws1" style="width:90px; float:left;">
       <tr>
         <th style="border-left-color: #fff;">${pkList.insDate}</th>
       </tr>
       <tr>
         <td style="border-left-color: #fff;">${safetyArr[pkST.index]}</td>
       </tr>
       <tr>
         <td style="border-left-color: #fff;">${notSafetyArr[pkST.index]}</td>
       </tr>
       <tr>
        <td colspan="7" style="border-left-color: #fff; border-right-color: #fff; height:0.2px;"></td>
       </tr>
       <c:forEach var="result" items="${resultList}" varStatus="resultST">
         <c:if test="${pkList.idx == result.selfDiagnosticIdx}">
            <tr height="35px;">
              <td style="border-left-color: #fff;">
                <c:if test="${result.isSafety eq '1'}">
                   <img src="<c:url value="/image/admin/self_chk_01.png"/>" style="vertical-align: middle;" alt="안전함"/>
                </c:if>
                 <c:if test="${result.isSafety eq '0'}">
                   <img src="<c:url value="/image/admin/self_dang_01.png"/>" style="vertical-align: middle; padding-left:7px;" alt="취약함"/>
                </c:if>
              </td>
            </tr>
         </c:if>
       </c:forEach>
   </table>
</c:forEach>
</div>
</div>

<object width=0 height=0 id='checkCtrl' classid='CLSID:2E0B3A4D-6EFE-4C60-9EEC-29E93F34AECF'
 codebase='http://192.168.1.60:8080/CQCheck/CQCheck2007.cab#version=2,0,0,7'>
 <param name='UrlPath' value='http://192.168.1.60:8080/CQCheck/CQCheck2007/rules/' />
 <param name='Sequence' value='S2013042900000000000' />
</object>
