package tt.com.bean;

import org.apache.commons.lang.builder.ToStringBuilder;

import tt.bean.VoSearch;

/**
 * DESC : 공통  VO 클래스 <br />
 *
 * @Company think-tree.inc
 * @author ks-lee
 * @Date 2013. 1. 31. 오전 9:25:18
 */
public class VoCoBase extends VoSearch {

    /** 시리얼ID */
    private static final long serialVersionUID = 677728686743635141L;

    /** 등록일 칼럼명 */
    public static final String COL_REG_DM = "REG_DM";
    /** 수정일일 칼럼명 */
    public static final String COL_UPD_DM = "UPD_DM";
    /** 사용구분 칼럼명 */
    public static final String COL_USE_YN = "USE_YN";
    /** 갱신자 칼럼명 */
    public static final String COL_USER_ID = "USER_ID";

    /** 현재페이지 */
    private int pageIndex = 1;

    /** 페이지갯수 */
    private int pageUnit = 10;

    /** 페이지사이즈 */
    private int pageSize = 10;

    /** firstIndex */
    private int firstIndex = 1;

    /** lastIndex */
    private int lastIndex = 1;

    /** recordCountPerPage */
    private int recordCountPerPage = 10;

    /** 등록일       */
    private String regDm;
    /** 수정일       */
    private String updDm;
    /** 이용구분     */
    private String useYn;
    /** 관리자아이디 */
    private String userId;
    /** 관리자권한   */
    private String authority;

    /** 액션확인용메세지 */
    private String checkMessage = "";

    /** 액션확인용리스트사이즈 */
    private int listSize = 0;

    /**
     * 액션확인용리스트사이즈 를 취득 <br />
     * @return listSixe 액션확인용리스트사이즈
     */
    public int getListSize() {
        return listSize;
    }

    /**
     * 액션확인용리스트사이즈 를 설정 <br />
     * @param listSize 액션확인용리스트사이즈 설정
     */
    public void setListSize(int listSize) {
        this.listSize = listSize;
    }

    public void initPaging() {
        setCurrentPageNo(getPageIndex());
        setRecordCountPerPage(getPageUnit());
        setPageSize(getPageSize());

        setFirstIndex(getFirstRecordIndex());
        setLastIndex(getLastRecordIndex());
        setRecordCountPerPage(getRecordCountPerPage());
    }

    public int getFirstIndex() {
        return firstIndex;
    }

    public void setFirstIndex(int firstIndex) {
        this.firstIndex = firstIndex;
    }

    public int getLastIndex() {
        return lastIndex;
    }

    public void setLastIndex(int lastIndex) {
        this.lastIndex = lastIndex;
    }

    public int getRecordCountPerPage() {
        return recordCountPerPage;
    }

    public void setRecordCountPerPage(int recordCountPerPage) {
        this.recordCountPerPage = recordCountPerPage;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageUnit() {
        return pageUnit;
    }

    public void setPageUnit(int pageUnit) {
        this.pageUnit = pageUnit;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
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
     * 수정일 를 취득 <br />
     * @return updDm 수정일
     */
    public String getUpdDm() {
        return updDm;
    }
    /**
     * 수정일 를 설정 <br />
     * @param updDm 수정일 설정
     */
    public void setUpdDm(String updDm) {
        this.updDm = updDm;
    }
    /**
     * 이용구분 를 취득 <br />
     * @return useYn 이용구분
     */
    public String getUseYn() {
        return useYn;
    }
    /**
     * 이용구분 를 설정 <br />
     * @param useYn 이용구분 설정
     */
    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }
    /**
     * 관리자아이디 를 취득 <br />
     * @return userId 관리자아이디
     */
    public String getUserId() {
        return userId;
    }
    /**
     * 관리자아이디 를 설정 <br />
     * @param userId 관리자아이디 설정
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**
     * 관리자권한 를 취득 <br />
     * @return authority 관리자권한
     */
    public String getAuthority() {
        return authority;
    }
    /**
     * 관리자권한 를 설정 <br />
     * @param authority 관리자권한 설정
     */
    public void setAuthority(String authority) {
        this.authority = authority;
    }

    /**
     * 액션확인용메세지 를 취득 <br />
     * @return checkMessage 액션확인용메세지
     */
    public String getCheckMessage() {
        return checkMessage;
    }

    /**
     * 액션확인용메세지 를 설정 <br />
     * @param checkMessage 액션확인용메세지 설정
     */
    public void setCheckMessage(String checkMessage) {
        this.checkMessage = checkMessage;
    }

}
