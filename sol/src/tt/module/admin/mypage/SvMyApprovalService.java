package tt.module.admin.mypage;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tt.base.module.TtBaseService;
import tt.bean.VoApprDetail;
import tt.bean.VoApprInfo;
import tt.bean.VoExctObjPc;
import tt.com.CoTtObjParams;
import tt.com.bean.VoCoUser;
import tt.com.utils.UtCoDateUtils;
import tt.com.utils.UtCoIOUtils;
import tt.com.utils.UtCoStringUtils;


/**
 * <pre>
 * tt.module.admin.mypage
 *    |_ SvMyDraftService.java
 *
 * DESC :
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author hk-kim
 * @Date 2013. 4. 15. 오후 7:43:26
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 4. 15.		hk-kim				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
@Service("svMyApprovalService")
public class SvMyApprovalService extends TtBaseService  {

    /**
     * 나의결재현황 <br />
     * @param ttObjParams 나의결재현황정보
     * @return 나의결재현황list
     */
    public List<VoApprInfo> getMyApprovalList(CoTtObjParams ttObjParams) {

        VoApprInfo voApprInfo = (VoApprInfo) ttObjParams.get("voApprInfo");

        //페이징파라미터설정
        CoTtObjParams ttObjParamsSql = getParamsObjOfPageInfoSetted(voApprInfo.getFirstIndex(), voApprInfo.getRecordCountPerPage());
        ttObjParamsSql.put("userId", UtCoStringUtils.trim(voApprInfo.getUserId()));
        ttObjParamsSql.put("searchStartDm", voApprInfo.getSearchStartDm());
        ttObjParamsSql.put("searchEndDm", voApprInfo.getSearchEndDm());
        ttObjParamsSql.put("searchStatus", voApprInfo.getSearchStatus());
        ttObjParamsSql.put("searchGubun", voApprInfo.getSearchGubun());
        ttObjParamsSql.put("searchUserNm", voApprInfo.getSearchUserNm());
        ttObjParamsSql.put("searchDeptNm", voApprInfo.getDeptNm());
        ttObjParamsSql.put("deptCd", voApprInfo.getDeptCd());
        
        ttObjParamsSql.put("tabDiv2", voApprInfo.getTabDiv2());
        
        System.out.println("ttObjParamsSql.get(tabDiv2)::::::::::::::::::::::::::::::"+ttObjParamsSql.get("tabDiv2"));
        
        if(ttObjParamsSql.get("tabDiv2") == null){
            ttObjParamsSql.put("tabDiv2", "myApp");
        }
        
        if(ttObjParamsSql.get("tabDiv2").equals("deptApp"))
        {
            ttObjParamsSql.put("deptCd", ttObjParamsSql.get("deptCd"));
            List<VoApprInfo> lowDeptList = daoMyApproval.getLowDeptList(ttObjParamsSql);
            List<VoApprInfo> resultDeptList = new ArrayList();
            
            VoApprInfo mydeptVO = new VoApprInfo();
            mydeptVO.setDeptCd((String)ttObjParamsSql.get("deptCd"));
            
            resultDeptList.add(mydeptVO);
            
            Map paramMap = new HashMap();
            paramMap.put("deptList", lowDeptList);
            if(lowDeptList != null)
            {
                resultDeptList.addAll(lowDeptList);
            }
            
            List<VoApprInfo> tempList = new ArrayList<VoApprInfo>();
            
            while(tempList != null)
            {
                // list 파라미터 쿼리
                tempList = daoMyApproval.getLowOrDeptList(paramMap);
                if(tempList.size() < 1)
                {
                    break;
                }
                else
                {
                    resultDeptList.addAll(tempList);
                    paramMap.put("deptList", tempList);
                }
            }
            return daoMyApproval.getMyApprovalList(ttObjParamsSql, resultDeptList);
        }
        
        System.out.println("ttObjParamsSql.get(tabDiv2)::::::::::::::::::::::::::::::"+ttObjParamsSql.get("tabDiv2"));
        
        return daoMyApproval.getMyApprovalList(ttObjParamsSql);
    }

    /**
     * 나의결재현황 총 갯수 취득 <br />
     * @param ttObjParams 나의결재현황정보
     * @return 토탈카운트
     */
    public int getMyApprovalListTotCnt(CoTtObjParams ttObjParams) {

        VoApprInfo voApprInfo = (VoApprInfo) ttObjParams.get("voApprInfo");

        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        ttObjParamsSql.put("userId", UtCoStringUtils.trim(voApprInfo.getUserId()));
        ttObjParamsSql.put("searchStartDm", voApprInfo.getSearchStartDm());
        ttObjParamsSql.put("searchEndDm", voApprInfo.getSearchEndDm());
        ttObjParamsSql.put("searchStatus", voApprInfo.getSearchStatus());
        ttObjParamsSql.put("searchGubun", voApprInfo.getSearchGubun());
        ttObjParamsSql.put("searchUserNm", voApprInfo.getSearchUserNm());
        ttObjParamsSql.put("searchDeptNm", voApprInfo.getDeptNm());
        ttObjParamsSql.put("deptCd", voApprInfo.getDeptCd());
        
        ttObjParamsSql.put("tabDiv2", voApprInfo.getTabDiv2());
        
        if(ttObjParamsSql.get("tabDiv2") == "" || ttObjParamsSql.get("tabDiv2") == null){
            ttObjParamsSql.put("tabDiv2", "myApp");
        }
        
        if(ttObjParamsSql.get("tabDiv2").equals("deptApp"))
        {
            ttObjParamsSql.put("deptCd", ttObjParamsSql.get("deptCd"));
            List<VoApprInfo> lowDeptList = daoMyApproval.getLowDeptList(ttObjParamsSql);
            List<VoApprInfo> resultDeptList = new ArrayList();
            
            VoApprInfo mydeptVO = new VoApprInfo();
            mydeptVO.setDeptCd((String)ttObjParamsSql.get("deptCd"));
            
            resultDeptList.add(mydeptVO);
            
            Map paramMap = new HashMap();
            paramMap.put("deptList", lowDeptList);
            if(lowDeptList != null)
            {
                resultDeptList.addAll(lowDeptList);
            }
            
            List<VoApprInfo> tempList = new ArrayList<VoApprInfo>();
            
            while(tempList != null)
            {
                // list 파라미터 쿼리
                tempList = daoMyApproval.getLowOrDeptList(paramMap);
                if(tempList.size() < 1)
                {
                    break;
                }
                else
                {
                    resultDeptList.addAll(tempList);
                    paramMap.put("deptList", tempList);
                }
            }
            return daoMyApproval.getMyApprovalListTotCnt(ttObjParamsSql, resultDeptList);
        }
        
        return daoMyApproval.getMyApprovalListTotCnt(ttObjParamsSql);
    }


    /**
     * 나의결재현황 상세 <br />
     * @param ttObjParams 나의결재현황정보
     * @return 나의결재현황 상세화면
     */
    public VoApprInfo getMyDraftDetail(CoTtObjParams ttObjParams) {
        VoApprInfo vo = (VoApprInfo) ttObjParams.get("voApprInfo");
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        ttObjParamsSql.put("apprId", UtCoStringUtils.trim(vo.getApprId()));

        return daoMyDraft.getMyDraftDetail(ttObjParamsSql);
    }

    /**
     * @param ttObjParams 파라메터
     * @return 기안자 상세정보
     */
    public VoCoUser getDrafterDtl(CoTtObjParams ttObjParams) {
        VoApprInfo vo = (VoApprInfo) ttObjParams.get("voApprInfo");
        ttObjParams.put("apprId", UtCoStringUtils.trim(vo.getApprId()));
        return daoMyApproval.getDrafterDtl(ttObjParams);
    }

    /**
     * @param ttObjParams 파라메터
     * @return 결재자 상세정보
     * @throws Exception 예외
     */
    public VoApprInfo getApprover(CoTtObjParams ttObjParams) throws Exception {
        VoApprInfo vo = (VoApprInfo) ttObjParams.get("voApprInfo");
        ttObjParams.put("apprId", UtCoStringUtils.trim(vo.getApprId()));
        ttObjParams.put("cdNo", "003");
        return daoMyApproval.getApprover(ttObjParams);
    }


    /**
     * @param ttObjParams 결재반려 파라메터
     * @throws Exception 예외
     */
    @Transactional
    public void rejectEscortRequest(CoTtObjParams ttObjParams) throws Exception {
        VoApprInfo voApprInfo = (VoApprInfo) ttObjParams.get("voApprInfo");
        String status = "03";
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        ttObjParamsSql.put("apprId", voApprInfo.getApprId());
        ttObjParamsSql.put("userId", voApprInfo.getUserId());
        ttObjParamsSql.put("status", status);
        ttObjParamsSql.put("resultReason", voApprInfo.getResultReason());
        daoMyApproval.rejectRequestApprInfo(ttObjParamsSql); //예외신청정보 결재 상태업데이트
        daoMyApproval.rejectRequestApprover(ttObjParamsSql); //결재자 결재상태 업데이트
    }

    /**
     * @param ttObjParams 결재승인 파라메터
     * @throws Exception 예외
     */
    @Transactional
    public void apprEscortRequest(CoTtObjParams ttObjParams) throws Exception {
        VoApprInfo voApprInfo = (VoApprInfo) ttObjParams.get("voApprInfo");
        String status = "01";
        String nextStatus = "02";
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        ttObjParamsSql.put("apprId", voApprInfo.getApprId());
        ttObjParamsSql.put("userId", voApprInfo.getUserId());
        ttObjParamsSql.put("status", status);
        ttObjParamsSql.put("permitGubun", voApprInfo.getPermitGubun());
        ttObjParamsSql.put("resultReason", voApprInfo.getResultReason());
        String expDm = UtCoDateUtils.getCurrentDateAsString(UtCoDateUtils.DATE_HMS_PATTERN); //yyyyMMddHHmmss
        ttObjParamsSql.put("expDm", expDm);

        List<VoApprInfo> approverList =  daoMyApproval.selectApproverList(ttObjParamsSql); //결재자 리스트
        int turnNo = Integer.parseInt(voApprInfo.getTurnNo());
        int j = turnNo;
        int k = turnNo + 1;
        String serial = "";
        ttObjParamsSql.put("turnNo", j);
        daoMyApproval.updateApporvoerStatus(ttObjParamsSql);
        outer: for (int i = 0; i < approverList.size() - turnNo + 1; i++) {
                    if (approverList.size() == j) {
                        VoApprInfo approvalInfo =  daoMyApproval.selectApprovalInfo(ttObjParamsSql); //결재정보
                        List<VoApprDetail> apprDtlList =  daoMyApproval.selectApprDtlList(ttObjParamsSql); //결재항목 리스트
                        List<VoExctObjPc> apprObjPcList =  daoMyApproval.selectApprObjPcList(ttObjParamsSql); //결재대상 리스트
                        ttObjParamsSql.put("startDm", approvalInfo.getStartDm());
                        ttObjParamsSql.put("endDm", approvalInfo.getEndDm());
                        ttObjParamsSql.put("reason", approvalInfo.getReason());
                        
                        for (int l = 0; l < apprObjPcList.size(); l++) {
                            ttObjParamsSql.put("serial", apprObjPcList.get(l).getSerial());
                            ttObjParamsSql.put("empNo", apprObjPcList.get(l).getUserId());
                            for (int m = 0; m < apprDtlList.size(); m++) {
                                ttObjParamsSql.put("gubun", apprDtlList.get(m).getGubun());
                                if (apprDtlList.get(m).getProGubun().equals("EXCEPTION")) {
                                    ttObjParamsSql.put("allowType", "0");
                                    ttObjParamsSql.put("smartPhoneExtend", "0");
                                    ttObjParamsSql.put("grpGubun", "P");
                                    daoMyApproval.callProcedureApprException(ttObjParamsSql);
                                } else if (apprDtlList.get(m).getProGubun().equals("STORERULE")) {
                                    ttObjParamsSql.put("allowType", "A");
                                    ttObjParamsSql.put("allowLog", "L");
                                    ttObjParamsSql.put("smartPhoneExtend", "0");
                                    ttObjParamsSql.put("grpGubun", "P");
                                    daoMyApproval.callProcedureApprStorerule(ttObjParamsSql);
                                } else if (apprDtlList.get(m).getProGubun().equals("EXCEPTIONAP")) { //등록AP
                                    ttObjParamsSql.put("allowType", "A");
                                    ttObjParamsSql.put("allowLog", "L");
                                    ttObjParamsSql.put("priority", "L");
                                    daoMyApproval.callProcedureApprExceptionAP(ttObjParamsSql);
                                } else if (apprDtlList.get(m).getProGubun().equals("ADDHDD")) { //추가 HDD
                                    int chkSameADDHDD = daoIncops5.selectChkSameADDHDD(ttObjParamsSql);
                                    if (chkSameADDHDD > 0) {
                                        daoIncops5.updateApprExceptionADDHDD(ttObjParamsSql);
                                    } else {
                                        daoIncops5.insertApprExceptionADDHDD(ttObjParamsSql);
                                    }
                                } else if (apprDtlList.get(m).getProGubun().equals("EXCEPTIONVPN")) { //반출잠김
                                    int chkSameExceptionVPN = daoIncops5.selectChkSameExceptionVPN(ttObjParamsSql);
                                    ttObjParamsSql.put("grpGubun", "P");
                                    if (chkSameExceptionVPN > 0) {
                                        daoIncops5.updateApprExceptionVPN(ttObjParamsSql);
                                    } else {
                                        daoIncops5.insertApprExceptionVPN(ttObjParamsSql);
                                    }
                                }
                                    //분기할 프로시저 추가부분
                            }
                            
                            // UDP 통신
                            System.out.println("sendPacket.do  start");
                            int port = 15010;
                            /*String[] prefix = {"[WR-BL]", "[VSXXD]", "[VS-OS]",  "[2WRP2]",  "[VS-FL]",  "[WR-PP]", 
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
                           
                            //String serial = "A00017A4DD1FD7";
                            serial = apprObjPcList.get(l).getSerial();
                            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$ serial : " + serial);
                            String sendMsg = "";
                           // for(int m=0;m<prefix.length;m++)
                           // {
                                sendMsg = prefix + serial + ";";
                                
                                byte ba[] = sendMsg.getBytes("utf-8");
                                
                                InetAddress inet = InetAddress.getByName("192.168.1.60");        
                                //InetAddress inet = InetAddress.getByName("192.168.1.174");
                                
                                DatagramPacket dpacket = new DatagramPacket(ba, ba.length, inet, port);
                                
                                DatagramSocket ds = new DatagramSocket();
                                ds.send(dpacket);
                                
                                ds.close();
                                ds.disconnect();
                                
                               /* Runtime rt = Runtime.getRuntime();
                                Process p;
                                try {
                                  p = rt.exec("cmd /c C:/SendUDPPacket.exe 192.168.1.60 15010 "+ sendMsg +" > C:/logs/results"+m+".txt");
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
                       }
                       daoMyApproval.updateApprInfoStatus(ttObjParamsSql); //결재정보 상태 업데이트
                       
                    
                           
                        // 최종 승인 이메일 발송
                           String toMailAddr = "";
                           String fromMailAddr = "";
                           String toUser = "";
                           String fromUser = "";
                           
                           //UtCoIOUtils.sendMail(toMailAddr, fromMailAddr, toUser, fromUser);
                           
                     //  }

                    } else if (approverList.get(k + i - 1).getPermitGubun().equals("03")) {
                        ttObjParamsSql.put("turnNo", k + i);
                        ttObjParamsSql.put("resultReason", "통보");
                        daoMyApproval.updateApporvoerStatus(ttObjParamsSql); //결재자 결재 상태 업데이트(승인)
                        j++;
                        
                        // 통보 이메일 발송
                        String toMailAddr = "";
                        String fromMailAddr = "";
                        String toUser = "";
                        String fromUser = "";
                        
                       // UtCoIOUtils.sendMail(toMailAddr, fromMailAddr, toUser, fromUser);
                        
                    } else if (approverList.get(k + i - 1).getPermitGubun().equals("01") || approverList.get(k + i - 1).getPermitGubun().equals("02")) {
                        ttObjParamsSql.put("turnNo", k + i);
                        ttObjParamsSql.put("status", nextStatus);
                        ttObjParamsSql.put("resultReason", "");
                        daoMyApproval.updateApporvoerStatus(ttObjParamsSql); //결재자 결재 상태 업데이트(승인중)
                        
                     // 중간결재 이메일 발송
                        /*String email = "";
                            email = svExctEscortDraftService.getEmail(approverList);
                            System.out.println("emailemailemailemailemailemailemailemailemail : " + email);
                            
                            try{
                                UtCoIOUtils.sendMail(email, "administrator@escort.co.kr", "Administrator", "", ttObjParams);
                            }catch (Exception  e) {
                                // TODO: handle exception
                                System.out.println("111111 : " + e.toString());
                            }*/
                        
                        /*
                        String toMailAddr = "";
                        String fromMailAddr = "";
                        String toUser = "";
                        String fromUser = "";
                        
                        UtCoIOUtils.approvalMail(toMailAddr, fromMailAddr, toUser, fromUser);*/
                        
                        break outer;
                        
                        
                    }
               }

    }

    /**
     * @param ttObjParams 결재승인 파라메터
     * @throws Exception 예외
     */
    @Transactional
    public void apprAttachRequest(CoTtObjParams ttObjParams) throws Exception {
        VoApprInfo voApprInfo = (VoApprInfo) ttObjParams.get("voApprInfo");
        String status = "01";
        String nextStatus = "02";
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        ttObjParamsSql.put("apprId", voApprInfo.getApprId());
        ttObjParamsSql.put("userId", voApprInfo.getUserId());
        ttObjParamsSql.put("status", status);
        ttObjParamsSql.put("permitGubun", voApprInfo.getPermitGubun());
        ttObjParamsSql.put("resultReason", voApprInfo.getResultReason());
        String expDm = UtCoDateUtils.getCurrentDateAsString(UtCoDateUtils.DATE_HMS_PATTERN); //yyyyMMddHHmmss
        ttObjParamsSql.put("expDm", expDm);

        List<VoApprInfo> approverList =  daoMyApproval.selectApproverList(ttObjParamsSql); //결재자 리스트
        int turnNo = Integer.parseInt(voApprInfo.getTurnNo());
        int j = turnNo;
        int k = turnNo + 1;
        String serial = "";
        ttObjParamsSql.put("turnNo", j);
        daoMyApproval.updateApporvoerStatus(ttObjParamsSql);
        outer: for (int i = 0; i < approverList.size() - turnNo + 1; i++) {
                    if (approverList.size() == j) {
                        VoApprInfo approvalInfo =  daoMyApproval.selectApprovalInfo(ttObjParamsSql); //결재정보
                        List<VoApprDetail> apprDtlList =  daoMyApproval.selectAttachApprDtlList(ttObjParamsSql); //결재항목 리스트
                        List<VoExctObjPc> apprObjPcList =  daoMyApproval.selectApprObjPcList(ttObjParamsSql); //결재대상 리스트
                        ttObjParamsSql.put("startDm", approvalInfo.getStartDm());
                        ttObjParamsSql.put("endDm", approvalInfo.getEndDm());
                        ttObjParamsSql.put("reason", approvalInfo.getReason());
                        ttObjParamsSql.put("grpGubun", "P");

                        for (int l = 0; l < apprObjPcList.size(); l++) {
                            ttObjParamsSql.put("serial", apprObjPcList.get(l).getSerial());
                            ttObjParamsSql.put("empNo", apprObjPcList.get(l).getUserId());
                            //for (int m = 0; m < apprDtlList.size(); m++) { 
                                if (approvalInfo.getAllowGubun().equals("01")) {
                                    ttObjParamsSql.put("allowType", "Y");
                                    ttObjParamsSql.put("allowLog", "L");
                                    ttObjParamsSql.put("subGubun", "");
                                    ttObjParamsSql.put("subValue1", "");
                                    ttObjParamsSql.put("subValue2", "");
                                    daoMyApproval.callProcedureExceptionICAT(ttObjParamsSql);
                                } else {
                                    for (int m = 0; m < apprDtlList.size(); m++) {
                                        ttObjParamsSql.put("allowType", "N");
                                        ttObjParamsSql.put("allowLog", "L");
                                        ttObjParamsSql.put("subGubun", apprDtlList.get(m).getAttachGubun());
                                        ttObjParamsSql.put("subValue1", apprDtlList.get(m).getValue1());
                                        ttObjParamsSql.put("subValue2", apprDtlList.get(m).getValue2());
                                        ttObjParamsSql.put("subStartDm", approvalInfo.getStartDm().substring(0, 10));
                                        ttObjParamsSql.put("subEndDm", approvalInfo.getEndDm().substring(0, 10));
                                        daoMyApproval.callProcedureExceptionICAT(ttObjParamsSql);
                                    }
                                }
                            //}

                            //첨부통제 프로시저콜 두가지
                       }
                       daoMyApproval.updateApprInfoStatus(ttObjParamsSql); //결재정보 상태 업데이트
                       
                    // UDP 통신
                       System.out.println("sendPacket.do  start");
                       int port = 15010;
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
                      
                       //String serial = "A00017A4DD1FD7";
                       serial = apprObjPcList.get(i).getSerial();
                       String sendMsg = "";
                       for(int m=0;m<prefix.length;m++)
                       {
                           sendMsg = prefix[i] + serial;
                           
                           /*byte ba[] = sendMsg.getBytes("utf-8");
                           
                           InetAddress inet = InetAddress.getByName("192.168.1.61");        
                           //InetAddress inet = InetAddress.getByName("192.168.1.174");
                           
                           DatagramPacket dpacket = new DatagramPacket(ba, ba.length, inet, port);
                           
                           DatagramSocket ds = new DatagramSocket();
                           ds.send(dpacket);
                           
                           ds.close();
                            ds.disconnect();*/
                           
                           Runtime rt = Runtime.getRuntime();
                           Process p;
                           try {
                             p = rt.exec("cmd /c C:/SendUDPPacket.exe 192.168.1.60 15010 "+ sendMsg +" > C:/logs/results"+m+".txt");
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
                           }



                           System.out.println("sendPacket.do  end : " + sendMsg);
                       }

                    } else if (approverList.get(k + i - 1).getPermitGubun().equals("03")) {
                        ttObjParamsSql.put("turnNo", k + i);
                        ttObjParamsSql.put("resultReason", "통보");
                        daoMyApproval.updateApporvoerStatus(ttObjParamsSql); //결재자 결재 상태 업데이트(승인)
                        j++;
                    } else if (approverList.get(k + i - 1).getPermitGubun().equals("01") || approverList.get(k + i - 1).getPermitGubun().equals("02")) {
                        ttObjParamsSql.put("turnNo", k + i);
                        ttObjParamsSql.put("status", nextStatus);
                        ttObjParamsSql.put("resultReason", "");
                        daoMyApproval.updateApporvoerStatus(ttObjParamsSql); //결재자 결재 상태 업데이트(승인중)
                        break outer;
                    }
               }

    }

    /**
     * @param ttObjParams 결재승인 파라메터
     * @throws Exception 예외
     */
    @Transactional
    public void apprOutputRequest(CoTtObjParams ttObjParams) throws Exception {
        VoApprInfo voApprInfo = (VoApprInfo) ttObjParams.get("voApprInfo");
        String status = "01";
        String nextStatus = "02";
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        ttObjParamsSql.put("apprId", voApprInfo.getApprId());
        //ttObjParamsSql.put("userId", voApprInfo.getUserId());
        ttObjParamsSql.put("status", status);
        ttObjParamsSql.put("permitGubun", voApprInfo.getPermitGubun());
        ttObjParamsSql.put("resultReason", voApprInfo.getResultReason());
        String expDm = UtCoDateUtils.getCurrentDateAsString(UtCoDateUtils.DATE_HMS_PATTERN); //yyyyMMddHHmmss
        ttObjParamsSql.put("expDm", expDm);

        List<VoApprInfo> approverList =  daoMyApproval.selectApproverList(ttObjParamsSql); //결재자 리스트
        int turnNo = Integer.parseInt(voApprInfo.getTurnNo());
        int j = turnNo;
        int k = turnNo + 1;
        String serial = "";
        ttObjParamsSql.put("turnNo", j);
        daoMyApproval.updateApporvoerStatus(ttObjParamsSql);
        outer: for (int i = 0; i < approverList.size() - turnNo + 1; i++) {
                    if (approverList.size() == j) {
                        VoApprInfo approvalInfo =  daoMyApproval.selectApprovalInfo(ttObjParamsSql); //결재정보
                        List<VoApprDetail> apprDtlList =  daoMyApproval.selectAttachApprDtlList(ttObjParamsSql); //결재항목 리스트
                        List<VoExctObjPc> apprObjPcList =  daoMyApproval.selectApprObjPcList(ttObjParamsSql); //결재대상 리스트
                        ttObjParamsSql.put("startDm", approvalInfo.getStartDm());
                        ttObjParamsSql.put("endDm", approvalInfo.getEndDm());
                        ttObjParamsSql.put("reason", approvalInfo.getReason());
                        ttObjParamsSql.put("grpGubun", "P");

                        for (int l = 0; l < apprObjPcList.size(); l++) {
                            ttObjParamsSql.put("apprId", apprObjPcList.get(l).getApprId());
                            ttObjParamsSql.put("userId", apprObjPcList.get(l).getUserId());
                            ttObjParamsSql.put("serial", apprObjPcList.get(l).getSerial());
                            ttObjParamsSql.put("empNo", apprObjPcList.get(l).getUserId());
                            String tempStartDm = approvalInfo.getStartDm() + "000000";
                            String tempEndDm = approvalInfo.getEndDm() + "235959";
                            String startDm = UtCoDateUtils.convertStringToString(tempStartDm, "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss");
                            String endDm = UtCoDateUtils.convertStringToString(tempEndDm, "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss");
                            ttObjParamsSql.put("startDm", startDm);
                            ttObjParamsSql.put("endDm", endDm);
                            ttObjParamsSql.put("value1", apprDtlList.get(0).getValue1());
                            //daoSecuPrint.insertApprUserOutput(ttObjParamsSql);
                            daoSecuPrint.insertApprExceptionOutput(ttObjParamsSql);

                            //첨부통제 프로시저콜 두가지
                       }
                       daoMyApproval.updateApprInfoStatus(ttObjParamsSql); //결재정보 상태 업데이트
                       

                       // UDP 통신
                       System.out.println("sendPacket.do  start");
                       int port = 15010;
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
                      
                       //String serial = "A00017A4DD1FD7";
                       serial = apprObjPcList.get(i).getSerial();
                       String sendMsg = "";
                       for(int m=0;m<prefix.length;m++)
                       {
                           sendMsg = prefix[i] + serial;
                           
                           /*byte ba[] = sendMsg.getBytes("utf-8");
                           
                           InetAddress inet = InetAddress.getByName("192.168.1.61");        
                           //InetAddress inet = InetAddress.getByName("192.168.1.174");
                           
                           DatagramPacket dpacket = new DatagramPacket(ba, ba.length, inet, port);
                           
                           DatagramSocket ds = new DatagramSocket();
                           ds.send(dpacket);
                           
                           ds.close();
                            ds.disconnect();*/
                           
                           Runtime rt = Runtime.getRuntime();
                           Process p;
                           try {
                             p = rt.exec("cmd /c C:/SendUDPPacket.exe 192.168.1.60 15010 "+ sendMsg +" > C:/logs/results"+m+".txt");
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
                           }



                           System.out.println("sendPacket.do  end : " + sendMsg);
                       }

                    } else if (approverList.get(k + i - 1).getPermitGubun().equals("03")) {
                        ttObjParamsSql.put("turnNo", k + i);
                        ttObjParamsSql.put("resultReason", "통보");
                        daoMyApproval.updateApporvoerStatus(ttObjParamsSql); //결재자 결재 상태 업데이트(승인)
                        j++;
                    } else if (approverList.get(k + i - 1).getPermitGubun().equals("01") || approverList.get(k + i - 1).getPermitGubun().equals("02")) {
                        ttObjParamsSql.put("turnNo", k + i);
                        ttObjParamsSql.put("status", nextStatus);
                        ttObjParamsSql.put("resultReason", "");
                        daoMyApproval.updateApporvoerStatus(ttObjParamsSql); //결재자 결재 상태 업데이트(승인중)
                        break outer;
                    }
               }

    }


    /**
     * 사이트예외신청액션<br/>
     * @param ttObjParams 결재승인 파라메터
     * @throws Exception 예외
     */
    @Transactional
    public void apprSiteRequest(CoTtObjParams ttObjParams) throws Exception {
        VoApprInfo voApprInfo = (VoApprInfo) ttObjParams.get("voApprInfo");
        VoApprDetail voApprDetail = (VoApprDetail) ttObjParams.get("voApprDetail");
        String status = "01";
        String nextStatus = "02";
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        ttObjParamsSql.put("apprId", voApprInfo.getApprId());
        ttObjParamsSql.put("userId", voApprInfo.getUserId());
        ttObjParamsSql.put("status", status);
        ttObjParamsSql.put("permitGubun", voApprInfo.getPermitGubun());
        ttObjParamsSql.put("resultReason", voApprInfo.getResultReason());
        String expDm = UtCoDateUtils.getCurrentDateAsString(UtCoDateUtils.DATE_HMS_PATTERN); //yyyyMMddHHmmss
        ttObjParamsSql.put("expDm", expDm);

        List<VoApprInfo> approverList =  daoMyApproval.selectApproverList(ttObjParamsSql); //결재자 리스트
        int turnNo = Integer.parseInt(voApprInfo.getTurnNo());
        int j = turnNo;
        int k = turnNo + 1;
        String serial = "";
        ttObjParamsSql.put("turnNo", j);
        daoMyApproval.updateApporvoerStatus(ttObjParamsSql);
        outer: for (int i = 0; i < approverList.size() - turnNo + 1; i++) {
                    if (approverList.size() == j) { //마지막 결제
                        VoApprInfo approvalInfo =  daoMyApproval.selectApprovalInfo(ttObjParamsSql); //결재정보
                        List<VoApprDetail> apprDtlList =  daoMyApproval.selectAttachApprDtlList(ttObjParamsSql); //결재항목 리스트
                        List<VoExctObjPc> apprObjPcList =  daoMyApproval.selectApprObjPcList(ttObjParamsSql); //결재대상 리스트
                        ttObjParamsSql.put("startDm", approvalInfo.getStartDm());
                        ttObjParamsSql.put("endDm", approvalInfo.getEndDm());
                        ttObjParamsSql.put("reason", approvalInfo.getReason());
                        ttObjParamsSql.put("grpGubun", "P");

                        for (int l = 0; l < apprObjPcList.size(); l++) {
                            /* 각각 예외신청에서 필요한 값들 */
                            ttObjParamsSql.put("ruleGUID", voApprDetail.getRuleGUID());
                            ttObjParamsSql.put("ruleName", voApprDetail.getRuleName());
                            ttObjParamsSql.put("timeGUID", voApprDetail.getTimeGUID());
                            ttObjParamsSql.put("timeName", voApprDetail.getTimeName());
                            ttObjParamsSql.put("deptGUID", voApprDetail.getDeptGUID());
                            ttObjParamsSql.put("empGUID", voApprDetail.getEmpGUID());
                            ttObjParamsSql.put("appId", voApprDetail.getAppId());
                            ttObjParamsSql.put("descript", voApprInfo.getReason());
                            ttObjParamsSql.put("endDm", voApprInfo.getEndDm());

                            //daoMyApproval.insertApprExceptionSite(ttObjParamsSql);
                            daoExctSiteDraft.insertApprExceptionSite(ttObjParamsSql);

                            //daoMyApproval.updateApprExceptionSite(ttObjParamsSql);
                            daoExctSiteDraft.updateApprExceptionSite(ttObjParamsSql);
                            System.out.println("sendPacket.do  start");
                            int port = 15010;
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
                           
                            //String serial = "A00017A4DD1FD7";
                            serial = apprObjPcList.get(i).getSerial();
                            String sendMsg = "";
                            /*for(int m=0;m<prefix.length;m++)
                            {
                                sendMsg = prefix[i] + serial;
                                
                                byte ba[] = sendMsg.getBytes("utf-8");
                                
                                InetAddress inet = InetAddress.getByName("192.168.1.61");        
                                //InetAddress inet = InetAddress.getByName("192.168.1.174");
                                
                                DatagramPacket dpacket = new DatagramPacket(ba, ba.length, inet, port);
                                
                                DatagramSocket ds = new DatagramSocket();
                                ds.send(dpacket);
                                
                                ds.close();
                                 ds.disconnect();
                                
                               



                                System.out.println("sendPacket.do  end : " + sendMsg);
                            }*/
                            
                            Runtime rt = Runtime.getRuntime();
                            Process p;
                            try {
                              p = rt.exec("cmd /c C:/RcvUptPersonPol.exe 192.168.1.94:5858 ["+ voApprInfo.getUserId() +"]");
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
                            }


                       }
                       daoMyApproval.updateApprInfoStatus(ttObjParamsSql); //결재정보 상태 업데이트

                    } else if (approverList.get(k + i - 1).getPermitGubun().equals("03")) { //통보
                        ttObjParamsSql.put("turnNo", k + i);
                        ttObjParamsSql.put("resultReason", "통보");
                        daoMyApproval.updateApporvoerStatus(ttObjParamsSql); //결재자 결재 상태 업데이트(승인)
                        j++;
                    } else if (approverList.get(k + i - 1).getPermitGubun().equals("01") || approverList.get(k + i - 1).getPermitGubun().equals("02")) {    //합의,결제
                        ttObjParamsSql.put("turnNo", k + i);
                        ttObjParamsSql.put("status", nextStatus);
                        ttObjParamsSql.put("resultReason", "");
                        daoMyApproval.updateApporvoerStatus(ttObjParamsSql); //결재자 결재 상태 업데이트(승인중)
                        break outer;
                    }
               }

    }


}