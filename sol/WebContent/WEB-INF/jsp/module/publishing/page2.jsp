<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javaScript" defer="defer">

  $(document).ready(function(){

      //메뉴설정
      openMenu('06');
  });

</script>

<h3>시스템 &gt; 게시관리 &gt; 등록</h3>

<table class="tbws1">
 <tr>
  <th>구 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;분&nbsp;<em class="red">*</em></th>
  <td class="align_l"><input type="text" class="middle2" id="" name=""/></td>
 </tr>
 <tr>
  <th>제 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;목&nbsp;<em class="red">*</em></th>
  <td class="align_l"><input type="text" class="long" id="" name=""/></td>
 </tr>
 <tr>
  <th style="vertical-align: top;">내 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;용&nbsp;<em class="red">*</em></th>
  <td class="align_l"><textarea id="" name="" cols="124" rows="20"></textarea></td>
 </tr>
 <tr>
  <th>첨부파일</th>
  <td class="align_l">
   <input type="file" class="long" id="" name=""/>
   <!-- <input type="button" class="swhite" id="" name="" value="찾아보기"/>
   <input type="button" class="swhite" id="" name="" value="등록"/> -->
  </td>
 </tr>
</table>
<div class="btn_area" style="margin-top:20px;">
 <div class="left" style="margin:0px 3px 0px 3px"><input type="button" class="bwhite" value="목록"/></div>
 <div class="right" style="margin:0px 3px 0px 3px"><input type="button" class="bwhite" value="등록"/></div>
</div>



