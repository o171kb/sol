package tt.module.admin.mypage;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import tt.TTLog;
import tt.base.TtHttpSessionManager;
import tt.base.TtSession;
import tt.base.module.TtBaseController;
import tt.com.CoTtObjParams;
import tt.com.bean.VoCoUser;
import tt.com.constant.CsCoConstDef;
import tt.com.utils.UtCoStringUtils;


/**
 * <pre>
 * tt.module.admin.mypage
 *    |_ CtAdMyPageController.java
 *
 * DESC :
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author hk-kim
 * @Date 2013. 4. 15. 오후 8:27:10
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 4. 15.		hk-kim				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
@Controller
public class CtAdMyPageController extends TtBaseController {

    /**
     * 나의정보수정 <br />
     * @param voCoUser 모델파라메터
     * @param model 모델
     * @param request 리퀘스트
     * @return 페이지경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adMyPage.do")
    public String adMyPage(@ModelAttribute("voCoUser") VoCoUser voCoUser,
            ModelMap model, HttpServletRequest request) throws Exception {

        TTLog.info(":::   adMyPage Start   :::", this.getClass());

        // check login
        if (!isLogin(request)) {
            return "admin.login.tiles";
        }

        //session id
        TtSession tts = (TtSession) TtHttpSessionManager.getTtSession(request);
        String sessionId = (String) tts.get(CsCoConstDef.SS_KEY_003);
        voCoUser.setUserId(sessionId);

        // set search params
        CoTtObjParams ttObjParams = new CoTtObjParams();
        ttObjParams.put("voCoUser", voCoUser);

        // regist dept
        String checkMessage = voCoUser.getCheckMessage();
        if (checkMessage.equals("success_mod")) {
            svCoUserService.modifyUser(ttObjParams);
        }

        // search
        VoCoUser rsUser = new VoCoUser();
        rsUser = svCoUserService.getUserDtl(ttObjParams);

        // 대행결제자 이름 가져오기
        if (!UtCoStringUtils.isEmpty(rsUser.getProxyApprId())) {
            voCoUser.setProxyApprId(rsUser.getProxyApprId());
            ttObjParams.put("voCoUser", voCoUser);
            String proxyNm = svCoUserService.getProxyName(ttObjParams);
            rsUser.setProxyApprNm(proxyNm);
        }

        // set success msg
        rsUser.setCheckMessage(checkMessage);

        // set view
        model.addAttribute("voCoUser", rsUser);

        //menuId 갖고다니기
        String menuId = "18";
        ttObjParams.put("id", menuId);
        model.addAttribute("ttObjParams", ttObjParams);

        TTLog.info(":::   adMyPage End   :::", this.getClass());

        return "admin.my.page.tiles";
    }

}
