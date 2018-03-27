package tt.module.admin.exct;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import tt.TTLog;
import tt.base.TtSession;
import tt.base.module.TtBaseController;
import tt.bean.VoAddUser;
import tt.bean.VoApprDetail;
import tt.bean.VoApprInfo;
import tt.bean.VoExct;
import tt.bean.VoExctDraft;
import tt.bean.VoPcBasic;
import tt.com.CoTtObjParams;
import tt.com.bean.VoCoCodeDtl;
import tt.com.bean.VoCoUser;
import tt.com.constant.CsCoConstDef;
import tt.com.utils.UtCoDateUtils;
import tt.com.utils.UtCoStringUtils;

/**
 * <pre>
 * tt.module.admin.exct
 *    |_ CtAdExctAttachDraftController.java
 *
 * DESC : 첨부통제예외신청 Controller 클래스 <br />
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author ks-lee
 * @Date 2013. 3. 19. 오후 1:00:21
 * @history :
 *  -----------------------------------------------------------------------
 *  변경일             작성자                     변경내용
 *  ----------- ------------------- ---------------------------------------
 *  2013. 3. 19.        ks-lee              최초 작성
 *  -----------------------------------------------------------------------
 *
 */
@Controller
public class CtAdExctApiController extends TtBaseController {

    /**
     * 첨부통제예외신청동의 <br />
     * @param model 모델
     * @param session 세션
     * @param request 요청
     * @return 페이지경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adApiRegist.do")
    public String adEscortDraft(ModelMap model, HttpSession session, HttpServletRequest request)
            throws Exception {

        TTLog.info(":::   adApiRegist Start   :::", this.getClass());

        // check login
        if (!isLogin(request)) {
            return "admin.login.tiles";
        }
        
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        
        String exctDiv = (String)request.getParameter("exctDiv"); // 예외신청구분
        
        ttObjParamsSql.put("exct_div", exctDiv);
        if(exctDiv.equals("ER")){ //Escort정책예외신청 API 
            
            String arr_serial = (String)request.getParameter("serial") +":"; //예외신청PC
            String userId = (String)request.getParameter("userId"); //사번
            String startDm = (String)request.getParameter("startDm"); //시작일
            String endDm = (String)request.getParameter("endDm"); //종료일
            String reason = (String)request.getParameter("reason"); //신청사유
            String inReason = new String(reason.getBytes("8859_1"), "euc-kr");
            String arr_exctId = (String)request.getParameter("exctId") +":"; // 예외ID
            
            String[] serial = arr_serial.split(":");
            
            System.out.println("############################  serial.length : " + serial.length);
            System.out.println("############################  reason : " + reason);
            System.out.println("############################  inReason : " + inReason);
            
            for(int i=0; i<serial.length; i++){
                ttObjParamsSql.put("serial", serial[i]);
                ttObjParamsSql.put("userId", userId);
                ttObjParamsSql.put("startDm", startDm);
                ttObjParamsSql.put("endDm", endDm);
                ttObjParamsSql.put("reason", inReason);
                
                String[] exctId = arr_exctId.split(":");
                
                System.out.println("############################  serial : " + ttObjParamsSql.get("serial"));
                System.out.println("############################  userId : " + ttObjParamsSql.get("userId"));
                System.out.println("############################  startDm : " + ttObjParamsSql.get("startDm"));
                System.out.println("############################  endDm : " + ttObjParamsSql.get("endDm"));
                System.out.println("############################  reason : " + ttObjParamsSql.get("reason"));
                
                for(int j=0; j<exctId.length; j++){
                    
                    ttObjParamsSql.put("exctId", exctId[j]);
                    System.out.println("############################  exctID : " + ttObjParamsSql.get("exctId"));
                    VoApprDetail voApprDetail = new VoApprDetail();
                    voApprDetail.setExctId(exctId[j]);
                    List<VoApprDetail> paramsList = svExctApiService.selectExctApi(voApprDetail);
                    
                    String pro_gubun = paramsList.get(j).getProGubun();
                    if (pro_gubun.equals("EXCEPTION")) {
                        ttObjParamsSql.put("gubun", paramsList.get(j).getGubun());
                        ttObjParamsSql.put("allowType", "0");
                        ttObjParamsSql.put("smartPhoneExtend", "0");
                        ttObjParamsSql.put("grpGubun", "P");
                        svExctApiService.callProcedureApprException(ttObjParamsSql);
                    } else if (pro_gubun.equals("STORERULE")) {
                        ttObjParamsSql.put("gubun", paramsList.get(j).getGubun());
                        ttObjParamsSql.put("allowType", "A");
                        ttObjParamsSql.put("allowLog", "L");
                        ttObjParamsSql.put("smartPhoneExtend", "0");
                        ttObjParamsSql.put("grpGubun", "P");
                        svExctApiService.callProcedureApprStorerule(ttObjParamsSql);
                    } else if (pro_gubun.equals("EXCEPTIONAP")) { //등록AP
                        ttObjParamsSql.put("allowType", "A");
                        ttObjParamsSql.put("allowLog", "L");
                        ttObjParamsSql.put("priority", "L");
                        svExctApiService.callProcedureApprExceptionAP(ttObjParamsSql);
                    } else if (pro_gubun.equals("ADDHDD")) { //추가 HDD
                        int chkSameADDHDD = svExctApiService.selectChkSameADDHDD(ttObjParamsSql);
                        if (chkSameADDHDD > 0) {
                            svExctApiService.updateApprExceptionADDHDD(ttObjParamsSql);
                        } else {
                            svExctApiService.insertApprExceptionADDHDD(ttObjParamsSql);
                        }
                    } else if (pro_gubun.equals("EXCEPTIONVPN")) { //반출잠김
                        int chkSameExceptionVPN = svExctApiService.selectChkSameExceptionVPN(ttObjParamsSql);
                        ttObjParamsSql.put("grpGubun", "P");
                        if (chkSameExceptionVPN > 0) {
                            svExctApiService.updateApprExceptionVPN(ttObjParamsSql);
                        } else {
                            svExctApiService.insertApprExceptionVPN(ttObjParamsSql);
                        }
                    }
                }
            }
        }
        else if(exctDiv.equals("AR")){ //첨부통제예외신청 API
            String arr_serial = (String)request.getParameter("serial") +":"; //예외신청PC
            String userId = (String)request.getParameter("userId"); //사번
            String startDm = (String)request.getParameter("startDm"); //시작일
            String endDm = (String)request.getParameter("endDm"); //종료일
            String reason = (String)request.getParameter("reason"); //신청사유
            String inReason = new String(reason.getBytes("8859_1"), "euc-kr");
            String arr_apprId = (String)request.getParameter("apprId") +":"; // 예외ID
            
            String[] serial = arr_serial.split(":");
            
            System.out.println("############################  serial.length : " + serial.length);
            System.out.println("############################  reason : " + reason);
            System.out.println("############################  inReason : " + inReason);
            
            for(int i=0; i<serial.length; i++){
                ttObjParamsSql.put("serial", serial[i]);
                ttObjParamsSql.put("userId", userId);
                ttObjParamsSql.put("startDm", startDm);
                ttObjParamsSql.put("endDm", endDm);
                ttObjParamsSql.put("reason", inReason);
                
                String[] apprId = arr_apprId.split(":");
                
                for(int j=0; j<apprId.length; j++){
                    ttObjParamsSql.put("apprId", apprId[j]);
                    
                    VoApprInfo approvalInfo =  svExctApiService.selectApprovalInfo(ttObjParamsSql); //결재정보
                    List<VoApprDetail> apprDtlList =  svExctApiService.selectAttachApprDtlList(ttObjParamsSql); //결재항목 리스트
                    
                    ttObjParamsSql.put("grpGubun", "P");
                    
                    if (approvalInfo.getAllowGubun().equals("01")) {
                        ttObjParamsSql.put("allowType", "Y");
                        ttObjParamsSql.put("allowLog", "L");
                        ttObjParamsSql.put("subGubun", "");
                        ttObjParamsSql.put("subValue1", "");
                        ttObjParamsSql.put("subValue2", "");
                        svExctApiService.callProcedureExceptionICAT(ttObjParamsSql);
                        //daoMyApproval.callProcedureExceptionICAT(ttObjParamsSql);
                    } else {
                        ttObjParamsSql.put("allowType", "N");
                        ttObjParamsSql.put("allowLog", "L");
                        ttObjParamsSql.put("subGubun", apprDtlList.get(j).getAttachGubun());
                        ttObjParamsSql.put("subValue1", apprDtlList.get(j).getValue1());
                        ttObjParamsSql.put("subValue2", apprDtlList.get(j).getValue2());
                        ttObjParamsSql.put("subStartDm", approvalInfo.getStartDm().substring(0, 10));
                        ttObjParamsSql.put("subEndDm", approvalInfo.getEndDm().substring(0, 10));
                        svExctApiService.callProcedureExceptionICAT(ttObjParamsSql);
                        //daoMyApproval.callProcedureExceptionICAT(ttObjParamsSql);
                    }
                    
                }
            }
        }
        
        else if(exctDiv.equals("OR")){ //출력보안예외신청 API
            String userId = (String)request.getParameter("userId"); //사번
            String startDm = (String)request.getParameter("startDm"); //시작일
            String endDm = (String)request.getParameter("endDm"); //종료일
            String reason = (String)request.getParameter("reason"); //신청사유
            String inReason = new String(reason.getBytes("8859_1"), "euc-kr");
            String value1 = (String)request.getParameter("value1"); 
            
            ttObjParamsSql.put("Sabeon,", userId);
            ttObjParamsSql.put("SingleID", userId);
            ttObjParamsSql.put("DateFROM", startDm);
            ttObjParamsSql.put("DateTo", endDm);
            ttObjParamsSql.put("Division", value1);
            ttObjParamsSql.put("Content", inReason);
            svExctApiService.insertApprExceptionOutput(ttObjParamsSql);
        }
        
        else if(exctDiv.equals("SR")){ //사이트예외신청 API
            String empID = (String)request.getParameter("empID");
            String appId = (String)request.getParameter("appId");
            String ruleID = (String)request.getParameter("ruleID");
            String timeID = (String)request.getParameter("timeID");
            String deptID = (String)request.getParameter("deptID");
            String descript = (String)request.getParameter("descript");
            String endDm = (String)request.getParameter("endDm");
            
            String empGUID = "";
            String end_Dm = UtCoDateUtils.convertStringToString(endDm, "yyyyMMddHHmm", "yyyy-MM-dd HH:mm");
            String ruleGUID = "";
            String timeGUID = "";
            String deptGUID = "";
            if(empID.equals("A2")){
                empGUID = "083DA7F1-35B6-4D24-8681-60C6B8FF9EC7";
            }else if(empID.equals("A2")){
                empGUID = "EEB24A89-74CA-4EE9-B38F-AE1DD4ED37F7";
            }else if(empID.equals("A2")){
                empGUID = "4B14046A-5FB4-46FD-B287-FC9922F105EA";
            }
            
            if(ruleID.equals("NB")){ //비업무사이트 차단
                ruleGUID = "F3D86F82-5444-432E-A66E-4A29C6EEFD76";
            }else if(ruleID.equals("BR")){ //기본규칙
                ruleGUID = "623C80E7-5D16-4FD6-A6D6-8D8DA217EAAF";
            }else if(ruleID.equals("LR")){ //점심시간용 규칙
                ruleGUID = "94CE7291-07AE-4602-BC86-ABBBDFEB659B";
            }else if(ruleID.equals("HB")){ //유해사이트 차단
                ruleGUID = "E208CA4A-78D0-46EE-ADEC-B87B79A617B6";
            }else if(ruleID.equals("SB")){ //증권사이트 차단
                ruleGUID = "425705FA-281F-49EF-940A-BDE9CCC031D1";
            }
            
            if(timeID.equals("ET")){ //모든시간대
                timeGUID = "75A2AC31-951C-41F1-949B-4421A657AD65";
            }else if(timeID.equals("BT")){ //업무시간대
                timeGUID = "CEDDFC43-D7C8-44A5-BF23-8AE23F066E9E";
            }else if(timeID.equals("NT")){ //비업무시간대
                timeGUID = "BE86F1AB-860F-44CE-9CC1-B1D4455B3D2A";
            }else if(timeID.equals("MT")){ //오전시간대
                timeGUID = "65DF5549-EDFB-477D-84E4-BC0985BD429C";
            }else if(timeID.equals("MIT")){ //점심시간 포함한 업무시간대
                timeGUID = "827EE29F-BC12-4759-9896-F3560ABD33B1";
            }else if(timeID.equals("AT")){ //오후시간대
                timeGUID = "0D0FBC59-4CA4-4464-9A2E-FB4734D91A4D";
            }
            
            if(deptID.equals("dept0")){
                deptGUID = "699830C2-4779-4F11-A357-AFBA8C388FEA";
            }else if(deptID.equals("dept1")){
                deptGUID = "379361F5-84A9-4807-B884-022176E9A105";
            }else if(deptID.equals("dept2")){
                deptGUID = "8EEFA3F5-FC63-4F6F-A267-4917C2F526FF";
            }
            ttObjParamsSql.put("empGUID", empGUID);
            ttObjParamsSql.put("appId", appId);
            ttObjParamsSql.put("ruleGUID", ruleGUID);
            ttObjParamsSql.put("timeGUID", timeGUID);
            ttObjParamsSql.put("deptGUID", deptGUID);
            ttObjParamsSql.put("descript", descript);
            ttObjParamsSql.put("endDm", end_Dm);

            svExctApiService.insertApprExceptionSite(ttObjParamsSql);

            svExctApiService.updateApprExceptionSite(ttObjParamsSql);
        }
        
        else{
            
        }

        //voExctDraft.setCheckMessage("success_modi");
        return "admin.exct.api.tiles";
    }

}
