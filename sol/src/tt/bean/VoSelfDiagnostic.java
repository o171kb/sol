package tt.bean;





/**
 * <pre>
 * tt.bean
 *    |_ VoSelfDiagnostic.java
 *
 * DESC :
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author hkkim
 * @Date 2013. 5. 8. 오전 10:05:05
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 5. 8.		hkkim				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
public class VoSelfDiagnostic extends VoPcCheck {

    private static final long serialVersionUID = 1315554918109341499L;

    /** pk */
    private String idx;
    /** serialNumber */
    private String serialNumber;
    /** id */
    private String userId;
    /** macAddress */
    private String macAddress;
    /** osType */
    private String osType;
    /** 등록일 */
    private String insDate;
    /** 수정일 */
    private String updDate;
    /** 삭제여부 */
    private String isDel;
    /** xxxxx */
    private String isSys;

    private String deptCd;

    private String deptNm;

    private String userNm;

    private String userPosition;

    private String deptNo;

    private String pcUser;  //점검자 리스트

    private String cdDtlNm;

    private String chkResult;

    private String realIpAddr;

    private String rownum;

    private String dangerList;

    private String isExcel;

    private String pcCnt;

    private int searchDate; //점검률 산출 기간

    private String safeStandard; //안전율 산출기준

    public String getSafeStandard() {
        return safeStandard;
    }
    public void setSafeStandard(String safeStandard) {
        this.safeStandard = safeStandard;
    }
    public int getSearchDate() {
        return searchDate;
    }
    public void setSearchDate(int searchDate) {
        this.searchDate = searchDate;
    }
    public String getDangerList() {
        return dangerList;
    }
    public void setDangerList(String dangerList) {
        this.dangerList = dangerList;
    }
    public String getPcCnt() {
        return pcCnt;
    }
    public void setPcCnt(String pcCnt) {
        this.pcCnt = pcCnt;
    }
    public String getIsExcel() {
        return isExcel;
    }
    public void setIsExcel(String isExcel) {
        this.isExcel = isExcel;
    }
    public String getRownum() {
        return rownum;
    }
    public void setRownum(String rownum) {
        this.rownum = rownum;
    }
    public String getRealIpAddr() {
        return realIpAddr;
    }
    public void setRealIpAddr(String realIpAddr) {
        this.realIpAddr = realIpAddr;
    }
    public String getChkResult() {
        return chkResult;
    }
    public void setChkResult(String chkResult) {
        this.chkResult = chkResult;
    }
    public String getCdDtlNm() {
        return cdDtlNm;
    }
    public void setCdDtlNm(String cdDtlNm) {
        this.cdDtlNm = cdDtlNm;
    }
    public String getPcUser() {
        return pcUser;
    }
    public void setPcUser(String pcUser) {
        this.pcUser = pcUser;
    }
    public String getDeptNo() {
        return deptNo;
    }
    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }
    public String getUserPosition() {
        return userPosition;
    }
    public void setUserPosition(String userPosition) {
        this.userPosition = userPosition;
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
    public String getUserNm() {
        return userNm;
    }
    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }
    /**
     * XXXXX 를 취득 <br />
     * @return idx XXXXX
     */
    public String getIdx() {
        return idx;
    }
    /**
     * XXXXX 를 설정 <br />
     * @param idx XXXXX 설정
     */
    public void setIdx(String idx) {
        this.idx = idx;
    }
    /**
     * XXXXX 를 취득 <br />
     * @return serialNumber XXXXX
     */
    public String getSerialNumber() {
        return serialNumber;
    }
    /**
     * XXXXX 를 설정 <br />
     * @param serialNumber XXXXX 설정
     */
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
    /**
     * XXXXX 를 취득 <br />
     * @return userId XXXXX
     */
    public String getUserId() {
        return userId;
    }
    /**
     * XXXXX 를 설정 <br />
     * @param userId XXXXX 설정
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**
     * XXXXX 를 취득 <br />
     * @return macAddress XXXXX
     */
    public String getMacAddress() {
        return macAddress;
    }
    /**
     * XXXXX 를 설정 <br />
     * @param macAddress XXXXX 설정
     */
    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }
    /**
     * XXXXX 를 취득 <br />
     * @return osType XXXXX
     */
    public String getOsType() {
        return osType;
    }
    /**
     * XXXXX 를 설정 <br />
     * @param osType XXXXX 설정
     */
    public void setOsType(String osType) {
        this.osType = osType;
    }
    /**
     * XXXXX 를 취득 <br />
     * @return insDate XXXXX
     */
    public String getInsDate() {
        return insDate;
    }
    /**
     * XXXXX 를 설정 <br />
     * @param insDate XXXXX 설정
     */
    public void setInsDate(String insDate) {
        this.insDate = insDate;
    }
    /**
     * XXXXX 를 취득 <br />
     * @return updDate XXXXX
     */
    public String getUpdDate() {
        return updDate;
    }
    /**
     * XXXXX 를 설정 <br />
     * @param updDate XXXXX 설정
     */
    public void setUpdDate(String updDate) {
        this.updDate = updDate;
    }
    /**
     * XXXXX 를 취득 <br />
     * @return isDel XXXXX
     */
    public String getIsDel() {
        return isDel;
    }
    /**
     * XXXXX 를 설정 <br />
     * @param isDel XXXXX 설정
     */
    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }
    /**
     * XXXXX 를 취득 <br />
     * @return isSys XXXXX
     */
    public String getIsSys() {
        return isSys;
    }
    /**
     * XXXXX 를 설정 <br />
     * @param isSys XXXXX 설정
     */
    public void setIsSys(String isSys) {
        this.isSys = isSys;
    }


}
