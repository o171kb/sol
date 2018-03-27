package tt.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import tt.base.dao.TtBaseDao;
import tt.bean.VoBoard;
import tt.com.CoTtObjParams;

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
@Repository("daoMain")
public class DaoMain extends TtBaseDao {

    /**
     * 게시관리 <br/>
     *
     * @param ttObjParamsSql 검색 파라미터
     * @return 리스트
     */
    @SuppressWarnings("unchecked")
    public List<VoBoard> getBoardList(CoTtObjParams ttObjParamsSql) {
        return list("DaoMain.page.list." + dbType, ttObjParamsSql);
    }

    /**
     * 게시관리 총 갯수 취득<br/>
     *
     * @param ttObjParamsSql 검색 파라미터
     * @return 토탈카운트
     */
    public int getBoardListTotCnt(CoTtObjParams ttObjParamsSql) {
        return (Integer) getSqlMapClientTemplate().queryForObject("DaoMain.page.cnt." + dbType, ttObjParamsSql);
    }

}
