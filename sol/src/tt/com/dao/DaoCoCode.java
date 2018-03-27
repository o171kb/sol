package tt.com.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import tt.base.dao.TtBaseDao;
import tt.com.bean.VoCoCode;
import tt.com.bean.VoCoCodeSp;

/**
 * DESC : 코드 테이블 관련 DAO 클래스 <br />
 *
 * @Company think-tree.inc
 * @author ks-lee
 * @Date 2013. 2. 15. 오후 12:54:27
 */
@Repository("daoCoCode")
@SuppressWarnings("unchecked")
public class DaoCoCode extends TtBaseDao {

    /**
     * 코드 정보를 취득한다 <br />
     *
     * @return 검색결과
     */
    public List<VoCoCodeSp> selectCodeInfoAll() {
        return list("DaoCoCode.code.list." + dbType, "");
    }

    /**
     * 소분류 코드 정보를 취득한다 <br />
     *
     * @param vo - 등록할 정보가 담긴 CoCodeVO
     * @return 등록 결과
     */
    public List<VoCoCodeSp> selectMedCode(VoCoCode vo) {
        return list("DaoCoCode.code.med.list." + dbType, vo);
    }

}
