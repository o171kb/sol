<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javaScript" language="javascript" defer="defer">
$(document).ready(function(){
    openMenu('02');

    $("#btn_agree").click(function(){
        $("#exctForm").attr("action", "<c:url value='/adMsgDraft.do'/>");
        $("#exctForm").submit();
        return false;
     });

    $("#btn_disAgree").click(function(){
        $("#exctForm").attr("action", "<c:url value='/adminHome.do'/>");
        $("#exctForm").submit();
        return false;
    });
 });
</script>

<!-- HEADER START -->
<h3>예외 &gt; 메신저예외신청</h3>
<!-- HEADER AND -->

<!-- CONTENT START -->
<form:form commandName="voExct" id="exctForm" name="exctForm" method="post" >

<div class="agreeLayout" >
 <div class="agreeContent">
 <div class="agreeHead" >아래 내용에 대한 동의가 있어야만 신청이 가능합니다.</div>
 <p>신청자 및 사용자인 본인은 "신청" 내용이 사실과 동일함을 증명하며,</p>
 <p>다음과 같은 보안서약서 내용을 충분히 숙지하고 성실히 준수할 것을 동의합니다.</p>
 <p>만일 서약사항을 위반하였을 경우에는 관계법에 따른 민·형사상의 책임은 물론,</p>
 <p>회사에 취업규칙이나 관련 정보보호규정에 따른 징계조치 등 어떠한 처벌도 감수</p>
 <p style="margin-bottom: 50px;">할 것이며, 회사에 끼친 손해에 대해 지체 없이 배상하며 회복시킬 것을 서약합니다.</p>

 <p>1. 업무용으로만 사용하며, 개인적인 목적으로는 사용하지 않겠습니다.</p>
 <p>2. 신청을 통해 얻은 권한이 더 이상 필요하지 않는 경우 즉시 권한을 반납 하겠습니다.</p>
 <p style="margin-bottom: 100px;">3. 신청자와 부서장은 정보 보호규정을 지켜야 할 의무와 책임을 다하겠습니다.</p>

 <p>신청일 : ${userInfo.regDm}  ${userInfo.updDm}</p>
 <p>신청자 : ${userInfo.userNm}</p>
 </div>
 <div style="margin:0 auto; margin-top:20px; width:95%; text-align:right;"><input type="button" name="" id="btn_agree" value="동의 함" class="swhite" style="cursor: pointer;" /> &nbsp;&nbsp;&nbsp;<input type="button" name="" id="btn_disAgree" value="동의 안함" class="swhite" style="cursor: pointer;" />
 </div>
</div>


</form:form>
<!-- CONTENT END -->