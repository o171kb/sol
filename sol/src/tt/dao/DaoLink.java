package tt.dao;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;



/*
 * TODO 파일명작성 샘플로 남겨두었습니다. 클래스가 만들어지면 이파일을 지워주세요
 * */

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
@Repository("daoLink")
public class DaoLink extends EgovAbstractDAO {

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

}
