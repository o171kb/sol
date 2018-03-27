<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="/module/jquery/plugin/jquery.cookie.js"></script>


<style type="text/css">
      SPAN.NoGap{ height: 17; border:0; padding:0; margin-bottom:-1; }
      .Scroll{ height: 670px; overflow: auto; width:100%}
      .menugrp{ display:none; }

  </style>
  
<script type="text/javaScript" language="javascript" defer="defer">

  $(document).ready(function(){

    $("#j_username").focus(function(){
    	if("사번" == $("#j_username").val()){
    		$("#j_username").val("");
    	}
    });

    $("#j_username").focusout(function(){
      var id =  $("#j_username").val();
      if(id.length <= 0){
        $("#j_username").val("사번");
      }
    });

    $(".btn_login").click(function(){
      var id = $("#j_username").val();

      if(id == "사번" || id.length <= 0){
        $('#j_username').focus();
        /* validator */
        alert("사번을 입력하세요.");
        return false;
      }
      if(id == "비밀번호" || id.length <= 0){
        $('#j_password').focus();
        /* validator */
        alert("비밀번호를 입력하세요.");
        return false;
      }

      /* cookie function */
      var cookie = $("#cookie").val();
      if(cookie == "true"){
        $.cookie('the_cookie');
        $.cookie('username', $('#j_username').val(), { expires: 31, path: '/'});
      }
      $("#loginForm").submit();
      return false;
    });
    
    
    $("#j_password").keypress(function(event) {
      var id = $("#j_username").val();
      var pwd = $("#j_password").val();

      if ( event.which == 13 ) {
        if(id == "사번" || id.length <= 0){
          $('#j_username').focus();
          /* validator */
          alert("사번을 입력하세요.");
          return false;
        }
        if(pwd.length <= 0){
          $('#j_password').focus();
          /* validator */
          alert("비밀번호를 입력하세요.");
          return false;
        }

        /* cookie function */
        var cookie = $("#cookie").val();
        if(cookie == "true"){
          $.cookie('the_cookie');
          $.cookie('username', $('#j_username').val(), { expires: 31, path: '/'});
        }
        $("#loginForm").submit();
        return false;
      }
    });

    /* cookie function */
    //쿠키값이 있다면 쿠키값 채우고 쿠키체크하고 비밀번호에 포커스 준다.
    if($.cookie('username') != null){
      $('#j_username').val($.cookie('username'));
      $('#j_password').focus();
      $('input[name=j_checked]:checkbox').attr("checked", true);
    }

    //체크되어 있다면 쿠키값을 true로 셋팅
    if($("#j_checked").attr("checked",true)){
      $("#cookie").val("true");
    }
    
    //사번저장을 클릭하면 id를 쿠키에 저장
    $("#j_checked").change(function(){
      var chk = $(this).attr("checked");
      var id =  $("#j_username").val();
      if(chk == true){
        $("#cookie").val("true");

      }else if(chk == false){
        $("#cookie").val("false");
        $.cookie('username', '', { expires: -1, path: '/' });
      }
    });
    
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

  });
  

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

  /*$.cookie('the_cookie'); //cookie취득
  $.cookie('the_cookie', 'the_value'); //cookie를 셋팅
  $.cookie('the_cookie', 'the_value', { expires: 7 }); //쿠키의 유효기간을 7일간으로 지정
  $.cookie('the_cookie', '', { expires: -1 }); //cookie 삭제
  $.cookie('the_cookie', null); //cookie 삭제 */

</script>

<%-- 
<div class="login">
  <input type="hidden" id="cookie" name="cookie"/>
  <form id="loginForm" name="loginForm" action="<c:url value='/j_spring_security_check'/>" method="post">
    <div class="login_layout">
      <div class="login_back">
        <div class="login_logo"><!--logo--></div>
        <fieldset>
          <legend>관리자 로그인</legend>
          <div>
            <span><label>사번</label><input type="text" id="j_username" name="j_username" value="사번" /></span>
            <span><label>비밀번호</label><input type="password" id="j_password" name="j_password" value="" /></span>
          </div>
          <em><input type="checkbox" id="j_checked" name="j_checked"/><label>사번 저장</label></em>
          <input type="submit" class="btn_login" />
          <c:if test="${result == 'error'}"><div><span id="error" style="color:red; font-size:11px;">로그인 정보가 올바르지 않습니다.</span></div></c:if>
        </fieldset>

      </div>

      <p><!----></p>
    </div>
  </form>
</div> --%>


