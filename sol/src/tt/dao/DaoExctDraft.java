package tt.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import tt.base.dao.TtBaseDao;
import tt.bean.VoAddUser;
import tt.bean.VoAttachDraft;
import tt.bean.VoExct;
import tt.bean.VoExctDraft;
import tt.bean.VoPcBasic;
import tt.com.CoTtObjParams;
import tt.com.bean.VoCoCodeDtl;
import tt.com.bean.VoCoDept;
import tt.com.bean.VoCoPagerHelper;
import tt.com.bean.VoCoUser;
import tt.com.utils.UtCoStringUtils;
import tt.utils.UtPagerUtils;


/**
 * <pre>
 * tt.dao
 *    |_ DaoLink.java
 *
 * DESC :
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author ks-lee
 * @Date 2013. 3. 5. 오후 6:09:59
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 3. 5.		ks-lee				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
@Repository("daoExctDraft")
public class DaoExctDraft extends TtBaseDao {

    /**
     * @param vo 파마리터
     * @return 용어관리 리스트
     */
    public List<VoExctDraft> getDraftModifyList(VoExctDraft vo) {
        return list("DaoExctDraft.getDraftModifyList." + dbType, vo);

    }

    /**
     * @param ttObjParams 파라미터
     * @return 용어관리
     */
    public VoExctDraft getExctDraftDtl(CoTtObjParams ttObjParams) {
        return (VoExctDraft) getSqlMapClientTemplate().queryForObject("DaoExctDraft.getExctDraftDtl." + dbType, ttObjParams);
    }

    /**
     * @param ttObjParams 파라미터
     * @throws Exception 예외신청문구 수정
     */
    public void modifyExctDraft(CoTtObjParams ttObjParams) throws Exception {
        update("DaoExctDraft.modifyExctDraft." + dbType , ttObjParams);
    }

    /**
     * @param ttObjParamsSql 파라미터
     * @return dwr결재자 리스트
     */
   /* @SuppressWarnings("unchecked")
    public List<VoCoUser> getApprovalUserListAll(CoTtObjParams ttObjParamsSql) {
        return list("DaoExctDraft.approval.user.list.all." + dbType, ttObjParamsSql);
    }*/
    
    @SuppressWarnings("unchecked")
    public List<VoCoUser> getApprovalUserListAll(CoTtObjParams ttObjParamsSql) {
        //return list("DaoExctDraft.approval.user.list.all." + dbType, ttObjParamsSql);
        return list("DaoExctDraft.approval.user.list.all.pop." + dbType, ttObjParamsSql);
    }

    /**
     * @param voCoUser 파라미터
     * @return dwr페이지
     */
   /* public int paginate(VoCoUser voCoUser) {

        return  (Integer) getSqlMapClientTemplate().queryForObject("DaoExctDraft.approval.user.list.all.cnt." + dbType, voCoUser);
    }*/
    
    public int paginate(VoCoUser voCoUser) {

        return  (Integer) getSqlMapClientTemplate().queryForObject("DaoExctDraft.approval.user.list.all.cnt." + dbType, voCoUser);
    }

    /**
     * @param ttObjParamsSql 파라미터
     * @return dwr결재자 리스트
     */
    @SuppressWarnings("unchecked")
    public List<VoPcBasic> getApprovalPcListAll(CoTtObjParams ttObjParamsSql) {
        return list("DaoExctDraft.approval.pc.list.all." + dbType, ttObjParamsSql);
    }

    /**
     * @param userId
     * @return 자신의 pc
     */
    public List<VoPcBasic> getApprovalPcList(CoTtObjParams ttObjParamsSql) {
        return list("DaoExctDraft.approval.pc.list." + dbType, ttObjParamsSql);
    }
    
    /**
     * @param voPcBasic 파라미터
     * @return dwr페이지
     */
    public int paginate_pc(VoPcBasic voPcBasic) {

        return  (Integer) getSqlMapClientTemplate().queryForObject("DaoExctDraft.approval.pc.list.all.cnt." + dbType, voPcBasic);
    }

    /**
     * @param ttObjParamsSql 파라미터
     * @return 로그인 사용자 정보 조회
     */
    public VoCoUser getUserDtl(CoTtObjParams ttObjParamsSql) {
        return (VoCoUser) getSqlMapClientTemplate().queryForObject("DaoExctDraft.getUserDtl." + dbType, ttObjParamsSql);
    }

//    @Override
//    public String paginate(VoExctDraft vo) {
//
//        //TODO countTotalRecord 구현필요
//        int totalRecordNo = frContentDAO.countTotalRecord(vo);
//
//        CoPagerHelperVO pagerHelper =  PagerUtils.paginate(vo, totalRecordNo);
//
//        //TODO javascriptName 타당성 검토, generatePagerHtml의 HTML 태그수정요
//        String resultHtml = PagerUtils.generatePagerHtml(pagerHelper, "contentListDwr");
//
//        return resultHtml;
//    }

    /**
     * @param voAddUser 파라미터
     * @return 초기 결재자 리스트
     */
    @SuppressWarnings("unchecked")
    public List<VoAddUser> getApprover(VoAddUser voAddUser) {
        return list("DaoExctDraft.approver.list." + dbType, voAddUser);
    }

    /**
     * @param voAddUser 파라미터
     * @return 초기 결재자 리스트
     */
    @SuppressWarnings("unchecked")
    public List<VoAddUser> getCompAdmin(VoAddUser voAddUser) {
        return list("DaoExctDraft.comp.admin.list." + dbType, voAddUser);
    }

    /**
     * @param ttObjParamsSql 파라미터
     * @return 해제대상 메신저 리스트
     */
    @SuppressWarnings("unchecked")
    public List<VoAttachDraft> getObjMsgListAll(CoTtObjParams ttObjParamsSql) {
        return list("DaoExctDraft.obj.msg.list.all." + dbType, ttObjParamsSql);
    }

    /**
     * @param ttObjParamsSql 파라미터
     * @return 해제대상 ActiveX 리스트
     */
    @SuppressWarnings("unchecked")
    public List<VoAttachDraft> getObjActiveXListAll(CoTtObjParams ttObjParamsSql) {
        return list("DaoExctDraft.obj.msg.list.all." + dbType, ttObjParamsSql);
    }
    
    /**
     * @param voAddUser 파라미터
     * @return 초기 결재자 리스트(김모세)
     */
    @SuppressWarnings("unchecked")
    public List<VoAddUser> getApproverUserList(VoAddUser voAddUser) {
        return list("DaoExctDraft.approver.user.list." + dbType, voAddUser);
    }

    /**
     * @param voAddUser 파라미터
     * @return 초기 결재자 리스트(김모세)
     */
    @SuppressWarnings("unchecked")
    public List<VoAddUser> getCompAdminUserList(VoAddUser voAddUser) {
        return list("DaoExctDraft.comp.admin.user.list." + dbType, voAddUser);
    }
    
    /**
     * @param voAddUser 파라미터
     * @return 상위부서 조회
     */
    public String getHighDeptList(VoAddUser voAddUser) {
        return (String)getSqlMapClientTemplate().queryForObject("DaoExctDraft.approver.high.dept.list."+ dbType, voAddUser);
    }
    
    @SuppressWarnings("unchecked")
    public List<VoPcBasic> getApprovalSitePcListInfo(CoTtObjParams ttObjParamsSql) {
        return list("DaoExctDraft.sitePc.basic.list.info." + dbType, ttObjParamsSql);
    }


}
