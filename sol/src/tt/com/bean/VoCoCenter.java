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
 *    |_ VoCoCenter.java
 *
 * DESC : 센터정보 VO 클래스
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author ks-lee
 * @Date 2013. 2. 27. 오후 1:17:49
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 2. 27.		ks-lee				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
public class VoCoCenter {

    /** 센터번호 칼럼명 */
    public static final String COL_CENTER_NO = "CENTER_NO";
    /** 게이트구분 칼럼명 */
    public static final String COL_GATE_CL = "GATE_CL";
    /** 센터명 칼럼명 */
    public static final String COL_CENTER_NM = "CENTER_NM";
    /** 센터이미지파일 칼럼명 */
    public static final String COL_CENTER_LOGO_IMG = "CENTER_LOGO_IMG";
    /** 광고이미지파일 칼럼명 */
    public static final String COL_CENTER_BAN_IMG = "CENTER_BAN_IMG";
    /** 센터타이틀 칼럼명 */
    public static final String COL_TITLE = "TITLE";
    /** META DESCRIPTION 칼럼명 */
    public static final String COL_DESCRIPTION = "DESCRIPTION";
    /** 센터URL 칼럼명 */
    public static final String COL_CENTER_URL = "CENTER_URL";
    /** 센터 SSL URL 칼럼명 */
    public static final String COL_CENTER_SSL_URL = "CENTER_SSL_URL";
    /** 대표이메일주소 칼럼명 */
    public static final String COL_CENTER_EML = "CENTER_EML";
    /** 시스템정보 칼럼명 */
    public static final String COL_CENTER_SYS_INF = "CENTER_SYS_INF";
    /** 데이타베이스정보 칼럼명 */
    public static final String COL_CENTER_DB_INF = "CENTER_DB_INF";
    /** 담당자이메일주소 칼럼명 */
    public static final String COL_MSTR_EML = "MSTR_EML";
    /** META KEYWORDS 칼럼명 */
    public static final String COL_KEYWORDS = "KEYWORDS";
    /** 문자열설정 칼럼명 */
    public static final String COL_MARQUEE = "MARQUEE";
    /** 모바일커스터마이징 칼럼명 */
    public static final String COL_MB_CUSTOMIZE_ITEM = "MB_CUSTOMIZE_ITEM";
    /** 센터구분 칼럼명 */
    public static final String COL_CENTER_TP = "CENTER_TP";
    /** 배송료구분 칼럼명 */
    public static final String COL_DELVCHAR_FLG = "DELVCHAR_FLG";
    /** 일괄배송료 칼럼명 */
    public static final String COL_DELV_CHARGE = "DELV_CHARGE";
    /** 대금수수료구분 칼럼명 */
    public static final String COL_CASHDELV_FLG = "CASHDELV_FLG";

    /** 센터번호 */
    private String centerNo;
    /** 게이트구분 */
    private String gateCl;
    /** 센터명 */
    private String centerNm;
    /** 센터이미지파일 */
    private String centerLogoImg;
    /** 광고이미지파일 */
    private String centerBanImg;
    /** 센터타이틀 */
    private String title;
    /** META DESCRIPTION */
    private String description;
    /** 센터URL */
    private String centerUrl;
    /** 센터 SSL URL */
    private String centerSslUrl;
    /** 대표이메일주소 */
    private String centerEml;
    /** 시스템정보 */
    private String centerSysInf;
    /** 데이타베이스정보 */
    private String centerDbInf;
    /** 담당자이메일주소 */
    private String mstrEml;
    /** META KEYWORDS */
    private String keywords;
    /** 문자열설정 */
    private String marquee;
    /** 모바일커스터마이징 */
    private String mbCustomizeItem;
    /** 센터구분 */
    private String centerTp;
    /** 배송료구분 */
    private String delvcharFlg;
    /** 일괄배송료 */
    private String delvCharge;
    /** 대금수수료구분 */
    private String cashdelvFlg;


