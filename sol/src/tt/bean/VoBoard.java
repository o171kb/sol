package tt.bean;

import tt.com.bean.VoCoBase;


/**
 * <pre>
 * tt.bean
 *    |_ VoTboard.java
 *
 * DESC :
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author hk-kim
 * @Date 2013. 4. 1. 오후 3:20:40
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 4. 1.		hk-kim				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
public class VoBoard extends VoCoBase {

    private static final long serialVersionUID = 1315554918109341499L;

    /** 게시판번호 */
    private String boardNo;
    /** 게시판제목 */
    private String boardTitle;
    /** 게시판내용 */
    private String contents;
    /** 코드상세번호-코드관리:01 ~ 03 */
    private String boardTp;
    /** 파일명 */
    private String fileNm;
    /** 파일URL */
    private String fileUrl;
    /** 등록일 */
    private String regDm;
    /** 수정일 */
    private String updDm;
    /** 사용구분 */
    private String userYn;
    /** 수정자 */
    private String userId;
    /** delete id */
    private String delArr;
    /** message check */
    private String checkMessage;
    /** 파일 삭제여부 */
    private String fileDelYn;
    
    /** tab 구분 */
    private String tabDiv;
    
    private String tabDiv2;


    public String getFileDelYn() {
        return fileDelYn;
    }

    public void setFileDelYn(String fileDelYn) {
        this.fileDelYn = fileDelYn;
    }

    /**
     * message check 를 취득 <br />
     * @return checkMessage message check
     */
    public String getCheckMessage() {
        return checkMessage;
    }

    /**
     * message check 를 설정 <br />
     * @param checkMessage message check 설정
     */
    public void setCheckMessage(String checkMessage) {
        this.checkMessage = checkMessage;
    }

    /**
     * delete id 를 취득 <br />
     * @return delArr delete id
     */
    public String getDelArr() {
        return delArr;
    }

    /**
     * delete id 를 설정 <br />
     * @param delArr delete id 설정
     */
    public void setDelArr(String delArr) {
        this.delArr = delArr;
    }

    /**
     * 게시판번호 를 취득 <br />
     * @return boardNo 게시판번호
     */
    public String getBoardNo() {
        return boardNo;
    }

    /**
     * 게시판번호 를 설정 <br />
     * @param boardNo 게시판번호 설정
     */
    public void setBoardNo(String boardNo) {
        this.boardNo = boardNo;
    }

    /**
     * 게시판제목 를 취득 <br />
     * @return boardTitle 게시판제목
     */
    public String getBoardTitle() {
        return boardTitle;
    }

    /**
     * 게시판제목 를 설정 <br />
     * @param boardTitle 게시판제목 설정
     */
    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }

    /**
     * 게시판내용 를 취득 <br />
     * @return contents 게시판내용
     */
    public String getContents() {
        return contents;
    }

    /**
     * 게시판내용 를 설정 <br />
     * @param contents 게시판내용 설정
     */
    public void setContents(String contents) {
        this.contents = contents;
    }

    /**
     * 코드상세번호 를 취득 <br />
     * @return boardTp 코드상세번호
     */
    public String getBoardTp() {
        return boardTp;
    }

    /**
     * 코드상세번호 를 설정 <br />
     * @param boardTp 코드상세번호 설정
     */
    public void setBoardTp(String boardTp) {
        this.boardTp = boardTp;
    }

    /**
     * 파일명 를 취득 <br />
     * @return fileNm 파일명
     */
    public String getFileNm() {
        return fileNm;
    }

    /**
     * 파일명 를 설정 <br />
     * @param fileNm 파일명 설정
     */
    public void setFileNm(String fileNm) {
        this.fileNm = fileNm;
    }

    /**
     * 파일URL 를 취득 <br />
     * @return fileUrl 파일URL
     */
    public String getFileUrl() {
        return fileUrl;
    }

    /**
     * 파일URL 를 설정 <br />
     * @param fileUrl 파일URL 설정
     */
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
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
     * 사용구분 를 취득 <br />
     * @return userYn 사용구분
     */
    public String getUserYn() {
        return userYn;
    }

    /**
     * 사용구분 를 설정 <br />
     * @param userYn 사용구분 설정
     */
    public void setUserYn(String userYn) {
        this.userYn = userYn;
    }

    /**
     * 수정자 를 취득 <br />
     * @return userId 수정자
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 수정자 를 설정 <br />
     * @param userId 수정자 설정
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTabDiv() {
        return tabDiv;
    }

    public void setTabDiv(String tabDiv) {
        this.tabDiv = tabDiv;
    }

    public String getTabDiv2() {
        return tabDiv2;
    }

    public void setTabDiv2(String tabDiv2) {
        this.tabDiv2 = tabDiv2;
    }

    

}
