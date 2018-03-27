<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="tt.com.bean.VoCoMenu"%>
<%@page import="tt.com.bean.VoCoUserGrpMenu"%>
<%@page import="tt.com.CoTtStrParams"%>
<%@page import="tt.com.constant.CsCoConstDef"%>
<%@page import="tt.base.TtSession"%>
<%@page import="tt.base.TtHttpSessionManager"%>
<%@page extends="tt.base.TtBaseServlet" %>

<%
//List<VoCoUserGrpMenu> userGrpMenuList = (List<VoCoUserGrpMenu>)request.getAttribute("userGrpMenuList");

CoTtStrParams grpMenuList = (CoTtStrParams)request.getAttribute("grpMenuList");
%>

<script type="text/javaScript" src="/admin/sitemap/common/OnetimeSubmit.js"></script>
<script type="text/javascript" src="<c:url value='/js/chkValidate.js'/>"></script>
<style type="text/css">
  label.error{
    display:block;
    color:red;
  }
</style>
<script type="text/javaScript" defer="defer">

  $(document).ready(function(){

      //메뉴설정
      openMenu('01');

      var strMode = "사용자그룹정보";
      if($("input:checkbox[name='compAdminChkBox']").is(":checked") == true){
        $("#compAdminChk").show();
      }
      if('1' == $('#isUserGrpMod').val()){
        $('div#userGrpTabArea').css("display","block");
        $('div#menuTabArea').css("display","none");
        strMode = "사용자그룹정보";
        $('#userGrpTab').attr('class','tab_cho');
        $('#menuTab').attr('class','tab_no');
      }else{
        $('div#menuTabArea').css("display","block");
        $('div#userGrpTabArea').css("display","none");
        strMode = "메뉴권한정보";
        $('#userGrpTab').attr('class','tab_no');
        $('#menuTab').attr('class','tab_cho');
      }

      //사용자그룹정보 수정
      $('#userGrpTab').click(function () {
        $('div#userGrpTabArea').css("display","block");
        $('div#menuTabArea').css("display","none");
        $('#isUserGrpMod').val("1");
        strMode = "사용자그룹정보";
        $('#btn_modify_menu').hide();
        $('#btn_modify').show();
        $('#userGrpTab').attr('class','tab_cho');
        $('#menuTab').attr('class','tab_no');
      });

      //그룹메뉴정보 수정
      $('#menuTab').click(function () {
        $('div#menuTabArea').css("display","block");
        $('div#userGrpTabArea').css("display","none");
        $('#isUserGrpMod').val("0");
        $('#btn_modify_menu').show();
        $('#btn_modify').hide();
        strMode = "메뉴권한정보";
        $('#userGrpTab').attr('class','tab_no');
        $('#menuTab').attr('class','tab_cho');
      });


      //액션완료 메시지 띄우기
      var checkMessage = $("#checkMessage").val();
      if(checkMessage == "success_mod"){
        $.prompt( strMode + " 수정이 완료 되었습니다.");
      }

      /*수정*/
      $("#btn_modify").click(function(){

        $.prompt(strMode + " 수정을 하시겠습니까?", {
          buttons: { "예": true, "아니오": false },
          submit: function(e,v,m,f){
            if (v) {
              var grpNmLength = $("#grpNm").val().length;
              if($("#grpNm").val() == null || $("#grpNm").val() == ""){
                  $(".chkGrpNm").html("*그룹명을 입력해주세요.");
                  $(".chkGrpNm").show();
                  $.prompt.close(true);
                  return false;
                }
                if(grpNmLength > 30){
                  $(".chkGrpNm").html("*그룹명은 50자 이하로 입력해주세요.");
                  $(".chkGrpNm").show();
                  $.prompt.close(true);
                  return false;
                }
              if($("input:checkbox[name='compAdminChkBox']").is(":checked") == true){
                    $("#compAdmin").val("on");
                    var adCode = $("input:radio[name='compAdminCode']:checked").val();
                    $("#compAdCode").val(adCode);
                 }else{
                    $("#compAdmin").val("0");
                 }
              $("#checkMessage").val("success_mod");
              $("#grpForm").attr("action","<c:url value='/adGrpModify.do'/>");
              $("#grpForm").submit();
            }
          }
        });
        return false;
      });

      /*수정*/
      $("#btn_modify_menu").click(function(){

        $.prompt(strMode + " 수정을 하시겠습니까?", {
          buttons: { "예": true, "아니오": false },
          submit: function(e,v,m,f){
            if (v) {
              var boxes = $("#menuTabArea").find("input[type=checkbox]").is(':checked');
              if(boxes == false){
                $("#errorSelect").show();
                $.prompt.close(true);
                return false;
              }
              if($("input:checkbox[name='compAdminChkBox']").is(":checked") == true){
                    $("#compAdmin").val("on");
                    var adCode = $("input:radio[name='compAdminCode']:checked").val();
                    $("#compAdCode").val(adCode);
                 }else{
                    $("#compAdmin").val("0");
                 }
              $("#checkMessage").val("success_mod");
              $("#grpForm").attr("action","<c:url value='/adGrpModify.do'/>");
              $("#grpForm").submit();
            }
          }
        });
        return false;
      });

      /*목록*/
      $("#btn_list").click(function(){
        $("#checkMessage").val("");
        $("#grpForm").attr("action","<c:url value='/adGrpSearchList.do'/>");
        $("#grpForm").submit();
        return false;
      });

      /*메뉴수정*/
      $("#btn_nemu_mod").click(function(){
        $("#checkMessage").val("");
        $("#grpForm").attr("action","<c:url value='/adGrpMenuModify.do'/>");
        $("#grpForm").submit();
        return false;
      });

      /*전산관리자 사용 여부*/
      $("#compAdminChkBox").click(function(){
          if($("input:checkbox[name='compAdminChkBox']").is(":checked") == true){
            $("#compAdminChk").show();
             }else{
               $("#compAdminChk").hide();
             }
        });
      /*메뉴권한 선택 에러표시 숨기기*/
      $("#menuTabArea").find(":checkbox").click(function(){
          $("#errorSelect").hide();
        });
      
      
      /* 권한처리 */
      <c:if test="${mnuLvlCd != 'write'}">
      		$("#btn_modify").attr("disabled", "disabled").attr("style", "color:#ABABAB");
      		$("#btn_modify_menu").attr("disabled", "disabled").attr("style", "color:#ABABAB");
      		//$("input:checkbox[name='chk_each']").attr("disabled", true);
      		$("input:text").attr("disabled", true);
      		$("input:checkbox").attr("disabled", true);
      		$("textarea").attr("disabled", true);
      	  	$("input[value='선택해제']").attr("style", "color:#ABABAB");
      	    $("input[value='전체선택']").attr("style", "color:#ABABAB");
      	   	$('#btn_modify_menu').hide();
      </c:if>
      
      
  });
  function changeCheckBox(id, checkYn){
      var input = document.getElementById(id).getElementsByTagName("input");
      for(var i = 0, l = input.length; i < l; i++){
          if(input.item(i).type == "checkbox"){
              if(!input.item(i).disabled) input.item(i).checked = checkYn;
          }
      }
  }
