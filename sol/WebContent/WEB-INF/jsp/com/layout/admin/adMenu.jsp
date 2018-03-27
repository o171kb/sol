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
  <style type="text/css">
      SPAN.NoGap{ height: 17; border:0; padding:0; margin-bottom:-1; }
      .Scroll{ height: 670px; overflow: auto; width:100%}
      .menugrp{ display:none; }

  </style>
  <script type="text/javaScript">
      var curA = null;
      $(document).ready(function(){

        //메뉴초기
        $("#sidebar").height($("#content").height() +  250);
        $(".Scroll").height($("#content").height() +165 );
        //$("#admin-menu").height("100");
        //var openMenuId = $("#mu_url").val();
        //if(openMenuId != 'adminHome'){
        //openMenu(openMenuId);
        //}


      });

      function openMenu(mnId){
          var exclusive = false;//if true, submenus are hidden when another is selected.
          var nodes = document.getElementById('Scroll').childNodes;
          //alert(mnId);
          for(i = 0; i < nodes.length; i++){
              if(nodes[i].className == "menugrp"){
                  if(nodes[i].id == "MN_" + mnId){
                          //alert($("#IMG_" + mnId).attr("src"));
                      if(nodes[i].style.display == "block"){
                          nodes[i].style.display = "none";
                          /* $("#IMG_" + mnId).attr("src","<c:url value='/" + "image/admin/btn_right_01.gif" + "'/>"); 수정*/
                          $("#IMG_" + mnId).attr("src","<c:url value='/" + "image/admin/002.png" + "'/>");
                          $("#BACK_" + mnId).css("background-color","#91B8DF");
                      }else{
                          nodes[i].style.display = "block";
                          /* $("#IMG_" + mnId).attr("src","<c:url value='/" + "image/admin/btn_down.gif" + "'/>"); 수정 */
                          $("#IMG_" + mnId).attr("src","<c:url value='/" + "image/admin/003.png" + "'/>");
                          $("#BACK_" + mnId).css("background-color","#3C638A");
                      }
                  }else if(exclusive){
                      nodes[i].style.display = "none";
                      /* $("#IMG_" + mnId).attr("src","<c:url value='/" + "image/admin/btn_down.gif" + "'/>"); 수정 */
                      $("#IMG_" + mnId).attr("src","<c:url value='/" + "image/admin/003.png" + "'/>");
                      $("#BACK_" + mnId).css("background-color","#3C638A");
                  }
              }

          }
      }


     function smallMenu(){
       <%-- var url = "<%=url%>";
       alert(url);
       var action = $("#url_"+url); //.attr("src");
       alert(action);
       //action = "<c:url value='image/admin/btn_down.gif'/>";
       $("#url_"+url).attr("src","<c:url value='/" + "image/admin/btn_down.gif" + "'/>"); --%>
     }

     function showPage(action,id) {
       $("#id").val(id);
       $("#menuForm").attr("action","<c:url value='/" + action + "'/>");
       $("#menuForm").submit();
       return false;
     }
