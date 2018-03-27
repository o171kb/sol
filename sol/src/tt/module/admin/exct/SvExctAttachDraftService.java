package tt.module.admin.exct;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tt.TTLog;
import tt.base.TtSession;
import tt.base.module.TtBaseService;
import tt.bean.VoAddUser;
import tt.bean.VoApprDetail;
import tt.bean.VoApprInfo;
import tt.bean.VoExct;
import tt.bean.VoExctDraft;
import tt.bean.VoPcBasic;
import tt.com.CoTtObjParams;
import tt.com.bean.VoCoBase;
import tt.com.bean.VoCoCodeDtl;
import tt.com.bean.VoCoDept;
import tt.com.bean.VoCoUser;
import tt.com.utils.UtCoDateUtils;
import tt.com.utils.UtCoStringUtils;
import tt.constant.CsConstDef;
import tt.dao.DaoExct;
import tt.dao.DaoIncops5;
import tt.module.CpDocument;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;


/**
 * <pre>
 * tt.com.module.dept
 *    |_ SvCoDeptService.java
 *
 * DESC : 부서 관리 서비스 클래스 <br />
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author ks-lee
 * @Date 2013. 3. 8. 오전 9:43:41
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 3. 8.		ks-lee				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
@Service("svExctAttachDraftService")
public class SvExctAttachDraftService extends TtBaseService  {

    /**
     * Escort 결제상신<br/>
     * @param ttObjParams 상신정보
     * @param request 리퀘스트
     * @throws Exception 예외
     */
    @Transactional
    public void insertApprAllInfo(CoTtObjParams ttObjParams, HttpServletRequest request) throws Exception {

        VoCoUser voCoUser = (VoCoUser) ttObjParams.get("voCoUser");
        VoApprInfo voApprInfo = (VoApprInfo) ttObjParams.get("voApprInfo");
        VoPcBasic voPcBasic = (VoPcBasic) ttObjParams.get("voPcBasic");
        VoApprDetail voApprDetail = (VoApprDetail) ttObjParams.get("voApprDetail");
        String today = UtCoDateUtils.getCurrentDateAsString(UtCoDateUtils.DATE_PATTERN);
        String latestIdSt = daoExctEscortDraft.getLatestApprId();
        String apprId = "";
        if (latestIdSt.equals("1")) {
            apprId = today + "0001";
        } else {
            String latestId = latestIdSt.substring(0, 8);
            if (today.equals(latestId)) {
               Long tempLatestId = Long.parseLong(latestIdSt) + 1;
               apprId = Long.toString(tempLatestId);
            } else {
                apprId = today + "0001";
            }
        }

        String updDm = UtCoDateUtils.getCurrentDateAsString(UtCoDateUtils.DATE_TIME_PATTERN);

        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        ttObjParamsSql.put("apprId", apprId);
        ttObjParamsSql.put(VoCoBase.COL_UPD_DM, updDm);
        ttObjParamsSql.put("status", "02");

        /* file upload */
        Map tmpUploadFileInfoMap = svCoFileService.uploadFileToTempDir(request);

        if (tmpUploadFileInfoMap.containsKey("_attachFile")) {
            CpDocument attachFileDoc = (CpDocument) tmpUploadFileInfoMap.get("_attachFile");
            //업로드디렉토리에 이동
            ttObjParamsSql.put("addProofNm", UtCoStringUtils.substringAfter(attachFileDoc.getFileName(), CsConstDef.STR_DASH));
            ttObjParamsSql.put("addProofUrl", svCoFileService.uploadFileToRealDirFromTempDir(attachFileDoc));
        }

        ttObjParamsSql.put("permitGubun", "Exct02");
        String[] userId = voCoUser.getChkApprover().split(",");
        String[] permitGubun = voApprInfo.getPermitGubuns().split(",");
        String[] tempChkPc = voPcBasic.getChkPc().split(",");
        String[] tempChkEmpNo = voPcBasic.getChkEmpNo().split(",");


        int turnNo = 1;
        String appDiv = "N";
        for (int i = 0; i < userId.length; i++) {   //결재자 정보
            ttObjParamsSql.put("turnNo", turnNo);
            ttObjParamsSql.put("userId", userId[i]);
            ttObjParamsSql.put("permitGubun", permitGubun[i]);
            
            // 결재 프로세스 수정 통보관련
            if(appDiv.equals("N"))
            {
                if(permitGubun[i].equals("01") || permitGubun[i].equals("02"))        // 결재일경우 승인중 으로 처리
                {
                    ttObjParamsSql.put("status", "02");
                    appDiv = "Y";
                }
                else if(permitGubun[i].equals("03"))
                {
                    ttObjParamsSql.put("status", "01");     // 통보인 경우 더미값
                }
            }
            else if(appDiv.equals("Y"))
            {
                if(!permitGubun[i].equals("03"))            // 결재자가 나온후 통보가 아닌경우 경우 대기중
                {
                    ttObjParamsSql.put("status", "04");
                }
                else if(permitGubun[i].equals("03"))
                {
                    ttObjParamsSql.put("status", "04");     // 결재자가 나온후 통보인경우 더미값
                    
                }                
            }
            daoExctEscortDraft.insertApprover(ttObjParamsSql);
            turnNo++;
        }
        for (int j = 0; j < tempChkPc.length; j++) {    //예외대상 PC
            ttObjParamsSql.put("serial", tempChkPc[j]);
            VoPcBasic tempVoPcBasic = daoIncops5.selectPcBasic(ttObjParamsSql);
            ttObjParamsSql.put("userNm", tempVoPcBasic.gethName());
            ttObjParamsSql.put("serial", tempVoPcBasic.getSerial());
            ttObjParamsSql.put("ipAddr", tempVoPcBasic.getIpAddr());
            ttObjParamsSql.put("appId", tempChkEmpNo[j]);
            daoExctEscortDraft.insertApprObjPc(ttObjParamsSql);
         }
            ttObjParamsSql.put("appId", tempChkEmpNo[0]);
            ttObjParamsSql.put("exctNms", voApprInfo.getChkExctNms());  //신청정보
            ttObjParamsSql.put("exctAppId", voApprInfo.getExctAppId());
            ttObjParamsSql.put("purpose", voApprInfo.getPurpose());
            ttObjParamsSql.put("startDm", voApprInfo.getStartDm());
            ttObjParamsSql.put("endDm", voApprInfo.getEndDm());
            ttObjParamsSql.put("reason", voApprInfo.getReason());
            ttObjParamsSql.put("apprObjUser", voApprInfo.getChkObjNms());
            ttObjParamsSql.put("apprObjDept", voApprInfo.getChkDeptNms());
            ttObjParamsSql.put("drafter", ttObjParams.get("drafter"));
            ttObjParamsSql.put("apprUserId", ttObjParams.get("apprUserId"));
            ttObjParamsSql.put("status", "02");
            ttObjParamsSql.put("attachAllowGubun", voApprInfo.getAllowGubun());
            daoExctEscortDraft.insertApprInfo(ttObjParamsSql);

            if (voApprInfo.getAllowGubun().equals("02")) {
                //attachDraft 관련 split
                String[] tempValue1 = voApprDetail.getValue1().split(",");
                String[] tempValue2 = voApprDetail.getValue2().split(",");
                String[] tempGubun = voApprDetail.getAttachGubun().split(",");
                String[] exctNm = voApprInfo.getExctNms().split(",");
                ttObjParamsSql.put("proGubun", voApprInfo.getExctAppId());
                int cnt = 0;
                if (tempValue2[0] != "" || tempValue2[0] != null) {
                    for (int i = 0; i < tempValue2.length; i++) {
                        ttObjParamsSql.put("value1", tempValue1[i]);
                        ttObjParamsSql.put("value2", tempValue2[i]);
                        ttObjParamsSql.put("attachGubun", tempGubun[i]);
                        ttObjParamsSql.put("exctNm", exctNm[i]);
                        daoExctEscortDraft.insertApprDetail(ttObjParamsSql);
                        cnt++;
                    }
                    for (int k = cnt; k < tempValue1.length; k++) {
                        ttObjParamsSql.put("value1", tempValue1[k]);
                        ttObjParamsSql.put("value2", "");
                        ttObjParamsSql.put("attachGubun", tempGubun[k]);
                        ttObjParamsSql.put("exctNm", exctNm[k]);
                        daoExctEscortDraft.insertApprDetail(ttObjParamsSql);
                    }
                }
            }

        }


}