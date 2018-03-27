/*
 * Copyright (C) 2005-2013 THINK TREE. All Rights Reserved.
 *
 * This software is the confidential and proprietary information
 * of korea.think-tree.com.,LTD. ("Confidential Information").
 */
package tt.com.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import tt.base.dao.TtBaseDao;
import tt.com.CoTtObjParams;
import tt.com.bean.VoCoDept;
import tt.com.utils.UtCoStringUtils;

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
@Repository("daoCoDept")
public class DaoCoDept extends TtBaseDao {

    /**
     * 부서정보취득 (팝업용) <br />
     *
     * @param ttObjParamsSql 부서검색정보
     * @return 부서정보
     */
    @SuppressWarnings("unchecked")
    public List<VoCoDept> getDeptSearchListAll(CoTtObjParams ttObjParamsSql) {
        return list("DaoCoDept.dept.list.all." + dbType, ttObjParamsSql);
    }

    /**
     * 부서검색정보취득 페이지 <br />
     *
     * @param ttObjParamsSql 부서검색정보
     * @return 부서정보
     */
    @SuppressWarnings("unchecked")
    public List<VoCoDept> getDeptSearchListForPage(CoTtObjParams ttObjParamsSql) {
        return list("DaoCoDept.dept.search.page.list." + dbType, ttObjParamsSql);
    }

    /**
     * 부서검색정보 총갯수 취득 <br />
     *
     * @param ttObjParamsSql 부서검색정보
     * @return 부서정보
     */
    public int getDeptSearchListTotCnt(CoTtObjParams ttObjParamsSql) {
        return (Integer) getSqlMapClientTemplate().queryForObject(
                "DaoCoDept.dept.search.cnt." + dbType, ttObjParamsSql);
    }

    /**
     * 부서를 (논리)삭제한다. <br />
     * @param ttObjParamsSql 삭제정보
     */
    public void deleteDept(CoTtObjParams ttObjParamsSql) {
        update("DaoCoDept.dept.delete." + dbType, ttObjParamsSql);
    }

    /**
     * 부서CD로 상세를 조회한다 <br />
     * @param ttObjParamsSql 검색 파라미터
     * @return 검색부서
     */
    public VoCoDept getDeptDtl(CoTtObjParams ttObjParamsSql) {
        return (VoCoDept) selectByPk("DaoCoDept.dept.search.dtl." + dbType, ttObjParamsSql);
    }

    /**
     * 부서정보를 수정한다 <br />
     * @param ttObjParamsSql 수정정보
     */
    public void updateDeptInfo(CoTtObjParams ttObjParamsSql) {
        update("DaoCoDept.dept.update." + dbType, ttObjParamsSql);

    }

    /**
     * 부서코드 중복을 조회한다.
     * @param ttObjParamsSql 검색정보
     * @return 체크 결과 -1:사용불가, 0:검색문자열"", 1:사용가능
     */
    public int checkDeptCd(CoTtObjParams ttObjParamsSql) {

        if (UtCoStringUtils.isEmpty(ttObjParamsSql.getString("deptCd"))) {
            return 0;
        }

        VoCoDept checkVO = (VoCoDept) selectByPk("DaoCoDept.dept.search.cd." + dbType, ttObjParamsSql);

        if (checkVO == null) {
            return 1;
        }
        return -1;
    }

    /**
     * 부서정보를 등록한다 <br />
     * @param ttObjParamsSql 등록정보
     */
    public void insertDeptInfo(CoTtObjParams ttObjParamsSql) {
        insert("DaoCoDept.dept.insert." + dbType, ttObjParamsSql);
    }

    /**
     * 부서등록용 상위부서가 가지고 있는 부서수 + 1 <br />
     * @param ttObjParamsSql 검색정보
     * @return 검색수
     */
    public int getHighDeptCnt(CoTtObjParams ttObjParamsSql) {
        return (Integer) getSqlMapClientTemplate().queryForObject(
                "DaoCoDept.dept.highdept.cnt." + dbType, ttObjParamsSql);
    }

    /**
     * 부서CD로 상세를 조회한다 <br />
     * @param ttObjParamsSql 검색 파라미터
     * @return 검색부서
     */
    public VoCoDept getDeptDtlForDeptCd(CoTtObjParams ttObjParamsSql) {
        return (VoCoDept) selectByPk("DaoCoDept.dept.dtl.cd." + dbType, ttObjParamsSql);
    }

    /**
     * MAX 부서번호취득 <br />
     * @return MAX 부서번호
     */
    public int getMaxDeptNo() {
        return (Integer) getSqlMapClientTemplate().queryForObject(
                "DaoCoDept.dept.max.deptno." + dbType);
    }

    /**
     * 부서에 속한 user list 총 갯수 <br />
     * @param ttObjParamsSql 검색 파라미터
     * @return 토탈카운트
     */
    public int getUserYnCnt(CoTtObjParams ttObjParamsSql) {
        return (Integer) getSqlMapClientTemplate().queryForObject("DaoCoDept.dept.user.cnt." + dbType, ttObjParamsSql);
    }

}
