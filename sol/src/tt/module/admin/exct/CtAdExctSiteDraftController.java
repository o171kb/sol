package tt.module.admin.exct;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import tt.TTLog;
import tt.base.TtSession;
import tt.base.module.TtBaseController;
import tt.bean.VoAddUser;
import tt.bean.VoApprDetail;
import tt.bean.VoApprInfo;
import tt.bean.VoExct;
import tt.bean.VoExctDraft;
import tt.bean.VoExctObjPc;
import tt.bean.VoPcBasic;
import tt.bean.VoWkPolicy;
import tt.com.CoTtObjParams;
import tt.com.bean.VoCoCodeDtl;
import tt.com.bean.VoCoUser;
import tt.com.constant.CsCoConstDef;
import tt.com.utils.UtCoDateUtils;
import tt.com.utils.UtCoStringUtils;

/**
 * <pre>
 * tt.module.admin.exct
 *    |_ CtAdExctAttachDraftController.java
 *
 * DESC : 첨부통제예외신청 <br />
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author ks-lee
 * @Date 2013. 3. 19. 오후 1:00:21
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 3. 19.		ks-lee				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
/**
 * <pre>
 * tt.module.admin.exct
 *    |_ CtAdExctSiteDraftController.java
 *
 * DESC : 사이트예외신청 Controller 클래스 <br />
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author ks-lee
 * @Date 2013. 3. 19. 오후 1:45:21
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 3. 19.		ks-lee				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
@Controller
public class CtAdExctSiteDraftController extends TtBaseController {

    /**
     * 사이트예외신청동의 <br />
     * @param model 모델
     * @param session 세션
     * @param request 요청
     * @return 페이지경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adSiteDraftAgree.do")
    public String adSiteDraftAgree(ModelMap model, HttpSession session, HttpServletRequest request) throws Exception {

        TTLog.info(":::   adSiteDraftAgree Start   :::", this.getClass());

     // check login
        if (!isLogin(request)) {
            return "admin.login.tiles";
        }
        TtSession tts = getTtSession();

        VoCoUser tempUser = new VoCoUser();
        String getUserNm = (String) tts.get(CsCoConstDef.SS_KEY_019); //기안자이름
        String getRegDm = UtCoDateUtils.getCurrentDateAsString(UtCoDateUtils.DATE_PATTERN_DASH);
        String getRegDay = UtCoDateUtils.getDayOfWeek(getRegDm, false, Locale.KOREA);

        tempUser.setUserNm(getUserNm);
        tempUser.setRegDm(getRegDm);
        tempUser.setUpdDm(getRegDay);

        model.addAttribute("userInfo", tempUser);
        CoTtObjParams ttObjParams = new CoTtObjParams();
        //menuId 갖고다니기
        String menuId = "12";
        ttObjParams.put("id", menuId);
        model.addAttribute("ttObjParams", ttObjParams);

        TTLog.info(":::   adSiteDraftAgree End   :::", this.getClass());

        return "admin.exct.site.draft.agree.tiles";
    }

    /**
     * 사이트예외신청 <br />
     * @param voExctDraft 파라메터
     * @param voWkPolicy 파라메터
     * @param voExct 파라메터
     * @param voCoCodeDtl 파라메터
     * @param voCoUser 파라메터
     * @param voAddUser 파라메터
     * @param model 모델
     * @param session 세션
     * @param request 요청
     * @return 페이지경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adSiteDraft.do")
    public String adSiteDraft(VoExctDraft voExctDraft, VoWkPolicy voWkPolicy,
            VoExct voExct, VoCoCodeDtl voCoCodeDtl, VoCoUser voCoUser, VoAddUser
            voAddUser, ModelMap model, HttpSession session, HttpServletRequest request) throws Exception {

        TTLog.info(":::   adSiteDraftAgree Start   :::", this.getClass());

     // check login
        if (!isLogin(request)) {
            return "admin.login.tiles";
        }
        TtSession tts = getTtSession();

        CoTtObjParams ttObjParams = new CoTtObjParams();
        voExctDraft.setExctAppId("SITEDRAFT");
        ttObjParams.put("voExctDraft", voExctDraft);
        VoExctDraft tempVO = svExctDraftService.getExctDraftDtl(ttObjParams);

        String getCurrentDm = UtCoDateUtils.getCurrentDateAsString(UtCoDateUtils.DATE_PATTERN_DASH);

            int maxAppTerm = Integer.parseInt(tempVO.getMaxAppTerm());
            String resultDay = UtCoDateUtils.addMonths(getCurrentDm, maxAppTerm);

            int addProofMonth = Integer.parseInt(tempVO.getAddProofMonth());
            int addProofDay = Integer.parseInt(tempVO.getAddProofDay());
            String getMaxMonth = UtCoDateUtils.addMonths(getCurrentDm, addProofMonth);
            String getMaxDay = UtCoDateUtils.addDays(getMaxMonth, addProofDay);




        List<VoExct> termManageList = svExctService.selectUseTermManageList(voExct);
        //Escape를 HTML 태그로 변환
        String topTerms = UtCoStringUtils.htmlUnescape(tempVO.getTopTerms());
        String permitTerms = UtCoStringUtils.htmlUnescape(tempVO.getPermitTerms());
        String bottomTerms = UtCoStringUtils.htmlUnescape(tempVO.getBottomTerms());

        tempVO.setTopTerms(topTerms);
        tempVO.setPermitTerms(permitTerms);
        tempVO.setBottomTerms(bottomTerms);

        //session에서 get user date
        String getUserId = (String) tts.get(CsCoConstDef.SS_KEY_003); //기안자ID
        ttObjParams.put("userId", getUserId);
        VoCoUser tempUser = svExctDraftService.getUserDtl(ttObjParams);
        voAddUser.setMadeCd(tempUser.getMadeCd());
        voAddUser.setUserPosition(tempUser.getUserPosition());
        voAddUser.setDeptCd(tempUser.getDeptCd());
        //기본 결재자 리스트
        //List<VoAddUser> approverList = svExctEscortDraftService.getApproverList(voAddUser);
        voAddUser.setMstYn((String)tts.get("mstAuth"));
        voAddUser.setGrpYn((String)tts.get("grpAuth"));
        voAddUser.setSelfYn((String)tts.get("selfAuth"));
        voAddUser.setUserId(getUserId);
        List<VoAddUser> approverList = svExctEscortDraftService.getApproverUserList(voAddUser);
        //session에서 get user date
        String getUserNm = (String) tts.get(CsCoConstDef.SS_KEY_019); //기안자이름

        tempUser.setUserNm(getUserNm);


        ttObjParams.put("voCoUser", tempUser);

        model.addAttribute("termManageList", termManageList);
        model.addAttribute("userDtl", tempUser);
        model.addAttribute("exctDraftDtl", tempVO);
        model.addAttribute("resultDay", resultDay);
        model.addAttribute("maxDay", getMaxDay);
        model.addAttribute("approverList", approverList);
        model.addAttribute("currentDm", getCurrentDm);

        //menuId 갖고다니기
        String menuId = "12";
        ttObjParams.put("id", menuId);
        model.addAttribute("ttObjParams", ttObjParams);

        TTLog.info(":::   adSiteDraft End   :::", this.getClass());

        return "admin.exct.site.draft.tiles";
    }


    /**
     * 사이트예외신청 <br />
     * @param voWkPolicy 모델파라메터
     * @param voExctDraft 모델파라메터
     * @param voCoUser 모델파라메터
     * @param voExctObjPc 모델파라메터
     * @param voApprInfo 모델파라메터
     * @param voPcBasic 모델파라메터
     * @param voApprDetail 모델파라메터
     * @param model 모델
     * @param session 세션
     * @param request 요청
     * @return 페이지경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adSiteApproval.do")
    public String adSiteApproval(VoWkPolicy voWkPolicy, VoExctDraft voExctDraft, VoPcBasic voPcBasic, VoApprDetail voApprDetail,
            VoCoUser voCoUser, VoApprInfo voApprInfo, VoExctObjPc voExctObjPc, ModelMap model,
            HttpSession session, HttpServletRequest request)throws Exception {

        TTLog.info(":::   adSiteApproval Start   :::", this.getClass());


        CoTtObjParams ttObjParams = new CoTtObjParams();
        TtSession tts = getTtSession();
        String getDrafterNm = (String) tts.get(CsCoConstDef.SS_KEY_019); //기안자이름
        String getUserId = (String) tts.get(CsCoConstDef.SS_KEY_003); //기안자ID
        String checkMessage = voCoUser.getCheckMessage();

        System.out.println("########################### " + voPcBasic.getChkEmpNo());

        ttObjParams.put("voPcBasic", voPcBasic);

        //부서GUID가져오기
        VoWkPolicy vo = svExctSiteDraftService.getDeptGUID(ttObjParams);

        if (checkMessage.equals("success_appr")) {
            ttObjParams.put("voCoUser", voCoUser);
            ttObjParams.put("voApprInfo", voApprInfo);
            ttObjParams.put("voExctObjPc", voExctObjPc);
            ttObjParams.put("voPcBasic", voPcBasic);
            ttObjParams.put("voExctDraft", voExctDraft);
            ttObjParams.put("voApprDetail", voApprDetail);
            ttObjParams.put("drafter", getDrafterNm);
            ttObjParams.put("apprUserId", getUserId);
            ttObjParams.put("voWkPolicy", vo);

            if (vo != null) {
                svExctSiteDraftService.insertApprAllInfo(ttObjParams, request);
            } else {

                voApprInfo.setCheckMessage("FALSE");
                model.addAttribute("voApprInfo", voApprInfo);
                return "admin.exct.site.draft.agree.tiles";
            }



        }

        voApprInfo.setCheckMessage(checkMessage);

        model.addAttribute("voApprInfo", voApprInfo);
        //menuId 갖고다니기
        String menuId = "12";
        ttObjParams.put("id", menuId);
        model.addAttribute("ttObjParams", ttObjParams);


        TTLog.info(":::   adSiteApproval End   :::", this.getClass());

        return "admin.exct.site.draft.tiles";

    }


}
