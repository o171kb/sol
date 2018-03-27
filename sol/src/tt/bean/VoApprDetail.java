package tt.bean;


/**
 * <pre>
 * tt.bean
 *    |_ VoApprDetail.java
 *
 * DESC :
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author hk-kim
 * @Date 2013. 4. 24. 오전 9:58:34
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 4. 24.		hk-kim				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
public class VoApprDetail extends VoExct {

    private static final long serialVersionUID = 1L;


    /** 구분 */
    private String exctNm;
    /** 등록일 */
    private String regDm;
    /** 예외ID */
    private String exctAppId;
    /** 예외신청문구ID */
    private String apprId;
    /** 예외구분 */
    private String proGubun;
    /** 값2 */
    private String value2;
    /** 통제구분 */
    private String attachGubun;

    /** 규칙아이디 ex) F3D86F82-5444-432E-A66E-4A29C6EEFD76  */
    private String ruleGUID;
    /** 규칙이름 ex) 비업무사이트 차단 */
    private String ruleName;
    /** 타임아이디  ex) 75A2AC31-951C-41F1-949B-4421A657AD65 */
    private String timeGUID;
    /** 시간대 ex) 모든시간대 */
    private String timeName;
    /** 부서 GUID */
    private String deptGUID;
    /** 아이디 GUID */
    private String empGUID;
    /** 신청자 ID */
    private String appId;


    public String getAppId() {
        return appId;
    }
    public void setAppId(String appId) {
        this.appId = appId;
    }
    public String getEmpGUID() {
        return empGUID;
    }
    public void setEmpGUID(String empGUID) {
        this.empGUID = empGUID;
    }
    public String getDeptGUID() {
        return deptGUID;
    }
    public void setDeptGUID(String deptGUID) {
        this.deptGUID = deptGUID;
    }
    public String getRuleGUID() {
        return ruleGUID;
    }
    public void setRuleGUID(String ruleGUID) {
        this.ruleGUID = ruleGUID;
    }
    public String getRuleName() {
        return ruleName;
    }
    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }
    public String getTimeGUID() {
        return timeGUID;
    }
    public void setTimeGUID(String timeGUID) {
        this.timeGUID = timeGUID;
    }
    public String getTimeName() {
        return timeName;
    }
    public void setTimeName(String timeName) {
        this.timeName = timeName;
    }
    /**
     * 구분 를 취득 <br />
     * @return exctNm 구분
     */
    public String getExctNm() {
        return exctNm;
    }
    /**
     * 구분 를 설정 <br />
     * @param exctNm 구분 설정
     */
    public void setExctNm(String exctNm) {
        this.exctNm = exctNm;
    }
    /**
     * 등록일 를 취득 <br />
     * @return regDm 등록일
     */
    public String getRegDm() {
        return regDm;
    }
    /**
     * 등록일 를 설정 <br />
     * @param regDm 등록일 설정
     */
    public void setRegDm(String regDm) {
        this.regDm = regDm;
    }
    /**
     * 예외ID 를 취득 <br />
     * @return exctAppId 예외ID
     */
    public String getExctAppId() {
        return exctAppId;
    }
    /**
     * 예외ID 를 설정 <br />
     * @param exctAppId 예외ID 설정
     */
    public void setExctAppId(String exctAppId) {
        this.exctAppId = exctAppId;
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
     * 예외구분 반환한다. <br />
     * @return proGubun 예외구분
     */
    public String getProGubun() {
        return proGubun;
    }
    /**
     * 예외구분 설정한다. <br />
     * @param proGubun 예외구분
     */
    public void setProGubun(String proGubun) {
        this.proGubun = proGubun;
    }
    /**
     * 값2 반환한다. <br />
     * @return value2 값2
     */
    public String getValue2() {
        return value2;
    }
    /**
     * 값2 설정한다. <br />
     * @param value2 값2
     */
    public void setValue2(String value2) {
        this.value2 = value2;
    }
    /**
     * 통제구분 반환한다. <br />
     * @return attachGubun 통제구분
     */
    public String getAttachGubun() {
        return attachGubun;
    }
    /**
     * 통제구분 설정한다. <br />
     * @param attachGubun 통제구분
     */
    public void setAttachGubun(String attachGubun) {
        this.attachGubun = attachGubun;
    }


}
