package tt.bean;

import java.io.Serializable;

import tt.com.bean.VoCoBase;

/**
 * DESC :
 *
 * @Company think-tree.inc
 * @author sj-hwang
 * @Date 2013. 4. 1. 오후 3:26:18
 */
public class VoPcBasic extends VoCoBase implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 시리얼 */
    private String serial;
    /** 사용자 ID */
    private String empNo;
    /** 사용자 이름 */
    private String hName;
    /**  */
    private String indeptNm;
    /**  */
    private String sdeptNm;
    /**  */
    private String locateNm;
    /**  */
    private String buildNm;
    /**  */
    private String area;
    /**  */
    private String startDate;
    /**  */
    private String latestDate;
    /**  */
    private String accessCnt;
    /**  */
    private String errorCnt;
    /** PC 종류 */
    private String pcType;
    /** IP 주소 */
    private String ipAddr;
    /**  */
    private String version;
    /**  */
    private String model;
    /**  */
    private String pcGubun;
    /** 사용 OS */
    private String os;
    /**  */
    private String remarks;
    /** 세부위치 */
    private String sebuArea;
    /**  */
    private String propNo;
    /** 컴퓨터 이름 */
    private String computerName;
    /**  */
    private String cpuId;
    /**  */
    private String mac;
    /**  */
    private String ownership;
    /**  */
    private String propGubun;
    /** 실제 IP 주소 */
    private String realIpAddr;
    /**  */
    private String pcUsed;
    /**  */
    private String tempSave;
    /**  */
    private String residence;
    /**  */
    private String vupTime;
    /** 부서코드 */
    private String deptCode;
    /**  */
    private String madeCode;
    /**  */
    private String buildVer;
    /** 예외대상PC선택 */
    private String chkPc;
    /** 예외대상 사원번호 */
    private String chkEmpNo;
    private String deptNm;
    
    private String userId;
    private String eMail;
    /**
     * 시리얼 반환한다. <br />
     * @return serial 시리얼
     */
    public String getSerial() {
        return serial;
    }
    /**
     * 사용자 ID 반환한다. <br />
     * @return empNo 사용자 ID
     */
    public String getEmpNo() {
        return empNo;
    }
    /**
     * 사용자 이름 반환한다. <br />
     * @return hName 사용자 이름
     */
    public String gethName() {
        return hName;
    }
    /**
     * xxxxx 반환한다. <br />
     * @return indeptNm xxxxx
     */
    public String getIndeptNm() {
        return indeptNm;
    }
    /**
     * xxxxx 반환한다. <br />
     * @return sdeptNm xxxxx
     */
    public String getSdeptNm() {
        return sdeptNm;
    }
    /**
     * xxxxx 반환한다. <br />
     * @return locateNm xxxxx
     */
    public String getLocateNm() {
        return locateNm;
    }
    /**
     * xxxxx 반환한다. <br />
     * @return buildNm xxxxx
     */
    public String getBuildNm() {
        return buildNm;
    }
    /**
     * xxxxx 반환한다. <br />
     * @return area xxxxx
     */
    public String getArea() {
        return area;
    }
    /**
     * xxxxx 반환한다. <br />
     * @return startDate xxxxx
     */
    public String getStartDate() {
        return startDate;
    }
    /**
     * xxxxx 반환한다. <br />
     * @return latestDate xxxxx
     */
    public String getLatestDate() {
        return latestDate;
    }
    /**
     * xxxxx 반환한다. <br />
     * @return accessCnt xxxxx
     */
    public String getAccessCnt() {
        return accessCnt;
    }
    /**
     * xxxxx 반환한다. <br />
     * @return errorCnt xxxxx
     */
    public String getErrorCnt() {
        return errorCnt;
    }
    /**
     * PC 종류 반환한다. <br />
     * @return pcType PC 종류
     */
    public String getPcType() {
        return pcType;
    }
    /**
     * IP 주소 반환한다. <br />
     * @return ipAddr IP 주소
     */
    public String getIpAddr() {
        return ipAddr;
    }
    /**
     * xxxxx 반환한다. <br />
     * @return version xxxxx
     */
    public String getVersion() {
        return version;
    }
    /**
     * xxxxx 반환한다. <br />
     * @return model xxxxx
     */
    public String getModel() {
        return model;
    }
    /**
     * xxxxx 반환한다. <br />
     * @return pcGubun xxxxx
     */
    public String getPcGubun() {
        return pcGubun;
    }
    /**
     * 사용 OS 반환한다. <br />
     * @return os 사용 OS
     */
    public String getOs() {
        return os;
    }
    /**
     * xxxxx 반환한다. <br />
     * @return remarks xxxxx
     */
    public String getRemarks() {
        return remarks;
    }
    /**
     * 세부위치 반환한다. <br />
     * @return sebuArea 세부위치
     */
    public String getSebuArea() {
        return sebuArea;
    }
    /**
     * xxxxx 반환한다. <br />
     * @return propNo xxxxx
     */
    public String getPropNo() {
        return propNo;
    }
    /**
     * 컴퓨터 이름 반환한다. <br />
     * @return computerName 컴퓨터 이름
     */
    public String getComputerName() {
        return computerName;
    }
    /**
     * xxxxx 반환한다. <br />
     * @return cpuId xxxxx
     */
    public String getCpuId() {
        return cpuId;
    }
    /**
     * xxxxx 반환한다. <br />
     * @return mac xxxxx
     */
    public String getMac() {
        return mac;
    }
    /**
     * xxxxx 반환한다. <br />
     * @return ownership xxxxx
     */
    public String getOwnership() {
        return ownership;
    }
    /**
     * xxxxx 반환한다. <br />
     * @return propGubun xxxxx
     */
    public String getPropGubun() {
        return propGubun;
    }
    /**
     * 실제 IP 주소 반환한다. <br />
     * @return realIpAddr 실제 IP 주소
     */
    public String getRealIpAddr() {
        return realIpAddr;
    }
    /**
     * xxxxx 반환한다. <br />
     * @return pcUsed xxxxx
     */
    public String getPcUsed() {
        return pcUsed;
    }
    /**
     * xxxxx 반환한다. <br />
     * @return tempSave xxxxx
     */
    public String getTempSave() {
        return tempSave;
    }
    /**
     * xxxxx 반환한다. <br />
     * @return residence xxxxx
     */
    public String getResidence() {
        return residence;
    }
    /**
     * xxxxx 반환한다. <br />
     * @return vupTime xxxxx
     */
    public String getVupTime() {
        return vupTime;
    }
    /**
     * 부서코드 반환한다. <br />
     * @return deptCode 부서코드
     */
    public String getDeptCode() {
        return deptCode;
    }
    /**
     * xxxxx 반환한다. <br />
     * @return madeCode xxxxx
     */
    public String getMadeCode() {
        return madeCode;
    }
    /**
     * xxxxx 반환한다. <br />
     * @return buildVer xxxxx
     */
    public String getBuildVer() {
        return buildVer;
    }
    /**
     * 시리얼 설정한다. <br />
     * @param serial 시리얼
     */
    public void setSerial(String serial) {
        this.serial = serial;
    }
    /**
     * 사용자 ID 설정한다. <br />
     * @param empNo 사용자 ID
     */
    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }
    /**
     * 사용자 이름 설정한다. <br />
     * @param hName 사용자 이름
     */
    public void sethName(String hName) {
        this.hName = hName;
    }
    /**
     * xxxxx 설정한다. <br />
     * @param indeptNm xxxxx
     */
    public void setIndeptNm(String indeptNm) {
        this.indeptNm = indeptNm;
    }
    /**
     * xxxxx 설정한다. <br />
     * @param sdeptNm xxxxx
     */
    public void setSdeptNm(String sdeptNm) {
        this.sdeptNm = sdeptNm;
    }
    /**
     * xxxxx 설정한다. <br />
     * @param locateNm xxxxx
     */
    public void setLocateNm(String locateNm) {
        this.locateNm = locateNm;
    }
    /**
     * xxxxx 설정한다. <br />
     * @param buildNm xxxxx
     */
    public void setBuildNm(String buildNm) {
        this.buildNm = buildNm;
    }
    /**
     * xxxxx 설정한다. <br />
     * @param area xxxxx
     */
    public void setArea(String area) {
        this.area = area;
    }
    /**
     * xxxxx 설정한다. <br />
     * @param startDate xxxxx
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    /**
     * xxxxx 설정한다. <br />
     * @param latestDate xxxxx
     */
    public void setLatestDate(String latestDate) {
        this.latestDate = latestDate;
    }
    /**
     * xxxxx 설정한다. <br />
     * @param accessCnt xxxxx
     */
    public void setAccessCnt(String accessCnt) {
        this.accessCnt = accessCnt;
    }
    /**
     * xxxxx 설정한다. <br />
     * @param errorCnt xxxxx
     */
    public void setErrorCnt(String errorCnt) {
        this.errorCnt = errorCnt;
    }
    /**
     * PC 종류 설정한다. <br />
     * @param pcType PC 종류
     */
    public void setPcType(String pcType) {
        this.pcType = pcType;
    }
    /**
     * IP 주소 설정한다. <br />
     * @param ipAddr IP 주소
     */
    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }
    /**
     * xxxxx 설정한다. <br />
     * @param version xxxxx
     */
    public void setVersion(String version) {
        this.version = version;
    }
    /**
     * xxxxx 설정한다. <br />
     * @param model xxxxx
     */
    public void setModel(String model) {
        this.model = model;
    }
    /**
     * xxxxx 설정한다. <br />
     * @param pcGubun xxxxx
     */
    public void setPcGubun(String pcGubun) {
        this.pcGubun = pcGubun;
    }
    /**
     * 사용 OS 설정한다. <br />
     * @param os 사용 OS
     */
    public void setOs(String os) {
        this.os = os;
    }
    /**
     * xxxxx 설정한다. <br />
     * @param remarks xxxxx
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    /**
     * 세부위치 설정한다. <br />
     * @param sebuArea 세부위치
     */
    public void setSebuArea(String sebuArea) {
        this.sebuArea = sebuArea;
    }
    /**
     * xxxxx 설정한다. <br />
     * @param propNo xxxxx
     */
    public void setPropNo(String propNo) {
        this.propNo = propNo;
    }
    /**
     * 컴퓨터 이름 설정한다. <br />
     * @param computerName 컴퓨터 이름
     */
    public void setComputerName(String computerName) {
        this.computerName = computerName;
    }
    /**
     * xxxxx 설정한다. <br />
     * @param cpuId xxxxx
     */
    public void setCpuId(String cpuId) {
        this.cpuId = cpuId;
    }
    /**
     * xxxxx 설정한다. <br />
     * @param mac xxxxx
     */
    public void setMac(String mac) {
        this.mac = mac;
    }
    /**
     * xxxxx 설정한다. <br />
     * @param ownership xxxxx
     */
    public void setOwnership(String ownership) {
        this.ownership = ownership;
    }
    /**
     * xxxxx 설정한다. <br />
     * @param propGubun xxxxx
     */
    public void setPropGubun(String propGubun) {
        this.propGubun = propGubun;
    }
    /**
     * 실제 IP 주소 설정한다. <br />
     * @param realIpAddr 실제 IP 주소
     */
    public void setRealIpAddr(String realIpAddr) {
        this.realIpAddr = realIpAddr;
    }
    /**
     * xxxxx 설정한다. <br />
     * @param pcUsed xxxxx
     */
    public void setPcUsed(String pcUsed) {
        this.pcUsed = pcUsed;
    }
    /**
     * xxxxx 설정한다. <br />
     * @param tempSave xxxxx
     */
    public void setTempSave(String tempSave) {
        this.tempSave = tempSave;
    }
    /**
     * xxxxx 설정한다. <br />
     * @param residence xxxxx
     */
    public void setResidence(String residence) {
        this.residence = residence;
    }
    /**
     * xxxxx 설정한다. <br />
     * @param vupTime xxxxx
     */
    public void setVupTime(String vupTime) {
        this.vupTime = vupTime;
    }
    /**
     * 부서코드 설정한다. <br />
     * @param deptCode 부서코드
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }
    /**
     * xxxxx 설정한다. <br />
     * @param madeCode xxxxx
     */
    public void setMadeCode(String madeCode) {
        this.madeCode = madeCode;
    }
    /**
     * xxxxx 설정한다. <br />
     * @param buildVer xxxxx
     */
    public void setBuildVer(String buildVer) {
        this.buildVer = buildVer;
    }
    /**
     * 예외대상PC선택 반환한다. <br />
     * @return chkPc 예외대상PC선택
     */
    public String getChkPc() {
        return chkPc;
    }
    /**
     * 예외대상PC선택 설정한다. <br />
     * @param chkPc 예외대상PC선택
     */
    public void setChkPc(String chkPc) {
        this.chkPc = chkPc;
    }
    /**
     * 예외대상 사원번호 반환한다. <br />
     * @return chkEmpNo 예외대상 사원번호
     */
    public String getChkEmpNo() {
        return chkEmpNo;
    }
    /**
     * 예외대상 사원번호 설정한다. <br />
     * @param chkEmpNo 예외대상 사원번호
     */
    public void setChkEmpNo(String chkEmpNo) {
        this.chkEmpNo = chkEmpNo;
    }
    /**
     * xxxxx 반환한다. <br />
     * @return deptNm xxxxx
     */
    public String getDeptNm() {
        return deptNm;
    }
    /**
     * xxxxx 설정한다. <br />
     * @param deptNm xxxxx
     */
    public void setDeptNm(String deptNm) {
        this.deptNm = deptNm;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String geteMail() {
        return eMail;
    }
    public void seteMail(String eMail) {
        this.eMail = eMail;
    }
    
    
}