<div id="wrapper">
    <div id="header">
    	<img style="margin: 10px 0 0 20px; cursor:pointer;" src="<c:url value="/image/admin/logo.png"/>" onClick="javascript:showPage('adHome.do','0');" />
    </div>
    
	<div id="content">
      <%-- <tiles:insertAttribute name="body" /> --%>
      	<div align="center" style="margin-top:10px;"> 
	     	<div style="background:url('../../image/admin/main_back_741.jpg'); background-repeat:no-repeat; width:741px; height:323px; align:center;" >
	     		<ul style="float:right;">
	     			<li><img src="<c:url value="/image/admin/main_btn_01.jpg"/>" style="margin:13px 10px 0 0"></li>
	     			<li><img src="<c:url value="/image/admin/main_btn_02.jpg"/>" style="margin:13px 10px 0 0"></li>
	     			<li><img src="<c:url value="/image/admin/main_btn_03.jpg"/>" style="margin:13px 10px 0 0"></li>
	     		</ul>
	     	</div>
		</div>
      	<%-- <table border="0" id="loginMenu" >
      		<tr>
      			<td rowspan="4">
      			</td>
      		</tr>
      		<tr>
      			<td class="loginTd">
      				<img src = "<c:url value="../../image/admin/main_btn01.jpg"/>" width="130px" height="130px"  >
      			</td>
      		</tr> 
      		<tr>
      			<td class="loginTd">
      				<img src = "<c:url value="../../image/admin/main_btn02.jpg"/>" width="130px" height="130px">
      			</td>
      		</tr>
      		<tr>
      			<td class="loginTd">
      				<img src = "<c:url value="../../image/admin/main_btn03.jpg"/>" width="130px" height="130px">
      			</td>
      		</tr>
      	</table> --%>
      	
      	
<!-- 공지사항 -->
		<div>
			<input type="hidden" id="boardNo" name="boardNo"/>
			<div class="img_va1"><img src="<c:url value="/image/admin/btn_right.gif"/>" />공지사항</div>
				<table class="tbws1">
					<tr>
						<th style=" width:10%;">번호</th>
						<th style="width:*;">제목</th>
						<th style="width:15%;">등록일</th>
						<th style="width:15%;">등록자</th>
					</tr>
				<c:choose>
					<c:when test="${fn:length(noticeList)>0}">
					<c:forEach var="notice" items="${noticeList}" varStatus="status" end="9">
					<c:set var="sips" value="${status.count }"/>
						<tr boardNo="${notice.boardNo}">
							<td><c:out value="${(voBoard.pageIndex-1)*voBoard.pageUnit + status.count}"/></td>
							<td class="align_l"><c:out value="${notice.boardTitle}"/></td>
							<td><c:out value="${notice.regDm}"/></td>
							<td><c:out value="${notice.userId}"/></td>
						</tr>
					</c:forEach>
					</c:when>
					<c:otherwise>
						<tr><td colspan="4">데이터가 없습니다.</td></tr>
					</c:otherwise>
				</c:choose>
				</table>
		 </div>
		 <!-- //공지사항 -->
     </div>
	<div id="sidebar">
			<div class="log_txt">
			<form id="loginForm" name="loginForm" action="<c:url value='/j_spring_security_check'/>" method="post">
				<%-- <input type="text" id="j_username" name="j_username" value="사번" />
				<input type="password" id="j_password" name="j_password" value="" />
				<span><em><input type="checkbox" id="j_checked" name="j_checked"/><label>사번 저장</label></em></span>
         		<input type="submit" class="btn_login" />
				<c:if test="${result == 'error'}"><div><span id="error" style="color:red; font-size:11px;">로그인 정보가 올바르지 않습니다.</span></div></c:if> --%>
				<div style="margin-top:5px">
					<table>
						<tr>
							<td><input type="text" id="j_username" name="j_username" value="사번" size="20" tabindex="1"/></td>
							<td rowspan="2"><input type="submit" class="btn_login" /></td>
						</tr>
						<tr>
							<td><div style="margin-top:5px"><input type="password" id="j_password" name="j_password" tabindex="2" value="" size="21"/></div></td>
						</tr>
						<tr>
							<td>
								<div style="margin-top:5px">
									<input type="checkbox" id="j_checked" name="j_checked" /><label>ID 저장</label>
									&nbsp;&nbsp;|&nbsp;&nbsp; Help
								</div>
							</td>
						</tr>
						<c:if test="${result == 'error'}">
							<tr>
								<td colspan="2">
									<div style="margin-top:5px">
										<span id="error" style="color:red; font-size:11px;">로그인 정보가 올바르지 않습니다.</span>
									</div>
								</td>
							</tr>
						</c:if>
					</table>
				</div>
			</form>
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
     </div>
    <div id="footer">
     	<div style="border: 1px; border-color: #919191"></div>
		<address>
		 (150-972) 서울시 영등포구 문래 3가 55-20 에이스하이테크시티 1-1동 411호 TEL : 070 - 4610 - 7000 / FAX : 02 - 3439 - 1260 <br />
		 Copyright (c) 2008 SOLUPIA All rights reserved.
		
		</address>
    </div>
 





