package tt.com.module.login;

import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import tt.TTLog;
import tt.base.TtHttpSessionManager;
import tt.base.TtSession;
import tt.com.CoTtObjParams;
import tt.com.CoTtStrParams;
import tt.com.bean.VoCoLoginUserInfo;
import tt.com.bean.VoCoMenu;
import tt.com.bean.VoCoUserGrpMenu;
import tt.com.constant.CsCoConstDef;
import tt.com.dao.DaoCoMenu;
import tt.com.dao.DaoCoUser;
import tt.com.utils.UtCoDateUtils;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * <pre>
 * tt.com.module.login
 *    |_ SvCoLoginService.java
 *
 * DESC : 관리자 로그인 서비스 클래스 <br />
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author ks-lee
 * @Date 2013. 2. 27. 오후 7:28:18
 * @history :
 *  -----------------------------------------------------------------------
 *  변경일             작성자                     변경내용
 *  ----------- ------------------- ---------------------------------------
 *  2013. 2. 27.        ks-lee              최초 작성
 *  -----------------------------------------------------------------------
 *
 */
@Service("svCoAdminLoginService")
public class SvCoLoginService extends AbstractServiceImpl {

    /** 관리자관련 DAO 클래스 */
    @Resource(name = "daoCoUser")
    private DaoCoUser daoCoUser;

    /** 메뉴관련 DAO 클래스 */
    @Resource(name = "daoCoMenu")
    private DaoCoMenu daoCoMenu;

    public void setLoginInfo(CoTtObjParams ttObjParams) {



        //로그인사용자
        String userId = ttObjParams.getString(CsCoConstDef.KEY_USER_ID);
        HttpServletRequest request = (HttpServletRequest) ttObjParams.get(CsCoConstDef.KEY_REQUEST);

        CoTtStrParams ttStrParams = new CoTtStrParams();
        ttStrParams.put(CsCoConstDef.KEY_USER_ID, userId);

        //로그인사용자정보취득
        VoCoLoginUserInfo rsVoCoLoginUserInfo = daoCoUser.getUserLoginInfo(ttStrParams);
        HttpSession httpses = request.getSession(false);
        if (rsVoCoLoginUserInfo != null) {
            // Session Fixation対策
            httpses = request.getSession(false);
            if (httpses != null) {
                httpses.invalidate();
            }

            TtSession tts = TtHttpSessionManager.logon(request);
            String grpCl = rsVoCoLoginUserInfo.getGrpCl();
            String deptNo = rsVoCoLoginUserInfo.getMemDeptNo();
            String userNm = rsVoCoLoginUserInfo.getUserNm();
            String deptCd = rsVoCoLoginUserInfo.getDeptCd();
            String deptNm = rsVoCoLoginUserInfo.getDeptNm();

            //업자번호
            tts.put(CsCoConstDef.SS_KEY_008 , deptNo);
            tts.put(CsCoConstDef.SS_KEY_002, "0"); // Staging or Live
            //사용자아이디
            tts.put(CsCoConstDef.SS_KEY_003, userId); // User ID
            //사용자이름
            tts.put(CsCoConstDef.SS_KEY_019, userNm);
            //ses.put(CsCoConstDef.SS_KEY_004, userPwd); // User Pwd
            //그룹권한
            tts.put(CsCoConstDef.SS_KEY_009, grpCl);
            
            tts.put("deptCd", deptCd);
            
            tts.put("deptNm", deptNm);

            String ranStr = String.valueOf(new Random().nextInt());
            String cartId = new StringBuffer(UtCoDateUtils.getCurrentDateAsString(UtCoDateUtils.DATE_HMS_PATTERN)).append(
                    ranStr.substring(ranStr.length() - 6)).toString();
            tts.put(CsCoConstDef.SS_KEY_005, cartId);

            // 그룹메뉴정보를 세션에 격납
            ttStrParams.put(CsCoConstDef.KEY_GRP_CL, grpCl);
            List<VoCoUserGrpMenu> userGrpMenuList = daoCoMenu.getTotalMenuList(ttStrParams);

            CoTtStrParams menuStrParams = null;

            if (userGrpMenuList != null && userGrpMenuList.size() > 1) {
                menuStrParams = new CoTtStrParams();

                for (VoCoUserGrpMenu voCoUserGrpMenu : userGrpMenuList) {
                    menuStrParams.put(voCoUserGrpMenu.getMenuAction(),
                            voCoUserGrpMenu.getMenuLvl());
                }
            }

            //그룹모든메뉴
            tts.put(CsCoConstDef.SS_KEY_017, menuStrParams);

            //사용자그룹
            //userMenuList = daoCoMenu.getUserMenuList(ttStrParams);


        } else {
            TTLog.debug("로그인정보가 존재하지 않는 관리자입니다.", this.getClass());
        }
    }

    public List<VoCoMenu> getUserMenuList(CoTtStrParams ttParams) {
        // 사용자그룹
        return daoCoMenu.getUserMenuList(ttParams);
    }

}
