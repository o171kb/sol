package tt.dao;


import java.util.List;

import org.springframework.stereotype.Repository;
import tt.base.dao.TtBaseDao;
import tt.bean.VoPcBasic;
import tt.bean.VoPcCheck;
import tt.bean.VoSelfDiagnostic;
import tt.com.CoTtObjParams;
import tt.com.bean.VoCoCodeDtl;
import tt.com.bean.VoCoDept;
import tt.com.bean.VoCoPagerHelper;
import tt.com.utils.UtCoStringUtils;
import tt.utils.UtPagerUtils;


/**
 * <pre>
 * tt.dao
 *    |_ DaoDeptPcCheck.java
 *
 * DESC :
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author Administrator
 * @Date 2013. 5. 12. 오후 6:29:17
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 5. 12.		Administrator				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
@Repository("daoDeptPcCheck")
public class DaoDeptPcCheck extends TtBaseDao {

    /**
     * 부서PC점검현황 <br/>
     * @param ttObjParamsSql 검색 파라미터
     * @return 부서명,부서번호
     * @throws Exception 예외
     */
    public VoCoDept getMyDept(CoTtObjParams ttObjParamsSql) throws Exception {
        return (VoCoDept) getSqlMapClientTemplate().queryForObject("DaoDeptPcCheck.self.myDept." + dbType, ttObjParamsSql);
    }


    /**
     * 대상자리스트 총 갯수  <br/>
     * @param ttObjParamsSql 검색 파라미터
     * @return 대상자 토탈카운트
     * @throws Exception 예외
     */
    public int getDeptRegister(CoTtObjParams ttObjParamsSql) throws Exception {
        return (Integer) getSqlMapClientTemplate().queryForObject("DaoDeptPcCheck.self.myDept.register.totCnt." + dbType, ttObjParamsSql);
    }

    //########################### DWR ##########################################
    /**
     * 대상자리스트 취득 <br />
     * @param ttObjParamsSql 검색 파라미터
     * @return 검색리스트
     */
    @SuppressWarnings("unchecked")
    public List<VoSelfDiagnostic> chkDeptUser(CoTtObjParams ttObjParamsSql) {
        return list("DaoDeptPcCheck.group.dept.user.list." + dbType, ttObjParamsSql);
    }

    /**
     * 대상자리스트 총 갯수 취득 및 페이징 <br/>
     * @param ttObjParamsSql 검색 파라미터
     * @return 대상자 토탈카운트
     * @throws Exception 예외
     */
    public int paginateChkDeptUser(CoTtObjParams ttObjParamsSql) throws Exception {
        return (Integer) getSqlMapClientTemplate().queryForObject("DaoDeptPcCheck.group.dept.user.page." + dbType, ttObjParamsSql);
    }

    /**
     * 점검자리스트 취득 <br />
     * @param ttObjParamsSql 검색 파라미터
     * @return 검색리스트
     */
    @SuppressWarnings("unchecked")
    public List<VoSelfDiagnostic> chkDeptPcUser(CoTtObjParams ttObjParamsSql) {
        return list("DaoDeptPcCheck.group.dept.pcchk.user.list." + dbType, ttObjParamsSql);
    }
    /**
     * 점검자리스트 총 갯수 취득 및 페이징 <br />
     *
     * @param ttObjParamsSql 검색 파라미터
     * @throws Exception 예외
     * @return 토탈카운트
     */
    public int paginateChkUser(CoTtObjParams ttObjParamsSql) throws Exception {
        return (Integer) getSqlMapClientTemplate().queryForObject("DaoDeptPcCheck.group.dept.pcchk.user.page." + dbType, ttObjParamsSql);
    }



    /**
     * 점검 PC 리스트 취득 <br />
     * @param voSelfDiagnostic 검색 파라미터
     * @return 검색리스트
     */
    @SuppressWarnings("unchecked")
    public List<VoSelfDiagnostic> chkDeptPc(CoTtObjParams ttObjParamsSql) {
        return list("DaoDeptPcCheck.group.dept.chkPc.list." + dbType, ttObjParamsSql);
    }

    /**
     * 점검 PC 리스트 총 갯수 취득 및 페이징 <br />
     *
     * @param ttObjParamsSql 검색 파라미터
     * @return 토탈카운트
     */
    public int paginateChkDeptPc(CoTtObjParams ttObjParamsSql) {
        return (Integer) getSqlMapClientTemplate().queryForObject("DaoDeptPcCheck.group.dept.chkPc.page." + dbType, ttObjParamsSql);
    }


    /**
     * 취약항목 리스트 취득 <br />
     * @param ttObjParamsSql 검색 파라미터
     * @return 검색리스트
     */
    @SuppressWarnings("unchecked")
    public List<VoSelfDiagnostic> chkDeptDanger(CoTtObjParams ttObjParamsSql) {
        return list("DaoDeptPcCheck.group.dept.danger.list." + dbType, ttObjParamsSql);
    }

    /**
     * 취약항목 총 갯수 취득 및 페이징 <br />
     * @throws Exception 예외
     * @param ttObjParamsSql 검색 파라미터
     * @return 검색리스트
     */
    public int paginateChkDeptDanger(CoTtObjParams ttObjParamsSql) throws Exception {
        return (Integer) getSqlMapClientTemplate().queryForObject("DaoDeptPcCheck.group.dept.danger.page." + dbType, ttObjParamsSql);
    }


    /**
     * 부서PC점검 점검자<br/>
     * @param ttObjParamsSql 검색 파라미터
     * @return 점검자 토탈카운트
     * @throws Exception 예외
     */
