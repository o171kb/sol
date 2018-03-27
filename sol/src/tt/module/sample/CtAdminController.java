package tt.module.sample;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import tt.TTLog;

/**
 * 관리자측 Controller 클래스
 *
 * @Company think-tree.inc
 * @author hk-kim
 * @Date 2013. 1. 8. 오후 1:43:15
 */
@Controller
public class CtAdminController {

    /**
     * 테스트 <br />
     * @param model 모델
     * @param session 세션
     * @return 페이지경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/adHome2.do")
    public String lee_test(ModelMap model, HttpSession session) throws Exception {

        TTLog.info(":::   adEventList Start   :::", this.getClass());

        TTLog.info(":::   adEventList End   :::", this.getClass());

        return "admin.lee.test.tiles";
    }

}
