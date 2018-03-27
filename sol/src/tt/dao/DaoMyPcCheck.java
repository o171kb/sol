package tt.dao;

import java.util.List;

import org.springframework.stereotype.Repository;


import tt.base.dao.TtBaseDao;
import tt.bean.VoApprDetail;
import tt.bean.VoApprInfo;
import tt.bean.VoExctObjPc;
import tt.bean.VoPcCheck;
import tt.bean.VoSelfDiaGroup;
import tt.bean.VoSelfDiaItem;
import tt.bean.VoSelfDiagnostic;
import tt.com.CoTtObjParams;
import tt.com.utils.UtCoStringUtils;

/**
 * <pre>
 * tt.dao
 *    |_ DaoMyPcCheck.java
 *
 * DESC :
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author hk-kim
 * @Date 2013. 5. 6. 오후 3:46:37
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 5. 6.		hk-kim				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
@Repository("daoMyPcCheck")
public class DaoMyPcCheck extends TtBaseDao {

    /**
     * 자가진단 그룹리스트<br/>
     * @param ttObjParamsSql 검색 파라미터
     * @return 그룹리스트
     */
//    @SuppressWarnings("unchecked")
//    public List<VoSelfDiaGroup> getDiaGroup(CoTtObjParams ttObjParamsSql) {
//        return list("DaoMyPcCheck.self.group.list." + dbType, ttObjParamsSql);
//    }

    /**
     * 자가진단 아이템 리스트<br/>
     * @param  ttObjParamsSql 검색 파라미터
     * @return 그룹아이템 리스트
     */
    @SuppressWarnings("unchecked")
    public List<VoSelfDiaItem> getDiaItem(CoTtObjParams ttObjParamsSql) {
        return list("DaoMyPcCheck.self.item.list." + dbType, ttObjParamsSql);
    }

    /**
     * 자가진단 아이템 리스트 총 갯수 취득<br/>
     * @param ttObjParamsSql 검색 파라미터
     * @return 토탈카운트
     */
    public int getDiaItemTotCnt(CoTtObjParams ttObjParamsSql) {
        return (Integer) getSqlMapClientTemplate().queryForObject("DaoMyPcCheck.self.item.list.totCnt." + dbType, ttObjParamsSql);
    }

    /**
     * 자가진단 결과리스트 PK <br/>
     * @param ttObjParamsSql 검색 파라미터
     * @return SelfDiagnosticIdx list
     */
    @SuppressWarnings("unchecked")
    public List<VoPcCheck> getResultPKList(CoTtObjParams ttObjParamsSql) {
        return list("DaoMyPcCheck.self.result.pk.list." + dbType, ttObjParamsSql);
    }

    /**
     * 안전/취약 결과 <br/>
     * @param ttObjParamsSql 검색 파라미터
     * @return 결과 카운트
     */
    @SuppressWarnings("unchecked")
    public List<VoPcCheck> getSafetyList(CoTtObjParams ttObjParamsSql) {
        return list("DaoMyPcCheck.self.isSafety.cnt." + dbType, ttObjParamsSql);
    }


    /**
     * 자가진단 결과리스트 <br/>
     * @param ttObjParamsSql 검색 파라미터
     * @return 결과리스트
     */
    @SuppressWarnings("unchecked")
    public List<VoPcCheck> getResultList(CoTtObjParams ttObjParamsSql) {
        return list("DaoMyPcCheck.self.result.list." + dbType, ttObjParamsSql);
    }

    /**
     * 자가진단 결과리스트 총 갯수 취득 <br/>
     * @param ttObjParamsSql 검색 파라미터
     * @return 토탈카운트
     */
    public int getResultListTotCnt(CoTtObjParams ttObjParamsSql) {
        return (Integer) getSqlMapClientTemplate().queryForObject("DaoMyPcCheck.self.result.list.totCnt." + dbType, ttObjParamsSql);
    }


    /**
     * 자가진단 PC정보 등록 <br/>
     * @param ttObjParamsSql 검색 파라미터
     * @return 진단PC정보
     * @throws Exception 예외
     */
    public int insertMyPcCheck(CoTtObjParams ttObjParamsSql) throws Exception {

        int param = (Integer) insert("DaoMyPcCheck.self.myPc.check." + dbType , ttObjParamsSql);

        System.out.println(param);

        return param;
    }

    /**
     * 자가진단 PC점검 결과등록 <br/>
     * @param ttObjParamsSql 검색 파라미터
     * @throws Exception 예외
     */
    public void insertPcCheckResult(CoTtObjParams ttObjParamsSql) throws Exception {
        insert("DaoMyPcCheck.self.myPc.check.result." + dbType , ttObjParamsSql);
    }

    /**
     * 자가진단 PC점검 안전PC결과 등록 <br/>
     * @param ttObjParamsSql 검색 파라미터
     * @throws Exception 예외
     */
    public void updateAllSafety(CoTtObjParams ttObjParamsSql) throws Exception {
        update("DaoMyPcCheck.myPc.allSafety.result." + dbType, ttObjParamsSql);
    }

    /**
     * 자가진단 그룹아이템 ChkValue 리스트 <br/>
     * @param params 검색 파라미터
     * @return 토탈카운트
     */
    @SuppressWarnings("unchecked")
    public List<VoPcCheck> getItemChkValue(CoTtObjParams params) {
        return list("DaoMyPcCheck.self.itemId.chkValue.list." + dbType, params);
    }


}
