package tt.com.bean;

/**
 * DESC : 코드상세 설정 클래스 <br />
 *
 * @Company think-tree.inc
 * @author ks-lee
 * @Date 2013. 2. 15. 오전 11:38:09
 */
public class VoCoCodeDtl extends VoCoBase {

    protected String cdDtlNo;
    protected String cdDtlNm;
    protected String cdDtlExp;
    protected int prirSeq;

    public VoCoCodeDtl() {
    }

    public VoCoCodeDtl(String s, String s1, String s2, int i) {
        cdDtlNo = s;
        cdDtlNm = s1;
        cdDtlExp = s2;
        prirSeq = i;
    }

    public String getCdDtlNo() {
        return cdDtlNo;
    }

    public void setCdDtlNo(String cdDtlNo) {
        this.cdDtlNo = cdDtlNo;
    }

    public String getCdDtlNm() {
        return cdDtlNm;
    }

    public void setCdDtlNm(String cdDtlNm) {
        this.cdDtlNm = cdDtlNm;
    }

    public String getCdDtlExp() {
        return cdDtlExp;
    }

    public void setCdDtlExp(String cdDtlExp) {
        this.cdDtlExp = cdDtlExp;
    }



}