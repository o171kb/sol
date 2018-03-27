package tt.module.admin;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tt.com.CoTtObjParams;
import tt.dao.DaoIncops5;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

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
@Service("svTestIncops5DbService")
public class SvTestIncops5DbService extends AbstractServiceImpl  {

    /** 관리자관련 DAO 클래스 */
    @Resource(name = "daoIncops5")
    protected DaoIncops5 daoIncops5;


    public List<CoTtObjParams> getDeptList() {
        return daoIncops5.getDeptList();
    }
}