package tt.module.com.dwr;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;


import tt.base.module.TtBaseService;
import tt.bean.VoAddUser;
import tt.bean.VoAttachDraft;
import tt.bean.VoPcBasic;
import tt.bean.VoWkPolicy;
import tt.com.CoTtObjParams;
import tt.com.bean.VoCoPagerHelper;
import tt.com.bean.VoCoUser;
import tt.com.utils.UtCoStringUtils;
import tt.utils.UtPagerUtils;

/**
 * DESC : DwrService
 *
 * @Company think-tree.inc
 * @author sj-hwang
 * @Date 2013. 3. 28. 오후 7:29:20
 */
@Service("svDwrService")
public class SvDwrService extends TtBaseService {

    /*#########################################################################################
     * DWR 결재자
     *######################################################################################### */
    /**
     * 결재자리스트 취득 <br />
     * @param voCoUser 검색 파라미터
     * @return 검색리스트
     */
   /* public List<VoCoUser> getApprovalUserListAll(VoCoUser voCoUser) {

        if (voCoUser.getPageNo() > 1) {
            voCoUser.setFirstIndex((voCoUser.getPageNo() - 1) * voCoUser.getViewRecordNo());
        } else {
            voCoUser.setFirstIndex(0);
        }

        //페이징파라미터설정
        CoTtObjParams ttObjParamsSql = getParamsObjOfPageInfoSetted(voCoUser.getFirstIndex(), voCoUser.getRecordCountPerPage());
        ttObjParamsSql.put("searchApprUserNm", voCoUser.getSearchApprUserNm());
        ttObjParamsSql.put("searchDeptNm", voCoUser.getSearchDeptNm());

        List<VoCoUser> approvalUserList = daoExctDraft.getApprovalUserListAll(ttObjParamsSql);
        List<VoCoUser> resultapprovalUserList = new ArrayList<VoCoUser>();
        String madeCd = voCoUser.getMadeCd();
        int length = madeCd.length() / 3;
        int cnt = madeCd.length();
        for (int i = 0; i < length; i++) {
            String tempDeptCd = madeCd.substring(0, cnt);
            for (int k = 0; k < approvalUserList.size(); k++) {
                if (tempDeptCd.equals(approvalUserList.get(k).getMadeCd())) {
                    if (approvalUserList.get(k).getProxyApprYn().equals("1")) {
                        ttObjParamsSql.put("proxyApprId", approvalUserList.get(k).getProxyApprId());
                      VoCoUser tempProxyUser = daoExctEscortDraft.getProxyApprover(ttObjParamsSql);
                      resultapprovalUserList.add(tempProxyUser);
                    } else {
                        VoCoUser tempCoUser = approvalUserList.get(k);
                        resultapprovalUserList.add(tempCoUser);
                    }
                }
            }
            cnt = cnt - 3;
        }

        return resultapprovalUserList;
    }

    *//**
     * 결재자리스트 총 갯수 취득 및 페이징<br />
     *
     * @param voCoUser 검색 파라미터
     * @return 토탈카운트
     *//*
    public String paginate(VoCoUser voCoUser) {

        int totalRecordNo = daoExctDraft.paginate(voCoUser);
        VoCoPagerHelper pagerHelper =  UtPagerUtils.paginate(voCoUser, totalRecordNo);
        String resultHtml = UtPagerUtils.generatePagerHtml(pagerHelper, "approverListDwr");

        return resultHtml;
    }*/
    
