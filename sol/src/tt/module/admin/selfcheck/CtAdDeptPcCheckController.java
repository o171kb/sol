package tt.module.admin.selfcheck;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import tt.TTLog;
import tt.base.TtSession;
import tt.base.module.TtBaseController;
import tt.base.module.TtBaseService;
import tt.bean.VoPcBasic;
import tt.bean.VoPcCheck;
import tt.bean.VoSelfDiagnostic;
import tt.com.CoTtObjParams;
import tt.com.bean.VoCoCodeDtl;
import tt.com.bean.VoCoDept;
import tt.com.bean.VoCoUser;
import tt.com.constant.CsCoConstDef;
import tt.utils.UtPagerUtils;

/**
 * <pre>
 * tt.module.admin.selfcheck
 *    |_ CtAdDeptPcCheckController.java
 *
 * DESC : 부서PC점검현황 Controller 클래스 <br />
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author ks-lee
 * @Date 2013. 3. 19. 오후 1:54:40
 * @history :
 *  -----------------------------------------------------------------------
 *  변경일             작성자                     변경내용
 *  ----------- ------------------- ---------------------------------------
 *  2013. 3. 19.        ks-lee              최초 작성
 *  -----------------------------------------------------------------------
 *
 */
@Controller
public class CtAdDeptPcCheckController extends TtBaseController {

