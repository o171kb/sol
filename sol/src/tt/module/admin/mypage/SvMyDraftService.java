package tt.module.admin.mypage;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import tt.base.module.TtBaseService;
import tt.bean.VoApprDetail;
import tt.bean.VoApprInfo;
import tt.bean.VoExctObjPc;
import tt.com.CoTtObjParams;
import tt.com.bean.VoCoUser;
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
@Service("svMyDraftService")
public class SvMyDraftService extends TtBaseService  {

    /**
     * 나의신청현황 <br />
     * @param ttObjParams 나의신청현황정보
     * @return 나의신청현황list
     */
    public List<VoApprInfo> getMyDraftList(CoTtObjParams ttObjParams) {

        VoApprInfo voApprInfo = (VoApprInfo) ttObjParams.get("voApprInfo");

        //페이징파라미터설정
        CoTtObjParams ttObjParamsSql = getParamsObjOfPageInfoSetted(voApprInfo.getFirstIndex(), voApprInfo.getRecordCountPerPage());
        ttObjParamsSql.put("userId", UtCoStringUtils.trim(voApprInfo.getUserId()));
        ttObjParamsSql.put("searchStartDm", voApprInfo.getSearchStartDm());
        ttObjParamsSql.put("searchEndDm", voApprInfo.getSearchEndDm());
        ttObjParamsSql.put("searchStatus", voApprInfo.getSearchStatus());
        ttObjParamsSql.put("searchGubun", voApprInfo.getSearchGubun());
        ttObjParamsSql.put("searchUserNm", voApprInfo.getSearchUserNm());
        ttObjParamsSql.put("searchDeptNo", voApprInfo.getDeptNo());
        ttObjParamsSql.put("deptCd", voApprInfo.getDeptCd());
        
        ttObjParamsSql.put("tabDiv", voApprInfo.getTabDiv());
        
        System.out.println("tabDiv:::::::::::::::::::::::::::::::"+ ttObjParamsSql.get("tabDiv"));
        
        if(ttObjParamsSql.get("tabDiv") == null){
            ttObjParamsSql.put("tabDiv", "myReq");
        }
        
        System.out.println("tabDiv:::::::::::::::::::::::::::::::"+ ttObjParamsSql.get("tabDiv"));
        
                
       /* List<VoApprInfo> lowDeptList = daoMyDraft.getLowDeptList(ttObjParamsSql);
        String[] arrDeptCd = new String[lowDeptList.size()];*/
       /* for(int i=0;i<lowDeptList.size();i++)
        {
            arrDeptCd[i] = (String)lowDeptList.get(0).getDeptCd();
            ttObjParamsSql.put("deptCd", arrDeptCd[i]);
            List<VoApprInfo> lowDeptList2 = daoMyDraft.getLowDeptList(ttObjParamsSql);
            String[] arrLowDeptCd = new String[lowDeptList.size()];
        }*/
        if(ttObjParamsSql.get("tabDiv").equals("deptReq"))
        {
            ttObjParamsSql.put("deptCd", ttObjParamsSql.get("deptCd"));
            List<VoApprInfo> lowDeptList = daoMyDraft.getLowDeptList(ttObjParamsSql);
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
                tempList = daoMyDraft.getLowOrDeptList(paramMap);
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
            return daoMyDraft.getMyDraftList(ttObjParamsSql, resultDeptList);
        }

        return daoMyDraft.getMyDraftList(ttObjParamsSql);
    }

    /**
     * 나의신청현황 총 갯수 취득 <br />
     * @param ttObjParams 나의신청현황정보
     * @return 토탈카운트
     */
    public int getMyDraftListTotCnt(CoTtObjParams ttObjParams) {

        VoApprInfo voApprInfo = (VoApprInfo) ttObjParams.get("voApprInfo");

        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        ttObjParamsSql.put("userId", UtCoStringUtils.trim(voApprInfo.getUserId()));
        ttObjParamsSql.put("searchStartDm", voApprInfo.getSearchStartDm());
        ttObjParamsSql.put("searchEndDm", voApprInfo.getSearchEndDm());
        ttObjParamsSql.put("searchStatus", voApprInfo.getSearchStatus());
        ttObjParamsSql.put("searchGubun", voApprInfo.getSearchGubun());
        ttObjParamsSql.put("searchUserNm", voApprInfo.getSearchUserNm());
        ttObjParamsSql.put("searchDeptNo", voApprInfo.getDeptNo());
        ttObjParamsSql.put("deptCd", voApprInfo.getDeptCd());
        
        ttObjParamsSql.put("tabDiv", voApprInfo.getTabDiv());
        
        if(ttObjParamsSql.get("tabDiv") == "" || ttObjParamsSql.get("tabDiv") == null){
            ttObjParamsSql.put("tabDiv", "myReq");
        }
        
        if(ttObjParamsSql.get("tabDiv").equals("deptReq"))
        {
            ttObjParamsSql.put("deptCd", ttObjParamsSql.get("deptCd"));
            List<VoApprInfo> lowDeptList = daoMyDraft.getLowDeptList(ttObjParamsSql);
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
                tempList = daoMyDraft.getLowOrDeptList(paramMap);
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
            return daoMyDraft.getMyDraftListTotCnt(ttObjParamsSql, resultDeptList);
        }

        return daoMyDraft.getMyDraftListTotCnt(ttObjParamsSql);
    }

    /**
     * 나의신청현황 상세 <br />
     * @param ttObjParams 나의신청현황정보
     * @return 나의신청현황 상세화면
     */
    public VoApprInfo getMyDraftDetail(CoTtObjParams ttObjParams) {
        VoApprInfo vo = (VoApprInfo) ttObjParams.get("voApprInfo");
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        ttObjParamsSql.put("apprId", UtCoStringUtils.trim(vo.getApprId()));

        return daoMyDraft.getMyDraftDetail(ttObjParamsSql);
    }

    /**
     * 나의신청현황 상세 - 결제자정보 <br />
     * @param ttObjParams 나의신청현황정보
     * @return 결제자정보
     */
    public List<VoApprInfo> getApproverList(CoTtObjParams ttObjParams) {

        VoApprInfo vo = (VoApprInfo) ttObjParams.get("voApprInfo");
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        ttObjParamsSql.put("apprId", UtCoStringUtils.trim(vo.getApprId()));
        return daoMyDraft.getApproverList(ttObjParamsSql);
    }

    /**
     * 나의신청현황 상세 - 예외대상PC <br />
     * @param ttObjParams 나의신청현황정보
     * @return 예외대상PC정보
     */
    public List<VoExctObjPc> getApprovalPcList(CoTtObjParams ttObjParams) {
        VoApprInfo vo = (VoApprInfo) ttObjParams.get("voApprInfo");
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        ttObjParamsSql.put("apprId", UtCoStringUtils.trim(vo.getApprId()));
        return daoMyDraft.getApprovalPcList(ttObjParamsSql);
    }

    /**
     * 나의신청현황 상세 - 신청항목리스트 <br />
     * @param ttObjParams 나의신청현황정보
     * @return 신청정보
     */
    public List<VoApprDetail> getApprDetailList(CoTtObjParams ttObjParams) {
        VoApprInfo vo = (VoApprInfo) ttObjParams.get("voApprInfo");
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        ttObjParamsSql.put("apprId", UtCoStringUtils.trim(vo.getApprId()));
        return daoMyDraft.getApprDetailList(ttObjParamsSql);
    }
    
    public List<VoApprInfo> getMyDeptList(VoApprInfo vo) {
        return daoMyDraft.getMyDeptList(vo);
    }

}