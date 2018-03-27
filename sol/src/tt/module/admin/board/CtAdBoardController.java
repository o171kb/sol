package tt.module.admin.board;

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
import tt.bean.VoBoard;
import tt.com.CoTtObjParams;
import tt.com.constant.CsCoConstDef;

/**
 * <pre>
 * tt.module.admin.board
 *    |_ CtAdBoardController.java
 *
 * DESC : 게시관리 Controller 클래스 <br />
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author ks-lee
 * @Date 2013. 3. 19. 오후 3:09:20
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 3. 19.		ks-lee				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
@Controller
public class CtAdBoardController extends TtBaseController {

    /**
     * 게시관리 검색 리스트 <br />
     * @param voBoard 모델파라메터
     * @param model 모델
     * @param session 세션
     * @param request 리퀘스트
     * @return to JSP
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adBoardSearchList.do")
    public String adBoardSearchList(@ModelAttribute("voBoard") VoBoard voBoard, ModelMap model, HttpSession session, HttpServletRequest request)
            throws Exception {
        TTLog.info(":::   adBoardSearchList Start   :::", this.getClass());

        // check login
        if (!isLogin(request)) {
            return "admin.login.tiles";
        }

        voBoard.initPaging();

        CoTtObjParams ttObjParams = new CoTtObjParams();
        ttObjParams.put("voBoard", voBoard);
        List<VoBoard> boardList = svBoardSearchService.getBoardList(ttObjParams);
        //total count
        int totCnt = svBoardSearchService.getBoardListTotCnt(ttObjParams);
        voBoard.setTotalRecordCount(totCnt);

        model.addAttribute("boardList", boardList);
        model.addAttribute("searchVoBoard", voBoard);

        //menuId 갖고다니기
        String menuId = "4";
        ttObjParams.put("id", menuId);
        model.addAttribute("ttObjParams", ttObjParams);

        TTLog.info(":::   adBoardSearchList End   :::", this.getClass());

        return "admin.board.search.list.tiles";
    }

    /**
     * 게시관리 검색 수정화면 <br />
     * @param voBoard 모델파라메터
     * @param model 모델
     * @param session 세션
     * @param request 리퀘스트
     * @return to JSP
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adBoardSearchModify.do")
    public String adBoardSearchModify(@ModelAttribute("voBoard") VoBoard voBoard, ModelMap model, HttpSession session, HttpServletRequest request)
            throws Exception {

        TTLog.info(":::   adBoardSearchModify Start   :::", this.getClass());

        //check login
        if (!isLogin(request)) {
            return "admin.login.tiles";
        }

        CoTtObjParams ttObjParams = new CoTtObjParams();
        ttObjParams.put("voBoard", voBoard);

        VoBoard vo = svBoardSearchService.getBoardDtl(ttObjParams);
        vo.setPageIndex(voBoard.getPageIndex());    //pageIndex 저장
        model.addAttribute("voBoard", vo);

        //menuId 갖고다니기
        String menuId = "4";
        ttObjParams.put("id", menuId);
        model.addAttribute("ttObjParams", ttObjParams);

        TTLog.info(":::   adBoardSearchModify End   :::", this.getClass());

        return "admin.board.search.modify.tiles";
    }

    /**
     * 게시관리 검색 수정 <br />
     * @param voBoard 모델파라메터
     * @param model 모델
     * @param session 세션
     * @param request 리퀘스트
     * @return to JSP
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adBoardSearchModifyAction.do")
    public String adBoardSearchModifyAction(@ModelAttribute("voBoard") VoBoard voBoard, ModelMap model, HttpSession session, HttpServletRequest request)
            throws Exception {

        TTLog.info(":::   adBoardSearchModifyAction Start   :::", this.getClass());

        //check login
        if (!isLogin(request)) {
            return "admin.login.tiles";
        }
        //로그인체크후 세션가져오기
        TtSession tts = getTtSession();
        String userId = (String) tts.get(CsCoConstDef.SS_KEY_003);

        CoTtObjParams ttObjParams = new CoTtObjParams();
        voBoard.setUserId(userId);

        ttObjParams.put("voBoard", voBoard);

        System.out.println("fileNm null??  : " + voBoard.getFileNm());
        System.out.println("fileUrl null?? : " + voBoard.getFileUrl());
        System.out.println("fileDelYn??    : " + voBoard.getFileDelYn());

        int result = svBoardSearchService.updateBoard(ttObjParams, request);
        if (result > 0) { voBoard.setCheckMessage("success_modi"); }

        model.addAttribute("voBoard", voBoard);

        //menuId 갖고다니기
        String menuId = "4";
        ttObjParams.put("id", menuId);
        model.addAttribute("ttObjParams", ttObjParams);

        TTLog.info(":::   adBoardSearchModifyAction End   :::", this.getClass());

        return "admin.board.search.modify.tiles";
    }

    /**
     * 게시관리 삭제 <br />
     * @param voBoard 모델파라메터
     * @param model 모델
     * @param session 세션
     * @param request 리퀘스트
     * @return to JSP
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adBoardSearchDelAction.do")
    public String adBoardSearchDelAction(@ModelAttribute("voBoard") VoBoard voBoard, ModelMap model, HttpSession session, HttpServletRequest request)
            throws Exception {

        TTLog.info(":::   adBoardSearchDelAction Start   :::", this.getClass());

        //check login
        if (!isLogin(request)) {
            return "admin.login.tiles";
        }

        CoTtObjParams ttObjParams = new CoTtObjParams();
        ttObjParams.put("voBoard", voBoard);

        svBoardSearchService.deleteBoard(ttObjParams);

        //menuId 갖고다니기
        String menuId = "4";
        ttObjParams.put("id", menuId);
        model.addAttribute("ttObjParams", ttObjParams);

        TTLog.info(":::   adBoardSearchDelAction End   :::", this.getClass());

        return "redirect: /adBoardSearchList.do";
    }

    /**
     * 게시관리 등록화면 <br />
     * @param voBoard 모델파라메터
     * @param model 모델
     * @param session 세션
     * @param request 리퀘스트
     * @return to JSP
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adBoardSearchWrite.do")
    public String adBoardSearchWrite(@ModelAttribute("voBoard") VoBoard voBoard, ModelMap model, HttpSession session, HttpServletRequest request)
            throws Exception {

        TTLog.info(":::   adBoardSearchWrite Start   :::", this.getClass());

        //check login
        if (!isLogin(request)) {
            return "admin.login.tiles";
        }

        model.addAttribute("voBoard", voBoard);
        CoTtObjParams ttObjParams = new CoTtObjParams();
        //menuId 갖고다니기
        String menuId = "4";
        ttObjParams.put("id", menuId);
        model.addAttribute("ttObjParams", ttObjParams);

        TTLog.info(":::   adBoardSearchWrite End   :::", this.getClass());

        return "admin.board.search.write.tiles";
    }

    /**
     * 게시관리 등록 <br />
     * @param voBoard 모델파라메터
     * @param model 모델
     * @param session 세션
     * @param request 리퀘스트
     * @return to JSP
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adBoardSearchWriteAction.do")
    public String adBoardSearchWriteAction(@ModelAttribute("voBoard") VoBoard voBoard, ModelMap model, HttpSession session, HttpServletRequest request)
            throws Exception {

        TTLog.info(":::   adBoardSearchWriteAction Start   :::", this.getClass());

        //check login
        if (!isLogin(request)) {
            return "admin.login.tiles";
        }

        //로그인체크후 세션가져오기
        TtSession tts = getTtSession();
        String userId = (String) tts.get(CsCoConstDef.SS_KEY_003);

        CoTtObjParams ttObjParams = new CoTtObjParams();
        voBoard.setUserId(userId);
        ttObjParams.put("voBoard", voBoard);

        svBoardSearchService.insertBoard(ttObjParams, request);
        voBoard.setCheckMessage("success_reg");

        //menuId 갖고다니기
        String menuId = "4";
        ttObjParams.put("id", menuId);
        model.addAttribute("ttObjParams", ttObjParams);

        model.addAttribute("voBoard", voBoard);

        TTLog.info(":::   adBoardSearchWriteAction End   :::", this.getClass());

        return "admin.board.search.write.tiles";
    }

}
