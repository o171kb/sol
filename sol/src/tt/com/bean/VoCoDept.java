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
 *    |_ VoCoDept.java
 *
 * DESC : 부서 VO 클래스 <br />
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author ks-lee
 * @Date 2013. 3. 7. 오후 6:02:15
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 3. 7.		ks-lee				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
public class VoCoDept extends VoCoBase {

    private static final long serialVersionUID = 1315554918109341499L;

    /** 부서번호 칼럼명 */
    public static final String COL_DEPT_NO = "DEPT_NO";
    /** 부서코드 칼럼명 */
    public static final String COL_DEPT_CD = "DEPT_CD";
    /** 부서트리코드 칼럼명 */
    public static final String COL_MADE_CD = "MADE_CD";
    /** 상위부서번호 칼럼명 */
    public static final String COL_HIGH_DEPT_CD = "HIGH_DEPT_CD";
    /** 부서명 칼럼명 */
    public static final String COL_DEPT_NM = "DEPT_NM";
    /** 부서영문명 칼럼명 */
    public static final String COL_DEPT_EN = "DEPT_NM_EN";
    /** 부서설명 칼럼명 */
    public static final String COL_DEPT_EXP = "DEPT_EXP";
    /** 대표이메일 칼럼명 */
    public static final String COL_DEPT_EML = "DEPT_EML";
    /** 대표전화번호 칼럼명 */
    public static final String COL_DEPT_TEL_NO = "DEPT_TEL_NO";
    /** 부서우편번호 칼럼명 */
    public static final String COL_DEPT_ZIP_CD = "DEPT_ZIP_CD";
    /** 부서주소1 칼럼명 */
    public static final String COL_DEPT_ZIP_ADDR1 = "DEPT_ZIP_ADDR1";
    /** 부서주소2 칼럼명 */
    public static final String COL_DEPT_ZIP_ADDR2 = "DEPT_ZIP_ADDR2";
    /** 부서구분 칼럼명 */
    public static final String COL_DEPT_TP = "DEPT_TP";

    /** 부서번호 */
    private String deptNo;
    /** 부서코드 */
    private String deptCd;
    /** 부서트리코드 */
    private String madeCd;
    /** 상위부서번호 */
    private String highDeptCd;
    /** 부서명 */
    private String deptNm;
    /** 부서영문명 */
    private String deptNmEn;
    /** 부서설명 */
    private String deptExp;
    /** 대표이메일 */
    private String deptEml;
    /** 대표전화번호 */
    private String deptTelNo;
    /** 부서우편번호 */
    private String deptZipCd;
    /** 부서주소1 */
    private String deptZipAddr1;
    /** 부서주소2 */
    private String deptZipAddr2;
    /** 부서구분 */
    private String deptTp;
    /** 멀티삭제용 */
    private String delArr;
    
    private String userPosition;
    
    private String userNm;


