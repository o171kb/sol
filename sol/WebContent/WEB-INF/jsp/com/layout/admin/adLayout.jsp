<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="tt.base.TtSession"%>
<%@page import="tt.base.TtHttpSessionManager"%>
<%@page import="tt.com.constant.CsCoConstDef"%>
<%@page import="tt.com.bean.VoCoMenu"%>
<%@page import="tt.com.utils.UtCoFileNameUtils"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	//현재 시간(초로환산)
	int _time = (int)(System.currentTimeMillis());
	
	TtSession tts = (TtSession) TtHttpSessionManager.getTtSession(request);
	List<VoCoMenu> mnuLvlList = (List<VoCoMenu>)tts.get("mnuLvlList");
	if (null == mnuLvlList) {
	    throw new Exception("로그인정보가 없습니다.");
	}
	pageContext.setAttribute("mnuLvlList", mnuLvlList);


%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head id="Head1" runat="server">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="imagetoolbar" content="no" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="robots" content="none" />


<title><tiles:getAsString name="title" /></title>
<link type="text/css" rel="stylesheet" href="<c:url value='/module/jquery/ui/css/jquery-impromptu.css'/>" />
<!-- front 공통 layer popup -->
<link type="text/css"
 href="<c:url value='/module/jquery/ui/css/custom-theme/jquery-ui-1.8.14.custom.css'/>?d=<%=_time %>" rel="stylesheet" />
<link type="text/css" href="<c:url value='/module/smartEditor/css/smart_editor2_items.css'/>?d=<%=_time %>"
 rel="stylesheet" />
<link type="text/css" href="<c:url value='/module/smartEditor/css/smart_editor2_out.css'/>?d=<%=_time %>"
 rel="stylesheet" />
<link type="text/css" href="<c:url value='/module/smartEditor/css/smart_editor2.css'/>?d=<%=_time %>" rel="stylesheet" />
<link type="text/css" href="<c:url value='/module/uploadify/uploadify.css'/>?d=<%=_time %>" rel="stylesheet" />
<link type="text/css" href="<c:url value='/module/smartEditor/css/smart_editor2_in.css'/>?d=<%=_time %>"
 rel="stylesheet" />
<link type="text/css" href="<c:url value='/css/admin/layout.css'/>?d=<%=_time %>" rel="stylesheet" />
<link type="text/css" href="<c:url value='/module/jquery/plugin/css/jquery.treeview.css'/>" rel="stylesheet" />

<script type='text/javascript' src="<c:url value='/dwr/util.js'/>?d=<%=_time %>"></script>
<script type='text/javascript' src="<c:url value='/dwr/engine.js'/>?d=<%=_time %>"></script>

<script type="text/javascript" src="<c:url value='/module/jquery/jquery.js'/>?d=<%=_time %>"></script>
<script type="text/javascript" src="<c:url value='/module/jquery/plugin/jquery.printElement.js'/>?d=<%=_time %>"></script>
<script type="text/javascript" src="<c:url value='/module/jquery/ui/jquery-ui-1.8.14.custom.min.js'/>?d=<%=_time %>"></script>
<script type="text/javascript" src="<c:url value='/module/jquery/plugin/jquery.xmldom.min.js'/>?d=<%=_time %>"></script>
<script type="text/javascript" src="<c:url value='/module/smartEditor/js/HuskyEZCreator.js'/>?d=<%=_time %>"></script>
<script type="text/javascript" src="<c:url value='/module/uploadify/jquery.uploadify-3.1.min.js'/>?d=<%=_time %>"></script>
<script type="text/javascript" src="<c:url value='/module/uploadify/swfobject.js'/>?d=<%=_time %>"></script>
<%-- 	<script type="text/javascript" src="<c:url value='/module/jquery/plugin/jquery.paulund_modal_box.js'/>?d=<%=_time %>"></script> --%>
<script type="text/javascript" src="<c:url value='/module/jquery/plugin/jquery-impromptu.js'/>?d=<%=_time %>"></script>
<script type="text/javascript" src="<c:url value='/module/jquery/plugin/jquery.cookie.js'/>?d=<%=_time %>"></script>
<script type="text/javascript" src="<c:url value='/module/jquery/plugin/jquery.base64.min.js'/>?d=<%=_time %>"></script>

