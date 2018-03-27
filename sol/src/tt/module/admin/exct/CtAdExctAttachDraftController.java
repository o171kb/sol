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
import tt.bean.VoPcBasic;
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
 * DESC : 첨부통제예외신청 Controller 클래스 <br />
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
@Controller
public class CtAdExctAttachDraftController extends TtBaseController {

    /**
     * 첨부통제예외신청동의 <br />
     * @param model 모델
     * @param session 세션
     * @param request 요청
     * @return 페이지경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adAttachDraftAgree.do")
    public String adAttachDraftAgree(ModelMap model, HttpSession session, HttpServletRequest request) throws Exception {

        TTLog.info(":::   adAttachDraftAgree Start   :::", this.getClass());

        // check login
        if (!isLogin(request)) {
            return "admin.login.tiles";
        }
        TtSession tts = getTtSession();
        CoTtObjParams ttObjParams = new CoTtObjParams();
        VoCoUser tempUser = new VoCoUser();
        String getUserNm = (String) tts.get(CsCoConstDef.SS_KEY_019); //기안자이름
        String getRegDm = UtCoDateUtils.getCurrentDateAsString(UtCoDateUtils.DATE_PATTERN_DASH);
        String getRegDay = UtCoDateUtils.getDayOfWeek(getRegDm, false, Locale.KOREA);

        tempUser.setUserNm(getUserNm);
        tempUser.setRegDm(getRegDm);
        tempUser.setUpdDm(getRegDay);

        model.addAttribute("userInfo", tempUser);

        //menuId 갖고다니기
        String menuId = "8";
        ttObjParams.put("id", menuId);
        model.addAttribute("ttObjParams", ttObjParams);

        TTLog.info(":::   adAttachDraftAgree End   :::", this.getClass());

        return "admin.exct.attach.draft.agree.tiles";
    }

    /**
     * 첨부통제예외신청<br/>
     * @param voExctDraft vo
     * @param voExct  vo
     * @param voCoCodeDtl  vo
     * @param voCoUser  vo
     * @param voAddUser  vo
     * @param model     모델
     * @param session 세션
     * @param request 요청
     * @return 페이지경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adAttachDraft.do")
    public String adAttachDraft(VoExctDraft voExctDraft, VoExct voExct, VoCoCodeDtl voCoCodeDtl, VoCoUser voCoUser, VoAddUser voAddUser,
            ModelMap model, HttpSession session, HttpServletRequest request) throws Exception {

        TTLog.info(":::   adEscortDraft Start   :::", this.getClass());

        // check login
        if (!isLogin(request)) {
            return "admin.login.tiles";
        }
        TtSession tts = getTtSession();

        CoTtObjParams ttObjParams = new CoTtObjParams();
        voExctDraft.setExctAppId("ATTACHDRAFT");
        ttObjParams.put("voExctDraft", voExctDraft);
        VoExctDraft tempVO = svExctDraftService.getExctDraftDtl(ttObjParams);

        String getCurrentDm = UtCoDateUtils.getCurrentDateAsString(UtCoDateUtils.DATE_PATTERN_DASH);

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
        ttObjParams.put("ipAddr", request.getLocalAddr());
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
        //pc 리스트(한개)
        //List<VoPcBasic> myPcList = svExctEscortDraftService.getMyPcList(ttObjParams);
        
        //예외대상PC IP
        //String ip = request.getRemoteAddr();
        String ip = "192.168.1.60";
        String userIp = "";
        String[] arr_userIp = ip.split("\\.");
        
        String userIp1 = (String)UtCoStringUtils.leftPadZero(arr_userIp[0], 3);
        String userIp2 = (String)UtCoStringUtils.leftPadZero(arr_userIp[1], 3);
        String userIp3 = (String)UtCoStringUtils.leftPadZero(arr_userIp[2], 3);
        String userIp4 = (String)UtCoStringUtils.leftPadZero(arr_userIp[3], 3);

        userIp = userIp1+"."+userIp2+"."+userIp3+"."+userIp4 ;
        
        ttObjParams.put("userIp", userIp);
        System.out.println("userIp ::::" + userIp);
        
        System.out.println("userIp:::::::::::::" + ttObjParams.get("userIp"));
        
        List<VoPcBasic> myPcList = svDwrService.getMyPcList(ttObjParams);
        //session에서 get user date
        String getUserNm = (String) tts.get(CsCoConstDef.SS_KEY_019); //기안자이름

        tempUser.setUserNm(getUserNm);

        model.addAttribute("userDtl", tempUser);
        model.addAttribute("exctDraftDtl", tempVO);
        model.addAttribute("currentDm", getCurrentDm);
        model.addAttribute("approverList", approverList);
        model.addAttribute("myPcList", myPcList);

        //menuId 갖고다니기
        String menuId = "8";
        ttObjParams.put("id", menuId);
        model.addAttribute("ttObjParams", ttObjParams);

        TTLog.info(":::   adEscortDraft End   :::", this.getClass());

        return "admin.exct.attach.draft.tiles";
    }

    /**
     * 첨부통제예외신청액션<br/>
     * @param voExctDraft vo
     * @param voCoUser vo
     * @param voApprInfo vo
     * @param voPcBasic vo
     * @param voApprDetail vo
     * @param model 모델
     * @param session 세션
     * @param request 요청
     * @return 페이지경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adAttachApproval.do")
    public String adAttachApprovalReq(VoExctDraft voExctDraft, VoCoUser voCoUser, VoApprInfo voApprInfo, VoPcBasic voPcBasic, VoApprDetail voApprDetail,
            ModelMap model, HttpSession session, HttpServletRequest request) throws Exception {

        TTLog.info(":::   adAttachApprovalReq Start   :::", this.getClass());
        CoTtObjParams ttObjParams = new CoTtObjParams();
        // check login
        if (!isLogin(request)) {
            return "admin.login.tiles";
        }

        TtSession tts = getTtSession();
        String getDrafterNm = (String) tts.get(CsCoConstDef.SS_KEY_019); //기안자이름
        String getUserId = (String) tts.get(CsCoConstDef.SS_KEY_003); //기안자ID
        String checkMessage = voCoUser.getCheckMessage();
        if (checkMessage.equals("success_appr")) {
            ttObjParams.put("voCoUser", voCoUser);
            ttObjParams.put("voApprInfo", voApprInfo);
            ttObjParams.put("voPcBasic", voPcBasic);
            ttObjParams.put("voApprDetail", voApprDetail);
            ttObjParams.put("voExctDraft", voExctDraft);
            ttObjParams.put("drafter", getDrafterNm);
            ttObjParams.put("apprUserId", getUserId);
            svExctAttachDraftService.insertApprAllInfo(ttObjParams, request);
        }

        voApprInfo.setCheckMessage(checkMessage);

        model.addAttribute("voApprInfo", voApprInfo);

        //menuId 갖고다니기
        String menuId = "8";
        ttObjParams.put("id", menuId);
        model.addAttribute("ttObjParams", ttObjParams);

        TTLog.info(":::   adAttachApprovalReq End   :::", this.getClass());

        return "admin.exct.attach.draft.tiles";
    }

}
