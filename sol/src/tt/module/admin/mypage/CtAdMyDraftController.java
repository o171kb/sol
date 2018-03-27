package tt.module.admin.mypage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import tt.TTLog;
import tt.base.TtHttpSessionManager;
import tt.base.TtSession;
import tt.base.module.TtBaseController;
import tt.bean.VoAddUser;
import tt.bean.VoApprDetail;
import tt.bean.VoApprInfo;
import tt.bean.VoExct;
import tt.bean.VoExctDraft;
import tt.bean.VoExctObjPc;
import tt.bean.VoPcBasic;
import tt.com.CoTtObjParams;
import tt.com.bean.VoCoCodeDtl;
import tt.com.bean.VoCoUser;
import tt.com.constant.CsCoConstDef;
import tt.com.utils.UtCoDateUtils;
import tt.com.utils.UtCoStringUtils;

/**
 * <pre>
 * tt.module.admin.mypage
 *    |_ CtAdMyDraftController.java
 *
 * DESC : 나의신청현황 Controller 클래스 <br />
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author ks-lee
 * @Date 2013. 3. 19. 오후 2:11:34
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 3. 19.		ks-lee				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
@Controller
public class CtAdMyDraftController extends TtBaseController {

    /**
     * 나의신청현황 <br />
     * @param voCoUser 관리자정보 vo
     * @param voApprInfo 예외신청리스트 vo
     * @param voPcBasic 예외처리대상PC vo
     * @param model 모델
     * @param session 세션
     * @param request 리퀘스트
     * @return to JSP 페이지경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adMyDraftList.do")
    public String adMyDraftList(VoCoUser voCoUser, VoApprInfo voApprInfo, VoPcBasic voPcBasic, ModelMap model, HttpSession session, HttpServletRequest request) throws Exception {


        TTLog.info(":::   adMyDraftList Start   :::", this.getClass());

        //check login
        if (!isLogin(request)) {
            return "admin.login.tiles";
        }

        voApprInfo.initPaging();    //paging

        CoTtObjParams ttObjParams = new CoTtObjParams();
        //session id
        TtSession tts = (TtSession) TtHttpSessionManager.getTtSession(request);
        String sessionId = (String) tts.get(CsCoConstDef.SS_KEY_003);
        voApprInfo.setUserId(sessionId);
        
        String deptCd = (String)tts.get("deptCd");
        
        String arrDept[] = {};
        
        VoApprInfo voDpet = new VoApprInfo();
        
        voDpet.setHighDeptCd(deptCd);
        
        List<VoApprInfo> deptList = svMyDraftService.getMyDeptList(voDpet);
        /*if(deptList.size() > 0 ){
            String highDeptCd = deptList.get(0).getDeptCd();
            
            List<VoApprInfo> deptList = svMyDraftService.getMyDeptList(voDpet);
        }*/
        System.out.println("deptCd::::::::::::::::::::::::" + deptCd);
        
        if(voApprInfo.getTabDiv() == null){
            voApprInfo.setTabDiv("myReq");
        }
        
        voApprInfo.setDeptCd((String)tts.get("deptCd"));
        ttObjParams.put("voApprInfo", voApprInfo);

        List<VoApprInfo> list = svMyDraftService.getMyDraftList(ttObjParams);

        VoApprInfo vo = new VoApprInfo();
        for (int i = 0; i < list.size(); i++) {
            vo = list.get(i);
            String getUsr[] = vo.getApprobjUser().split(",");

            if (getUsr.length > 1) {
                String name = getUsr[0] + " 외 " + (getUsr.length - 1) + " 건";
                list.get(i).setApprobjUser(name);
            }
        }


        //total count
        int totCnt = svMyDraftService.getMyDraftListTotCnt(ttObjParams);
        voApprInfo.setTotalRecordCount(totCnt);

        model.addAttribute("list", list);
        //model.addAttribute("voApprInfo", voApprInfo);
        
        //menuId 갖고다니기
        String menuId = "19";
        ttObjParams.put("id", menuId);
        model.addAttribute("ttObjParams", ttObjParams);

        TTLog.info(":::   adMyDraftList End   :::", this.getClass());

        return "admin.mypage.mydraft.list.tiles";
    }



    /**
     * 나의신청현황 엑셀 다운로드 <br />
     * @param voCoUser 관리자정보 vo
     * @param voApprInfo 예외신청리스트 vo
     * @param voPcBasic 예외처리대상PC vo
     * @param model 모델
     * @param session 세션
     * @param request 리퀘스트
     * @return to JSP 페이지경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adMyDraftExcelList.do")
    public String adMyDraftExcelList(VoCoUser voCoUser, VoApprInfo voApprInfo, VoPcBasic voPcBasic, ModelMap model, HttpSession session, HttpServletRequest request) throws Exception {


        TTLog.info(":::   adMyDraftList Start   :::", this.getClass());

        //check login
        if (!isLogin(request)) {
            return "admin.login.tiles";
        }

        voApprInfo.initPaging();    //paging

        CoTtObjParams ttObjParams = new CoTtObjParams();
        //session id
        TtSession tts = (TtSession) TtHttpSessionManager.getTtSession(request);
        String sessionId = (String) tts.get(CsCoConstDef.SS_KEY_003);
        voApprInfo.setUserId(sessionId);

        ttObjParams.put("voApprInfo", voApprInfo);

        List<VoApprInfo> list = svMyDraftService.getMyDraftList(ttObjParams);

        VoApprInfo vo = new VoApprInfo();
        for (int i = 0; i < list.size(); i++) {
            vo = list.get(i);
            String getUsr[] = vo.getApprobjUser().split(",");

            if (getUsr.length > 1) {
                String name = getUsr[0] + " 외 " + (getUsr.length - 1) + " 건";
                list.get(i).setApprobjUser(name);
            }
        }


        //total count
        int totCnt = svMyDraftService.getMyDraftListTotCnt(ttObjParams);
        voApprInfo.setTotalRecordCount(totCnt);

        model.addAttribute("list", list);
        model.addAttribute("voApprInfo", voApprInfo);

        //menuId 갖고다니기
        String menuId = "19";
        ttObjParams.put("id", menuId);
        model.addAttribute("ttObjParams", ttObjParams);

        TTLog.info(":::   adMyDraftList End   :::", this.getClass());

        return "admin.mypage.mydraft.excel.list.tiles";
    }

    /**
     * 나의신청현황 상세 <br />
     * @param voApprInfo 예외신청리스트 vo
     * @param voExctDraft vo
     * @param voExct vo
     * @param voCoCodeDtl 코드상세 vo
     * @param voAddUser vo
     * @param voCoUser vo
     * @param voPcBasic 예외처리대상PC vo
     * @param model 모델
     * @param request 리퀘스트
     * @return to JSP
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adMyDraftView.do")
    public String adMyDraftView(@ModelAttribute("voApprInfo") VoApprInfo voApprInfo, VoExctDraft voExctDraft, VoExct voExct, VoCoCodeDtl voCoCodeDtl, VoAddUser voAddUser,
            VoCoUser voCoUser, VoPcBasic voPcBasic, ModelMap model, HttpServletRequest request) throws Exception {

        TTLog.info(":::   adMyDraftView Start   :::", this.getClass());

        //check login
        if (!isLogin(request)) {
            return "admin.login.tiles";
        }

        CoTtObjParams ttObjParams = new CoTtObjParams();
        CoTtObjParams ttObjParams2 = new CoTtObjParams();
        ttObjParams.put("voApprInfo", voApprInfo);

        VoApprInfo vo = svMyDraftService.getMyDraftDetail(ttObjParams);     //신청항목
        vo.setPageIndex(voApprInfo.getPageIndex());
        model.addAttribute("voApprInfo", vo);

        TtSession tts = getTtSession();

        //session에서 get user date
        String getUserId = (String) tts.get(CsCoConstDef.SS_KEY_003); //기안자ID
        
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!temmpUser : " + getUserId);
        ttObjParams.put("userId", getUserId);
        ttObjParams2.put("userId", voApprInfo.getUserId());
        VoCoUser tempUser = svExctDraftService.getUserDtl(ttObjParams2);     //기안자정보 (로그인자)
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!temmpUser : " + tempUser.getUserNm());
        //voAddUser.setMadeCd(tempUser.getMadeCd());
        //session에서 get user date
        //String getUserNm = (String) tts.get(CsCoConstDef.SS_KEY_019); //기안자이름
        //tempUser.setUserNm(getUserNm);
        model.addAttribute("userDtl", tempUser);

        List<VoApprInfo> approverList  = svMyDraftService.getApproverList(ttObjParams);     //결재자 정보
        model.addAttribute("approverList", approverList);

        List<VoExctObjPc> approvalPcList = svMyDraftService.getApprovalPcList(ttObjParams);     //예외대상PC
        model.addAttribute("approvalPcList", approvalPcList);

        List<VoApprDetail> apprDetailList = svMyDraftService.getApprDetailList(ttObjParams);          //신청항목 리스트
        model.addAttribute("apprDetailList", apprDetailList);

        //menuId 갖고다니기
        String menuId = "19";
        ttObjParams.put("id", menuId);
        model.addAttribute("ttObjParams", ttObjParams);

        TTLog.info(":::   adMyDraftView End   :::", this.getClass());

        return "admin.mypage.mydraft." + vo.getExctAppId() + ".view.tiles";
    }

}