</script>

<!-- HEADER START -->
<h3>시스템 &gt; 그룹관리 &gt; 수정</h3>
<!-- HEADER AND -->


<a id="userGrpTab" class="tab_cho">사용자그룹</a>
<a id="menuTab" class="tab_no">메뉴권한</a>
<!-- <div class="tabline"></div> -->

<br />
<!-- CONTENT START -->
<form:form commandName="voCoUserGrp" id="grpForm" name="grpForm" method="post" onsubmit="return onetimeSubmit(this)">
  <input type="hidden" id="userGrpNo" name="userGrpNo" value="${voCoUserGrp.userGrpNo}">
  <input type="hidden" id="isUserGrpMod" name="isUserGrpMod" value="${isUserGrpMod}">
  <input type="hidden" id="checkMessage" name="checkMessage" value="${voCoUserGrp.checkMessage}"/>
  <input type="hidden" id="compAdmin" name="compAdmin" value="${voCoUserGrp.compAdmin}"/>
  <input type="hidden" id="compAdCode" name="compAdCode" value="${voCoUserGrp.compAdCode}"/>


  <div id="userGrpTabArea" style="display:block;">
    <table class="tbws1">
      <tr>
        <th style="width:200px;">그룹ID</th>
        <td class="align_l">${voCoUserGrp.userGrpId}</td>
      </tr>
      <tr>
        <th>그룹명<em class="red">*</em></th>
        <td class="align_l">
        <form:input path="grpNm" name="grpNm" id="grpNm" type="text" value="${voCoUserGrp.grpNm}" style="width:300px;"/><p>
        <span class="chkGrpNm" style="color:red; display:none;">에러메시지</span>
        </td>
      </tr>
      <tr>
        <th>그룹설명</th>
        <td class="align_l"><form:input path="grpExp" type="text" value="${voCoUserGrp.grpExp}" style="width:300px;"/></td>
      </tr>
      <tr>
        <th>결재권한</th>
        <td class="align_l">
           <input type="checkbox" id="apprYn" name="apprYn" <c:if test="${voCoUserGrp.apprYn eq '1'}">checked</c:if>/> 결재권한부여
        </td>
      </tr>
      <tr>
        <th>마스터권한</th>
        <td class="align_l">
           <input type="checkbox" id="mstYn" name="mstYn" <c:if test="${voCoUserGrp.mstYn eq '1'}">checked</c:if>/> 마스터권한부여
        </td>
      </tr>
      <tr>
        <th>그룹관리권한</th>
        <td class="align_l">
           <input type="checkbox" id="grpYn" name="grpYn" <c:if test="${voCoUserGrp.grpYn eq '1'}">checked</c:if>/> 그룹관리권한부여
        </td>
      </tr>
      <tr>
        <th>본인결재권한</th>
        <td class="align_l">
           <input type="checkbox" id="selfYn" name="selfYn" <c:if test="${voCoUserGrp.selfYn eq '1'}">checked</c:if>/> 본인결재권한부여
        </td>
      </tr>
      <tr>
        <th>전산관리자</th>
        <td class="align_l">
           <input type="checkbox" id="compAdminChkBox" name="compAdminChkBox" <c:if test="${voCoUserGrp.compAdmin eq '1'}">checked</c:if>/> 전산관리자사용
           <span id="compAdminChk" style="padding-left: 10px; display:none;">
            <c:forEach var="code" items="${tt:getCodeValues('003') }" begin="1">
             <input type="radio" class="compAdminCode" name="compAdminCode" value="${code[0]}" <c:if test="${voCoUserGrp.compAdCode == code[0]}">checked</c:if>>${code[1]}
            </c:forEach>
           </span>
        </td>
      </tr>
    </table>
  </div>


  <!-- 메뉴수정 ##################################################################################################  -->
  <div id="menuTabArea" style="display:none;">
    <div class="btn_area">
      <div class="right" style="margin:0px 3px 0px 3px"><input type="button" id="btn_next" class="nwhite" value="전체선택" onclick="changeCheckBox('menuTabArea', true)"/></div>
      <div class="right" style="margin:0px 3px 0px 3px"><input type="button" id="btn_next" class="nwhite" value="선택해제" onclick="changeCheckBox('menuTabArea', false)"/></div>
    </div>
    <br />

    <table class="tbws1">
      <tr>
          <th style="width:30%">분류명</th>
          <th style="width:50%">메뉴명</th>
          <th style="width:10%">읽기권한</th>
          <th style="width:10%">쓰기권한</th>
      </tr>
      <%
      //全てのメニュー情報を取得
      //CTpAccount accountMan = new CTpAccount();
      //CSaProperties p = new CSaProperties();
      //p.put("GRP_CL", (String) ses.get("COMM_009"));
      //CSaResultSet rsAllMenu = accountMan.getAllMenu(prop, p, msgInfo);


      TtSession tts = (TtSession) TtHttpSessionManager.getTtSession(request);
      List<VoCoMenu> userMenuList = (List<VoCoMenu>)tts.get(CsCoConstDef.SS_KEY_018);


      //CoTtStrParams grpMenuList = (CoTtStrParams) tts.get(CsCoConstDef.SS_KEY_017);

      int listSize = userMenuList.size();

      for (int i = 0; i < listSize; ) {
        VoCoMenu tmpVoCoMenu = userMenuList.get(i);
        String grpNm = tmpVoCoMenu.getMenuGrpNm();
        String nextGrpNm = "";

      %>
      <tr>
          <td style="width:20%"><%=grpNm%></td>
              <%
              StringBuffer sbmenu = new StringBuffer();
              do {

                sbmenu.append("<tr><td style=\"width:70%\">" + tmpVoCoMenu.getMenuNm() + "</td>");
                boolean ishasViewAuth = hasViewAuth(tts, tmpVoCoMenu.getMenuAction());
                boolean ishasModAuth = hasModAuth(tts, tmpVoCoMenu.getMenuAction());

                boolean chckedView = false;
                boolean chckedMod = false;

                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@         " + grpMenuList.containsKey(tmpVoCoMenu.getMenuAction()) );

                if (grpMenuList.containsKey(tmpVoCoMenu.getMenuAction())) {
                    String menuLvl = grpMenuList.get(tmpVoCoMenu.getMenuAction());
                    if ("15".equals(menuLvl)) {
                        chckedView = true;
                        chckedMod = true;
                    } else if ("8".equals(menuLvl)) {
                        chckedMod = true;
                    } else if ("7".equals(menuLvl)) {
                        chckedView = true;
                    }
                }

                sbmenu.append("<td style=\"width:14%\"><input type=\"checkbox\" name=\"VIEW."
                        + tmpVoCoMenu.getMenuId()
                        + "\" value=\"7\""
                        + (chckedView ? " checked" : "")
                        + (ishasViewAuth ? "" : " disabled")
                        + ">" + "</td>");
                sbmenu.append("<td style=\"width:14%\">&nbsp;&nbsp;<input type=\"checkbox\" name=\"MOD."
                        + tmpVoCoMenu.getMenuId()
                        + "\" value=\"8\""
                        + (chckedMod ? " checked" : "")
                        + (ishasModAuth ? "" : " disabled")
                        + ">" + "</td></tr>");

                if(listSize > i +1){
                  i++;
                  tmpVoCoMenu = userMenuList.get(i);
                }else{
                  i++;
                  break;
                }
                nextGrpNm = userMenuList.get(i).getMenuGrpNm();

              } while (grpNm.equals(nextGrpNm));
              %>
          <td style="width:60%" colspan="3"><table class="tbmenu"><%=sbmenu.toString()%></table></td>
      </tr>
      <%
          };
      %>
      <tr>
       <td colspan="4" id="errorSelect" style="display:none; color:red;">*메뉴권한을 선택해주세요.</td>
      </tr>
    </table>
  </div>
  <div class="btn_area">
    <div class="right" style="margin:0px 3px 0px 3px"><input type="button" id="btn_list" class="nwhite" value="목록"/></div>
    <div class="right" style="margin:0px 3px 0px 3px"><input type="button" id="btn_modify" class="nwhite" value="수정"/></div>
    <div class="right" style="margin:0px 3px 0px 3px;"><input type="button" style="display:none;" id="btn_modify_menu" class="nwhite" value="수정"/></div>
  </div>
</form:form>
<!-- CONTENT END -->