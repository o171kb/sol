package tt.bean;

import java.io.Serializable;

import tt.com.bean.VoCoBase;

public class VoExct extends VoCoBase implements Serializable {

    private static final long serialVersionUID = 1L;

    private String Id;
    private String IsDiagnosticTarget;
    private String InsDate;
    private String UpdDate;
    private String IsDel;
    private String IsSys;
    /* 프로시저1 */
    private String serial;
    private String gubun;
    private String exp_date;
    private String exp_desc;
    private String value1;
    private String allow_fromdate;
    private String allow_todate;
    private String reg_empno;
    private String grp_gubun;
    private String exception;
    private String rname;

    /*jsp 배열값*/
    private String arrChk;
    private String exceptionName;
    private String exctId;
    private String realExctNm;
    private String exctNm;
    private String exctDtl;
    private String proGubun;
    private String useYn;
    private String regDm;
    private String updDm;
    private String updUser;
    private String gubunAllow;
    private String gubunLog;
    private String reason;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getIsDiagnosticTarget() {
        return IsDiagnosticTarget;
    }

    public void setIsDiagnosticTarget(String isDiagnosticTarget) {
        IsDiagnosticTarget = isDiagnosticTarget;
    }

    public String getInsDate() {
        return InsDate;
    }

    public void setInsDate(String insDate) {
        InsDate = insDate;
    }

    public String getUpdDate() {
        return UpdDate;
    }

    public void setUpdDate(String updDate) {
        UpdDate = updDate;
    }

    public String getIsDel() {
        return IsDel;
    }

    public void setIsDel(String isDel) {
        IsDel = isDel;
    }

    public String getIsSys() {
        return IsSys;
    }

    public void setIsSys(String isSys) {
        IsSys = isSys;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getGubun() {
        return gubun;
    }

    public void setGubun(String gubun) {
        this.gubun = gubun;
    }

    public String getExp_date() {
        return exp_date;
    }

    public void setExp_date(String exp_date) {
        this.exp_date = exp_date;
    }

    public String getExp_desc() {
        return exp_desc;
    }

    public void setExp_desc(String exp_desc) {
        this.exp_desc = exp_desc;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getReg_empno() {
        return reg_empno;
    }

    public void setReg_empno(String reg_empno) {
        this.reg_empno = reg_empno;
    }

    public String getGrp_gubun() {
        return grp_gubun;
    }

    public void setGrp_gubun(String grp_gubun) {
        this.grp_gubun = grp_gubun;
    }

    public String getAllow_fromdate() {
        return allow_fromdate;
    }

    public void setAllow_fromdate(String allow_fromdate) {
        this.allow_fromdate = allow_fromdate;
    }

    public String getAllow_todate() {
        return allow_todate;
    }

    public void setAllow_todate(String allow_todate) {
        this.allow_todate = allow_todate;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getArrChk() {
        return arrChk;
    }

    public String getExceptionName() {
        return exceptionName;
    }

    public String getGubunAllow() {
        return gubunAllow;
    }

    public String getGubunLog() {
        return gubunLog;
    }

    public String getReason() {
        return reason;
    }

    public void setArrChk(String arrChk) {
        this.arrChk = arrChk;
    }

    public void setExceptionName(String exceptionName) {
        this.exceptionName = exceptionName;
    }

    public void setGubunAllow(String gubunAllow) {
        this.gubunAllow = gubunAllow;
    }

    public void setGubunLog(String gubunLog) {
        this.gubunLog = gubunLog;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getExctId() {
        return exctId;
    }

    public String getRealExctNm() {
        return realExctNm;
    }

    public String getExctNm() {
        return exctNm;
    }

    public String getExctDtl() {
        return exctDtl;
    }

    public String getProGubun() {
        return proGubun;
    }

    public String getUseYn() {
        return useYn;
    }

    public String getRegDm() {
        return regDm;
    }

    public String getUpdDm() {
        return updDm;
    }

    public String getUpdUser() {
        return updUser;
    }

    public void setExctId(String exctId) {
        this.exctId = exctId;
    }

    public void setRealExctNm(String realExctNm) {
        this.realExctNm = realExctNm;
    }

    public void setExctNm(String exctNm) {
        this.exctNm = exctNm;
    }

    public void setExctDtl(String exctDtl) {
        this.exctDtl = exctDtl;
    }

    public void setProGubun(String proGubun) {
        this.proGubun = proGubun;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public void setRegDm(String regDm) {
        this.regDm = regDm;
    }

    public void setUpdDm(String updDm) {
        this.updDm = updDm;
    }

    public void setUpdUser(String updUser) {
        this.updUser = updUser;
    }

}
