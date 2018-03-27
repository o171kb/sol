package tt.module.admin.main;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import tt.base.module.TtBaseService;
import tt.bean.VoBoard;
import tt.com.CoTtObjParams;
import tt.com.utils.UtCoStringUtils;

/**
 * <pre>
 * tt.module.admin.main
 *    |_ SvAdMainService.java
 *
 * DESC :
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author hk-kim
 * @Date 2013. 4. 12. 오후 1:53:34
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 4. 12.		hk-kim				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
@Service("svAdMainService")
public class SvAdMainService extends TtBaseService {

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
//        return daoMain.getBoardList(ttObjParamsSql);
//        return daoMain.getBoardList(ttObjParams);
        return new ArrayList<VoBoard>();
        
        
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
//        return daoMain.getBoardListTotCnt(ttObjParamsSql);
        return 0;
    }
}
