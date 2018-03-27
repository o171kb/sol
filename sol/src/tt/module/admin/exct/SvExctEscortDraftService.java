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
import tt.com.utils.UtCoIOUtils;
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
@Service("svExctEscortDraftService")
public class SvExctEscortDraftService extends TtBaseService  {

    /**
     * @param vo 파라미터
     * @return 예외신청문구수정 리스트
     * @throws Exception 예외
     */
    public VoExctDraft getExctEscortDraft(VoExctDraft vo) throws Exception {
        vo.setExctAppId("ESCORTDRAFT");
        return daoExctEscortDraft.getExctEscortDraft(vo);
    }

    /**
     * @param vo 파라미터
     * @return 예외신청문구수정 리스트
     * @throws Exception 예외
     */
    public List<VoExctDraft> getDraftModifyList(VoExctDraft vo) throws Exception {
        return daoExctDraft.getDraftModifyList(vo);
    }

    /**
     * @param ttObjParams 파라미터
     * @return 예외신청문구수정 상세
     * @throws Exception 예외
     */
    public VoExctDraft getExctDraftDtl(CoTtObjParams ttObjParams) throws Exception {

        VoExctDraft voExctDraft = (VoExctDraft) ttObjParams.get("voExctDraft");
        TTLog.debug(voExctDraft.toString(), this.getClass());

        //파라미터설정
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        ttObjParamsSql.put("exctAppId", voExctDraft.getExctAppId());

        return daoExctDraft.getExctDraftDtl(ttObjParamsSql);
    }

    /**
     * 예외신청문구수정 액션
     * @param ttObjParams 파라메터
     * @throws Exception 예외
     */
    public void modifyExctDraft(CoTtObjParams ttObjParams) throws Exception {

        VoExctDraft voExctDraft = (VoExctDraft) ttObjParams.get("voExctDraft");
        TTLog.debug(voExctDraft.toString(), this.getClass());

        //파라미터설정
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        ttObjParamsSql.put("exctAppId", voExctDraft.getExctAppId());
        ttObjParamsSql.put("exctBasicTerms", voExctDraft.getExctBasicTerms());
        ttObjParamsSql.put("maxAppTerm", voExctDraft.getMaxAppTerm());
        ttObjParamsSql.put("appTermEx", voExctDraft.getAppTermEx());
        ttObjParamsSql.put("addProofYn", voExctDraft.getAddProofYn());
        ttObjParamsSql.put("addProofMonth", voExctDraft.getAddProofMonth());
        ttObjParamsSql.put("addProofDay", voExctDraft.getAddProofDay());
//        ttObjParamsSql.put("topTerms", UtCoStringUtils.htmlEscape(voExctDraft.getTopTerms()));
//        ttObjParamsSql.put("permitTerms", UtCoStringUtils.htmlEscape(voExctDraft.getPermitTerms()));
//        ttObjParamsSql.put("bottomTerms", UtCoStringUtils.htmlEscape(voExctDraft.getBottomTerms()));
        ttObjParamsSql.put("topTerms", voExctDraft.getTopTerms());
        ttObjParamsSql.put("permitTerms", voExctDraft.getPermitTerms());
        ttObjParamsSql.put("bottomTerms", voExctDraft.getBottomTerms());

        ttObjParamsSql.put("updDm", voExctDraft.getUpdDm());
        ttObjParamsSql.put("updUser", voExctDraft.getUpdUser());



        daoExctDraft.modifyExctDraft(ttObjParamsSql);
    }

    /**
     * @param vo 파라미터
     * @return 초기 결재자 리스트
     * @throws Exception 예외
     */

    @Value("${procdureType}")
    private String procdureType;
    public List<VoAddUser> getApproverList(VoAddUser vo) throws Exception {
        int cdLength = vo.getMadeCd().length();
        int resultDiv = cdLength / 3;
        ArrayList<VoAddUser> resultApproverList = new ArrayList<VoAddUser>();
        ArrayList<VoAddUser> resultCompAdList = new ArrayList<VoAddUser>();
        ArrayList<VoAddUser> resultList = new ArrayList<VoAddUser>();

        for (int i = 0; i < resultDiv; i++) {
            resultApproverList.addAll(daoExctDraft.getApprover(vo));
            resultCompAdList.addAll(daoExctDraft.getCompAdmin(vo));
            cdLength = cdLength - 3;
            vo.setMadeCd(vo.getMadeCd().substring(0, cdLength));
        }
  //      try{
            if (procdureType.equals("0")) {                 //결재자 순서 변경
                resultList.add(resultApproverList.get(0));  //최초 결제자 추가
                if (resultCompAdList.size() > 0) {
                    resultList.add(resultCompAdList.get(0));    //전산관리자 정보 추가
                }

            } else {
                if (resultCompAdList.size() > 0) {
                    resultList.add(resultCompAdList.get(0));
                }
                resultList.add(resultApproverList.get(0));
            }
    //    }catch (Exception e){e.printStackTrace();}

        return resultList;
    }
    
