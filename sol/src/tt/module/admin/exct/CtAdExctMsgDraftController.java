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
import tt.bean.VoExct;
import tt.bean.VoExctDraft;
import tt.com.CoTtObjParams;
import tt.com.bean.VoCoCodeDtl;
import tt.com.bean.VoCoUser;
import tt.com.constant.CsCoConstDef;
import tt.com.utils.UtCoDateUtils;
import tt.com.utils.UtCoStringUtils;

/**
 * <pre>
 * tt.module.admin.exct
 *    |_ CtAdExctMsgDraftController.java
 *
 * DESC : 메신저예외신청 Controller 클래스 <br />
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author ks-lee
 * @Date 2013. 3. 19. 오후 1:43:11
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 3. 19.		ks-lee				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
@Controller
public class CtAdExctMsgDraftController extends TtBaseController {

    /**
     * 메신저예외신청<br/>
     * @param model 모델
     * @param session 세션
     * @param request 요청
     * @return 페이지경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adMsgDraftAgree.do")
    public String adAttachDraftAgree(ModelMap model, HttpSession session, HttpServletRequest request) throws Exception {

        TTLog.info(":::   adMsgDraftAgree Start   :::", this.getClass());

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
        String menuId = "11";
        ttObjParams.put("id", menuId);
        model.addAttribute("ttObjParams", ttObjParams);

        TTLog.info(":::   adMsgDraftAgree End   :::", this.getClass());

        return "admin.exct.msg.draft.agree.tiles";
    }

    /**
     * @param voExctDraft vo
     * @param voExct vo
     * @param voCoCodeDtl vo
     * @param voCoUser vo
     * @param voAddUser vo
     * @param model 모델
     * @param session 세션
     * @param request 요청
     * @return 페이지경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adMsgDraft.do")
    public String adMsgDraft(VoExctDraft voExctDraft, VoExct voExct, VoCoCodeDtl voCoCodeDtl, VoCoUser voCoUser, VoAddUser voAddUser, ModelMap model, HttpSession session, HttpServletRequest request) throws Exception {

        TTLog.info(":::   adMsgDraft Start   :::", this.getClass());

        // check login
        if (!isLogin(request)) {
            return "admin.login.tiles";
        }
        TtSession tts = getTtSession();

        CoTtObjParams ttObjParams = new CoTtObjParams();
        voExctDraft.setExctAppId("MSGDRAFT");
        ttObjParams.put("voExctDraft", voExctDraft);
        VoExctDraft tempVO = svExctDraftService.getExctDraftDtl(ttObjParams);

        String getCurrentDm = UtCoDateUtils.getCurrentDateAsString(UtCoDateUtils.DATE_PATTERN_DASH);

        int maxAppTerm = Integer.parseInt(tempVO.getMaxAppTerm());
        String resultDay = UtCoDateUtils.addMonths(getCurrentDm, maxAppTerm);

        int addProofMonth = Integer.parseInt(tempVO.getAddProofMonth());
        int addProofDay = Integer.parseInt(tempVO.getAddProofDay());
        String getMaxMonth = UtCoDateUtils.addMonths(getCurrentDm, addProofMonth);
        String getMaxDay = UtCoDateUtils.addDays(getMaxMonth, addProofDay);

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
        //기본 결재자 리스트
        List<VoAddUser> approverList = svExctEscortDraftService.getApproverList(voAddUser);
        //session에서 get user date
        String getUserNm = (String) tts.get(CsCoConstDef.SS_KEY_019); //기안자이름

        tempUser.setUserNm(getUserNm);

        model.addAttribute("userDtl", tempUser);
        model.addAttribute("exctDraftDtl", tempVO);
        model.addAttribute("resultDay", resultDay);
        model.addAttribute("maxDay", getMaxDay);
        model.addAttribute("approverList", approverList);

        //menuId 갖고다니기
        String menuId = "11";
        ttObjParams.put("id", menuId);
        model.addAttribute("ttObjParams", ttObjParams);

        TTLog.info(":::   adMsgDraft End   :::", this.getClass());

        return "admin.exct.msg.draft.tiles";
    }

}
