package tt.com.module.dept;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import tt.base.TtSession;
import tt.base.module.TtBaseController;
import tt.com.CoTtObjParams;
import tt.com.bean.VoCoDept;
import tt.com.constant.CsCoConstDef;
import tt.com.utils.UtCoStringUtils;

/**
 * <pre>
 * tt.com.module.dept
 *    |_ CtCoDeptController.java
 *
 * DESC : 부서 관리 Controller 클래스 <br />
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author ks-lee
 * @Date 2013. 3. 8. 오전 9:39:35
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 3. 8.		ks-lee				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
@Controller
public class CtCoDeptController extends TtBaseController {

    /**
     * 부서검색 취득 <br />
     * @param voCoDept 모델파라메터
     * @param model 모델
     * @param request 리퀘스트
     * @return to JSP
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adDeptSearchList.do")
    public String adDeptSearchList(@ModelAttribute("voCoDept") VoCoDept voCoDept, ModelMap model,
            HttpServletRequest request) throws Exception {

        // check login
        if (!isLogin(request)) {
            return "admin.login.tiles";
        }

        //initialize page
        voCoDept.initPaging();

        //set search params
        CoTtObjParams ttObjParams = new CoTtObjParams();
        ttObjParams.put("voCoDept", voCoDept);

        //search list
        List<VoCoDept> rsDeptList = svCoDeptService.getDeptSearchListForPage(ttObjParams);
        //total count
        int totCnt = svCoDeptService.getDeptSearchListTotCnt(ttObjParams);
        voCoDept.setTotalRecordCount(totCnt);

        //set view
        model.addAttribute("searchVoCoDept", voCoDept);
        model.addAttribute("totCnt", totCnt);
        model.addAttribute("rsDeptList", rsDeptList);

        //menuId 갖고다니기
        String menuId = "2";
        ttObjParams.put("id", menuId);
        model.addAttribute("ttObjParams", ttObjParams);

        return "admin.dept.search.list";
    }

    /**
     * 부서삭제 <br />
     * @param voCoDept 모델파라메터
     * @param model 모델
     * @param request 리퀘스트
     * @return to JSP
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adDeptDelete.do")
    public String adDeptDelete(@ModelAttribute("voCoDept") VoCoDept voCoDept, ModelMap model,
            HttpServletRequest request) throws Exception {

        // check login
        if (!isLogin(request)) {
            return "admin.login.tiles";
        }

        //set search params
        CoTtObjParams ttObjParams = new CoTtObjParams();
        ttObjParams.put("voCoDept", voCoDept);

        int result = svCoDeptService.getUserYnCnt(ttObjParams);

        if (result > 0) {
            voCoDept.setCheckMessage("fail_del");

            model.addAttribute("searchVoCoDept", voCoDept);
            return "admin.dept.search.list";
        }



        //delete dept
        String checkMessage = voCoDept.getCheckMessage();
        if (checkMessage.equals("success_del")) {
            svCoDeptService.deleteDept(ttObjParams);
        } else {

            //보여진페이지에 모두 삭제되었을경우
            if (voCoDept.getListSize() == 0 && voCoDept.getPageIndex() > 1) {
                voCoDept.setPageIndex(voCoDept.getPageIndex() - 1);
            }
        }

        //initialize page
        voCoDept.initPaging();

        //set search params
        ttObjParams.put("voCoDept", voCoDept);

        //search list
        List<VoCoDept> rsDeptList = svCoDeptService.getDeptSearchListForPage(ttObjParams);
        //total count
        int totCnt = svCoDeptService.getDeptSearchListTotCnt(ttObjParams);
        voCoDept.setTotalRecordCount(totCnt);

        //set view
        model.addAttribute("searchVoCoDept", voCoDept);
        model.addAttribute("rsDeptList", rsDeptList);

        //menuId 갖고다니기
        String menuId = "2";
        ttObjParams.put("id", menuId);
        model.addAttribute("ttObjParams", ttObjParams);


        return "admin.dept.search.list";
    }

    /**
     * 부서수정 <br />
     * @param voCoDept 모델파라메터
     * @param model 모델
     * @param request 리퀘스트
     * @return to JSP
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adDeptModify.do")
    public String adDeptModify(@ModelAttribute("voCoDept") VoCoDept voCoDept, ModelMap model,
            HttpServletRequest request) throws Exception {

        // check login
        if (!isLogin(request)) {
            return "admin.login.tiles";
        }
        //로그인체크후 세션을 가져올수 있다
        TtSession tts = getTtSession();
        String userId = (String) tts.get(CsCoConstDef.SS_KEY_003);

        //set search params
        CoTtObjParams ttObjParams = new CoTtObjParams();
        voCoDept.setUserId(userId);
        ttObjParams.put("voCoDept", voCoDept);

        //delete dept
        String checkMessage = voCoDept.getCheckMessage();
        if (checkMessage.equals("success_mod")) {
            svCoDeptService.modifyDept(ttObjParams);
        }

        //search
        VoCoDept rsDept = svCoDeptService.getDeptDtl(ttObjParams);
        //set success msg
        rsDept.setCheckMessage(checkMessage);

        //set view
        model.addAttribute("voCoDept", rsDept);

        //menuId 갖고다니기
        String menuId = "2";
        ttObjParams.put("id", menuId);
        model.addAttribute("ttObjParams", ttObjParams);


        return "admin.dept.modify";
    }

    /**
     * 부서등록 <br />
     * @param voCoDept 모델파라메터
     * @param model 모델
     * @param request 리퀘스트
     * @return to JSP
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adDeptRegist.do")
    public String adDeptRegist(@ModelAttribute("voCoDept") VoCoDept voCoDept, ModelMap model,
            HttpServletRequest request) throws Exception {

        // check login
        if (!isLogin(request)) {
            return "admin.login.tiles";
        }
        //로그인체크후 세션을 가져올수 있다
        TtSession tts = getTtSession();
        String userId = (String) tts.get(CsCoConstDef.SS_KEY_003);

        //set search params
        CoTtObjParams ttObjParams = new CoTtObjParams();
        voCoDept.setUserId(userId);
        ttObjParams.put("voCoDept", voCoDept);

        //regist dept
        String checkMessage = voCoDept.getCheckMessage();
        if (checkMessage.equals("success_reg")) {
            svCoDeptService.registDept(ttObjParams);
        }

        //search
        VoCoDept rsDept = new VoCoDept();
        if (!UtCoStringUtils.isEmpty(voCoDept.getDeptNo())) {
            svCoDeptService.getDeptDtl(ttObjParams);
        }
        //set success msg
        rsDept.setCheckMessage(checkMessage);

        //for dept popup
        List<VoCoDept> rsDeptList =  svCoDeptService.getDeptSearchListAll(ttObjParams);

        //set view
        model.addAttribute("voCoDept", rsDept);
        model.addAttribute("deptList", rsDeptList);

        //menuId 갖고다니기
        String menuId = "2";
        ttObjParams.put("id", menuId);
        model.addAttribute("ttObjParams", ttObjParams);


        return "admin.dept.regist";
    }

}