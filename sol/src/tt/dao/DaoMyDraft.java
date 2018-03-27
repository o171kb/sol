package tt.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;


import tt.base.dao.TtBaseDao;
import tt.bean.VoApprDetail;
import tt.bean.VoApprInfo;
import tt.bean.VoExctObjPc;
import tt.com.CoTtObjParams;
import tt.com.utils.UtCoStringUtils;

/**
 * <pre>
 * tt.dao
 *    |_ DaoMyDraft.java
 *
 * DESC :
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author hk-kim
 * @Date 2013. 4. 15. 오후 8:38:21
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 4. 15.		hk-kim				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
@Repository("daoMyDraft")
public class DaoMyDraft extends TtBaseDao {

    /**
     * 나의신청현황 <br />
     * @param ttObjParamsSql 나의신청현황정보
     * @return 나의신청현황list
     */
    @SuppressWarnings("unchecked")
    public List<VoApprInfo> getMyDraftList(CoTtObjParams ttObjParamsSql) {
        return list("DaoMyDraft.draft.search.page.list." + dbType, ttObjParamsSql);
    }
    
    @SuppressWarnings("unchecked")
    public List<VoApprInfo> getMyDraftList(CoTtObjParams ttObjParamsSql, List<VoApprInfo> vo) {
        ttObjParamsSql.put("deptList", vo);
        return list("DaoMyDraft.draft.search.page.list2." + dbType, ttObjParamsSql);
    }

    /**
     * 나의신청현황 총 갯수 취득 <br />
     * @param ttObjParamsSql 나의신청현황정보
     * @return 토탈카운트
     */
    public int getMyDraftListTotCnt(CoTtObjParams ttObjParamsSql) {
        return (Integer) getSqlMapClientTemplate().queryForObject("DaoMyDraft.draft.search.page.cnt." + dbType, ttObjParamsSql);
    }
    
    /**
     * 나의신청현황 총 갯수 취득 <br />
     * @param ttObjParamsSql 나의신청현황정보
     * @return 토탈카운트
     */
    public int getMyDraftListTotCnt(CoTtObjParams ttObjParamsSql, List<VoApprInfo> vo) {
        ttObjParamsSql.put("deptList", vo);
        return (Integer) getSqlMapClientTemplate().queryForObject("DaoMyDraft.draft.search.page.cnt2." + dbType, ttObjParamsSql);
    }


    /**
     * 나의신청현황 상세 <br />
     * @param ttObjParamsSql 나의신청현황정보
     * @return 나의신청현황 상세화면
     */
    public VoApprInfo getMyDraftDetail(CoTtObjParams ttObjParamsSql) {
        return (VoApprInfo) selectByPk("DaoMyDraft.draft.detail." + dbType, ttObjParamsSql);
    }

    /**
     * 나의신청현황 상세 - 결제자정보 <br />
     * @param ttObjParamsSql 나의신청현황정보
     * @return 결제자정보
     */
    @SuppressWarnings("unchecked")
    public List<VoApprInfo> getApproverList(CoTtObjParams ttObjParamsSql) {
        return list("DaoMyDraft.draft.approver.list." + dbType, ttObjParamsSql);
    }


    /**
     * 나의신청현황 상세 - 예외대상PC <br />
     * @param ttObjParamsSql 나의신청현황정보
     * @return 예외대상PC정보
     */
    @SuppressWarnings("unchecked")
    public List<VoExctObjPc> getApprovalPcList(CoTtObjParams ttObjParamsSql) {
        return list("DaoMyDraft.draft.pc.list." + dbType, ttObjParamsSql);
    }

    /**
     * 나의신청현황 상세 - 신청항목리스트 <br />
     * @param ttObjParamsSql 나의신청현황정보
     * @return 신청정보
     */
    @SuppressWarnings("unchecked")
    public List<VoApprDetail> getApprDetailList(CoTtObjParams ttObjParamsSql) {
        return list("DaoMyDraft.draft.term.list." + dbType, ttObjParamsSql);
    }
    
    @SuppressWarnings("unchecked")
    public List<VoApprInfo> getMyDeptList(VoApprInfo vo) {
        return list("DaoMyDraft.draft.dept.list." + dbType, vo);
    }
    
    @SuppressWarnings("unchecked")
    public List<VoApprInfo> getLowDeptList(CoTtObjParams vo) {
        return list("DaoMyDraft.draft.lowDept.list." + dbType, vo);
    }
    
    @SuppressWarnings("unchecked")
    public List<VoApprInfo> getLowOrDeptList(Map vo) {
        return list("DaoMyDraft.draft.lowOrDept.list." + dbType, vo);
    }
    
}
