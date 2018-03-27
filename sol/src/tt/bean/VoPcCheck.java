package tt.bean;
/**
 * <pre>
 * tt.bean
 *    |_ VoPcCheck.java
 *
 * DESC :
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author hj-kim
 * @Date 2013. 5. 6. 오후 2:21:51
 * @history :
 *  -----------------------------------------------------------------------
 *  변경일             작성자                     변경내용
 *  ----------- ------------------- ---------------------------------------
 *  2013. 5. 6.     hj-kim              최초 작성
 *  -----------------------------------------------------------------------
 *
 */
public class VoPcCheck extends VoSelfDiaItem {

    private static final long serialVersionUID = 1315554918109341499L;

    private String serialNumber;
    private String userId;
    private String macAddress;
    private String osType;
    private String isSys;

    private String selfDiagnosticIdx;
    private String itemId;
    private String isSafety;
    private String description;
    private String insDate;
    private String updDate;
    private String isDel;
    private String idx;
    private String chkValue;
    private String allSafety;

    private String selfDiagnosticPkey;
    private String VIRUS_FILE_VERIFY;
    private String SPYWARE_VERIFY;
    private String UPDATE_AUTO_DISC_VERIFY;
    private String UPDATE_AUTO_VACCINE_VERIFY;
    private String VIRUS_UPFILE_VERIFY;
    private String SERVICE_PACK_VERIFY;
    private String IE_VERSION_VERIFY;
    private String OS_UPDATE_VERIFY;
    private String OS_PATCH_VERIFY;
    private String IE_PATCH_VERIFY;
    private String GUEST_VERIFY;
    private String ADMIN_VERIFY;
    private String SIMPLE_PWD_VERIFY;
    private String SIMPLE_PWD_CURRENT;
    private String SAVER_PASSWD_VERIFY;
    private String TENMIN_VERIFY;
    private String REG_KEY_VERIFY;
    private String RPC_SHARE_VERIFY;
    private String SHARE_FOLDER_VERIFY;
    private String EVERYONE_VERIFY;
    private String NO_SERVICE_VERIFY;
    private String RW_EXCUTE_VERIFY;
    private String AUTOBAT_READ_VERIFY;
    private String NTFS_VERIFY;
    private String UPDATE_AUTO_DISC_CURRENT;

    private String IE_VERSION_CURRENT;
    private String OS_PATCH_CURRENT;
    private String IE_PATCH_CURRENT;
    private String TENMIN_CURRENT;
    private String SHARE_FOLDER_CURRENT;

    private String SOFTWARE_VERIFY;
    private String NO_SERVICE_CURRENT;

    private String pkId;


    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public String getAllSafety() {
        return allSafety;
    }
    public void setAllSafety(String allSafety) {
        this.allSafety = allSafety;
    }
    private String[] attr = new String[5];

