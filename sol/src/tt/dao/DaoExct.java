package tt.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import tt.base.dao.TtBaseDao;
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
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 3. 5.		ks-lee				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
@Repository("daoExct")
public class DaoExct extends TtBaseDao {

//    /**
//     * 관련링크 목록 유무를 조회한다.
//     *
//     * @param searchMap - 조회할 정보가 담긴 Map
//     * @return 글 총 갯수
//     * @exception
//     */
//    public int selectLinkCntByIdAndSeq(VoLink vo) {
//        return (Integer) getSqlMapClientTemplate().queryForObject("adLinkDAO.selectLinkCntByIdAndSeq", vo);
//    }
//
//    /**
//     * 관련링크 등록.
//     *
//     * @param vo - 조회할 정보가 담긴 CoContentVO
//     * @return 수정 페이지
//     * @exception Exception
//     */
//    public void insertLink(VoLink vo) throws Exception {
//        insert("adLinkDAO.insertLink", vo);
//    }
//
//    /**
//     * 관련링크 수정.
//     *
//     * @param vo - 조회할 정보가 담긴 CoContentVO
//     * @return 수정 페이지
//     * @exception Exception
//     */
//    public void updateLink(VoLink vo) throws Exception {
//        update("adLinkDAO.updateLink", vo);
//    }
//
//    /**
//     * 관련링크 삭제.
//     *
//     * @param vo - 조회할 정보가 담긴 CoContentVO
//     * @return 수정 페이지
//     * @exception Exception
//     */
//    public void deleteLink(VoLink vo) throws Exception {
//        update("adLinkDAO.deleteLink", vo);
//    }


    /**
     * @param vo 파마리터
     * @return 용어관리 리스트
     */
    public List<VoExct> selectTermManageList(VoExct vo) {
        return list("DaoExct.getTermManageList." + dbType, vo);
    }

    /**
     * @param vo 파마리터
     * @return 예외처리 사용 용어 리스트
     */
    public List<VoExct> selectUseTermManageList(VoExct vo) {
        return list("DaoExct.getUseTermManageList." + dbType, vo);
    }

    /**
     * @param ttObjParams 파라미터
     * @return 용어관리
     */
    public VoExct getExctStringDtl(CoTtObjParams ttObjParams) {
        return (VoExct) getSqlMapClientTemplate().queryForObject("DaoExct.getExctStringDtl." + dbType, ttObjParams);
    }

    /**
     * @param ttObjParams 파라미터
     * @throws Exception 예외용어문구 수정
     */
    public void modifyExctString(CoTtObjParams ttObjParams) throws Exception {
        update("DaoExct.modifyExctString." + dbType , ttObjParams);
    }

    /**
     * 예외처리 액션 <br />
     * @param ttObjParamsSql 삭제정보
     */
    public void updateExctUseYn(CoTtObjParams ttObjParamsSql) {
        update("DaoExct.updateExctUseYn." + dbType, ttObjParamsSql);
    }

}