//    public int getPcChkRegister(CoTtObjParams ttObjParamsSql) throws Exception {
//        return (Integer) getSqlMapClientTemplate().queryForObject("DaoDeptPcCheck.self.myDept.pcChkRegister.totCnt." + dbType, ttObjParamsSql);
//    }


    /**
     * 부서원 개인의 자가진단한 PC수 <br/>
     * @param ttObjParamsSql 검색 파라미터
     * @return 부서원 개인의 자가진단한 PC수
     * @throws Exception 예외
     */
    public int getMyPcSelfChkCnt(CoTtObjParams ttObjParamsSql) throws Exception {
        return (Integer) getSqlMapClientTemplate().queryForObject("DaoDeptPcCheck.self.check.mypc.totcnt." + dbType, ttObjParamsSql);
    }

    /**
     * 각 부서원이 자가진단한 SELF_DIAGNOSTIC 결과 테이블의 IDX <br/>
     * @param ttObjParamsSql 검색 파라미터
     * @return list
     * @throws Exception 예외
     */
    @SuppressWarnings("unchecked")
    public List<VoPcCheck> getSelfIdx(CoTtObjParams ttObjParamsSql) throws Exception {
        return list("DaoDeptPcCheck.self.diagnostic.idx.list." + dbType, ttObjParamsSql);
    }

    /**
     * 각 부서원이 점검한 내역 (true / false 일때 카운트) <br/>
     * @param ttObjParamsSql 검색 파라미터
     * @return 토탈 카운트
     * @throws Exception 예외
     */
    public int getSafetyIdx(CoTtObjParams ttObjParamsSql) throws Exception {
        return (Integer) getSqlMapClientTemplate().queryForObject("DaoDeptPcCheck.self.isSafety.totCnt." + dbType, ttObjParamsSql);
    }

    /**
     * 해당부서원 아이디 리스트 <br/>
     * @param ttObjParamsSql 검색 파라미터
     * @return 아이디 리스트
     * @throws Exception 예외
     */
    @SuppressWarnings("unchecked")
    public List<VoCoDept> getRegisterIdList(CoTtObjParams ttObjParamsSql) throws Exception {
        return list("DaoDeptPcCheck.self.myDept.registerId.list." + dbType, ttObjParamsSql);
    }

    /**
     * 부서PC점검현황<br/>
     * @param ttObjParamsSql 검색 파라미터
     * @return 점검한 부서원들의 PC 토탈카운트
     * @throws Exception 예외
     */
    public int getRegisterEachPcCnt(CoTtObjParams ttObjParamsSql) throws Exception {
        return (Integer) getSqlMapClientTemplate().queryForObject("DaoDeptPcCheck.self.myDept.regist.pcCnt." + dbType, ttObjParamsSql);
    }

}
