package tt.module.admin.mypage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
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

/**
 * <pre>
 * tt.module.admin.mypage
 *    |_ CtAdMyDraftController.java
 *
 * DESC : 나의결재함 Controller 클래스 <br />
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
public class CtAdMyApprovalController extends TtBaseController {

    /**
     * 나의결재함 <br />
     * @param voCoUser 관리자정보 vo
     * @param voApprInfo 예외신청리스트 vo
     * @param voPcBasic 예외처리대상PC vo
     * @param model 모델
     * @param session 세션
     * @param request 요청
     * @return 페이지경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adMyApprovalList.do")
    public String adMyApprovalList(VoCoUser voCoUser, VoApprInfo voApprInfo, VoPcBasic voPcBasic, ModelMap model, HttpSession session, HttpServletRequest request) throws Exception {

        TTLog.info(":::   adMyApprovalList Start   :::", this.getClass());

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
        
        if(voApprInfo.getTabDiv2() == null){
            voApprInfo.setTabDiv2("myApp");
        }
        
        voApprInfo.setDeptCd((String)tts.get("deptCd"));
        ttObjParams.put("voApprInfo", voApprInfo);
        
        List<VoApprInfo> list = svMyApprovalService.getMyApprovalList(ttObjParams);

        VoApprInfo vo = new VoApprInfo();
        for (int i = 0; i < list.size(); i++) {
            vo = list.get(i);
            String[] getUsr = vo.getApprobjUser().split(",");
            String[] getDept = vo.getApprObjDept().split(",");

            if (getUsr.length > 1) {
                String name = getUsr[0] + " 외 " + (getUsr.length - 1) + " 건";
                list.get(i).setApprobjUser(name);
            }
            if (getDept.length > 1) {
                String dept = getDept[0] + "외" + (getDept.length - 1) + "건 ";
                list.get(i).setApprObjDept(dept);
            }
        }

        //total count
        int totCnt = svMyApprovalService.getMyApprovalListTotCnt(ttObjParams);
        voApprInfo.setTotalRecordCount(totCnt);

        model.addAttribute("list", list);
        if(list.size() > 0){
            model.addAttribute("tabDiv2", list.get(0).getTabDiv2());
        }
        model.addAttribute("voApprInfo", voApprInfo);

        //menuId 갖고다니기
        String menuId = "20";
        ttObjParams.put("id", menuId);
        model.addAttribute("ttObjParams", ttObjParams);

        TTLog.info(":::   adMyApprovalList End   :::", this.getClass());

        return "admin.mypage.myapproval.list.tiles";
    }

    /**
     * @param voCoUser 모델파라메터
     * @param voApprInfo 모델파라메터
     * @param voPcBasic 모델파라메터
     * @param model 모델
     * @param session 세션
     * @param request 리퀘스트
     * @return 페이지경로
     * @throws Exception 예왼
     */
    @RequestMapping(value = "/adMyApprovalDownLoad.do")
    public String adMyApprovalDownLoad(@ModelAttribute("voCoUser") VoCoUser voCoUser, VoApprInfo voApprInfo, VoPcBasic voPcBasic,
            ModelMap model, HttpSession session, HttpServletRequest request) throws Exception {

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

        List<VoApprInfo> list = svMyApprovalService.getMyApprovalList(ttObjParams);

        VoApprInfo vo = new VoApprInfo();
        for (int i = 0; i < list.size(); i++) {
            vo = list.get(i);
            String[] getUsr = vo.getApprobjUser().split(",");

            if (getUsr.length > 1) {
                String name = getUsr[0] + " 외 " + (getUsr.length - 1) + " 건";
                list.get(i).setApprobjUser(name);
            }
        }


        //total count
        int totCnt = svMyApprovalService.getMyApprovalListTotCnt(ttObjParams);
        voApprInfo.setTotalRecordCount(totCnt);

        //menuId 갖고다니기
        String menuId = "20";
        ttObjParams.put("id", menuId);
        model.addAttribute("ttObjParams", ttObjParams);

        model.addAttribute("list", list);
        model.addAttribute("voApprInfo", voApprInfo);

        return "admin.mypage.approval.excel.list";
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
    @RequestMapping(value = "/adMyApprovalView.do")
    public String adMyDraftView(@ModelAttribute("voApprInfo") VoApprInfo voApprInfo, VoExctDraft voExctDraft, VoExct voExct, VoCoCodeDtl voCoCodeDtl, VoAddUser voAddUser,
            VoCoUser voCoUser, VoPcBasic voPcBasic, ModelMap model, HttpServletRequest request) throws Exception {

        TTLog.info(":::   adMyApprvalView Start   :::", this.getClass());

        //check login
        if (!isLogin(request)) {
            return "admin.login.tiles";
        }

        CoTtObjParams ttObjParams = new CoTtObjParams();
        ttObjParams.put("voApprInfo", voApprInfo);

        VoApprInfo vo = svMyDraftService.getMyDraftDetail(ttObjParams);     //신청항목
        vo.setPageIndex(voApprInfo.getPageIndex());
        model.addAttribute("voApprInfo", vo);

        TtSession tts = getTtSession();

        //session에서 get user date
        /*String getUserId = (String) tts.get(CsCoConstDef.SS_KEY_003); //결재자ID
        //String getUserId = voApprInfo.getAppId();
        ttObjParams.put("userId", getUserId);
        VoApprInfo tempUser = svMyApprovalService.getApprover(ttObjParams);     //결재자정보 (로그인자)
        voAddUser.setMadeCd(tempUser.getMadeCd());
        //session에서 get user date
        String getUserNm = (String) tts.get(CsCoConstDef.SS_KEY_019); //결재자이름
        tempUser.setUserNm(getUserNm);
        model.addAttribute("userDtl", tempUser);*/
        
        String getUserId = voApprInfo.getUserId(); //기안자 ID
        ttObjParams.put("userId", getUserId);
        
        VoApprInfo tempUser = svMyApprovalService.getApprover(ttObjParams);     //결재자정보 (로그인자)
        VoCoUser tempUser2 = svExctDraftService.getUserDtl(ttObjParams);     //기안자정보 (로그인자)
        model.addAttribute("userDtl", tempUser);
        model.addAttribute("userDtl2", tempUser2);

        List<VoApprInfo> approverList  = svMyDraftService.getApproverList(ttObjParams);     //결재자 정보
        model.addAttribute("approverList", approverList);

        VoCoUser drafterList  = svMyApprovalService.getDrafterDtl(ttObjParams);     //기안자 정보
        model.addAttribute("drafterList", drafterList);

        List<VoExctObjPc> approvalPcList = svMyDraftService.getApprovalPcList(ttObjParams);     //예외대상PC
        model.addAttribute("approvalPcList", approvalPcList);

        List<VoApprDetail> apprDetailList = svMyDraftService.getApprDetailList(ttObjParams);          //신청항목 리스트
        model.addAttribute("apprDetailList", apprDetailList);

        //menuId 갖고다니기
        String menuId = "20";
        ttObjParams.put("id", menuId);
        model.addAttribute("ttObjParams", ttObjParams);

        TTLog.info(":::   adMyApprvalView End   :::", this.getClass());
        
        System.out.println("vo.getExctAppId()::::::::::::::::::::::::"+ vo.getExctAppId());
        System.out.println("voApprInfo.getPermitGubun()()::::::::::::::::::::::::"+ voApprInfo.getPermitGubun());
        
        if(voApprInfo.getPermitGubun().equals("03")){
            System.out.println("admin.mypage.mydraft."+vo.getExctAppId()+".view");
            return "admin.mypage.mydraft." + vo.getExctAppId() + ".view.tiles";
        }else{
            System.out.println("admin.mypage.appr."+vo.getExctAppId()+".view");
            return "admin.mypage.appr." + vo.getExctAppId() + ".view.tiles";
       }
    }

    /**
     * 승인반려 <br />
     * @param voCoUser 파라메터
     * @param voApprInfo 파라메터
     * @param model 모델
     * @param session 세션
     * @param request 요청
     * @return 페이지 경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adMyEscortReject.do")
    public String adMyEscortReject(VoCoUser voCoUser, VoApprInfo voApprInfo, ModelMap model, HttpSession session, HttpServletRequest request) throws Exception {
        TTLog.info(":::   adMyEscortReject Start   :::", this.getClass());

      //check login
        if (!isLogin(request)) {
            return "admin.login.tiles";
        }

        CoTtObjParams ttObjParams = new CoTtObjParams();
        //session id
        TtSession tts = (TtSession) TtHttpSessionManager.getTtSession(request);
        String sessionId = (String) tts.get(CsCoConstDef.SS_KEY_003); //로그인자ID
        voApprInfo.setUserId(sessionId);
        ttObjParams.put("voApprInfo", voApprInfo);
        String checkMessage = voCoUser.getCheckMessage();
        System.out.println("checkMessage" + checkMessage);
        if (checkMessage.equals("success_appr")) {
            svMyApprovalService.rejectEscortRequest(ttObjParams);
        }
        voApprInfo.setCheckMessage(checkMessage);

        //menuId 갖고다니기
        String menuId = "20";
        ttObjParams.put("id", menuId);
        model.addAttribute("ttObjParams", ttObjParams);

        TTLog.info(":::   adMyEscortReject End   :::", this.getClass());

        return "admin.mypage.appr." + voApprInfo.getExctAppId() + ".view.tiles";
    }

    /**
     * Escort 결재승인 <br />
     * @param voCoUser 파라메터
     * @param voApprInfo 파라메터
     * @param model 모델
     * @param session 세션
     * @param request 요청
     * @return 페이지경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adMyEscortAppr.do")
    public String adMyEscortAppr(VoCoUser voCoUser, VoApprInfo voApprInfo, ModelMap model, HttpSession session, HttpServletRequest request) throws Exception {

        TTLog.info(":::   adMyEscortAppr Start   :::", this.getClass());

        //check login
        if (!isLogin(request)) {
            return "admin.login.tiles";
        }

        CoTtObjParams ttObjParams = new CoTtObjParams();
        //session id
        TtSession tts = (TtSession) TtHttpSessionManager.getTtSession(request);
        String sessionId = (String) tts.get(CsCoConstDef.SS_KEY_003); //로그인자ID
        voApprInfo.setUserId(sessionId);
        ttObjParams.put("voApprInfo", voApprInfo);
        String checkMessage = voCoUser.getCheckMessage();
        if (checkMessage.equals("success_appr")) {
            svMyApprovalService.apprEscortRequest(ttObjParams);
        }
        voApprInfo.setCheckMessage(checkMessage);

        //menuId 갖고다니기
        String menuId = "20";
        ttObjParams.put("id", menuId);
        model.addAttribute("ttObjParams", ttObjParams);

        TTLog.info(":::   adMyEscortAppr End   :::", this.getClass());

        return "admin.mypage.appr." + voApprInfo.getExctAppId() + ".view.tiles";
    }

    /**
     * 첨부통제 결재승인 <br />
     * @param voCoUser 파라메터
     * @param voApprInfo 파라메터
     * @param model 모델
     * @param session 세션
     * @param request 요청
     * @return 페이지 경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adMyAttachAppr.do")
    public String adMyAttachAppr(VoCoUser voCoUser, VoApprInfo voApprInfo, ModelMap model, HttpSession session, HttpServletRequest request) throws Exception {

        TTLog.info(":::   adMyAttachAppr Start   :::", this.getClass());

        //check login
        if (!isLogin(request)) {
            return "admin.login.tiles";
        }

        CoTtObjParams ttObjParams = new CoTtObjParams();
        //session id
        TtSession tts = (TtSession) TtHttpSessionManager.getTtSession(request);
        String sessionId = (String) tts.get(CsCoConstDef.SS_KEY_003); //로그인자ID
        voApprInfo.setUserId(sessionId);
        ttObjParams.put("voApprInfo", voApprInfo);
        String checkMessage = voCoUser.getCheckMessage();
        if (checkMessage.equals("success_appr")) {
            svMyApprovalService.apprAttachRequest(ttObjParams);
        }
        voApprInfo.setCheckMessage(checkMessage);

        //menuId 갖고다니기
        String menuId = "20";
        ttObjParams.put("id", menuId);
        model.addAttribute("ttObjParams", ttObjParams);

        TTLog.info(":::   adMyAttachAppr End   :::", this.getClass());

        return "admin.mypage.appr." + voApprInfo.getExctAppId() + ".view.tiles";
    }


    /**
     * 출력보안 결재승인 <br />
     * @param voCoUser 파라메터
     * @param voApprInfo 파라메터
     * @param model 모델
     * @param session 세션
     * @param request 요청
     * @return 페이지 경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adMyOutputAppr.do")
    public String adMyOutputAppr(VoCoUser voCoUser, VoApprInfo voApprInfo, ModelMap model, HttpSession session, HttpServletRequest request) throws Exception {

        TTLog.info(":::   adMyOutputAppr Start   :::", this.getClass());

        //check login
        if (!isLogin(request)) {
            return "admin.login.tiles";
        }

        CoTtObjParams ttObjParams = new CoTtObjParams();
        //session id
        TtSession tts = (TtSession) TtHttpSessionManager.getTtSession(request);
        String sessionId = (String) tts.get(CsCoConstDef.SS_KEY_003); //로그인자ID
        voApprInfo.setUserId(sessionId);
        ttObjParams.put("voApprInfo", voApprInfo);
        String checkMessage = voCoUser.getCheckMessage();
        if (checkMessage.equals("success_appr")) {
            svMyApprovalService.apprOutputRequest(ttObjParams);
        }
        voApprInfo.setCheckMessage(checkMessage);

        //menuId 갖고다니기
        String menuId = "20";
        ttObjParams.put("id", menuId);
        model.addAttribute("ttObjParams", ttObjParams);

        TTLog.info(":::   adMyOutputAppr End   :::", this.getClass());

        return "admin.mypage.appr." + voApprInfo.getExctAppId() + ".view.tiles";
    }




    /**
     * 사이트예외신청 <br />
     * @param voCoUser 파라메터
     * @param voApprInfo 파라메터
     * @param voApprDetail 파라메터
     * @param model 모델
     * @param session 세션
     * @param request 요청
     * @return 페이지 경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adMySiteAppr.do")
    public String adMySiteAppr(VoCoUser voCoUser, VoApprInfo voApprInfo, VoApprDetail voApprDetail, ModelMap model, HttpSession session, HttpServletRequest request) throws Exception {

        TTLog.info(":::   adMySiteAppr Start   :::", this.getClass());

        //check login
        if (!isLogin(request)) {
            return "admin.login.tiles";
        }

        CoTtObjParams ttObjParams = new CoTtObjParams();
        //session id
        TtSession tts = (TtSession) TtHttpSessionManager.getTtSession(request);
        String sessionId = (String) tts.get(CsCoConstDef.SS_KEY_003); //로그인자ID
        voApprInfo.setUserId(sessionId);
        ttObjParams.put("voApprInfo", voApprInfo);
        ttObjParams.put("voApprDetail", voApprDetail);
        String checkMessage = voCoUser.getCheckMessage();
        if (checkMessage.equals("success_appr")) {
            svMyApprovalService.apprSiteRequest(ttObjParams);
        }
        voApprInfo.setCheckMessage(checkMessage);

        //menuId 갖고다니기
        String menuId = "20";
        ttObjParams.put("id", menuId);
        model.addAttribute("ttObjParams", ttObjParams);

        TTLog.info(":::   adMySiteAppr End   :::", this.getClass());

        return "admin.mypage.appr." + voApprInfo.getExctAppId() + ".view.tiles";
    }
    
    /*@RequestMapping(value = "/sendPacket.do")
    public String sendPacket(VoCoUser voCoUser, VoApprInfo voApprInfo, ModelMap model, HttpSession session, HttpServletRequest request) throws Exception {
        
        if (!isLogin(request)) {
            return "admin.login.tiles";
        }
        
        System.out.println("sendPacket.do  start");
        int port = 15000;
        String[] prefix = {"[WR-BL]", "[VSXXD]", "[VS-OS]",  "[2WRP2]",  "[VS-FL]",  "[WR-PP]", 
               "[2WRPP]", "[VS-SW]", "[PCEQA]", "[VS-QP]", "[VSCST]", "[VSMNU]", "[2WQPP]", 
               "[VSAQR]", "[PC-XU]",  "[pc-fw]",  "[VSXFL]", "[PC-BI]", "[PC-SD]",  "[2WRPS]", 
               "[PC-VR]",  "[PC-HD]",  "[IG-QH]",  "[VS-NL]", "[PC-IX]", "[VS-AQ]", "[VSUHQ]", 
               "[PC-DR]",  "[PCAQP]", "[PC-K8]",  "[PCXFL]",  "[VS-BR]",  "[2WRPE]", "[WR-PS]", 
               "[PC-OI]",  "[2WRP3]", "[VS-UL]",   "[VS-XD]",  "[VSAEQ]",  "[2WRBL]",  "[PC-XC]", 
               "[PCXON]",  "[PC-CD]",  "[VS-QE]",  "[VS-DQ]",  "[PC-QL]",   "[PC-MT]",  "[PCP01]", 
               "[VS-MM]",  "[VS-HV]",  "[PC-S3]", "[pc-sr]",  "[PC-CS]",  "[PC-S2]",   "[PC-HS]", 
               "[PC-QT]",  "[VS-HW]",  "[PC-IF]",  "[PC-FL]",  "[VSRUQ]",  "[2WRP1]",  "[VS-PT]", 
               "[PCXCD]",   "[VS-OP]"};
       
       String serial = "A0002522E730C4";




       String sendMsg = "";
        for(int i=0;i<prefix.length;i++)
        {
            sendMsg = prefix[i] + serial + ";";
            byte ba[] = sendMsg.getBytes("utf-8");
            
            InetAddress inet = InetAddress.getByName("192.168.1.156");        
            //InetAddress inet = InetAddress.getByName("192.168.1.174");
            
            DatagramPacket dpacket = new DatagramPacket(ba, ba.length, inet, port);
            
            DatagramSocket ds = new DatagramSocket();
            ds.send(dpacket);
            
            ds.close();
            ds.disconnect();
            System.out.println("sendPacket.do  end : " + dpacket.toString());
        }
        System.out.println("prefix.length :::::::::::::::::::::::::::: " + prefix.length);
        return "redirect:/adHome.do";
    }*/
    
    @RequestMapping(value = "/sendPacket.do")
    public String sendPacket(VoCoUser voCoUser, VoApprInfo voApprInfo, ModelMap model, HttpSession session, HttpServletRequest request) throws Exception {
        
        if (!isLogin(request)) {
            return "admin.login.tiles";
        }
        
        System.out.println("sendPacket.do  start");
        /*int[] port = {15002,15011, 15000,  15013, 15006, 15001, 15003,  15026,  15015, 15007, 15005, 15022, 15010,  15000,   15012};*/
        int port = 15010;
        
       /* String[] prefix = {"[WR-BL]", "[VSXXD]", "[VS-OS]",  "[2WRP2]",  "[VS-FL]",  "[WR-PP]", 
                "[2WRPP]", "[VS-SW]", "[PCEQA]", "[VS-QP]", "[VSCST]", "[VSMNU]", "[2WQPP]", 
                "[VSAQR]", "[PC-XU]",  "[pc-fw]",  "[VSXFL]", "[PC-BI]", "[PC-SD]",  "[2WRPS]", 
                "[PC-VR]",  "[PC-HD]",  "[IG-QH]",  "[VS-NL]", "[PC-IX]", "[VS-AQ]", "[VSUHQ]", 
                "[PC-DR]",  "[PCAQP]", "[PC-K8]",  "[PCXFL]",  "[VS-BR]",  "[2WRPE]", "[WR-PS]", 
                "[PC-OI]",  "[2WRP3]", "[VS-UL]",   "[VS-XD]",  "[VSAEQ]",  "[2WRBL]",  "[PC-XC]", 
                "[PCXON]",  "[PC-CD]",  "[VS-QE]",  "[VS-DQ]",  "[PC-QL]",   "[PC-MT]",  "[PCP01]", 
                "[VS-MM]",  "[VS-HV]",  "[PC-S3]", "[pc-sr]",  "[PC-CS]",  "[PC-S2]",   "[PC-HS]", 
                "[PC-QT]",  "[VS-HW]",  "[PC-IF]",  "[PC-FL]",  "[VSRUQ]",  "[2WRP1]",  "[VS-PT]", 
                "[PCXCD]",   "[VS-OP]"};*/
        
        String prefix = "[WR-PP]";
        String serial = "A050B7C38FF3E5";
        String sendMsg = "";
                sendMsg = prefix + serial + ";";
                
                byte ba[] = sendMsg.getBytes("utf-8");
                
                InetAddress inet = InetAddress.getByName("192.168.1.60");        
                //InetAddress inet = InetAddress.getByName("192.168.1.174");
                
                DatagramPacket dpacket = new DatagramPacket(ba, ba.length, inet, port);
                
                DatagramSocket ds = new DatagramSocket();
                ds.send(dpacket);
                
                ds.close();
                 ds.disconnect();
                
                /*Runtime rt = Runtime.getRuntime();
                Process p;
                try {
                  p = rt.exec("cmd /c C:/SendUDPPacket.exe 192.168.1.60 " + port[k] + " " + sendMsg +" > C:/logs/results"+i+".txt");
                  InputStream in = p.getInputStream();
                  InputStreamReader isr = new InputStreamReader(in);
                  //System.out.println(isr.getEncoding());
                  BufferedReader br = new BufferedReader(isr);
                  String line;
                  while ((line = br.readLine()) != null) {
                    System.out.println("zzzzzzzzzzzzzzzzzzzzz : " + line);
                  }
                  in.close();
                }
                catch (IOException e) {
                  e.printStackTrace();
                }*/
    
    
    
                System.out.println("sendPacket.do  end : " + sendMsg);
            
        
        
        
        /*byte[]buf = new byte[256];
        
        DatagramSocket datagramSocket = new DatagramSocket(5000);
        DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length);
        datagramSocket.receive(datagramPacket);*/
        return "redirect:/adHome.do";
    }
}
