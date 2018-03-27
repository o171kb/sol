package tt.module.admin.exct;

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
import tt.bean.VoExct;
import tt.bean.VoExctDraft;
import tt.com.CoTtObjParams;
import tt.com.constant.CsCoConstDef;
import tt.com.utils.UtCoDateUtils;
import tt.com.utils.UtCoStringUtils;

/**
 * <pre>
 * tt.module.admin.exct
 *    |_ CtAdExctDraftModifyController.java
 *
 * DESC : 예외신청문구수정 Controller 클래스 <br />
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author ks-lee
 * @Date 2013. 3. 19. 오후 12:57:07
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 3. 19.		ks-lee				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
@Controller
public class CtAdExctDraftModifyController extends TtBaseController {


    /**
     * 예외신청문구수정<br />
     * @param voExctDraft 모델파라메터
     * @param model 모델
     * @param request 리퀘스트
     * @return to JSP
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adExctDraftModifyList.do")
    public String adExctDraftModifyList(@ModelAttribute("voExctDraft")VoExctDraft voExctDraft, ModelMap model, HttpServletRequest request) throws Exception {
        // check login
        if (!isLogin(request)) {
            return "admin.login.tiles";
        }
        TTLog.info(":::   adExctDraftModifyList Start   :::", this.getClass());
        CoTtObjParams ttObjParams = new CoTtObjParams();
        List<VoExctDraft> draftList = svExctDraftService.getDraftModifyList(voExctDraft);

        model.addAttribute("draftList", draftList);
        TTLog.info(":::   adExctDraftModifyList End   :::", this.getClass());

        //menuId 갖고다니기
        String menuId = "6";
        ttObjParams.put("id", menuId);
        model.addAttribute("ttObjParams", ttObjParams);

        return "admin.exct.draft.modify.list.tiles";
    }

    /**
     * 예외신청문구수정<br />
     * @param voExctDraft 모델파라메터
     * @param model 모델
     * @param request 리퀘스트
     * @return to JSP
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adExctDraftModify.do")
    public String adExctDraftModify(@ModelAttribute("voExctDraft") VoExctDraft voExctDraft, ModelMap model,
            HttpServletRequest request) throws Exception {

        // check login
        if (!isLogin(request)) {
            return "admin.login.tiles";
        }
        //set search params
        CoTtObjParams ttObjParams = new CoTtObjParams();
        ttObjParams.put("voExctDraft", voExctDraft);

        TtSession tts = getTtSession();
        //session에서 user추가
        String updUser = (String) tts.get(CsCoConstDef.SS_KEY_003);
        voExctDraft.setUpdUser(updUser);
        voExctDraft.setUpdDm(UtCoDateUtils.getCurrentDateAsString(UtCoDateUtils.DATE_PATTERN_DASH));

        //modify exct string
        String checkMessage = voExctDraft.getCheckMessage();
        VoExctDraft msg = new VoExctDraft();

        if (checkMessage.equals("success_mod")) {

            svExctDraftService.modifyExctDraft(ttObjParams);

            //set success msg
            msg.setCheckMessage(checkMessage);
            model.addAttribute("outMsg", msg);

            List<VoExctDraft> draftList = svExctDraftService.getDraftModifyList(voExctDraft);
            model.addAttribute("draftList", draftList);
            return "admin.exct.draft.modify.list.tiles";
        }

        ttObjParams.put("voExctDraft", voExctDraft);
        VoExctDraft tempVO = svExctDraftService.getExctDraftDtl(ttObjParams);

        String topTerms = UtCoStringUtils.htmlUnescape(tempVO.getTopTerms());
        String permitTerms = UtCoStringUtils.htmlUnescape(tempVO.getPermitTerms());
        String bottomTerms = UtCoStringUtils.htmlUnescape(tempVO.getBottomTerms());

        tempVO.setTopTerms(topTerms);
        tempVO.setPermitTerms(permitTerms);
        tempVO.setBottomTerms(bottomTerms);

        //menuId 갖고다니기
        String menuId = "6";
        ttObjParams.put("id", menuId);
        model.addAttribute("ttObjParams", ttObjParams);

        model.addAttribute("exctDraftDtl", tempVO);

        return "admin.exct.draft.modify.tiles";
    }

}
