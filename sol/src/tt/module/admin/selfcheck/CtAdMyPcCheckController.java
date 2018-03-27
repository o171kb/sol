package tt.module.admin.selfcheck;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.text.AbstractDocument.Content;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContext;

import tt.TTLog;
import tt.base.TtHttpSessionManager;
import tt.base.TtSession;
import tt.base.module.TtBaseController;
import tt.bean.VoPcCheck;
import tt.bean.VoSelfDiaGroup;
import tt.bean.VoSelfDiaItem;
import tt.bean.VoSelfDiagnostic;
import tt.com.CoTtObjParams;
import tt.com.constant.CsCoConstDef;
import tt.com.utils.UtCoStringUtils;

/**
 * <pre>
 * tt.module.admin.selfcheck
 *    |_ CtAdMyPcCheckController.java
 *
 * DESC : 나의PC점검현황 Controller 클래스 <br />
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author ks-lee
 * @Date 2013. 3. 19. 오후 1:52:10
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 3. 19.		ks-lee				최초 작성
 * 2013. 5.  2.        hj-kim                 메서드 추가
 *	-----------------------------------------------------------------------
 *
 */
@Controller
public class CtAdMyPcCheckController extends TtBaseController {

    /**
     * 나의PC점검현황 <br />
     * @param model 모델
     * @param session 세션
     * @return 페이지경로
     * @param voPcCheck 모델파라메터
     * @param voSelfDiaGroup 모델파라메터
     * @param voSelfDiaItem 모델파라메터
     * @param request 리퀘스트
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adMyPcCheckList.do")
    public String adMyPcCheckList(VoSelfDiaGroup voSelfDiaGroup, VoSelfDiaItem voSelfDiaItem, VoPcCheck voPcCheck, ModelMap model, HttpSession session, HttpServletRequest request) throws Exception {
        TTLog.info(":::   adMyPcCheckList Start   :::", this.getClass());
        //check login
        if (!isLogin(request)) {
            return "admin.login.tiles";
        }

        CoTtObjParams ttObjParams = new CoTtObjParams();
        TtSession tts = getTtSession();
        String userId = (String) tts.get(CsCoConstDef.SS_KEY_003);

        voPcCheck.setUserId(userId);
        ttObjParams.put("voPcCheck", voPcCheck);

        List<VoSelfDiaItem> itemList = svMyPcCheckService.getDiaItem(ttObjParams);
        int totCnt = svMyPcCheckService.getDiaItemTotCnt(ttObjParams);

        model.addAttribute("itemList", itemList);
        model.addAttribute("totCnt", totCnt);

        /* 결과 select */
        List<VoPcCheck> pkList = svMyPcCheckService.getResultPKList(ttObjParams);
        List<VoPcCheck> list = new ArrayList<VoPcCheck>();
        List<VoPcCheck> resultList = new ArrayList<VoPcCheck>();
        VoPcCheck resultVo = new VoPcCheck();
        if (pkList != null) {
           for (int k = 0; k < pkList.size(); k++) {
               resultVo = pkList.get(k);
               resultVo.setItemId("");
               ttObjParams.put("voPcCheck", resultVo);
               list = svMyPcCheckService.getResultList(ttObjParams);  //결과값 select
               resultList.addAll(list);
           }
        }
        model.addAttribute("resultList", resultList);

        int totResultCnt = svMyPcCheckService.getResultListTotCnt(ttObjParams);

        VoPcCheck pkVo = new VoPcCheck();
        VoPcCheck isSafety = new VoPcCheck();
        List<VoPcCheck> safetyList = new ArrayList<VoPcCheck>();
        int[] safetyArr = new int[pkList.size()];
        int[] notSafetyArr = new int[pkList.size()];
//        List<VoPcCheck> safetyArr = new ArrayList<VoPcCheck>();
//        List<VoPcCheck> notSafetyArr = new ArrayList<VoPcCheck>();
        //안전 / 취약 cnt
        if (pkList != null) {
            for (int i = 0; i < pkList.size(); i++) {
                int safetyIdx = 0;
                int notSafetyIdx = 0;

                pkVo = pkList.get(i);
                ttObjParams.put("voPcCheck", pkVo);
                safetyList = svMyPcCheckService.getSafetyList(ttObjParams);

                if (safetyList != null) {
                    for (int j = 0; j < safetyList.size(); j++) {
                        isSafety = safetyList.get(j);
                        String safety = isSafety.getIsSafety();

                        if (safety.equals("1")) {
                            safetyIdx++;
                        } else if (safety.equals("0")) {
                            notSafetyIdx++;
                        }
                    }
                    safetyArr[i] = safetyIdx;
                    notSafetyArr[i] = notSafetyIdx;
                }
            }
        }

