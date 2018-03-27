package tt.module.admin.customer;


import java.util.List;

import org.springframework.stereotype.Service;

import tt.base.module.TtBaseService;
import tt.bean.VoBoard;
import tt.com.CoTtObjParams;
import tt.com.utils.UtCoStringUtils;

/**
 * <pre>
 * tt.module.admin.customer
 *    |_ SvCtNoticeService.java
 *
 * DESC :
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author Administrator
 * @Date 2013. 4. 1. 오후 6:27:17
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 4. 1.		Administrator				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
@Service("svCustomerService")
public class SvCustomerService extends TtBaseService  {


    /**
     * 고객센터 공지사항<br/>
     * @param ttObjParams 검색 파라미터
     * @return 검색리스트
     */
    public List<VoBoard> getCustomerList(CoTtObjParams ttObjParams) {
        VoBoard voBoard = (VoBoard) ttObjParams.get("voBoard");

        //페이징파라미터설정
        CoTtObjParams ttObjParamsSql = getParamsObjOfPageInfoSetted(voBoard.getFirstIndex(), voBoard.getRecordCountPerPage());
        //검색어 설정
        ttObjParamsSql.put("searchStatus", UtCoStringUtils.trim(voBoard.getSearchStatus()));
        ttObjParamsSql.put("searchTitle", UtCoStringUtils.trim(voBoard.getSearchTitle()));
        ttObjParamsSql.put("searchUserId", UtCoStringUtils.trim(voBoard.getSearchUserId()));
        ttObjParamsSql.put("searchStartDm", UtCoStringUtils.trim(voBoard.getSearchStartDm()));
        ttObjParamsSql.put("searchEndDm", UtCoStringUtils.trim(voBoard.getSearchEndDm()));
        //게시판 타입
        ttObjParamsSql.put("boardTp", UtCoStringUtils.trim(voBoard.getBoardTp()));

        return daoCustomer.getCustomerList(ttObjParamsSql);
    }

    /**
     * 고객센터 총 갯수 취득<br/>
     *
     * @param ttObjParams 검색 파라미터
     * @return 토탈카운트
     */
    public int getCustomerListTotCnt(CoTtObjParams ttObjParams) {
        VoBoard voBoard = (VoBoard) ttObjParams.get("voBoard");

        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        ttObjParamsSql.put("searchStatus", UtCoStringUtils.trim(voBoard.getSearchStatus()));
        ttObjParamsSql.put("searchTitle", UtCoStringUtils.trim(voBoard.getSearchTitle()));
        ttObjParamsSql.put("searchUserId", UtCoStringUtils.trim(voBoard.getSearchUserId()));
        ttObjParamsSql.put("searchStartDm", UtCoStringUtils.trim(voBoard.getSearchStartDm()));
        ttObjParamsSql.put("searchEndDm", UtCoStringUtils.trim(voBoard.getSearchEndDm()));

        //게시판 타입
        ttObjParamsSql.put("boardTp", UtCoStringUtils.trim(voBoard.getBoardTp()));

        return daoCustomer.getCustomerListTotCnt(ttObjParamsSql);
    }

    /**
     * 고객센터 공지사항 상세<br/>
     *
     * @param ttObjParams 검색 파라미터
     * @return 상세화면
     */
    public VoBoard getCustomerDtl(CoTtObjParams ttObjParams) {

        VoBoard voBoard = (VoBoard) ttObjParams.get("voBoard");
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        ttObjParamsSql.put("boardNo", Integer.parseInt(voBoard.getBoardNo()));

        //게시판 타입
        ttObjParamsSql.put("boardTp", UtCoStringUtils.trim(voBoard.getBoardTp()));

        return daoCustomer.getCustomerDtl(ttObjParamsSql);

    }
}