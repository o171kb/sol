package tt.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;


import tt.base.dao.TtBaseDao;
import tt.bean.VoApprDetail;
import tt.bean.VoApprInfo;
import tt.bean.VoExctObjPc;
import tt.com.CoTtObjParams;
import tt.com.bean.VoCoUser;
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
@Repository("daoMyApproval")
public class DaoMyApproval extends TtBaseDao {

    /**
     * 나의결재현황 <br />
     * @param ttObjParamsSql 나의신청현황정보
     * @return 나의신청현황list
     */
    @SuppressWarnings("unchecked")
    public List<VoApprInfo> getMyApprovalList(CoTtObjParams ttObjParamsSql) {
        return list("DaoMyApproval.appr.search.list." + dbType, ttObjParamsSql);
    }
    
    @SuppressWarnings("unchecked")
    public List<VoApprInfo> getMyApprovalList(CoTtObjParams ttObjParamsSql, List<VoApprInfo> vo) {
        ttObjParamsSql.put("deptList", vo);
        return list("DaoMyApproval.appr.search.list2." + dbType, ttObjParamsSql);
    }

    /**
     * 나의결재현황 총 갯수 취득 <br />
     * @param ttObjParamsSql 나의신청현황정보
     * @return 토탈카운트
     */
    public int getMyApprovalListTotCnt(CoTtObjParams ttObjParamsSql) {
        return (Integer) getSqlMapClientTemplate().queryForObject("DaoMyApproval.appr.search.page.cnt." + dbType, ttObjParamsSql);
    }
    
    public int getMyApprovalListTotCnt(CoTtObjParams ttObjParamsSql, List<VoApprInfo> vo) {
        ttObjParamsSql.put("deptList", vo);
        return (Integer) getSqlMapClientTemplate().queryForObject("DaoMyApproval.appr.search.page.cnt2." + dbType, ttObjParamsSql);
    }

    /**
     * 나의결재황 상세 <br />
     * @param ttObjParamsSql 나의신청현황정보
     * @return 나의신청현황 상세화면
     */
    public VoApprInfo getMyDraftDetail(CoTtObjParams ttObjParamsSql) {
        return (VoApprInfo) selectByPk("DaoMyDraft.draft.detail." + dbType, ttObjParamsSql);
    }

    /**
     * 나의결재황 상세 <br />
     * @param coTtObjParams 나의신청현황정보
     * @return 나의신청현황 상세화면
     */
    public VoCoUser getDrafterDtl(CoTtObjParams coTtObjParams) {
        return (VoCoUser) selectByPk("DaoMyApproval.get.drafter.dtl." + dbType, coTtObjParams);
    }

    /**
     * @param ttObjParamsSql 나의신청현황정보
     * @return 결재자 상세 정보
     */
    public VoApprInfo getApprover(CoTtObjParams ttObjParamsSql) {
        return (VoApprInfo) getSqlMapClientTemplate().queryForObject("DaoMyApproval.getApprover." + dbType, ttObjParamsSql);
    }

    /**
     * @param ttObjParamsSql 결재승인정보
     * @throws Exception 예외
     */
    public void rejectRequestApprInfo(CoTtObjParams ttObjParamsSql) throws Exception {
        update("DaoMyApproval.update.reject.req.apprInfo." + dbType , ttObjParamsSql);
    }

    /**
     * @param ttObjParamsSql 결재승인정보
     * @throws Exception 예외
     */
    public void rejectRequestApprover(CoTtObjParams ttObjParamsSql) throws Exception {
        update("DaoMyApproval.update.reject.req.approver." + dbType , ttObjParamsSql);
    }

    /**
     * @param ttObjParamsSql 결재아이디
     * @return 결재자 리스트
     */
    @SuppressWarnings("unchecked")
    public List<VoApprInfo> selectApproverList(CoTtObjParams ttObjParamsSql) {
        return list("DaoMyApproval.approver.list." + dbType, ttObjParamsSql);
    }

    /**
     * @param ttObjParamsSql 결재항목 정보
     * @return 결재정보
     */
    public VoApprInfo selectApprovalInfo(CoTtObjParams ttObjParamsSql) {
        return (VoApprInfo) getSqlMapClientTemplate().queryForObject("DaoMyApproval.approval.info." + dbType, ttObjParamsSql);
    }