    /**
     * 부서PC점검현황 <br />
     * @param model 모델
     * @param session 세션
     * @param voCoDept 모델파라메터
     * @param voPcBasic 모델파라메터
     * @return 페이지경로
     * @param request 리퀘스트
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adDeptPcCheckList.do")
    public String adDeptPcCheckList(@ModelAttribute("voPcBasic") VoPcBasic voPcBasic, VoCoDept voCoDept, ModelMap model, HttpSession session, HttpServletRequest request) throws Exception {

        TTLog.info(":::   adDeptPcCheckList Start   :::", this.getClass());

        //check login
        if (!isLogin(request)) {
            return "admin.login.tiles";
        }
        //###############

        CoTtObjParams ttObjParams = new CoTtObjParams();


        TtSession tts = getTtSession();
        String userId = (String) tts.get(CsCoConstDef.SS_KEY_003);
        voCoDept.setUserId(userId);
        ttObjParams.put("voCoDept", voCoDept);

        VoCoDept myDept = svDeptPcCheckService.getMyDept(ttObjParams);  //부서명과 부서번호

        ttObjParams.put("myDept", myDept);

        int register = svDeptPcCheckService.getDeptRegister(ttObjParams);    //부서원(대상자 수)

//        int pcChkRegister = svDeptPcCheckService.getPcChkRegister(ttObjParams);    PC 점검자 (점검자 수)

        List<VoCoDept> registerIdList = svDeptPcCheckService.getRegisterIdList(ttObjParams);   //해당부서원 아이디

        int selfChkResult = 0;
        int getMyPcCnt = 0;
        int myPcCheckInt = 0;
        int selfChkPcResult = 0;
        int safetyIdx = 0;
        int notSafetyIdx = 0;
        int allSafetyIdx = 0;
        String pcUser = "";
        VoCoDept tempVo = new VoCoDept();
        VoPcBasic tempPcVo = new VoPcBasic();
        List<VoPcBasic> pcInfoList = new ArrayList<VoPcBasic>();
        List<VoPcCheck> selfIdxList = new ArrayList<VoPcCheck>();
        VoPcCheck selfIdxVo = new VoPcCheck();
        String macAddList = "";
        if (registerIdList != null) {   // 점검자
            for (int i = 0; i < registerIdList.size(); i++) {
                tempVo = registerIdList.get(i);
                tempPcVo.setEmpNo(tempVo.getUserId());
                ttObjParams.put("voPcBasic", tempPcVo);
                getMyPcCnt = svDeptPcCheckService.getIncops5MyPcCnt(ttObjParams);   //각 부서원들의 pc보유수
                pcInfoList = svDeptPcCheckService.getIncops5MyPcInfoList(ttObjParams);   //각 부서원들의 pc 정보

                VoPcBasic infoVo = new VoPcBasic();

                if (pcInfoList != null) {
                    myPcCheckInt = 0;
                    for (int j = 0; j < pcInfoList.size(); j++) {
                        infoVo = pcInfoList.get(j);
                        infoVo.setSearchDate(voPcBasic.getSearchDate());
                        macAddList = infoVo.getMac() + "," + macAddList;

                        //2013.07.04
                        System.out.println("MAC##################################   " + macAddList);

                        System.out.println("$$$$$$$$$$사용할 param macAddress : " + infoVo.getMac());
                        System.out.println("$$$$$$$$$$사용할 param id : " + infoVo.getEmpNo());
                        System.out.println("검색날짜 : " + voPcBasic.getSearchDate());
                        ttObjParams.put("voPcBasic", infoVo);
                        myPcCheckInt = myPcCheckInt + svDeptPcCheckService.getMyPcSelfChkCnt(ttObjParams);    //각 부서원이 자가진단한 pc수
                        System.out.println("pc보유수 : " + getMyPcCnt + "  자가진단한 pc수 : " + myPcCheckInt);


                        //안전항목 & 취약항목
                        selfIdxVo.setMacAddress(infoVo.getMac());
                        selfIdxVo.setUserId(infoVo.getEmpNo());
                        selfIdxVo.setSearchDate(voPcBasic.getSearchDate());
                        System.out.println("검색날짜 : " + selfIdxVo.getSearchDate());
                        ttObjParams.put("voPcCheck", selfIdxVo);

                        System.out.println("$$$$$$$$사용할 param macAddress : " + infoVo.getMac());
                        System.out.println("$$$$$$$$사용할 param id : " + infoVo.getEmpNo());

                        selfIdxList = svDeptPcCheckService.getSelfIdx(ttObjParams); //각 부서원이 자가진단한 SELF_DIAGNOSTIC 결과 테이블의 IDX, AllSafety

                        VoPcCheck safetyVo = new VoPcCheck();
                        if  (selfIdxList != null) {
                            for (int k = 0; k < selfIdxList.size(); k++) {
                                safetyVo = selfIdxList.get(k);

                                safetyVo.setIsSafety("1");
                                ttObjParams.put("voPcCheck", safetyVo);
                                System.out.println("안전 :1  :: " + safetyVo.getIsSafety());
                                safetyIdx = safetyIdx + svDeptPcCheckService.getSafetyIdx(ttObjParams); //각 부서원이 점검한 내역 (true 일때 카운트)
                                System.out.println("안전 한 항목 수 : " + safetyIdx);

                                safetyVo.setIsSafety("0");
                                ttObjParams.put("voPcCheck", safetyVo);
                                System.out.println("비안전 : 0  :: " + safetyVo.getIsSafety());
                                notSafetyIdx = notSafetyIdx + svDeptPcCheckService.getSafetyIdx(ttObjParams); //각 부서원이 점검한 내역 (false 일때 카운트)
                                System.out.println("비안전 한 항목 수 : " + notSafetyIdx);

                                System.out.println("safety : " + safetyVo.getAllSafety());
                                if (safetyVo.getAllSafety().equals("1")) {
                                    allSafetyIdx++;
                                    System.out.println("모두안전한 PC수 : " + allSafetyIdx);
                                }
                            }
                        }

                    }

                    if (myPcCheckInt == getMyPcCnt && myPcCheckInt != 0 && getMyPcCnt != 0) {
                        selfChkResult++;   // 각 부서원 자신이 가진 PC 대수만큼 자가진단한 사람만 점검자 카운트에 포함한다.
                        pcUser = tempVo.getUserId() + "," + pcUser;
                    }
                    selfChkPcResult = selfChkPcResult + myPcCheckInt;
                    System.out.println("selfChkPcResult :  " + selfChkPcResult);

                }
            }
        }
        System.out.println("점검 PC 대수 : " + selfChkPcResult);
        System.out.println("점검자 :   " + selfChkResult);
/*
        if (registerIdList != null) {   //각 부서원들의 개인 보유피씨중, 점검한 피씨 대수 확인
            for (int i = 0; i < registerIdList.size(); i++) {
                tempVo = registerIdList.get(i);
                ttObjParams.put("voCoDept", tempVo);
                registerEachPcCnt = registerEachPcCnt +  svDeptPcCheckService.getRegisterEachPcCnt(ttObjParams);
                System.out.println("$$$$$$$$$$$$$$$$$$tott     " + registerEachPcCnt);
            }
        }*/


