package tt.com.module.file;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import tt.module.CpDocument;

/**
 * DESC : 파일 액션 콘트롤러 클래스 <br />
 *
 * @Company think-tree.inc
 * @author ks-lee
 * @Date 2013. 2. 15. 오전 11:47:16
 */
@Controller
public class CtCoFileController {

    // 파일서비스
    @Resource(name = "coSvFileService")
    protected SvCoFileService coSvFileService;

    /**
     * 에디터파일업로드 <br />
     * @param mav mvc모델 오브젝트
     * @param uploadInputBox 업로드 파일
     * @param request 리퀘스트
     * @param response 리스폰스
     */
    @RequestMapping(value = "/file/efileUploader.do", method = RequestMethod.POST)
    public void efileUploader(ModelAndView mav,
            @RequestParam(value = "update_image") CommonsMultipartFile uploadInputBox, HttpServletRequest request,
            HttpServletResponse response) {
        coSvFileService.uploadEditFile(uploadInputBox, request, response);
    }

    /**
     * 파일 다운로드 <br />
     * @param downloadType 다운로드 관련구분
     * @param fid 파일관련 ID
     * @param pid 파일파라메터 ID
     * @param model 모델
     * @return mvc모델 오브젝트
     */
    @RequestMapping(value = "/{downloadType}/download.do")
    public ModelAndView download(@PathVariable String downloadType, @RequestParam(defaultValue = "") String fid,
            @RequestParam(defaultValue = "") String pid, Model model) {

        CpDocument resultDoc = coSvFileService.downloadFile(downloadType, fid, pid);

        model.addAttribute("fileName", resultDoc.getOrgFileName());

        return new ModelAndView("cpDownloadView", "downloadFile", resultDoc.getFile());
    }
}