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
import tt.com.bean.VoCoUserGrp;
import tt.com.bean.VoCoUserGrpMenu;
import tt.com.utils.UtCoStringUtils;

/**
 * <pre>
 * tt.com.dao
 *    |_ DaoCoGroup.java
 *
 * DESC : 그룹관련 DAO 클래스 <br />
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author ks-lee
 * @Date 2013. 3. 25. 오전 11:16:55
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 3. 25.		ks-lee				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
@Repository("daoCoGroup")
public class DaoCoGroup extends TtBaseDao {

//    /**
//     * 부서정보취득 (팝업용) <br />
//     *
//     * @param ttObjParamsSql 부서검색정보
//     * @return 부서정보
//     */
//    @SuppressWarnings("unchecked")
//    public List<VoCoDept> getDeptSearchListAll(CoTtObjParams ttObjParamsSql) {
//        return list("DaoCoGroup..list.all." + dbType, ttObjParamsSql);
//    }
//
    /**
     * 그룹검색정보취득 페이지 <br />
     *
     * @param ttObjParamsSql 부서검색정보
     * @return 부서정보
     */
    @SuppressWarnings("unchecked")
    public List<VoCoUserGrp> getGrpSearchListForPage(CoTtObjParams ttObjParamsSql) {
        return list("DaoCoGroup.group.search.page.list." + dbType, ttObjParamsSql);
    }

    /**
     * 부서검색정보 총갯수 취득 <br />
     *
     * @param ttObjParamsSql 부서검색정보
     * @return 부서정보
     */
    public int getGrpSearchListTotCnt(CoTtObjParams ttObjParamsSql) {
        return (Integer) getSqlMapClientTemplate().queryForObject(
                "DaoCoGroup.group.search.cnt." + dbType, ttObjParamsSql);
    }

    /**
     * 그룹메뉴를 등록하기 위해 초기(물리)삭제한다. <br />
     * @param ttObjParamsSql 삭제정보
     */
    public void deleteGrpMenuByUserGrpNo(CoTtObjParams ttObjParamsSql) {
        delete("DaoCoGroup.group.physical.delete." + dbType, ttObjParamsSql);
    }

    /**
     * 그룹메뉴를 등록한다 <br />
     * @param ttObjParamsSql 등록정보
     */
    public void insertGrpMenu(CoTtObjParams ttObjParamsSql) {
        insert("DaoCoGroup.group.menu.insert." + dbType, ttObjParamsSql);
    }




    /**
     * 부서No로 상세를 조회한다 <br />
     * @param ttObjParamsSql 검색 파라미터
     * @return 검색부서
     */
    public VoCoUserGrp getGrpDtl(CoTtObjParams ttObjParamsSql) {
        return (VoCoUserGrp) selectByPk("DaoCoGroup.group.search.dtl." + dbType, ttObjParamsSql);
    }

    /**
     * 그룹정보를 수정한다 <br />
     * @param ttObjParamsSql 수정정보
     */
    public void updateGrpInfo(CoTtObjParams ttObjParamsSql) {
        update("DaoCoGroup.group.info.update." + dbType, ttObjParamsSql);

    }

    /**
     * 그룹메뉴를 수정한다 <br />
     * @param ttObjParamsSql 수정정보
     */
    public void updateGrpMenu(CoTtObjParams ttObjParamsSql) {
        update("DaoCoGroup.group.menu.update." + dbType, ttObjParamsSql);

    }

    /**
     * 사용자그룹ID 중복을 조회한다.
     * @param ttObjParamsSql 검색정보
     * @return 체크 결과 -1:사용불가, 0:검색문자열"", 1:사용가능
     */
    public int checkUserGrpId(CoTtObjParams ttObjParamsSql) {

        if (UtCoStringUtils.isEmpty(ttObjParamsSql.getString("userGrpId"))) {
            return 0;
        }

        VoCoDept checkVO = (VoCoDept) selectByPk("DaoCoGroup.group.check.user.grp.id." + dbType, ttObjParamsSql);

        if (checkVO == null) {
            return 1;
        }
        return -1;
    }

    /**
     * 사용자그룹정보를 등록한다 <br />
     * @param ttObjParamsSql 등록정보
     */
    public void insertUserGrpInfo(CoTtObjParams ttObjParamsSql) {
        insert("DaoCoGroup.group.insert.user.gtp.info." + dbType, ttObjParamsSql);
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
     * MAX 사용자그룹NO취득 <br />
     * @return MAX 사용자그룹NO
     */
    public int getUserGrpNo() {
        return (Integer) getSqlMapClientTemplate().queryForObject(
                "DaoCoGroup.group.max.user.grp.no." + dbType);
    }

    @SuppressWarnings("unchecked")
    public List<VoCoUserGrpMenu> getUserGrpMenu(CoTtObjParams ttObjParamsSql) {
        return list("DaoCoGroup.group.user.grp.menu.list." + dbType, ttObjParamsSql);
    }


    /**
     * 그룹를 (논리)삭제한다 <br />
     * @param ttObjParamsSql 삭제 파라미터
     */
    public void deleteGroup(CoTtObjParams ttObjParamsSql) {
        update("DaoCoGroup.group.delete." + dbType, ttObjParamsSql);
    }

    /**
     * 그룹에 속한 user list 총 갯수 <br />
     * @param ttObjParamsSql 검색 파라미터
     * @return 토탈카운트
     */
    public int getUserYnCnt(CoTtObjParams ttObjParamsSql) {
        return (Integer) getSqlMapClientTemplate().queryForObject("DaoCoGroup.group.user.cnt." + dbType, ttObjParamsSql);
    }

}
