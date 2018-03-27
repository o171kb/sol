
$(document).ready(function(){
    String.prototype.string = function(len){var s = '', i = 0; while (i++ < len) { s += this; } return s;};
    String.prototype.zf = function(len){return "0".string(len - this.length) + this;};

  $("#openDate").datepicker({
      dateFormat:'yy-mm-dd',
      showOn: 'focus',
      changeYear: true,
      changeMonth: true,
      showMonthAfterYear: true ,
      currentText: '오늘' ,
      minDate:$("#currentDm").val(),
      dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'], // 요일의 한글 형식.
      monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] ,
      showButtonPanel: true, // 하단 today, done  버튼기능 추가 표시 (기본은 false)
      onSelect : function(dateText, inst){
        var max = dateCal(dateText);
        $("#closeDate").attr("disabled", false);
        $("#closeDate").datepicker("option", "minDate", dateText);
        $("#closeDate").datepicker("option", "maxDate", max);
        removeErrorMsg();
      }
    });

    $("#closeDate").datepicker({
      dateFormat:'yy-mm-dd',
      showOn: 'focus',
      changeMonth: true,
      changeYear: true,
      showMonthAfterYear: true ,
      currentText: '오늘',
      minDate:$("#currentDm").val(),
      dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'], // 요일의 한글 형식.
      monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] ,
       showButtonPanel: true, // 하단 today, done  버튼기능 추가 표시 (기본은 false)
       onSelect : function(dateText, inst){
         //$("#openDate").datepicker("option", "minDate", dateText);
         removeErrorMsg();
      }
    });

    $("#date").datepicker({
        dateFormat:'yy-mm-dd',
        showOn: 'focus',
        changeYear: true,
        changeMonth: true,
        showMonthAfterYear: true ,
        minDate:$("#openDate").val(),
        currentText: '오늘' ,
        dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'], // 요일의 한글 형식.
        monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] ,
        showButtonPanel: true, // 하단 today, done  버튼기능 추가 표시 (기본은 false)
        onSelect : function(dateText, inst){
          $("#date").attr("disabled", false);
            $("#openDate").datepicker("option", "maxDate", dateText);
//            removeErrorMsg();
         }
      });

    $("#searchOpenDate").datepicker({
        dateFormat:'yy-mm-dd',
        showOn: 'focus',
        changeYear: true,
        changeMonth: true,
        showMonthAfterYear: true ,
        currentText: '오늘' ,
        dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'], // 요일의 한글 형식.
        monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] ,
        showButtonPanel: true, // 하단 today, done  버튼기능 추가 표시 (기본은 false)

      });

    $("#searchCloseDate").datepicker({
        dateFormat:'yy-mm-dd',
        showOn: 'focus',
        changeYear: true,
        changeMonth: true,
        showMonthAfterYear: true ,
        currentText: '오늘' ,
        dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'], // 요일의 한글 형식.
        monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] ,
        showButtonPanel: true, // 하단 today, done  버튼기능 추가 표시 (기본은 false)

      });
    $.datepicker.setDefaults($.datepicker.regional['ko']);
});
function dateCal(dateText){
  var tempDateText = dateText;
    var tempMaxAppTerm = $("#maxAppTerm").val();
    tempMaxAppTerm *= 1;
    var tempAddProofMonth = $("#addProofMonth").val();
    tempAddProofMonth *= 1;
    var tempAddProofDay= $("#addProofDay").val();
    tempAddProofDay *= 1;
    var resultDate = "";
    if($("#chkDay").val() == "0"){
        var tempDate  =  new Date(tempDateText.substring(0,4),tempDateText.substring(5,7)-1,tempDateText.substring(8,10));
        tempDate.setMonth(tempDate.getMonth() + tempMaxAppTerm);
        var tempMonth = ((tempDate.getMonth() + 1)+"").zf(2);
        var tempDay = (tempDate.getDate()+"").zf(2);
        resultDate = tempDate.getFullYear() + "-" + tempMonth + "-" + tempDay;
    } else {
        var tempDate  =  new Date(tempDateText.substring(0,4),tempDateText.substring(5,7)-1,tempDateText.substring(8,10));
        tempDate.setMonth(tempDate.getMonth() + tempAddProofMonth);
        tempDate.setDate(tempDate.getDate() + tempAddProofDay);
        var tempMonth = ((tempDate.getMonth() + 1)+"").zf(2);
        var tempDay = (tempDate.getDate()+"").zf(2);
        resultDate = tempDate.getFullYear() + "-" + tempMonth + "-" + tempDay;
    }
    return resultDate;
}
