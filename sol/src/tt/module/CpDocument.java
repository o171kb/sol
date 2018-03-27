package tt.module;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import tt.TTLog;
import tt.com.utils.UtCoFileNameUtils;
import tt.com.utils.UtCoFileUtils;
import tt.com.utils.UtCoIOUtils;
import tt.constant.CsStatus;

/**
 * DESC : File의 생성 및 관련 정보를 관리하는 클래스. Document는 File, InputStream, byte[]등의 파라미터로 부터 Document
 * 인스턴스를 생성할 수 있는 특징을 가진다.
 *
 * @Company think-tree.inc
 * @author ks-lee
 * @Date 2013. 1. 16. 오후 8:04:50
 */
public class CpDocument implements Serializable {

    private static final long serialVersionUID = 4545320111319217462L;

    private File file;

    private String id;

    private String orgFileName;

    public CpDocument(File file) {
        this.file = file;
    }

    public CpDocument(String filePath) {
        this.file = new File(filePath);
    }

    public CpDocument(InputStream is, String filePath) {

        this.file = new File(filePath);
        FileOutputStream fos = UtCoFileUtils.openOutputStream(this.file);

        int copiedCharLen = UtCoIOUtils.copy(is, fos);

        if (copiedCharLen <= 0) {
            TTLog.error("Document-constructor ERROR: Document 객체 생성에 실패하였습니다.", getClass());
        }

        UtCoIOUtils.closeQuietly(fos);
    }

    public CpDocument(byte[] data, String filePath) {

        this.file = new File(filePath);
        FileOutputStream fos = UtCoFileUtils.openOutputStream(this.file);

        CsStatus result = UtCoIOUtils.write(data, fos);

        if (result == CsStatus.FAIL) {
            TTLog.error("Document-constructor ERROR: Document 객체 생성에 실패하였습니다.", getClass());
        }

        UtCoIOUtils.closeQuietly(fos);
    }

    public InputStream getInputStream() {
        return UtCoFileUtils.openInputStream(this.file);
    }

    public boolean exists() {
        return this.file.exists();
    }

    public boolean isReadOnly() {
        return (!this.file.canWrite() && !this.file.isDirectory());
    }

    public URL getURL() {
        try {
            return this.file.toURI().toURL();
        } catch (MalformedURLException e) {
            TTLog.error("Document-getURL ERROR: " + e.getMessage(), getClass());
            return null;
        }
    }

    public URI getURI() {
        return this.file.toURI();
    }

    public long contentLength() {
        return this.file.length();
    }

    public long lastModified() {
        return file.lastModified();
    }

    public String getFileName() {
        return this.file.getName();
    }

    public String getFileDir() {
        return UtCoFileNameUtils.getFullPath(this.file.getAbsolutePath());
    }

    public String getFilePath() {
        return this.file.getAbsolutePath();
    }

    public String getDescription() {
        return "file [" + this.file.getAbsolutePath() + "]";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrgFileName() {
        return orgFileName;
    }

    public void setOrgFileName(String orgFileName) {
        this.orgFileName = orgFileName;
    }

    public File getFile() {
        return this.file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public byte[] getBytes() {
        return UtCoFileUtils.readFileToByteArray(this.file);
    }

}
