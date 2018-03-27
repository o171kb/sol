package tt.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import tt.com.constant.CsCoConstDef;
import tt.com.utils.UtCoStringUtils;

/**
 * DESC : 차트관련 클래스<br />
 *
 * @Company think-tree.inc
 * @author ks-lee
 * @Date 2013. 2. 15. 오후 1:10:21
 */
public final class TtLogin {

    private TtLogin() {
    }


    /**
     * 로그인상태를 체크한다 <br />
     * @param request 리퀘스트
     * @return TT세션
     */
    public static TtSession checkLogin(HttpServletRequest request) {
        TtSession tts = null;
        boolean isLogin = false;
        HttpSession httpses = request.getSession(false);
        if (httpses != null) {
            tts = (TtSession) TtHttpSessionManager.getTtSession(request);
            if (tts != null) {
                String userId = (String) tts.get(CsCoConstDef.SS_KEY_003);
                if (!UtCoStringUtils.isEmpty(userId)) {
                    isLogin = true;
                } else {
                    tts = null;
                }
            }
        }

        if (!isLogin) {
            if (httpses != null) {
                httpses.invalidate();
            }
        }

        return tts;
    }
}