    /**
     * 결재자리스트 취득 <br />
     * @param voCoUser 검색 파라미터
     * @return 검색리스트
     */
    public List<VoCoUser> getApprovalUserListAll(VoCoUser voCoUser) {

        if (voCoUser.getPageNo() > 1) {
            voCoUser.setFirstIndex((voCoUser.getPageNo() - 1) * voCoUser.getViewRecordNo());
        } else {
            voCoUser.setFirstIndex(0);
        }

        //페이징파라미터설정
        CoTtObjParams ttObjParamsSql = getParamsObjOfPageInfoSetted(voCoUser.getFirstIndex(), voCoUser.getRecordCountPerPage());
        ttObjParamsSql.put("searchApprUserNm", voCoUser.getSearchApprUserNm());
        ttObjParamsSql.put("searchDeptNm", voCoUser.getSearchDeptNm());
        ttObjParamsSql.put("deptCd", voCoUser.getDeptCd());
        ttObjParamsSql.put("userPosition", voCoUser.getUserPosition());
        
        List<VoCoUser> resultapprovalUserList = new ArrayList<VoCoUser>();
        
        String highDeptCd = "";
        VoAddUser  paramVO = new VoAddUser();
        paramVO.setDeptCd(voCoUser.getDeptCd());
        paramVO.setUserPosition(voCoUser.getUserPosition());
        paramVO.setUserNm(voCoUser.getSearchApprUserNm());
        paramVO.setDeptNm(voCoUser.getSearchDeptNm());
        
        String deptCd = paramVO.getDeptCd();
        
        List<VoCoUser> tempList = daoExctDraft.getApprovalUserListAll(ttObjParamsSql);
        if(tempList != null)
        {
            resultapprovalUserList.addAll(tempList);
        }
        while(!deptCd.equals("TOP"))
        {
            if(!deptCd.equals("TOP")) // 자신의 부서에 결재자가 없을 경우 상위 부서의 결재자를 찾는다. TOP이 최상위 부서
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
                if(!highDeptCd.equals("0"))
                {
                    paramVO.setDeptCd(highDeptCd);
                }
                tempList = (ArrayList)daoExctDraft.getApproverUserList(paramVO);
            }
            if(tempList != null)
            {
                resultapprovalUserList.addAll(tempList);
            }
            if(highDeptCd.equals("TOP"))
            {
                break;
            }
        }
        // 전산관리자 정보 추출
        tempList = (ArrayList)daoExctDraft.getCompAdminUserList(paramVO);
        if(tempList != null)
        {
            resultapprovalUserList.addAll(tempList);
        }
       
        /*String madeCd = voCoUser.getMadeCd();
        int length = madeCd.length() / 3;
        int cnt = madeCd.length();
        for (int i = 0; i < length; i++) {
            String tempDeptCd = madeCd.substring(0, cnt);
            for (int k = 0; k < approvalUserList.size(); k++) {
                if (tempDeptCd.equals(approvalUserList.get(k).getMadeCd())) {
                    if (approvalUserList.get(k).getProxyApprYn().equals("1")) {
                        ttObjParamsSql.put("proxyApprId", approvalUserList.get(k).getProxyApprId());
                      VoCoUser tempProxyUser = daoExctEscortDraft.getProxyApprover(ttObjParamsSql);
                      resultapprovalUserList.add(tempProxyUser);
                    } else {
                        VoCoUser tempCoUser = approvalUserList.get(k);
                        resultapprovalUserList.add(tempCoUser);
                    }
                }
            }
            cnt = cnt - 3;
        }*/

