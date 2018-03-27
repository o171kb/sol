package tt.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;


import tt.base.dao.TtBaseDao;
import tt.bean.VoPcBasic;
import tt.bean.VoWkPolicy;
import tt.com.CoTtObjParams;
import tt.com.bean.VoCoUser;


/**
 * <pre>
 * tt.dao
 *    |_ DaoExctSiteDraft.java
 *
 * DESC :
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author hk-kim
 * @Date 2013. 5. 20. 오후 8:02:50
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 5. 20.		hk-kim				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
@Repository("daoExctSiteDraft")
public class DaoExctSiteDraft extends TtWkPolicyBaseDao {


    /**
     * 사이트예외신청 <br/>
     * @param params 검색파라메타
     * @return 규칙리스트
     */
    @SuppressWarnings("unchecked")
    public List<VoWkPolicy> getRulesListDwrAll(CoTtObjParams params) {
        return list("DaoExctSiteDraft.dwr.wk.policy.rules.list." + dbType, params);
    }

    /**
     * 사이트예외신청 <br/>
     * @param params 검색 파라미터
     * @return 시간대리스트
     */
    @SuppressWarnings("unchecked")
    public List<VoWkPolicy> getTimeListDwrAll(CoTtObjParams params) {
        return list("DaoExctSiteDraft.dwr.wk.policy.time.list." + dbType, params);
    }

    /**
     * 부서GUID <br/>
     * @param ttObjParamsSql 상신정보
     * @return 부서GUID
     * @throws Exception 예외
     */
    public VoWkPolicy getDeptGUID(CoTtObjParams ttObjParamsSql) throws Exception {
        return (VoWkPolicy) selectByPk("DaoExctSiteDraft.get.deptGUID." + dbType, ttObjParamsSql);
    }

    public void insertApprExceptionSite(CoTtObjParams ttObjParamsSql) {
        insert("DaoExctSiteDraft.insert.appr.exception.site." + dbType , ttObjParamsSql);
        
    }

    public void updateApprExceptionSite(CoTtObjParams ttObjParamsSql) {
        update("DaoExctSiteDraft.update.appr.exception.site." + dbType , ttObjParamsSql);
        
    }
    @SuppressWarnings("unchecked")
    public List<VoPcBasic> getApprovalSitePcListAll(CoTtObjParams ttObjParamsSql) {
        return list("DaoExctSiteDraft.sitePc.basic.list.all." + dbType, ttObjParamsSql);
    }
    

    /**
     * @param voPcBasic 파라미터
     * @return dwr페이지
     */
    public int paginate_sitePc(VoPcBasic voPcBasic) {

        return  (Integer) getSqlMapClientTemplate().queryForObject("DaoExctSiteDraft.sitePc.basic.list.all.cnt." + dbType, voPcBasic);
    }



}
