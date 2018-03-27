
$(document).ready(function(){

  $("#searchStartDm").datepicker({
      dateFormat:'yy-mm-dd',
      showOn: 'focus',
      changeYear: true,
      changeMonth: true,
      showMonthAfterYear: true ,
      currentText: '오늘' ,
      dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'], // 요일의 한글 형식.
      monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] ,
      showButtonPanel: true, // 하단 today, done  버튼기능 추가 표시 (기본은 false)
      onSelect : function(dateText, inst){
        $("#searchEndDm").datepicker("option", "minDate", dateText);
      }
    });

  $("#searchEndDm").datepicker({
      dateFormat:'yy-mm-dd',
      showOn: 'focus',
      changeMonth: true,
      changeYear: true,
      showMonthAfterYear: true ,
      currentText: '오늘',
      dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'], // 요일의 한글 형식.
      monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] ,
       showButtonPanel: true, // 하단 today, done  버튼기능 추가 표시 (기본은 false)
       onSelect : function(dateText, inst){
         $("#searchStartDm").datepicker("option", "maxDate", dateText);
      }
  });

    $.datepicker.setDefaults($.datepicker.regional['ko']);
});