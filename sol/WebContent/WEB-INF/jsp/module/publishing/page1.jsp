<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javaScript" defer="defer">
  $(document).ready(function() {

    //메뉴설정
    openMenu('06');
    $("#date").datepicker();
    $("#date2").datepicker();
  });
</script>

<h3>예외 &gt; 정책예외신청</h3>


 <!-- 기안자정보 -->
 <div class="img_va"><img src="<c:url value="/image/admin/btn_right.gif"/>" />기안자정보</div>
 <table class="tbws1">
  <tr>
   <th rowspan="2">신청자</th>
   <th>성 명</th>
   <td>홍길동</td>
   <th>직 급</th>
   <td>사원</td>
  </tr>
  <tr>
   <th>부 서</th>
   <td>솔루션기술팀</td>
   <th>전화번호</th>
   <td>010-2222-3456</td>
  </tr>
 </table>
 <!-- //기안자정보 -->

 <!-- 결제자정보 -->
 <div class="img_va"><img src="<c:url value="/image/admin/btn_right.gif"/>" />결재자정보</div>
 <table class="tbws1">
  <tr>
   <th style="width:5px;"></th>
   <th>결재구분</th>
   <th>결재자</th>
   <th>직급</th>
   <th>부서</th>
   <th style="width:5px;">삭제</th>
  </tr>
  <tr>
   <td><input type="radio" id="" name="btn_rdo"/></td>
   <td>결재</td>
   <td>홍길동</td>
   <td>과장</td>
   <td>기술2부</td>
   <td><input type="checkbox" id="" name=""/></td>
  </tr>
  <tr>
   <td><input type="radio" id="" name="btn_rdo"/></td>
   <td>결재</td>
   <td>홍길동</td>
   <td>과장</td>
   <td>기술2부</td>
   <td><input type="checkbox" id="" name=""/></td>
  </tr>
  <tr>
   <td><input type="radio" id="" name="btn_rdo"/></td>
   <td>결재</td>
   <td>홍길동</td>
   <td>과장</td>
   <td>기술2부</td>
   <td><input type="checkbox" id="" name=""/></td>
  </tr>
  <tr>
   <td><input type="radio" id="" name="btn_rdo"/></td>
   <td>결재</td>
   <td>홍길동</td>
   <td>과장</td>
   <td>기술2부</td>
   <td><input type="checkbox" id="" name=""/></td>
  </tr>
 </table>
 <div class="btn_area">
  <div class="left" style="margin:0px 3px 0px 3px"><input type="button" class="swhite" value="순서위로"/></div>
  <div class="left" style="margin:0px 3px 0px 3px"><input type="button" class="swhite" value="순서아래로"/></div>

  <div class="right" style="margin:0px 3px 0px 3px"><input type="button" class="swhite" value="추가"/></div>
  <div class="right" style="margin:0px 3px 0px 3px"><input type="button" class="swhite" value="삭제"/></div>
 </div>
 <!-- //결제자정보 -->

 <!-- 예외대상PC -->
 <div class="img_va"><img src="<c:url value="/image/admin/btn_right.gif"/>" />예외대상PC</div>
 <table class="tbws1">
  <tr>
   <th style="width:5px;">번호</th>
   <th style="width:15px;">사용자</th>
   <th style="width:15px;">부서명</th>
   <th style="width:25px;">연락처</th>
   <th style="width:25px;">제조번호</th>
   <th style="width:*px;">IP</th>
   <th style="width:5px;">삭제</th>
  </tr>
  <tr>
   <td>1</td>
   <td>홍길동</td>
   <td>개발1부</td>
   <td>010-1111-2222</td>
   <td>000-0000-0000</td>
   <td>111.555.1.554</td>
   <td><input type="checkbox" id="" name=""/></td>
  </tr>
  <tr>
   <td>2</td>
   <td>홍길동</td>
   <td>개발1부</td>
   <td>010-1111-2222</td>
   <td>000-0000-0000</td>
   <td>111.555.1.554</td>
   <td><input type="checkbox" id="" name=""/></td>
  </tr>
  <tr>
   <td>3</td>
   <td>홍길동</td>
   <td>개발1부</td>
   <td>010-1111-2222</td>
   <td>000-0000-0000</td>
   <td>111.555.1.554</td>
   <td><input type="checkbox" id="" name=""/></td>
  </tr>
  <tr>
   <td>4</td>
   <td>홍길동</td>
   <td>개발1부</td>
   <td>010-1111-2222</td>
   <td>000-0000-0000</td>
   <td>111.555.1.554</td>
   <td><input type="checkbox" id="" name=""/></td>
  </tr>

 </table>
 <div class="btn_area">
  <div class="right" style="margin:0px 3px 0px 3px"><input type="button" class="swhite" value="추가"/></div>
  <div class="right" style="margin:0px 3px 0px 3px"><input type="button" class="swhite" value="삭제"/></div>
 </div>
 <!-- //예외대상PC -->

 <!-- 신청정보 -->
 <div class="img_va"><img src="<c:url value="/image/admin/btn_right.gif"/>" />신청정보</div>
 <table class="tbws1">
  <tr>
   <th style="width:40px;">용&nbsp;&nbsp;&nbsp;&nbsp;도 </th>
   <td class="align_l">
    <input type="radio" id="" name="btn_used_rdo" checked="checked"/> <span>출장</span>
    <input type="radio" id="" name="btn_used_rdo"/> <span>교육</span>
    <input type="radio" id="" name="btn_used_rdo"/> <span>자료제출</span>
    <input type="radio" id="" name="btn_used_rdo"/> <span>개발</span>
   </td>
  </tr>
  <tr>
   <th>신청기간 </th>
   <td class="align_l">
    <input type="text" id="date" name="" class="middle"/> <input type="text" id="" name="" class="short"/> 시&nbsp;&nbsp;<input type="text" id="" name="" class="short"/> 분&nbsp;&nbsp;~&nbsp;&nbsp;
    <input type="text" id="date2" name="" class="middle"/> <input type="text" id="" name="" class="short"/> 시&nbsp;&nbsp;<input type="text" id="" name="" class="short"/> 분
   </td>
  </tr>
 </table>
 <!-- //신청정보 -->


<div class="btn_area" style="margin-top:20px;">
 <div class="left" style="margin:0px 3px 0px 3px"><input type="button" class="bwhite" value="목록"/></div>
 <div class="right" style="margin:0px 3px 0px 3px"><input type="button" class="bwhite" value="등록"/></div>
</div>
