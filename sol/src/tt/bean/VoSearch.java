package tt.bean;

import tt.com.bean.VoCoPagerEntity;

/**
 * <pre>
 * tt.bean
 *    |_ VoSearch.java
 *
 * DESC : 검색조건을 저장하는 VO 클래스 <br />
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author ks-lee
 * @Date 2013. 3. 19. 오후 3:53:53
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 3. 19.		ks-lee				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
public class VoSearch extends VoCoPagerEntity {

    /** 시리얼ID */
    private static final long serialVersionUID = 5213483797097095708L;

    /** 검색조건 */
    private String searchCondition = "";

    /** 검색부서코드 */
    private String searchDeptCd = "";

    /** 검색부서명 */
    private String searchDeptNm = "";

    /** 검색Keyword */
    private String searchKeyword = "";

    /** 검색사용여부 */
    private String searchUseYn = "";

    /** 검색그룹ID */
    private String searchGrpId = "";

    /** 검색그룹명 */
    private String searchGrpNm = "";

    /** 검색 결재자명 */
    private String searchApprUserNm = "";

    /** 검색구분 */
    private String searchCd = "";

    /** 검색 사용자그룹 번호 */
    private String searchUserGrpNo = "";

    /** 검색 제목 */
    private String searchTitle;

    /** 검색 등록자 */
    private String searchUserId;

    /** 검색 등록일 start */
    private String searchStartDm;

    /** 검색 등록일 end */
    private String searchEndDm;

    /** 검색 구분 */
    private String searchBoardTp;

    /** 검색 결재 상태 */
    private String searchStatus;

    /** 검색 예외 구분 */
    private String searchGubun;

    /** 검색 대상 사용자 */
    private String searchUserNm;

    /** 검색 최근 xx개월 */
    private int searchDate;


    public int getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(int searchDate) {
        this.searchDate = searchDate;
    }

    /**
     * 구분 를 취득 <br />
     * @return searchBoardTp 구분
     */
    public String getSearchBoardTp() {
        return searchBoardTp;
    }

    /**
     * 구분 를 설정 <br />
     * @param searchBoardTp 구분 설정
     */
    public void setSearchBoardTp(String searchBoardTp) {
        this.searchBoardTp = searchBoardTp;
    }

    /**
     * 검색 제목 를 취득 <br />
     * @return searchTitle 검색 제목
     */
    public String getSearchTitle() {
        return searchTitle;
    }

    /**
     * 검색 제목 를 설정 <br />
     * @param searchTitle 검색 제목 설정
     */
    public void setSearchTitle(String searchTitle) {
        this.searchTitle = searchTitle;
    }

    /**
     * 검색 등록자 를 취득 <br />
     * @return searchUserId 검색 등록자
     */
    public String getSearchUserId() {
        return searchUserId;
    }

    /**
     * 검색 등록자 를 설정 <br />
     * @param searchUserId 검색 등록자 설정
     */
    public void setSearchUserId(String searchUserId) {
        this.searchUserId = searchUserId;
    }

    /**
     * 검색 등록일 시작일 를 취득 <br />
     * @return searchStartDm 검색 등록일 시작일
     */
    public String getSearchStartDm() {
        return searchStartDm;
    }

    /**
     * 검색 등록일 시작일 를 설정 <br />
     * @param searchStartDm 검색 등록일 시작일 설정
     */
    public void setSearchStartDm(String searchStartDm) {
        this.searchStartDm = searchStartDm;
    }

    /**
     * 검색 등록일 종료일 를 취득 <br />
     * @return searchEndDm 검색 등록일 종료일
     */
    public String getSearchEndDm() {
        return searchEndDm;
    }

    /**
     * 검색 등록일 종료일 를 설정 <br />
     * @param searchEndDm 검색 등록일 종료일 설정
     */
    public void setSearchEndDm(String searchEndDm) {
        this.searchEndDm = searchEndDm;
    }

    /**
     * 검색조건 를 취득 <br />
     * @return searchCondition 검색조건
     */
    public String getSearchCondition() {
        return searchCondition;
    }

    /**
     * 검색부서코드 를 취득 <br />
     * @return searchDeptCd 검색부서코드
     */
    public String getSearchDeptCd() {
        return searchDeptCd;
    }

    /**
     * 검색부서명 를 취득 <br />
     * @return searchDeptNm 검색부서명
     */
    public String getSearchDeptNm() {
        return searchDeptNm;
    }

    /**
     * 검색Keyword 를 취득 <br />
     * @return searchKeyword 검색Keyword
     */
    public String getSearchKeyword() {
        return searchKeyword;
    }

    /**
     * 검색사용여부 를 취득 <br />
     * @return searchUseYn 검색사용여부
     */
    public String getSearchUseYn() {
        return searchUseYn;
    }

    /**
     * 검색조건 를 설정 <br />
     * @param searchCondition 검색조건 설정
     */
    public void setSearchCondition(String searchCondition) {
        this.searchCondition = searchCondition;
    }

    /**
     * 검색부서코드 를 설정 <br />
     * @param searchDeptCd 검색부서코드 설정
     */
    public void setSearchDeptCd(String searchDeptCd) {
        this.searchDeptCd = searchDeptCd;
    }

    /**
     * 검색부서명 를 설정 <br />
     * @param searchDeptNm 검색부서명 설정
     */
    public void setSearchDeptNm(String searchDeptNm) {
        this.searchDeptNm = searchDeptNm;
    }

    /**
     * 검색Keyword 를 설정 <br />
     * @param searchKeyword 검색Keyword 설정
     */
    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    /**
     * 검색사용여부 를 설정 <br />
     * @param searchUseYn 검색사용여부 설정
     */
    public void setSearchUseYn(String searchUseYn) {
        this.searchUseYn = searchUseYn;
    }

    /**
     * 검색그룹ID 를 취득 <br />
     * @return searchGrpId 검색그룹ID
     */
    public String getSearchGrpId() {
        return searchGrpId;
    }

    /**
     * 검색그룹ID 를 설정 <br />
     * @param searchGrpId 검색그룹ID 설정
     */
    public void setSearchGrpId(String searchGrpId) {
        this.searchGrpId = searchGrpId;
    }

    /**
     * 검색그룹명 를 취득 <br />
     * @return searchGrpNm 검색그룹명
     */
    public String getSearchGrpNm() {
        return searchGrpNm;
    }

    /**
     * 검색그룹명 를 설정 <br />
     * @param searchGrpNm 검색그룹명 설정
     */
    public void setSearchGrpNm(String searchGrpNm) {
        this.searchGrpNm = searchGrpNm;
    }

    /**
     * 검색 결재자명 반환한다. <br />
     * @return appUserNm 검색 결재자명
     */
    public String getSearchApprUserNm() {
        return searchApprUserNm;
    }

    /**
     * 검색 결재자명 설정한다. <br />
     * @param searchApprUserNm 검색 결재자명
     */
    public void setSearchApprUserNm(String searchApprUserNm) {
        this.searchApprUserNm = searchApprUserNm;
    }

    /**
     * 검색구분 를 취득 <br />
     * @return searchCd 검색구분
     */
    public String getSearchCd() {
        return searchCd;
    }

    /**
     * 검색구분 를 설정 <br />
     * @param searchCd 검색구분 설정
     */
    public void setSearchCd(String searchCd) {
        this.searchCd = searchCd;
    }

    /**
     * 검색 사용자그룹 번호 를 취득 <br />
     * @return searchUserGrpNo 검색 사용자그룹 번호
     */
    public String getSearchUserGrpNo() {
        return searchUserGrpNo;
    }

    /**
     * 검색 사용자그룹 번호 를 설정 <br />
     * @param searchUserGrpNo 검색 사용자그룹 번호 설정
     */
    public void setSearchUserGrpNo(String searchUserGrpNo) {
        this.searchUserGrpNo = searchUserGrpNo;
    }

    /**
     * 검색 결재 상태 반환한다. <br />
     * @return searchStatus 검색 결재 상태
     */
    public String getSearchStatus() {
        return searchStatus;
    }

    /**
     * 검색 결재 상태 설정한다. <br />
     * @param searchStatus 검색 결재 상태
     */
    public void setSearchStatus(String searchStatus) {
        this.searchStatus = searchStatus;
    }

    /**
     * 검색 예외 구분 반환한다. <br />
     * @return searchGubun 검색 예외 구분
     */
    public String getSearchGubun() {
        return searchGubun;
    }

    /**
     * 검색 예외 구분 설정한다. <br />
     * @param searchGubun 검색 예외 구분
     */
    public void setSearchGubun(String searchGubun) {
        this.searchGubun = searchGubun;
    }

    /**
     * 검색 대상 사용자 반환한다. <br />
     * @return searchUserNm 검색 대상 사용자
     */
    public String getSearchUserNm() {
        return searchUserNm;
    }

    /**
     * 검색 대상 사용자 설정한다. <br />
     * @param searchUserNm 검색 대상 사용자
     */
    public void setSearchUserNm(String searchUserNm) {
        this.searchUserNm = searchUserNm;
    }

}
