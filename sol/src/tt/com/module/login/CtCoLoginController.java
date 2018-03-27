package tt.com.module.login;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import tt.TTLog;
import tt.base.TtSession;
import tt.base.module.TtBaseController;
import tt.bean.VoBoard;
import tt.com.CoTtObjParams;
import tt.com.CoTtStrParams;
import tt.com.bean.VoCoMenu;
import tt.com.constant.CsCoConstDef;
import tt.com.utils.UtCoStringUtils;

/**
 * <pre>
 * tt.com.module.login
 *    |_ CtCoLoginController.java
 *
 * DESC : 로그인 Controller 클래스 <br />
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author ks-lee
 * @Date 2013. 3. 19. 오후 3:07:14
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 3. 19.		ks-lee				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
@Controller
public class CtCoLoginController extends TtBaseController {

    /**
     * 로그인 <br />
     * @param model 모델
     * @param session 세션
     * @param request 리퀘스트
     * @return to JSP
     * @throws Exception 예외
     */
    @RequestMapping(value = "/login.do")
    public String login(ModelMap model, HttpSession session, VoBoard voBoard,
            HttpServletRequest request) throws Exception {

        TTLog.info(":::   login Start   :::", this.getClass());

        // login 후 접근 불가
        if (isLogin(request)) {
            // 관리자홈으로
            return "redirect:/adminHome.do";
        }
        
        /* 공지사항 */
        CoTtObjParams ttObjParams = new CoTtObjParams();
        ttObjParams.put("voBoard", voBoard);
        //List<VoBoard> noticeList = svAdMainService.getBoardList(ttObjParams);
        int totCnt = svBoardSearchService.getBoardListTotCnt(ttObjParams); //total count
        voBoard.setTotalRecordCount(totCnt);

        //model.addAttribute("noticeList", noticeList);
        model.addAttribute("voBoard", voBoard);

        if (request.getParameter("result") != null && request.getParameter("result") != "") { // 만약 로그인 실패하여 반환값이 전달됐을
                                                                                                // 경우
            String result = request.getParameter("result"); // 반환값 변수 생성 후, 반환값 저장
            model.addAttribute("result", result); // view에 반환값을 표시하기 위해 오브젝트 추가
        }

        TTLog.info(":::   login End   :::", this.getClass());

        return "admin.login.tiles";
    }

    /**
     * 로그인 인증후 처리 <br />
     * @param model 모델
     * @param request 리퀘스트
     * @return to JSP
     * @throws Exception 예외
     */
    @RequestMapping(value = "/accessDenied.do")
    public String accessDenied(ModelMap model, HttpServletRequest request)
            throws Exception {

        User user = (User) ((SecurityContext) SecurityContextHolder.getContext()).getAuthentication().getPrincipal(); // Spring
        String userId = user.getUsername().toString(); // Spring security에서 로그인 아이디를 가져온다.

        if (UtCoStringUtils.isEmpty(userId)) {
           //model.addAttribute("userId", userId);
           return "admin.login.tiles";
        }

        CoTtObjParams ttParams = new CoTtObjParams();
        ttParams.put(CsCoConstDef.KEY_USER_ID, userId);
        ttParams.put(CsCoConstDef.KEY_REQUEST, request);

        //로그인정보설정
        svCoAdminLoginService.setLoginInfo(ttParams);

        return "redirect:/adminHome.do";
    }

    /**
     * 로그인 인증후 처리 <br />
     * @param model 모델
     * @param request 리퀘스트
     * @return to JSP
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adminHome.do")
    public String adminHome(ModelMap model, HttpServletRequest request) throws Exception {

        //로그인체크
        if (!isLogin(request)) {
         // 로그인화면으로
            return "admin.login.tiles";
        }

        CoTtObjParams ttObjParams = new CoTtObjParams();
        //로그인체크후 세션을 가져올수 있다
        TtSession tts = getTtSession();


        String userId = (String) tts.get(CsCoConstDef.SS_KEY_003);
        String grpCl = (String) tts.get(CsCoConstDef.SS_KEY_009);

        CoTtStrParams ttParams = new CoTtStrParams();
        ttParams.put(CsCoConstDef.KEY_USER_ID, userId);
        ttParams.put(CsCoConstDef.KEY_GRP_CL, grpCl);

        List<VoCoMenu> userMenuList = svCoAdminLoginService.getUserMenuList(ttParams);

        //menuId 갖고다니기
        String menuId = "20";
        ttObjParams.put("id", menuId);
        model.addAttribute("ttObjParams", ttObjParams);

        tts.put(CsCoConstDef.SS_KEY_018, userMenuList);
        tts.put("mnuLvlList", userMenuList);
        tts.put("grpAuth", userMenuList.get(0).getGrpYn());
        tts.put("selfAuth", userMenuList.get(0).getSelfYn());
        tts.put("mstAuth", userMenuList.get(0).getMstYn());
        // 메인화면홈으로
        return "redirect:/adHome.do";
    }

}