    public String getChkValue() {
        return chkValue;
    }
    public void setChkValue(String chkValue) {
        this.chkValue = chkValue;
    }
    public String getIdx() {
        return idx;
    }
    public void setIdx(String idx) {
        this.idx = idx;
    }
    public String getSelfDiagnosticIdx() {
        return selfDiagnosticIdx;
    }
    public void setSelfDiagnosticIdx(String selfDiagnosticIdx) {
        this.selfDiagnosticIdx = selfDiagnosticIdx;
    }
    public String getItemId() {
        return itemId;
    }
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
    public String getIsSafety() {
        return isSafety;
    }
    public void setIsSafety(String isSafety) {
        this.isSafety = isSafety;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getInsDate() {
        return insDate;
    }
    public void setInsDate(String insDate) {
        this.insDate = insDate;
    }
    public String getUpdDate() {
        return updDate;
    }
    public void setUpdDate(String updDate) {
        this.updDate = updDate;
    }
    public String getIsDel() {
        return isDel;
    }
    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }
    public String getIsSys() {
        return isSys;
    }
    public void setIsSys(String isSys) {
        this.isSys = isSys;
    }
    public String getSerialNumber() {
        return serialNumber;
    }
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getMacAddress() {
        return macAddress;
    }
    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }
    public String getOsType() {
        return osType;
    }
    public void setOsType(String osType) {
        this.osType = osType;
    }
    public String getSelfDiagnosticPkey() {
        return selfDiagnosticPkey;
    }
    public void setSelfDiagnosticPkey(String selfDiagnosticPkey) {
        this.selfDiagnosticPkey = selfDiagnosticPkey;
    }
    public String getVIRUS_FILE_VERIFY() {
        return VIRUS_FILE_VERIFY;
    }
    public void setVIRUS_FILE_VERIFY(String vIRUS_FILE_VERIFY) {
        VIRUS_FILE_VERIFY = vIRUS_FILE_VERIFY;
    }
    public String getSPYWARE_VERIFY() {
        return SPYWARE_VERIFY;
    }
    public void setSPYWARE_VERIFY(String sPYWARE_VERIFY) {
        SPYWARE_VERIFY = sPYWARE_VERIFY;
    }
    public String getUPDATE_AUTO_DISC_VERIFY() {
        return UPDATE_AUTO_DISC_VERIFY;
    }
    public void setUPDATE_AUTO_DISC_VERIFY(String uPDATE_AUTO_DISC_VERIFY) {
        UPDATE_AUTO_DISC_VERIFY = uPDATE_AUTO_DISC_VERIFY;
    }
    public String getUPDATE_AUTO_VACCINE_VERIFY() {
        return UPDATE_AUTO_VACCINE_VERIFY;
    }
    public void setUPDATE_AUTO_VACCINE_VERIFY(String uPDATE_AUTO_VACCINE_VERIFY) {
        UPDATE_AUTO_VACCINE_VERIFY = uPDATE_AUTO_VACCINE_VERIFY;
    }
    public String getVIRUS_UPFILE_VERIFY() {
        return VIRUS_UPFILE_VERIFY;
    }
    public void setVIRUS_UPFILE_VERIFY(String vIRUS_UPFILE_VERIFY) {
        VIRUS_UPFILE_VERIFY = vIRUS_UPFILE_VERIFY;
    }
    public String getSERVICE_PACK_VERIFY() {
        return SERVICE_PACK_VERIFY;
    }
    public void setSERVICE_PACK_VERIFY(String sERVICE_PACK_VERIFY) {
        SERVICE_PACK_VERIFY = sERVICE_PACK_VERIFY;
    }
    public String getIE_VERSION_VERIFY() {
        return IE_VERSION_VERIFY;
    }
    public void setIE_VERSION_VERIFY(String iE_VERSION_VERIFY) {
        IE_VERSION_VERIFY = iE_VERSION_VERIFY;
    }
    public String getOS_UPDATE_VERIFY() {
        return OS_UPDATE_VERIFY;
    }
    public void setOS_UPDATE_VERIFY(String oS_UPDATE_VERIFY) {
        OS_UPDATE_VERIFY = oS_UPDATE_VERIFY;
    }
    public String getOS_PATCH_VERIFY() {
        return OS_PATCH_VERIFY;
    }
    public void setOS_PATCH_VERIFY(String oS_PATCH_VERIFY) {
        OS_PATCH_VERIFY = oS_PATCH_VERIFY;
    }
    public String getIE_PATCH_VERIFY() {
        return IE_PATCH_VERIFY;
    }
    public void setIE_PATCH_VERIFY(String iE_PATCH_VERIFY) {
        IE_PATCH_VERIFY = iE_PATCH_VERIFY;
    }
    public String getGUEST_VERIFY() {
        return GUEST_VERIFY;
    }
    public void setGUEST_VERIFY(String gUEST_VERIFY) {
        GUEST_VERIFY = gUEST_VERIFY;
    }
    public String getADMIN_VERIFY() {
        return ADMIN_VERIFY;
    }
    public void setADMIN_VERIFY(String aDMIN_VERIFY) {
        ADMIN_VERIFY = aDMIN_VERIFY;
    }
    public String getSIMPLE_PWD_VERIFY() {
        return SIMPLE_PWD_VERIFY;
    }
    public void setSIMPLE_PWD_VERIFY(String sIMPLE_PWD_VERIFY) {
        SIMPLE_PWD_VERIFY = sIMPLE_PWD_VERIFY;
    }
    public String getSIMPLE_PWD_CURRENT() {
        return SIMPLE_PWD_CURRENT;
    }
    public void setSIMPLE_PWD_CURRENT(String sIMPLE_PWD_CURRENT) {
        SIMPLE_PWD_CURRENT = sIMPLE_PWD_CURRENT;
    }
    public String getSAVER_PASSWD_VERIFY() {
        return SAVER_PASSWD_VERIFY;
    }
    public void setSAVER_PASSWD_VERIFY(String sAVER_PASSWD_VERIFY) {
        SAVER_PASSWD_VERIFY = sAVER_PASSWD_VERIFY;
    }
    public String getTENMIN_VERIFY() {
        return TENMIN_VERIFY;
    }
    public void setTENMIN_VERIFY(String tENMIN_VERIFY) {
        TENMIN_VERIFY = tENMIN_VERIFY;
    }
    public String getREG_KEY_VERIFY() {
        return REG_KEY_VERIFY;
    }
    public void setREG_KEY_VERIFY(String rEG_KEY_VERIFY) {
        REG_KEY_VERIFY = rEG_KEY_VERIFY;
    }
    public String getRPC_SHARE_VERIFY() {
        return RPC_SHARE_VERIFY;
    }
    public void setRPC_SHARE_VERIFY(String rPC_SHARE_VERIFY) {
        RPC_SHARE_VERIFY = rPC_SHARE_VERIFY;
    }
    public String getSHARE_FOLDER_VERIFY() {
        return SHARE_FOLDER_VERIFY;
    }
    public void setSHARE_FOLDER_VERIFY(String sHARE_FOLDER_VERIFY) {
        SHARE_FOLDER_VERIFY = sHARE_FOLDER_VERIFY;
    }
    public String getEVERYONE_VERIFY() {
        return EVERYONE_VERIFY;
    }
    public void setEVERYONE_VERIFY(String eVERYONE_VERIFY) {
        EVERYONE_VERIFY = eVERYONE_VERIFY;
    }
    public String getNO_SERVICE_VERIFY() {
        return NO_SERVICE_VERIFY;
    }
    public void setNO_SERVICE_VERIFY(String nO_SERVICE_VERIFY) {
        NO_SERVICE_VERIFY = nO_SERVICE_VERIFY;
    }
    public String getRW_EXCUTE_VERIFY() {
        return RW_EXCUTE_VERIFY;
    }
    public void setRW_EXCUTE_VERIFY(String rW_EXCUTE_VERIFY) {
        RW_EXCUTE_VERIFY = rW_EXCUTE_VERIFY;
    }
    public String getAUTOBAT_READ_VERIFY() {
        return AUTOBAT_READ_VERIFY;
    }
    public void setAUTOBAT_READ_VERIFY(String aUTOBAT_READ_VERIFY) {
        AUTOBAT_READ_VERIFY = aUTOBAT_READ_VERIFY;
    }
    public String getNTFS_VERIFY() {
        return NTFS_VERIFY;
    }
    public void setNTFS_VERIFY(String nTFS_VERIFY) {
        NTFS_VERIFY = nTFS_VERIFY;
    }
    public String getUPDATE_AUTO_DISC_CURRENT() {
        return UPDATE_AUTO_DISC_CURRENT;
    }
    public void setUPDATE_AUTO_DISC_CURRENT(String uPDATE_AUTO_DISC_CURRENT) {
        UPDATE_AUTO_DISC_CURRENT = uPDATE_AUTO_DISC_CURRENT;
    }
    public String getIE_VERSION_CURRENT() {
        return IE_VERSION_CURRENT;
    }
    public void setIE_VERSION_CURRENT(String iE_VERSION_CURRENT) {
        IE_VERSION_CURRENT = iE_VERSION_CURRENT;
    }
    public String getOS_PATCH_CURRENT() {
        return OS_PATCH_CURRENT;
    }
    public void setOS_PATCH_CURRENT(String oS_PATCH_CURRENT) {
        OS_PATCH_CURRENT = oS_PATCH_CURRENT;
    }
    public String getIE_PATCH_CURRENT() {
        return IE_PATCH_CURRENT;
    }
    public void setIE_PATCH_CURRENT(String iE_PATCH_CURRENT) {
        IE_PATCH_CURRENT = iE_PATCH_CURRENT;
    }
    public String getTENMIN_CURRENT() {
        return TENMIN_CURRENT;
    }
    public void setTENMIN_CURRENT(String tENMIN_CURRENT) {
        TENMIN_CURRENT = tENMIN_CURRENT;
    }
    public String getSHARE_FOLDER_CURRENT() {
        return SHARE_FOLDER_CURRENT;
    }
    public void setSHARE_FOLDER_CURRENT(String sHARE_FOLDER_CURRENT) {
        SHARE_FOLDER_CURRENT = sHARE_FOLDER_CURRENT;
    }
    public String getSOFTWARE_VERIFY() {
        return SOFTWARE_VERIFY;
    }
    public void setSOFTWARE_VERIFY(String sOFTWARE_VERIFY) {
        SOFTWARE_VERIFY = sOFTWARE_VERIFY;
    }
    public String getNO_SERVICE_CURRENT() {
        return NO_SERVICE_CURRENT;
    }
    public void setNO_SERVICE_CURRENT(String nO_SERVICE_CURRENT) {
        NO_SERVICE_CURRENT = nO_SERVICE_CURRENT;
    }
}
