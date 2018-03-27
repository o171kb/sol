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
 *    |_ VoCoUser.java
 *
 * DESC : 관리자 정보 VO 클래스
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author ks-lee
 * @Date 2013. 2. 27. 오후 12:35:34
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 2. 27.		ks-lee				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
public class VoCoUser extends VoCoUserGrp {

    private static final long serialVersionUID = 4374929825785214754L;

    /** 비밀번호 칼럼명 */
    public static final String COL_USER_PWD = "USER_PWD";
    /** 사용자이름 칼럼명 */
    public static final String COL_USER_NM = "USER_NM";
    /** 사용자그룹번호 칼럼명 */
    public static final String COL_USER_GRP_NO = "USER_GRP_NO";
    /** 대행결재자사용여부 칼럼명 */
    public static final String COL_PROXY_APPR_YN = "PROXY_APPR_YN";
    /** 대행결재자ID 칼럼명 */
    public static final String COL_PROXY_APPR_ID = "PROXY_APPR_ID";

    /** 비밀번호 */
    private String userPwd;
    /** 사용자이름 */
    private String userNm;
    /** 사용자그룹번호 */
    private String userGrpNo;
    /** 직급 */
    private String userPosition;
    /** 직급 */
    private String userPositionNm;
    /** 전화번호 */
    private String userTel;
    /** 휴대폰 */
    private String userMobile;
    /** 사용자이메일 */
    private String userEmail;
    /** 대행결재자사용여부 */
    private String proxyApprYn;
    /** 대행결재자ID */
    private String proxyApprId;
    /** 대행결재자 nm */
    private String proxyApprNm;
    /** 결재자 선택 */
    private String chkApprover;
    
    /** 부서 코드*/
    private String deptCd;
    
    /** 부서명*/
    private String deptNm;
    
    private String drafterId;


    /**
     * 대행결재자 nm 를 취득 <br />
     * @return proxyApprNm 대행결재자 nm
     */
    public String getProxyApprNm() {
        return proxyApprNm;
    }
    /**
     * 대행결재자 nm 를 설정 <br />
     * @param proxyApprNm 대행결재자 nm 설정
     */
    public void setProxyApprNm(String proxyApprNm) {
        this.proxyApprNm = proxyApprNm;
    }

    /**
     * 비밀번호 를 취득 <br />
     * @return userPwd 비밀번호
     */
    public String getUserPwd() {
        return userPwd;
    }
    /**
     * 비밀번호 를 설정 <br />
     * @param userPwd 비밀번호 설정
     */
    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }
    /**
     * 사용자이름 를 취득 <br />
     * @return userNm 사용자이름
     */
    public String getUserNm() {
        return userNm;
    }
    /**
     * 사용자이름 를 설정 <br />
     * @param userNm 사용자이름 설정
     */
    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }
    /**
     * 사용자그룹번호 를 취득 <br />
     * @return userGrpNo 사용자그룹번호
     */
    public String getUserGrpNo() {
        return userGrpNo;
    }
    /**
     * 사용자그룹번호 를 설정 <br />
     * @param userGrpNo 사용자그룹번호 설정
     */
    public void setUserGrpNo(String userGrpNo) {
        this.userGrpNo = userGrpNo;
    }
    /**
     * 직급 를 취득 <br />
     * @return userPosition 직급
     */
    public String getUserPosition() {
        return userPosition;
    }
    /**
     * 직급 를 설정 <br />
     * @param userPosition 직급 설정
     */
    public void setUserPosition(String userPosition) {
        this.userPosition = userPosition;
    }
    /**
     * 전화번호 를 취득 <br />
     * @return userTel 전화번호
     */
    public String getUserTel() {
        return userTel;
    }
    /**
     * 전화번호 를 설정 <br />
     * @param userTel 전화번호 설정
     */
    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }
    /**
     * 휴대폰 를 취득 <br />
     * @return userMobile 휴대폰
     */
    public String getUserMobile() {
        return userMobile;
    }
    /**
     * 휴대폰 를 설정 <br />
     * @param userMobile 휴대폰 설정
     */
    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }
    /**
     * 사용자이메일 를 취득 <br />
     * @return userEmail 사용자이메일
     */
    public String getUserEmail() {
        return userEmail;
    }
    /**
     * 사용자이메일 를 설정 <br />
     * @param userEmail 사용자이메일 설정
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * 대행결재자사용여부 를 취득 <br />
     * @return proxyApprYn 대행결재자사용여부
     */
    public String getProxyApprYn() {
        return proxyApprYn;
    }
    /**
     * 대행결재자사용여부 를 설정 <br />
     * @param proxyApprYn 대행결재자사용여부 설정
     */
    public void setProxyApprYn(String proxyApprYn) {
        this.proxyApprYn = proxyApprYn;
    }
    /**
     * 대행결재자ID 를 취득 <br />
     * @return proxyApprId 대행결재자ID
     */
    public String getProxyApprId() {
        return proxyApprId;
    }
    /**
     * 대행결재자ID 를 설정 <br />
     * @param proxyApprId 대행결재자ID 설정
     */
    public void setProxyApprId(String proxyApprId) {
        this.proxyApprId = proxyApprId;
    }
    /**
     * 결재자 선택 반환한다. <br />
     * @return chkApprover 결재자 선택
     */
    public String getChkApprover() {
        return chkApprover;
    }
    /**
     * 결재자 선택 설정한다. <br />
     * @param chkApprover 결재자 선택
     */
    public void setChkApprover(String chkApprover) {
        this.chkApprover = chkApprover;
    }
    public String getDeptCd() {
        return deptCd;
    }
    public void setDeptCd(String deptCd) {
        this.deptCd = deptCd;
    }
    public String getDeptNm() {
        return deptNm;
    }
    public void setDeptNm(String deptNm) {
        this.deptNm = deptNm;
    }
    public String getUserPositionNm() {
        return userPositionNm;
    }
    public void setUserPositionNm(String userPositionNm) {
        this.userPositionNm = userPositionNm;
    }
    public String getDrafterId() {
        return drafterId;
    }
    public void setDrafterId(String drafterId) {
        this.drafterId = drafterId;
    }
    
    
}
