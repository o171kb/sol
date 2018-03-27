package tt.bean;

import tt.com.bean.VoCoBase;


/**
 * <pre>
 * tt.bean
 *    |_ VoWkPolicy.java
 *
 * DESC :
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author hk-kim
 * @Date 2013. 5. 20. 오후 7:35:15
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 5. 20.		hk-kim				최초 작성
 *  WK_POLICY.dbo.TB_TRULE  TBL      WK_POLICY.dbo.TB_MTIMEDATA  TBL
 *	-----------------------------------------------------------------------
 *
 */
public class VoWkPolicy extends VoCoBase {

    /** 시리얼ID */
    private static final long serialVersionUID = 677728686743635141L;

    /** 규칙아이디 ex) F3D86F82-5444-432E-A66E-4A29C6EEFD76  */
    private String ruleGUID;
    /** 규칙이름 ex) 비업무사이트 차단 */
    private String ruleName;
    /** 웹규칙아이디 ex) AB05E474-F711-43CC-9690-189580CB28C2  */
    private String webRuleGUID;
    /** 사유 */
    private String descript;

    private String defaultPortAct;
    /** 부서아이디  ex) 699830C2-4779-4F11-A357-AFBA8C388FEA */
    private String deptGUID;
    /** 타임아이디  ex) 75A2AC31-951C-41F1-949B-4421A657AD65 */
    private String timeGUID;
    /** 타임값(이진수) ex) 0xFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF */
    private String timeValue;
    /** 시간대 ex) 모든시간대 */
    private String timeName;
    /** 아이디 GUID */
    private String empGUID;


    public String getEmpGUID() {
        return empGUID;
    }
    public void setEmpGUID(String empGUID) {
        this.empGUID = empGUID;
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
    public String getWebRuleGUID() {
        return webRuleGUID;
    }
    public void setWebRuleGUID(String webRuleGUID) {
        this.webRuleGUID = webRuleGUID;
    }
    public String getDescript() {
        return descript;
    }
    public void setDescript(String descript) {
        this.descript = descript;
    }
    public String getDefaultPortAct() {
        return defaultPortAct;
    }
    public void setDefaultPortAct(String defaultPortAct) {
        this.defaultPortAct = defaultPortAct;
    }
    public String getDeptGUID() {
        return deptGUID;
    }
    public void setDeptGUID(String deptGUID) {
        this.deptGUID = deptGUID;
    }
    public String getTimeGUID() {
        return timeGUID;
    }
    public void setTimeGUID(String timeGUID) {
        this.timeGUID = timeGUID;
    }
    public String getTimeValue() {
        return timeValue;
    }
    public void setTimeValue(String timeValue) {
        this.timeValue = timeValue;
    }
    public String getTimeName() {
        return timeName;
    }
    public void setTimeName(String timeName) {
        this.timeName = timeName;
    }



}
