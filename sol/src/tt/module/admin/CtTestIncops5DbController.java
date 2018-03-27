package tt.module.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import tt.TTLog;
import tt.base.module.TtBaseController;
import tt.com.CoTtObjParams;
import tt.com.module.login.SvCoLoginService;

/**
 * <pre>
 * tt.module.admin
 *    |_ CtTestIncops5DbController.java
 *
 * DESC : incops5 DB test용
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author ks-lee
 * @Date 2013. 4. 3. 오후 6:02:01
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 4. 3.		ks-lee				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
@Controller
public class CtTestIncops5DbController  {

    /** 테스트 */
    @Resource(name = "svTestIncops5DbService")
    protected SvTestIncops5DbService svTestIncops5DbService;

    /**
     * DB test <br />
     * @param model 모델
     * @param session 세션
     * @return 페이지경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/testEscortDb.do")
    public String testEscortDb(ModelMap model, HttpSession session) throws Exception {


        List<CoTtObjParams>  testlist = svTestIncops5DbService.getDeptList();

        for (CoTtObjParams map : testlist) {
            System.out.println(map.get("DEPTCODE"));
        }

        return "admin.incopys5.db.test.tiles";
    }

}
