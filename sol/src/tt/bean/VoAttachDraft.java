package tt.bean;

import java.io.Serializable;

import tt.com.bean.VoCoBase;

/**
 * DESC :
 *
 * @Company think-tree.inc
 * @author sj-hwang
 * @Date 2013. 4. 1. 오후 3:26:18
 */
public class VoAttachDraft extends VoApprInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 메신저 실행명 */
    private String appExe;
    /** 메신저명 */
    private String appName;
    /**
     * 메신저 실행명 반환한다. <br />
     * @return appExe 메신저 실행명
     */
    public String getAppExe() {
        return appExe;
    }
    /**
     * 메신저명 반환한다. <br />
     * @return appName 메신저명
     */
    public String getAppName() {
        return appName;
    }
    /**
     * 메신저 실행명 설정한다. <br />
     * @param appExe 메신저 실행명
     */
    public void setAppExe(String appExe) {
        this.appExe = appExe;
    }
    /**
     * 메신저명 설정한다. <br />
     * @param appName 메신저명
     */
    public void setAppName(String appName) {
        this.appName = appName;
    }


    }
