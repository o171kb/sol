package tt.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import tt.base.dao.TtBaseDao;
import tt.bean.VoExct;
import tt.bean.VoExctDraft;
import tt.bean.VoPcBasic;
import tt.com.CoTtObjParams;
import tt.com.bean.VoCoUser;


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
@Repository("daoExctEscortDraft")
public class DaoExctEscortDraft extends TtBaseDao {

      public VoExctDraft getExctEscortDraft(VoExctDraft vo) {
          return (VoExctDraft) getSqlMapClientTemplate().queryForObject("DaoExctEscortDraft.getExctEscortDraft." + dbType, vo);
      }

      /**
       * MAX Appr_Id <br />
       * @return MAX Appr_Id
       */
      public String getLatestApprId() {
          return (String) getSqlMapClientTemplate().queryForObject(
                  "DaoExctEscortDraft.getLatestApprId." + dbType);
      }
      /**
       * @param ttObjParamsSql 파라미터
       * @throws Exception Escort 예외신청
       */
      public void insertApprover(CoTtObjParams ttObjParamsSql) throws Exception {
          update("DaoExctEscortDraft.insertApprover." + dbType , ttObjParamsSql);
      }

      /**
       * @param ttObjParamsSql 파라미터
       * @throws Exception Escort 예외신청
       */
      public void insertApprObjPc(CoTtObjParams ttObjParamsSql) throws Exception {
          insert("DaoExctEscortDraft.insertApprObjPc." + dbType , ttObjParamsSql);
      }

      /**
       * @param ttObjParamsSql 파라미터
       * @throws Exception Escort 예외신청
       */
      public void insertApprInfo(CoTtObjParams ttObjParamsSql) throws Exception {
          insert("DaoExctEscortDraft.insertApprInfo." + dbType , ttObjParamsSql);
      }

      /**
       * @param ttObjParamsSql 파라미터
       * @return  VoExct 예외정책 상세
       */
      public VoExct selectApprDetail(CoTtObjParams ttObjParamsSql) {
          return (VoExct) getSqlMapClientTemplate().queryForObject("DaoExct.getExctStringDtl." + dbType, ttObjParamsSql);
      }

      /**
       * @param ttObjParamsSql 파라미터
       * @throws Exception Escort 예외신청
       */
      public void insertApprDetail(CoTtObjParams ttObjParamsSql) throws Exception {
          insert("DaoExctEscortDraft.insertApprDetail." + dbType , ttObjParamsSql);
      }

      public VoCoUser getProxyApprover(CoTtObjParams ttObjParamsSql) {
          return (VoCoUser) getSqlMapClientTemplate().queryForObject("DaoExctDraft.approval.user.list.all." + dbType, ttObjParamsSql);
      }



    /**
     * @param vo 파마리터
     * @return 용어관리 리스트
     */
//    public List<VoExctDraft> getDraftModifyList(VoExctDraft vo) {
//        return list("DaoExctDraft.getDraftModifyList." + dbType, vo);
//
//    }

    /**
     * @param ttObjParams 파라미터
     * @return 용어관리
     */
//    public VoExctDraft getExctDraftDtl(CoTtObjParams ttObjParams) {
//        return (VoExctDraft)getSqlMapClientTemplate().queryForObject("DaoExctDraft.getExctDraftDtl." + dbType, ttObjParams);
//    }

    /**
     * @param ttObjParams 파라미터
     * @throws Exception 예외신청문구 수정
     */
//    public void modifyExctDraft(CoTtObjParams ttObjParams) throws Exception {
//        update("DaoExctDraft.modifyExctDraft." + dbType , ttObjParams);
//    }
      
      public String getEmail(String id) {
          return (String) getSqlMapClientTemplate().queryForObject("DaoExctEscortDraft.getEmail." + dbType, id);
      }
}