        return resultapprovalUserList;
    }

    /**
     * 결재자리스트 총 갯수 취득 및 페이징<br />
     *
     * @param voCoUser 검색 파라미터
     * @return 토탈카운트
     */
    public String paginate(VoCoUser voCoUser) {

        int totalRecordNo = daoExctDraft.paginate(voCoUser);
        VoCoPagerHelper pagerHelper =  UtPagerUtils.paginate(voCoUser, totalRecordNo);
        String resultHtml = UtPagerUtils.generatePagerHtml(pagerHelper, "approverListDwr");

        return resultHtml;
    }

    /**
     * 예외대상PC 리스트 취득 <br />
     * @param voPcBasic 검색 파라미터
     * @return 검색리스트
     */
    public List<VoPcBasic> getApprovalPcListAll(VoPcBasic voPcBasic, HttpServletRequest request) {
        if (voPcBasic.getPageNo() > 1) {
            voPcBasic.setFirstIndex((voPcBasic.getPageNo() - 1) * voPcBasic.getViewRecordNo());
        } else {
            voPcBasic.setFirstIndex(0);
        }
        
        String ip = request.getRemoteAddr();
        String userIp = "";
        String[] arr_userIp = ip.split("\\.");
        
        String userIp1 = (String)UtCoStringUtils.leftPadZero(arr_userIp[0], 3);
        String userIp2 = (String)UtCoStringUtils.leftPadZero(arr_userIp[1], 3);
        String userIp3 = (String)UtCoStringUtils.leftPadZero(arr_userIp[2], 3);
        String userIp4 = (String)UtCoStringUtils.leftPadZero(arr_userIp[3], 3);

        userIp = userIp1+"."+userIp2+"."+userIp3+"."+userIp4 ;
        System.out.println("userIp ::::" + userIp);
        
        //페이징파라미터설정
        CoTtObjParams ttObjParamsSql = getParamsObjOfPageInfoSetted(voPcBasic.getFirstIndex(), voPcBasic.getRecordCountPerPage());
        ttObjParamsSql.put("userIp", userIp);
        ttObjParamsSql.put("sdeptNm", voPcBasic.getSdeptNm());
        ttObjParamsSql.put("hName", voPcBasic.gethName());
        ttObjParamsSql.put("deptNm", voPcBasic.getDeptNm());
        return daoIncops5.getApprovalPcListAll(ttObjParamsSql);
    }

    /**
     * 예외대상PC 총 갯수 취득 및 페이징<br />
     *
     * @param voPcBasic 검색 파라미터
     * @return 토탈카운트
     */
    public String paginate_pc(VoPcBasic voPcBasic) {

        int totalRecordNo = daoIncops5.paginate_pc(voPcBasic);

        VoCoPagerHelper pagerHelper =  UtPagerUtils.paginate(voPcBasic, totalRecordNo);
        String resultHtml = UtPagerUtils.generatePagerHtml(pagerHelper, "approvalPcList");
        return resultHtml;
    }
    
    /**
     * 자신의 예외 대상 PC 1개 <br />
     * @param voPcBasic 검색 파라미터
     * @return 검색리스트
     */
    public List<VoPcBasic> getMyPcList(CoTtObjParams ttObjParams) {
        
        return daoIncops5.getMyPcList(ttObjParams);
    }

    /**
     * 사이트예외신청 <br/>
     * @param vo 검색 파라미터
     * @return 규칙리스트
     */
    public List<VoWkPolicy> getRulesListDwrAll(VoWkPolicy vo) {
        if (vo.getPageNo() > 1) {
            vo.setFirstIndex((vo.getPageNo() - 1) * vo.getViewRecordNo());
        } else {
            vo.setFirstIndex(0);
        }

        //페이징파라미터설정
        CoTtObjParams ttObjParamsSql = getParamsObjOfPageInfoSetted(vo.getFirstIndex(), vo.getRecordCountPerPage());

        return daoExctSiteDraft.getRulesListDwrAll(ttObjParamsSql);
    }

    /**
     * 사이트예외신청 <br/>
     * @param vo 검색 파라미터
     * @return 시간대리스트
     */
    public List<VoWkPolicy> getTimeListDwrAll(VoWkPolicy vo) {
        if (vo.getPageNo() > 1) {
            vo.setFirstIndex((vo.getPageNo() - 1) * vo.getViewRecordNo());
        } else {
            vo.setFirstIndex(0);
        }

        //페이징파라미터설정
        CoTtObjParams ttObjParamsSql = getParamsObjOfPageInfoSetted(vo.getFirstIndex(), vo.getRecordCountPerPage());

        return daoExctSiteDraft.getTimeListDwrAll(ttObjParamsSql);
    }

    /**
     * 해제대상 메신저 리스트 취득 <br />
     * @param voAttachDraft 검색 파라미터
     * @return 검색리스트
     */
    public List<VoAttachDraft> getObjMsgListAll(VoAttachDraft voAttachDraft) {
        if (voAttachDraft.getPageNo() > 1) {
            voAttachDraft.setFirstIndex((voAttachDraft.getPageNo() - 1) * voAttachDraft.getViewRecordNo());
        } else {
            voAttachDraft.setFirstIndex(0);
        }

        //페이징파라미터설정
        CoTtObjParams ttObjParamsSql = getParamsObjOfPageInfoSetted(voAttachDraft.getFirstIndex(), voAttachDraft.getRecordCountPerPage());
        ttObjParamsSql.put("gubun", "msgr");
        return daoExctDraft.getObjMsgListAll(ttObjParamsSql);
    }

    /**
     * 해제대상 ActiveX 리스트 취득 <br />
     * @param voAttachDraft 검색 파라미터
     * @return 검색리스트
     */
    public List<VoAttachDraft> getObjActiveXListAll(VoAttachDraft voAttachDraft) {
        if (voAttachDraft.getPageNo() > 1) {
            voAttachDraft.setFirstIndex((voAttachDraft.getPageNo() - 1) * voAttachDraft.getViewRecordNo());
        } else {
            voAttachDraft.setFirstIndex(0);
        }

        //페이징파라미터설정
        CoTtObjParams ttObjParamsSql = getParamsObjOfPageInfoSetted(voAttachDraft.getFirstIndex(), voAttachDraft.getRecordCountPerPage());
        ttObjParamsSql.put("gubun", "");
        return daoExctDraft.getObjActiveXListAll(ttObjParamsSql);
    }
    
    
    
    /**
     * 사이트 예외대상PC 리스트 취득 <br />
     * @param voPcBasic 검색 파라미터
     * @return 검색리스트
     */
    public List<VoPcBasic> getApprovalSitePcListAll(VoPcBasic voPcBasic) {
        if (voPcBasic.getPageNo() > 1) {
            voPcBasic.setFirstIndex((voPcBasic.getPageNo() - 1) * voPcBasic.getViewRecordNo());
        } else {
            voPcBasic.setFirstIndex(0);
        }

        //페이징파라미터설정
        CoTtObjParams ttObjParamsSql = getParamsObjOfPageInfoSetted(voPcBasic.getFirstIndex(), voPcBasic.getRecordCountPerPage());
        //ttObjParamsSql.put("sdeptNm", voPcBasic.getSdeptNm());
        ttObjParamsSql.put("hName", voPcBasic.gethName());
        //ttObjParamsSql.put("deptNm", voPcBasic.getDeptNm());
        return daoExctSiteDraft.getApprovalSitePcListAll(ttObjParamsSql);
    }
    
    public List<VoPcBasic> getApprovalSitePcListInfo(VoPcBasic voPcBasic) {

        //페이징파라미터설정
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        ttObjParamsSql.put("userId", voPcBasic.getUserId());
        return daoExctDraft.getApprovalSitePcListInfo(ttObjParamsSql);
    }
    

    /**
     * 사이트 예외대상PC 총 갯수 취득 및 페이징<br />
     *
     * @param voPcBasic 검색 파라미터
     * @return 토탈카운트
     */
    public String paginate_sitePc(VoPcBasic voPcBasic) {

        int totalRecordNo = daoExctSiteDraft.paginate_sitePc(voPcBasic);

        VoCoPagerHelper pagerHelper =  UtPagerUtils.paginate(voPcBasic, totalRecordNo);
        String resultHtml = UtPagerUtils.generatePagerHtml(pagerHelper, "approvalPcList2");
        return resultHtml;
    }
}