    /**
     * 센터번호 를 취득 <br />
     * @return centerNo 센터번호
     */
    public String getCenterNo() {
        return centerNo;
    }
    /**
     * 센터번호 를 설정 <br />
     * @param centerNo 센터번호 설정
     */
    public void setCenterNo(String centerNo) {
        this.centerNo = centerNo;
    }
    /**
     * 게이트구분 를 취득 <br />
     * @return gateCl 게이트구분
     */
    public String getGateCl() {
        return gateCl;
    }
    /**
     * 게이트구분 를 설정 <br />
     * @param gateCl 게이트구분 설정
     */
    public void setGateCl(String gateCl) {
        this.gateCl = gateCl;
    }
    /**
     * 센터명 를 취득 <br />
     * @return centerNm 센터명
     */
    public String getCenterNm() {
        return centerNm;
    }
    /**
     * 센터명 를 설정 <br />
     * @param centerNm 센터명 설정
     */
    public void setCenterNm(String centerNm) {
        this.centerNm = centerNm;
    }
    /**
     * 센터이미지파일 를 취득 <br />
     * @return centerLogoImg 센터이미지파일
     */
    public String getCenterLogoImg() {
        return centerLogoImg;
    }
    /**
     * 센터이미지파일 를 설정 <br />
     * @param centerLogoImg 센터이미지파일 설정
     */
    public void setCenterLogoImg(String centerLogoImg) {
        this.centerLogoImg = centerLogoImg;
    }
    /**
     * 광고이미지파일 를 취득 <br />
     * @return centerBanImg 광고이미지파일
     */
    public String getCenterBanImg() {
        return centerBanImg;
    }
    /**
     * 광고이미지파일 를 설정 <br />
     * @param centerBanImg 광고이미지파일 설정
     */
    public void setCenterBanImg(String centerBanImg) {
        this.centerBanImg = centerBanImg;
    }
    /**
     * 센터타이틀 를 취득 <br />
     * @return title 센터타이틀
     */
    public String getTitle() {
        return title;
    }
    /**
     * 센터타이틀 를 설정 <br />
     * @param title 센터타이틀 설정
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * META DESCRIPTION 를 취득 <br />
     * @return description META DESCRIPTION
     */
    public String getDescription() {
        return description;
    }
    /**
     * META DESCRIPTION 를 설정 <br />
     * @param description META DESCRIPTION 설정
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * 센터URL 를 취득 <br />
     * @return centerUrl 센터URL
     */
    public String getCenterUrl() {
        return centerUrl;
    }
    /**
     * 센터URL 를 설정 <br />
     * @param centerUrl 센터URL 설정
     */
    public void setCenterUrl(String centerUrl) {
        this.centerUrl = centerUrl;
    }
    /**
     * 센터 SSL URL 를 취득 <br />
     * @return centerSslUrl 센터 SSL URL
     */
    public String getCenterSslUrl() {
        return centerSslUrl;
    }
    /**
     * 센터 SSL URL 를 설정 <br />
     * @param centerSslUrl 센터 SSL URL 설정
     */
    public void setCenterSslUrl(String centerSslUrl) {
        this.centerSslUrl = centerSslUrl;
    }
    /**
     * 대표이메일주소 를 취득 <br />
     * @return centerEml 대표이메일주소
     */
    public String getCenterEml() {
        return centerEml;
    }
    /**
     * 대표이메일주소 를 설정 <br />
     * @param centerEml 대표이메일주소 설정
     */
    public void setCenterEml(String centerEml) {
        this.centerEml = centerEml;
    }
    /**
     * 시스템정보 를 취득 <br />
     * @return centerSysInf 시스템정보
     */
    public String getCenterSysInf() {
        return centerSysInf;
    }
    /**
     * 시스템정보 를 설정 <br />
     * @param centerSysInf 시스템정보 설정
     */
    public void setCenterSysInf(String centerSysInf) {
        this.centerSysInf = centerSysInf;
    }
    /**
     * 데이타베이스정보 를 취득 <br />
     * @return centerDbInf 데이타베이스정보
     */
    public String getCenterDbInf() {
        return centerDbInf;
    }
    /**
     * 데이타베이스정보 를 설정 <br />
     * @param centerDbInf 데이타베이스정보 설정
     */
    public void setCenterDbInf(String centerDbInf) {
        this.centerDbInf = centerDbInf;
    }
    /**
     * 담당자이메일주소 를 취득 <br />
     * @return mstrEml 담당자이메일주소
     */
    public String getMstrEml() {
        return mstrEml;
    }
    /**
     * 담당자이메일주소 를 설정 <br />
     * @param mstrEml 담당자이메일주소 설정
     */
    public void setMstrEml(String mstrEml) {
        this.mstrEml = mstrEml;
    }
    /**
     * META KEYWORDS 를 취득 <br />
     * @return keywords META KEYWORDS
     */
    public String getKeywords() {
        return keywords;
    }
    /**
     * META KEYWORDS 를 설정 <br />
     * @param keywords META KEYWORDS 설정
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
    /**
     * 문자열설정 를 취득 <br />
     * @return marquee 문자열설정
     */
    public String getMarquee() {
        return marquee;
    }
    /**
     * 문자열설정 를 설정 <br />
     * @param marquee 문자열설정 설정
     */
    public void setMarquee(String marquee) {
        this.marquee = marquee;
    }
    /**
     * 모바일커스터마이징 를 취득 <br />
     * @return mbCustomizeItem 모바일커스터마이징
     */
    public String getMbCustomizeItem() {
        return mbCustomizeItem;
    }
    /**
     * 모바일커스터마이징 를 설정 <br />
     * @param mbCustomizeItem 모바일커스터마이징 설정
     */
    public void setMbCustomizeItem(String mbCustomizeItem) {
        this.mbCustomizeItem = mbCustomizeItem;
    }
    /**
     * 센터구분 를 취득 <br />
     * @return centerTp 센터구분
     */
    public String getCenterTp() {
        return centerTp;
    }
    /**
     * 센터구분 를 설정 <br />
     * @param centerTp 센터구분 설정
     */
    public void setCenterTp(String centerTp) {
        this.centerTp = centerTp;
    }
    /**
     * 배송료구분 를 취득 <br />
     * @return delvcharFlg 배송료구분
     */
    public String getDelvcharFlg() {
        return delvcharFlg;
    }
    /**
     * 배송료구분 를 설정 <br />
     * @param delvcharFlg 배송료구분 설정
     */
    public void setDelvcharFlg(String delvcharFlg) {
        this.delvcharFlg = delvcharFlg;
    }
    /**
     * 일괄배송료 를 취득 <br />
     * @return delvCharge 일괄배송료
     */
    public String getDelvCharge() {
        return delvCharge;
    }
    /**
     * 일괄배송료 를 설정 <br />
     * @param delvCharge 일괄배송료 설정
     */
    public void setDelvCharge(String delvCharge) {
        this.delvCharge = delvCharge;
    }
    /**
     * 대금수수료구분 를 취득 <br />
     * @return cashdelvFlg 대금수수료구분
     */
    public String getCashdelvFlg() {
        return cashdelvFlg;
    }
    /**
     * 대금수수료구분 를 설정 <br />
     * @param cashdelvFlg 대금수수료구분 설정
     */
    public void setCashdelvFlg(String cashdelvFlg) {
        this.cashdelvFlg = cashdelvFlg;
    }

}
