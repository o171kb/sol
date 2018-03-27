package tt.module.admin.main;

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
import tt.bean.VoApprInfo;
import tt.bean.VoBoard;
import tt.com.CoTtObjParams;
import tt.com.constant.CsCoConstDef;
import tt.com.utils.UtCoStringUtils;

/**
 * <pre>
 * tt.module.admin.main
 *    |_ CtAdMainController.java
 *
 * DESC : 메인 Controller 클래스 <br />
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author ks-lee
 * @Date 2013. 3. 19. 오후 3:11:22
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 3. 19.		ks-lee				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
@Controller
public class CtAdMainController extends TtBaseController {

    /**
     * 메인화면 <br />
     * @param voBoard 모델파라메터
     * @param model 모델
     * @param session 세션
     * @param request 리퀘스트
     * @return to JSP
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adHome.do")
    public String adHome(@ModelAttribute("vo") VoBoard voBoard, VoApprInfo voApprInfo, ModelMap model, HttpSession session, HttpServletRequest request)
            throws Exception {
        TTLog.info(":::   adHome Start   :::", this.getClass());

        // check login
        if (!isLogin(request)) {
            return "admin.login.tiles";
        }


        TtSession tts = (TtSession) TtHttpSessionManager.getTtSession(request);
        String sessionId = (String) tts.get(CsCoConstDef.SS_KEY_003);
        voApprInfo.setUserId(sessionId);

        /* 공지사항 */
        CoTtObjParams ttObjParams = new CoTtObjParams();
        
        if(voBoard.getTabDiv() == null){
            voBoard.setTabDiv("myReq");
        }
        
        if(voBoard.getTabDiv2() == null){
            voBoard.setTabDiv2("myApp");
        }
        
        ttObjParams.put("voBoard", voBoard);
        List<VoBoard> noticeList = svAdMainService.getBoardList(ttObjParams);
        int totCnt = svBoardSearchService.getBoardListTotCnt(ttObjParams); //total count
        voBoard.setTotalRecordCount(totCnt);

        model.addAttribute("noticeList", noticeList);
        model.addAttribute("voBoard", voBoard);
        /*//공지사항 */

        /* 나의신청현황(상신함) */
        ttObjParams.clear();    //param 초기화

        voApprInfo.setDeptCd((String)tts.get("deptCd"));
        ttObjParams.put("voApprInfo", voApprInfo);
        ttObjParams.put("tabDiv", voBoard.getTabDiv());
        /*ttObjParams.put("deptCd", tts.get(CsCoConstDef.SS_KEY_003));*/
        
//        voApprInfo.initPaging();    //paging
//        List<VoApprInfo> draftList = svMyDraftService.getMyDraftList(ttObjParams);
//        
//        System.out.println("::::::::::::::::::::::::::::::::::::::"+draftList.size());
//        
//        String reqCount = "0";
//        String reqDoCount = "0";
//        
//        if(draftList.size() > 0 )
//        {
//            reqCount = UtCoStringUtils.nvl(draftList.get(0).getReqCount(),"0");
//            reqDoCount = UtCoStringUtils.nvl(draftList.get(0).getReqDoCount(),"0");   
//        }
//        
//        VoApprInfo draftVo = new VoApprInfo();
//        for (int i = 0; i < draftList.size(); i++) {
//            draftVo = draftList.get(i);
//            String getUsr[] = draftVo.getApprobjUser().split(",");
//
//            if (getUsr.length > 1) {
//                String name = getUsr[0] + " 외 " + (getUsr.length - 1) + " 건";
//                draftList.get(i).setApprobjUser(name);
//            }
//        }
//
//        int totCnt2 = svMyDraftService.getMyDraftListTotCnt(ttObjParams);   //total count
//        voApprInfo.setTotalRecordCount(totCnt2);
//        
//        model.addAttribute("reqCount", reqCount);
//        model.addAttribute("reqDoCount", reqDoCount);
//        model.addAttribute("draftList", draftList);
//        model.addAttribute("voDraftInfo", voApprInfo);
//        /*//나의신청현황(상신함)  */
//
//
//        /* 나의결재함 */
//        ttObjParams.clear();    //param 초기화
//        ttObjParams.put("voApprInfo", voApprInfo);
//        ttObjParams.put("tabDiv2", voBoard.getTabDiv2());
//        
//        /*ttObjParams.put("deptCd", tts.get(CsCoConstDef.SS_KEY_003));*/
//
//        voApprInfo.initPaging();    //paging
//        List<VoApprInfo> approvalList = svMyApprovalService.getMyApprovalList(ttObjParams);
//        
//        System.out.println("::::::::::::::::::::::::::::::::::::::"+approvalList.size());
//        
//        String apprCount = "0";
//        String apprDoCount = "0";
//        
//        if(approvalList.size() > 0 )
//        {
//            apprCount = UtCoStringUtils.nvl(approvalList.get(0).getApprCount(), "0");
//            apprDoCount = UtCoStringUtils.nvl(approvalList.get(0).getApprDoCount(), "0");
//        }
//        
//        VoApprInfo vo = new VoApprInfo();
//        for (int i = 0; i < approvalList.size(); i++) {
//            vo = approvalList.get(i);
//            String[] getUsr = vo.getApprobjUser().split(",");
//            String[] getDept = vo.getApprObjDept().split(",");
//
//            if (getUsr.length > 1) {
//                String name = getUsr[0] + " 외 " + (getUsr.length - 1) + " 건";
//                approvalList.get(i).setApprobjUser(name);
//            }
//            if (getDept.length > 1) {
//                String dept = getDept[0] + "외" + (getDept.length - 1) + "건 ";
//                approvalList.get(i).setApprObjDept(dept);
//            }
//        }
        //total count

        //menuId 갖고다니기
        String menuId = "0";
        ttObjParams.put("id", menuId);
        
//        model.addAttribute("apprCount", apprCount);
//        model.addAttribute("apprDoCount", apprDoCount);
//        model.addAttribute("ttObjParams", ttObjParams);
//        model.addAttribute("approvalList", approvalList);
        /*//나의결재함*/


        TTLog.info(":::   adHome End   :::", this.getClass());

        return "admin.main.home.tiles";
    }

}
