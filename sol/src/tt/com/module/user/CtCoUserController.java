package tt.com.module.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import tt.base.TtSession;
import tt.base.module.TtBaseController;
import tt.com.CoTtObjParams;
import tt.com.bean.VoCoDept;
import tt.com.bean.VoCoUser;
import tt.com.constant.CsCoConstDef;
import tt.com.utils.UtCoStringUtils;

/**
 * <pre>
 * tt.com.module.user
 *    |_ CtCoUserController.java
 *
 * DESC : 사용자(관리자) 관리 Controller 클래스 <br />
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author ks-lee
 * @Date 2013. 3. 19. 오후 3:08:29
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 3. 19.		ks-lee				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
@Controller
public class CtCoUserController extends TtBaseController {

    /**
     * 사용자 검색 초기 <br />
     * @param voCoUser 모델파라메터
     * @param model 모델
     * @param session 세션
     * @param request 리퀘스트
     * @return to JSP
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adUserSearchList.do")
    public String adUserGrpList(@ModelAttribute("voCoUser") VoCoUser voCoUser, ModelMap model, HttpSession session,
            HttpServletRequest request) throws Exception {

      // check login
      if (!isLogin(request)) {
          return "admin.login.tiles";
      }

      //initialize page
      voCoUser.initPaging();

      //set search params
      CoTtObjParams ttObjParams = new CoTtObjParams();
      ttObjParams.put("voCoUser", voCoUser);

      //search list
      List<VoCoUser> rsUserList = svCoUserService.getUserSearchListForPage(ttObjParams);
      //total count
      int totCnt = svCoUserService.getUserSearchListTotCnt(ttObjParams);
      voCoUser.setTotalRecordCount(totCnt);


      //set view
      model.addAttribute("searchVoCoUser", voCoUser);
      model.addAttribute("totCnt", totCnt);
      model.addAttribute("rsUserList", rsUserList);

      //menuId 갖고다니기
      String menuId = "3";
      ttObjParams.put("id", menuId);
      model.addAttribute("ttObjParams", ttObjParams);

      return "admin.user.search.list";
    }

    /**
     * 사용자등록 <br />
     *
     * @param voCoUser 모델파라메터
     * @param model 모델
     * @param request 리퀘스트
     * @return to JSP
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adUserRegist.do")
    public String adUserRegist(@ModelAttribute("voCoUser") VoCoUser voCoUser,
            ModelMap model, HttpServletRequest request) throws Exception {

        // check login
        if (!isLogin(request)) {
            return "admin.login.tiles";
        }

        // set search params
        CoTtObjParams ttObjParams = new CoTtObjParams();

        // regist dept
        String checkMessage = voCoUser.getCheckMessage();
        if (checkMessage.equals("success_reg")) {
            ttObjParams.put("voCoUser", voCoUser);
            svCoUserService.registUser(ttObjParams);
        }

        //search
        VoCoUser rsUser = new VoCoUser();
        if (!UtCoStringUtils.isEmpty(voCoUser.getUserId())) {
            rsUser = svCoUserService.getUserDtl(ttObjParams);
            rsUser.setCheckMessage(checkMessage);
        }
        model.addAttribute("voCoUser", rsUser);

        //menuId 갖고다니기
        String menuId = "3";
        ttObjParams.put("id", menuId);
        model.addAttribute("ttObjParams", ttObjParams);

        return "admin.user.regist";
    }


    /**
     * 사용자수정 <br />
     *
     * @param voCoUser 모델파라메터
     * @param model 모델
     * @param request 리퀘스트
     * @return to JSP
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adUserModify.do")
    public String adUserModify(@ModelAttribute("voCoUser") VoCoUser voCoUser,
            ModelMap model, HttpServletRequest request) throws Exception {

        // check login
        if (!isLogin(request)) {
            return "admin.login.tiles";
        }

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
        String menuId = "3";
        ttObjParams.put("id", menuId);
        model.addAttribute("ttObjParams", ttObjParams);

        return "admin.user.modify";
    }

    /**
     * 사용자 삭제 <br />
     *
     * @param voCoUser 모델파라메터
     * @param model 모델
     * @param request 리퀘스트
     * @return to JSP
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adUserDelete.do")
    public String adUserDelete(@ModelAttribute("voCoUser") VoCoUser voCoUser,
            ModelMap model, HttpServletRequest request) throws Exception {

        //set search params
        CoTtObjParams ttObjParams = new CoTtObjParams();
        ttObjParams.put("voCoUser", voCoUser);

        //delete dept
        String checkMessage = voCoUser.getCheckMessage();
        if (checkMessage.equals("success_del")) {
            svCoUserService.deleteUser(ttObjParams);
        } else {

            //보여진페이지에 모두 삭제되었을경우
            if (voCoUser.getListSize() == 0 && voCoUser.getPageIndex() > 1) {
                voCoUser.setPageIndex(voCoUser.getPageIndex() - 1);
            }
        }

        //initialize page
        voCoUser.initPaging();

        //set search params
        ttObjParams.put("voCoUser", voCoUser);

        //search list
        List<VoCoUser> rsDeptList = svCoUserService.getUserSearchListForPage(ttObjParams);
        //total count
        int totCnt = svCoUserService.getUserSearchListTotCnt(ttObjParams);
        voCoUser.setTotalRecordCount(totCnt);

        //set view
        model.addAttribute("searchVoCoUser", voCoUser);
        model.addAttribute("totCnt", totCnt);
        model.addAttribute("rsUserList", rsDeptList);

        //menuId 갖고다니기
        String menuId = "3";
        ttObjParams.put("id", menuId);
        model.addAttribute("ttObjParams", ttObjParams);

        return "admin.user.search.list";
    }


    /**
     * 사용자리스트 엑셀 다운로드 <br />
     *
     * @param voCoUser 모델파라메터
     * @param model 모델
     * @param request 리퀘스트
     * @return to JSP
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adUserExcelList.do")
    public String adUserExcelList(@ModelAttribute("voCoUser") VoCoUser voCoUser,
            ModelMap model, HttpServletRequest request) throws Exception {

        // check login
        if (!isLogin(request)) {
            return "admin.login.tiles";
        }

        //initialize page
        voCoUser.initPaging();

        //set search params
        CoTtObjParams ttObjParams = new CoTtObjParams();
        ttObjParams.put("voCoUser", voCoUser);

        //search list
        List<VoCoUser> rsUserList = svCoUserService.getUserSearchListForPage(ttObjParams);

        //set view
        model.addAttribute("searchVoCoUser", voCoUser);
        model.addAttribute("rsUserList", rsUserList);

        //menuId 갖고다니기
        String menuId = "3";
        ttObjParams.put("id", menuId);
        model.addAttribute("ttObjParams", ttObjParams);

        return "admin.user.excel.list";
    }
}