<script type="text/javascript" src="<c:url value='/js/imgover.js'/>?d=<%=_time %>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.validate.js'/>?d=<%=_time %>"></script>
<script type="text/javascript" src="<c:url value='/js/util_map.js'/>?d=<%=_time %>"></script>

<script type='text/javascript' src="<c:url value='/module/jquery/plugin/jquery.treeview.js'/>"></script>
<script type='text/javascript' src="<c:url value='/module/jquery/plugin/jquery.treeview.edit.js'/>"></script>
<script type='text/javascript' src="<c:url value='/module/jquery/plugin/jquery.treeview.ext.js'/>"></script>
<script type='text/javascript' src="<c:url value='/module/jquery/plugin/jquery.treeview.edit.ext.js'/>"></script>

</head>
<!-- 초기 -->

<%-- <body>
 <div id="wrapper">
  <div id="wrapper2">
   <div id="header">
    <tiles:insertAttribute name="header" />
   </div>
   <div class="" id="main">
    <div id="content">
     <tiles:insertAttribute name="body" />
    </div>
    <div id="sidebar">
     <tiles:insertAttribute name="menu" />
    </div>
   </div>
   <div id="footer">
    <tiles:insertAttribute name="footer" />
   </div>
  </div>
 </div>
</body> --%>



<!-- 페이지의 권한을 가져오기 위한 c태그, 해당 페이지의 권한을 mnuLvlCd에 담고 해당내용을 각각의 페이지에서 참조하여 페이지의 권한처리를 한다 -->
<c:set var="mnuApprovLvl" value="none" scope="session"/>
<c:set var="mnuReqLvl" value="none" scope="session"/>
<c:set var="mstYn" value="0" scope="session"/>
<c:set var="grpYn" value="0" scope="session"/>
<c:set var="selfYn" value="0" scope="session"/>


<c:choose>
  <c:when test="${fn:length(mnuLvlList)>0}">
    <c:set var="new_item" value="TRUE"/>
    <c:set var="next_item" value="TRUE"/>
    <c:set var="end_item" value="FALSE"/>

    <c:forEach var="menu" items="${mnuLvlList}" varStatus="status">
    	<c:set var="grpYn" value="${menu.grpYn}" scope="session"/>
    	<c:set var="selfYn" value="${menu.selfYn}" scope="session"/>
    	<c:set var="mstYn" value="${menu.mstYn}" scope="session"/>
      <c:choose>
        <c:when test="${status.last}">
           <c:set var="end_item" value="TRUE"/>
        </c:when>
        <c:otherwise>
          <c:forEach var="next_menu" items="${mnuLvlList}" begin="${status.count}" end="${status.count}">
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

      </c:if>
      <!-- menu 2dept  -->
      <c:set var="selectId" value="${ttObjParams.id}"/><c:set var="menuId" value="${menu.menuId}"/>
      
        <c:if test="${selectId == menuId}">
        	<c:if test="${menu.menuLvl == '7' }">
          		<c:set var="mnuLvlCd" value="read" scope="session"/>
          	</c:if>
          	<c:if test="${menu.menuLvl == '15' }">
          		<c:set var="mnuLvlCd" value="write" scope="session"/>
          	</c:if>
        </c:if>

    	<c:if test="${menu.menuAction == 'adMyApprovalList.do' }">
      		<c:set var="mnuApprovLvl" value="read" scope="session"/>
      	</c:if>
      	
      	<c:if test="${menu.menuAction == 'adMyDraftList.do' }">
      		<c:set var="mnuReqLvl" value="read" scope="session"/>
      	</c:if>
        
        <c:if test="${selectId != menuId}">

        </c:if>
      <c:if test="${end_item eq 'TRUE'}">
      </c:if>
      <c:set var="new_item" value="${end_item}"/>
    </c:forEach>
  </c:when>
</c:choose>






 <body> 
  <div id="wrapper">
    <div id="header">
     <tiles:insertAttribute name="header" />
    </div>
     <div id="content">
      <tiles:insertAttribute name="body" />
     </div>
     <div id="sidebar">
      <tiles:insertAttribute name="menu" />
     </div>
    <div id="footer">
     <tiles:insertAttribute name="footer" />
    </div>
  </div>
 </body>
</html>