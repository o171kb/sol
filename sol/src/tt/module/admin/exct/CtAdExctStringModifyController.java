package tt.module.admin.exct;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import tt.TTLog;
import tt.base.module.TtBaseController;
import tt.bean.VoExct;
import tt.com.CoTtObjParams;
import tt.com.bean.VoCoDept;

/**
 * <pre>
 * tt.module.admin.exct
 *    |_ CtAdExctStringModifyController.java
 *
 * DESC : 예외용어문구수정 Controller 클래스 <br />
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author ks-lee
 * @Date 2013. 3. 19. 오후 12:56:48
 * @history :
 *  -----------------------------------------------------------------------
 *  변경일             작성자                     변경내용
 *  ----------- ------------------- ---------------------------------------
 *  2013. 3. 19.        ks-lee              최초 작성
 *  -----------------------------------------------------------------------
 *
 */
@Controller
public class CtAdExctStringModifyController extends TtBaseController {
    /**
     * 용어관리 사용 업데이트<br />
     * @param voExct 모델파라메터
     * @param model 모델
     * @param request 리퀘스트
     * @return to JSP
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adExctStringUseYn.do")
    public String adTermUpdateUseYn(@ModelAttribute("voExct") VoExct voExct, ModelMap model,
            HttpServletRequest request) throws Exception {

        // check login
        if (!isLogin(request)) {
            return "admin.login.tiles";
        }

      //set useYn params
        CoTtObjParams ttObjParams = new CoTtObjParams();
        ttObjParams.put("voExct", voExct);

        svExctService.updateExctUseYn(ttObjParams);

        String checkMessage = voExct.getCheckMessage();
        voExct.setCheckMessage(checkMessage);
        System.out.println(checkMessage);

        model.addAttribute("voExct", voExct);

        //menuId 갖고다니기
        String menuId = "5";
        ttObjParams.put("id", menuId);
        model.addAttribute("ttObjParams", ttObjParams);

        return "admin.exct.string.modify.list.tiles";
    }

    /**
     * 예외용어문구수정페이지 <br />
     * @param model 모델
     * @param session 세션
     * @return 페이지경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adExctStringModifyList.do")
    public String adTermManageList(@ModelAttribute("voExct") VoExct voExct, ModelMap model,
            HttpServletRequest request) throws Exception {

        // check login
        if (!isLogin(request)) {
            return "admin.login.tiles";
        }
        TTLog.info(":::   adExctStringModifyList Start   :::", this.getClass());

        List<VoExct> termManageList = svExctService.selectTermManageList(voExct);

        TTLog.info(":::   adExctStringModifyList End   :::", this.getClass());
        model.addAttribute("termManageList", termManageList);
        CoTtObjParams ttObjParams = new CoTtObjParams();
        //menuId 갖고다니기
        String menuId = "5";
        ttObjParams.put("id", menuId);
        model.addAttribute("ttObjParams", ttObjParams);
        return "admin.exct.string.modify.list.tiles";
    }

    /**
     * 용어관리 수정폼<br />
     * @param voExct 모델파라메터
     * @param model 모델
     * @param request 리퀘스트
     * @return to JSP
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adExctStringModify.do")
    public String adExctStringModify(@ModelAttribute("voExct") VoExct voExct, ModelMap model,
            HttpServletRequest request) throws Exception {

        // check login
        if (!isLogin(request)) {
            return "admin.login.tiles";
        }
      //set search params
        CoTtObjParams ttObjParams = new CoTtObjParams();
        ttObjParams.put("voExct", voExct);

        //modify exct string
        String checkMessage = voExct.getCheckMessage();
        if (checkMessage.equals("success")) {
            svExctService.modifyExctString(ttObjParams);
        }

        VoExct tempVO = svExctService.getExctStringDtl(ttObjParams);
        //set success msg
        tempVO.setCheckMessage(checkMessage);

        model.addAttribute("exctList", tempVO);

        //menuId 갖고다니기
        String menuId = "5";
        ttObjParams.put("id", menuId);
        model.addAttribute("ttObjParams", ttObjParams);

        return "admin.exct.string.modify.tiles";
    }
    
    /**
     * 용어관리 입력폼<br />
     * @param voExct 모델파라메터
     * @param model 모델
     * @param request 리퀘스트
     * @return to JSP
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adExctStringInsert.do")
    public String adExctStringInsert(@ModelAttribute("voExct") VoExct voExct, ModelMap model,
            HttpServletRequest request) throws Exception {

        // check login
        if (!isLogin(request)) {
            return "admin.login.tiles";
        }
      //set search params
        CoTtObjParams ttObjParams = new CoTtObjParams();
        ttObjParams.put("voExct", voExct);

        //modify exct string
        String checkMessage = voExct.getCheckMessage();
        if (checkMessage.equals("success")) {
            svExctService.modifyExctString(ttObjParams);
        }

        VoExct tempVO = svExctService.getExctStringDtl(ttObjParams);
        //set success msg
        tempVO.setCheckMessage(checkMessage);

        model.addAttribute("exctList", tempVO);

        //menuId 갖고다니기
        String menuId = "5";
        ttObjParams.put("id", menuId);
        model.addAttribute("ttObjParams", ttObjParams);

        return "admin.exct.string.insert.tiles";
    }


}
