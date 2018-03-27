package tt.bean;


/**
 * DESC :
 *
 * @Company think-tree.inc
 * @author sj-hwang
 * @Date 2013. 4. 1. 오후 3:26:18
 */
public class VoExctObjPc extends VoApprInfo {

    private static final long serialVersionUID = 1L;

    /** IP주소 */
    private String ipAddr;
    /** 등록일 */
    private String regDm;
    /** 사용자 */
    private String userNm;
    /** 사용자연락처 */
    private String userMobile;
    /** 사용자ID */
    private String userId;
    /** 시리얼 */
    private String serial;
    /** 예외신청ID */
    private String apprId;


    /**
     * 사용자연락처 를 취득 <br />
     * @return userMobile 사용자연락처
     */
    public String getUserMobile() {
        return userMobile;
    }
    /**
     * 사용자연락처 를 설정 <br />
     * @param userMobile 사용자연락처 설정
     */
    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }
    /**
     * IP주소 를 취득 <br />
     * @return ipAddr IP주소
     */
    public String getIpAddr() {
        return ipAddr;
    }
    /**
     * IP주소 를 설정 <br />
     * @param ipAddr IP주소 설정
     */
    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
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
     * 사용자 를 취득 <br />
     * @return userNm 사용자
     */
    public String getUserNm() {
        return userNm;
    }
    /**
     * 사용자 를 설정 <br />
     * @param userNm 사용자 설정
     */
    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }
    /**
     * 사용자ID 를 취득 <br />
     * @return userId 사용자ID
     */
    public String getUserId() {
        return userId;
    }
    /**
     * 사용자ID 를 설정 <br />
     * @param userId 사용자ID 설정
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**
     * 시리얼 를 취득 <br />
     * @return serial 시리얼
     */
    public String getSerial() {
        return serial;
    }
    /**
     * 시리얼 를 설정 <br />
     * @param serial 시리얼 설정
     */
    public void setSerial(String serial) {
        this.serial = serial;
    }
    /**
     * 예외신청ID 를 취득 <br />
     * @return apprId 예외신청ID
     */
    public String getApprId() {
        return apprId;
    }
    /**
     * 예외신청ID 를 설정 <br />
     * @param apprId 예외신청ID 설정
     */
    public void setApprId(String apprId) {
        this.apprId = apprId;
    }



}