        model.addAttribute("registerIdList", registerIdList);
        model.addAttribute("voCoDept", myDept);
        model.addAttribute("register", register);   //대상자
        model.addAttribute("selfChkResult", selfChkResult); //점검자
        model.addAttribute("selfChkPcResult", selfChkPcResult); //점검PC
        model.addAttribute("safetyIdx", safetyIdx); //안전한 항목수
        model.addAttribute("notSafetyIdx", notSafetyIdx); //취약한 항목수
        model.addAttribute("allSafetyIdx", allSafetyIdx); //모두안전한 PC수
        model.addAttribute("macAddList", macAddList);    //mac Address
        model.addAttribute("pcUser", pcUser);    //PC점검자

        //menuId 갖고다니기
        String menuId = "14";
        ttObjParams.put("id", menuId);
        model.addAttribute("ttObjParams", ttObjParams);

        TTLog.info(":::   adDeptPcCheckList End   :::", this.getClass());

        return "admin.dept.pc.check.list.tiles";
    }



    /*
     * ###############################################
     * ###############################################
     * ################## EXCEL  #####################
     * ################## 다운로드 #####################
     * ###############################################
     * ###############################################
     * */

    /**
     * 대상자 리스트 다운로드<br />
     *
     * @param voSelfDiagnostic 모델파라메터
     * @param voCoCodeDtl 모델파라메터
     * @param model 모델
     * @param request 리퀘스트
     * @return to JSP
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adDeptPcExcelRegiPopup.do")
    public String adDeptPcExcelRegiPopup(@ModelAttribute("voSelfDiagnostic")VoSelfDiagnostic voSelfDiagnostic, VoCoCodeDtl voCoCodeDtl, ModelMap model, HttpServletRequest request) throws Exception {

        voSelfDiagnostic.setIsExcel("True");

        List<VoSelfDiagnostic> chkDeptUserList = svDeptPcCheckService.chkDeptUser(voSelfDiagnostic);
        String totCnt = svDeptPcCheckService.paginateChkDeptUser(voSelfDiagnostic);

        model.addAttribute("totCnt", totCnt);
        model.addAttribute("chkDeptUserList", chkDeptUserList);
        return "admin.dept.pc.check.regi.popup.excel";
    }

    /**
     * 점검자 리스트 다운로드<br />
     *
     * @param voSelfDiagnostic 모델파라메터
     * @param voSelfDiagnostic 모델파라메터
     * @param model 모델
     * @param request 리퀘스트
     * @return to JSP
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adDeptPcExcelPcChkUserPopup.do")
    public String adDeptPcExcelPcChkUserPopup(@ModelAttribute("voSelfDiagnostic")VoSelfDiagnostic voSelfDiagnostic, ModelMap model, HttpServletRequest request) throws Exception {

        voSelfDiagnostic.setIsExcel("True");

        List<VoSelfDiagnostic> chkDeptPcUserList = svDeptPcCheckService.chkDeptPcUser(voSelfDiagnostic);
        String totCnt = svDeptPcCheckService.paginateChkDeptUser(voSelfDiagnostic);

        model.addAttribute("totCnt", totCnt);
        model.addAttribute("chkDeptPcUserList", chkDeptPcUserList);

        return "admin.dept.pc.check.user.popup.excel";
    }

    /**
     * 점검PC 리스트 다운로드<br />
     *
     * @param voPcBasic 모델파라메터
     * @param model 모델
     * @param request 리퀘스트
     * @return to JSP
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adDeptPcExcelChkPcPopup.do")
    public String adDeptPcExcelChkPcPopup(@ModelAttribute("voPcBasic") VoPcBasic voPcBasic, VoCoDept voCoDept, ModelMap model, HttpSession session, HttpServletRequest request) throws Exception {


        return "admin.dept.pc.check.popup.excel";
    }

    /**
     * 취약항목 리스트 다운로드<br />
     *
     * @param voPcBasic 모델파라메터
     * @param model 모델
     * @param request 리퀘스트
     * @return to JSP
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adDeptPcExcelNoSafePopup.do")
    public String adDeptPcExcelNoSafePopup(@ModelAttribute("voPcBasic") VoPcBasic voPcBasic, VoCoDept voCoDept, ModelMap model, HttpSession session, HttpServletRequest request) throws Exception {


        return "admin.dept.pc.check.noSafety.popup.excel";
    }

}
