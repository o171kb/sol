package tt.bean;

import java.io.Serializable;

import tt.com.bean.VoCoBase;

/**
 * DESC :
 *
 * @Company think-tree.inc
 * @author sj-hwang
 * @Date 2013. 3. 21. 오후 4:23:30
 */
public class VoExctDraft extends VoCoBase implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 예외신청문구ID */
    private String exctAppId;
    /** 신청사유기본문구 */
    private String exctAppNm;
    /** 예외신청문구명 */
    private String exctBasicTerms;
    /** 최대신청기간 */
    private String maxAppTerm;
    /** 신청기간안내문구 */
    private String appTermEx;
    /** 증빙서류첨부 */
    private String addProofYn;
    /** 증빙서류첨부사용 개월 */
    private String addProofMonth;
    /** 증빙서류첨부사용 일 */
    private String addProofDay;
    /** 상단안내문구 */
    private String topTerms;
    /** 결재안내문구 */
    private String permitTerms;
    /** 하단안내문구 */
    private String bottomTerms;
    /** 등록일 */
    private String regDm;
    /** 수정일 */
    private String updDm;
    /** 수정자 */
    private String updUser;
    /**
     * 예외신청문구ID 반환한다. <br />
     * @return exctAppId 예외신청문구ID
     */
    public String getExctAppId() {
        return exctAppId;
    }
    /**
     * 신청사유기본문구 반환한다. <br />
     * @return exctAppNm 신청사유기본문구
     */
    public String getExctAppNm() {
        return exctAppNm;
    }
    /**
     * 예외신청문구명 반환한다. <br />
     * @return exctBasicTerms 예외신청문구명
     */
    public String getExctBasicTerms() {
        return exctBasicTerms;
    }
    /**
     * 최대신청기간 반환한다. <br />
     * @return maxAppTerm 최대신청기간
     */
    public String getMaxAppTerm() {
        return maxAppTerm;
    }
    /**
     * 신청기간안내문구 반환한다. <br />
     * @return appTermEx 신청기간안내문구
     */
    public String getAppTermEx() {
        return appTermEx;
    }
    /**
     * 증빙서류첨부 반환한다. <br />
     * @return addProofYn 증빙서류첨부
     */
    public String getAddProofYn() {
        return addProofYn;
    }
    /**
     * 증빙서류첨부사용 개월 반환한다. <br />
     * @return addProofMonth 증빙서류첨부사용 개월
     */
    public String getAddProofMonth() {
        return addProofMonth;
    }
    /**
     * 증빙서류첨부사용 일 반환한다. <br />
     * @return addProofDay 증빙서류첨부사용 일
     */
    public String getAddProofDay() {
        return addProofDay;
    }
    /**
     * 상단안내문구 반환한다. <br />
     * @return topTerms 상단안내문구
     */
    public String getTopTerms() {
        return topTerms;
    }
    /**
     * 결재안내문구 반환한다. <br />
     * @return permitTerms 결재안내문구
     */
    public String getPermitTerms() {
        return permitTerms;
    }
    /**
     * 하단안내문구 반환한다. <br />
     * @return bottomTerms 하단안내문구
     */
    public String getBottomTerms() {
        return bottomTerms;
    }
    /**
     * 등록일 반환한다. <br />
     * @return regDm 등록일
     */
    public String getRegDm() {
        return regDm;
    }
    /**
     * 수정일 반환한다. <br />
     * @return updDm 수정일
     */
    public String getUpdDm() {
        return updDm;
    }
    /**
     * 수정자 반환한다. <br />
     * @return updUser 수정자
     */
    public String getUpdUser() {
        return updUser;
    }
    /**
     * 예외신청문구ID 예외신청문구ID. <br />
     * @param exctAppId 예외신청문구ID
     */
    public void setExctAppId(String exctAppId) {
        this.exctAppId = exctAppId;
    }
    /**
     * 신청사유기본문구 설정한다. <br />
     * @param exctAppNm 신청사유기본문구
     */
    public void setExctAppNm(String exctAppNm) {
        this.exctAppNm = exctAppNm;
    }
    /**
     * 예외신청문구명 설정한다. <br />
     * @param exctBasicTerms 예외신청문구명
     */
    public void setExctBasicTerms(String exctBasicTerms) {
        this.exctBasicTerms = exctBasicTerms;
    }
    /**
     * 최대신청기간 설정한다. <br />
     * @param maxAppTerm 최대신청기간
     */
    public void setMaxAppTerm(String maxAppTerm) {
        this.maxAppTerm = maxAppTerm;
    }
    /**
     * 신청기간안내문구 설정한다. <br />
     * @param appTermEx 신청기간안내문구
     */
    public void setAppTermEx(String appTermEx) {
        this.appTermEx = appTermEx;
    }
    /**
     * 증빙서류첨부 설정한다. <br />
     * @param addProofYn 증빙서류첨부
     */
    public void setAddProofYn(String addProofYn) {
        this.addProofYn = addProofYn;
    }
    /**
     * 증빙서류첨부사용 개월 설정한다. <br />
     * @param addProofMonth 증빙서류첨부사용 개월
     */
    public void setAddProofMonth(String addProofMonth) {
        this.addProofMonth = addProofMonth;
    }
    /**
     * 증빙서류첨부사용 일 설정한다. <br />
     * @param addProofDay 증빙서류첨부사용 일
     */
    public void setAddProofDay(String addProofDay) {
        this.addProofDay = addProofDay;
    }
    /**
     * 상단안내문구 설정한다. <br />
     * @param topTerms 상단안내문구
     */
    public void setTopTerms(String topTerms) {
        this.topTerms = topTerms;
    }
    /**
     * 결재안내문구 설정한다. <br />
     * @param permitTerms 결재안내문구
     */
    public void setPermitTerms(String permitTerms) {
        this.permitTerms = permitTerms;
    }
    /**
     * 하단안내문구 설정한다. <br />
     * @param bottomTerms 하단안내문구
     */
    public void setBottomTerms(String bottomTerms) {
        this.bottomTerms = bottomTerms;
    }
    /**
     * 등록일 설정한다. <br />
     * @param regDm 등록일
     */
    public void setRegDm(String regDm) {
        this.regDm = regDm;
    }
    /**
     * 수정일 설정한다. <br />
     * @param updDm 수정일
     */
    public void setUpdDm(String updDm) {
        this.updDm = updDm;
    }
    /**
     * 수정자 설정한다. <br />
     * @param updUser 수정자
     */
    public void setUpdUser(String updUser) {
        this.updUser = updUser;
    }







}
