package tt.base.module;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;

import tt.base.TtLogin;
import tt.base.TtSession;
import tt.com.module.dept.SvCoDeptService;
import tt.com.module.group.SvCoGroupService;
import tt.com.module.login.SvCoLoginService;
import tt.com.module.user.SvCoUserService;
import tt.module.admin.board.SvBoardSearchService;
import tt.module.admin.customer.SvCustomerService;
import tt.module.admin.exct.SvExctApiService;
import tt.module.admin.exct.SvExctAttachDraftService;
import tt.module.admin.exct.SvExctDraftService;
import tt.module.admin.exct.SvExctEscortDraftService;
import tt.module.admin.exct.SvExctOutputDraftService;
import tt.module.admin.exct.SvExctService;
import tt.module.admin.exct.SvExctSiteDraftService;
import tt.module.admin.main.SvAdMainService;
import tt.module.admin.mypage.SvMyApprovalService;
import tt.module.admin.mypage.SvMyDraftService;
import tt.module.admin.selfcheck.SvDeptPcCheckService;
import tt.module.admin.selfcheck.SvMyPcCheckService;
import tt.module.com.dwr.SvDwrService;

/**
 * <pre>
 * tt.com.module.user
 *    |_ CtCoUserController.java
 *
 * DESC : 관리자 관리 Controller 클래스 <br />
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author ks-lee
 * @Date 2013. 3. 6. 오후 1:07:37
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 3. 6.		ks-lee				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
/**
 * <pre>
 * tt.base.module
 *    |_ TtBaseController.java
 *
 * DESC : 베이스 콘트롤러 클래스 <br />
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author ks-lee
 * @Date 2013. 3. 6. 오후 2:58:39
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 3. 6.		ks-lee				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
@Controller
public class TtBaseController {

    /** 로그인 서비스 */
    @Resource(name = "svCoAdminLoginService")
    protected SvCoLoginService svCoAdminLoginService;

    @Resource(name = "svAdMainService")
    protected SvAdMainService svAdMainService;

    /** 관리자관리 서비스 */
    @Resource(name = "svCoUserService")
    protected SvCoUserService svCoUserService;

    /** 부서관리 서비스 */
    @Resource(name = "svCoDeptService")
    protected SvCoDeptService svCoDeptService;

    /** 그룹관리 서비스 */
    @Resource(name = "svCoGroupService")
    protected SvCoGroupService svCoGroupService;

    /** 예외관리 서비스 */
    @Resource(name = "svExctService")
    protected SvExctService svExctService;

    /** 예외신청 문구 서비스 */
    @Resource(name = "svExctDraftService")
    protected SvExctDraftService svExctDraftService;

    /** 나의신청현황*/
    @Resource(name = "svMyDraftService")
    protected SvMyDraftService svMyDraftService;

    /** Escort 예외신청 서비스 */
    @Resource(name = "svExctEscortDraftService")
    protected SvExctEscortDraftService svExctEscortDraftService;

    /** 첨부통제 예외신청 서비스 */
    @Resource(name = "svExctAttachDraftService")
    protected SvExctAttachDraftService svExctAttachDraftService;

    /** 출력보안 예외신청 서비스 */
    @Resource(name = "svExctOutputDraftService")
    protected SvExctOutputDraftService svExctOutputDraftService;

    /** 고객센터 */
    @Resource(name = "svCustomerService")
    protected SvCustomerService svCustomerService;

    /** 시스템 게시관리 */
    @Resource(name = "svBoardSearchService")
    protected SvBoardSearchService svBoardSearchService;

    /** 시스템 게시관리 */
    @Resource(name = "svMyApprovalService")
    protected SvMyApprovalService svMyApprovalService;

    /** dwr 서비스 */
    @Resource(name = "svDwrService")
    protected SvDwrService svDwrService;

    /** 내PC점검현황 */
    @Resource(name = "svMyPcCheckService")
    protected SvMyPcCheckService svMyPcCheckService;
    /** 부서PC점검현황 */
    @Resource(name = "svDeptPcCheckService")
    protected SvDeptPcCheckService svDeptPcCheckService;
    /** 사이트예외신청 */
    @Resource(name = "svExctSiteDraftService")
    protected SvExctSiteDraftService svExctSiteDraftService;

    /** 사이트예외신청 */
    @Resource(name = "svExctApiService")
    protected SvExctApiService svExctApiService;
    
    
    /** 로그인세션 */
    protected TtSession ttSession = null;

    /**
     * 로그인 상태를 체크한다 <br />
     * @param request 리퀘스트
     * @return 로그인상태 true:로그인 false:비로그인
     */
    protected boolean isLogin(HttpServletRequest request) {

        TtSession tts = TtLogin.checkLogin(request);

        if (tts != null) {
            ttSession = tts;
            return true;
        }
        return false;
    }

    /**
     * 로그인세션 를 취득 <br />
     * @return ttSession 로그인세션
     */
    public TtSession getTtSession() {
        return ttSession;
    }

}