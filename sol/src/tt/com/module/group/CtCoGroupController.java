package tt.com.module.group;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import tt.base.TtSession;
import tt.base.module.TtBaseController;
import tt.com.CoTtObjParams;
import tt.com.CoTtStrParams;
import tt.com.bean.VoCoUserGrp;
import tt.com.constant.CsCoConstDef;
import tt.com.utils.UtCoStringUtils;

/**
 * <pre>
 * tt.com.module.group
 *    |_ CtCoGroupController.java
 *
 * DESC : 그룹 관리 Controller 클래스 <br />
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author ks-lee
 * @Date 2013. 3. 19. 오후 2:51:40
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 3. 19.		ks-lee				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
@Controller
public class CtCoGroupController extends TtBaseController {

    /**
     * 그룹리스트 취득 <br />
     * @param voCoUserGrp 모델파라메터
     * @param model 모델
     * @param request 리퀘스트
     * @return to JSP
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adGrpSearchList.do")
    public String adGrpSearchList(@ModelAttribute("voCoUserGrp") VoCoUserGrp voCoUserGrp, ModelMap model,
            HttpServletRequest request) throws Exception {

        // check login
        if (!isLogin(request)) {
            return "admin.login.tiles";
        }

        //initialize page
        voCoUserGrp.initPaging();

        //set search params
        CoTtObjParams ttObjParams = new CoTtObjParams();
        ttObjParams.put("voCoUserGrp", voCoUserGrp);

        //search list
        List<VoCoUserGrp> rsUserGrpList = svCoGroupService.getUserGrpSearchListForPage(ttObjParams);
        //total count
        int totCnt = svCoGroupService.getGrpSearchListTotCnt(ttObjParams);
        voCoUserGrp.setTotalRecordCount(totCnt);

        //set view
        model.addAttribute("searchVoCoUserGrp", voCoUserGrp);
        model.addAttribute("totCnt", totCnt);
        model.addAttribute("rsUserGrpList", rsUserGrpList);

        //menuId 갖고다니기
        String menuId = "1";
        ttObjParams.put("id", menuId);
        model.addAttribute("ttObjParams", ttObjParams);

        return "admin.group.search.list";
    }

    /**
     * 그룹삭제 <br />
     * @param voCoUserGrp 모델파라메터
     * @param model 모델
     * @param request 리퀘스트
     * @return to JSP
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adGrpDelete.do")
    public String adDeptDelete(@ModelAttribute("voCoUserGrp") VoCoUserGrp voCoUserGrp, ModelMap model,
            HttpServletRequest request) throws Exception {

        // check login
        if (!isLogin(request)) {
            return "admin.login.tiles";
        }

        //set search params
        CoTtObjParams ttObjParams = new CoTtObjParams();
        ttObjParams.put("voCoUserGrp", voCoUserGrp);
        String checkMessage = voCoUserGrp.getCheckMessage();

        //사용자가 속한 그룹은 삭제 불가
        int result = svCoGroupService.getUserYnCnt(ttObjParams);

        if (result > 0) {
            voCoUserGrp.setCheckMessage("fail_del");

            model.addAttribute("searchVoCoUserGrp", voCoUserGrp);
            return "admin.group.search.list";
        }

        //delete dept
        if (checkMessage.equals("success_del")) {
            svCoGroupService.deleteGroup(ttObjParams);
        } else {

            //보여진페이지에 모두 삭제되었을경우
            if (voCoUserGrp.getListSize() == 0 && voCoUserGrp.getPageIndex() > 1) {
                voCoUserGrp.setPageIndex(voCoUserGrp.getPageIndex() - 1);
            }
        }

        //initialize page
        voCoUserGrp.initPaging();

        ttObjParams.put("voCoUserGrp", voCoUserGrp);

        //search list
        List<VoCoUserGrp> rsUserGrpList = svCoGroupService.getUserGrpSearchListForPage(ttObjParams);
        //total count
        int totCnt = svCoGroupService.getGrpSearchListTotCnt(ttObjParams);
        voCoUserGrp.setTotalRecordCount(totCnt);

        //set view
        model.addAttribute("searchVoCoUserGrp", voCoUserGrp);
        model.addAttribute("totCnt", totCnt);
        model.addAttribute("rsUserGrpList", rsUserGrpList);

        //menuId 갖고다니기
        String menuId = "1";
        ttObjParams.put("id", menuId);
        model.addAttribute("ttObjParams", ttObjParams);

        return "admin.group.search.list";
    }

    /**
     * 그룹수정 <br />
     * @param voCoUserGrp 모델파라메터
     * @param model 모델
     * @param request 리퀘스트
     * @return to JSP
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adGrpModify.do")
    public String adDeptModify(@ModelAttribute("voCoUserGrp") VoCoUserGrp voCoUserGrp, ModelMap model,
            HttpServletRequest request) throws Exception {

        // check login
        if (!isLogin(request)) {
            return "admin.login.tiles";
        }
        //로그인체크후 세션을 가져올수 있다
        TtSession tts = getTtSession();
        String userId = (String) tts.get(CsCoConstDef.SS_KEY_003);

        //사용자정보수정 또는 메뉴수정
        String isUserGrpMod = "1";
        if (!UtCoStringUtils.isEmpty((String) request.getParameter("isUserGrpMod"))) {
            isUserGrpMod = (String) request.getParameter("isUserGrpMod");
        }

        //set search params
        CoTtObjParams ttObjParams = new CoTtObjParams();
        voCoUserGrp.setUserId(userId);
        ttObjParams.put("voCoUserGrp", voCoUserGrp);

        //delete dept
        String checkMessage = voCoUserGrp.getCheckMessage();
        if (checkMessage.equals("success_mod")) {
            if ("1".equals(isUserGrpMod)) {
                //기본그룹정보수정
                svCoGroupService.modifyGrpInfo(ttObjParams);
            } else {
                //메뉴정보수정
                CoTtStrParams ttStrParamsMenu = new CoTtStrParams();
                @SuppressWarnings("rawtypes")
                Enumeration names = request.getParameterNames();
                while (names.hasMoreElements()) {
                    String name = (String) names.nextElement();
                    if (name.startsWith("VIEW.") || name.startsWith("MOD.")) {
                        String menuNo = name.replaceAll("VIEW.", "").replaceAll("MOD.", "");
                        boolean view = (request.getParameter("VIEW." + menuNo) != null ? true : false);
                        boolean mod = (request.getParameter("MOD." + menuNo) != null ? true : false);

                        if (view && mod) {
                            ttStrParamsMenu.put(menuNo, "15");
                        } else if (view && !mod) {
                            ttStrParamsMenu.put(menuNo, "7");
                        } else if (!view && mod) {
                            ttStrParamsMenu.put(menuNo, "8");
                        }
                    }
                }
                //메뉴리스트작성
                ttObjParams.put("ttStrParamsMenu", ttStrParamsMenu);
                svCoGroupService.modifyGrpMenu(ttObjParams);

            }
        }

        //사용자그룹정보취득
        VoCoUserGrp rsUserGrp = svCoGroupService.getGrpDtl(ttObjParams);
        //로그인자권한의 모든그룹메뉴취득
        //List<VoCoUserGrpMenu> userGrpMenuList = svCoGroupService.getUserGrpMenu(ttObjParams);
        //사용자그룹메뉴리스트취득
        CoTtStrParams grpMenuList = svCoGroupService.getUserGrpMenu(ttObjParams);

        //set success msg
        rsUserGrp.setCheckMessage(checkMessage);

        //set view userGrpModifyMode
        model.addAttribute("isUserGrpMod", isUserGrpMod);
        model.addAttribute("voCoUserGrp", rsUserGrp);
        model.addAttribute("grpMenuList", grpMenuList);

        //menuId 갖고다니기
        String menuId = "1";
        ttObjParams.put("id", menuId);
        model.addAttribute("ttObjParams", ttObjParams);

        return "admin.group.modify";
    }

    /**
     * 그룹등록 <br />
     * @param voCoUserGrp 모델파라메터
     * @param model 모델
     * @param request 리퀘스트
     * @return to JSP
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adGrpRegist.do")
    public String adGrpRegist(@ModelAttribute("voCoUserGrp") VoCoUserGrp voCoUserGrp, ModelMap model,
            HttpServletRequest request) throws Exception {

        // check login
        if (!isLogin(request)) {
            return "admin.login.tiles";
        }
        //로그인체크후 세션을 가져올수 있다
        TtSession tts = getTtSession();
        String userId = (String) tts.get(CsCoConstDef.SS_KEY_003);

        //사용자정보수정 또는 메뉴수정
        String isUserGrpMod = "1";
        if (!UtCoStringUtils.isEmpty((String) request.getParameter("isUserGrpMod"))) {
            isUserGrpMod = (String) request.getParameter("isUserGrpMod");
        }

        //set search params
        CoTtObjParams ttObjParams = new CoTtObjParams();
        voCoUserGrp.setUserId(userId);
        ttObjParams.put("voCoUserGrp", voCoUserGrp);

        //delete dept
        String checkMessage = voCoUserGrp.getCheckMessage();

        VoCoUserGrp rsUserGrp = null;
        CoTtStrParams grpMenuList = null;
        //등록할 경우
        if (checkMessage.equals("success_reg")) {
            //메뉴정보설정
            CoTtStrParams ttStrParamsMenu = new CoTtStrParams();
            @SuppressWarnings("rawtypes")
            Enumeration names = request.getParameterNames();
            while (names.hasMoreElements()) {
                String name = (String) names.nextElement();
                if (name.startsWith("VIEW.") || name.startsWith("MOD.")) {
                    String menuNo = name.replaceAll("VIEW.", "").replaceAll("MOD.", "");
                    boolean view = (request.getParameter("VIEW." + menuNo) != null ? true : false);
                    boolean mod = (request.getParameter("MOD." + menuNo) != null ? true : false);

                    if (view && mod) {
                        ttStrParamsMenu.put(menuNo, "15");
                    } else if (view && !mod) {
                        ttStrParamsMenu.put(menuNo, "7");
                    } else if (!view && mod) {
                        ttStrParamsMenu.put(menuNo, "8");
                    }
                }
            }
            //메뉴리스트작성
            ttObjParams.put("ttStrParamsMenu", ttStrParamsMenu);

            //기본그룹정보등록
            int regGrpNo = svCoGroupService.registGrpInfo(ttObjParams);

            //등록한 그룹번호설정
            voCoUserGrp.setUserGrpNo(String.valueOf(regGrpNo));

            //사용자그룹정보취득
            rsUserGrp = svCoGroupService.getGrpDtl(ttObjParams);
            //로그인자권한의 모든그룹메뉴취득
            //List<VoCoUserGrpMenu> userGrpMenuList = svCoGroupService.getUserGrpMenu(ttObjParams);
            //사용자그룹메뉴리스트취득
            grpMenuList = svCoGroupService.getUserGrpMenu(ttObjParams);

            //set success msg
            rsUserGrp.setCheckMessage(checkMessage);
        } else {
        //등록 폼일경우
            rsUserGrp = new VoCoUserGrp();
            grpMenuList = new CoTtStrParams();

        }

        //set view userGrpModifyMode
        model.addAttribute("isUserGrpMod", isUserGrpMod);
        model.addAttribute("voCoUserGrp", rsUserGrp);
        model.addAttribute("grpMenuList", grpMenuList);

        //menuId 갖고다니기
        String menuId = "1";
        ttObjParams.put("id", menuId);
        model.addAttribute("ttObjParams", ttObjParams);

        return "admin.group.regist";
    }


}