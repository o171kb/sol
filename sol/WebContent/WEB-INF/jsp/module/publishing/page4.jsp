<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javaScript" defer="defer">

  $(document).ready(function(){

    //메뉴설정
    openMenu('06');
    $("#date").datepicker();
    $("#date2").datepicker();
  });

</script>

<h3>통제예외목록</h3>

<!-- 신청정보 -->
 <table class="tbws1">
  <tr>
   <th style="width:40px;">용&nbsp;&nbsp;&nbsp;&nbsp;도 </th>
   <td colspan="2" class="align_l">
    <input type="radio" id="" name="btn_used_rdo" checked="checked"/> <span>출장</span>
    <input type="radio" id="" name="btn_used_rdo"/> <span>교육</span>
    <input type="radio" id="" name="btn_used_rdo"/> <span>자료제출</span>
    <input type="radio" id="" name="btn_used_rdo"/> <span>개발</span>
   </td>
  </tr>
  <tr>
   <th>신청기간 </th>
   <td colspan="2" class="align_l">
    <input type="text" id="date" name="" class="middle"/> <input type="text" id="" name="" class="short"/> 시&nbsp;&nbsp;<input type="text" id="" name="" class="short"/> 분&nbsp;&nbsp;~&nbsp;&nbsp;
    <input type="text" id="date2" name="" class="middle"/> <input type="text" id="" name="" class="short"/> 시&nbsp;&nbsp;<input type="text" id="" name="" class="short"/> 분
   </td>
  </tr>
  <tr>
   <th rowspan="4">통제예외목록</th>
   <td class="align_l" width="100px;">해제대상 IP</td>
   <td class="align_l">
    <div class="bigTd">
     <table class="tbws3">
      <tr>
       <td style="border-right: hidden;">1</td>
       <td>
        <input type="text" class="middle200" />&nbsp;&nbsp;~
        <input type="text" class="middle200" />&nbsp;&nbsp;
        <img src="<c:url value="/image/admin/btn_01.gif"/>" class="img_verti"/>
       </td>
      </tr>
     </table>
    </div>
    <div style="float:left; width:*; margin-left:20px; padding:18px 10px 10px 20px;">
     <img src="<c:url value="/image/admin/btn_02.gif"/>" class="img_verti"/>
    </div>
   </td>
  </tr>
  <tr>
   <td class="align_l">해제대상 사이트</td>
   <td class="align_l">
    <div class="bigTd">
     <table class="tbws3">
      <tr>
       <td style="border-right:hidden; padding-right:20px;">1</td>
       <td class="align_l">
        <input type="text" class="middle500" style="margin-right:10px;"/>
        <img src="<c:url value="/image/admin/btn_01.gif"/>" class="img_verti"/>
       </td>
      </tr>
     </table>
    </div>
    <div style="float:left; width:*; margin-left:20px; padding:18px 10px 10px 20px;">
     <img src="<c:url value="/image/admin/btn_02.gif"/>" class="img_verti"/>
    </div>
   </td>
  </tr>
  <tr>
   <td class="align_l">해제대상 메신저</td>
   <td class="align_l">
    <div class="bigTd">
     <table class="tbws3">
      <tr>
       <td style="border-right: hidden; padding-right:20px;">1</td>
       <td class="align_l">
        <input type="text" class="middle500" style="margin-right:10px;"/>
        <img src="<c:url value="/image/admin/btn_01.gif"/>" class="img_verti"/>
       </td>
      </tr>
     </table>
    </div>
    <div style="float:left; width:*; margin-left:20px; padding:18px 10px 10px 20px;">
     <img src="<c:url value="/image/admin/btn_02.gif"/>" class="img_verti"/>
    </div>
   </td>
  </tr>
  <tr>
   <td class="align_l">해제대상 ActiveX</td>
   <td class="align_l">
    <div class="bigTd">
     <table class="tbws3">
      <tr>
       <td style="border-right: hidden; padding-right:20px;">1</td>
       <td class="align_l">
        <input type="text" class="middle500" style="margin-right:10px;"/>
        <img src="<c:url value="/image/admin/btn_01.gif"/>" class="img_verti"/>
       </td>
      </tr>
     </table>
    </div>
    <div style="float:left; width:*; margin-left:20px; padding:18px 10px 10px 20px;">
     <img src="<c:url value="/image/admin/btn_02.gif"/>" class="img_verti"/>
    </div>
   </td>
  </tr>
  <tr>
   <th class="align_l">신청사유</th>
   <td colspan="2" class="align_l"><input type="text" class="long" id="" name=""/></td>
  </tr>
 </table>
<div class="btn_area" style="margin-top:20px;">
 <div class="right" style="margin:0px 3px 0px 3px"><input type="button" class="bwhite" value="결재상신"/></div>
</div>




