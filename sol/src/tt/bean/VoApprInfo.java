package tt.bean;

import java.io.Serializable;

import tt.com.bean.VoCoUser;

/**
 * DESC :
 *
 * @Company think-tree.inc
 * @author sj-hwang
 * @Date 2013. 4. 1. 오후 3:26:18
 */
public class VoApprInfo extends VoCoUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 항목명 */
    private String exctNms;
    /** 예외신청구분 */
    private String exctAppId;
    /** 용도 */
    private String purpose;
    /** 신청일 */
    private String apprDm;
    /** 시작일 */
    private String startDm;
    /** 종료일 */
    private String endDm;
    /** 사유 */
    private String reason;
    /** 증빙서류명*/
    private String addProofNm;
    /** 증빙서류url */
    private String addProofUrl;
    /** 예외선택아이디 */
    private String chkExctId;
    /** 예외선택아이디 */
    private String chkExctIds;
    /** 예외선택이름 */
    private String chkExctNms;
    /** 예외대상이름 */
    private String chkObjNms;
    /** 예외대상부서 */
    private String chkDeptNms;
    /** 기안자 */
    private String drafter;
    /** 예외신청문구ID */
    private String apprId;
    /** 사용자 */
    private String approbjUser;
    /** 사용자 */
    private String appId;
    /** 대표자ID */
    private String approbjId;
    /** 상태 */
    private String status;
    /** 결재구분 */
    private String permitGubuns;
    /** 결재구분 */
    private String permitGubun;
    /** 코드명 */
    private String cdDtlNm;
    /** 부서명 */
    private String deptNm;
    /** 예외구분 */
    private String exctAppNm;
    /** 성명 */
    private String userNm;
    /** 직급 */
    private String userPosition;
    /** 전화번호 */
    private String userTel;
    /** 순번 */
    private String turnNo;
    /** 부서번호 */
    private String deptNo;
    /** 사용자 대표 부서명 */
    private String AppIdDeptNm;
    /** 예외대상부서 */
    private String apprObjDept;
    /** 결재의견 */
    private String resultReason;
    /** 첨부 통제구분 */
    private String allowGubun;
    
    private String reqCount;
    
    private String reqDoCount;
    
    private String apprCount;
    
    private String apprDoCount;
    
    private String userEmail;
    
    private String tabDiv;
    
    private String tabDiv2;

    /**
     * 순번 를 취득 <br />
     * @return turnNo 순번
     */
    public String getTurnNo() {
        return turnNo;
    }
    /**
     * 순번 를 설정 <br />
     * @param turnNo 순번 설정
     */
    public void setTurnNo(String turnNo) {
        this.turnNo = turnNo;
    }
    /**
     * 대표자ID 를 취득 <br />
     * @return appId 대표자ID
     */
    public String getAppId() {
        return appId;
    }
    /**
     * 대표자ID 를 설정 <br />
     * @param appId 대표자ID 설정
     */
    public void setAppId(String appId) {
        this.appId = appId;
    }
    /**
     * 사용자ID 를 취득 <br />
     * @return approbjId 사용자ID
     */
    public String getApprobjId() {
        return approbjId;
    }
    /**
     * 사용자ID 를 설정 <br />
     * @param approbjId 사용자ID 설정
     */
    public void setApprobjId(String approbjId) {
        this.approbjId = approbjId;
    }
    /**
     * 성명 를 취득 <br />
     * @return userNm 성명
     */
    public String getUserNm() {
        return userNm;
    }
    /**
     * 성명 를 설정 <br />
     * @param userNm 성명 설정
     */
    public void setUserNm(String userNm) {
        this.userNm = userNm;
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
     * 증빙서류명 를 취득 <br />
     * @return addProofNm 증빙서류명
     */
    public String getAddProofNm() {
        return addProofNm;
    }
    /**
     * 증빙서류명 를 설정 <br />
     * @param addProofNm 증빙서류명 설정
     */
    public void setAddProofNm(String addProofNm) {
        this.addProofNm = addProofNm;
    }
    /**
     * 증빙서류url 를 취득 <br />
     * @return addProofUrl 증빙서류url
     */
    public String getAddProofUrl() {
        return addProofUrl;
    }
    /**
     * 증빙서류url 를 설정 <br />
     * @param addProofUrl 증빙서류url 설정
     */
    public void setAddProofUrl(String addProofUrl) {
        this.addProofUrl = addProofUrl;
    }
    /**
     * 예외구분 를 취득 <br />
     * @return exctAppNm 예외구분
     */
    public String getExctAppNm() {
        return exctAppNm;
    }
    /**
     * 예외구분 를 설정 <br />
     * @param exctAppNm 예외구분 설정
     */
    public void setExctAppNm(String exctAppNm) {
        this.exctAppNm = exctAppNm;
    }
    /**
     * 부서명 를 취득 <br />
     * @return deptNm 부서명
     */
    public String getDeptNm() {
        return deptNm;
    }
    /**
     * 부서명 를 설정 <br />
     * @param deptNm 부서명 설정
     */
    public void setDeptNm(String deptNm) {
        this.deptNm = deptNm;
    }
    /**
     * 코드명 를 취득 <br />
     * @return cdDtlNm 코드명
     */
    public String getCdDtlNm() {
        return cdDtlNm;
    }
    /**
     * 코드명 를 설정 <br />
     * @param cdDtlNm 코드명 설정
     */
    public void setCdDtlNm(String cdDtlNm) {
        this.cdDtlNm = cdDtlNm;
    }
    /**
     * 항목명 를 취득 <br />
     * @return exctNms 항목명
     */
    public String getExctNms() {
        return exctNms;
    }
    /**
     * 항목명 를 설정 <br />
     * @param exctNms 항목명 설정
     */
    public void setExctNms(String exctNms) {
        this.exctNms = exctNms;
    }
    /**
     * 예외신청문구ID 를 취득 <br />
     * @return apprId 예외신청문구ID
     */
    public String getApprId() {
        return apprId;
    }
    /**
     * 예외신청문구ID 를 설정 <br />
     * @param apprId 예외신청문구ID 설정
     */
    public void setApprId(String apprId) {
        this.apprId = apprId;
    }
    /**
     * 사용자 를 취득 <br />
     * @return approbjUser 사용자
     */
    public String getApprobjUser() {
        return approbjUser;
    }
    /**
     * 사용자 를 설정 <br />
     * @param approbjUser 사용자 설정
     */
    public void setApprobjUser(String approbjUser) {
        this.approbjUser = approbjUser;
    }
    /**
     * 상태 를 취득 <br />
     * @return status 상태
     */
    public String getStatus() {
        return status;
    }
    /**
     * 상태 를 설정 <br />
     * @param status 상태 설정
     */
    public void setStatus(String status) {
        this.status = status;
    }
    /**
     * 기안자 를 취득 <br />
     * @return drafter 기안자
     */
    public String getDrafter() {
        return drafter;
    }
    /**
     * 기안자 를 설정 <br />
     * @param drafter 기안자 설정
     */
    public void setDrafter(String drafter) {
        this.drafter = drafter;
    }

    /**
     * 예외신청구분 반환한다. <br />
     * @return exctAppId 예외신청구분
     */
    public String getExctAppId() {
        return exctAppId;
    }
    /**
     * 용도 반환한다. <br />
     * @return purpose 용도
     */
    public String getPurpose() {
        return purpose;
    }
    /**
     * 신청일 반환한다. <br />
     * @return apprDm 신청일
     */
    public String getApprDm() {
        return apprDm;
    }
    /**
     * 시작일 반환한다. <br />
     * @return startDm 시작일
     */
    public String getStartDm() {
        return startDm;
    }
    /**
     * 종료일 반환한다. <br />
     * @return endDm 종료일
     */
    public String getEndDm() {
        return endDm;
    }
    /**
     * 사유 반환한다. <br />
     * @return reason 사유
     */
    public String getReason() {
        return reason;
    }
    /**
     * 예외신청구분 설정한다. <br />
     * @param exctAppId 예외신청구분
     */
    public void setExctAppId(String exctAppId) {
        this.exctAppId = exctAppId;
    }
    /**
     * 용도 설정한다. <br />
     * @param purpose 용도
     */
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
    /**
     * 신청일 설정한다. <br />
     * @param apprDm 신청일
     */
    public void setApprDm(String apprDm) {
        this.apprDm = apprDm;
    }
    /**
     * 시작일 설정한다. <br />
     * @param startDm 시작일
     */
    public void setStartDm(String startDm) {
        this.startDm = startDm;
    }
    /**
     * 종료일 설정한다. <br />
     * @param endDm 종료일
     */
    public void setEndDm(String endDm) {
        this.endDm = endDm;
    }
    /**
     * 사유 설정한다. <br />
     * @param reason 사유
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * 선택아이디 반환한다. <br />
     * @return chkExctId 선택아이디
     */
    public String getChkExctId() {
        return chkExctId;
    }
    /**
     * 선택아이디 설정한다. <br />
     * @param chkExctId 선택아이디
     */
    public void setChkExctId(String chkExctId) {
        this.chkExctId = chkExctId;
    }
    /**
     * 예외선택이름 반환한다. <br />
     * @return chkExctNms 예외선택이름
     */
    public String getChkExctNms() {
        return chkExctNms;
    }
    /**
     * 예외선택이름 설정한다. <br />
     * @param chkExctNms 예외선택이름
     */
    public void setChkExctNms(String chkExctNms) {
        this.chkExctNms = chkExctNms;
    }
    /**
     * 예외대상이름 반환한다. <br />
     * @return chkObjNms 예외대상이름
     */
    public String getChkObjNms() {
        return chkObjNms;
    }
    /**
     * 예외대상이름 설정한다. <br />
     * @param chkObjNms 예외대상이름
     */
    public void setChkObjNms(String chkObjNms) {
        this.chkObjNms = chkObjNms;
    }
    /**
     * 결재구분 반환한다. <br />
     * @return permitGubuns 결재구분
     */
    public String getPermitGubuns() {
        return permitGubuns;
    }
    /**
     * 결재구분 설정한다. <br />
     * @param permitGubuns 결재구분
     */
    public void setPermitGubuns(String permitGubuns) {
        this.permitGubuns = permitGubuns;
    }

    /**
     * 결재구분 를 취득 <br />
     * @return permitGubun 결재구분
     */
    public String getPermitGubun() {
        return permitGubun;
    }
    /**
     * 결재구분 를 설정 <br />
     * @param permitGubun 결재구분 설정
     */
    public void setPermitGubun(String permitGubun) {
        this.permitGubun = permitGubun;
    }
    /**
     * 예외선택아이디 반환한다. <br />
     * @return chkExctIds 예외선택아이디
     */
    public String getChkExctIds() {
        return chkExctIds;
    }
    /**
     * 예외선택아이디 설정한다. <br />
     * @param chkExctIds 예외선택아이디
     */
    public void setChkExctIds(String chkExctIds) {
        this.chkExctIds = chkExctIds;
    }
    /**
     * 부서번호 반환한다. <br />
     * @return deptNo 부서번호
     */
    public String getDeptNo() {
        return deptNo;
    }
    /**
     * 부서번호 설정한다. <br />
     * @param deptNo 부서번호
     */
    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }
    /**
     * 사용자 대표 부서명 반환한다. <br />
     * @return appIdDeptNm 사용자 대표 부서명
     */
    public String getAppIdDeptNm() {
        return AppIdDeptNm;
    }
    /**
     * 사용자 대표 부서명 설정한다. <br />
     * @param appIdDeptNm 사용자 대표 부서명
     */
    public void setAppIdDeptNm(String appIdDeptNm) {
        AppIdDeptNm = appIdDeptNm;
    }
    /**
     * 예외대상부서 반환한다. <br />
     * @return chkDeptNms 예외대상부서
     */
    public String getChkDeptNms() {
        return chkDeptNms;
    }
    /**
     * 예외대상부서 설정한다. <br />
     * @param chkDeptNms 예외대상부서
     */
    public void setChkDeptNms(String chkDeptNms) {
        this.chkDeptNms = chkDeptNms;
    }
    /**
     * 예외대상부서 반환한다. <br />
     * @return apprObjDept 예외대상부서
     */
    public String getApprObjDept() {
        return apprObjDept;
    }
    /**
     * 예외대상부서 설정한다. <br />
     * @param apprObjDept 예외대상부서
     */
    public void setApprObjDept(String apprObjDept) {
        this.apprObjDept = apprObjDept;
    }
    /**
     * 결재의견 반환한다. <br />
     * @return resultReason 결재의견
     */
    public String getResultReason() {
        return resultReason;
    }
    /**
     * 결재의견 설정한다. <br />
     * @param resultReason 결재의견
     */
    public void setResultReason(String resultReason) {
        this.resultReason = resultReason;
    }
    /**
     * 첨부 통제구분 반환한다. <br />
     * @return allowGubun 첨부 통제구분
     */
    public String getAllowGubun() {
        return allowGubun;
    }
    /**
     * 첨부 통제구분 설정한다. <br />
     * @param allowGubun 첨부 통제구분
     */
    public void setAllowGubun(String allowGubun) {
        this.allowGubun = allowGubun;
    }
    public String getReqCount() {
        return reqCount;
    }
    public void setReqCount(String reqCount) {
        this.reqCount = reqCount;
    }
    public String getReqDoCount() {
        return reqDoCount;
    }
    public void setReqDoCount(String reqDoCount) {
        this.reqDoCount = reqDoCount;
    }
    public String getApprCount() {
        return apprCount;
    }
    public void setApprCount(String apprCount) {
        this.apprCount = apprCount;
    }
    public String getApprDoCount() {
        return apprDoCount;
    }
    public void setApprDoCount(String apprDoCount) {
        this.apprDoCount = apprDoCount;
    }
    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    public String getTabDiv() {
        return tabDiv;
    }
    public void setTabDiv(String tabDiv) {
        this.tabDiv = tabDiv;
    }
    public String getTabDiv2() {
        return tabDiv2;
    }
    public void setTabDiv2(String tabDiv2) {
        this.tabDiv2 = tabDiv2;
    }
    
    

    }
