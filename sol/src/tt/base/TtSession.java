package tt.base;

import java.io.Serializable;
import java.util.HashMap;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.springframework.stereotype.Component;

import tt.TTLog;

/**
 * <pre>
 * tt.base.admin
 *    |_ TtSession.java
 *
 * DESC : 시스템 관리 세션 클래스 <br />
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author ks-lee
 * @Date 2013. 3. 5. 오전 10:55:11
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 3. 5.		ks-lee				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
@Component
public class TtSession extends HashMap<Object, Object> implements Serializable, HttpSessionBindingListener {

    private static final long serialVersionUID = -3324905964837484093L;

    public static final String SESSION_KEY = "KEY";
    protected final String custNode = "CUST";

    public TtSession() {
    }

    protected static void login(String s) {
        TTLog.debug("Session Key added......................", tt.base.TtSession.class);
    }

    protected static void logout(String s) {
        TTLog.debug("Session Key removed......................", tt.base.TtSession.class);
    }

    public TtSession(String s) {
        put(SESSION_KEY, s);
    }

    public String getId() {
        return (String) get(SESSION_KEY);
    }

    public void setContactCust(Object obj) {
        if (obj == null) {
            remove(custNode);
        } else {
            put(custNode, obj);
        }
    }

    public Object getContactCust() {
        return get(custNode);
    }

    public void valueBound(HttpSessionBindingEvent httpsessionbindingevent) {
        login(httpsessionbindingevent.getSession().getId());
    }

    public void valueUnbound(HttpSessionBindingEvent httpsessionbindingevent) {
        logout(httpsessionbindingevent.getSession().getId());
    }

}