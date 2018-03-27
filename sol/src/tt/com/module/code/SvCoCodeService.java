package tt.com.module.code;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import tt.com.bean.VoCoCodeDtl;
import tt.com.bean.VoCoCodeSp;
import tt.com.dao.DaoCoCode;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * DESC : 코드관련 서비스 클래스<br />
 *
 * @Company think-tree.inc
 * @author ks-lee
 * @Date 2013. 2. 15. 오후 1:00:18
 */
@Service("svCoCodeService")
public class SvCoCodeService extends AbstractServiceImpl {

    @Resource(name = "daoCoCode")
    private DaoCoCode daoCoCode;

    private static SvCoCodeService instance;
    private static HashMap<String, VoCoCodeSp> codes;
    private static Vector<?> emptyVector;

    public void setCoCodeDAO(DaoCoCode coCodeDAO) {
        this.daoCoCode = coCodeDAO;
    }

    @Bean(initMethod = "init")
    public String setCode() {

        VoCoCodeDtl codedtl;
        VoCoCodeSp code;

        codes = new HashMap<String, VoCoCodeSp>();

        try {
            List<VoCoCodeSp> codelist = daoCoCode.selectCodeInfoAll();

            for (VoCoCodeSp coCodeVO : codelist) {

                String s = coCodeVO.getCdNo();

                if (!codes.containsKey(s)) {
                    code = new VoCoCodeSp(s, coCodeVO.getCdNm());

                    codedtl = new VoCoCodeDtl(coCodeVO.getCdDtlNo(), coCodeVO.getCdDtlNm(), coCodeVO.getCdDtlExp(),
                            Integer.valueOf(coCodeVO.getPrirSeq()));
                    code.addCodeDtl(codedtl);
                    codes.put(s, code);
                } else {
                    codedtl = new VoCoCodeDtl(coCodeVO.getCdDtlNo(), coCodeVO.getCdDtlNm(), coCodeVO.getCdDtlExp(),
                            Integer.valueOf(coCodeVO.getPrirSeq()));
                    codes.get(s).addCodeDtl(codedtl);
                }
            }
        } catch (Exception e) {

            log.error("코드생성시 에러가 발생했습니다.");
            e.printStackTrace();
        }

        instance = new SvCoCodeService();
        return null;
    }

    public static SvCoCodeService getInstance() {
        return instance;
    }

    private static VoCoCodeSp getCodeInstance(String s) {

        VoCoCodeSp code = (VoCoCodeSp) codes.get(s);
        return code;
    }

    public static Vector<? extends Object> getValues(String s) {
        if (instance == null) {
            throw new IllegalStateException();
        } else {
            VoCoCodeSp code = getCodeInstance(s);
            return code != null ? code.getCdDtlNoCdDtlNmPairVector() : emptyVector;
        }
    }

    public static Vector<String> getCodes(String s) {
        if (instance == null) {
            throw new IllegalStateException();
        } else {
            VoCoCodeSp code = getCodeInstance(s);
            return code != null ? code.getCdDtlNoVector() : null;
        }
    }

    public static Vector<String> getCodeNames(String s) {
        if (instance == null) {
            throw new IllegalStateException();
        } else {
            VoCoCodeSp code = getCodeInstance(s);
            return code != null ? code.getCdDtlNmVector() : null;
        }
    }

    public static String getCodeString(String s, String s1) {
        if (instance == null) {
            throw new IllegalStateException();
        } else {
            VoCoCodeSp code = getCodeInstance(s);
            return code != null ? code.getCdDtlNm(s1) : "";
        }
    }

    public static String getCodeExpString(String s, String s1) {
        VoCoCodeSp code = getCodeInstance(s);
        return code != null ? code.getCdDtlExp(s1) : "";
    }

    public static int getCodePriority(String s, String s1) {
        VoCoCodeSp code = getCodeInstance(s);
        return code != null ? code.getCdDtlPrior(s1) : -1;
    }

    public String genComboBox(String s, String s1) {
        return genComboBox(s, s1, null, -1);
    }

    public String genComboBox(String s, String s1, String s2, int i) {
        VoCoCodeSp code = getCodeInstance(s);
        if (code != null) {
            return code.createComboBoxString(s1, s2, i);
        } else {
            return "<select name='" + s1 + "'>\n</select>\n";
        }
    }

    public String genOption(String s, String s1, int i) {
        VoCoCodeSp code = getCodeInstance(s);
        if (code != null) {
            return code.createOptionString(s1, i);
        } else {
            return null;
        }
    }

    public String getCode(String s) {
        Vector<String> vector = getCodes(s);
        if (vector != null) {
            return (String) vector.get(0);
        } else {
            return null;
        }
    }
}
