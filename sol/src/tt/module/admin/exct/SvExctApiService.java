package tt.module.admin.exct;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tt.TTLog;
import tt.base.TtSession;
import tt.base.module.TtBaseService;
import tt.bean.VoAddUser;
import tt.bean.VoApprDetail;
import tt.bean.VoApprInfo;
import tt.bean.VoExct;
import tt.bean.VoExctDraft;
import tt.bean.VoPcBasic;
import tt.com.CoTtObjParams;
import tt.com.bean.VoCoBase;
import tt.com.bean.VoCoCodeDtl;
import tt.com.bean.VoCoDept;
import tt.com.bean.VoCoUser;
import tt.com.utils.UtCoDateUtils;
import tt.com.utils.UtCoStringUtils;
import tt.constant.CsConstDef;
import tt.dao.DaoExct;
import tt.dao.DaoExctApi;
import tt.dao.DaoIncops5;
import tt.module.CpDocument;
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
 *  -----------------------------------------------------------------------
 *  변경일             작성자                     변경내용
 *  ----------- ------------------- ---------------------------------------
 *  2013. 3. 8.     ks-lee              최초 작성
 *  -----------------------------------------------------------------------
 *
 */
@Service("svExctApiService")
public class SvExctApiService extends TtBaseService  {
    /**
     * Escort API<br/>
     * @param ttObjParams 상신정보
     * @return 
     * @throws Exception 예외
     */
    @Transactional
    public List<VoApprDetail> selectExctApi(VoApprDetail voApprDetail) throws Exception {
        return daoExctApi.selectExctApi(voApprDetail);
    }
    
    @Transactional
    public void callProcedureApprException(CoTtObjParams ttObjParamsSql) throws Exception {
        daoMyApproval.callProcedureApprException(ttObjParamsSql);
    }
    
    @Transactional
    public void callProcedureApprStorerule(CoTtObjParams ttObjParamsSql) throws Exception {
        daoMyApproval.callProcedureApprStorerule(ttObjParamsSql);
    }
    
    @Transactional
    public void callProcedureApprExceptionAP(CoTtObjParams ttObjParamsSql) throws Exception {
        daoMyApproval.callProcedureApprExceptionAP(ttObjParamsSql);
    }
    
    @Transactional
    public int selectChkSameADDHDD(CoTtObjParams ttObjParamsSql) throws Exception {
        return daoIncops5.selectChkSameADDHDD(ttObjParamsSql);
    }
    
    @Transactional
    public void updateApprExceptionADDHDD(CoTtObjParams ttObjParamsSql) throws Exception {
        daoIncops5.updateApprExceptionADDHDD(ttObjParamsSql);
    }
    
    @Transactional
    public void insertApprExceptionADDHDD(CoTtObjParams ttObjParamsSql) throws Exception {
        daoIncops5.insertApprExceptionADDHDD(ttObjParamsSql);
    }
    
    @Transactional
    public int selectChkSameExceptionVPN(CoTtObjParams ttObjParamsSql) throws Exception {
        return daoIncops5.selectChkSameExceptionVPN(ttObjParamsSql);
    }
    
    @Transactional
    public void updateApprExceptionVPN(CoTtObjParams ttObjParamsSql) throws Exception {
        daoIncops5.updateApprExceptionVPN(ttObjParamsSql);
    }
    
    @Transactional
    public void insertApprExceptionVPN(CoTtObjParams ttObjParamsSql) throws Exception {
        daoIncops5.insertApprExceptionVPN(ttObjParamsSql);
    }
    
    @Transactional
    public VoApprInfo selectApprovalInfo(CoTtObjParams ttObjParamsSql) throws Exception {
        return daoMyApproval.selectApprovalInfo(ttObjParamsSql);
    }

    public void callProcedureExceptionICAT(CoTtObjParams ttObjParamsSql) throws Exception {
        daoMyApproval.callProcedureExceptionICAT(ttObjParamsSql);
    }

    public List<VoApprDetail> selectAttachApprDtlList(CoTtObjParams ttObjParamsSql){
        return daoMyApproval.selectAttachApprDtlList(ttObjParamsSql);
    }

    public void insertApprExceptionOutput(CoTtObjParams ttObjParamsSql) throws Exception {
        daoSecuPrint.insertApprExceptionOutput(ttObjParamsSql);
    }

    public void updateApprExceptionSite(CoTtObjParams ttObjParamsSql) throws Exception {
        daoMyApproval.insertApprExceptionSite(ttObjParamsSql);
    }

    public void insertApprExceptionSite(CoTtObjParams ttObjParamsSql) throws Exception {
        daoMyApproval.updateApprExceptionSite(ttObjParamsSql);
    }
    
}