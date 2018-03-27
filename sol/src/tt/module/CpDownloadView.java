package tt.module;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import tt.com.utils.UtCoFileUtils;
import tt.com.utils.UtCoIOUtils;
import tt.com.utils.UtCoStringUtils;

/**
 * <pre>
 * tt.component
 *    |_ CpDownloadView.java
 *
 * DESC : 파일다운로드 뷰 클래스 <br />
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author ks-lee
 * @Date 2013. 3. 5. 오후 5:52:36
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 3. 5.		ks-lee				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
@Component
public class CpDownloadView extends AbstractView {

    public CpDownloadView() {
        setContentType("application/download; utf-8");
    }

    @Override
    @SuppressWarnings("rawtypes")
    protected void renderMergedOutputModel(Map model, HttpServletRequest req, HttpServletResponse res) throws Exception {

        File file = (File) model.get("downloadFile");

        String fileName = (String) model.get("fileName");
        fileName = UtCoStringUtils.isEmpty(fileName) ? file.getName() : fileName;
        fileName = fileName.replace(" ", "_");

        String userAgent = req.getHeader("User-Agent");

        boolean ie = userAgent.indexOf("MSIE") > -1;

        if (ie) {
            fileName = URLEncoder.encode(fileName, "utf-8");
        } else {
            fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");
        }

        res.setContentType(getContentType());
        res.setContentLength((int) file.length());
        res.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
        res.setHeader("Content-Transfer-Encoding", "binary");

        OutputStream os = res.getOutputStream();

        FileInputStream fis = UtCoFileUtils.openInputStream(file);

        UtCoIOUtils.copy(fis, os);

        os.flush();

        UtCoIOUtils.closeQuietly(os);
        UtCoIOUtils.closeQuietly(fis);

    }
}
