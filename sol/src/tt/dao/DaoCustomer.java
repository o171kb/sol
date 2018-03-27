package tt.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import tt.base.dao.TtBaseDao;
import tt.com.CoTtObjParams;
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
@Repository("daoCustomer")
public class DaoCustomer extends TtBaseDao {

    /**
     * 고객센터 공지사항<br/>
     *
     * @param ttObjParamsSql 공지사항 정보
     * @return 공지사항
     */
    @SuppressWarnings("unchecked")
    public List<VoBoard> getCustomerList(CoTtObjParams ttObjParamsSql) {
        return list("DaoCustomer.page.list." + dbType, ttObjParamsSql);
    }

    /**
     * 고객센터 총 갯수 취득<br/>
     *
     * @param ttObjParamsSql 검색 파라미터
     * @return 토탈카운트
     */
    public int getCustomerListTotCnt(CoTtObjParams ttObjParamsSql) {
        return (Integer) getSqlMapClientTemplate().queryForObject("DaoCustomer.page.cnt." + dbType, ttObjParamsSql);
    }

    /**
     * 고객센터 공지사항 상세<br/>
     *
     * @param ttObjParamsSql 검색 파라미터
     * @return 상세화면
     */
    public VoBoard getCustomerDtl(CoTtObjParams ttObjParamsSql) {
        return (VoBoard) selectByPk("DaoCustomer.page.view." + dbType, ttObjParamsSql);
    }

}
