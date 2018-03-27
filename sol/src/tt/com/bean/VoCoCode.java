package tt.com.bean;

import java.io.Serializable;

/**
 * DESC : 코드  VO 클래스 <br />
 *
 * @Company think-tree.inc
 * @author ks-lee
 * @Date 2013. 2. 15. 오전 11:46:23
 */
public class VoCoCode implements Serializable {

    private static final long serialVersionUID = 1L;

    // 코드번호
    private String cdNo = "";
    // 코드명
    private String cdNm = "";
    // 코드설명
    private String cdExp = "";
    // 시스템관련
    private String sysCdyn = "";
    // 코드상세번호
    private String cdDtlNo = "";
    // 코드상세명
    private String cdDtlNm = "";
    // 코드상세설명
    private String cdDtlExp = "";
    // 우선순위
    private String prirSeq = "";
    // 등록일
    private String regDm = "";
    // 수정일
    private String updDm = "";

    /**
     * 코드번호 반환한다.
     *
     * @return cdNo 코드번호
     */
    public String getCdNo() {
        return cdNo;
    }

    /**
     * 코드번호 설정한다.
     *
     * @param cdNo 코드번호
     */
    public void setCdNo(String cdNo) {
        this.cdNo = cdNo;
    }

    /**
     * 코드명 반환한다.
     *
     * @return cdNm 코드명
     */
    public String getCdNm() {
        return cdNm;
    }

    /**
     * 코드명 설정한다.
     *
     * @param cdNm 코드명
     */
    public void setCdNm(String cdNm) {
        this.cdNm = cdNm;
    }

    /**
     * 코드설명 반환한다.
     *
     * @return cdExp 코드설명
     */
    public String getCdExp() {
        return cdExp;
    }

    /**
     * 코드설명 설정한다.
     *
     * @param cdExp 코드설명
     */
    public void setCdExp(String cdExp) {
        this.cdExp = cdExp;
    }

    /**
     * 시스템관련 반환한다.
     *
     * @return sysCdyn 시스템관련
     */
    public String getSysCdYn() {
        return sysCdyn;
    }

    /**
     * 시스템관련 설정한다.
     *
     * @param sysCdyn 시스템관련
     */
    public void setSysCdYn(String sysCdyn) {
        this.sysCdyn = sysCdyn;
    }

    /**
     * 코드상세번호 반환한다.
     *
     * @return cdDtlNo 코드상세번호
     */
    public String getCdDtlNo() {
        return cdDtlNo;
    }

    /**
     * 코드상세번호 설정한다.
     *
     * @param cdDtlNo 코드상세번호
     */
    public void setCdDtlNo(String cdDtlNo) {
        this.cdDtlNo = cdDtlNo;
    }

    /**
     * 코드상세명 반환한다.
     *
     * @return cdDtlNm 코드상세명
     */
    public String getCdDtlNm() {
        return cdDtlNm;
    }

    /**
     * 코드상세명 설정한다.
     *
     * @param cdDtlNm 코드상세명
     */
    public void setCdDtlNm(String cdDtlNm) {
        this.cdDtlNm = cdDtlNm;
    }

    /**
     * 코드상세설명 반환한다.
     *
     * @return cdDtlExp 코드상세설명
     */
    public String getCdDtlExp() {
        return cdDtlExp;
    }

    /**
     * 코드상세설명 설정한다.
     *
     * @param cdDtlExp 코드상세설명
     */
    public void setCdDtlExp(String cdDtlExp) {
        this.cdDtlExp = cdDtlExp;
    }

    /**
     * 우선순위 반환한다.
     *
     * @return prirSeq 우선순위
     */
    public String getPrirSeq() {
        return prirSeq;
    }

    /**
     * 우선순위 설정한다.
     *
     * @param prirSeq 우선순위
     */
    public void setPrirSeq(String prirSeq) {
        this.prirSeq = prirSeq;
    }

    /**
     * 등록일 반환한다.
     *
     * @return regDm 등록일
     */
    public String getRegDm() {
        return regDm;
    }

    /**
     * 등록일 설정한다.
     *
     * @param regDm 등록일
     */
    public void setRegDm(String regDm) {
        this.regDm = regDm;
    }

    /**
     * 수정일 반환한다.
     *
     * @return updDm 수정일
     */
    public String getUpdDm() {
        return updDm;
    }

    /**
     * 수정일 설정한다.
     *
     * @param updDm 수정일
     */
    public void setUpdDm(String updDm) {
        this.updDm = updDm;
    }
}
