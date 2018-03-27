package tt.com.module.dept;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tt.TTLog;
import tt.base.module.TtBaseService;
import tt.com.CoTtObjParams;
import tt.com.bean.VoCoDept;
import tt.com.constant.CsCoConstDef;
import tt.com.utils.UtCoDateUtils;
import tt.com.utils.UtCoStringUtils;


/**
 * <pre>
 * tt.com.module.dept
 *    |_ SvCoDeptService.java
 *
 * DESC : 부서 관리 서비스 클래스 <br />
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author ks-lee
 * @Date 2013. 3. 8. 오전 9:43:41
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 3. 8.		ks-lee				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
@Service("svCoDeptService")
public class SvCoDeptService extends TtBaseService  {


    /**
     * 부서검색리스트 취득 <br />
     * @param ttObjParams 검색 파라미터
     * @return 검색리스트
     */
    public List<VoCoDept> getDeptSearchListForPage(CoTtObjParams ttObjParams) {

        VoCoDept voCoDept = (VoCoDept) ttObjParams.get("voCoDept");

        //페이징파라미터설정
        CoTtObjParams ttObjParamsSql = getParamsObjOfPageInfoSetted(voCoDept.getFirstIndex(), voCoDept.getRecordCountPerPage());
        ttObjParamsSql.put("searchStatus", UtCoStringUtils.trim(voCoDept.getSearchStatus()));
        ttObjParamsSql.put("searchDeptCd", UtCoStringUtils.trim(voCoDept.getSearchDeptCd()));
        ttObjParamsSql.put("searchDeptNm", UtCoStringUtils.trim(voCoDept.getSearchDeptNm()));

        return daoCoDept.getDeptSearchListForPage(ttObjParamsSql);
    }

    /**
     * 부서검색 총 갯수 취득 <br />
     *
     * @param ttObjParams 검색 파라미터
     * @return 토탈카운트
     */
    public int getDeptSearchListTotCnt(CoTtObjParams ttObjParams) {

        VoCoDept voCoDept = (VoCoDept) ttObjParams.get("voCoDept");

        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        ttObjParamsSql.put("searchDeptCd", UtCoStringUtils.trim(voCoDept.getSearchDeptCd()));
        ttObjParamsSql.put("searchDeptNm", UtCoStringUtils.trim(voCoDept.getSearchDeptNm()));

        return daoCoDept.getDeptSearchListTotCnt(ttObjParamsSql);
    }

    /**
     * 부서의 상세를 조회한다 <br />
     * @param ttObjParams 검색 파라미터
     * @return 검색부서
     */
    public VoCoDept getDeptDtl(CoTtObjParams ttObjParams) {

        VoCoDept voCoDept = (VoCoDept) ttObjParams.get("voCoDept");
        TTLog.debug(voCoDept.toString(), this.getClass());

        //파라미터설정
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        ttObjParamsSql.put("deptNo", Integer.parseInt(voCoDept.getDeptNo()));


        return daoCoDept.getDeptDtl(ttObjParamsSql);
    }

    /**
     * 부서를 (논리)삭제한다 <br />
     * @param ttObjParams 삭제정보
     */
    @Transactional
    public void deleteDept(CoTtObjParams ttObjParams) {

        VoCoDept voCoDept = (VoCoDept) ttObjParams.get("voCoDept");

        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        if (!UtCoStringUtils.isEmpty(voCoDept.getDelArr())) {
            String[] chkDept = voCoDept.getDelArr().split(",");

            for (int i = 0; i < chkDept.length; ++i) {
                ttObjParamsSql.put("deptNo", Integer.parseInt(chkDept[i]));
                daoCoDept.deleteDept(ttObjParamsSql);
            }
        }
    }

    /**
     * 부서정보를 수정한다 <br />
     * @param ttObjParams 수정정보
     */
    @Transactional
    public void modifyDept(CoTtObjParams ttObjParams) {

        VoCoDept voCoDept = (VoCoDept) ttObjParams.get("voCoDept");
        TTLog.debug(voCoDept.toString(), this.getClass());

        //파라미터설정
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        ttObjParamsSql.put("deptNo", Integer.parseInt(voCoDept.getDeptNo()));
        ttObjParamsSql.put("deptNm", voCoDept.getDeptNm());
        ttObjParamsSql.put("deptNmEn", voCoDept.getDeptNmEn());
        ttObjParamsSql.put("deptExp", voCoDept.getDeptExp());

        daoCoDept.updateDeptInfo(ttObjParamsSql);
        //TODO return daoCoDept.getDeptDtl(ttObjParamsSql);
    }

    /**
     * 부서정보를 등록한다 <br />
     * @param ttObjParams 등록정보
     */
    @Transactional
    public void registDept(CoTtObjParams ttObjParams) {

        VoCoDept voCoDept = (VoCoDept) ttObjParams.get("voCoDept");
        TTLog.debug(voCoDept.toString(), this.getClass());

        //등록시간
        String sysdate = UtCoDateUtils.getCurrentDateAsString(UtCoDateUtils.DATE_HMS_PATTERN);

        //파라미터설정
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();

        //MAX 부서번호취득 + 1
        int maxDeptNo = daoCoDept.getMaxDeptNo() + 1;

        //상위부서에 직속부서수 + 1 취득
        ttObjParamsSql.put("highDeptCd", voCoDept.getHighDeptCd());
        int nextMadeCdNo = daoCoDept.getHighDeptCnt(ttObjParamsSql);

        //상위부서정보취득
        ttObjParamsSql.put("deptCd", voCoDept.getHighDeptCd());
        VoCoDept rsHighDeptInfo =  daoCoDept.getDeptDtlForDeptCd(ttObjParamsSql);
        String highDeptMadeCd = rsHighDeptInfo.getMadeCd();
        String nextMadeCd = highDeptMadeCd + UtCoStringUtils.leftPad(String.valueOf(nextMadeCdNo), 3, '0');

        ttObjParamsSql.put(VoCoDept.COL_DEPT_NO, maxDeptNo);
        ttObjParamsSql.put(VoCoDept.COL_DEPT_CD, voCoDept.getDeptCd());
        ttObjParamsSql.put(VoCoDept.COL_MADE_CD, nextMadeCd);
        ttObjParamsSql.put(VoCoDept.COL_HIGH_DEPT_CD, voCoDept.getHighDeptCd());
        ttObjParamsSql.put(VoCoDept.COL_DEPT_NM, voCoDept.getDeptNm());
        ttObjParamsSql.put(VoCoDept.COL_DEPT_EN, voCoDept.getDeptNmEn());
        ttObjParamsSql.put(VoCoDept.COL_DEPT_EXP, voCoDept.getDeptExp());
        ttObjParamsSql.put(VoCoDept.COL_DEPT_EML, "");
        ttObjParamsSql.put(VoCoDept.COL_DEPT_TEL_NO, "");
        ttObjParamsSql.put(VoCoDept.COL_DEPT_ZIP_CD, "");
        ttObjParamsSql.put(VoCoDept.COL_DEPT_ZIP_ADDR1, "");
        ttObjParamsSql.put(VoCoDept.COL_DEPT_ZIP_ADDR2, "");
        ttObjParamsSql.put(VoCoDept.COL_DEPT_TP, "1");
        ttObjParamsSql.put(VoCoDept.COL_REG_DM, sysdate);
        ttObjParamsSql.put(VoCoDept.COL_UPD_DM, "");
        ttObjParamsSql.put(VoCoDept.COL_USE_YN, "1");
        ttObjParamsSql.put(VoCoDept.COL_USER_ID, voCoDept.getUserId());

        daoCoDept.insertDeptInfo(ttObjParamsSql);
    }

    /**
     * 부서코드 중복을 조회한다.
     * @param ttObjParamsSql 검색정보
     * @return 체크 결과 -1:사용불가, 0:검색문자열"", 1:사용불가
     */
    public int checkDeptCd(CoTtObjParams ttObjParamsSql) {

        int rsCnt = daoCoDept.checkDeptCd(ttObjParamsSql);

        return rsCnt;
    }


    /**
     * 부서검색리스트 취득 <br />
     * @param ttObjParams 검색 파라미터
     * @return 검색리스트
     */
    public List<VoCoDept> getDeptSearchListAll(CoTtObjParams ttObjParams) {

        //VoCoDept voCoDept = (VoCoDept) ttObjParams.get("voCoDept");

        //페이징파라미터설정
        //CoTtObjParams ttObjParamsSql = getParamsObjOfPageInfoSetted(voCoDept.getFirstIndex(), voCoDept.getRecordCountPerPage());
        //ttObjParamsSql.put("searchDeptCd", UtCoStringUtils.trim(voCoDept.getSearchDeptCd()));
        //ttObjParamsSql.put("searchDeptNm", UtCoStringUtils.trim(voCoDept.getSearchDeptNm()));
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        return daoCoDept.getDeptSearchListAll(ttObjParamsSql);
    }

    /**
     * 부서에 속한 user list 총 갯수 <br />
     * @param ttObjParams 검색 파라미터
     * @return 토탈카운트
     */
    public int getUserYnCnt(CoTtObjParams ttObjParams) {
        VoCoDept voCoDept = (VoCoDept) ttObjParams.get("voCoDept");
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        int result = 0;
        if (!UtCoStringUtils.isEmpty(voCoDept.getDelArr())) {
            String[] chkUser = voCoDept.getDelArr().split(",");

            for (int i = 0; i < chkUser.length; ++i) {
                ttObjParamsSql.put("deptNo", Integer.parseInt(chkUser[i]));
                result = daoCoDept.getUserYnCnt(ttObjParamsSql);
                if (result > 0) {
                    break;
                }
            }
        }
        return result;
    }



}