package tt.module.admin.exct;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tt.TTLog;
import tt.base.module.TtBaseService;
import tt.bean.VoExct;
import tt.com.CoTtObjParams;
import tt.com.bean.VoCoDept;
import tt.com.utils.UtCoStringUtils;
import tt.dao.DaoExct;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;


/**
 * <pre>
 * tt.com.module.dept
 *    |_ SvCoDeptService.java
 *
 * DESC : 부서 관리 서비스 클래스 <br />
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author ks-lee
 * @Date 2013. 3. 8. 오전 9:43:41
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 3. 8.		ks-lee				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
@Service("svExctService")
public class SvExctService extends TtBaseService  {

    /**
     * @param vo 파라미터
     * @return 용어관리 리스트
     * @throws Exception 예외
     */
    public List<VoExct> selectTermManageList(VoExct vo) throws Exception {
        return daoExct.selectTermManageList(vo);
    }

    /**
     * @param vo 파라미터
     * @return 예외처리 사용 용어 리스트
     * @throws Exception 예외
     */
    public List<VoExct> selectUseTermManageList(VoExct vo) throws Exception {
        return daoExct.selectUseTermManageList(vo);
    }

    /**
     * 용어관리 사용여부 업데이트 <br />
     * @param ttObjParams 업데이트 정보
     */
    @Transactional
    public void updateExctUseYn(CoTtObjParams ttObjParams) {

        VoExct voExct = (VoExct) ttObjParams.get("voExct");
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        if (!UtCoStringUtils.isEmpty(voExct.getArrChk())) {
            String[] useYn = voExct.getArrChk().split(",");
            String[] exctId = voExct.getExctId().split(",");

            for (int i = 0; i < useYn.length; i++) {
                ttObjParamsSql.put("useYn", useYn[i]);
                ttObjParamsSql.put("exctId", exctId[i]);
                daoExct.updateExctUseYn(ttObjParamsSql);
            }
        }
    }

    public VoExct getExctStringDtl(CoTtObjParams ttObjParams) throws Exception {

        VoExct voExct = (VoExct) ttObjParams.get("voExct");
        TTLog.debug(voExct.toString(), this.getClass());

        //파라미터설정
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        ttObjParamsSql.put("exctId", voExct.getExctId());

        return daoExct.getExctStringDtl(ttObjParamsSql);
    }


    /**
     * @param ttObjParams 파라미터
     * @throws Exception 수정액션
     */
    public void modifyExctString(CoTtObjParams ttObjParams) throws Exception {

        VoExct voExct = (VoExct) ttObjParams.get("voExct");
        TTLog.debug(voExct.toString(), this.getClass());

        //파라미터설정
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        ttObjParamsSql.put("exctId", voExct.getExctId());
        ttObjParamsSql.put("exctNm", voExct.getExctNm());
        ttObjParamsSql.put("exctDtl", voExct.getExctDtl());
        ttObjParamsSql.put("useYn", voExct.getUseYn());

        daoExct.modifyExctString(ttObjParamsSql);
    }

}