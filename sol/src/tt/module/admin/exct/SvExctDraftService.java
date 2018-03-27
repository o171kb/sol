package tt.module.admin.exct;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tt.TTLog;
import tt.base.module.TtBaseService;
import tt.bean.VoExct;
import tt.bean.VoExctDraft;
import tt.com.CoTtObjParams;
import tt.com.bean.VoCoDept;
import tt.com.bean.VoCoUser;
import tt.com.constant.CsCoConstDef;
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
@Service("svExctDraftService")
public class SvExctDraftService extends TtBaseService  {

    /**
     * @param vo 파라미터
     * @return 예외신청문구수정 리스트
     * @throws Exception 예외
     */
    public List<VoExctDraft> getDraftModifyList(VoExctDraft vo) throws Exception {
        return daoExctDraft.getDraftModifyList(vo);
    }

    /**
     * @param ttObjParams 파라미터
     * @return 예외신청문구수정 상세
     * @throws Exception 예외
     */
    public VoExctDraft getExctDraftDtl(CoTtObjParams ttObjParams) throws Exception {

        VoExctDraft voExctDraft = (VoExctDraft) ttObjParams.get("voExctDraft");
        TTLog.debug(voExctDraft.toString(), this.getClass());

        //파라미터설정
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        ttObjParamsSql.put("exctAppId", voExctDraft.getExctAppId());

        return daoExctDraft.getExctDraftDtl(ttObjParamsSql);
    }

    /**
     * 예외신청문구수정 액션
     * @param ttObjParams 파라메터
     * @throws Exception 예외
     */
    public void modifyExctDraft(CoTtObjParams ttObjParams) throws Exception {

        VoExctDraft voExctDraft = (VoExctDraft) ttObjParams.get("voExctDraft");
        TTLog.debug(voExctDraft.toString(), this.getClass());

        //파라미터설정
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        ttObjParamsSql.put("exctAppId", voExctDraft.getExctAppId());
        ttObjParamsSql.put("exctBasicTerms", voExctDraft.getExctBasicTerms());
        ttObjParamsSql.put("maxAppTerm", voExctDraft.getMaxAppTerm());
        ttObjParamsSql.put("appTermEx", voExctDraft.getAppTermEx());
        ttObjParamsSql.put("addProofYn", voExctDraft.getAddProofYn());
        ttObjParamsSql.put("addProofMonth", voExctDraft.getAddProofMonth());
        ttObjParamsSql.put("addProofDay", voExctDraft.getAddProofDay());
//        ttObjParamsSql.put("topTerms", UtCoStringUtils.htmlEscape(voExctDraft.getTopTerms()));
//        ttObjParamsSql.put("permitTerms", UtCoStringUtils.htmlEscape(voExctDraft.getPermitTerms()));
//        ttObjParamsSql.put("bottomTerms", UtCoStringUtils.htmlEscape(voExctDraft.getBottomTerms()));
        ttObjParamsSql.put("topTerms", voExctDraft.getTopTerms());
        ttObjParamsSql.put("permitTerms", voExctDraft.getPermitTerms());
        ttObjParamsSql.put("bottomTerms", voExctDraft.getBottomTerms());

        ttObjParamsSql.put("updDm", voExctDraft.getUpdDm());
        ttObjParamsSql.put("updUser", voExctDraft.getUpdUser());



        daoExctDraft.modifyExctDraft(ttObjParamsSql);
    }

    /**
     * @param ttObjParams 파라미터
     * @return 로그인 사용자 정보 조회
     * @throws Exception 예외
     */
    public VoCoUser getUserDtl(CoTtObjParams ttObjParams) throws Exception {

        return daoExctDraft.getUserDtl(ttObjParams);
    }

}