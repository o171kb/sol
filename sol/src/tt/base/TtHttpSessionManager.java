package tt.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import tt.TTLog;

/**
 * <pre>
 * tt.base.admin
 *    |_ TtHttpSessionManager.java
 *
 * DESC : 세션관리 클래스 <br />
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author ks-lee
 * @Date 2013. 3. 5. 오후 5:37:47
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 3. 5.		ks-lee				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
@Component
public final class TtHttpSessionManager {

    public static final String TT_ADMIN_SESSION = "TT_ADMIN_SESSION";
    public static final String TT_FRONT_SESSION = "TT_FRONT_SESSION";

    private TtHttpSessionManager() {
    }

    public static TtSession logon(HttpServletRequest httpservletrequest, String s) {
        HttpSession httpsession = httpservletrequest.getSession(true);
        StringBuffer stringbuffer = (new StringBuffer()).append("HttpSessionManager.logon: creating new seesion id=")
                .append(httpsession.getId());
        TTLog.debug(stringbuffer.toString(), tt.base.TtHttpSessionManager.class);
        TtSession csasession = new TtSession(httpsession.getId());
        httpsession.setAttribute("TT_FRONT_SESSION", csasession);
        return csasession;
    }

    public static TtSession logon(HttpServletRequest httpservletrequest) {
        HttpSession httpsession = httpservletrequest.getSession(false);
        httpsession = httpservletrequest.getSession(true);
        StringBuffer stringbuffer = (new StringBuffer()).append("HttpSessionManager.logon: creating new seesion id=")
                .append(httpsession.getId());
        TTLog.debug(stringbuffer.toString(), tt.base.TtHttpSessionManager.class);
        TtSession csasession = new TtSession(httpsession.getId());
        httpsession.setAttribute("TT_ADMIN_SESSION", csasession);
        return csasession;
    }

    public static TtSession logout(HttpServletRequest httpservletrequest, String s) {
        HttpSession httpsession = httpservletrequest.getSession(false);
        if (httpsession != null) {
            TtSession csasession = (TtSession) httpsession.getAttribute("TT_FRONT_SESSION");
            httpsession.invalidate();
            return csasession;
        } else {
            return null;
        }
    }

    public static TtSession logout(HttpServletRequest httpservletrequest) {
        HttpSession httpsession = httpservletrequest.getSession(false);
        if (httpsession != null) {
            TtSession csasession = (TtSession) httpsession.getAttribute("TT_ADMIN_SESSION");
            httpsession.invalidate();
            return csasession;
        } else {
            return null;
        }
    }

    public static TtSession getTtSession(HttpServletRequest httpservletrequest) {
        HttpSession httpsession = httpservletrequest.getSession(false);
        if (httpsession == null) {
            return null;
        } else {
            TtSession csasession = (TtSession) httpsession.getAttribute("TT_ADMIN_SESSION");
            return csasession;
        }
    }

    public static TtSession getTtSession(HttpServletRequest httpservletrequest, String s) {
        HttpSession httpsession = httpservletrequest.getSession(false);
        if (httpsession == null) {
            return null;
        }
        TtSession csasession = (TtSession) httpsession.getAttribute("TT_ADMIN_SESSION");
        TtSession csasession1 = (TtSession) httpsession.getAttribute("TT_FRONT_SESSION");
        if (csasession != null && csasession1 == null) {
            csasession1 = new TtSession(httpsession.getId());
            httpsession.setAttribute("TT_FRONT_SESSION", csasession1);
        }
        return csasession1;
    }

    public static TtSession getTtSession(HttpSession httpsession) {
        if (httpsession == null) {
            return null;
        }
        TtSession csasession = (TtSession) httpsession.getAttribute("TT_ADMIN_SESSION");
        TtSession csasession1 = (TtSession) httpsession.getAttribute("TT_FRONT_SESSION");
        if (csasession != null && csasession1 == null) {
            csasession1 = new TtSession(httpsession.getId());
            httpsession.setAttribute("TT_FRONT_SESSION", csasession1);
        }
        return csasession1;
    }

}