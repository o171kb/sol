/*
 * Copyright (C) 2005-2013 THINK TREE. All Rights Reserved.
 *
 * This software is the confidential and proprietary information
 * of korea.think-tree.com.,LTD. ("Confidential Information").
 */
package tt.base.module;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tt.com.CoTtObjParams;
import tt.com.dao.DaoCoDept;
import tt.com.dao.DaoCoGroup;
import tt.com.dao.DaoCoMenu;
import tt.com.dao.DaoCoUser;
import tt.com.module.file.SvCoFileService;
import tt.dao.DaoBoardSearch;
import tt.dao.DaoCustomer;
import tt.dao.DaoDeptPcCheck;
import tt.dao.DaoExct;
import tt.dao.DaoExctApi;
import tt.dao.DaoExctDraft;
import tt.dao.DaoExctEscortDraft;
import tt.dao.DaoExctSiteDraft;
import tt.dao.DaoIncops5;
import tt.dao.DaoMain;
import tt.dao.DaoMyApproval;
import tt.dao.DaoMyDraft;
import tt.dao.DaoMyPcCheck;
import tt.dao.DaoSecuPrint;
import tt.module.admin.mypage.SvMyDraftService;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;


/**
 * <pre>
 * tt.base.module
 *    |_ TtBaseService.java
 *
 * DESC :
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author hk-kim
 * @Date 2013. 4. 1. 오후 8:39:56
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 4. 1.		hk-kim				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
@Service("TtBaseService")
public class TtBaseService extends AbstractServiceImpl {

    /** 관리자관련 DAO 클래스 */
    @Resource(name = "daoCoUser")
    protected DaoCoUser daoCoUser;

    @Resource(name = "daoMain")
    protected DaoMain daoMain;

    /** 메뉴관련 DAO 클래스 */
    @Resource(name = "daoCoMenu")
    protected DaoCoMenu daoCoMenu;

    /** 부서관련 DAO 클래스 */
    @Resource(name = "daoCoDept")
    protected DaoCoDept daoCoDept;

    /** 그룹관련 DAO 클래스 */
    @Resource(name = "daoCoGroup")
    protected DaoCoGroup daoCoGroup;

    /** 고객센터 DAO 클래스 */
    @Resource(name = "daoCustomer")
    protected DaoCustomer daoCustomer;

    /** 시스템 게시관리 DAO 클래스 */
    @Resource(name = "daoBoardSearch")
    protected DaoBoardSearch daoBoardSearch;

    /** 예외처리 관련 DAO 클래스 */
    @Resource(name = "daoExct")
    protected DaoExct daoExct;

    /** 예외처리 관련 DAO 클래스 */
    @Resource(name = "daoExctDraft")
    protected DaoExctDraft daoExctDraft;

    /** 나의신청현황*/
    @Resource(name = "daoMyDraft")
    protected DaoMyDraft daoMyDraft;

    /** 나의결재현황*/
    @Resource(name = "daoMyApproval")
    protected DaoMyApproval daoMyApproval;

    /** Escort 예외처리 관련 DAO 클래스 */
    @Resource(name = "daoExctEscortDraft")
    protected DaoExctEscortDraft daoExctEscortDraft;

    /** file관련 service */
    @Resource(name = "svCoFileService")
    protected SvCoFileService svCoFileService;

    /** incops5 table 관련 DAO 클래스 */
    @Resource(name = "daoIncops5")
    protected DaoIncops5 daoIncops5;

    /** 사이트예외신청 */
    @Resource(name = "daoExctSiteDraft")
    protected DaoExctSiteDraft daoExctSiteDraft;


    /** 자가진단 관련 DAO 클래스 */
    @Resource(name = "daoMyPcCheck")
    protected DaoMyPcCheck daoMyPcCheck;

    /** 자가진단 관련 DAO 클래스 */
    @Resource(name = "daoDeptPcCheck")
    protected DaoDeptPcCheck daoDeptPcCheck;

    /** secuPrint 관련 DAO 클래스 */
    @Resource(name = "daoSecuPrint")
    protected DaoSecuPrint daoSecuPrint;

    /** API 관련 DAO 클래스 */
    @Resource(name = "daoExctApi")
    protected DaoExctApi daoExctApi;

    /**
     * 자가진단 를 설정 <br />
     * @param daoMyPcCheck 자가진단 설정
     */
    public void setDaoMyPcCheck(DaoMyPcCheck daoMyPcCheck) {
        this.daoMyPcCheck = daoMyPcCheck;
    }

    /**
     * 페이징을 위한 파라미터 설정
     * @param firstIndex 시작ROW인텍스
     * @param recordCountPerPage 조회할 ROW 갯수
     * @return 오브젝트파라이터오브젝트
     */
    protected CoTtObjParams getParamsObjOfPageInfoSetted(int firstIndex, int recordCountPerPage) {
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        ttObjParamsSql.put("fromIndex", firstIndex);
        ttObjParamsSql.put("toIndex", firstIndex + recordCountPerPage);
        return ttObjParamsSql;
    }



    /**
     * 사이트예외신청 <br/>
     * @param daoExctSiteDraft 사이트예외신청
     */
    public void setDaoExctSiteDraft(DaoExctSiteDraft daoExctSiteDraft) {
        this.daoExctSiteDraft = daoExctSiteDraft;
    }

    /**
     * 메인 를 설정 <br />
     * @param daoMain 메인 설정
     */
    public void setDaoMain(DaoMain daoMain) {
        this.daoMain = daoMain;
    }

    /**
     * 나의신청현황 를 설정 <br />
     * @param daoMyDraft 나의신청현황 설정
     */
    public void setDaoMyDraft(DaoMyDraft daoMyDraft) {
        this.daoMyDraft = daoMyDraft;
    }

    /**
     * 나의결재현황 를 설정 <br />
     * @param daoMyApproval 나의신청현황 설정
     */
    public void setDaoMyApproval(DaoMyApproval daoMyApproval) {
        this.daoMyApproval = daoMyApproval;
    }

    /**
     * 고객센터 를 설정 <br />
     * @param daoCustomer 공지사항 설정
     */
    public void setDaoCustomer(DaoCustomer daoCustomer) {
        this.daoCustomer = daoCustomer;
    }

    /**
     * 게시관리 를 설정 <br />
     * @param daoBoardSearch 게시관리 설정
     */
    public void setDaoBoardSearch(DaoBoardSearch daoBoardSearch) {
        this.daoBoardSearch = daoBoardSearch;
    }

    /**
     * DWR용 변수 설정 <br />
     * @param daoCoUser DWR용 변수 설정
     */
    public void setDaoCoUser(DaoCoUser daoCoUser) {
        this.daoCoUser = daoCoUser;
    }

    /**
     * DWR용 변수 설정 <br />
     * @param daoCoMenu DWR용 변수 설정
     */
    public void setDaoCoMenu(DaoCoMenu daoCoMenu) {
        this.daoCoMenu = daoCoMenu;
    }

    /**
     * DWR용 변수 를 설정 <br />
     * @param daoCoDept DWR용 변수 설정
     */
    public void setDaoCoDept(DaoCoDept daoCoDept) {
        this.daoCoDept = daoCoDept;
    }

    /**
     * DWR용 변수 설정 <br />
     * @param daoExct DWR용 변수 설정
     */
    public void setDaoExct(DaoExct daoExct) {
        this.daoExct = daoExct;
    }

    /**
     * DWR용 변수 설정 <br />
     * @param daoExctDraft DWR용 변수 설정
     */
    public void setDaoExctDraft(DaoExctDraft daoExctDraft) {
        this.daoExctDraft = daoExctDraft;
    }

    /**
     * DWR용 변수 설정 <br />
     * @param daoCoGroup DWR용 변수 설정
     */
    public void setDaoCoGroup(DaoCoGroup daoCoGroup) {
        this.daoCoGroup = daoCoGroup;
    }

    /**
     * DWR용 변수 설정 <br />
     * @param daoDeptPcCheck DWR용 변수 설정
     */
    public void setDaoDeptPcCheck(DaoDeptPcCheck daoDeptPcCheck) {
        this.daoDeptPcCheck = daoDeptPcCheck;
    }

    /**
     * DWR용 변수 설정 <br />
     * @param daoExctEscortDraft DWR용 변수 설정
     */
    public void setDaoExctEscortDraft(DaoExctEscortDraft daoExctEscortDraft) {
        this.daoExctEscortDraft = daoExctEscortDraft;
    }

    /**
     * DWR용 변수 설정 <br />
     * @param daoIncops5 DWR용 변수 설정
     */
    public void setDaoIncops5(DaoIncops5 daoIncops5) {
        this.daoIncops5 = daoIncops5;
    }

    /**
     * DWR용 변수 설정 <br />
     * @param daoSecuPrint DWR용 변수 설정
     */
    public void setDaoSecuPrint(DaoSecuPrint daoSecuPrint) {
        this.daoSecuPrint = daoSecuPrint;
    }

    public void setDaoExctApi(DaoExctApi daoExctApi) {
        this.daoExctApi = daoExctApi;
    }

    /**
     * file 를 설정 <br />
     * @param svCoFileService file 설정
     */
    public void setSvCoFileService(SvCoFileService svCoFileService) {
        this.svCoFileService = svCoFileService;
    }

}
