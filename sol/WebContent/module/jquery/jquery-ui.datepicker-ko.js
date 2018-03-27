/* Korean initialisation for the jQuery calendar extension. */
/* Written by DaeKwon Kang (ncrash.dk@gmail.com). */
jQuery(function($){
	$.datepicker.regional['ko'] = {
		closeText: '닫기',
		prevText: '이전달',
		nextText: '다음달',
		currentText: '오늘',
		monthNames: ['년 1월','년 2월','년 3월','년 4월','년 5월','년 6월',
		'년 7월','년 8월','년 9월','년 10월','년 11월','년 12월'],
		monthNamesShort: ['1월','2월','3월','4월','5월','6월',
		'7월','8월','9월','10월','11월','12월'],
		
		dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'],
		dayNamesShort: ['일','월','화','수','목','금','토'],
		dayNamesMin: ['일','월','화','수','목','금','토'],
		/*weekHeader: 'Wk',*/
		dateFormat: 'yy-mm-dd',
		firstDay: 0,
		isRTL: false,
		showMonthAfterYear: true,
		/*yearSuffix: '년',*/
		numberOfMonths: 1,
		buttonImage: 'chrome/common/ics.png',
		buttonImageOnly: true,
		showOn: 'both',
		showOtherMonths: true,
		/*selectOtherMonths: true,*/
		/*showWeek: false,*/
		changeYear: false,
		changeMonth: false,
		showButtonPanel: false
		,beforeShowDay: function(day) {
            var result;
            // 포맷에 대해선 다음 참조(http://docs.jquery.com/UI/Datepicker/formatDate)
            var holiday = holidays[$.datepicker.formatDate("mmdd",day )];
            
            // exist holiday?
            if (holiday) {
              result =  [true, "date-holiday"+holiday.type, holiday.title];
            } else {
              switch (day.getDay()) {
                case 0: // is sunday?
                  result = [true, "date-sunday"];
                  break;
                case 6: // is saturday?
                  result = [true, "date-saturday"];                  
                  break;
                default:
                  result = [true, ""];
                  break;
              }
            }
            
            return result;
          }
	};
	
	$.datepicker.setDefaults($.datepicker.regional['ko']);
});

var holidays = {

        /*"0101":{type:0, title:"신정"},

        "0301":{type:0, title:"삼일절"},

        "0505":{type:0, title:"어린이날"},

        "0606":{type:0, title:"현충일"},

        "0815":{type:0, title:"광복절"},

        "1003":{type:0, title:"개천절"},

        "1225":{type:0, title:"크리스마스"}*/

};
