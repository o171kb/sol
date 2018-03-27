package tt.module.admin.publishing;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import tt.TTLog;
import tt.base.module.TtBaseController;


/**
 * <pre>
 * tt.module.admin.publishing
 *    |_ PublishingController.java
 *
 * DESC : Publishing
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author Administrator
 * @Date 2013. 3. 25. 오후 5:48:52
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 3. 25.		hk-kim				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
@Controller
public class PublishingController extends TtBaseController {

    /**
     * 나의결재함 <br />
     * @param model 모델
     * @param session 세션
     * @return 페이지경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/page1.do")
    public String page1(ModelMap model, HttpSession session) throws Exception {

        TTLog.info(":::   page1 Start   :::", this.getClass());

        TTLog.info(":::   page1 End   :::", this.getClass());

        return "admin.publishing.1.tiles";
    }


    @RequestMapping(value = "/page2.do")
    public String page2(ModelMap model, HttpSession session) throws Exception {

        TTLog.info(":::   page2 Start   :::", this.getClass());

        TTLog.info(":::   page2 End   :::", this.getClass());

        return "admin.publishing.2.tiles";
    }

    @RequestMapping(value = "/page3.do")
    public String page3(ModelMap model, HttpSession session) throws Exception {

        TTLog.info(":::   page3 Start   :::", this.getClass());

        TTLog.info(":::   page3 End   :::", this.getClass());

        return "admin.publishing.3.tiles";
    }

    @RequestMapping(value = "/page4.do")
    public String page4(ModelMap model, HttpSession session) throws Exception {

        TTLog.info(":::   page4 Start   :::", this.getClass());

        TTLog.info(":::   page4 End   :::", this.getClass());

        return "admin.publishing.4.tiles";
    }

}
