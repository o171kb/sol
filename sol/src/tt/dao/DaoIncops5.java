/*
 * Copyright (C) 2005-2013 THINK TREE. All Rights Reserved.
 *
 * This software is the confidential and proprietary information
 * of korea.think-tree.com.,LTD. ("Confidential Information").
 */
package tt.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import tt.bean.VoApprInfo;
import tt.bean.VoExctDraft;
import tt.bean.VoPcBasic;
import tt.com.CoTtObjParams;

/**
 * <pre>
 * tt.com.dao
 *    |_ DaoCoDept.java
 *
 * DESC : 부서관련 DAO 클래스 <br />
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author ks-lee
 * @Date 2013. 3. 8. 오전 10:01:47
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 3. 8.		ks-lee				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
@Repository("daoIncops5")
public class DaoIncops5 extends TtIncops5BaseDao {

    @SuppressWarnings("unchecked")
    public List<CoTtObjParams> getDeptList() {
        return list("incops5.dept.list." + dbType, null);
    }


    /**
     * @param ttObjParamsSql 파라미터
     * @return dwr결재자 리스트
     */
    @SuppressWarnings("unchecked")
    public List<VoPcBasic> getApprovalPcListAll(CoTtObjParams ttObjParamsSql) {
        return list("incops5.pc.basic.list.all." + dbType, ttObjParamsSql);
    }

    /**
     * @param voPcBasic 파라미터
     * @return dwr페이지
     */
    public int paginate_pc(VoPcBasic voPcBasic) {

        return  (Integer) getSqlMapClientTemplate().queryForObject("incops5.pc.basic.list.all.cnt." + dbType, voPcBasic);
    }

    /**
     * @param ttObjParamsSql 파라미터
     * @return dwr결재자 리스트
     */
    @SuppressWarnings("unchecked")
    public List<VoPcBasic> getMyPcList(CoTtObjParams ttObjParamsSql) {
        return list("incops5.pc.basic.obj.all." + dbType, ttObjParamsSql);
    }

    /**
     * @param ttObjParamsSql 파라미터
     * @return  VoPcBasic
     */
    public VoPcBasic selectPcBasic(CoTtObjParams ttObjParamsSql) {
        return (VoPcBasic) getSqlMapClientTemplate().queryForObject("incops5.selectPcBasic." + dbType, ttObjParamsSql);
    }

    /**
     * @param ttObjParamsSql 조회항목 정보
     * @return 추가 HDD 시리얼 중복체크
     */
    public int selectChkSameADDHDD(CoTtObjParams ttObjParamsSql) {
        return (Integer) getSqlMapClientTemplate().queryForObject("DaoMyApproval.select.chk.same.ADDHDD." + dbType, ttObjParamsSql);
    }

    /**
     * @param ttObjParamsSql 결재정보
     * @throws Exception 추가 HDD 업데이트
     */
    public void updateApprExceptionADDHDD(CoTtObjParams ttObjParamsSql) throws Exception {
        update("DaoMyApproval.update.appr.exception.ADDHDD." + dbType , ttObjParamsSql);
    }

    /**
     * @param ttObjParamsSql 결재정보
     * @throws Exception 추가 HDD 예외적용
     */
    public void insertApprExceptionADDHDD(CoTtObjParams ttObjParamsSql) throws Exception {
        update("DaoMyApproval.insert.appr.exception.ADDHDD." + dbType , ttObjParamsSql);
    }

    /**
     * @param ttObjParamsSql 조회항목 정보
     * @return 반출잠김 시리얼 중복체크
     */
    public int selectChkSameExceptionVPN(CoTtObjParams ttObjParamsSql) {
        return (Integer) getSqlMapClientTemplate().queryForObject("DaoMyApproval.select.chk.same.vpn." + dbType, ttObjParamsSql);
    }

    /**
     * @param ttObjParamsSql 결재정보
     * @throws Exception 반출잠김 업데이트
     */
    public void updateApprExceptionVPN(CoTtObjParams ttObjParamsSql) throws Exception {
        update("DaoMyApproval.update.appr.exception.vpn." + dbType , ttObjParamsSql);
    }

    /**
     * @param ttObjParamsSql 결재정보
     * @throws Exception 반출잠김 예외적용
     */
    public void insertApprExceptionVPN(CoTtObjParams ttObjParamsSql) throws Exception {
        update("DaoMyApproval.insert.appr.exception.vpn." + dbType , ttObjParamsSql);
    }


    /**
     * 부서원 개인PC 보유수 <br/>
     * @param ttObjParamsSql 검색 파라미터
     * @return 개인PC 토탈카운트
     * @throws Exception 예외
     */
    public int getIncops5MyPcCnt(CoTtObjParams ttObjParamsSql) throws Exception {
      return (Integer) getSqlMapClientTemplate().queryForObject("DaoDeptPcCheck.incops5.mypc.totcnt." + dbType, ttObjParamsSql);
    }


    //####################################### 자가진단 DWR ##################
    /**
     * 점검자중 결과리스트 취득 <br />
     * @param ttObjParamsSql 검색 파라미터
     * @return list
     */
    @SuppressWarnings("unchecked")
    public List<VoPcBasic> getIncops5MyPc(CoTtObjParams ttObjParamsSql) {
        return list("DaoDeptPcCheck.incops5.group.dept.pcchk.user." + dbType, ttObjParamsSql);
    }

    /**
     * 부서원 개인PC 정보 <br/>
     * @param ttObjParamsSql 검색 파라미터
     * @return 부서원 개인PC 정보
     * @throws Exception 예외
     */
    @SuppressWarnings("unchecked")
    public List<VoPcBasic> getIncops5MyPcInfoList(CoTtObjParams ttObjParamsSql) throws Exception {
        return list("DaoDeptPcCheck.incops5.mypc.info.list." + dbType, ttObjParamsSql);
    }

    /**
     * @param ttObjParamsSql 파라미터
     * @return dwr결재자 리스트
     */
    @SuppressWarnings("unchecked")
    public List<VoPcBasic> getApprovalSitePcListAll(CoTtObjParams ttObjParamsSql) {
        return list("incops5.sitePc.basic.list.all." + dbType, ttObjParamsSql);
    }

    /**
     * @param voPcBasic 파라미터
     * @return dwr페이지
     */
    public int paginate_sitePc(VoPcBasic voPcBasic) {

        return  (Integer) getSqlMapClientTemplate().queryForObject("incops5.sitePc.basic.list.all.cnt." + dbType, voPcBasic);
    }


}
