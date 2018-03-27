package tt.com.bean;

import java.util.HashMap;
import java.util.Vector;

/**
 * DESC : 코드 관련 유틸 클래스 <br />
 *
 * @Company think-tree.inc
 * @author ks-lee
 * @Date 2013. 2. 15. 오전 11:32:55
 */
@SuppressWarnings("unused")
public class VoCoCodeSp extends VoCoCode {

    private static final long serialVersionUID = 1L;

    public VoCoCodeSp() {
    }

    private String tmpCdNo;
    private String tmpCdNm;
    private Vector<VoCoCodeDtl> codeDtls;
    private HashMap<String, VoCoCodeDtl> codeDtlsHash;

    public VoCoCodeSp(String s, String s1) {
        codeDtls = new Vector<VoCoCodeDtl>();
        codeDtlsHash = new HashMap<String, VoCoCodeDtl>();
        tmpCdNo = s;
        tmpCdNm = s1;
    }

    public void addCodeDtl(VoCoCodeDtl codedtl) {
        codeDtls.add(codedtl);
        codeDtlsHash.put(codedtl.cdDtlNo, codedtl);
    }

    public String getCdDtlNm(String s) {
        VoCoCodeDtl codedtl = (VoCoCodeDtl) codeDtlsHash.get(s);
        return codedtl != null ? codedtl.cdDtlNm : "";
    }

    public String getCdDtlExp(String s) {
        VoCoCodeDtl codedtl = (VoCoCodeDtl) codeDtlsHash.get(s);
        return codedtl != null ? codedtl.cdDtlExp : "";
    }

    public int getCdDtlPrior(String s) {
        VoCoCodeDtl codedtl = (VoCoCodeDtl) codeDtlsHash.get(s);
        return (codedtl != null ? Integer.valueOf(codedtl.prirSeq) : null).intValue();
    }

    public Vector<String[]> getCdDtlNoCdDtlNmPairVector() {
        Vector<String[]> vector = new Vector<String[]>();
        int i = 0;
        for (int j = codeDtls.size(); i < j; i++) {
            VoCoCodeDtl codedtl = (VoCoCodeDtl) codeDtls.get(i);
            vector.add(new String[] { codedtl.cdDtlNo, codedtl.cdDtlNm });
        }

        return vector;
    }

    public Vector<String> getCdDtlNoVector() {
        Vector<String> vector = new Vector<String>();
        int i = 0;
        for (int j = codeDtls.size(); i < j; i++) {
            VoCoCodeDtl codedtl = (VoCoCodeDtl) codeDtls.get(i);
            vector.add(codedtl.cdDtlNo);
        }

        return vector;
    }

    public Vector<String> getCdDtlNmVector() {
        Vector<String> vector = new Vector<String>();
        int i = 0;
        for (int j = codeDtls.size(); i < j; i++) {
            VoCoCodeDtl codedtl = (VoCoCodeDtl) codeDtls.get(i);
            vector.add(codedtl.cdDtlNm);
        }

        return vector;
    }

    public String createComboBoxString(String s, String s1, int i) {
        StringBuffer stringbuffer = (new StringBuffer("<select name='")).append(s).append("'>\n");
        int j = 0;
        for (int k = codeDtls.size(); j < k; j++) {
            VoCoCodeDtl codedtl = (VoCoCodeDtl) codeDtls.get(j);
            stringbuffer.append("    <option value='").append(codedtl.cdDtlNo).append('\'');
            if (i == 0 && codedtl.cdDtlNo.equals(s1) || i == 1 && codedtl.cdDtlNm.equals(s1)) {
                stringbuffer.append(" selected");
            }
            stringbuffer.append(">").append(codedtl.cdDtlNm).append("</option>\n");
        }

        return stringbuffer.append("</select>\n").toString();
    }

    public String createOptionString(String s, int i) {
        StringBuffer stringbuffer = new StringBuffer();
        int j = 0;
        for (int k = codeDtls.size(); j < k; j++) {
            VoCoCodeDtl codedtl = (VoCoCodeDtl) codeDtls.get(j);
            stringbuffer.append("    <option VALUE='").append(codedtl.cdDtlNo).append('\'');
            if (i == 0 && codedtl.cdDtlNo.equals(s) || i == 1 && codedtl.cdDtlNm.equals(s)) {
                stringbuffer.append(" selected");
            }
            stringbuffer.append(">").append(codedtl.cdDtlNm).append("</option>\n");
        }

        return stringbuffer.toString();
    }

}