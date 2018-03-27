$(document).ready(function(){

    jQuery.validator.addMethod("alphanumeric", function(value,element){
      return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value);
    });
    jQuery.validator.addMethod("phonenemeric", function(value,element){
      return this.optional(element) ||/^\d{2,3}-\d{3,4}-\d{4}$/.test(value);
    });
    jQuery.validator.addMethod("englishkorean", function(value,element){
      return this.optional(element) || /^[a-zA-Z가-힣]+$/.test(value);
    });
    jQuery.validator.addMethod("url", function(value,element){
      return this.optional(element) || /^(https?:\/\/)?([\da-z\.-]+)\.([a-z\.]{2,6})([\/\w_\.-]*)*\/?$/.test(value);
    });

    jQuery.validator.addMethod("englishkorean", function(value,element){
      return this.optional(element) || /^[a-zA-Z가-힣]+$/.test(value);
    });


    /*사용자그룹 등록 페이지*/
    $("#grpForm").validate({
      rules : {
        grpExp:{
          maxlength: 2000
        }
      },
      messages : {
        grpExp:{
          maxlength: "*그룹설명은 2000자 이하로 입력해주세요."
        }
      }

    });

    /*부서관리 페이지*/
    $("#deptForm").validate({
      rules : {
        deptNm: {
          required: true,
          maxlength: 50
        },
        deptNmEn: {
          alphanumeric: true,
          maxlength: 50
        },
        deptExp: {
          maxlength: 2000
        }
      },
      messages : {
        deptNm: {
          required: "*부서명을 입력해주세요.",
          maxlength: "부서명은 50자 이하로 입력해주세요."
        },
        deptNmEn: {
          alphanumeric: "*부서영문명은 영어 숫자 조합으로 입력해주세요.",
          maxlength: "*부서영문명은 50자 이하로 입력해주세요."
        },
        deptExp: {
          maxlength: "*부서설명은 2000자 이하로 입력해주세요."
        }
     }
   });
    /*예외신청 문구 수정 페이지, Escort 예외신청*/
    $("#exctForm").validate({
      rules : {
        objUserNm: {
              required: true
        },
        reason:{
          required: true,
          maxlength: 100
        },
        timeSolt:{
          required: true
        },
        rule:{
          required: true
        },
        date:{
          required: true
        },
        exctBasicTerms: {
          required: true,
          maxlength: 25
        },
        maxAppTerm: {
          required: true,
          maxlength: 3,
          number: true
        },
        appTermEx: {
          required: true,
          maxlength: 50
        },
        addProofMonth:{
          maxlength: 2,
          number: true
        },
        addProofDay: {
          maxlength: 2,
          number: true
        },
        topTerms: {
          required: true,
          maxlength: 2000
      },
      permitTerms: {
        required: true,
        maxlength: 2000
      },
      bottomTerms: {
        required: true,
        maxlength: 2000
      },
      exctNm: {
        required: true,
        maxlength: 50
      },
      exctDtl: {
        required: true,
        maxlength: 100
      }
    },
      messages : {
        objUserNm: {
          required: "*사용자를 선택해주세요."
        },
        reason:{
          required: "*신청사유를 입력해 주세요.",
          maxlength: "*신청사유를 100자이하로 입력해 주세요."
        },
        timeSolt:{
          required: "*시간대를 선택해 주세요."
        },
        rule:{
          required: "*규칙을 선택해 주세요."
        },
        date:{
          required: "*만료일을 선택해 주세요."
        },
        exctBasicTerms: {
          required: "*신청사유기본문구를 입력해주세요.",
          maxlength: "*신청사유기본문구를 25자 이하로 입력해주세요."
        },
        maxAppTerm: {
          required: "*최대신청기간을 입력해주세요.",
          maxlength: "*최대신청기간을 3자 이하로 입력해주세요.",
          number: "*최대신청기간은 숫자만 입력가능합니다."
        },
        appTermEx: {
          required: "*신청기간 안내문구를 입력해주세요",
          maxlength: "*신청기간 안내문구는 50자 이하로 입력해주세요."
        },
        addProofMonth: {
          maxlength: "*개월은 2자리 이하로 입력해주세요.",
          number: "개월은 숫자만 입력가능합니다."
        },
        addProofDay: {
          maxlength: "*일은 2자리 이하로 입력해주세요.",
          number: "일은 숫자만 입력가능합니다."
        },
        topTerms: {
          required: "*상단안내문구를 입력해주세요.",
          maxlength: "*상단안내문구는 2000자 이하로 입력해주세요."
        },
        permitTerms: {
          required: "*결재안내문구를 입력해주세요.",
          maxlength: "*결재안내문구는 2000자 이하로 입력해주세요."
        },
        bottomTerms: {
          required: "*하단안내문구를 입력해주세요.",
          maxlength: "*하단안내문구는 2000자 이하로 입력해주세요."
        },
        exctNm: {
          required: "*예외명을 입력해주세요.",
          maxlength: "*예외명을 50자 이하로 입력해주세요."
        },
        exctDtl: {
          required: "*상세설명을 입력해주세요.",
          maxlength: "*상세설명을 100자 이하로 입력해주세요.",
        }
      }
    });




      /*사용자 등록/수정 페이지*/
       $("#userForm").validate({
      rules : {
        userPosition: {
          required: true,
          maxlength: 20
        },
        userNm:{
          required: true,
          englishkorean: true,
          maxlength: 18,
        },
        userEmail:{
          required: true,
        },
        password1:{
          required: true,
/*          minlength: 8,*/
          maxlength: 20,
          alphanumeric: true
        },
        password2:{
          required: true,
          equalTo: "#password1",
/*          minlength: 8,*/
          maxlength: 20,
          alphanumeric: true
        },
        userTel:{
          phonenemeric : true,
          maxlength: 20
        },
        userMobile:{
          phonenemeric : true,
          maxlength: 20
        }
      },
      messages : {
        userPosition: {
          required: "*직급을 입력해 주세요.",
          maxlength: "*직급을 20자이하로 입력해 주세요."
        },
        userNm:{
          required: "*성명을 입력해 주세요.",
          englishkorean: "*성명 형식이 올바르지 않습니다.",
          maxlength: "*성명은 최대 18자만 입력해주세요."
        },
        userEmail: {
          required: "*e-mail을 입력해 주세요.",
        },
        password1:{
          required: "*비밀번호를 입력해 주세요.",
          /*minlength: "*최소 8자 이상 입력해 주세요.",*/
          maxlength: "*최대 20자 이하로 입력해 주세요.",
          alphanumeric: "*비밀번호 형식이 올바르지 않습니다."
        },
        password2:{
          required: "*비밀번호를 입력해 주세요.",
          equalTo: "*비밀번호가 일치하지 않습니다.",
          /*minlength: "*최소 8자 이상 입력해 주세요.",*/
          maxlength: "*최대 20자 이하로 입력해 주세요.",
          alphanumeric: "*비밀번호 형식이 올바르지 않습니다.."
        },
        userTel:{
          phonenemeric: "*연락처 형식이 올바르지 않습니다.",
          maxlength: "*연락처를 최대 20자만 입력해주세요."
        },
        userMobile:{
          phonenemeric: "*휴대폰 형식이 올바르지 않습니다.",
          maxlength: "*휴대폰 최대 20자만 입력해주세요."
        }
      }
    });


     $.validator.addMethod("valueNotEquals", function(value, element, arg){
        return arg != value;
     }, "Value must not equal arg.");




    /*게시글 등록 페이지*/
     $("#writeForm").validate({
      rules : {
        boardTitle:{
          required: true,
          maxlength: 2000
        },
        contents:{
          required: true,
          maxlength: 2000
        }
      },
      messages : {
        boardTitle :{
          required: "*제목을 입력해 주세요",
          number: "*제목은 2000자 이하로 입력해주세요."
        },
        contents :{
          required: "*내용을 입력해 주세요",
          number: "*내용은 2000자 이하로 입력해주세요."
        }
      }
    });

     /*게시글 등록 페이지*/
     $("#modifyForm").validate({
      rules : {
        boardTitle:{
          required: true,
          maxlength: 2000
        },
        contents:{
          required: true,
          maxlength: 2000
        }
      },
      messages : {
        boardTitle :{
          required: "*제목을 입력해 주세요",
          number: "*제목은 2000자 이하로 입력해주세요."
        },
        contents :{
          required: "*내용을 입력해 주세요",
          number: "*내용은 2000자 이하로 입력해주세요."
        }
      }
    });


    /*이벤트(전시/행사) 수정 페이지*/
    $("#infoForm").validate({
      rules : {
        resultReason:{
          required: true,
          maxlength: 400
        }
      },
      messages : {
        resultReason:{
          required: "*결재의견을 입력해주세요",
          maxlength: "*결재의견을 400자 이하로 입력해주세요."
        }
      }

    });

    /*과학관 등록
    $("#musForm").validate({
      rules : {
        museumName:{
          required: true,
          maxlength: 20
        },
        area:{
          valueNotEquals: "default"
        },
        siteUrl:{
          required: true,
          maxlength: 200
        }
      },
      messages : {
        museumName:{
          required: "*과학관명을 입력해 주세요",
          maxlength: "*과학관명을 최대 20자만 입력해 주세요."
        },
        area :{
          valueNotEquals: "*지역을 선택해 주세요"
        },
        siteUrl:{
          required: "*해당사이트URL을 입력해 주세요",
          maxlength: "*URL을  최대 200자만 입력해 주세요."
        }
      }
    });
*/
});