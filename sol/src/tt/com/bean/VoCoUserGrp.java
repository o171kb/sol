/*
 * Copyright (C) 2005-2013 THINK TREE. All Rights Reserved.
 *
 * This software is the confidential and proprietary information
 * of korea.think-tree.com.,LTD. ("Confidential Information").
 */
package tt.com.bean;

/**
 * <pre>
 * tt.com.bean
 *    |_ VoCoUserGrp.java
 *
 * DESC : 관리자그룹 VO 클래스
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author ks-lee
 * @Date 2013. 2. 27. 오후 1:02:57
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 2. 27.		ks-lee				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
public class VoCoUserGrp extends VoCoDept {

    private static final long serialVersionUID = 1315554918109341499L;

    /** 관리자그룹번호 칼럼명 */
    public static final String COL_USER_GRP_NO = "USER_GRP_NO";
    /** 관리자그룹ID 칼럼명 */
    public static final String COL_USER_GRP_ID = "USER_GRP_ID";
    /** 관리자그룹명 칼럼명 */
    public static final String COL_GRP_NM = "GRP_NM";
    /** 관리자그룹설명 칼럼명 */
    public static final String COL_GRP_EXP = "GRP_EXP";
    /** 관리자그룹종류 칼럼명 */
    public static final String COL_GRP_TP = "GRP_TP";
    /** 관리자그룹구분 칼럼명 */
    public static final String COL_GRP_CL = "GRP_CL";
    /** 센터번호 칼럼명 */
    public static final String COL_MEM_CENTER_NO = "MEM_CENTER_NO";
    /** 부서번호 칼럼명 */
    public static final String COL_MEM_DEPT_NO = "MEM_DEPT_NO";
    /** 결재권한 칼럼명 */
    public static final String COL_APPR_YN = "APPR_YN";
    /** 마스터권한 칼럼명 */
    public static final String COL_MST_YN = "MST_YN";
    /** 그룹권한 칼럼명 */
    public static final String COL_GRP_YN = "GRP_YN";
    /** 자신결재권한 칼럼명 */
    public static final String COL_SELF_YN = "SELF_YN";
    /** 전산관리자 결재권한 칼럼명 */
    public static final String COL_COMPADMIN = "COMPADMIN";
    /** 전산관리자 결재코드 칼럼명 */
    public static final String COL_COMPADCODE = "COMPADCODE";


    /** 관리자그룹번호 */
    private String userGrpNo;
    /** 관리자그룹ID */
    private String userGrpId;
    /** 관리자그룹명 */
    private String grpNm;
    /** 관리자그룹설명 */
    private String grpExp;
    /** 관리자그룹종류 */
    private String grpTp;
    /** 관리자그룹구분 */
    private String grpCl;
    /** 센터번호 */
    private String memCenterNo;
    /** 부서번호 */
    private String memDeptNo;
    /** 결재권한 */
    private String apprYn;
    /** 전산관리자사용 */
    private String compAdmin;
    /** 전산관리자 결재코드 */
    private String compAdCode;
    /** 마스터권한 */
    private String mstYn;
    /** 그룹관리권한 */
    private String grpYn;
    /** 자신결재권한 */
    private String selfYn;

    /**
     * 관리자그룹번호 를 취득 <br />
     * @return userGrpNo 관리자그룹번호
     */
    public String getUserGrpNo() {
        return userGrpNo;
    }
    /**
     * 관리자그룹번호 를 설정 <br />
     * @param userGrpNo 관리자그룹번호 설정
     */
    public void setUserGrpNo(String userGrpNo) {
        this.userGrpNo = userGrpNo;
    }
    /**
     * 관리자그룹ID 를 취득 <br />
     * @return userGrpId 관리자그룹ID
     */
    public String getUserGrpId() {
        return userGrpId;
    }
    /**
     * 관리자그룹ID 를 설정 <br />
     * @param userGrpId 관리자그룹ID 설정
     */
    public void setUserGrpId(String userGrpId) {
        this.userGrpId = userGrpId;
    }
    /**
     * 관리자그룹명 를 취득 <br />
     * @return grpNm 관리자그룹명
     */
    public String getGrpNm() {
        return grpNm;
    }
    /**
     * 관리자그룹명 를 설정 <br />
     * @param grpNm 관리자그룹명 설정
     */
    public void setGrpNm(String grpNm) {
        this.grpNm = grpNm;
    }
    /**
     * 관리자그룹설명 를 취득 <br />
     * @return grpExp 관리자그룹설명
     */
    public String getGrpExp() {
        return grpExp;
    }
    /**
     * 관리자그룹설명 를 설정 <br />
     * @param grpExp 관리자그룹설명 설정
     */
    public void setGrpExp(String grpExp) {
        this.grpExp = grpExp;
    }
    /**
     * 관리자그룹종류 를 취득 <br />
     * @return grpTp 관리자그룹종류
     */
    public String getGrpTp() {
        return grpTp;
    }
    /**
     * 관리자그룹종류 를 설정 <br />
     * @param grpTp 관리자그룹종류 설정
     */
    public void setGrpTp(String grpTp) {
        this.grpTp = grpTp;
    }
    /**
     * 관리자그룹구분 를 취득 <br />
     * @return grpCl 관리자그룹구분
     */
    public String getGrpCl() {
        return grpCl;
    }
    /**
     * 관리자그룹구분 를 설정 <br />
     * @param grpCl 관리자그룹구분 설정
     */
    public void setGrpCl(String grpCl) {
        this.grpCl = grpCl;
    }
    /**
     * 센터번호 를 취득 <br />
     * @return memCenterNo 센터번호
     */
    public String getMemCenterNo() {
        return memCenterNo;
    }
    /**
     * 부서번호 를 설정 <br />
     * @param memCenterNo 부서번호 설정
     */
    public void setMemCenterNo(String memCenterNo) {
        this.memCenterNo = memCenterNo;
    }
    /**
     * 부서번호 를 취득 <br />
     * @return memDeptNo 부서번호
     */
    public String getMemDeptNo() {
        return memDeptNo;
    }
    /**
     * 부서번호 를 설정 <br />
     * @param memDeptNo 부서번호 설정
     */
    public void setMemDeptNo(String memDeptNo) {
        this.memDeptNo = memDeptNo;
    }
    /**
     * 결재권한 를 취득 <br />
     * @return apprYn 결재권한
     */
    public String getApprYn() {
        return apprYn;
    }
    /**
     * 결재권한 를 설정 <br />
     * @param apprYn 결재권한 설정
     */
    public void setApprYn(String apprYn) {
        this.apprYn = apprYn;
    }
    /**
     * 전산관리자사용 반환한다. <br />
     * @return compAdmin 전산관리자사용
     */
    public String getCompAdmin() {
        return compAdmin;
    }
    /**
     * 전산관리자사용 설정한다. <br />
     * @param compAdmin 전산관리자사용
     */
    public void setCompAdmin(String compAdmin) {
        this.compAdmin = compAdmin;
    }
    /**
     * 전산관리자 결재코드 반환한다. <br />
     * @return compAdCode 전산관리자 결재코드
     */
    public String getCompAdCode() {
        return compAdCode;
    }
    /**
     * 전산관리자 결재코드 설정한다. <br />
     * @param compAdCode 전산관리자 결재코드
     */
    public void setCompAdCode(String compAdCode) {
        this.compAdCode = compAdCode;
    }
    public String getGrpYn() {
        return grpYn;
    }
    public void setGrpYn(String grpYn) {
        this.grpYn = grpYn;
    }
    public String getSelfYn() {
        return selfYn;
    }
    public void setSelfYn(String selfYn) {
        this.selfYn = selfYn;
    }
    public String getMstYn() {
        return mstYn;
    }
    public void setMstYn(String mstYn) {
        this.mstYn = mstYn;
    }
}
