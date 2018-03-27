package tt.com.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import tt.base.dao.TtBaseDao;
import tt.com.CoTtStrParams;
import tt.com.bean.VoCoMenu;
import tt.com.bean.VoCoUserGrpMenu;

/**
 * <pre>
 * tt.com.dao
 *    |_ DaoCoMenu.java
 *
 * DESC : 메뉴관련 DAO 클래스 <br />
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author ks-lee
 * @Date 2013. 2. 27. 오후 6:59:59
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 2. 27.		ks-lee				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
@Repository("daoCoMenu")
public class DaoCoMenu extends TtBaseDao {

    /**
     * 관리자별 모든메뉴리스트를 취득한다 <br />
     * @param ttStrParams 조회용 파라메터
     * @return 메뉴리스트
     */
    @SuppressWarnings("unchecked")
    public List<VoCoUserGrpMenu> getTotalMenuList(CoTtStrParams ttStrParams) {
        return list("DaoCoMenu.total.menu.list." + dbType, ttStrParams);
    }

    /**
     * 로그인관리자 메뉴리스트를 취득한다 <br />
     * @param ttStrParams 조회용 파라메터
     * @return 메뉴리스트
     */
    @SuppressWarnings("unchecked")
    public List<VoCoMenu> getUserMenuList(CoTtStrParams ttStrParams) {
        return list("DaoCoMenu.user.menu.list." + dbType, ttStrParams);
    }


}
