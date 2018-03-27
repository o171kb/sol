onloads.push(function() {
    init();
});

function init() {
    util.execFunc(this, function() {

        //alert('rrr');
        //wtvh = new WebTreeViewHandler('WebTreeViewOrganization');
        //        $("#btnStart").click(function() {
        //            
        //        });


        // 이미지 버튼 롤오버 처리
        $(".imgBtn").mouseover(function() {
                var s = this.src;
                var foo = new Array();
                this.src = '/SystemResource/Image/IRCenter/ProcessGuideBtn_Over.png'
            }
        );

        $(".imgBtn").mouseout(function() {
                var s = this.src;
                var foo = new Array();
                this.src = '/SystemResource/Image/IRCenter/ProcessGuideBtn_Normal.png'
            }
        );

        $(".imgBtn").mousedown(function() {
                var s = this.src;
                var foo = new Array();
                this.src = '/SystemResource/Image/IRCenter/ProcessGuideBtn_Active.png'
                openNoticeWindow(this.value);
            }
        );



    });}

// 팝업 오픈
function openNoticeWindow(arg) {
    var width = 700;
    var height = 600;
    var winToTop = (screen.height) ? (screen.height - height) / 2 : 0;
    var winToLeft = (screen.width) ? (screen.width - width) / 2 : 0;
    var winstyle = 'width=' + width + ', height=' + height + ', top=' + winToTop + ', left=' + winToLeft + ', status=no, toolbar=no, menubar=0, location=no, scrollbars=1';    

    window.open('/UX/IRCENTER/UI/InstructionsManualClient/View.aspx?pkey=' + arg, 'SecurityExceptionRequestClientNotice', winstyle);
}


function StartCheck() {    
    if (checkIsDiagnosticTarget()) {
        checkCtrl = $('#checkCtrl').get(0);

        if (checkCtrl.object != null) {
            clearIt();
            checkCtrl.StartCheck();
            setResult();
            //saveResult();
        }
        else {
            alert('PC 점검툴 Active X가 새로 배포되었습니다.\n\nPC 점검툴 Active X를 (새로) 설치하신 후 실행하여 주세요.');
            window.location.reload();
        }
    } else {
        alert('자가진단 대상자가 아닙니다.');
        return false;
    }
}

function checkIsDiagnosticTarget() {
    var isDiagnosticTarget = true;
    
    var ajax = $.ajax({
        type: 'post',
        url: '/UX/IRCENTER/Handler/Ajax/SelfDiagnostic.aspx?methodName=CheckIsDiagnosticTarget',
        async: false,
        success: function(msg) {
            if (msg == 'false') {
                isDiagnosticTarget = false;
            }
        }
    });
    
    return isDiagnosticTarget;
}




function clearIt() {
    //for (i = 1; i < 24; i++) {
    //    $('#result_'+i).toggleClass("init");
    // }
    //$(".init").toggleClass("play");
}

function getDateNow() {
    var dateNow = new Date();
    var dateNowForm = dateNow.getYear() + '-' + util.lpad((parseInt(dateNow.getMonth()) + 1), 2, '0') + '-' + util.lpad(dateNow.getDate(), 2, '0');
    return dateNowForm;
}

