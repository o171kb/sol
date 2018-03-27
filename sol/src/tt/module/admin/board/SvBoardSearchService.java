package tt.module.admin.board;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tt.base.module.TtBaseService;
import tt.bean.VoBoard;
import tt.com.CoTtObjParams;
import tt.com.module.file.SvCoFileService;
import tt.com.module.login.SvCoLoginService;
import tt.com.utils.UtCoDateUtils;
import tt.com.utils.UtCoStringUtils;
import tt.constant.CsConstDef;
import tt.module.CpDocument;

/**
 * <pre>
 * tt.module.admin.customer
 *    |_ SvCtNoticeService.java
 *
 * DESC :
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author hk-kim
 * @Date 2013. 4. 1. 오후 6:27:17
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 4. 1.		hk-kim				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
@Service("svBoardSearchService")
public class SvBoardSearchService extends TtBaseService  {


    /**
     * 게시관리 리스트<br/>
     * @param ttObjParams 검색 파라미터
     * @return 검색리스트
     */
    public List<VoBoard> getBoardList(CoTtObjParams ttObjParams) {
        VoBoard voBoard = (VoBoard) ttObjParams.get("voBoard");

//        //페이징파라미터설정
//        CoTtObjParams ttObjParamsSql = getParamsObjOfPageInfoSetted(voBoard.getFirstIndex(), voBoard.getRecordCountPerPage());
//        //검색어 설정
//        ttObjParamsSql.put("searchTitle", UtCoStringUtils.trim(voBoard.getSearchTitle()));
//        ttObjParamsSql.put("searchUserId", UtCoStringUtils.trim(voBoard.getSearchUserId()));
//        ttObjParamsSql.put("searchStartDm", UtCoStringUtils.trim(voBoard.getSearchStartDm()));
//        ttObjParamsSql.put("searchEndDm", UtCoStringUtils.trim(voBoard.getSearchEndDm()));
//        ttObjParamsSql.put("searchBoardTp", UtCoStringUtils.trim(voBoard.getSearchBoardTp()));
//        return daoBoardSearch.getBoardList(ttObjParamsSql);
        return daoBoardSearch.getBoardList(ttObjParams);

    }

    /**
     * 게시관리 리스트 총 갯수 취득<br/>
     *
     * @param ttObjParams 검색 파라미터
     * @return 토탈카운트
     */
    public int getBoardListTotCnt(CoTtObjParams ttObjParams) {
        VoBoard voBoard = (VoBoard) ttObjParams.get("voBoard");

//        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
//        ttObjParamsSql.put("searchTitle", UtCoStringUtils.trim(voBoard.getSearchTitle()));
//        ttObjParamsSql.put("searchUserId", UtCoStringUtils.trim(voBoard.getSearchUserId()));
//        ttObjParamsSql.put("searchStartDm", UtCoStringUtils.trim(voBoard.getSearchStartDm()));
//        ttObjParamsSql.put("searchEndDm", UtCoStringUtils.trim(voBoard.getSearchEndDm()));
//        ttObjParamsSql.put("searchBoardTp", UtCoStringUtils.trim(voBoard.getSearchBoardTp()));
//
//        return daoBoardSearch.getBoardListTotCnt(ttObjParamsSql);
        return 0;
    }

    /**
     * 게시관리 수정화면<br/>
     *
     * @param ttObjParams 검색 파라미터
     * @return 수정화면
     */
    public VoBoard getBoardDtl(CoTtObjParams ttObjParams) {

        VoBoard voBoard = (VoBoard) ttObjParams.get("voBoard");
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        ttObjParamsSql.put("boardNo", Integer.parseInt(voBoard.getBoardNo()));

        //게시판 타입
        ttObjParamsSql.put("boardTp", UtCoStringUtils.trim(voBoard.getBoardTp()));

        return daoBoardSearch.getBoardDtl(ttObjParamsSql);

    }

    /**
     * 게시관리 수정<br/>
     * @param ttObjParams 검색 파라미터
     * @param request file 정보
     * @throws Exception 예외처리
     * @return result int
     */
    public Integer updateBoard(CoTtObjParams ttObjParams, HttpServletRequest request) throws Exception {

        VoBoard voBoard = (VoBoard) ttObjParams.get("voBoard");
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();

        /* file upload */
        Map tmpUploadFileInfoMap = svCoFileService.uploadFileToTempDir(request);

        if (tmpUploadFileInfoMap.containsKey("_attachFile")) {
            CpDocument attachFileDoc = (CpDocument) tmpUploadFileInfoMap.get("_attachFile");
            //업로드디렉토리에 이동
            ttObjParamsSql.put("fileNm", UtCoStringUtils.substringAfter(attachFileDoc.getFileName(), CsConstDef.STR_DASH));
            ttObjParamsSql.put("fileUrl", svCoFileService.uploadFileToRealDirFromTempDir(attachFileDoc));
        }

        /* file upload */

        String updDm = UtCoDateUtils.getCurrentDateAsString(UtCoDateUtils.DATE_HMS_PATTERN);

        ttObjParamsSql.put("fileDelYn", UtCoStringUtils.trim(voBoard.getFileDelYn()));
        ttObjParamsSql.put("boardNo", Integer.parseInt(voBoard.getBoardNo()));
        ttObjParamsSql.put("boardTitle", UtCoStringUtils.trim(voBoard.getBoardTitle()));
        ttObjParamsSql.put("contents", UtCoStringUtils.trim(voBoard.getContents()));
        ttObjParamsSql.put("updDm", updDm);
        ttObjParamsSql.put("userId", UtCoStringUtils.trim(voBoard.getUserId()));
        //게시판 타입
        ttObjParamsSql.put("boardTp", UtCoStringUtils.trim(voBoard.getBoardTp()));

        return (Integer) daoBoardSearch.updateBoard(ttObjParamsSql);

    }

    /**
     * 게시관리 게시글 삭제<br/>
     *
     * @param ttObjParams 삭제정보
     */
    public void deleteBoard(CoTtObjParams ttObjParams) {

        VoBoard voBoard = (VoBoard) ttObjParams.get("voBoard");
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();

        if (!UtCoStringUtils.isEmpty(voBoard.getDelArr())) {
            String[] chkId = voBoard.getDelArr().split(",");

            for (int i = 0; i < chkId.length; i++) {
                ttObjParamsSql.put("boardNo", chkId[i]);
                daoBoardSearch.deleteBoard(ttObjParamsSql);
            }
        }
    }

    /**
     * 게시관리 등록 <br />
     * @param ttObjParams 등록정보
     * @param request file 정보
     * @throws Exception 예외처리
     */
    public void insertBoard(CoTtObjParams ttObjParams, HttpServletRequest request) throws Exception {

        VoBoard voBoard = (VoBoard) ttObjParams.get("voBoard");
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();

        /* file upload */
        Map tmpUploadFileInfoMap = svCoFileService.uploadFileToTempDir(request);

        if (tmpUploadFileInfoMap.containsKey("_attachFile")) {
            CpDocument attachFileDoc = (CpDocument) tmpUploadFileInfoMap.get("_attachFile");
            //업로드디렉토리에 이동
            ttObjParamsSql.put("fileNm", UtCoStringUtils.substringAfter(attachFileDoc.getFileName(), CsConstDef.STR_DASH));
            ttObjParamsSql.put("fileUrl", svCoFileService.uploadFileToRealDirFromTempDir(attachFileDoc));
        }

        /* file upload */

        int maxBoardNo = daoBoardSearch.getBoardNo() + 1;
        String sysdate = UtCoDateUtils.getCurrentDateAsString(UtCoDateUtils.DATE_PATTERN_DASH);

        ttObjParamsSql.put("boardNo", maxBoardNo);
        ttObjParamsSql.put("boardTitle", UtCoStringUtils.trim(voBoard.getBoardTitle()));
        ttObjParamsSql.put("contents", UtCoStringUtils.trim(voBoard.getContents()));
        ttObjParamsSql.put("userId", UtCoStringUtils.trim(voBoard.getUserId()));
        ttObjParamsSql.put("regDm", sysdate);
        //게시판 타입
        ttObjParamsSql.put("boardTp", UtCoStringUtils.trim(voBoard.getBoardTp()));

        daoBoardSearch.insertBoard(ttObjParamsSql);

    }
}