package tt.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import tt.base.dao.TtBaseDao;
import tt.com.CoTtObjParams;
import tt.com.utils.UtCoStringUtils;
import tt.bean.VoBoard;

/**
 * <pre>
 * tt.dao
 *    |_ DaoCtNotice.java
 *
 * DESC :
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author hk-kim
 * @Date 2013. 4. 1. 오후 8:39:41
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 4. 1.		hk-kim				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
@Repository("daoBoardSearch")
public class DaoBoardSearch extends TtBaseDao {

    /**
     * 게시관리 <br/>
     *
     * @param ttObjParamsSql 검색 파라미터
     * @return 리스트
     */
    @SuppressWarnings("unchecked")
    public List<VoBoard> getBoardList(CoTtObjParams ttObjParamsSql) {
        return list("DaoBoardSearch.page.list." + dbType, ttObjParamsSql);
    }

    /**
     * 게시관리 총 갯수 취득<br/>
     *
     * @param ttObjParamsSql 검색 파라미터
     * @return 토탈카운트
     */
    public int getBoardListTotCnt(CoTtObjParams ttObjParamsSql) {
        return (Integer) getSqlMapClientTemplate().queryForObject("DaoBoardSearch.page.cnt." + dbType, ttObjParamsSql);
    }

    /**
     * 게시관리 수정화면<br/>
     *
     * @param ttObjParamsSql 검색 파라미터
     * @return 수정화면
     */
    public VoBoard getBoardDtl(CoTtObjParams ttObjParamsSql) {
        return (VoBoard) selectByPk("DaoBoardSearch.page.view." + dbType, ttObjParamsSql);
    }

    /**
     * 게시관리 수정<br/>
     * @param ttObjParamsSql 수정 파라미터
     * @return result int
     */
    public Integer updateBoard(CoTtObjParams ttObjParamsSql) {
        return (Integer) update("DaoBoardSearch.page.update." + dbType, ttObjParamsSql);
    }

    /**
     * 게시관리 삭제<br/>
     * @param ttObjParamsSql 삭제 파라미터
     */
    public void deleteBoard(CoTtObjParams ttObjParamsSql) {
        update("DaoBoardSerch.board.delete." + dbType, ttObjParamsSql);
    }

    /**
     * 게시관리 등록<br/>
     * @param ttObjParamsSql 등록 파라미터
     */
    public void insertBoard(CoTtObjParams ttObjParamsSql) {
        insert("DaoBoardSearch.page.insert." + dbType, ttObjParamsSql);
    }

    public Integer getBoardNo() {
        return (Integer) getSqlMapClientTemplate().queryForObject("DaoBoardSearch.board.max.boardno." + dbType);
    }
}
