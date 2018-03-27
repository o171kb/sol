package tt.com.module.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import tt.TTLog;
//import tt.bean.VoEvent;
//import tt.bean.VoMuseum;
import tt.base.module.TtBaseService;
import tt.bean.VoApprInfo;
import tt.bean.VoBoard;
import tt.com.CoTtObjParams;
import tt.com.utils.UtCoDateUtils;
import tt.com.utils.UtCoFileNameUtils;
import tt.com.utils.UtCoFileUtils;
import tt.com.utils.UtCoIOUtils;
import tt.com.utils.UtCoStringUtils;
import tt.com.utils.UtCoThumbnailUtils;
import tt.constant.CsConstDef;
import tt.module.CpDocument;
//import tt.dao.DaoAdmin;

/**
 * DESC : 파일관련 서비스 클래스 <br />
 *
 * @Company think-tree.inc
 * @author ks-lee
 * @Date 2013. 1. 16. 오후 7:27:19
 */
@Service("coSvFileService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class SvCoFileService extends TtBaseService {

    // 파일업로드 디렉토리
//    @Value("${file.upload.dir}")
    private String fileUploadDir = "D:/uploadDir/image/attach/solupia/";

    // 파일업로드 디렉토리
//    @Value("${file.upload.temp.dir}")
    private String fileUploadTempDir = "uploadTempDir";

    // DB등록용 이미지루트디렉토리
//    @Value("${file.upload.db.root.dir}")
    private String fileUploadDbRootDir = "/image";

    // 프로퍼티 값 가져오기
    @Resource(name = "messageSourceAccessor")
    private MessageSourceAccessor accessor;

    private static final int UUID_SUBSTR_POS_START = 0;
    private static final int UUID_SUBSTR_POS_END = 16;
    private static final int DEFAULT_BUFFER_SIZE = 8192;
    private static final int DEFAULT_THUMBNAIL_WIDTH = 153;
    private static final int DEFAULT_THUMBNAIL_HEIGHT = 151;

    public void uploadEditFile(CommonsMultipartFile uploadInputBox, HttpServletRequest request,
            HttpServletResponse response) {

        try {
            String tmpContextPath = request.getContextPath();
            String tmpBindName = getSavedFileName(uploadInputBox, request, CsConstDef.UPLOAD_FROM_EDITER,
                    fileUploadTempDir, fileUploadDir);
            String tmpCallbackFunc = request.getParameter(CsConstDef.UPLOAD_PARAM_CALLBACK_FUNC_NAME);

            // 에디터에 표시될 이미지패스설정
            tmpBindName = tmpContextPath + fileUploadDbRootDir + "/" + CsConstDef.UPLOAD_IMG_ROOT_DIR
                    + UtCoStringUtils.substringAfter(tmpBindName, CsConstDef.UPLOAD_IMG_ROOT_DIR);

            // redirect url
            String redirectUrl = tmpContextPath.concat(CsConstDef.UPLOAD_REDIRECT_URL).concat(tmpCallbackFunc)
                    .concat(CsConstDef.UPLOAD_REDIRECT_URL_PARAM_FILE)
                    .concat(URLEncoder.encode(uploadInputBox.getOriginalFilename(), CsConstDef.ENCODE_CD_UTF8))
                    .concat(CsConstDef.UPLOAD_REDIRECT_URL_PARAM_FILE_URL).concat(tmpBindName);

            TTLog.info("redirectUrl :: " + redirectUrl, this.getClass());

            response.sendRedirect(redirectUrl);
        } catch (IOException e) {
            e.printStackTrace();
            // 에디터파일업로드중 에러가 발생했습니다.
            TTLog.error(accessor.getMessage("E001"), this.getClass());
        }
    }

    public static String getSavedFileName(CommonsMultipartFile fileData, HttpServletRequest request, String uploadFrom,
            String fileUploadTempDir, String fileUploadDir) {

        String tmpOrgRootDir = "";
        String tmpRootSaveDir = "";
        String tmpResultFileName = "";
        String tmpOrgResultFilename = "";

        InputStream tmpInputStream = null;
        OutputStream tmpOutputStream = null;

        try {
            // 세션ID로 임시폴더작성용
            String sessionId = (String) request.getSession().getId();
            // 점을 포함확장자
            String tmpFileExt = CsConstDef.STR_DOT
                    .concat(UtCoFileNameUtils.getExtension(fileData.getOriginalFilename()));
            // 업로드파일명
            String tmpFileName = generateId(uploadFrom);
            // 업로드폴더디레토리용날짜문자열
            String tmpSysDate = UtCoDateUtils.getCurrentDateAsString(UtCoDateUtils.DATE_PATTERN);
            // 업로드파일디렉토리
            String tmpSaveDir = fileUploadDir + fileUploadTempDir + "/" + tmpSysDate + sessionId + File.separator;

            File checkPath = new File(tmpSaveDir);
            if (!checkPath.isDirectory()) {
                checkPath.mkdirs();
            }

            tmpResultFileName = tmpSaveDir + tmpFileName + tmpFileExt;

            if (fileData.getSize() > 0) {
                tmpInputStream = fileData.getInputStream();
                tmpOutputStream = new FileOutputStream(tmpResultFileName);
                int readBytes = 0;
                byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
                while ((readBytes = tmpInputStream.read(buffer, 0, DEFAULT_BUFFER_SIZE)) != -1) {
                    tmpOutputStream.write(buffer, 0, readBytes);
                }
                UtCoIOUtils.closeQuietly(tmpOutputStream);
                UtCoIOUtils.closeQuietly(tmpInputStream);
            }

            // 원본파일이 필요한 파일 업로드
            if (uploadFrom.equals("banner") || uploadFrom.equals("center_room") || uploadFrom.equals("center_back")) {
                tmpOrgResultFilename = tmpRootSaveDir + tmpFileName + tmpFileExt;
                tmpRootSaveDir = tmpOrgRootDir + File.separator + uploadFrom + File.separator + tmpSysDate
                        + File.separator;

                // 원본파일이 필요할 경우 디렉토리 생성
                checkPath = new File(tmpRootSaveDir);
                if (!checkPath.isDirectory()) {
                    checkPath.mkdirs();
                }
                if (fileData.getSize() > 0) {
                    tmpInputStream = fileData.getInputStream();
                    tmpOutputStream = new FileOutputStream(tmpOrgResultFilename);
                    int readBytes = 0;
                    byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
                    while ((readBytes = tmpInputStream.read(buffer, 0, DEFAULT_BUFFER_SIZE)) != -1) {
                        tmpOutputStream.write(buffer, 0, readBytes);
                    }
                    UtCoIOUtils.closeQuietly(tmpOutputStream);
                    UtCoIOUtils.closeQuietly(tmpInputStream);
                }
            }

            // 섬네일 서버 업로드
            if (uploadFrom.equals("thumb")) {
                UtCoThumbnailUtils.createThumb(tmpResultFileName, tmpResultFileName, DEFAULT_THUMBNAIL_WIDTH,
                        DEFAULT_THUMBNAIL_HEIGHT);
            }

        } catch (Exception e) {
            e.printStackTrace();
            TTLog.error("CoFileService-getSavedFileName ERROR: " + e.getMessage(), UtCoIOUtils.class);
        } finally {
            UtCoIOUtils.closeQuietly(tmpOutputStream);
            UtCoIOUtils.closeQuietly(tmpInputStream);
        }

        return tmpResultFileName;
    }

    public static String generateId(String uploadFrom) {

        String uuid = UUID.randomUUID().toString().replace("-", "")
                .substring(UUID_SUBSTR_POS_START, UUID_SUBSTR_POS_END);
        StringBuilder sb = new StringBuilder();
        sb.append(UtCoDateUtils.getCurrentDateTime(UtCoDateUtils.DATE_HMS_PATTERN));
        sb.append("-");
        sb.append(uuid);
        sb.append("-");
        sb.append(uploadFrom);
        return sb.toString();
    }

    /**
     * 업로드화면으로부터 가져온 업로드정보를 임시디텍토리에 업로드.<br />
     *
     * @param request HttpServletRequest
     * @return 업로드정보
     */
    public Map uploadFileToTempDir(HttpServletRequest request) {

        Map paramMap = getParameterMap(request);
        paramMap = addMultipartFileAsFile(request, paramMap);

        return paramMap;
    }

    /**
     * 업로드화면으로부터 가져온 업로드정보를 임시디텍토리에 업로드.<br />
     *
     * @param srcDoc 저장도큐먼트
     * @return 업로드디텍토리
     * @throws Exception 예외
     */
    public String uploadFileToRealDirFromTempDir(CpDocument srcDoc) throws Exception {

        String resultUploadUrl = "";
        String tmpFromFileDir = "";

        if (!srcDoc.equals("") && srcDoc != null) {
            tmpFromFileDir = CsConstDef.STR_BOARD;
        } else {
            // 디렉토리생성용 ID를 구분할수없습니다.
            TTLog.error(accessor.getMessage("E002"), this.getClass());
            throw new Exception(accessor.getMessage("E002"));
        }

        String tmpDir = fileUploadDir + tmpFromFileDir + File.separator;

        if (!UtCoFileUtils.existsDir(tmpDir)) {
            new File(tmpDir).mkdir();
        }

        resultUploadUrl = tmpDir + generateId(UtCoStringUtils.lowerCase(srcDoc.getId())) + CsConstDef.STR_DOT
                + UtCoFileNameUtils.getExtension(srcDoc.getFileName());

        UtCoFileUtils.moveFile(srcDoc.getFile(), new File(resultUploadUrl));

        // 에디터에 표시될 이미지패스설정
        resultUploadUrl = fileUploadDbRootDir + File.separator + CsConstDef.UPLOAD_IMG_ROOT_DIR
                + UtCoStringUtils.substringAfter(resultUploadUrl, CsConstDef.UPLOAD_IMG_ROOT_DIR);

        TTLog.info("CoFileService-uploadFileToRealDirFromTempDir INFO resultUploadUrl : " + resultUploadUrl,
                this.getClass());
        return resultUploadUrl;
    }

    private Map getParameterMap(HttpServletRequest request) {

        Map reqParamMap = request.getParameterMap();

        Map paramMap = new HashMap();

        Iterator iter = reqParamMap.entrySet().iterator();

        while (iter.hasNext()) {

            Entry e = (Entry) iter.next();

            paramMap.put(e.getKey(), ((String[]) e.getValue())[0]);
        }

        return paramMap;
    }

    private Map addMultipartFileAsFile(HttpServletRequest request, Map paramMap) {

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        MultiValueMap multiValueMap = multipartRequest.getMultiFileMap();

        Iterator iter = multiValueMap.entrySet().iterator();

        while (iter.hasNext()) {

            Entry e = (Entry) iter.next();

            MultipartFile file = (MultipartFile) ((LinkedList) e.getValue()).get(0);

            if (file.getSize() != 0) {
                paramMap.put(e.getKey(), convertMultipartFileToDocument(file, (String) e.getKey()));
            }
        }

        return paramMap;
    }

    private CpDocument convertMultipartFileToDocument(MultipartFile multipartFile, String fileId) {

        File file = null;

        try {

            String uuid = UUID.randomUUID().toString().replace(CsConstDef.STR_DASH, "")
                    .substring(UUID_SUBSTR_POS_START, UUID_SUBSTR_POS_END);

            String tmpFileName = uuid + CsConstDef.STR_DASH + multipartFile.getOriginalFilename();
            file = new File(fileUploadDir + fileUploadTempDir + File.separator + tmpFileName);

            if (!UtCoFileUtils.existsDir(fileUploadDir + fileUploadTempDir)) {
                new File(fileUploadDir + fileUploadTempDir).mkdir();
            }

            multipartFile.transferTo(file);

        } catch (Exception e) {

            return null;
        }

        CpDocument resultDoc = new CpDocument(file);
        resultDoc.setId(fileId);
        return resultDoc;
    }

    /**
     * 파일다운로드 <br />
     *
     * @param downloadType 다운로드 관련구분
     * @param fid 파일관련 ID
     * @param pid 파일파라메터 ID
     * @return 다운로드 도큐멘트
     */
    public CpDocument downloadFile(String downloadType, String fid, String pid) {

        CpDocument resultDoc = null;
        String filePath = "";
        String fileName = "";

        try {
            if (CsConstDef.STR_BOARD.equals(downloadType)) {
                CoTtObjParams ttObjParamsSql = new CoTtObjParams();
                ttObjParamsSql.put("boardNo", fid);
                VoBoard rsVo = daoBoardSearch.getBoardDtl(ttObjParamsSql);
                fileName = rsVo.getFileNm();
                filePath = rsVo.getFileUrl();
            } else if (CsConstDef.STR_APPR.equals(downloadType)) {
                CoTtObjParams ttObjParamsSql = new CoTtObjParams();
                ttObjParamsSql.put("apprId", fid);
                VoApprInfo rsVo = daoMyApproval.selectApprovalInfo(ttObjParamsSql);
                fileName = rsVo.getAddProofNm();
                filePath = rsVo.getAddProofUrl();
            }
            // /image\attach/event\EV0000000001\20130121130830-5fa52ca3b9ca4c9a-_attachfile.jpg
            String tmpFilePath = fileUploadDir
                    + UtCoStringUtils.substringAfter(filePath, CsConstDef.UPLOAD_IMG_ROOT);

            File file = new File(tmpFilePath);
            if (file == null || file.length() == 0) {
                // 다운로드파일이 존재하지않습니다.
                throw new Exception(accessor.getMessage("E003"));
            }

            resultDoc = new CpDocument(tmpFilePath);
            resultDoc.setOrgFileName(fileName);
            TTLog.info(tmpFilePath, this.getClass());
        } catch (Exception e) {
            e.printStackTrace();
            TTLog.error(e.getMessage(), this.getClass());
            return null;
        }

        return resultDoc;
    }
}