    public List<VoAddUser> getApproverUserList(VoAddUser vo) throws Exception {
        int cdLength = vo.getMadeCd().length();
        int resultDiv = cdLength / 3;
        ArrayList<VoAddUser> resultApproverList = new ArrayList<VoAddUser>();
        ArrayList<VoAddUser> resultCompAdList = new ArrayList<VoAddUser>();
        ArrayList<VoAddUser> resultList = new ArrayList<VoAddUser>();
        
        ArrayList<VoAddUser> tempList = new ArrayList<VoAddUser>();
        String highDeptCd = "";
        VoAddUser  paramVO = vo;
        String deptCd = vo.getDeptCd();
        
        // 기본 결재자 정보 추출
        tempList = (ArrayList)daoExctDraft.getApproverUserList(paramVO);
        while(tempList.size() == 0 || tempList == null)
        {
            if(!deptCd.equals("TOP") || (tempList.size() <= 0||tempList == null)) // 자신의 부서에 결재자가 없을 경우 상위 부서의 결재자를 찾는다. TOP이 최상위 부서
            {
                if(!highDeptCd.equals(""))
                {
                    deptCd = highDeptCd;
                    if(highDeptCd.equals("0"))
                    {
                        break;
                    }
                }
                paramVO.setDeptCd(deptCd);
                highDeptCd = (String)daoExctDraft.getHighDeptList(paramVO);
                paramVO.setDeptCd(highDeptCd);
                tempList = (ArrayList)daoExctDraft.getApproverUserList(paramVO);
            }
            else
            {
                break;
            }
        }
        resultApproverList.addAll(tempList);
        if(resultApproverList != null)
        {
            resultList.addAll(tempList);
        }
        
        // 전산관리자 정보 추출
        tempList = (ArrayList)daoExctDraft.getCompAdminUserList(vo);
        if(tempList != null)
        {
            resultList.addAll(tempList);
        }
            
        

        /*for (int i = 0; i < resultDiv; i++) {
            resultApproverList.addAll(daoExctDraft.getApprover(vo));
            resultCompAdList.addAll(daoExctDraft.getCompAdmin(vo));
            cdLength = cdLength - 3;
            vo.setMadeCd(vo.getMadeCd().substring(0, cdLength));
        }
  //      try{
            if (procdureType.equals("0")) {                 //결재자 순서 변경
                resultList.add(resultApproverList.get(0));  //최초 결제자 추가
                if (resultCompAdList.size() > 0) {
                    resultList.add(resultCompAdList.get(0));    //전산관리자 정보 추가
                }

            } else {
                if (resultCompAdList.size() > 0) {
                    resultList.add(resultCompAdList.get(0));
                }
                resultList.add(resultApproverList.get(0));
            }
    //    }catch (Exception e){e.printStackTrace();}
*/
        return resultList;
    }
    

    /**
     * @param vo 파라미터
     * @return 초기 결재자 리스트
     * @throws Exception 예외
     */

    public List<VoPcBasic> getMyPcList(CoTtObjParams ttObjParams) throws Exception {
        return daoExctDraft.getApprovalPcList(ttObjParams);
    }    
    

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
        String[] tempChkExctId = voApprInfo.getChkExctIds().split(",");
        int turnNo = 1;
        String appDiv = "N";
        for (int i = 0; i < userId.length; i++) {   //결재자 정보
            ttObjParamsSql.put("turnNo", turnNo);
            ttObjParamsSql.put("userId", userId[i]);
            ttObjParamsSql.put("permitGubun", permitGubun[i]);
            //if (i != 0) {
            
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
            //}
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
            ttObjParamsSql.put("purpose", "");
            ttObjParamsSql.put("startDm", voApprInfo.getStartDm());
            ttObjParamsSql.put("endDm", voApprInfo.getEndDm());
            ttObjParamsSql.put("reason", voApprInfo.getReason());
            ttObjParamsSql.put("apprObjUser", voApprInfo.getChkObjNms());
            ttObjParamsSql.put("apprObjDept", voApprInfo.getChkDeptNms());
            ttObjParamsSql.put("drafter", ttObjParams.get("drafter"));
            ttObjParamsSql.put("apprUserId", ttObjParams.get("apprUserId"));
            ttObjParamsSql.put("status", "02");
            daoExctEscortDraft.insertApprInfo(ttObjParamsSql);
        for (int k = 0; k < tempChkExctId.length; k++) {    //신청항목
            ttObjParamsSql.put("exctId", tempChkExctId[k]);
            VoExct tempVoApprDetail = daoExctEscortDraft.selectApprDetail(ttObjParamsSql);
            ttObjParamsSql.put("exctNm", tempVoApprDetail.getExctNm());
            ttObjParamsSql.put("exctAppId", tempVoApprDetail.getExctId());
            ttObjParamsSql.put("proGubun", tempVoApprDetail.getProGubun());
            daoExctEscortDraft.insertApprDetail(ttObjParamsSql);
        }


    }
    
    public String getEmail(String id) throws Exception {
        return daoExctEscortDraft.getEmail(id);
    }

}