</script>
  <div class="log_box">
    <div class="log_txt">
      ID : ${user_id}<p/>
      IP : ${user_ip}<p/>
            부서 : ${dept_nm}
    </div>
    <div class="log_btn">
      <a href="<c:url value='/logout'/>" class="mout"><img src="<c:url value="/image/admin/logout.gif"/>"/></a>
    </div>
  </div>
  <!-- <div id="admin-menu" style="margin: 10px 10px;">  수정-->
  <div id="admin-menu">
  <form name="menuForm" id="menuForm" method="post">
    <input type="hidden" id="id" name="id" value="${ttObjParams.id}"/>
    <input type="hidden" id="mu_url" value="${mu_url}">
        <div bgcolor="#E7E7E7" height="1" width="205" valign="TOP">
          <div class="Scroll" id="Scroll">
            <c:choose>
              <c:when test="${fn:length(userMenuList)>0}">
                <c:set var="new_item" value="TRUE"/>
                <c:set var="next_item" value="TRUE"/>
                <c:set var="end_item" value="FALSE"/>

                <c:forEach var="menu" items="${userMenuList}" varStatus="status">
                  <c:choose>
                    <c:when test="${status.last}">
                       <c:set var="end_item" value="TRUE"/>
                    </c:when>
                    <c:otherwise>
                      <c:forEach var="next_menu" items="${userMenuList}" begin="${status.count}" end="${status.count}">
                        <c:set var="next_menu_grp_no" value="${next_menu.menuGrpNo}"/>
                        <c:set var="next_menu_grp_nm" value="${next_menu.menuGrpNm}"/>
                          <c:if test="${menu.menuGrpNo eq next_menu_grp_no}">
                            <c:set var="end_item" value="FALSE"/>
                          </c:if>
                          <c:if test="${menu.menuGrpNo ne next_menu_grp_no}">
                            <c:set var="end_item" value="TRUE"/>
                          </c:if>
                      </c:forEach>
                    </c:otherwise>
                  </c:choose>

                  <c:set var="lineImgfileName" value="null"/>
                  <c:if test="${end_item eq 'TRUE'}">
                    <c:set var="lineImgfileName" value="image/admin/btn_03.gif"/>
                  </c:if>
                  <c:if test="${end_item ne 'TRUE'}">
                    <c:set var="lineImgfileName" value="image/admin/btn_03.gif"/>
                  </c:if>

                  <c:if test="${new_item eq 'TRUE'}">
                    <div id="BACK_${menu.menuGrpNo}" style="background-color:#91B8DF; width:100%;">
                        <!-- <span style="width:10; height:21;" class="NoGap">11</span> -->
                        <%-- <span class="NoGap"><img id="IMG_${menu.menuGrpNo}" src="<c:url value='${menu.menuGrpIcon}'/>" onclick="openMenu('${menu.menuGrpNo}')" width="50" height="40" border="0" style=cursor:pointer>
                          <font class="UnselectedMenuItem" onclick="openMenu('${menu.menuGrpNo}')" onselectstart="return false" style=cursor:pointer>
                            <span style="color:#333399; ">
                              <b>${menu.menuGrpNm}</b>
                            </span>
                          </font>
                       </span> --%>
                       <table border="0" style=" border-bottom:1px solid #5C97CF;">
                         <tr>
                           <td>
                             <div style="width:100%;">
                               <img id="IMG_${menu.menuGrpNo}" src="<c:url value='${menu.menuGrpIcon}'/>" onclick="openMenu('${menu.menuGrpNo}')" style="width:41px; height:36px; cursor:pointer;" align="middle">
                               <span class="UnselectedMenuItem" onclick="openMenu('${menu.menuGrpNo}')" onselectstart="return false" style="cursor:pointer; color:#FFFFFF; font-size:15px;">
                                 ${menu.menuGrpNm}
                              </span>
                             </div>
                          </td>
                         </tr>
                       </table>
                       <%-- <div style="" class="NoGap"><img id="IMG_${menu.menuGrpNo}" src="<c:url value='${menu.menuGrpIcon}'/>" onclick="openMenu('${menu.menuGrpNo}')" width="50" height="43" border="0" style=cursor:pointer>
                          <span class="UnselectedMenuItem" onclick="openMenu('${menu.menuGrpNo}')" onselectstart="return false" style="cursor:pointer; color:#333399; font-size:24px; ">
                            ${menu.menuGrpNm}
                          </span>
                       </div> --%>
                    </div>
                    <div class="menugrp" id="MN_${menu.menuGrpNo}" display="none">
                  </c:if>
                  <!-- menu 2dept  -->
                  <c:set var="selectId" value="${ttObjParams.id}"/><c:set var="menuId" value="${menu.menuId}"/>
                  <%-- ${url} :: ${action} --%>
                  <div style="margin:10px 0 10px 0;" class="menuname">
                    <span style="margin: 0px 10px; width:16" class="NoGap"></span><span class="NoGap">
                    <c:if test="${selectId == menuId}">
                      <img align="ABSMIDDLE" src="<c:url value='image/admin/bullet_01.jpg'/>" width="9" height="9" border="0">
                      <a href="#" onclick="showPage('${menu.menuAction}','${menu.menuId}'); return false;" class="UnselectedMenuItem"><span style="color:#0000FF;">${menu.menuNm}</span></a>
                    </c:if>
                    <c:if test="${selectId != menuId}">
                      <img align="ABSMIDDLE" src="<c:url value='image/admin/bullet_01.jpg'/>" width="9" height="9" border="0">
                      <a href="#" onclick="showPage('${menu.menuAction}','${menu.menuId}'); return false;" class="UnselectedMenuItem"><span style="color:#787878;">${menu.menuNm}</span></a>
                    </c:if>
                      <p style="display: none">${menu.menuExp}</p>
                    </span>
                  </div>

                  <c:if test="${end_item eq 'TRUE'}">
                    </div>
                  </c:if>
                  <c:set var="new_item" value="${end_item}"/>
                </c:forEach>
              </c:when>
            </c:choose>
          </div>
        </div>
  </form>
  </div>