    /**
     * @param ttObjParamsSql 결재항목 정보
     * @return Escort 결재상세 리스트
     */
    @SuppressWarnings("unchecked")
    public List<VoApprDetail> selectApprDtlList(CoTtObjParams ttObjParamsSql) {
        return list("DaoMyApproval.appr.dtl.list." + dbType, ttObjParamsSql);
    }

    /**
     * @param ttObjParamsSql 결재항목 정보
     * @return 첨부통제 결재상세 리스트
     */
    @SuppressWarnings("unchecked")
    public List<VoApprDetail> selectAttachApprDtlList(CoTtObjParams ttObjParamsSql) {
        return list("DaoMyApproval.attach.appr.dtl.list." + dbType, ttObjParamsSql);
    }

    /**
     * @param ttObjParamsSql 결재항목 정보
     * @return 결재대상PC 리스트
     */
    @SuppressWarnings("unchecked")
    public List<VoExctObjPc> selectApprObjPcList(CoTtObjParams ttObjParamsSql) {
        return list("DaoMyApproval.appr.obj.pc.list." + dbType, ttObjParamsSql);
    }

    /**
     * @param ttObjParamsSql 결재정보
     * @throws Exception exception 프로시저호출
     */
    public void callProcedureApprException(CoTtObjParams ttObjParamsSql) throws Exception {
        update("DaoMyApproval.call.pro.appr.exception." + dbType , ttObjParamsSql);
    }

    /**
     * @param ttObjParamsSql 결재정보
     * @throws Exception storerule 프로시저호출
     */
    public void callProcedureApprStorerule(CoTtObjParams ttObjParamsSql) throws Exception {
        update("DaoMyApproval.call.pro.appr.storerule." + dbType , ttObjParamsSql);
    }

    /**
     * @param ttObjParamsSql 결재정보
     * @throws Exception AP 프로시저호출
     */
    public void callProcedureApprExceptionAP(CoTtObjParams ttObjParamsSql) throws Exception {
        update("DaoMyApproval.call.pro.appr.exceptionAP." + dbType , ttObjParamsSql);
    }

    /**
     * @param ttObjParamsSql 결재상태 업데이트
     * @throws Exception 예외
     */
    public void updateApporvoerStatus(CoTtObjParams ttObjParamsSql) throws Exception {
        update("DaoMyApproval.update.approver.status." + dbType , ttObjParamsSql);
    }

    /**
     * @param ttObjParamsSql 결재상태 업데이트
     * @throws Exception 예외
     */
    public void updateApprInfoStatus(CoTtObjParams ttObjParamsSql) throws Exception {
        update("DaoMyApproval.update.appr.info.status." + dbType , ttObjParamsSql);
    }

    /**
     * @param ttObjParamsSql 결재정보
     * @throws Exception ICAT 프로시저호출
     */
    public void callProcedureExceptionICAT(CoTtObjParams ttObjParamsSql) throws Exception {
        update("DaoMyApproval.call.pro.exceptionICAT." + dbType , ttObjParamsSql);
    }

    /**
     * 사이트예외신청액션<br/>
     * @param ttObjParamsSql 파라미터
     * @throws Exception Escort 예외신청
     */
    public void insertApprExceptionSite(CoTtObjParams ttObjParamsSql) throws Exception {
        insert("DaoMyApproval.insert.appr.exception.site." + dbType , ttObjParamsSql);
    }

    /**
     * 사이트예외신청 수정액션<br/>
     * @param ttObjParamsSql 파라미터
     * @throws Exception Escort 예외신청
     */
    public void updateApprExceptionSite(CoTtObjParams ttObjParamsSql) throws Exception {
        update("DaoMyApproval.update.appr.exception.site." + dbType , ttObjParamsSql);
    }
    
    @SuppressWarnings("unchecked")
    public List<VoApprInfo> getLowDeptList(CoTtObjParams vo) {
        return list("DaoMyApproval.draft.lowDept.list." + dbType, vo);
    }
    
    @SuppressWarnings("unchecked")
    public List<VoApprInfo> getLowOrDeptList(Map vo) {
        return list("DaoMyApproval.draft.lowOrDept.list." + dbType, vo);
    }


}