function setResult() {
    
    var checkCtrl;
    checkCtrl = $('#checkCtrl').get(0);
    
    var selfDiagnosticPkey = 0;

    var pkey = checkCtrl.PRIMARY_KEY;    
    
    if (pkey.substring(9) == '00000000000') {
        //pkey = (getDateNow() + checkCtrl.MAC_ADDRESS).replace(/-/gi, '');
        pkey = (checkCtrl.MAC_ADDRESS).replace(/-/gi, '');
    }
    
    
    //바로 저장    
    if (checkCtrl.PRIMARY_KEY) {
        var ajax = $.ajax({
            type: 'post',
            url: '/UX/IRCENTER/Handler/Ajax/SelfDiagnostic.aspx?methodName=Insert',
            async: false,
            data: 'serialNumber=' + pkey + '&userId=' + user.id + '&macAddress=' + checkCtrl.MAC_ADDRESS + '&osType=' + checkCtrl.OS_TYPE,
            success: function(msg) {
                if (msg == 'false') {
                    alert('자가진단 대상자가 아닙니다.');
                    return false;
                } else {
                    selfDiagnosticPkey = msg;   
                }
            }
        });
    }
    
    // 결과 저장
    if (selfDiagnosticPkey != 0) {
        var ajax = $.ajax({
            type: 'post',
            url: '/UX/IRCENTER/Handler/Ajax/SelfDiagnostic.aspx?methodName=InsertResult',
            async: false,
            data: 'selfDiagnosticPkey=' + selfDiagnosticPkey 
            
                + '&VIRUS_FILE_VERIFY=' + checkCtrl.VIRUS_FILE_VERIFY 
                + '&SPYWARE_VERIFY=' + checkCtrl.SPYWARE_VERIFY
                + '&UPDATE_AUTO_DISC_VERIFY=' + checkCtrl.UPDATE_AUTO_DISC_VERIFY 
                + '&UPDATE_AUTO_VACCINE_VERIFY=' + checkCtrl.UPDATE_AUTO_VACCINE_VERIFY 
                + '&VIRUS_UPFILE_VERIFY=' + checkCtrl.VIRUS_UPFILE_VERIFY 
                
                + '&SERVICE_PACK_VERIFY=' + checkCtrl.SERVICE_PACK_VERIFY
                + '&IE_VERSION_VERIFY=' + checkCtrl.IE_VERSION_VERIFY 
                + '&OS_UPDATE_VERIFY=' + checkCtrl.OS_UPDATE_VERIFY
                + '&OS_PATCH_VERIFY=' + checkCtrl.OS_PATCH_VERIFY
                + '&IE_PATCH_VERIFY=' + checkCtrl.IE_PATCH_VERIFY
                
                + '&GUEST_VERIFY=' + checkCtrl.GUEST_VERIFY
                + '&ADMIN_VERIFY=' + checkCtrl.ADMIN_VERIFY
                + '&SIMPLE_PWD_VERIFY=' + checkCtrl.SIMPLE_PWD_VERIFY
                + '&SIMPLE_PWD_CURRENT=' + checkCtrl.SIMPLE_PWD_CURRENT
                + '&SAVER_PASSWD_VERIFY=' + checkCtrl.SAVER_PASSWD_VERIFY
                
                + '&TENMIN_VERIFY=' + checkCtrl.TENMIN_VERIFY
                + '&REG_KEY_VERIFY=' + checkCtrl.REG_KEY_VERIFY
                + '&RPC_SHARE_VERIFY=' + checkCtrl.RPC_SHARE_VERIFY
                + '&SHARE_FOLDER_VERIFY=' + checkCtrl.SHARE_FOLDER_VERIFY
                + '&EVERYONE_VERIFY=' + checkCtrl.EVERYONE_VERIFY
                
                + '&NO_SERVICE_VERIFY=' + checkCtrl.NO_SERVICE_VERIFY
                + '&RW_EXCUTE_VERIFY=' + checkCtrl.RW_EXCUTE_VERIFY
                + '&AUTOBAT_READ_VERIFY=' + checkCtrl.AUTOBAT_READ_VERIFY
                + '&NTFS_VERIFY=' + checkCtrl.NTFS_VERIFY
                + '&UPDATE_AUTO_DISC_CURRENT=' + checkCtrl.UPDATE_AUTO_DISC_CURRENT
                
                + '&IE_VERSION_CURRENT=' + checkCtrl.IE_VERSION_CURRENT
                + '&OS_PATCH_CURRENT=' + checkCtrl.OS_PATCH_CURRENT
                + '&IE_PATCH_CURRENT=' + checkCtrl.IE_PATCH_CURRENT
                + '&TENMIN_CURRENT=' + checkCtrl.TENMIN_CURRENT
                + '&SHARE_FOLDER_CURRENT=' + checkCtrl.SHARE_FOLDER_CURRENT
                
                + '&SOFTWARE_VERIFY=' + checkCtrl.SOFTWARE_VERIFY
                + '&NO_SERVICE_CURRENT=' + checkCtrl.NO_SERVICE_CURRENT,
            success: function(msg) {
                util.refresh();
            }
        });
    }
    
    //이 페이지 리 프레시..
    //점검 기초 정보 세팅
    //$('#primaryKey').html(checkCtrl.PRIMARY_KEY);
    //$('#osType').html(checkCtrl.OS_TYPE);
    
    //$('#userName').html(checkCtrl.OS_TYPE);

//    alert("VIRUS_FILE_VERIFY            :" + checkCtrl.VIRUS_FILE_VERIFY);
//    alert("SPYWARE_VERIFY               :" + checkCtrl.SPYWARE_VERIFY);
//    alert("UPDATE_AUTO_DISC_VERIFY      :" + checkCtrl.UPDATE_AUTO_DISC_VERIFY);
//    alert("UPDATE_AUTO_VACCINE_VERIFY   :" + checkCtrl.UPDATE_AUTO_VACCINE_VERIFY);
//    alert("VIRUS_UPFILE_VERIFY          :" + checkCtrl.VIRUS_UPFILE_VERIFY);
//    
//    alert("SERVICE_PACK_VERIFY          :" + checkCtrl.SERVICE_PACK_VERIFY);
//    alert("IE_VERSION_VERIFY            :" + checkCtrl.IE_VERSION_VERIFY);
//    alert("OS_UPDATE_VERIFY             :" + checkCtrl.OS_UPDATE_VERIFY);
//    alert("OS_PATCH_VERIFY              :" + checkCtrl.OS_PATCH_VERIFY);
//    alert("IE_PATCH_VERIFY              :" + checkCtrl.IE_PATCH_VERIFY);
//    
//    alert("GUEST_VERIFY                 :" + checkCtrl.GUEST_VERIFY);
//    alert("ADMIN_VERIFY                 :" + checkCtrl.ADMIN_VERIFY);
//    alert("SIMPLE_PWD_VERIFY            :" + checkCtrl.SIMPLE_PWD_VERIFY);
//    alert("SIMPLE_PWD_CURRENT           :" + checkCtrl.SIMPLE_PWD_CURRENT);
//    alert("SAVER_PASSWD_VERIFY          :" + checkCtrl.SAVER_PASSWD_VERIFY);
//    
//    alert("TENMIN_VERIFY                :" + checkCtrl.TENMIN_VERIFY);
//    alert("REG_KEY_VERIFY               :" + checkCtrl.REG_KEY_VERIFY);
//    alert("RPC_SHARE_VERIFY             :" + checkCtrl.RPC_SHARE_VERIFY);
//    alert("SHARE_FOLDER_VERIFY          :" + checkCtrl.SHARE_FOLDER_VERIFY);
//    alert("EVERYONE_VERIFY              :" + checkCtrl.EVERYONE_VERIFY);
//    
//    alert("NO_SERVICE_VERIFY            :" + checkCtrl.NO_SERVICE_VERIFY);    
//    alert("RW_EXCUTE_VERIFY             :" + checkCtrl.RW_EXCUTE_VERIFY);
//    alert("AUTOBAT_READ_VERIFY          :" + checkCtrl.AUTOBAT_READ_VERIFY);
//    alert("NTFS_VERIFY                  :" + checkCtrl.NTFS_VERIFY);
//    alert("UPDATE_AUTO_DISC_CURRENT     :" + checkCtrl.UPDATE_AUTO_DISC_CURRENT);
//    
//    alert("IE_VERSION_CURRENT           :" + checkCtrl.IE_VERSION_CURRENT);
//    alert("OS_PATCH_CURRENT             :" + checkCtrl.OS_PATCH_CURRENT);
//    alert("IE_PATCH_CURRENT             :" + checkCtrl.IE_PATCH_CURRENT);
//    alert("TENMIN_CURRENT               :" + checkCtrl.TENMIN_CURRENT);
//    alert("SHARE_FOLDER_CURRENT         :" + checkCtrl.SHARE_FOLDER_CURRENT);
//    
//    alert("NO_SERVICE_CURRENT           :" + checkCtrl.NO_SERVICE_CURRENT);
    
    
    
    
//    alert("OS_TYPE                      :" + checkCtrl.OS_TYPE);
//    alert("MAC_ADDRESS                  :" + checkCtrl.MAC_ADDRESS);

//    alert("PRODUCTION_NO                :" + checkCtrl.PRODUCTION_NO);-
//    alert("PRIMARY_KEY                  :" + checkCtrl.PRIMARY_KEY);--
    
    
    //alert(checkCtrl.OS_TYPE);
    //alert(checkCtrl.SOFTWARE_VERIFY);

    //debug.printMode = 'console'
    ////debug.handlingErr(checkCtrl);
   // debug.handlingErr(checkCtrl, 'console');
//    checkForm.macAddress.value = checkCtrl.MAC_ADDRESS;

//    checkForm.productionNo.value = checkCtrl.PRODUCTION_NO;
//    checkForm.serialNo.value = checkCtrl.PRIMARY_KEY;

//    alert(checkCtrl.SOFTWARE_verify);    
//    if (checkCtrl.SOFTWARE_VERIFY == "FAIL") {
//        $('#SOFTWARE_VERIFY').removeClass('init');
//        $('#SOFTWARE_VERIFY').addClass('weak');
//    } else {
//        $('#SOFTWARE_VERIFY').removeClass('init');
//        $('#SOFTWARE_VERIFY').addClass('safe');
//    }

//    if (checkCtrl.VIRUS_FILE_VERIFY == "FAIL") {
//        $('#VIRUS_FILE_VERIFY').removeClass('init');
//        $('#VIRUS_FILE_VERIFY').addClass('weak');
//    } else {
//        $('#VIRUS_FILE_VERIFY').removeClass('init');
//        $('#VIRUS_FILE_VERIFY').addClass('safe');
//    }

//    if (checkCtrl.SPYWARE_VERIFY == "FAIL") {
//        $('#SPYWARE_VERIFY').removeClass('init');
//        $('#SPYWARE_VERIFY').addClass('weak');
//    } else {
//        $('#SPYWARE_VERIFY').removeClass('init');
//        $('#SPYWARE_VERIFY').addClass('safe');
//    }

//    if (checkCtrl.UPDATE_AUTO_DISC_VERIFY == "FAIL") {
//        $('#UPDATE_AUTO_DISC_VERIFY').removeClass('init');
//        $('#UPDATE_AUTO_DISC_VERIFY').addClass('weak');
//    } else {
//        $('#UPDATE_AUTO_DISC_VERIFY').removeClass('init');
//        $('#UPDATE_AUTO_DISC_VERIFY').addClass('safe');
//    }

//    if (checkCtrl.UPDATE_AUTO_VACCINE_VERIFY == "FAIL") {
//        $('#UPDATE_AUTO_VACCINE_VERIFY').removeClass('init');
//        $('#UPDATE_AUTO_VACCINE_VERIFY').addClass('weak');
//    } else {
//        $('#UPDATE_AUTO_VACCINE_VERIFY').removeClass('init');
//        $('#UPDATE_AUTO_VACCINE_VERIFY').addClass('safe');
//    }

//    if (checkCtrl.VIRUS_UPFILE_VERIFY == "FAIL") {
//        $('#VIRUS_UPFILE_VERIFY').removeClass('init');
//        $('#VIRUS_UPFILE_VERIFY').addClass('weak');
//    } else {
//        $('#VIRUS_UPFILE_VERIFY').removeClass('init');
//        $('#VIRUS_UPFILE_VERIFY').addClass('safe');
//    }

//    if (checkCtrl.SERVICE_PACK_VERIFY == "FAIL") {
//        $('#SERVICE_PACK_VERIFY').removeClass('init');
//        $('#SERVICE_PACK_VERIFY').addClass('weak');
//    } else {
//        $('#SERVICE_PACK_VERIFY').removeClass('init');
//        $('#SERVICE_PACK_VERIFY').addClass('safe');
//    }

//    if (checkCtrl.IE_VERSION_VERIFY == "FAIL") {
//        $('#IE_VERSION_VERIFY').removeClass('init');
//        $('#IE_VERSION_VERIFY').addClass('weak');
//    } else {
//        $('#IE_VERSION_VERIFY').removeClass('init');
//        $('#IE_VERSION_VERIFY').addClass('safe');
//    }

//    if (checkCtrl.OS_UPDATE_VERIFY == "FAIL") {
//        $('#OS_UPDATE_VERIFY').removeClass('init');
//        $('#OS_UPDATE_VERIFY').addClass('weak');
//    } else {
//        $('#OS_UPDATE_VERIFY').removeClass('init');
//        $('#OS_UPDATE_VERIFY').addClass('safe');
//    }

//    if (checkCtrl.OS_PATCH_VERIFY == "FAIL") {
//        $('#OS_PATCH_VERIFY').removeClass('init');
//        $('#OS_PATCH_VERIFY').addClass('weak');
//    } else {
//        $('#OS_PATCH_VERIFY').removeClass('init');
//        $('#OS_PATCH_VERIFY').addClass('safe');
//    }

//    if (checkCtrl.IE_PATCH_VERIFY == "FAIL") {
//        $('#IE_PATCH_VERIFY').removeClass('init');
//        $('#IE_PATCH_VERIFY').addClass('weak');
//    } else {
//        $('#IE_PATCH_VERIFY').removeClass('init');
//        $('#IE_PATCH_VERIFY').addClass('safe');
//    }

//    if (checkCtrl.GUEST_VERIFY == "FAIL") {
//        $('#GUEST_VERIFY').removeClass('init');
//        $('#GUEST_VERIFY').addClass('weak');
//    } else {
//        $('#GUEST_VERIFY').removeClass('init');
//        $('#GUEST_VERIFY').addClass('safe');
//    }

//    if (checkCtrl.ADMIN_VERIFY == "FAIL") {
//        $('#ADMIN_VERIFY').removeClass('init');
//        $('#ADMIN_VERIFY').addClass('weak');
//    } else {
//        $('#ADMIN_VERIFY').removeClass('init');
//        $('#ADMIN_VERIFY').addClass('safe');
//    }

//    if (checkCtrl.SIMPLE_PWD_VERIFY == "FAIL") {
//        $('#SIMPLE_PWD_VERIFY').removeClass('init');
//        $('#SIMPLE_PWD_VERIFY').addClass('weak');
//    } else {
//        $('#SIMPLE_PWD_VERIFY').removeClass('init');
//        $('#SIMPLE_PWD_VERIFY').addClass('safe');
//    }

//    if (checkCtrl.SAVER_PASSWD_VERIFY == "FAIL") {
//        $('#SAVER_PASSWD_VERIFY').removeClass('init');
//        $('#SAVER_PASSWD_VERIFY').addClass('weak');
//    } else {
//        $('#SAVER_PASSWD_VERIFY').removeClass('init');
//        $('#SAVER_PASSWD_VERIFY').addClass('safe');
//    }

//    if (checkCtrl.TENMIN_VERIFY == "FAIL") {
//        $('#TENMIN_VERIFY').removeClass('init');
//        $('#TENMIN_VERIFY').addClass('weak');
//    } else {
//        $('#TENMIN_VERIFY').removeClass('init');
//        $('#TENMIN_VERIFY').addClass('safe');
//    }

//    if (checkCtrl.REG_KEY_VERIFY == "FAIL") {
//        $('#REG_KEY_VERIFY').removeClass('init');
//        $('#REG_KEY_VERIFY').addClass('weak');
//    } else {
//        $('#REG_KEY_VERIFY').removeClass('init');
//        $('#REG_KEY_VERIFY').addClass('safe');
//    }

//    if (checkCtrl.RPC_SHARE_VERIFY == "FAIL") {
//        $('#RPC_SHARE_VERIFY').removeClass('init');
//        $('#RPC_SHARE_VERIFY').addClass('weak');
//    } else {
//        $('#RPC_SHARE_VERIFY').removeClass('init');
//        $('#RPC_SHARE_VERIFY').addClass('safe');
//    }

//    if (checkCtrl.SHARE_FOLDER_VERIFY == "FAIL") {
//        $('#SHARE_FOLDER_VERIFY').removeClass('init');
//        $('#SHARE_FOLDER_VERIFY').addClass('weak');
//    } else {
//        $('#SHARE_FOLDER_VERIFY').removeClass('init');
//        $('#SHARE_FOLDER_VERIFY').addClass('safe');
//    }

//    if (checkCtrl.EVERYONE_VERIFY == "FAIL") {
//        $('#EVERYONE_VERIFY').removeClass('init');
//        $('#EVERYONE_VERIFY').addClass('weak');
//    } else {
//        $('#EVERYONE_VERIFY').removeClass('init');
//        $('#EVERYONE_VERIFY').addClass('safe');
//    }

//    if (checkCtrl.NO_SERVICE_VERIFY == "FAIL") {
//        $('#NO_SERVICE_VERIFY').removeClass('init');
//        $('#NO_SERVICE_VERIFY').addClass('weak');
//    } else {
//        $('#NO_SERVICE_VERIFY').removeClass('init');
//        $('#NO_SERVICE_VERIFY').addClass('safe');
//    }

//    if (checkCtrl.RW_EXCUTE_VERIFY == "FAIL") {
//        $('#RW_EXCUTE_VERIFY').removeClass('init');
//        $('#RW_EXCUTE_VERIFY').addClass('weak');
//    } else {
//        $('#RW_EXCUTE_VERIFY').removeClass('init');
//        $('#RW_EXCUTE_VERIFY').addClass('safe');
//    }

//    if (checkCtrl.NTFS_VERIFY == "FAIL") {
//        $('#NTFS_VERIFY').removeClass('init');
//        $('#NTFS_VERIFY').addClass('weak');
//    } else {
//        $('#NTFS_VERIFY').removeClass('init');
//        $('#NTFS_VERIFY').addClass('safe');
//    }

//    $('#result').css("display", "block");
//    $('#init').css("display", "none");


}        
        
        