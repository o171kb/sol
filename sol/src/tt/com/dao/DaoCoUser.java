package tt.com.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import tt.base.dao.TtBaseDao;
import tt.com.CoTtObjParams;
import tt.com.CoTtStrParams;
import tt.com.bean.VoCoLoginUserInfo;
import tt.com.bean.VoCoUser;
import tt.com.utils.UtCoStringUtils;

/**
 * <pre>
 * tt.com.dao
 *    |_ DaoCoUser.java
 *
 * DESC : 관리자관련 DAO 클래스 <br />
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author ks-lee
 * @Date 2013. 2. 27. 오후 7:01:40
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 2. 27.		ks-lee				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
/**
 * <pre>
 * tt.com.dao
 *    |_ DaoCoUser.java
 *
 * DESC :
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author Administrator
 * @Date 2013. 4. 11. 오후 5:00:47
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 4. 11.		Administrator				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
@Repository("daoCoUser")
public class DaoCoUser extends TtBaseDao {

    /**
     * 사용자정보취득 <br />
     *
     * @param ttStrParams 사용자검색정보
     * @return 사용자정보
     */
    public VoCoLoginUserInfo getUserLoginInfo(CoTtStrParams ttStrParams) {
        return (VoCoLoginUserInfo) selectByPk("DaoCoUser.user.login.info." + super.dbType, ttStrParams);
    }

    /**
     * 사용자검색정보취득 페이지 <br />
     *
     * @param ttObjParamsSql 사용자검색정보
     * @return 사용자정보
     */
    @SuppressWarnings("unchecked")
    public List<VoCoUser> getUserSearchListForPage(CoTtObjParams ttObjParamsSql) {
        return list("DaoCoUser.user.search.page.list." + dbType, ttObjParamsSql);
    }

    /**
     * 사용자검색정보 총갯수 취득 <br />
     *
     * @param ttObjParamsSql 사용자검색정보
     * @return 사용자정보
     */
    public int getUserSearchListTotCnt(CoTtObjParams ttObjParamsSql) {
        return (Integer) getSqlMapClientTemplate().queryForObject(
                "DaoCoUser.user.search.cnt." + dbType, ttObjParamsSql);
    }

    /**
     * 대행결제자 이름 가져오기 <br />
     *
     * @param ttObjParamsSql 검색 파라미터
     * @return 대행결제자 이름
     */
    public String getProxyName(CoTtObjParams ttObjParamsSql) {
        return (String) selectByPk("DaoCoUser.user.select.proxyNm." + dbType, ttObjParamsSql);
    }

    /**
     * 사용자 사번 중복을 조회한다.
     * @param ttObjParamsSql 검색정보
     * @return 체크 결과 -1:사용불가, 0:검색문자열"", 1:사용가능
     */
    public int checkUserId(CoTtObjParams ttObjParamsSql) {

        if (UtCoStringUtils.isEmpty(ttObjParamsSql.getString("userId"))) {
            return 0;
        }

        VoCoUser checkVO = (VoCoUser) selectByPk("DaoCoUser.user.search.user.id." + dbType, ttObjParamsSql);

        if (checkVO == null) {
            return 1;
        }
        return -1;
    }


    /**
     * 대행결재자용 사용자정보취득 페이지 <br />
     *
     * @param ttObjParamsSql 부서검색정보
     * @return 부서정보
     */
    @SuppressWarnings("unchecked")
    public List<VoCoUser> getSearchUserDeptListForPage(CoTtObjParams ttObjParamsSql) {
        return list("DaoCoUser.user.search.user.dept.page.list." + dbType, ttObjParamsSql);
    }

    /**
     * 대행결재자용 사용자정보 총갯수 취득 <br />
     *
     * @param ttObjParamsSql 부서검색정보
     * @return 부서정보
     */
    public int paginateUserDeptListTotCnt(CoTtObjParams ttObjParamsSql) {
        return (Integer) getSqlMapClientTemplate().queryForObject(
                "DaoCoUser.user.search.user.dept.cnt." + dbType, ttObjParamsSql);
    }

    /**
     * 사용자 등록 <br />
     * @param ttObjParamsSql 등록 파라미터
     */
    public void registUser(CoTtObjParams ttObjParamsSql) {
        insert("DaoCoUser.user.search.user.regist." + dbType, ttObjParamsSql);
    }

    /**
     * 사용자 상세 <br />
     * @param ttObjParamsSql 등록 파라미터
     * @return VoCoUser
     */
    public VoCoUser getUserDtl(CoTtObjParams ttObjParamsSql) {
        return (VoCoUser) selectByPk("DaoCoUser.user.search.page.detail." + dbType, ttObjParamsSql);
    }

    /**
     * 사용자 수정 <br />
     * @param ttObjParamsSql 수정 파라미터
     */
    public void modifyUser(CoTtObjParams ttObjParamsSql) {
        update("DaoCoUser.user.search.user.modify." + dbType, ttObjParamsSql);
    }

    /**
     * 사용자 삭제 <br />
     * @param ttObjParamsSql 삭제 정보
     */
    public void deleteUser(CoTtObjParams ttObjParamsSql) {
        update("DaoCoUser.user.delete." + dbType, ttObjParamsSql);
    }

}
