package tt.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import tt.base.dao.TtBaseDao;
import tt.bean.VoApprDetail;
import tt.bean.VoExct;
import tt.com.CoTtObjParams;


/**
 * <pre>
 * tt.dao
 *    |_ DaoLink.java
 *
 * DESC :
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author ks-lee
 * @Date 2013. 3. 5. 오후 6:09:59
 * @history :
 *  -----------------------------------------------------------------------
 *  변경일             작성자                     변경내용
 *  ----------- ------------------- ---------------------------------------
 *  2013. 3. 5.     ks-lee              최초 작성
 *  -----------------------------------------------------------------------
 *
 */
@Repository("daoExctApi")
public class DaoExctApi extends TtBaseDao {
    
    /**
     * @param ttObjParamsSql 파마리터
     * @return 용어관리 리스트
     */
    
    /*@SuppressWarnings("unchecked")
    public List<VoApprDetail> selectExctApi(CoTtObjParams ttObjParamsSql) {
        return list("DaoExctApi.selectExctApi." + dbType, ttObjParamsSql);
    }*/

    @SuppressWarnings("unchecked")
    public List<VoApprDetail> selectExctApi(VoApprDetail voApprDetail) {
        return list("DaoExctApi.selectExctApi." + dbType, voApprDetail);
    }
    
}
