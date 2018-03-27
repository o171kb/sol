package tt.module.admin.customer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import tt.TTLog;
import tt.base.module.TtBaseController;
import tt.bean.VoBoard;
import tt.com.CoTtObjParams;

/**
 * <pre>
 * tt.module.admin.customer
 *    |_ CtAdNoticeController.java
 *
 * DESC : 공지사항 Controller 클래스 <br />
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author ks-lee
 * @Date 2013. 3. 19. 오후 2:00:38
 * @history :
 *          --------------------------------------------------------------------
 *          --- 변경일 작성자 변경내용 ----------- -------------------
 *          --------------------------------------- 2013. 3. 19. ks-lee 최초 작성
 *          ----
 *          -------------------------------------------------------------------
 *
 */

@Controller
public class CtAdCustomerController extends TtBaseController {

    /**
     * 고객센터 공지사항 리스트 <br/>
     * @param voBoard 모델파라메터
     * @param model 모델
     * @param session 세션
     * @param request 리퀘스트
     * @return to JSP
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adNoticeList.do")
    public String adNoticeList(@ModelAttribute("voBoard") VoBoard voBoard, ModelMap model, HttpSession session, HttpServletRequest request)
            throws Exception {

        TTLog.info(":::   adNoticeList Start   :::", this.getClass());

        // check login
        if (!isLogin(request)) {
            return "admin.login.tiles";
        }

        voBoard.initPaging();
        voBoard.setBoardTp("01");   //Notice

        CoTtObjParams ttObjParams = new CoTtObjParams();
        ttObjParams.put("voBoard", voBoard);

        List<VoBoard> noticeList = svCustomerService.getCustomerList(ttObjParams);
        //total count
        int totCnt = svCustomerService.getCustomerListTotCnt(ttObjParams);
        voBoard.setTotalRecordCount(totCnt);

        model.addAttribute("noticeList", noticeList);
        model.addAttribute("searchVoBoard", voBoard);

        //menuId 갖고다니기
        String menuId = "15";
        ttObjParams.put("id", menuId);
        model.addAttribute("ttObjParams", ttObjParams);

        TTLog.info(":::   adNoticeList End   :::", this.getClass());

        return "admin.customer.notice.list.tiles";
    }

    /**
     * 고객센터 공지사항 상세화면 <br/>
     * @param voBoard 모델파라메터
     * @param model 모델
     * @param session 세션
     * @param request 리퀘스트
     * @return to JSP
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adNoticeView.do")
    public String adNoticeView(@ModelAttribute("voBoard") VoBoard voBoard, ModelMap model, HttpSession session, HttpServletRequest request)
            throws Exception {

        TTLog.info(":::   adNoticeView Start   :::", this.getClass());

        //check login
        if (!isLogin(request)) {
            return "admin.login.tiles";
        }

        voBoard.setBoardTp("01");   //Notice

        CoTtObjParams ttObjParams = new CoTtObjParams();
        ttObjParams.put("voBoard", voBoard);

        VoBoard vo = svCustomerService.getCustomerDtl(ttObjParams);
        vo.setPageIndex(voBoard.getPageIndex());    //pageIndex 저장
        model.addAttribute("voBoard", vo);

        //menuId 갖고다니기
        String menuId = "15";
        ttObjParams.put("id", menuId);
        model.addAttribute("ttObjParams", ttObjParams);

        TTLog.info(":::   adNoticeView End   :::", this.getClass());

        return "admin.customer.notice.view.tiles";
    }

    /**
     * FAQ 리스트<br />
     * @param voBoard 모델파라메터
     * @param model 모델
     * @param session 세션
     * @param request 리퀘스트
     * @return 페이지경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adFaqList.do")
    public String adFaqList(@ModelAttribute("voBoard") VoBoard voBoard, ModelMap model, HttpSession session, HttpServletRequest request)
            throws Exception {

        TTLog.info(":::   adFaqList Start   :::", this.getClass());

     // check login
        if (!isLogin(request)) {
            return "admin.login.tiles";
        }

        voBoard.initPaging();
        voBoard.setBoardTp("02");   //Faq

        CoTtObjParams ttObjParams = new CoTtObjParams();
        ttObjParams.put("voBoard", voBoard);

        List<VoBoard> faqList = svCustomerService.getCustomerList(ttObjParams);
        //total count
        int totCnt = svCustomerService.getCustomerListTotCnt(ttObjParams);
        voBoard.setTotalRecordCount(totCnt);

        model.addAttribute("faqList", faqList);
        model.addAttribute("searchVoBoard", voBoard);

        //menuId 갖고다니기
        String menuId = "16";
        ttObjParams.put("id", menuId);
        model.addAttribute("ttObjParams", ttObjParams);

        TTLog.info(":::   adFaqList End   :::", this.getClass());

        return "admin.customer.faq.list.tiles";
    }

    /**
     * FAQ 상세화면 <br />
     * @param voBoard 모델파라메터
     * @param model 모델
     * @param session 세션
     * @param request 리퀘스트
     * @return 페이지경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adFaqView.do")
    public String adFaqView(@ModelAttribute("voBoard") VoBoard voBoard, ModelMap model, HttpSession session, HttpServletRequest request)
            throws Exception {

        TTLog.info(":::   adFaqView Start   :::", this.getClass());

      //check login
        if (!isLogin(request)) {
            return "admin.login.tiles";
        }

        voBoard.setBoardTp("02");   //Faq

        CoTtObjParams ttObjParams = new CoTtObjParams();
        ttObjParams.put("voBoard", voBoard);

        VoBoard vo = svCustomerService.getCustomerDtl(ttObjParams);
        vo.setPageIndex(voBoard.getPageIndex());    //pageIndex 저장
        model.addAttribute("voBoard", vo);

        //menuId 갖고다니기
        String menuId = "16";
        ttObjParams.put("id", menuId);
        model.addAttribute("ttObjParams", ttObjParams);

        TTLog.info(":::   adFaqView End   :::", this.getClass());

        return "admin.customer.faq.view.tiles";
    }

    /**
     * 자료실 리스트<br />
     *
     * @param voBoard 모델파라메터
     * @param model 모델
     * @param session 세션
     * @param request 리퀘스트
     * @return 페이지경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adDataList.do")
    public String adDataList(@ModelAttribute("voBoard") VoBoard voBoard, ModelMap model, HttpSession session, HttpServletRequest request)
            throws Exception {
        TTLog.info(":::   adDataList Start   :::", this.getClass());

     // check login
        if (!isLogin(request)) {
            return "admin.login.tiles";
        }

        voBoard.initPaging();
        voBoard.setBoardTp("03");   //Data

        CoTtObjParams ttObjParams = new CoTtObjParams();
        ttObjParams.put("voBoard", voBoard);

        List<VoBoard> dataList = svCustomerService.getCustomerList(ttObjParams);
        //total count
        int totCnt = svCustomerService.getCustomerListTotCnt(ttObjParams);
        voBoard.setTotalRecordCount(totCnt);

        model.addAttribute("dataList", dataList);
        model.addAttribute("searchVoBoard", voBoard);

        //menuId 갖고다니기
        String menuId = "17";
        ttObjParams.put("id", menuId);
        model.addAttribute("ttObjParams", ttObjParams);

        TTLog.info(":::   adDataList End   :::", this.getClass());

        return "admin.customer.data.list.tiles";
    }

    /**
     * 자료실 상세화면 <br />
     * @param voBoard 모델파라메터
     * @param model 모델
     * @param session 세션
     * @param request 리퀘스트
     * @return 페이지경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adDataView.do")
    public String adDataView(@ModelAttribute("voBoard") VoBoard voBoard, ModelMap model, HttpSession session, HttpServletRequest request)
            throws Exception {

        TTLog.info(":::   adDataView Start   :::", this.getClass());

      //check login
        if (!isLogin(request)) {
            return "admin.login.tiles";
        }

        voBoard.setBoardTp("03");   //Data

        CoTtObjParams ttObjParams = new CoTtObjParams();
        ttObjParams.put("voBoard", voBoard);

        VoBoard vo = svCustomerService.getCustomerDtl(ttObjParams);
        vo.setPageIndex(voBoard.getPageIndex());    //pageIndex 저장
        model.addAttribute("voBoard", vo);

        //menuId 갖고다니기
        String menuId = "17";
        ttObjParams.put("id", menuId);
        model.addAttribute("ttObjParams", ttObjParams);

        TTLog.info(":::   adDataView End   :::", this.getClass());

        return "admin.customer.data.view.tiles";
    }

}
