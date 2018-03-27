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
CoTtStrParams grpMenuList = (CoTtStrParams)request.getAttribute("grpMenuList");
%>

<script type="text/javascript" src="<c:url value='/dwr/interface/dwrUserGrpIdManagement.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/chkValidate.js'/>"></script>
<script type="text/javaScript" src="/admin/sitemap/common/OnetimeSubmit.js"></script>
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
    
    //그룹ID 체크 초기값
    var chkValidateGrpId = "";
      //액션완료 메시지 띄우기
      var checkMessage = $("#checkMessage").val();
      if(checkMessage == "success_reg"){
          $('div#menuTabArea').css("display","block");
          $('div#userGrpTabArea').css("display","none");

          $.prompt("등록 완료 되었습니다.", {
            buttons: { "확인": true },
            submit: function(e,v,m,f){
              if (v) {
                  $("#checkMessage").val("");
                  $("#grpForm").attr("action","<c:url value='/adGrpSearchList.do'/>");
                  $("#grpForm").submit();
              }
            }
          });
      }
		
      $( '#userGrpId' ).live("blur keyup", function() {
    	  $(this).val( $(this).val().replace( /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/g, '' ) );
    	});

      /*등록*/
      $("#btn_regist").click(function(){
        $.prompt("등록 하시겠습니까?", {
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
              $("#checkMessage").val("success_reg");
              $("#grpForm").attr("action","<c:url value='/adGrpRegist.do'/>");
              $("#grpForm").submit();
            }
          }
        });
        return false;
      });

      /*다음*/
      $("#btn_next").click(function(){
        $(".chkId").html("");
        $(".chkId").hide();
        $(".chkGrpNm").html("");
        $(".chkGrpNm").hide();
        var userGrpIdLength = $("#userGrpId").val().length;
        var grpNmLength = $("#grpNm").val().length;
       if($("#userGrpId").val() == null || $("#userGrpId").val() == ""){
           $(".chkId").html("*아이디를 입력해주세요.");
           $(".chkId").show();
           return false;
         }
       if( $("#userGrpId").val() != $("#compairId").val()){
         $(".chkId").html("*아이디 중복검사를 해주세요.");
             $(".chkId").show();
             return false;
       }
        if(chkValidateGrpId != 1){
          $(".chkId").html("*아이디 중복검사를 해주세요.");
          $(".chkId").show();
          return false;
        }
        if(userGrpIdLength > 30){
          $(".chkId").html("*아이디는 30자 이하로 입력해주세요.");
          $(".chkId").show();
          return false;
        }
        if($("#grpNm").val() == null || $("#grpNm").val() == ""){
          $(".chkGrpNm").html("*그룹명을 입력해주세요.");
          $(".chkGrpNm").show();
          return false;
        }
        if(grpNmLength > 30){
          $(".chkGrpNm").html("*그룹명은 50자 이하로 입력해주세요.");
          $(".chkGrpNm").show();
          return false;
        }
          $('div#menuTabArea').css("display","block");
          $('div#userGrpTabArea').css("display","none");

          $('#userGrpTab').attr('class','tab_no');
          $('#menuTab').attr('class','tab_cho');
      });

      /*이전*/
      $("#btn_back").click(function(){
          $('div#userGrpTabArea').css("display","block");
          $('div#menuTabArea').css("display","none");

          $('#userGrpTab').attr('class','tab_cho');
          $('#menuTab').attr('class','tab_no');
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

      /*아이디 중복 체크*/
      $("#id_check").click(function(){

        $(".chkId").html("");
        $(".chkId").hide("");
//         if ($(".error").css("display") !="none"){
//           return false;
//         }
        var userGrpId = $("#userGrpId").val();
        var userGrpIdLength = $("#userGrpId").val().length;
        if(userGrpId == null || userGrpId == ""){
          $(".chkId").html("*아이디를 입력해주세요.");
          $(".chkId").show();
          return false;
        };
        if(userGrpIdLength > 30){
          $(".chkId").html("*아이디는 30자 이하로 입력해주세요.");
          $(".chkId").show();
          return false;
        };
        var entity = {
            userGrpId:userGrpId
        };
        dwrUserGrpIdManagement.checkUserGrpId(entity, function appendFormList(data){
        $("#checkResult").val(data);
          
          if(data == 1) {
              var compairId = $("#userGrpId").val();
              
              $(".chkId").html("<font style='color:black;'>*등록 할 수 있는 그룹ID 입니다.</font>");
              $(".chkId").show();
              $("#checkConfirm").val('1');
              $("#compairId").val(compairId);
              chkValidateGrpId = 1;
              return false;
          } else if(data == -1) {
              $(".chkId").html("*이미사용중인 그룹ID입니다.");
              $(".chkId").show();
              chkValidateGrpId = 0;
              return false;
          }
        });
      });

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
<h3>시스템 &gt; 그룹관리 &gt; 등록</h3>
<!-- HEADER AND -->


<a id="userGrpTab" class="tab_cho">사용자그룹</a>
<a id="menuTab" class="tab_no">메뉴권한</a>
<div class="tabline"></div>

<br />
<!-- CONTENT START -->
<form:form commandName="voCoUserGrp" id="grpForm" name="grpForm" method="post" onsubmit="return onetimeSubmit(this)">
  <input type="hidden" id="userGrpNo" name="userGrpNo" value="${voCoUserGrp.userGrpNo}">
  <input type="hidden" id="checkMessage" name="checkMessage" value="${voCoUserGrp.checkMessage}"/>
  <input type="hidden" id="compAdmin" name="compAdmin" value=""/>
  <input type="hidden" id="compAdCode" name="compAdCode" value=""/>
  <input type="hidden" id="compairId" name="compairId" value=""/>

  <div id="userGrpTabArea" style="display:block;">
    <!-- 검색 -->
    <table class="tbws1">
      <tr>
        <th style="width:100px;">그룹ID<em class="red">*</em></th>
        <td class="align_l">
          <form:input type="text" path="userGrpId" id="userGrpId"/>
          <input type="button"  style="text-align:center;" value="중복검사" id="id_check" class="swhite"><span style="margin-left:5px; color:#8C8C8C">*영문자 8~20자 입력</span><p>
<!--           <label class="error" name="error" for="userGrpId" generated="true" style="display:none;">에러메시지</label> -->
          <span class="chkId" style="color:red; display:none;">에러메시지</span>
        </td>
      </tr>
      <tr>
        <th>그룹명<em class="red">*</em></th>
        <td class="align_l">
         <form:input path="grpNm" id="grpNm" type="text" value="${voCoUserGrp.grpNm}" style="width:300px;"/><p>
         <span class="chkGrpNm" style="color:red; display:none;">에러메시지</span>
        </td>
      </tr>
      <tr>
        <th>그룹설명</th>
        <td class="align_l"><form:input path="grpExp" id="grpExp" type="text" value="${voCoUserGrp.grpExp}" style="width:300px;"/></td>
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
             <input type="radio" class="compAdminCode" name="compAdminCode" value="${code[0]}" <c:if test="${code[0] == '02'}">checked</c:if>/>${code[1]}
            </c:forEach>
           </span>
        </td>
      </tr>
    </table>
    <div class="btn_area">
      <div class="right" style="margin:0px 3px 0px 3px"><input type="button" id="btn_next" class="nwhite" value="다음>>"/></div>
    </div>
  </div>


  <!-- 메뉴등록 ##################################################################################################  -->
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
          <th style="width:10%">참조</th>
          <th style="width:10%">갱신</th>
      </tr>
      <%
      TtSession tts = (TtSession) TtHttpSessionManager.getTtSession(request);
      List<VoCoMenu> userMenuList = (List<VoCoMenu>)tts.get(CsCoConstDef.SS_KEY_018);
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
    <div class="btn_area">
      <div class="left" style="margin:0px 3px 0px 3px"><input type="button" id="btn_back" class="nwhite" value="<<이전" /></div>
      <div class="right" style="margin:0px 3px 0px 3px"><input type="button" id="btn_list" class="nwhite" value="목록"/></div>
      <div class="right" style="margin:0px 3px 0px 3px"><input type="button" id="btn_regist" class="nwhite" value="등록"/></div>
    </div>
  </div>

  <!-- 메뉴등록 ##################################################################################################  -->

</form:form>
<!-- CONTENT END -->