    /**
     * 부서번호 를 취득 <br />
     * @return deptNo 부서번호
     */
    public String getDeptNo() {
        return deptNo;
    }
    /**
     * 부서번호 를 설정 <br />
     * @param deptNo 부서번호 설정
     */
    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }
    /**
     * 부서코드 를 취득 <br />
     * @return deptCd 부서코드
     */
    public String getDeptCd() {
        return deptCd;
    }
    /**
     * 부서코드 를 설정 <br />
     * @param deptCd 부서코드 설정
     */
    public void setDeptCd(String deptCd) {
        this.deptCd = deptCd;
    }
    /**
     * 부서트리코드 를 취득 <br />
     * @return madeCd 부서트리코드
     */
    public String getMadeCd() {
        return madeCd;
    }
    /**
     * 부서트리코드 를 설정 <br />
     * @param madeCd 부서트리코드 설정
     */
    public void setMadeCd(String madeCd) {
        this.madeCd = madeCd;
    }
    /**
     * 상위부서코드 를 취득 <br />
     * @return highDeptNo 상위부서코드
     */
    public String getHighDeptCd() {
        return highDeptCd;
    }
    /**
     * 상위부서코드 를 설정 <br />
     * @param highDeptCd 상위부서코드 설정
     */
    public void setHighDeptCd(String highDeptCd) {
        this.highDeptCd = highDeptCd;
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
     * 부서영문명 를 취득 <br />
     * @return deptNmEn 부서영문명
     */
    public String getDeptNmEn() {
        return deptNmEn;
    }
    /**
     * 부서영문명 를 설정 <br />
     * @param deptNmEn 부서영문명 설정
     */
    public void setDeptNmEn(String deptNmEn) {
        this.deptNmEn = deptNmEn;
    }
    /**
     * 부서설명 를 취득 <br />
     * @return deptExp 부서설명
     */
    public String getDeptExp() {
        return deptExp;
    }
    /**
     * 부서설명 를 설정 <br />
     * @param deptExp 부서설명 설정
     */
    public void setDeptExp(String deptExp) {
        this.deptExp = deptExp;
    }
    /**
     * 대표이메일 를 취득 <br />
     * @return deptEml 대표이메일
     */
    public String getDeptEml() {
        return deptEml;
    }
    /**
     * 대표이메일 를 설정 <br />
     * @param deptEml 대표이메일 설정
     */
    public void setDeptEml(String deptEml) {
        this.deptEml = deptEml;
    }
    /**
     * 대표전화번호 를 취득 <br />
     * @return deptTelNo 대표전화번호
     */
    public String getDeptTelNo() {
        return deptTelNo;
    }
    /**
     * 대표전화번호 를 설정 <br />
     * @param deptTelNo 대표전화번호 설정
     */
    public void setDeptTelNo(String deptTelNo) {
        this.deptTelNo = deptTelNo;
    }
    /**
     * 부서우편번호 를 취득 <br />
     * @return deptZipCd 부서우편번호
     */
    public String getDeptZipCd() {
        return deptZipCd;
    }
    /**
     * 부서우편번호 를 설정 <br />
     * @param deptZipCd 부서우편번호 설정
     */
    public void setDeptZipCd(String deptZipCd) {
        this.deptZipCd = deptZipCd;
    }
    /**
     * 부서주소1 를 취득 <br />
     * @return deptZipAddr1 부서주소1
     */
    public String getDeptZipAddr1() {
        return deptZipAddr1;
    }
    /**
     * 부서주소1 를 설정 <br />
     * @param deptZipAddr1 부서주소1 설정
     */
    public void setDeptZipAddr1(String deptZipAddr1) {
        this.deptZipAddr1 = deptZipAddr1;
    }
    /**
     * 부서주소2 를 취득 <br />
     * @return deptZipAddr2 부서주소2
     */
    public String getDeptZipAddr2() {
        return deptZipAddr2;
    }
    /**
     * 부서주소2 를 설정 <br />
     * @param deptZipAddr2 부서주소2 설정
     */
    public void setDeptZipAddr2(String deptZipAddr2) {
        this.deptZipAddr2 = deptZipAddr2;
    }
    /**
     * 부서구분 를 취득 <br />
     * @return deptTp 부서구분
     */
    public String getDeptTp() {
        return deptTp;
    }
    /**
     * 부서구분 를 설정 <br />
     * @param deptTp 부서구분 설정
     */
    public void setDeptTp(String deptTp) {
        this.deptTp = deptTp;
    }

    /**
     * 멀티삭제용 를 취득 <br />
     * @return delArr 멀티삭제용
     */
    public String getDelArr() {
        return delArr;
    }
    /**
     * 멀티삭제용 를 설정 <br />
     * @param delArr 멀티삭제용 설정
     */
    public void setDelArr(String delArr) {
        this.delArr = delArr;
    }
    public String getUserPosition() {
        return userPosition;
    }
    public void setUserPosition(String userPosition) {
        this.userPosition = userPosition;
    }
    public String getUserNm() {
        return userNm;
    }
    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }
    
    
}