        model.addAttribute("safetyArr", safetyArr);
        model.addAttribute("notSafetyArr", notSafetyArr);
        model.addAttribute("pkList", pkList);

        model.addAttribute("totResultCnt", totResultCnt);

        /*//결과 select */

        //menuId 갖고다니기
        String menuId = "13";
        ttObjParams.put("id", menuId);
        model.addAttribute("ttObjParams", ttObjParams);

        TTLog.info(":::   adMyPcCheckList End   :::", this.getClass());

        return "admin.my.pc.check.list.tiles";
    }

    /**
     * 나의PC점검 기본사항 Insert   <br/>
     * @param voPcCheck 모델파라메터
     * @param model 모델
     * @param session 세션
     * @param request 리퀘스트
     * @return Tojsp
     * @throws Exception 예외
     * @param rctx 시퀀스
     */

    @RequestMapping(value = "/adMyPcCheckInsertAction.do")
    @ResponseBody
    public String adMyPcCheckInsertAction(VoPcCheck voPcCheck, ModelMap model, HttpSession session, HttpServletRequest request, RequestContext rctx) throws Exception {

       TTLog.info(":::   adMyPcCheckInsertAction Start   :::", this.getClass());

       CoTtObjParams ttObjParams = new CoTtObjParams();
       String resultStr = voPcCheck.getMacAddress()
               .replaceAll("-", "")
               .replaceAll(":", "");
       voPcCheck.setMacAddress(resultStr);
       /*자가진단 Max*/
       System.out.println("::::::::::::::::::::"+resultStr);

       ttObjParams.put("voPcCheck", voPcCheck);

       int param  = svMyPcCheckService.insertMyPcCheck(ttObjParams);
       String idx = Integer.toString(param);

       TTLog.info(":::   adMyPcCheckInsertAction End   :::", this.getClass());

       return idx;
    }

    /**
     * 나의PC점검 Result Insert <br/>
     * @param voPcCheck 모델파라메터
     * @param request 리퀘스트
     * @return Tojsp
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adMyPcCheckResultInsertAction.do")
    @ResponseBody
    public String adMyPcCheckResultInsertAction(VoPcCheck voPcCheck, HttpServletRequest request) throws Exception {
        TTLog.info(":::   adMyPcCheckResultInsertAction Start   :::", this.getClass());

        //TODO 프로시저 호출하시오 - InsSelfDiagnostic(SELF_DIAGNOSTIC Table Insert)

        CoTtObjParams params = new CoTtObjParams();
        //VoPcCheck tempVo = new VoPcCheck();

        List<VoPcCheck> itemList = svMyPcCheckService.getItemChkValue(params);

        CoTtObjParams ttObjParams = new CoTtObjParams();
        String[] allChk = new String[itemList.size()];

        for (int i = 0; i < itemList.size(); i++) {

            VoPcCheck tempVo = itemList.get(i);   //  tempVo.getId()

            //get Method 동적 생성 호출
            String methodName = "get" + tempVo.getChkValue();
            Method dyMethod = null;
            Object resultObj = voPcCheck;
            dyMethod = resultObj.getClass().getMethod(methodName);
            String chkValue = (String) dyMethod.invoke(resultObj);
            allChk[i] = chkValue;

            //DB에 저장되는 값 true/false 변경(안전함 : true, 취약함 : false)
            if (chkValue.equals("OK")) {
                chkValue = "true";
            } else {
                chkValue = "false";
            }

            voPcCheck.setItemId(tempVo.getPkId());
            voPcCheck.setIsSafety(chkValue);

            ttObjParams.put("voPcCheck", voPcCheck);                      //insert를 위한 값 set
            svMyPcCheckService.insertPcCheckResult(ttObjParams);    //insert
        }

        /* 모든항목이 안전한 PC 인지 확인후에 칼럼에 값 지정 */
        for (int i = 0; i < allChk.length; i++) {
            if (!allChk[i].equals("OK")) {
               voPcCheck.setAllSafety("False");
               break;
            } else {
               voPcCheck.setAllSafety("True");
            }
        }
        ttObjParams.put("voPcCheck", voPcCheck);
        svMyPcCheckService.updateAllSafety(ttObjParams);

        TTLog.info(":::   adMyPcCheckInsertAction End   :::", this.getClass());

        return "true";
    }
}
